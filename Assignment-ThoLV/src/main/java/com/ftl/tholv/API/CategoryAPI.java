package com.ftl.tholv.API;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftl.tholv.DAO.CategoryDAO;
import com.ftl.tholv.Entity.Category;
@RestController
public class CategoryAPI {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@GetMapping("/category")
	public List<Category> getAll(){
		return categoryDAO.findAll();
	}
	@GetMapping("/category/{category_id}")
	public Category get(@PathVariable Long category_id ) throws Exception {
		Category au=categoryDAO.findById(category_id)
				.orElseThrow(()-> new Exception("id not found :"+category_id));
		return au;
	}
	@PostMapping(produces = "application/json",value = "/category/create")
	public Category save(@RequestBody Category acc) {
		return categoryDAO.save(acc);
	}
	@PutMapping(produces = "application/json", value = "/category/update/{category_id}")
	public Category update(@RequestBody Category cate,@PathVariable Long category_id) {
		return categoryDAO.findById(category_id).map(
				e->{
					e.setName(cate.getName());
					e.setStatus(cate.getStatus());
					e.setModified(new Date());
					e.setModifier(cate.getModifier());
					return categoryDAO.save(e);
				}).orElseGet(() -> {
			        cate.setCategoryId(category_id);
			        return categoryDAO.save(cate);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/category/delete/{category_id}")
	void Delete(@PathVariable Long category_id) {
		categoryDAO.deleteById(category_id);
		
	}
}
