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

import com.nguyennt.app.DAO.StaffDAO;
import com.nguyennt.app.Entity.Staff;


@RestController
public class StaffController {
	@Autowired
	private StaffDAO categoryDAO;
	
	@GetMapping("/staff")
	public List<Staff> getAll(){
		return categoryDAO.findAll();
	}
	@GetMapping("/staff/{staffId}")
	public Staff get(@PathVariable Long staffId ) throws Exception {
		Staff staff=categoryDAO.findById(staffId)
				.orElseThrow(()-> new Exception("id not found :"+staffId));
		return staff;
	}
	@PostMapping(produces = "application/json",value = "/staff/create")
	public Staff save(@RequestBody Staff bill) {
		return categoryDAO.save(bill);
	}
	@PutMapping(produces = "application/json", value = "/staff/update/{staffId}")
	public Staff update(@RequestBody Staff staff,@PathVariable Long staffId) {
		return categoryDAO.findById(staffId).map(
				e->{
					e.setName(staff.getName());
					e.setAddress(staff.getEmail());
					e.setEmail(staff.getEmail());
					e.setPhone(staff.getPhone());
					e.setStatus(staff.getStatus());
					return categoryDAO.save(e);
				}).orElseGet(() -> {
					staff.setStaffId(staffId);
			        return categoryDAO.save(staff);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/staff/delete/{staffId}")
	void Delete(@PathVariable Long staffId) {
		categoryDAO.deleteById(staffId);
		
	}
}
