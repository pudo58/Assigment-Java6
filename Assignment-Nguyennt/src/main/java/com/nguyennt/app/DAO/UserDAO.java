package com.nguyennt.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyennt.app.Entity.User;

public interface UserDAO extends JpaRepository<User,Long>{

}
