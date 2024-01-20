package com.songzio.songzio.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.songzio.songzio.formDTO.ProductFormDTO;
import com.songzio.songzio.jpa.CategoryRepository;
import com.songzio.songzio.jpa.Order_ProductRepository;
import com.songzio.songzio.jpa.ProductRepository;
import com.songzio.songzio.jpa.PromotionRepository;
import com.songzio.songzio.model.Category;
import com.songzio.songzio.model.Product;
import com.songzio.songzio.model.Promotion;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Order_ProductRepository order_ProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;

	public ProductController(CategoryRepository categoryRepository, Order_ProductRepository order_ProductRepository,
			PromotionRepository promotionRepository, ProductRepository productRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.order_ProductRepository = order_ProductRepository;
		this.promotionRepository = promotionRepository;
		this.productRepository = productRepository;
	}
	
	@RequestMapping(path="/products", method=RequestMethod.GET)
	public List<Product> retreiveAllProducts(){
		return productRepository.findAllProducts();
	}
	
	@RequestMapping(path="products/{id}", method=RequestMethod.GET)
	public Product findProductById(@PathVariable("id") int productID) {
		Optional<Product> optionalProduct =  productRepository.findById(productID);
		if(optionalProduct != null) {
			return optionalProduct.get();
		}else {
			return null;
		}
	}
			
	@PostMapping(path="/products")
	public Product createProduct(@RequestBody ProductFormDTO productFormDTO) {
		
		Product product = new Product();
		String categoryName = productFormDTO.getCategoryName();
		
		Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
		
		
		if(optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			product.setCategory(category);
		}
		
		product.setProductName(productFormDTO.getProductName());
		product.setProductPrice(productFormDTO.getProductPrice());
		product.setProductStock(productFormDTO.getProductStock());
		product.setProductImage(productFormDTO.getProductImage());
		product.setProductID(null);
		product.setProductCount(0);
		return productRepository.save(product);
	}
	
	@DeleteMapping(path="/products/{id}")
	public void deleteProduct(@PathVariable("id") int productID) {
		productRepository.deleteById(productID);
	}
	
	@RequestMapping(path="/products/{id}", method=RequestMethod.PUT)
	public Product updateProduct(@PathVariable("id") int productID, @RequestBody ProductFormDTO productFormDTO) {
		Optional<Product> optionalProduct = productRepository.findById(productID);
		Product product = optionalProduct.get();
		
		String categoryName = productFormDTO.getCategoryName();
		
		Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
		
		if(optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			product.setCategory(category);
		}
		
		product.setProductName(productFormDTO.getProductName());
		product.setProductPrice(productFormDTO.getProductPrice());
		product.setProductStock(productFormDTO.getProductStock());
		product.setProductStock(product.getProductStock());
		product.setProductImage(productFormDTO.getProductImage());
		return productRepository.save(product);
	}
	
	@RequestMapping(path="/products/search/name", method=RequestMethod.POST)
	public List<Product> findProductByName(@RequestBody String encodeProductName){
		String productName = URLDecoder.decode(encodeProductName, StandardCharsets.UTF_8);
		return productRepository.findByProductName(productName);
	}
	
	@RequestMapping(path="products/search/category", method= RequestMethod.POST)
	public List<Product> findProductByCategory(@RequestBody String encodeCategoryName){
		String categoryName = URLDecoder.decode(encodeCategoryName, StandardCharsets.UTF_8);
		return productRepository.findByCategoryName(categoryName);
	}
	
	@GetMapping(path="products/hint")
	public List<Product> findProductsByMostCategory(){
		return productRepository.findByMostCategory();
	}
	
}
