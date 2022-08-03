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
import com.nguyennt.app.DAO.UserDAO;
import com.nguyennt.app.Entity.User;

@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/user")
	public List<User> getAll(){
		return userDAO.findAll();
	}
	@GetMapping("/user/{user_id}")
	public User get(@PathVariable Long user_id ) throws Exception {
		User user=userDAO.findById(user_id)
				.orElseThrow(()-> new Exception("id not found :"+user_id));
		return user;
	}
	@PostMapping(produces = "application/json",value = "/user/create")
	public User save(@RequestBody User bill) {
		return userDAO.save(bill);
	}
	@PutMapping(produces = "application/json", value = "/user/update/{user_id}")
	public User update(@RequestBody User user,@PathVariable Long user_id) {
		return userDAO.findById(user_id).map(
				e->{
					e.setPhone(user.getPhone());
					e.setPassword(user.getPassword());
					e.setAddress(user.getAddress());
					e.setEmail(user.getEmail());
					e.setStatus(e.getStatus());
					return userDAO.save(e);
				}).orElseGet(() -> {
					user.setUserId(user_id);
			        return userDAO.save(user);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/user/delete/{user_id}")
	void Delete(@PathVariable Long user_id) {
		userDAO.deleteById(user_id);
		
	}
}
