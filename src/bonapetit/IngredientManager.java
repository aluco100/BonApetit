package bonapetit;

import javax.swing.*;

public class IngredientManager {
    private String Ingredients;
    
    public IngredientManager(){
        this.Ingredients = "";
    }
    
    //getter
    public String getIngredientsMemory(){
        return this.Ingredients;
    }
    
    //metodos
    public void addIngredient(String Ingredient){
        if(Ingredient.isEmpty()){
            JOptionPane.showMessageDialog(null,"Debes agregar un ingrediente.",
                                          "Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            if(this.Ingredients.isEmpty()){
                this.Ingredients = Ingredient;
            }else{
                this.Ingredients += ","+Ingredient;
            }
        }
    }
    
    
    public void clearIngredient(){
        this.Ingredients = "";
    }
}
