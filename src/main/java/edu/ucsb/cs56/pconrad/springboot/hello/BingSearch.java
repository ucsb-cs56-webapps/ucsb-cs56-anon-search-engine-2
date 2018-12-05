package edu.ucsb.cs56.pconrad.springboot.hello;
 import java.net.*;
import java.util.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Controller;



@Controller
public class BingSearch{


	public static ArrayList<SearchResult> getResult(String searchTerm) {

		ArrayList<SearchResult> results = new ArrayList<SearchResult>();

		String bingSubscriptionKey = Application.bingKey;
        String binghost = "https://api.cognitive.microsoft.com";
        String bingpath = "/bing/v7.0/search";



	        // Confirm the subscriptionKey is valid.
	        if (bingSubscriptionKey.length() != 32) {
	            System.out.println("Invalid Bing Search API subscription key!");
	            System.exit(1);
	        }

		try {
	            URL url = new URL(binghost + bingpath + "?q=" +  URLEncoder.encode(searchTerm, "UTF-8"));
	        	// Open the connection.
		        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		        connection.setRequestProperty("Ocp-Apim-Subscription-Key", bingSubscriptionKey);
	       		// Receive the JSON response body.
		        InputStream stream = connection.getInputStream();
		        String bingResponse = new Scanner(stream).useDelimiter("\\A").next();
		        stream.close();
 		        JsonParser parser = new JsonParser();
		        JsonObject json = parser.parse(bingResponse).getAsJsonObject();

		        json = parser.parse(json.get("webPages").toString()).getAsJsonObject();
		        JsonArray array = json.getAsJsonArray("value");
		        for(int i = 0; i < array.size(); ++i) {
		        	JsonObject jname = parser.parse(array.get(i).toString()).getAsJsonObject();
                    String name = jname.get("name").toString();
                    JsonObject jsnippet = parser.parse(array.get(i).toString()).getAsJsonObject();
                    String snippet = jname.get("snippet").toString();
                    JsonObject jurl_ = parser.parse(array.get(i).toString()).getAsJsonObject();
                    String url_ = jname.get("url").toString();
                    results.add(new SearchResult(name, snippet, url_));
		        }
		        return results;
	        }
	        catch (Exception e) {
	            e.printStackTrace(System.out);
                System.out.println("Unsuccessful Bing search");
	        }
        return null;
	}
}
