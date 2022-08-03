package com.nguyennt.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyennt.app.Entity.Staff;

public interface StaffDAO extends JpaRepository<Staff, Long>{

}
