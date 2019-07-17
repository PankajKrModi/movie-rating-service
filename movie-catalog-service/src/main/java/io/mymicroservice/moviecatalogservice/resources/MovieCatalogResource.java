package io.mymicroservice.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.mymicroservice.moviecatalogservice.models.CatalogItem;
import io.mymicroservice.moviecatalogservice.models.CatalogItemsList;
import io.mymicroservice.moviecatalogservice.models.Movie;
import io.mymicroservice.moviecatalogservice.models.Rating;
import io.mymicroservice.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	RestTemplate restTemplate ; 
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public CatalogItemsList getCatalog(@PathVariable("userId") String userId){
		UserRating userRating;
		
		userRating =	webClientBuilder.build().get()
				.uri("http://rating-data-service/ratingsdata/users/"+userId)
				.retrieve().bodyToMono(UserRating.class).block();
			
		List<CatalogItem> catalogItems = userRating.getUserRating().stream().map(rating->{
			Movie movieObject = webClientBuilder.build().get().uri("http://movie-info-service/movies/"+rating.getMovieId())
			.retrieve().bodyToMono(Movie.class).block(); //block turns async to sync call
			
		return new CatalogItem(movieObject.getName(), movieObject.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
				
		return new CatalogItemsList(catalogItems);
	}
}
