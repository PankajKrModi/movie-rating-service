package io.mymicroservice.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.mymicroservice.moviecatalogservice.models.CatalogItem;
import io.mymicroservice.moviecatalogservice.models.Movie;
import io.mymicroservice.moviecatalogservice.models.Rating;

@Service
public class MovieInfoService {

	@Autowired
	RestTemplate restTemplate ; 
	
	@HystrixCommand(fallbackMethod ="getFallbackCatalogItem",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
		})
	public CatalogItem getCatalogItem(Rating rating) {
		
		Movie movieObject = restTemplate.
				getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new CatalogItem(movieObject.getName(), movieObject.getDesc(), rating.getRating());

	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("no movie found","none",0);
	}

}
