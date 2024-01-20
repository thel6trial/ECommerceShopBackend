package com.songzio.songzio.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.songzio.songzio.formDTO.OrderFormDTO;
import com.songzio.songzio.formDTO.ProductFormDTO;
import com.songzio.songzio.formDTO.QuantityFormDTO;
import com.songzio.songzio.jpa.CategoryRepository;
import com.songzio.songzio.jpa.OrderRepository;
import com.songzio.songzio.jpa.Order_ProductRepository;
import com.songzio.songzio.jpa.ProductRepository;
import com.songzio.songzio.jpa.PromotionRepository;
import com.songzio.songzio.jpa.UserRepository;
import com.songzio.songzio.jpa.User_LoyaltyRepository;
import com.songzio.songzio.jpa.User_PromotionRepository;
import com.songzio.songzio.model.Category;
import com.songzio.songzio.model.Order;
import com.songzio.songzio.model.Order_Product;
import com.songzio.songzio.model.Product;
import com.songzio.songzio.model.Promotion;
import com.songzio.songzio.model.User;
import com.songzio.songzio.model.User_Loyalty;
import com.songzio.songzio.model.User_Promotion;

@RestController
@RequestMapping("/")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private Order_ProductRepository order_ProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User_LoyaltyRepository user_LoyaltyRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private User_PromotionRepository user_PromotionRepository;
	
	public OrderController(OrderRepository orderRepository, Order_ProductRepository order_ProductRepository,
			ProductRepository productRepository, UserRepository userRepository, User_LoyaltyRepository user_LoyaltyRepository,
			PromotionRepository promotionRepository, User_PromotionRepository user_PromotionRepository, CategoryRepository categoryRepository) {
		super();
		this.orderRepository = orderRepository;
		this.order_ProductRepository = order_ProductRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.user_LoyaltyRepository = user_LoyaltyRepository;
		this.promotionRepository = promotionRepository;
		this.user_PromotionRepository = user_PromotionRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping(path="/orders")
	public List<Order> retrieveAllOrders(){
		return orderRepository.findAll();
	}
	
	@GetMapping(path="/orders/{id}")
	public Order retrieveOrderById(@PathVariable("id") int orderID) {
		return orderRepository.findById(orderID).get();
	}
	
	@GetMapping(path="/order_product/{id}")
	public List<Order_Product> retrieveOrderProductByOrderId(@PathVariable("id") int orderID) {
		return order_ProductRepository.findByOrderId(orderID);
	}

	@RequestMapping(path="/products/order/{id}", method=RequestMethod.POST)
	public Order_Product buyProduct(@PathVariable("id") int productID, @RequestBody QuantityFormDTO quantityFormDTO) {
		
		Optional<Product> optionalProduct =  productRepository.findById(productID);
		
		Product product = optionalProduct.orElseThrow(() -> new IllegalArgumentException("Product not found"));
		
		
		Order order = orderRepository.findByPriceZero();
		
		Order_Product order_Product = new Order_Product();
		order_Product.setId(null);
		order_Product.setQuantity(quantityFormDTO.getQuantity());
		
		if(product.getOrder_Products() == null) {
			product.setOrder_Products(new ArrayList<>());
		}
		
		if(order.getOrder_Products() == null) {
			order.setOrder_Products(new ArrayList<>());		
		}
		
		order_Product.setOrder(order);
		order_Product.setProduct(product);
		
		product.getOrder_Products().add(order_Product);
		order.getOrder_Products().add(order_Product);
		
		productRepository.save(product);
		orderRepository.save(order);
		return order_ProductRepository.save(order_Product);
	}
	
	private int calculatePrice(int quantity, int pricePerItem) {
        return pricePerItem * quantity;
    }
	// flow: buyProduct => cart => checkout => order success to back to main page
	
	@GetMapping("/cart")
	public List<Order_Product> cart(){
        Order order = orderRepository.findByPriceZero();
		
		List<Order_Product> order_Products = order.getOrder_Products();
		return order_Products;
	}
	
	
	@PostMapping("/checkout")
	public Order checkoutOrder(@RequestBody OrderFormDTO orderFormDTO) {
		int total_price = 0;
		
		Order order = orderRepository.findByPriceZero();
		User user = order.getUser();
		List<Order_Product> order_Products = order.getOrder_Products();
		for(Order_Product order_Product: order_Products) {
			Product product = order_Product.getProduct();
			total_price += calculatePrice(order_Product.getQuantity(), Integer.parseInt(product.getProductPrice()));
			product.setProductStock(product.getProductStock() - order_Product.getQuantity());
			product.setProductCount(product.getProductCount() + 1);
			productRepository.save(product);
			
			Category category = categoryRepository.findById(product.getCategory().getCategoryID()).get();
			category.setCategoryCount(category.getCategoryCount() + 1);
			categoryRepository.save(category);
		}
		
		if(orderFormDTO.getOrderDiscountCode() == "") {
			order.setOrderPrice(total_price);
			order.setOrderDiscount(0);
		}else {
			Promotion promotion = promotionRepository.findByPromotionCode(orderFormDTO.getOrderDiscountCode());
			promotion.setPromotionCount(promotion.getPromotionCount() - 1);
			
			int discount = promotion.getPromotionNumber();
			order.setOrderDiscount(discount * total_price /100);
			order.setOrderPrice(total_price - discount * total_price /100);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			User_Promotion user_Promotion = user_PromotionRepository.findByUserPromotionID(user.getUserID(), promotion.getPromotionID());
			user_Promotion.setIsUsed(true);
			user_Promotion.setTimeUsed(LocalTime.now().format(formatter));
			user_PromotionRepository.save(user_Promotion);
			promotionRepository.save(promotion);
		}
		order.setOrderPay(orderFormDTO.getOrderPay());
		orderRepository.save(order);
		return order;
	}
	
	@GetMapping("/success/{id}")
	public Order orderSuccess(@PathVariable("id") Integer userID) {
		Order order = new Order();
		order.setOrderID(null);
		
		User user = userRepository.findById(userID).get();
		int total = user.getUser_total_payment();
		for(Order usedOrder: user.getOrders()) {
			total += usedOrder.getOrderPrice();
		}
		
		if(total > 5000000 && user.getUser_total_payment() <= 5000000) {
			User_Loyalty user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName("Vang").get();
			updatePromotion(userID, user_loyalty);
			user.setUser_loyalty(user_loyalty);
		} else if(total > 7000000 && user.getUser_total_payment() <= 7000000) {
			User_Loyalty user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName("Kim Cuong").get();
			updatePromotion(userID, user_loyalty);
			user.setUser_loyalty(user_loyalty);
		}
		
		user.setUser_total_payment(total);
		order.setUser(user);
		order.setOrderPrice(0);
		order.setOrderDiscount(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		order.setOrderTime(LocalTime.now().format(formatter));
		
		user.getOrders().add(order);
		userRepository.save(user);
		return orderRepository.save(order);
	}
	
    public void updatePromotion(int userID, User_Loyalty user_loyalty) {
    	user_PromotionRepository.deleteUserId(userID);
    	User user = userRepository.findById(userID).get();
    	
    	// Set Promotion for this User
    	List<Promotion> promotions = promotionRepository.findByLoyaltyID(user_loyalty.getLoyaltyID());
    	for(Promotion promotion: promotions) {
    		User_Promotion user_Promotion = new User_Promotion();
    		user_Promotion.setUser(user);
    		user_Promotion.setPromotion(promotion);
    		user_Promotion.setUserPromotionID(null);
    		user_Promotion.setIsUsed(false);
    				
    		user_PromotionRepository.save(user_Promotion);
    	}
    }
    
    @GetMapping(path="/order/{userID}")
    public Order getNewestOrderByUserID(@PathVariable("userID") int userID) {
    	return orderRepository.getNewestOrderByUserID(userID);
    }
}

