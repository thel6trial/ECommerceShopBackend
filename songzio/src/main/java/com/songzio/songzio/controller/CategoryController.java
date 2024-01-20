package com.songzio.songzio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.songzio.songzio.formDTO.CategoryFormDTO;
import com.songzio.songzio.formDTO.ProductFormDTO;
import com.songzio.songzio.jpa.CategoryRepository;
import com.songzio.songzio.model.Category;
import com.songzio.songzio.model.Product;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class CategoryController {
	
	public CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@GetMapping(path="/category")
	public List<Category> retrieveAllCategories(){
		return categoryRepository.findAllCategories();
	}
	

	@RequestMapping(path="category/{id}", method=RequestMethod.GET)
	public Category findProductById(@PathVariable("id") int categoryID) {
		
		return categoryRepository.findById(categoryID).get();
	}
	
	@DeleteMapping(path="/category/{id}")
	public void deleteCategoryByID(@PathVariable("id") int category_id) {
		categoryRepository.deleteById(category_id);
	}
	
	@PutMapping(path="/category/{id}")
	public Category updateCategory(@PathVariable("id") int category_id, @RequestBody CategoryFormDTO categoryFormDTO) {
		Category category = new Category();
		
		category.setCategoryID(null);
		category.setCategoryName(categoryFormDTO.getCategoryName());
		category.setCategoryCount(categoryFormDTO.getCategoryCount());
		
		return categoryRepository.save(category);
	}
	
	@PostMapping(path="/category")
	public Category createCategory(@RequestBody CategoryFormDTO categoryFormDTO) {
        Category category = new Category();
		
		category.setCategoryID(null);
		category.setCategoryName(categoryFormDTO.getCategoryName());
		category.setCategoryCount(0);
		
		return categoryRepository.save(category);
	}

}
