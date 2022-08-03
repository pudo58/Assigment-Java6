package com.nguyennt.app.Entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="\"BILL\"")

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bill_id")
	private Long billId;

	@Temporal(TemporalType.DATE)
	private Date created;

	private Integer quantity;

	private Integer status;

	@Column(name="total_price")
	private float totalPrice;

	//bi-directional many-to-one association to ProductTree
	@ManyToOne
	@JoinColumn(name="tree_id")
	private ProductTree productTree;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Bill() {
	}

	public Long getBillId() {
		return this.billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ProductTree getProductTree() {
		return this.productTree;
	}

	public void setProductTree(ProductTree productTree) {
		this.productTree = productTree;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}