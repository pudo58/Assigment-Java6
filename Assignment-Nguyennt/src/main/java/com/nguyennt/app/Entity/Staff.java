package com.nguyennt.app.Entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "STAFF" database table.
 * 
 */
@Entity
@Table(name="STAFF")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private Long staffId;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date created;

	private String creator;

	private String email;

	private String name;

	private String password;

	private String phone;

	private Integer status;

	private String username;

	@JsonIgnore
	@OneToMany(mappedBy="staff")
	private List<Category> categories;

	@JsonIgnore
	@OneToMany(mappedBy="staff")
	private List<ProductTree> productTrees;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admin admin;

	public Staff() {
	}

	public Long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setStaff(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setStaff(null);

		return category;
	}

	public List<ProductTree> getProductTrees() {
		return this.productTrees;
	}

	public void setProductTrees(List<ProductTree> productTrees) {
		this.productTrees = productTrees;
	}

	public ProductTree addProductTree(ProductTree productTree) {
		getProductTrees().add(productTree);
		productTree.setStaff(this);

		return productTree;
	}

	public ProductTree removeProductTree(ProductTree productTree) {
		getProductTrees().remove(productTree);
		productTree.setStaff(null);

		return productTree;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}