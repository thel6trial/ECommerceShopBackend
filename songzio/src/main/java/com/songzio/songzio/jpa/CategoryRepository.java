package com.songzio.songzio.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category_seq WHERE category_name = :categoryName", nativeQuery = true)
	public Optional<Category> findByCategoryName(@Param("categoryName")String categoryName);
	
	@Query(value = "SELECT * FROM category_seq ORDER BY category_count DESC", nativeQuery = true)
	public List<Category> findAllCategories();
}
