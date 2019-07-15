package io.mymicroservice.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.mymicroservice.moviecatalogservice.models.CatalogItem;
import io.mymicroservice.moviecatalogservice.models.Movie;
import io.mymicroservice.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		RestTemplate restTemplate = new RestTemplate(); 
		
		List<Rating> ratings = Arrays.asList(
				new Rating("123",4),new Rating("124",3)
				); 
		return ratings.stream().map(rating->{
			Movie movieObject = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(),
					Movie.class);
		return new CatalogItem(movieObject.getName(), movieObject.getDesc(), rating.getRating());
		})
				.collect(Collectors.toList());
	}
}
