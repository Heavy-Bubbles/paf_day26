package sg.edu.nus.iss.paf_Day26;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.paf_Day26.repository.ShowsRepo;

@SpringBootApplication
public class PafDay26Application implements CommandLineRunner{
	
	@Autowired
	ShowsRepo showsRepo;
	public static void main(String[] args) {
		SpringApplication.run(PafDay26Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		List<Document> docs = showsRepo.findShowsByName("Bitten");
		for (Document d : docs){
			String name = d.getString("name");
			String type = d.getString("type");
			Integer runtime = d.getInteger("runtime");
			// return json array as a list of string 
			List<String> genres = d.getList("genres", String.class);
			// get average rating
			Document rateDoc = d.get("rating", Document.class);
			Double avg = rateDoc.getDouble("average");

			System.out.printf("title: %s, type: %s, runtime: %d, avg rating: %.2f\n", name, type, runtime, avg);
			System.out.printf("\tgenres: %s\n", genres);
		}
	}

}
