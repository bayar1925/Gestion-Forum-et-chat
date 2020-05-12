package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.History;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.repository.IHistoryRepository;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.repository.IUserRepository;

@Service
public class HistoryServiceImpl implements IHistoryService{
	@Autowired
	IHistoryRepository hr;
	@Autowired
	IUserRepository UserRepository;
	@Autowired
	IPostRepository PostRepository;
	@Autowired
	ICommentRepository CommentRepository;
	private static final Logger L = LogManager.getLogger(HistoryServiceImpl.class);
	
	@Override
	public History addHistory(History h,Long userId,Long commentId) {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	   User user = UserRepository.findById(userId).get();
	   h.setUsers(user);
	//   Post postToUpdate = PostRepository.findById(p.getId()).get(); 	
	 //   Optional<Comment> comment = CommentRepository.findById(commentId);
	  
	  Comment comment = CommentRepository.findById(commentId).get();
		h.setDateHistory(date);
		h.setComments(comment);
		
		return hr.save(h);
	
	}
	@Override
	public void deleteReact(Long userId,Long postId){
		 hr.deleteHistoryLike(userId, postId);
		
		
	}
	@Override
	public History addHistoryPost(History h,Long userId) {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	   User user = UserRepository.findById(userId).get();
	   h.setUsers(user);
	//   Post postToUpdate = PostRepository.findById(p.getId()).get(); 	
	 //   Optional<Comment> comment = CommentRepository.findById(commentId);
	  
	//  Comment comment = CommentRepository.findById(commentId).get();
		h.setDateHistory(date);
		
		
		return hr.save(h);
	
	}
	
}
