package com.ftl.tholv.Entity;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="film_attr")
@NamedQuery(name="FilmAttr.findAll", query="SELECT f FROM FilmAttr f")
public class FilmAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_attr_id")
	private Long filmAttrId;

	private Integer chapter;

	private Date created;

	private String creator;

	@Column(name="film_name")
	private String filmName;

	private Long likes;

	private Date modified;

	private String modifier;

	private String url;

	@Column(name="\"VIEWS\"")
	private Long views;

	//bi-directional many-to-one association to Comment
	@JsonIgnore
	@OneToMany(mappedBy="filmAttr")
	private List<Comment> comments;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;

	public FilmAttr() {
	}

	public Long getFilmAttrId() {
		return this.filmAttrId;
	}

	public void setFilmAttrId(Long filmAttrId) {
		this.filmAttrId = filmAttrId;
	}

	public Integer getChapter() {
		return this.chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
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

	public String getFilmName() {
		return this.filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getViews() {
		return this.views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setFilmAttr(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setFilmAttr(null);

		return comment;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}