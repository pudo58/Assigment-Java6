package com.ftl.tholv.API;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ftl.tholv.DAO.AdmUserDAO;
import com.ftl.tholv.Entity.AdmUser;

@RestController
public class AdmUserAPI {
	@Autowired
	private AdmUserDAO admUserDAO;
	
	@GetMapping("/admin-user")
	public List<AdmUser> getAll(){
		return admUserDAO.findAll();
	}
	@GetMapping("/admin-user/{adm_user_id}")
	public AdmUser get(@PathVariable Long adm_user_id ) throws Exception {
		AdmUser au=admUserDAO.findById(adm_user_id)
				.orElseThrow(()-> new Exception("id not found :"+adm_user_id));
		return au;
	}
	@PostMapping(produces = "application/json",value = "/admin-user/create")
	public AdmUser save(@RequestBody AdmUser acc) {
		return admUserDAO.save(acc);
	}
	@PutMapping(produces = "application/json", value = "/admin-user/update/{adm_user_id}")
	public AdmUser update(@RequestBody AdmUser acc,@PathVariable Long adm_user_id) {
		return admUserDAO.findById(adm_user_id).map(
				e->{
					e.setUsername(acc.getUsername());
					e.setPassword(acc.getPassword());
					e.setEmail(acc.getEmail());
					e.setPhone(acc.getPhone());
					return admUserDAO.save(e);
				}).orElseGet(() -> {
			        acc.setAdmUserId(adm_user_id);
			        return admUserDAO.save(acc);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/admin-user/delete/{adm_user_id}")
	void Delete(@PathVariable Long adm_user_id) {
		admUserDAO.deleteById(adm_user_id);
		
	}

}
