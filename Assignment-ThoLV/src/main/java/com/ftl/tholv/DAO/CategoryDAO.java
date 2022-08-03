package com.ftl.tholv.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftl.tholv.Entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Long>{

}
