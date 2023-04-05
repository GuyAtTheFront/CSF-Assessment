package ibf2022.batch1.csf.assessment.server.utils;

import java.io.StringReader;
import java.util.List;

import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Utils {
    
    public static JsonObject toJson(String jsonString) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject json = jsonReader.readObject();
        jsonReader.close();

        return json;
    }

    public static Review toReview(JsonObject json) {
        Review review = new Review();

        review.setTitle(json.getString("display_title"));
        review.setRating(json.getString("mpaa_rating"));
        review.setByline(json.getString("byline"));
        review.setHeadline(json.getString("headline"));
        review.setSummary(json.getString("summary_short"));
        review.setReviewURL(json.getJsonObject("link").getString("url"));

        if (JsonValue.NULL == json.get("multimedia")) {
            // System.out.println("null");
            review.setImage(null);
        } else{
            review.setImage(json.getJsonObject("multimedia").getString("src"));
        }

        return review;
    }

    public static JsonArrayBuilder toJsonArrayBuilder (List<Review> reviews) {

        JsonArrayBuilder builder = Json.createArrayBuilder();
        reviews.forEach(x -> builder.add(Utils.toJson(x)));

        return builder;
    }

    public static JsonObject toJson (Review review) {
        return Json.createObjectBuilder()
                .add("title", review.getTitle())
                .add("rating", review.getRating())
                .add("byline", review.getByline())
                .add("headline", review.getHeadline())
                .add("summary", review.getSummary())
                .add("reviewURL", review.getReviewURL())
                .add("image", review.getImage())
                .add("commentCount", review.getCommentCount())
                .build();
    }

}
