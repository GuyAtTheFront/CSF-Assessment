package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static ibf2022.batch1.csf.assessment.server.utils.MONGO_CONSTANTS.*;

@Repository
public class MovieRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.comments.find({movieName: "Mr Robot"}).count();
	public int countComments(String movieName) {

        // db.comments.find({movieName: "Mr Robot"}).count();

		Criteria criteria = Criteria.where(FIELD_MOVIE_NAME).is(movieName);
        Query query = Query.query(criteria);
        long count = mongoTemplate.count(query, COLLECTION_COMMENTS);

		return Long.valueOf(count).intValue();
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	// db.comments.insertOne({movieName: 'Mr Robot', posterName: 'anonymous', rating: 5, comment: 'pwned'});

	public void insertComment(Document doc) {
        // db.comments.insertOne({movieName: 'Mr Robot', posterName: 'anonymous', rating: 5, comment: 'pwned'});
        Document inserted = mongoTemplate.insert(doc, COLLECTION_COMMENTS);
        return;
    }
}
