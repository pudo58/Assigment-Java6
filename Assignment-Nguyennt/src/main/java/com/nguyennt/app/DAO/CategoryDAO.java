package com.nguyennt.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyennt.app.Entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Long>{

}
