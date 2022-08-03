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

import com.nguyennt.app.DAO.ProductTreeDAO;
import com.nguyennt.app.Entity.ProductTree;

@RestController
public class ProductTreeController {
	@Autowired
	private ProductTreeDAO productTreeDAO;
	
	@GetMapping("/product")
	public List<ProductTree> getAll(){
		return productTreeDAO.findAll();
	}
	@GetMapping("/product/{billId}")
	public ProductTree get(@PathVariable Long productTreeId ) throws Exception {
		ProductTree bill=productTreeDAO.findById(productTreeId)
				.orElseThrow(()-> new Exception("id not found :"+productTreeId));
		return bill;
	}
	@PostMapping(produces = "application/json",value = "/product/create")
	public ProductTree save(@RequestBody ProductTree productTree) {
		return productTreeDAO.save(productTree);
	}
	@PutMapping(produces = "application/json", value = "/product/update/{productTreeId}")
	public ProductTree update(@RequestBody ProductTree productTree,@PathVariable Long productTreeId) {
		return productTreeDAO.findById(productTreeId).map(
				e->{
					e.setDescribe(productTree.getDescribe());
					e.setName(productTree.getName());
					e.setPrice(productTree.getPrice());
					e.setSize(productTree.getSize());
					e.setQuantity(productTree.getQuantity());
					e.setSale(productTree.getSale());		
					e.setStatus(productTree.getStatus());
					return productTreeDAO.save(e);
				}).orElseGet(() -> {
					productTree.setTreeId(productTreeId);
			        return productTreeDAO.save(productTree);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/product/delete/{productTreeId}")
	void Delete(@PathVariable Long productTreeId) {
		productTreeDAO.deleteById(productTreeId);
		
	}
}
