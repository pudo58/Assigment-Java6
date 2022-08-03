package com.ftl.tholv.Entity;

import java.io.Serializable;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="acc_id")
	private Long accId;

	@Temporal(TemporalType.DATE)
	private Date created;

	private String creator;

	private String email;

	private Date modified;

	private String modifier;

	private float money;

	private String password;

	private String phone;

	private String username;

	//bi-directional many-to-one association to AdmUser
	@ManyToOne
	@JoinColumn(name="adm_user_id")
	private AdmUser admUser;

	//bi-directional many-to-one association to Comment
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Comment> comments;

	//bi-directional many-to-one association to CommentAttr
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<CommentAttr> commentAttrs;

	public Account() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AdmUser getAdmUser() {
		return this.admUser;
	}

	public void setAdmUser(AdmUser admUser) {
		this.admUser = admUser;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAccount(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAccount(null);

		return comment;
	}

	public List<CommentAttr> getCommentAttrs() {
		return this.commentAttrs;
	}

	public void setCommentAttrs(List<CommentAttr> commentAttrs) {
		this.commentAttrs = commentAttrs;
	}

	public CommentAttr addCommentAttr(CommentAttr commentAttr) {
		getCommentAttrs().add(commentAttr);
		commentAttr.setAccount(this);

		return commentAttr;
	}

	public CommentAttr removeCommentAttr(CommentAttr commentAttr) {
		getCommentAttrs().remove(commentAttr);
		commentAttr.setAccount(null);

		return commentAttr;
	}

}