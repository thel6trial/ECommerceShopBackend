package com.songzio.songzio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Order;
import com.songzio.songzio.model.Order_Product;

@Repository
public interface Order_ProductRepository extends JpaRepository<Order_Product, Integer>{

	@Query(value = "SELECT * FROM order_product_seq  WHERE order_id = :orderID", nativeQuery = true)
	public List<Order_Product> findByOrderId(@Param("orderID") int orderID);

}
