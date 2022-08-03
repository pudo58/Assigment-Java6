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
import com.ftl.tholv.DAO.FilmAttrDAO;
import com.ftl.tholv.Entity.FilmAttr;
@RestController
public class FilmAttrAPI {
	@Autowired
	private FilmAttrDAO filmDAO;
	
	@GetMapping("/film-detail")
	public List<FilmAttr> getAll(){
		return filmDAO.findAll();
	}
	@GetMapping("/film-detail/{film_id}")
	public FilmAttr get(@PathVariable Long film_id ) throws Exception {
		FilmAttr f=filmDAO.findById(film_id)
				.orElseThrow(()-> new Exception("id not found :"+film_id));
		return f;
	}
	@PostMapping(produces = "application/json",value = "/film-detail/create")
	public FilmAttr save(@RequestBody FilmAttr f) {
		return filmDAO.save(f);
	}
	@PutMapping(produces = "application/json", value = "/film-detail/update/{film_id}")
	public FilmAttr update(@RequestBody FilmAttr f,@PathVariable Long film_id) {
		return filmDAO.findById(film_id).map(
				e->{
					e.setFilmName(f.getFilmName());
					e.setModifier(f.getModifier());
					e.setModified(new Date());
					return filmDAO.save(e);
				}).orElseGet(() -> {
			        f.setFilmAttrId(film_id);
			        return filmDAO.save(f);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/film-detail/delete/{film_id}")
	void Delete(@PathVariable Long film_id) {
		filmDAO.deleteById(film_id);
		
	}
}
