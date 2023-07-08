package sg.edu.nus.iss.paf_Day26.controller;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.paf_Day26.model.TvShow;
import sg.edu.nus.iss.paf_Day26.service.ShowService;

@Controller
@RequestMapping("/")
public class ShowsController {

    @Autowired
    ShowService showService;
    
    @GetMapping
    public String getHomePage(){
        return "home";
    }

    // @GetMapping("/submit")
    // public ResponseEntity<List<Document>> getTvShow(@RequestParam(value = "name", required = true) String name){
    //     List<Document> shows = showService.getShowbyName(name);
       
    //     return ResponseEntity.ok().body(shows);
    // }

    // @GetMapping("/submit")
    // public String getTvShow(@RequestParam(value = "name", required = true) String name, Model model){
    //     List<Document> shows = showService.getShowbyName(name);
    //     model.addAttribute("shows", shows);
    //     return "shows";
    // }

    // return model and view 
    @GetMapping("/submit")
    public ModelAndView getShow(@RequestParam(value = "name", required = true) String name){

        Optional<TvShow> opt = showService.findShowByName(name);

        ModelAndView mav = new ModelAndView();

        if (opt.isEmpty()){
            mav.setViewName("not-found");
            mav.addObject("message", "Cannot find %s".formatted(name));
            mav.setStatus(HttpStatusCode.valueOf(404));
            return mav;
        } else {
            mav.setViewName("result");
            mav.addObject("title", name);
            mav.addObject("show", opt.get());
            mav.setStatus(HttpStatusCode.valueOf(200));
        }

        return mav;

    }

    // get as json teacher's solution
    // must anntotate as @ResponseBody if it is a restcontroller in a controller
    @GetMapping(path="/show", produces = MediaType.APPLICATION_NDJSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getShowAsJson(@RequestParam String name){

        List<Document> result = showService.getShowbyName(name);

        if (result.size() <= 0){
            return ResponseEntity.status(404)
                .body("{}");
        }

        Document doc = result.get(0);
        return (ResponseEntity.ok(doc.toJson()));

    }

}
