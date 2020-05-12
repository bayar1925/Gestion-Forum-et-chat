package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
	
	private List<Rating>ratings;
	
	@Access(AccessType.PROPERTY)
	@OneToMany(mappedBy="user")
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	private static final long serialVersionUID = 1L;
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id; // Identifiant formation (Clé primaire)
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	
	

	public User(Long id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + "]";
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="users")
	private Set<Post> Postes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Useres")
	private Set<Comment> Comments;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Users")
	private Set<History> Messages;
	
	public void assignRatingsToThisUser(List<Rating> ratings){
		this.setRatings(ratings);
		for(Rating r : ratings){
			r.setUser(this);
		}
		
	}

	

}
