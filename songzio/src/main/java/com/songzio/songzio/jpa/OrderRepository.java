package com.songzio.songzio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Order;

import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query(value = "SELECT * FROM order_seq WHERE order_price = 0", nativeQuery = true)
	public Order findByPriceZero();
	
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM order_seq os WHERE os.order_price = 0", nativeQuery = true)
    public void deleteBy0();
	
	@Query(value = "SELECT * FROM order_seq WHERE order_price != 0", nativeQuery = true)
	public List<Order> findByDiff0();

	@Query(value= "SELECT * FROM order_seq WHERE user_id = :userID AND order_price != 0 ORDER BY order_id DESC LIMIT 1", nativeQuery = true)
	public Order getNewestOrderByUserID(int userID);

}
