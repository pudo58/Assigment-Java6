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
import com.ftl.tholv.DAO.FilmDAO;
import com.ftl.tholv.Entity.Film;
@RestController
public class FilmAPI {
	@Autowired
	private FilmDAO filmDAO;
	@Autowired
	private AccountDAO acc;
	
	@GetMapping("/film")
	public List<Film> getAll(){
		return filmDAO.findAll();
	}
	@GetMapping("/film/{film_id}")
	public Film get(@PathVariable Long film_id ) throws Exception {
		Film f=filmDAO.findById(film_id)
				.orElseThrow(()-> new Exception("id not found :"+film_id));
		return f;
	}
	@PostMapping(produces = "application/json",value = "/film/create")
	public Film save(@RequestBody Film f) {
		return filmDAO.save(f);
	}
	@PutMapping(produces = "application/json", value = "/film/update/{film_id}")
	public Film update(@RequestBody Film f,@PathVariable Long film_id) {
		return filmDAO.findById(film_id).map(
				e->{
					e.setName(f.getName());
					e.setModified(new Date());
					e.setModifier(acc.findById(f.getAccId()).get().getUsername());
					return filmDAO.save(e);
				}).orElseGet(() -> {
			        f.setFilmId(film_id);
			        return filmDAO.save(f);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/film/delete/{film_id}")
	void Delete(@PathVariable Long film_id) {
		filmDAO.deleteById(film_id);
		
	}
}
