package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.User;

public interface IUserRepository extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.userName=:userName and u.password=:password")
public User getUserAutho(@Param("userName")String login, @Param("password")String password);
	  

}
