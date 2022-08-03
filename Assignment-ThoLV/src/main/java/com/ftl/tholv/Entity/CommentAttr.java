package com.ftl.tholv.Entity;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;



/**
 * The persistent class for the comment_attr database table.
 * 
 */
@Entity
@Table(name="comment_attr")
@NamedQuery(name="CommentAttr.findAll", query="SELECT c FROM CommentAttr c")
public class CommentAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comment_attr_id")
	private Long commentAttrId;

	@Column(name="comment_reply")
	private String commentReply;

	private Date created;

	private String creator;

	private Date modified;

	private String modifier;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="acc_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="comment_id")
	private Comment comment;

	public CommentAttr() {
	}

	public Long getCommentAttrId() {
		return this.commentAttrId;
	}

	public void setCommentAttrId(Long commentAttrId) {
		this.commentAttrId = commentAttrId;
	}

	public String getCommentReply() {
		return this.commentReply;
	}

	public void setCommentReply(String commentReply) {
		this.commentReply = commentReply;
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

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}