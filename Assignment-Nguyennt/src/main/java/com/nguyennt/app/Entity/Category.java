package com.nguyennt.app.Entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "CATEGORY" database table.
 * 
 */
@Entity
@Table(name="CATEGORY")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;

	@Temporal(TemporalType.DATE)
	private Date created;

	private String creator;

	@Column(name="\"NAME\"")
	private String name;

	private Integer status;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<ProductTree> productTrees;

	public Category() {
	}

	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<ProductTree> getProductTrees() {
		return this.productTrees;
	}

	public void setProductTrees(List<ProductTree> productTrees) {
		this.productTrees = productTrees;
	}

	public ProductTree addProductTree(ProductTree productTree) {
		getProductTrees().add(productTree);
		productTree.setCategory(this);

		return productTree;
	}

	public ProductTree removeProductTree(ProductTree productTree) {
		getProductTrees().remove(productTree);
		productTree.setCategory(null);

		return productTree;
	}

}