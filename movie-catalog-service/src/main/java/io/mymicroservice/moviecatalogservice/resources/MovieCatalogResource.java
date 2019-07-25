package io.mymicroservice.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.mymicroservice.moviecatalogservice.models.CatalogItem;
import io.mymicroservice.moviecatalogservice.models.CatalogItemsList;
import io.mymicroservice.moviecatalogservice.models.UserRating;
import io.mymicroservice.moviecatalogservice.services.MovieInfoService;
import io.mymicroservice.moviecatalogservice.services.UserRatingInfoService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	UserRatingInfoService userRatingInfoService;
	@Autowired
	MovieInfoService movieInfoService;
	
	@RequestMapping("/{userId}")
	public CatalogItemsList getCatalog(@PathVariable("userId") String userId){
		UserRating userRating;

		userRating = userRatingInfoService.getUserRating(userId);

		List<CatalogItem> catalogItems = userRating.getUserRating().stream()
				.map(rating->movieInfoService.getCatalogItem(rating)).collect(Collectors.toList());
		

		return new CatalogItemsList(catalogItems);
	}
	
	
	
	
}
