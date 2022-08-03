package com.ftl.tholv.API;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftl.tholv.DAO.CommentAttrDAO;
import com.ftl.tholv.Entity.CommentAttr;

public class CommentAtrrAPI {
	@Autowired
	private CommentAttrDAO commentDAO;
	
	@GetMapping("/comment-detail")
	public List<CommentAttr> getAll(){
		return commentDAO.findAll();
	}
	@GetMapping("/comment-detail/{cmt_id}")
	public CommentAttr get(@PathVariable Long cmt_id ) throws Exception {
		CommentAttr au=commentDAO.findById(cmt_id)
				.orElseThrow(()-> new Exception("id not found :"+cmt_id));
		return au;
	}
	@PostMapping(produces = "application/json",value = "/comment-detail/create")
	public CommentAttr save(@RequestBody CommentAttr acc) {
		return commentDAO.save(acc);
	}
	@PutMapping(produces = "application/json", value = "/comment-detail/update/{cmt_id}")
	public CommentAttr update(@RequestBody CommentAttr cmt,@PathVariable Long cmt_id) {
		return commentDAO.findById(cmt_id).map(
				e->{
					e.setCommentReply(cmt.getCommentReply());
					e.setModified(new Date());
					e.setModifier(cmt.getAccount().getUsername());
					return commentDAO.save(e);
				}).orElseGet(() -> {
			        cmt.setCommentAttrId(cmt_id);
			        return commentDAO.save(cmt);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/comment-detail/delete/{cmt_id}")
	void Delete(@PathVariable Long cmt_id) {
		commentDAO.deleteById(cmt_id);
		
	}
}
