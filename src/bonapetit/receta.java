/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonapetit;

/**
 *
 * @author ignacia
 */

import java.io.IOException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
 
public class receta {
 
    public static void main(String[] args) {
        String url = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3";
        /*
         * {"title":"Free Music Archive - Genres","message":"","errors":[],"total" : "161","total_pages":81,"page":1,"limit":"2",
         * "dataset":
         * [{"genre_id": "1","genre_parent_id":"38","genre_title":"Avant-Garde" ,"genre_handle": "Avant-Garde","genre_color":"#006666"},
         * {"genre_id":"2","genre_parent_id" :null,"genre_title":"International","genre_handle":"International","genre_color":"#CC3300"}]}
         */
        try {
            String genreJson = IOUtils.toString(new URL(url));
            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            // get the title
            System.out.println(genreJsonObject.get("title"));
            // get the data
            JSONArray genreArray = (JSONArray) genreJsonObject.get("results");
            // get the first genre
            for(int i=0; i<genreArray.size();i++){
            JSONObject firstGenre = (JSONObject) genreArray.get(i);
            System.out.println(firstGenre.get("title"));}
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}