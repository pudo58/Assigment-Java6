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

import com.ftl.tholv.DAO.AccountDAO;
import com.ftl.tholv.Entity.Account;


@RestController
public class AccountAPI {
	@Autowired
	private AccountDAO accountDAO;
	
	// Get all list Admin
	@GetMapping("/account")
	public List<Account> getAll(){
		return accountDAO.findAll();
	}
	@GetMapping("/account/{acc_id}")
	public Account get(@PathVariable Long acc_id ) throws Exception {
		Account acc=accountDAO.findById(acc_id)
				.orElseThrow(()-> new Exception("id not found :"+acc_id));
		return acc;
	}
	@PostMapping(produces = "application/json",value = "/account/create")
	public Account save(@RequestBody Account acc) {
		return accountDAO.save(acc);
	}
	@PutMapping(produces = "application/json", value = "/account/update/{acc_id}")
	public Account update(@RequestBody Account acc,@PathVariable Long acc_id) {
		return accountDAO.findById(acc_id).map(
				e->{
					e.setPhone(acc.getPhone());
					e.setPassword(acc.getPassword());
					e.setEmail(acc.getEmail());
					e.setModifier(acc.getModifier());
					e.setModified(new Date());
					return accountDAO.save(e);
				}).orElseGet(() -> {
			        acc.setAccId(acc_id);
			        return accountDAO.save(acc);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/account/delete/{acc_id}")
	void Delete(@PathVariable Long acc_id) {
		accountDAO.deleteById(acc_id);
		
	}

}
