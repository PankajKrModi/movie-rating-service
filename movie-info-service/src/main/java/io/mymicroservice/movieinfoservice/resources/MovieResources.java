package io.mymicroservice.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mymicroservice.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResources {
	
	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {
		return new Movie(movieId,"Transformer","hollywood ovie");
	}
}
