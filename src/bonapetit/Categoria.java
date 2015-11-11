package bonapetit;

import java.lang.*;
import java.io.IOException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
public class Categoria {
    
    private final String nombre;
    
    public Categoria(String Nombre){
        this.nombre = Nombre;
    }
    
    public JSONArray getJSONResult(){
        Provider prov = new Provider("http://www.recipepuppy.com/api/?i=&q="+this.nombre);
        return prov.getJSONQuery();
    }
    
    public String[] getRecipiesByCategory(){
        JSONArray resultJson = this.getJSONResult();
        Parser RecetasInit = new Parser(resultJson);
        return RecetasInit.getPosiblesRecetas();
    }
    
}
