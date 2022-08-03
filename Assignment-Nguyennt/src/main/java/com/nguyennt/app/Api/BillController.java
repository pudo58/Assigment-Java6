package com.nguyennt.app.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyennt.app.DAO.BillDAO;
import com.nguyennt.app.Entity.Bill;
@RestController
public class BillController {
	@Autowired
	private BillDAO billDAO;
	
	// Get all list Admin
	@GetMapping("/bill")
	public List<Bill> getAll(){
		return billDAO.findAll();
	}
	@GetMapping("/bill/{billId}")
	public Bill get(@PathVariable Long billId ) throws Exception {
		Bill bill=billDAO.findById(billId)
				.orElseThrow(()-> new Exception("id not found :"+billId));
		return bill;
	}
	@PostMapping(produces = "application/json",value = "/bill/create")
	public Bill save(@RequestBody Bill bill) {
		return billDAO.save(bill);
	}
	@PutMapping(produces = "application/json", value = "/bill/update/{bill_id}")
	public Bill update(@RequestBody Bill bill,@PathVariable Long bill_id) {
		return billDAO.findById(bill_id).map(
				e->{
					e.setQuantity(bill.getQuantity());
					e.setTotalPrice(bill.getTotalPrice());
					e.setStatus(bill.getStatus());
					return billDAO.save(e);
				}).orElseGet(() -> {
			        bill.setBillId(bill_id);
			        return billDAO.save(bill);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/bill/delete/{bill_id}")
	void Delete(@PathVariable Long bill_id) {
		billDAO.deleteById(bill_id);
		
	}
}
