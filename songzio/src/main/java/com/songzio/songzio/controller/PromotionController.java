package com.songzio.songzio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.songzio.songzio.formDTO.OrderPromotionFormDTO;
import com.songzio.songzio.formDTO.PromotionCodeRequestDTO;
import com.songzio.songzio.formDTO.PromotionFormDTO;
import com.songzio.songzio.jpa.PromotionRepository;
import com.songzio.songzio.jpa.UserRepository;
import com.songzio.songzio.jpa.User_LoyaltyRepository;
import com.songzio.songzio.jpa.User_PromotionRepository;
import com.songzio.songzio.model.Promotion;
import com.songzio.songzio.model.User;
import com.songzio.songzio.model.User_Loyalty;
import com.songzio.songzio.model.User_Promotion;

@RestController
@RequestMapping("/")
public class PromotionController {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User_PromotionRepository user_PromotionRepository;
	
	@Autowired
	private User_LoyaltyRepository user_LoyaltyRepository;

	public PromotionController(PromotionRepository promotionRepository,
			User_PromotionRepository user_PromotionRepository, User_LoyaltyRepository user_LoyaltyRepository, UserRepository userRepository) {
		super();
		this.promotionRepository = promotionRepository;
		this.user_PromotionRepository = user_PromotionRepository;
		this.user_LoyaltyRepository = user_LoyaltyRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(path="/promotions", method=RequestMethod.GET)
	public List<Promotion> getAllPromotion() {
		return promotionRepository.findAll();
	}
	
	@GetMapping(path="/promotions/{id}")
	public Promotion getPromotionById(@PathVariable("id") int promotionID) {
		return promotionRepository.findById(promotionID).get();
	}
	
	@PostMapping(path="/promotions/code")
	public Promotion getPromotionByCode(@RequestBody PromotionCodeRequestDTO promotionCodeRequestDTO) {
		return promotionRepository.findByPromotionCode(promotionCodeRequestDTO.getPromotionCode());
	}
	
	@GetMapping(path="/loyalty/{loyaltyID}")
	public List<Promotion> getPromotionLoyaltyId(@PathVariable("loyaltyID") int loyaltyID) {
		return promotionRepository.findByLoyaltyID(loyaltyID);
	}
	
	@PostMapping(path="/promotions")
	public Promotion createPromotion(@RequestBody PromotionFormDTO promotionFormDTO) {
		
		Promotion promotion = new Promotion();
		
		String loyaltyName = promotionFormDTO.getLoyaltyName();
		User_Loyalty user_Loyalty = user_LoyaltyRepository.findByUser_LoyaltyName(loyaltyName).get();
		
		promotion.setUser_loyalty(user_Loyalty);
		promotion.setPromotionID(null);
		promotion.setPromotionName(promotionFormDTO.getPromotionName());
		promotion.setPromotionCode(promotionFormDTO.getPromotionCode());
		promotion.setPromotionType(promotionFormDTO.getPromotionType());
		promotion.setPromotionNumber(promotionFormDTO.getPromotionNumber());
		promotion.setPromotionCount(promotionFormDTO.getPromotionCount());
		promotion.setPromotionFlag(true);
		promotionRepository.save(promotion);
		
		// save to User_Promotion
		List<User> users = userRepository.findByLoyaltyID(user_Loyalty.getLoyaltyID());
		for(User user: users) {
			User_Promotion user_Promotion = new User_Promotion();
			user_Promotion.setUser(user);
			user_Promotion.setPromotion(promotion);
			user_Promotion.setIsUsed(false);
			user_PromotionRepository.save(user_Promotion);
		}
		
		return promotion;
	}
	
	@PutMapping(path="/promotions/{id}")
	public Promotion updatePromotion(@PathVariable("id") int promotionID, @RequestBody PromotionFormDTO promotionFormDTO) {
		
		Promotion promotion = promotionRepository.findById(promotionID).get();
		
		String loyaltyName = promotionFormDTO.getLoyaltyName();
		User_Loyalty user_Loyalty = user_LoyaltyRepository.findByUser_LoyaltyName(loyaltyName).get();
		
		promotion.setUser_loyalty(user_Loyalty);
		promotion.setPromotionName(promotionFormDTO.getPromotionName());
		promotion.setPromotionCode(promotionFormDTO.getPromotionCode());
		promotion.setPromotionType(promotionFormDTO.getPromotionType());
		promotion.setPromotionNumber(promotionFormDTO.getPromotionNumber());
		promotion.setPromotionCount(promotionFormDTO.getPromotionCount());
		return promotionRepository.save(promotion);
	}
	
	@PostMapping(path="/promotions/deactivate/{id}")
	public Promotion deletePromotion(@PathVariable("id") Integer promotionID) {
		Promotion promotion = promotionRepository.findById(promotionID).get();
		promotion.setPromotionFlag(false);
		return promotionRepository.save(promotion);
	}
	
	@PostMapping(path="/promotions/activate/{id}")
	public Promotion activatePromotion(@PathVariable("id") Integer promotionID) {
		Promotion promotion = promotionRepository.findById(promotionID).get();
		promotion.setPromotionFlag(true);
		return promotionRepository.save(promotion);
	}
	
	@GetMapping(path="/user/promotion/{id}")
	public List<User_Promotion> getUserPromotionsByUserID(@PathVariable("id") int userID){
		
		List<User_Promotion> user_promotions = user_PromotionRepository.findByUserID(userID);
		
		return user_promotions;
	}
	
}
