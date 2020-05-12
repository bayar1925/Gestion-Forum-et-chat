package tn.esprit.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.Rate;
import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.repository.IRatingRepository;
import tn.esprit.spring.service.ICommentService;
import tn.esprit.spring.service.IPostService;
import tn.esprit.spring.service.IRatingService;
import tn.esprit.spring.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsomithamzaApplicationTests {
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

	@Test
	public void contextLoads() throws ParseException{
		List<Post> posts = (List<Post>) PostRepository.findAll();
		  for (Post post : posts) {
		Long x= post.getId();
		//	ps.addTotalPoint(x);
			}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy") ;
		Date d = dateFormat.parse("01/02/2015");
		//Date d1 = dateFormat.get2DigitYearStart();
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
		
	    User u = new User("sssss","qqqqq");
	//	us.addUser(u);
		Post p1 = new Post ( "test","rating");
	//	rs.findByPostUser(5L, 105L);
	//	ps.addPost(p1);
	//	ps.retrieveAllPosts();
	//	ps.retrievePost(1L);
	//	ps.updatePost(p1);
	//	ps.deletePost("6");
	
//	rs.addRate(3L, 18L, Rate.EXCELLENT);	
//	rs.addRate(1L, 18L, Rate.EXCELLENT);
//rs.sommeParPost(18L);
//	ps.test();
//	Rating r1 = new Rating (u,p1,Rate.GOOD);
//	rs.addRate(1L,11L,Rate.GOOD);
//	ps.calculTotal();
//	rs.getRateByPostIdJPQL(11L);
//ps.getRateByPostId(p1);	}
//	rs.AllRates();
  
 // ps.getAllbestPostJPQL();
//  ps.deleteAutoPost("5");
	Comment c = new Comment(1L,"first comment");
	//	cs.addComment(1L,21L,c);
		//cs.CommentQuery(18L);
	//	cs.addLike(c, 4L);
	//	cs.adddd( c);
	//	cs.addLike(c, 5L);
	
//	cs.verifUser(5L, likk);
	}
	
}
