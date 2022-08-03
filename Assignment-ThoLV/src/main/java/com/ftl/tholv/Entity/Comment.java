package com.ftl.tholv.Entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comment_id")
	private Long commentId;

	private String comment;

	private Date created;

	private String creator;

	private Long likes;

	private Date modified;

	private String modifier;

	private Integer status;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="acc_id")
	private Account account;

	//bi-directional many-to-one association to FilmAttr
	@ManyToOne
	@JoinColumn(name="film_attr_id")
	private FilmAttr filmAttr;

	//bi-directional many-to-one association to CommentAttr
	@JsonIgnore
	@OneToMany(mappedBy="comment")
	private List<CommentAttr> commentAttrs;

	public Comment() {
	}

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public FilmAttr getFilmAttr() {
		return this.filmAttr;
	}

	public void setFilmAttr(FilmAttr filmAttr) {
		this.filmAttr = filmAttr;
	}

	public List<CommentAttr> getCommentAttrs() {
		return this.commentAttrs;
	}

	public void setCommentAttrs(List<CommentAttr> commentAttrs) {
		this.commentAttrs = commentAttrs;
	}

	public CommentAttr addCommentAttr(CommentAttr commentAttr) {
		getCommentAttrs().add(commentAttr);
		commentAttr.setComment(this);

		return commentAttr;
	}

	public CommentAttr removeCommentAttr(CommentAttr commentAttr) {
		getCommentAttrs().remove(commentAttr);
		commentAttr.setComment(null);

		return commentAttr;
	}

}