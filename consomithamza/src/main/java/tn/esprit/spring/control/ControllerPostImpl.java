package tn.esprit.spring.control;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.Rate;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.repository.IRatingRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.ICommentService;
import tn.esprit.spring.service.IPostService;
import tn.esprit.spring.service.IRatingService;
import tn.esprit.spring.service.IUserService;
@ViewScoped
@Scope(value = "session")
@Controller(value = "postController")
@ELBeanName(value = "postController")
@Join(path= "/forum", to = "/pages/forum/forum.jsf")
public class ControllerPostImpl {
	@Autowired
	IPostService ps;
	@Autowired
	IUserService us;
	@Autowired
	IRatingService rs;
	@Autowired
	IPostRepository PostRepository;
	@Autowired
	ICommentService cs;
	@Autowired
	ControllerUserImpl userController;
	


	private String title;
	private String picture;
	private Date datePost;
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<Post> posts;
	private List<Post> postTotal;

	public List<Post> getPostTotal() {
		postTotal=ps.retrieveAllPostsByPoints();
		return postTotal;
	}

	public void setPostTotal(List<Post> postTotal) {
		this.postTotal = postTotal;
	}

public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}
	
 
	

	public List<Post> getPosts() {
		
		posts= ps.retrieveAllPosts();
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public String addPost(Long user) {
			String navigateTo ="null";
			user = userController.idConnect;
			
			Set<String> testSujet = new HashSet<String>();
			
			posts = (List<Post>) PostRepository.findAll();
			for (Post post : posts) {
				testSujet.add(post.getTitle());}
			
			if(!(testSujet.contains(title))&&(!(title.equals("")))){
			
			
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pihamza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT image_name FROM upload_image ORDER BY image_id DESC LIMIT 1");
			while (rs.next()){
	        System.out.println(rs.getString(1));
	        picture=rs.getString(1);
	       
	        ps.addPost(new Post(title,picture,datePost),user);
			
			}}
			
			
			catch (Exception e1) {
                System.out.println("exeption champ vide" + e1.getMessage());}
			
			
			return navigateTo;}
			
			 else{
				FacesMessage facesMessage=
						new FacesMessage("Add Failed: Post empty or already exist ..");
						FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
						}
			return navigateTo;
			}
	// **************************************************************//
	public boolean testPosted(Long idPost){
		{	Post post =PostRepository.findById(idPost).get();
		if (userController.idConnect.equals(post.getUsers().getId())){
			return true;}
		else
			return false;
		}
	}
	public void removePost(Long idPost){
	
		String id = idPost.toString();
		
		ps.deletePost(id);
		
		}
	private Long postIdToBeUpdated;
	
	public Long getPostIdToBeUpdated() {
		return postIdToBeUpdated;
	}

	public void setPostIdToBeUpdated(Long postIdToBeUpdated) {
		this.postIdToBeUpdated = postIdToBeUpdated;
	}
	public void displayPost(Post pos){
		this.setTitle(pos.getTitle());
	//	this.setPicture(pos.getPicture());
		this.setPostIdToBeUpdated(pos.getId());
		
	}
	public String updatePost(){
		String navigateTo ="null";
		Set<String> testSujet = new HashSet<String>();
		
		posts = (List<Post>) PostRepository.findAll();
		for (Post post : posts) {
			testSujet.add(post.getTitle());}
		
		if(!(testSujet.contains(title))&&(!(title.equals("")))){
		
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pihamza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT image_name FROM upload_image ORDER BY image_id DESC LIMIT 1");
		while (rs.next()){
        System.out.println(rs.getString(1));
        picture=rs.getString(1);
       
        ps.updatePost(new Post(postIdToBeUpdated,title,picture));		
		}}
		
		
		catch (Exception e1) {
            System.out.println("exeption champ vide" + e1.getMessage());}
		
		
		return navigateTo;}
		
		 else{
			FacesMessage facesMessage=
					new FacesMessage("Add Failed: Post empty or already exist ..");
					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
					}
		return navigateTo;
		
	}
	
	private List<Post> bestPost;
	
	

	public List<Post> getBestPost() {
		bestPost=ps.getAllbestPostJPQL();
		return bestPost;
	}

	public void setBestPost(List<Post> bestPost) {
		this.bestPost = bestPost;
	}
	

	
	//*************************************************************************************************************//
	private String searchValue;
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String submit(Long idPost) {
		 
	    id =idPost;
	    return "/pages/forum/comment.xhtml?faces-redirect=true&includeViewParams=true";
	}
	public String submitSorted(Long idPost) {
		 
	    id =idPost;
	    return "/pages/forum/commentSorted.xhtml?faces-redirect=true&includeViewParams=true";
	}
	public String recherche(String title){
		searchValue = title;
		return "/pages/forum/search.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String logOut(){
		return "/login?faces-redirect=true";
	}
	public Rate[] getRates() { return Rate.values(); }
	
	public void addRating4(Long user,Long idPost,Rate r){
		user = userController.idConnect;
	// idPost =PostRepository.findById(idPost).get().getId();
		r=Rate.EXCELLENT;
		rs.addRate(user, idPost, r);
		rs.sommeParPost(idPost);
		ps.addTotalPoint(idPost);
	}
	public void addRating1(Long user,Long idPost,Rate r){
		user = userController.idConnect;
	// idPost =PostRepository.findById(idPost).get().getId();
		r=Rate.POOR;
		rs.addRate(user, idPost, r);
		rs.sommeParPost(idPost);
		ps.addTotalPoint(idPost);
	}
	public void addRating0(Long user,Long idPost,Rate r){
		user = userController.idConnect;
	// idPost =PostRepository.findById(idPost).get().getId();
		r=Rate.TERRIBLE;
		rs.addRate(user, idPost, r);
		rs.sommeParPost(idPost);
		ps.addTotalPoint(idPost);
	}
	public void addRating2(Long user,Long idPost,Rate r){
		user = userController.idConnect;
	// idPost =PostRepository.findById(idPost).get().getId();
		r=Rate.AVERAGE;
		rs.addRate(user, idPost, r);
		rs.sommeParPost(idPost);
		ps.addTotalPoint(idPost);
	}
	public void addRating3(Long user,Long idPost,Rate r){
		user = userController.idConnect;
	// idPost =PostRepository.findById(idPost).get().getId();
		r=Rate.GOOD;
		rs.addRate(user, idPost, r);
		rs.sommeParPost(idPost);
		ps.addTotalPoint(idPost);
	}
	public String RateParUser(Long user,Long idPost){
		user = userController.idConnect;
		return rs.findByPostUser(user, idPost);
	}
	 
}