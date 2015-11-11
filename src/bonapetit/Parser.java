package bonapetit;

//incluir librerias
import java.lang.*;
import java.io.IOException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Parser {
    private final JSONArray reference;
    
    public Parser(JSONArray Reference){
        this.reference = Reference;
    }
    
    public String[] getPosiblesRecetas(){
        JSONObject aux;
        //instanciar elementos de las posibles recetas
        String[] posiblesRecetas = new String[this.reference.size()];
        for(int i=0; i<this.reference.size();i++){
        aux = (JSONObject)this.reference.get(i);
        posiblesRecetas[i]= aux.get("title").toString();
        }
        
        return posiblesRecetas;
        
    }
}
