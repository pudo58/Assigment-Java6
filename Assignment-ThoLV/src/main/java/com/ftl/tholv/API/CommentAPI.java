package com.ftl.tholv.API;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ftl.tholv.DAO.CommentDAO;
import com.ftl.tholv.Entity.Comment;

public class CommentAPI {
	@Autowired
	private CommentDAO commentDAO;
	
	@GetMapping("/comment")
	public List<Comment> getAll(){
		return commentDAO.findAll();
	}
	@GetMapping("/comment/{cmt_id}")
	public Comment get(@PathVariable Long cmt_id ) throws Exception {
		Comment au=commentDAO.findById(cmt_id)
				.orElseThrow(()-> new Exception("id not found :"+cmt_id));
		return au;
	}
	@PostMapping(produces = "application/json",value = "/comment/create")
	public Comment save(@RequestBody Comment acc) {
		return commentDAO.save(acc);
	}
	@PutMapping(produces = "application/json", value = "/comment/update/{cmt_id}")
	public Comment update(@RequestBody Comment cmt,@PathVariable Long cmt_id) {
		return commentDAO.findById(cmt_id).map(
				e->{
					e.setComment(cmt.getComment());
					e.setStatus(cmt.getStatus());
					return commentDAO.save(e);
				}).orElseGet(() -> {
			        cmt.setCommentId(cmt_id);
			        return commentDAO.save(cmt);
			      });
	}
	
	@DeleteMapping(produces = "application/json",value = "/comment/delete/{cmt_id}")
	void Delete(@PathVariable Long cmt_id) {
		commentDAO.deleteById(cmt_id);
		
	}
}
