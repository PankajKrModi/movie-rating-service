package io.mymicroservice.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.mymicroservice.moviecatalogservice.models.CatalogItem;
import io.mymicroservice.moviecatalogservice.models.Movie;
import io.mymicroservice.moviecatalogservice.models.Rating;

@Service
public class MovieInfoService {

	@Autowired
	RestTemplate restTemplate ; 
	
	@HystrixCommand(fallbackMethod ="getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		
		Movie movieObject = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
//				webClientBuilder.build().get().uri("http://movie-info-service/movies/"+rating.getMovieId())
//				.retrieve().bodyToMono(Movie.class).block(); //block turns async to sync call

		return new CatalogItem(movieObject.getName(), movieObject.getDesc(), rating.getRating());

	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("no movie found","none",0);
	}

}
