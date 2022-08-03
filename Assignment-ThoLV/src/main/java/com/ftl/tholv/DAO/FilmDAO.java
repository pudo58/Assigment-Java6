package com.ftl.tholv.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftl.tholv.Entity.Film;

public interface FilmDAO extends JpaRepository<Film, Long>{

}
