package io.mymicroservice.moviecatalogservice.models;

import java.io.Serializable;
import java.util.List;

public class UserRating implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
	
}
