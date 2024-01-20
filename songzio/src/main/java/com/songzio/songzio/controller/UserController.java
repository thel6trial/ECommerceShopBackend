package com.songzio.songzio.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.songzio.songzio.formDTO.RegisterFormDTO;
import com.songzio.songzio.formDTO.UserFormDTO;
import com.songzio.songzio.jpa.OrderRepository;
import com.songzio.songzio.jpa.ProductRepository;
import com.songzio.songzio.jpa.PromotionRepository;
import com.songzio.songzio.jpa.UserRepository;
import com.songzio.songzio.jpa.User_LoyaltyRepository;
import com.songzio.songzio.jpa.User_PromotionRepository;
import com.songzio.songzio.jpa.User_RoleRepository;
import com.songzio.songzio.model.Order;
import com.songzio.songzio.model.Product;
import com.songzio.songzio.model.Promotion;
import com.songzio.songzio.model.User;
import com.songzio.songzio.model.User_Loyalty;
import com.songzio.songzio.model.User_Promotion;
import com.songzio.songzio.model.User_Role;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User_RoleRepository user_RoleRepository;
	
	@Autowired
	private User_LoyaltyRepository user_LoyaltyRepository;
	
	@Autowired
	private User_PromotionRepository user_PromotionRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	public UserController(UserRepository userRepository, User_RoleRepository user_RoleRepository,
			User_LoyaltyRepository user_LoyaltyRepository, User_PromotionRepository user_PromotionRepository, OrderRepository orderRepository,
			PromotionRepository promotionRepository) {
		super();
		this.userRepository = userRepository;
		this.user_RoleRepository = user_RoleRepository;
		this.user_LoyaltyRepository = user_LoyaltyRepository;
		this.user_PromotionRepository = user_PromotionRepository;
		this.orderRepository = orderRepository;
		this.promotionRepository = promotionRepository;
	}
	
	@RequestMapping(path="/users", method=RequestMethod.GET)
	public List<User> retreiveAllUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(path="users/{id}", method=RequestMethod.GET)
	public User findUserById(@PathVariable("id") int userID) {
		Optional<User> optionalUser =  userRepository.findById(userID);
		if(optionalUser != null) {
			return optionalUser.get();
		}else {
			return null;
		}
	}
	
	@RequestMapping(path="/info", method=RequestMethod.GET)
	public Integer findUserBy0() {
		Order order = orderRepository.findByPriceZero();
		User user = order.getUser();
		return user.getUserID();
	}
			
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public User createUser(@RequestBody UserFormDTO userFormDTO) {
		User user = new User();
		
		String user_role_name = userFormDTO.getUser_role_name();
		
		Optional<User_Role> optional_user_role = user_RoleRepository.findByUser_RoleName(user_role_name);
		
		if(optional_user_role.isPresent()) {
			User_Role user_role = optional_user_role.get();
			user.setUser_role(user_role);
		}
		
		if(user.getUser_role().getRoleID() == 1) {
			Optional<User_Loyalty> optional_user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName("Admin");
			User_Loyalty user_loyalty = optional_user_loyalty.get();
			user.setUser_loyalty(user_loyalty);
			
			user.setUserID(null);
			user.setUsername(userFormDTO.getUsername());
			user.setUser_password(userFormDTO.getUser_password());
			user.setUser_birthday(userFormDTO.getUser_birthday());
			user.setUser_phone(userFormDTO.getUser_phone());
			user.setUser_total_payment(0);
		    userRepository.save(user);
		}else {
			Optional<User_Loyalty> optional_user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName("Khach hang");
			User_Loyalty user_loyalty = optional_user_loyalty.get();
			user.setUser_loyalty(user_loyalty);
			
			user.setUserID(null);
			user.setUsername(userFormDTO.getUsername());
			user.setUser_password(userFormDTO.getUser_password());
			user.setUser_birthday(userFormDTO.getUser_birthday());
			user.setUser_phone(userFormDTO.getUser_phone());
			user.setUser_total_payment(0);
		    userRepository.save(user);
		    
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
		return user;
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public User registerUser(@RequestBody RegisterFormDTO registerFormDTO) {
		User user = new User();
				
		Optional<User_Role> optional_user_role = user_RoleRepository.findByUser_RoleName("Guest");
		
		User_Role user_role = optional_user_role.get();
		user.setUser_role(user_role);
		
		Optional<User_Loyalty> optional_user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName("Khach hang");
		User_Loyalty user_loyalty = optional_user_loyalty.get();
		user.setUser_loyalty(user_loyalty);
			
		user.setUserID(null);
		user.setUsername(registerFormDTO.getUsername());
		user.setUser_password(registerFormDTO.getUser_password());
		user.setUser_birthday(registerFormDTO.getUser_birthday());
		user.setUser_phone(registerFormDTO.getUser_phone());
		user.setUser_total_payment(0);
		userRepository.save(user);
		    
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
			
		return user;
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable("id") int userID) {
		userRepository.deleteById(userID);
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.PUT)
	public User updateUser(@PathVariable("id") int userID, @RequestBody UserFormDTO userFormDTO) {

		Optional<User> optionalUser = userRepository.findById(userID);
		User user = optionalUser.get();
		String user_role_name = userFormDTO.getUser_role_name();
		String user_loyalty_name = userFormDTO.getUser_loyalty_name();
		
		Optional<User_Role> optional_user_role = user_RoleRepository.findByUser_RoleName(user_role_name);
		Optional<User_Loyalty> optional_user_loyalty = user_LoyaltyRepository.findByUser_LoyaltyName(user_loyalty_name);
		
		if(optional_user_role.isPresent()) {
			User_Role user_role = optional_user_role.get();
			user.setUser_role(user_role);
		}
		
		if(optional_user_loyalty.isPresent()) {
			User_Loyalty user_loyalty = optional_user_loyalty.get();
			user.setUser_loyalty(user_loyalty);
		}
		
		user.setUsername(userFormDTO.getUsername());
		user.setUser_password(userFormDTO.getUser_password());
		user.setUser_birthday(userFormDTO.getUser_birthday());
		user.setUser_phone(userFormDTO.getUser_phone());
		user.setUser_total_payment(userFormDTO.getUser_total_payment());
		return userRepository.save(user);
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		
		orderRepository.deleteBy0();
		// order has price = 0 so delete to get a new order
		
		String username = user.getUsername(); 
		String user_password = user.getUser_password();
		// Authenticate username and password
	    User existingUser = userRepository.Authenticate(username, user_password);
        if (existingUser != null) {
            // Đăng nhập thành công
        	if(existingUser.getUser_role().getRoleID() == 0) {
        		Order order = new Order();
        		order.setOrderID(null);
        		order.setOrderPay(null);
        		order.setOrderPrice(0);
        		order.setOrderDiscount(0);
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        		order.setOrderTime(LocalTime.now().format(formatter));
        		order.setUser(existingUser);
        		if(existingUser.getOrders() == null) {
        			existingUser.setOrders(new ArrayList<>());
        		}
        		existingUser.getOrders().add(order);
        		
        		orderRepository.save(order);
        	}
            return ResponseEntity.ok().body(existingUser.getUser_role().getRoleID());
        } else {
            // Đăng nhập thất bại
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
