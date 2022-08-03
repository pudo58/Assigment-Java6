package com.ftl.tholv.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftl.tholv.Entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long>{

}
