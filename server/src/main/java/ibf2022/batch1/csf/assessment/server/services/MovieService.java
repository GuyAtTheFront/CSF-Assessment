package ibf2022.batch1.csf.assessment.server.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.utils.Utils;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;;

@Service
public class MovieService {

	@Value("${ny.api.key}")
	private String API_KEY;

	private final String URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
	
	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		
        String uri = UriComponentsBuilder.fromUriString(URL)
                                .queryParam("query", query)
                                .queryParam("api-key", API_KEY)
                                .build()
                                .toUriString();

        RequestEntity<Void> request = RequestEntity
                                        .get(uri)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .build();

        RestTemplate template = new RestTemplate();

		ResponseEntity<String> response = null;

		try { 
        	response = template.exchange(request, String.class);

		} catch (Exception e) {

			return new LinkedList<>();
		}

		if(!response.getStatusCode().is2xxSuccessful()) {
			return new LinkedList<>();
		}

		JsonObject json = Utils.toJson(response.getBody());

		if(JsonValue.NULL == json.get("results")) {
			return new LinkedList<>();
		}

		JsonArray details = json.getJsonArray("results");

		System.out.println(details);

		List<Review> reviews =  details.stream()
									.map(x -> (JsonObject) x)
									.map(x -> Utils.toReview(x))
									.toList();


		return reviews;
	}

}
