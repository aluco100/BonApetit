package bonapetit;
import java.io.IOException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
 
public class Provider {
    
    private final String URL;
    
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
           
        } catch (IOException | ParseException e) {
        }
        return genreArray;
    }
    
    public String[] defaultRecetas(){
        JSONArray resultJSON = this.getJSONQuery();
        
        JSONObject aux;
        
        String[] posiblesRecetas = new String[resultJSON.size()];
        
        for(int i=0; i<resultJSON.size();i++){
        aux = (JSONObject)resultJSON.get(i);
        posiblesRecetas[i]= aux.get("title").toString();
        }
        
        return posiblesRecetas;
    }
}