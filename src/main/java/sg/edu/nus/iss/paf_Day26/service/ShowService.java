package sg.edu.nus.iss.paf_Day26.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_Day26.model.TvShow;
import sg.edu.nus.iss.paf_Day26.repository.ShowsRepo;

@Service
public class ShowService {
    @Autowired
    ShowsRepo showsRepo;

    public List<Document> getShowbyName(String name){
        return showsRepo.findShowsByName(name);
    }

    public Optional<TvShow> findShowByName(String name){
        List<Document> result = showsRepo.findShowsByName(name);
        if (result.size() <= 0){
            return Optional.empty();
        }

        return Optional.of(Utility.toTvShow(result.get(0)));
    }
}
