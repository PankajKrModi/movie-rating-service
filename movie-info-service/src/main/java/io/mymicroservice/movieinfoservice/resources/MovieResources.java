package io.mymicroservice.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.mymicroservice.movieinfoservice.models.Movie;
import io.mymicroservice.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResources {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/110?api_key=0d3c8b60df0e3710ad0b4ee456b1809f", MovieSummary.class);
//				"https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}
}
