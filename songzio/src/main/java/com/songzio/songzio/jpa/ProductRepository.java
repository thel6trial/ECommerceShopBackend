package com.songzio.songzio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Category;
import com.songzio.songzio.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT * FROM product_seq WHERE product_name = :productName", nativeQuery = true)
	public List<Product> findByProductName(String productName);
	
	@Query(value = "SELECT * FROM product_seq WHERE product_stock > 0", nativeQuery = true)
	public List<Product> findStock(int productStock);
	
	@Query(value = "SELECT * FROM product_seq WHERE product_price > :min AND product_price < :max", nativeQuery = true)
	public List<Product> findByPriceRange(@Param("min") int min, @Param("max") int max);
	
	public List<Product> findByCategory(Category category);

	@Query(value = "SELECT * FROM product_seq p WHERE p.category_id IN (SELECT category_id FROM category_seq c WHERE c.category_name = :categoryName)", nativeQuery = true)
	public List<Product> findByCategoryName(String categoryName);
	
	@Query(value = "SELECT * FROM product_seq ORDER BY product_count DESC", nativeQuery = true)
	public List<Product> findAllProducts();

	@Query(value = "SELECT * FROM product_seq\n"
			+ "WHERE category_id = (\n"
			+ "    SELECT category_id\n"
			+ "    FROM category_seq\n"
			+ "    ORDER BY category_count DESC\n"
			+ "    LIMIT 1\n"
			+ ");", nativeQuery = true)
	public List<Product> findByMostCategory();
}
