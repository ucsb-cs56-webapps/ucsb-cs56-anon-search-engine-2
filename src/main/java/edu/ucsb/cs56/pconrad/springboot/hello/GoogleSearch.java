package edu.ucsb.cs56.pconrad.springboot.hello;

import java.util.*;
import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class GoogleSearch{

    static String apiKey = Application.googleApiKey;
    static String customSearchEngineKey = Application.googleEngineId;
    static String searchURL = "https://www.googleapis.com/customsearch/v1?";


    public static ArrayList<SearchResult> gSearch(SearchQuery query) {

    	String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey+"&q=";

    	String searchTerm=query.getUserEntry();
    	ArrayList<SearchResult> results = new ArrayList<SearchResult>();
    	try {
    	    URL url=new URL(toSearch + URLEncoder.encode(searchTerm,"UTF-8"));

    	    HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
    	    //  connection.setRequestProperty("User-Agent","UCSB/1.0");
    	    BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	    String line;
    	    StringBuffer buffer=new StringBuffer();
    	    while((line=input.readLine())!=null){
    		          buffer.append(line);
    	    }
    	    input.close();
            connection.disconnect();
    	    JsonParser parser = new JsonParser();
    	    JsonObject json = parser.parse(buffer.toString()).getAsJsonObject();


    	    JsonArray array = json.getAsJsonArray("items");
    	    for(int i = 0; i < array.size(); i++) {
        		JsonObject jname = parser.parse(array.get(i).toString()).getAsJsonObject();
        		String name = jname.get("title").toString();
        		JsonObject jsnippet = parser.parse(array.get(i).toString()).getAsJsonObject();
        		String snippet = jname.get("snippet").toString();
        		JsonObject jurl_ = parser.parse(array.get(i).toString()).getAsJsonObject();
        		String url_ = jname.get("link").toString();
        		results.add(new SearchResult(name, snippet, url_));
    	    }
    	    return results;

    	} catch(Exception e){
    	    e.printStackTrace(System.out);
    	    System.out.println("Error getting Google results");
    	}
    	return null;
        }
}
