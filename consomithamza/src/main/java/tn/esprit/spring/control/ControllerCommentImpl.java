package tn.esprit.spring.control;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.History;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.repository.IHistoryRepository;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.service.ICommentService;
import tn.esprit.spring.service.IHistoryService;
import tn.esprit.spring.service.IPostService;

@Scope(value = "session")
@Controller(value = "commentController")
@ELBeanName(value = "commentController")
@Join(path= "/comment", to = "/pages/forum/comment.jsf")
public class ControllerCommentImpl {
	@Autowired
	IPostRepository PostRepository;
	@Autowired
	ICommentService cs;
	@Autowired
	ControllerUserImpl userController;
	@Autowired
	IPostService ps;
	@Autowired
	ICommentRepository cr;
	@Autowired
	IHistoryRepository hr;
	@Autowired
	IHistoryService hs;
	private String continu;
	private Date dateComment;
	private int like;
	private int deslike;
	private int love;
	private int rire;
	private List<Comment> comments;
	private List<Post> postOne;
	
	/*private ArrayList<Comment> comments;
	public ArrayList<Comment> getComments(Long postId) {
		comments= cs.CommentQuery(postId);
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}*/

	

	


	public List<Post> getPostOne() {
		return postOne;
	}


	public void setPostOne(List<Post> postOne) {
		this.postOne = postOne;
	}


	public String getContinu() {
		return continu;
	}
	

	public List<Comment> getComments() {
		return comments;
	}


	public List<Comment> showComments(Long postId) {
		comments=cs.allComments(postId);
		return comments;
	}
	

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setContinu(String continu) {
		this.continu = continu;
	}

	public Date getDateComment() {
		return dateComment;
	}

	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDeslike() {
		return deslike;
	}

	public void setDeslike(int deslike) {
		this.deslike = deslike;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public int getRire() {
		return rire;
	}

	public void setRire(int rire) {
		this.rire = rire;
	}

	public String addComment(Long userId,Long postId){
		
		Set<String> testComment = new HashSet<String>();
		testComment.add("hell");
		testComment.add("fuck");
		testComment.add("pute");
		testComment.add("shit");
		testComment.add("merde");
		String navigateTo ="null";
		userId = userController.idConnect;
		Post post =PostRepository.findById(postId).get();
	//	Set<String> decompose = new HashSet<String>();
		
		String[] splited = continu.split("\\s+");
		for (String mot : splited){
		if((testComment.contains(mot))||(continu.equals(""))){
			FacesMessage facesMessage=
					new FacesMessage("Add Failed: Comment empty or contains bad words . try again.");
					FacesContext.getCurrentInstance().addMessage("form:btn1",facesMessage);
		return navigateTo;}

		 }
		
			 cs.addComment(userId, post.getId(), new Comment(like,deslike,love,rire,continu,dateComment));
			
					
		return navigateTo;
		}
	


	public Post onePost(Long postId){
		return ps.retrievePost(postId);
		}
	
	
	private Long commentIdToBeUpdated;



	
	
	public Long getCommentIdToBeUpdated() {
		return commentIdToBeUpdated;
	}


	public void setCommentIdToBeUpdated(Long commentIdToBeUpdated) {
		this.commentIdToBeUpdated = commentIdToBeUpdated;
	}


	public void displayComment(Comment com){
		this.setContinu(com.getContenuComment());
		this.setCommentIdToBeUpdated(com.getId());
		
	}
	public String updateComment(){
		String navigateTo ="null";
		Set<String> testComment = new HashSet<String>();
		testComment.add("hell");
		testComment.add("fuck");
		testComment.add("pute");
		testComment.add("shit");
		testComment.add("merde");
		String[] splited = continu.split("\\s+");
		for (String mot : splited){
		if((testComment.contains(mot))||(continu.equals(""))){
			FacesMessage facesMessage=
					new FacesMessage("Add Failed: Comment empty or contains bad words . try again.");
					FacesContext.getCurrentInstance().addMessage("form:btn1",facesMessage);
		return navigateTo;}

		 }
		
		cs.updateComment(new Comment(commentIdToBeUpdated,continu));
		return navigateTo;
	}
	public boolean testComment(Long idComment){
		{	Comment comment =cr.findById(idComment).get();
		if (userController.idConnect.equals(comment.getUseres().getId())){
			return true;}
		else
			return false;
		}
	}
	public void removeComment(Long idComment){
		
		String id = idComment.toString();
		
		cs.deleteComment(id);
		
		}
	HashMap<String, Entry<Long, Long>> HashMap = new HashMap<String, Entry<Long, Long>>();
	private List<History> Histories;
	public List<History> getHistories() {
		return Histories;
	}


	public void setHistories(List<History> histories) {
		Histories = histories;
	}
	
	
public void liked(Long commentId, Long userId){
		
		userId=userController.idConnect;
		Histories =(List<History>) hr.findAll();
		for(History history : Histories ){
			java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(history.getComments().getId(),history.getUsers().getId());
			HashMap.put(history.getReactHistory(),pair1);
		}
		java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(commentId,userId);
		if(!(HashMap.containsValue(pair1))){
		cs.addLike(commentId, userId);
//	Long postId= cr.findById(commentId).get().getPosts().getId();
		hs.addHistory(new History("","Like"), userController.idConnect,commentId);
		HashMap.put("Like", pair1);
		
		}else{
			if(HashMap.containsValue(pair1)&&HashMap.containsKey("Like")){
		//		Long postId= cr.findById(commentId).get().getPosts().getId();
				hs.deleteReact(userId, commentId);
				cs.removeLike(commentId, userId);
				HashMap.remove("Like", pair1);
			}
		}
	}
	public void loved(Long commentId, Long userId){
		
		userId=userController.idConnect;
		Histories =(List<History>) hr.findAll();
		for(History history : Histories ){
			
			java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(history.getComments().getId(),history.getUsers().getId());
			HashMap.put(history.getReactHistory(),pair1);
		}
		java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(commentId,userId);
		if(!(HashMap.containsValue(pair1))){
		
		cs.addLove(commentId, userId);
	//	Long postId= cr.findById(commentId).get().getPosts().getId();
		hs.addHistory(new History("","Love"), userController.idConnect,commentId);
		HashMap.put("Love", pair1);
		}
		else{
			if(HashMap.containsValue(pair1)&&HashMap.containsKey("Love")){
		//		Long postId= cr.findById(commentId).get().getPosts().getId();
				hs.deleteReact(userId, commentId);
				cs.removeLove(commentId, userId);
				HashMap.remove("Love", pair1);
				
			}
		}
	}
	public void disliked(Long commentId, Long userId){
		userId=userController.idConnect;
		Histories =(List<History>) hr.findAll();
		for(History history : Histories ){
			
			java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(history.getComments().getId(),history.getUsers().getId());
			HashMap.put(history.getReactHistory(),pair1);
		}
		java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(commentId,userId);
		if(!(HashMap.containsValue(pair1))){
		cs.addDesLike(commentId, userId);
	//	Long postId= cr.findById(commentId).get().getPosts().getId();
		hs.addHistory(new History("","Dislike"), userController.idConnect,commentId);
		HashMap.put("Dislike", pair1);
		}
		else{
			if(HashMap.containsValue(pair1)&&HashMap.containsKey("Dislike")){
			//	Long postId= cr.findById(commentId).get().getPosts().getId();
				hs.deleteReact(userId, commentId);
				cs.removeDesLike(commentId, userId);
				HashMap.remove("Dislike", pair1);
				
			}
		}
	}
	public void rired(Long commentId, Long userId){
		Histories =(List<History>) hr.findAll();
		for(History history : Histories ){
			
			java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(history.getComments().getId(),history.getUsers().getId());
			HashMap.put(history.getReactHistory(),pair1);
		}
		userId=userController.idConnect;
		java.util.Map.Entry<Long,Long> pair1=new java.util.AbstractMap.SimpleEntry<>(commentId,userId);
		if(!(HashMap.containsValue(pair1))){
		cs.addRire(commentId, userId);
	//	Long postId= cr.findById(commentId).get().getPosts().getId();
		hs.addHistory(new History("","Rire"), userController.idConnect,commentId);
		HashMap.put("Rire", pair1);
		}else{
			if(HashMap.containsValue(pair1)&&HashMap.containsKey("Rire")){
		//		Long postId= cr.findById(commentId).get().getPosts().getId();
				hs.deleteReact(userId, commentId);
				cs.removeRire(commentId, userId);
				HashMap.remove("Rire", pair1);
				
			}
		}
	}
	
	
	
}
