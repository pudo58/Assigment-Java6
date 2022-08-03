package com.ftl.tholv.Entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the adm_user database table.
 * 
 */
@Entity
@Table(name="adm_user")
@NamedQuery(name="AdmUser.findAll", query="SELECT a FROM AdmUser a")
public class AdmUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="adm_user_id")
	private Long admUserId;

	private String email;

	private String password;

	private String phone;

	private Integer status;

	private String username;

	//bi-directional many-to-one association to Account
	@JsonIgnore
	@OneToMany(mappedBy="admUser")
	private List<Account> accounts;

	//bi-directional many-to-one association to Category
	@JsonIgnore
	@OneToMany(mappedBy="admUser")
	private List<Category> categories;

	public AdmUser() {
	}

	public Long getAdmUserId() {
		return this.admUserId;
	}

	public void setAdmUserId(Long admUserId) {
		this.admUserId = admUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setAdmUser(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setAdmUser(null);

		return account;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setAdmUser(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setAdmUser(null);

		return category;
	}

}