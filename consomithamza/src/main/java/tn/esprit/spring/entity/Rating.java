package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating implements Serializable {

	
	@Override
	public String toString() {
		return "Rating [User=" + User + ", Post=" + Post + ", rate=" + rate + "]";
	}
	private static final long serialVersionUID = 1L;
	public Rating(tn.esprit.spring.entity.User user, tn.esprit.spring.entity.Post post, Rate rate) {
		super();
		User = user;
		Post = post;
		this.rate = rate;
	}
	private RatingPk RatingPk;
	private User User;
	private Post Post;
	@Enumerated(EnumType.STRING)
	Rate rate; 
	
	
	
	
	public Rating() {
		super();
	}
	public Rating(tn.esprit.spring.entity.RatingPk ratingPk, tn.esprit.spring.entity.User user,
			tn.esprit.spring.entity.Post post, Long points, Rate rate) {
		super();
		RatingPk = ratingPk;
		User = user;
		Post = post;
		
		this.rate = rate;
	}
	public Rate getRate() {
		return rate;
	}
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	@EmbeddedId
	public RatingPk getRatingPk() {
		return RatingPk;
	}
	public void setRatingPk(RatingPk ratingPk) {
		RatingPk = ratingPk;
	}
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name ="idUser", referencedColumnName = "USER_ID")
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name ="idPost", referencedColumnName = "POST_ID")
	public Post getPost() {
		return Post;
	}
	public void setPost(Post post) {
		Post = post;
	}
	

}
