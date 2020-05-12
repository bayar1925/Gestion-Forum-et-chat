package tn.esprit.spring.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.IPostService;
import tn.esprit.spring.service.IUserService;
@ViewScoped
@Scope(value = "session")
@Controller(value = "userController")
@ELBeanName(value = "userController")
@Join(path= "/form", to = "/login.jsf")
public class ControllerUserImpl {

	@Autowired
	IUserService userService;
	@Autowired
	IPostService postservice;
	private String login; private String password; private User user; static Long idConnect;
	private User authenticatedUser;
	public User getAuthenticatedUser() {
		return authenticatedUser;
	}
	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	

	private Boolean loggedIn;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String doLogin() {
		String navigateTo= "null";
		authenticatedUser =userService.authenticate(login, password);
		if(authenticatedUser != null) {
		idConnect=	authenticatedUser.getId();
	//		System.out.println("*********************"+idConnect);
		postservice.deleteAutoPost("2");
		navigateTo= "/pages/forum/forum.xhtml?faces-redirect=true";
		
		loggedIn= true; }
		
		else{
		FacesMessage facesMessage=
		new FacesMessage("Login Failed: please check your username/password and try again.");
		FacesContext.getCurrentInstance().addMessage("form:btn2",facesMessage);
		}
		return navigateTo;}
	
		public String doLogout() {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "/login.xhtml?faces-redirect=true";
			}
		
		
		}

