package com.nguyennt.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyennt.app.Entity.Admin;

public interface AdminDAO extends JpaRepository<Admin, Long> {

}
