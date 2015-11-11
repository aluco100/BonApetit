package bonapetit;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Recipe {
    private String Nombre;

    public Recipe(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getParameter(JSONArray query, String param) {
        String result = "";
        for (Object query1 : query) {
            JSONObject aux = (JSONObject) query1;
            if(this.Nombre.equals(aux.get("title").toString())){
                System.out.println("ture");
                result = aux.get(param).toString();
                return result;
            }
        }
           
        return result;
    }
    
    public ImageIcon parseImage(String link) throws IOException{
        ImageIcon iconcategoria;
        if(!link.equals("")){
            URL imageurl = null;
            try {
                imageurl = new URL(link);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Recipe.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image img = ImageIO.read(imageurl);
            iconcategoria = new ImageIcon(imageurl);
            Image newicon = iconcategoria.getImage();
            Image resized = newicon.getScaledInstance(200, 160, Image.SCALE_SMOOTH);
            iconcategoria = new ImageIcon(resized);
        }else{
            URL imageurl = getClass().getResource("icon.jpg");
            iconcategoria = new ImageIcon(imageurl);
            Image newicon = iconcategoria.getImage();
            Image resized = newicon.getScaledInstance(200, 160, Image.SCALE_SMOOTH);
            iconcategoria = new ImageIcon(resized);
        }
        
        return iconcategoria;
        
    }
}
