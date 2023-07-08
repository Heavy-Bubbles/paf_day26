package sg.edu.nus.iss.paf_Day26.service;

import org.bson.Document;

import sg.edu.nus.iss.paf_Day26.model.TvShow;

//converting the json document to a model
public class Utility {

    public static TvShow toTvShow(Document doc){
        return new TvShow(
            doc.getInteger("id"), 
            doc.getString("name"),
            doc.getString("type"),
            doc.getString("language"),
            doc.getInteger("runtime")

        );
    }
    
}
