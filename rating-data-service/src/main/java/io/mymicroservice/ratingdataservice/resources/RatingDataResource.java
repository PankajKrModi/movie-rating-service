package io.mymicroservice.ratingdataservice.resources;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mymicroservice.ratingdataservice.models.Rating;
import io.mymicroservice.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {
	@RequestMapping("/users/{userId}")
	public UserRating getRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(
				new Rating("123",4),
				new Rating("128",3)
				));
		return userRating;
	}
}
