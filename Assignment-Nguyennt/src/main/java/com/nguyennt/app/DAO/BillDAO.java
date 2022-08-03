package com.nguyennt.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyennt.app.Entity.Bill;

public interface BillDAO  extends JpaRepository<Bill, Long>{

}
