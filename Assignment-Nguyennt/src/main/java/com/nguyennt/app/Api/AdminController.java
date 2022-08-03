package com.nguyennt.app.Api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nguyennt.app.DAO.AdminDAO;
import com.nguyennt.app.Entity.Admin;

@CrossOrigin(maxAge = 6300,methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class AdminController {
	@Autowired
	private AdminDAO adminDAO;
	
	@CrossOrigin()
	@GetMapping("/admin")
	public List<Admin> getAll(){
		return adminDAO.findAll();
	}
	@GetMapping("/admin/{adminId}")
	public Admin get(@PathVariable Long adminId ) throws Exception {
		Admin admin=adminDAO.findById(adminId)
				.orElseThrow(()-> new Exception("id not found :"+adminId));
		return admin;
	}
	@PostMapping(produces = "application/json",value = "/admin/create")
	public Admin save(@RequestBody Admin admin) {
		return adminDAO.save(admin);
	}
	@PutMapping(produces = "application/json", value = "/admin/update/{admin_id}")
	public Admin update(@RequestBody Admin admin,@PathVariable Long admin_id) {
		return adminDAO.findById(admin_id).map(
				e->{
					e.setAddress(admin.getAddress());
					e.setEmail(admin.getEmail());
					e.setPassword(admin.getPassword());
					return adminDAO.save(e);
				}).orElseGet(() -> {
			        admin.setAdminId(admin_id);
			        return adminDAO.save(admin);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/admin/delete/{admin_id}")
	void Delete(@PathVariable Long admin_id) {
		adminDAO.deleteById(admin_id);
		
	}

}
