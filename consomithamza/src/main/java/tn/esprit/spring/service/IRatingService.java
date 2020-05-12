package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Rate;
import tn.esprit.spring.entity.Rating;

public interface IRatingService {
	public void addRate(Long userId,Long postId,Rate r );
	public List <Rating>AllRates();
//	public int getRateByPostIdJPQL(Long postId);
	public float sommeParPost(Long postId);
	public String findByPostUser(Long userId,Long postId);
}
