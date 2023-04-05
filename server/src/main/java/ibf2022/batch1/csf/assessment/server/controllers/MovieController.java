package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import ibf2022.batch1.csf.assessment.server.utils.Utils;
import jakarta.json.Json;

@RestController
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	// TODO: Task 3, Task 4, Task 8

	@Autowired
	MovieService movieService;

	@Autowired
	MovieRepository movieRepository;
	
	// Task 3
	@GetMapping(path="/search")
	public ResponseEntity<String> searchMovie(@RequestParam String query) {

		List<Review> reviews = movieService.searchReviews(query);

		// set default image
		reviews.stream().forEach( x -> { 
			if(!x.hasImage()) x.setImage("");
			Integer count = movieRepository.countComments(x.getTitle());
			x.setCommentCount(count);
		});


		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Json.createObjectBuilder()
							.add("reviews", Utils.toJsonArrayBuilder(reviews))
							.build().toString());
	}


	@PostMapping(path="/comment", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> postComment(@RequestBody MultiValueMap<String, String> form) {

		System.out.println(">>>"+form.getFirst("movieName"));

		String json = Json.createObjectBuilder()
						.add("movieName", form.getFirst("movieName"))
						.add("posterName", form.getFirst("posterName"))
						.add("rating", form.getFirst("rating"))
						.add("comment", form.getFirst("comment"))
						.build().toString();

		Document doc = Document.parse(json);
		movieRepository.insertComment(doc);

		return ResponseEntity.ok().build();

	}

}
