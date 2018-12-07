package edu.ucsb.cs56.pconrad.springboot.hello;

import edu.ucsb.cs56.pconrad.springboot.hello.SearchQuery;
import edu.ucsb.cs56.pconrad.springboot.hello.SearchResult;
import edu.ucsb.cs56.pconrad.springboot.hello.DuckDuckGoSearchManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;
import java.util.ArrayList;
import javax.validation.Valid;


@RestController
public class SearchController{

	// Used https://www.mkyong.com/spring-boot/spring-boot-ajax-example/ for reference
	@PostMapping("/search")
    public ResponseEntity<?> getSearchResult(@Valid @RequestBody String rawQuery, Errors errors){
		SearchQuery query = new SearchQuery(rawQuery);
		String errorMsg = new String();
		// We will eventually return something more complex than a String
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();

		if(errors.hasErrors()) {
			errorMsg = (errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(errorMsg);
		}

		// API's should return an ArrayList<SearchResult> Object.

		if(query.getEngine().equals("Google")){
			results = GoogleSearch.gSearch(query);
		} else if(query.getEngine().equals("DuckDuckGo")) {
			try {
				results = DuckDuckGoSearchManager.search(query);
			} catch (Exception e) {
				System.out.println("DuckDuckGoSearchManager Error: " + e);
			}

		}
		else if(query.getEngine().equals("Bing")){
			String searchTerm = query.getUserEntry();
	   		try {
				results = BingSearch.getResult(searchTerm);
			} catch(Exception e) {
				System.out.println("ISSSUUUUUEEE: " + e);
			}
		}

		return ResponseEntity.ok(results);
	}
}
