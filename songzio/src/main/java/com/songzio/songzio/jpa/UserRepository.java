package com.songzio.songzio.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "SELECT * FROM user_seq WHERE username = :username AND user_password = :user_password", nativeQuery = true)
    public User Authenticate(@Param("username") String username, @Param("user_password") String user_password);
	
	@Query(value = "SELECT * FROM user_seq WHERE loyalty_id = :loyaltyID", nativeQuery = true)
    public List<User> findByLoyaltyID(int loyaltyID);

}
