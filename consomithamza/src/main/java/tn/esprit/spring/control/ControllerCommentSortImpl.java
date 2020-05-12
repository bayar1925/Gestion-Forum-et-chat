package tn.esprit.spring.control;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.service.ICommentService;

@ViewScoped
@Scope(value = "session")
@Controller(value = "sortController")
@ELBeanName(value = "sortController")
@Join(path= "/sort", to = "/pages/forum/commentSorted.jsf")
public class ControllerCommentSortImpl {
	
	@Autowired
	ICommentService cs;
	
	private List<Comment>CommentSorted;
	
	public List<Comment> getCommentSorted() {
		return CommentSorted;
	}


	public void setCommentSorted(List<Comment> commentSorted) {
		CommentSorted = commentSorted;
	}
	
	public List<Comment> showCommentsSorted(Long postId) {
		CommentSorted=cs.allCommentsSorted(postId);
		return CommentSorted;
	}
}
