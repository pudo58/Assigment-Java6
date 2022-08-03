package com.ftl.tholv.Entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id")
	private Long filmId;

	@Column(name="acc_id")
	private Long accId;

	private Date created;

	private String creator;

	private Long interested;

	private Long likes;

	private Date modified;

	private String modifier;

	private String name;
	

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional many-to-one association to FilmAttr
	@JsonIgnore
	@OneToMany(mappedBy="film")
	private List<FilmAttr> filmAttrs;

	public Film() {
	}

	public Long getFilmId() {
		return this.filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	public Long getAccId() {
		return this.accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
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

	public Long getInterested() {
		return this.interested;
	}

	public void setInterested(Long interested) {
		this.interested = interested;
	}

	public Long getLikes() {
		return this.likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<FilmAttr> getFilmAttrs() {
		return this.filmAttrs;
	}

	public void setFilmAttrs(List<FilmAttr> filmAttrs) {
		this.filmAttrs = filmAttrs;
	}

	public FilmAttr addFilmAttr(FilmAttr filmAttr) {
		getFilmAttrs().add(filmAttr);
		filmAttr.setFilm(this);

		return filmAttr;
	}

	public FilmAttr removeFilmAttr(FilmAttr filmAttr) {
		getFilmAttrs().remove(filmAttr);
		filmAttr.setFilm(null);

		return filmAttr;
	}

}