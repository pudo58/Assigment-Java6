package com.nguyennt.app.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nguyennt.app.DAO.CategoryDAO;
import com.nguyennt.app.Entity.Category;
@RestController
public class CategoryController {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@GetMapping("/category")
	public List<Category> getAll(){
		return categoryDAO.findAll();
	}
	@GetMapping("/category/{categoryId}")
	public Category get(@PathVariable Long categoryId ) throws Exception {
		Category admin=categoryDAO.findById(categoryId)
				.orElseThrow(()-> new Exception("id not found :"+categoryId));
		return admin;
	}
	@PostMapping(produces = "application/json",value = "/category/create")
	public Category save(@RequestBody Category bill) {
		return categoryDAO.save(bill);
	}
	@PutMapping(produces = "application/json", value = "/category/update/{category_id}")
	public Category update(@RequestBody Category category,@PathVariable Long category_id) {
		return categoryDAO.findById(category_id).map(
				e->{
					e.setName(category.getName());
					e.setStatus(category.getStatus());
					return categoryDAO.save(e);
				}).orElseGet(() -> {
					category.setCategoryId(category_id);
			        return categoryDAO.save(category);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/category/delete/{category_id}")
	void Delete(@PathVariable Long category_id) {
		categoryDAO.deleteById(category_id);
		
	}
}
