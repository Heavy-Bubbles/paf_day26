package sg.edu.nus.iss.paf_Day26.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowsRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    // F for field, C for column

    public static final String F_GENRES = "genres";
    public static final String F_ID = "_id";
    public static final String F_NAME = "name";
    public static final String C_TVSHOW = "tvshow";

    // in assessment, on top of the java, must write mongo query
    // db.tvshow.find({ name: 'the name' })
    public List<Document> findShowsByName(String name){

        // setting the filter 
        Criteria criteria = Criteria.where(F_NAME).regex(name);

        // Create mongo query with the filter
        Query query = Query.query(criteria);

        // Perform the query
       return mongoTemplate.find(query, Document.class, C_TVSHOW);
    }

    public List<String> findShowsByGenre(Object... genreList){
        
        // create the filter
        Criteria criteria = Criteria.where(F_GENRES)
            .in(genreList);

        Query query = Query.query(criteria);
        query.fields()
            .exclude(F_ID)
            .include(F_NAME);

        return mongoTemplate.find(query, String.class, C_TVSHOW);
    }
}
