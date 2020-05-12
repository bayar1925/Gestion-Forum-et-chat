package tn.esprit.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.Rate;
import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.repository.IRatingRepository;
import tn.esprit.spring.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserRepository UserRepository;
	
	
	private static final Logger L = LogManager.getLogger(UserServiceImpl.class);
	@Override
	public User addUser(User u) {
		
		return UserRepository.save(u);
	}
//	@Override
//	public List<Rating> getTimesheetsByMissionAndDate(User user, Post post, Rate rate) {
//		return RatingRepository.getTimesheetsByMissionAndDate(user, post, rate);
//	}
	@Override
	public User authenticate(String login, String password) {
	
		return UserRepository.getUserAutho(login, password);
	}
	

}
