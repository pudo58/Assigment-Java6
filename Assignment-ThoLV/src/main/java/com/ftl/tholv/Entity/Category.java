package com.ftl.tholv.Entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private Long categoryId;

	@Temporal(TemporalType.DATE)
	private Date created;

	private String creator;

	private Date modified;

	private String modifier;

	private String name;

	private Integer status;

	//bi-directional many-to-one association to AdmUser
	@ManyToOne
	@JoinColumn(name="adm_user_id")
	private AdmUser admUser;

	//bi-directional many-to-one association to Film
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<Film> films;

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

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
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

	public AdmUser getAdmUser() {
		return this.admUser;
	}

	public void setAdmUser(AdmUser admUser) {
		this.admUser = admUser;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setCategory(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setCategory(null);

		return film;
	}

}