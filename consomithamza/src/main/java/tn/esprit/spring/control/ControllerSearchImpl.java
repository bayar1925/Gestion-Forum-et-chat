package tn.esprit.spring.control;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Post;
import tn.esprit.spring.service.IPostService;
@ViewScoped
@Scope(value = "session")
@Controller(value = "searchController")
@ELBeanName(value = "searchController")
@Join(path= "/search", to = "/pages/forum/search.jsf")
public class ControllerSearchImpl {
	@Autowired
	IPostService ps;
private List<Post> searchPost;
	

	public List<Post> getSearchPost() {
		return searchPost;
	}

	public void setSearchPost(List<Post> searchPost) {
		this.searchPost = searchPost;
	}
	public List<Post> searchByPost( String recherche) {
		
		return ps.search(recherche);
		
	}
	

}
