package io.mymicroservice.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.mymicroservice.moviecatalogservice.models.Rating;
import io.mymicroservice.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfoService {

	@Autowired
	RestTemplate restTemplate ; 
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		UserRating userRating=
		restTemplate.
				getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class); 			
		return userRating;
	}
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId("0");
		userRating.setUserRating(Arrays.asList(new Rating("0",0)));
		return userRating;		
	}
}
