package com.songzio.songzio.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{

	public Optional<Promotion> findByPromotionName(String promotionName);
	
	@Query(value = "SELECT * FROM promotion_seq  WHERE promotion_code = :promotionCode", nativeQuery = true)
	public Promotion findByPromotionCode(String promotionCode);

	@Query(value = "SELECT * FROM promotion_seq  WHERE loyalty_id = :loyaltyID", nativeQuery = true)
	public List<Promotion> findByLoyaltyID(int loyaltyID);

}
