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
 
public class Provider {
    
    private String URL;
    
    //constructor
    public Provider(String url){
        this.URL = url;
    }
    
    public JSONArray getJSONQuery(){
        JSONArray genreArray = new JSONArray();
        try {
            String genreJson = IOUtils.toString(new URL(this.URL));
            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            // get the title
            System.out.println(genreJsonObject.get("title"));
            // get the data
            genreArray = (JSONArray) genreJsonObject.get("results");
            // get the first genre
            JSONObject firstGenre = (JSONObject) genreArray.get(0);
            System.out.println(firstGenre.get("title"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return genreArray;
    } 
}