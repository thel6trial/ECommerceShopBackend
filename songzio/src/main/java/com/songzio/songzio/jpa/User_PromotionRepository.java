package com.songzio.songzio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Product;
import com.songzio.songzio.model.User_Promotion;

import jakarta.transaction.Transactional;

@Repository
public interface User_PromotionRepository extends JpaRepository<User_Promotion, Integer>{
	
	@Query(value = "SELECT * FROM user_promotion_seq WHERE user_id = :userID", nativeQuery = true)
	public List<User_Promotion> findByUserID(int userID);
	
	@Query(value = "SELECT * FROM user_promotion_seq WHERE user_id = :userID AND promotion_id = :promotionID", nativeQuery = true)
	public User_Promotion findByUserPromotionID(int userID, int promotionID);
	
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM user_promotion_seq WHERE user_id = :userID", nativeQuery = true)
	public void deleteUserId(int userID);

}
