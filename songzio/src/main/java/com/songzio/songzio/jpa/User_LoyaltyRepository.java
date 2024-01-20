package com.songzio.songzio.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.User_Loyalty;

@Repository
public interface User_LoyaltyRepository extends JpaRepository<User_Loyalty, Integer>{
	
	@Query(value = "SELECT * FROM user_loyalty_seq WHERE loyalty_name = :user_loyalty_name", nativeQuery = true)
	public Optional<User_Loyalty> findByUser_LoyaltyName(@Param("user_loyalty_name") String user_loyalty_name);
	
}
