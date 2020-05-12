package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface IUserService {
	User addUser(User u);
	//public List<Rating> getTimesheetsByMissionAndDate(User user, Post post, Rate rate);
	public User authenticate(String login, String password) ;
	
}
