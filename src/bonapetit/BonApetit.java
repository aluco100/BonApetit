package bonapetit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;

import java.io.IOException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
 
public class BonApetit extends JFrame{
    
   public  JSONArray resultadojson;
    
    //paneles
    private JPanel categorias,recetas,filtroIngredientes,resultado;
    
    //elementos categorias
    private JButton ensalada,picoteo,platoprincipal,tragos,postres;
    private JLabel textoCategorias;
    
    //elementos filtro ingredientes
    private JLabel listaIngredientes;
    private JComboBox ingredientes;
    private JButton agregarIngredientes;
    private JScrollPane contenedoringredientes;
    
    //elementos recetas display
    private JLabel textoRecetas, fotoreceta,ingredientesreceta,preparacionreceta;
    
    //elementos posibles recetas 
    private JList posiblesRecetasList;
    private JScrollPane scrollRecetas;
    
    //constructor
    public BonApetit(){
        //titulo de la interfaz
        setTitle("Bon Apetit v1.0");
        
        //instanciar los paneles
        construirCategorias();
        filtroIngredientes();
        contruirRecetas();
        construirResultadoBusqueda();
        construirEventos();
        
        pack();
        
        //en caso de cerrarse detener programa
        addWindowListener(
            new WindowAdapter(){
                @Override
                public void windowClosing (WindowEvent evt){
                    System.exit(0);
                }
            }
        );
    }
    
    private void construirCategorias(){
        //inicializar el panel de categoria
        categorias = new JPanel();
        
        //instanciar la foto de icono
        URL imageurl = getClass().getResource("icon.jpg");
        ImageIcon iconcategoria = new ImageIcon(imageurl);
        Image newicon = iconcategoria.getImage();
        Image resized = newicon.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        iconcategoria = new ImageIcon(resized);
        
        //inicializar el label
        textoCategorias = new JLabel(iconcategoria);
        textoCategorias.setHorizontalAlignment(SwingConstants.LEFT);
        
        //inicializar los botones
        picoteo = new JButton("Snack");
        ensalada = new JButton("Salad");
        platoprincipal = new JButton("Main Dish");
        tragos = new JButton("Drink");
        postres = new JButton("Dessert");
        
        
        
        //agregar todo al frame
        getContentPane().add(categorias, BorderLayout.NORTH);
        categorias.add(textoCategorias);
        categorias.add(picoteo);
        categorias.add(ensalada);
        categorias.add(platoprincipal);
        categorias.add(tragos);
        categorias.add(postres);
    }
    
    private void filtroIngredientes(){
        //instanciar panel
        filtroIngredientes = new JPanel();
        
        //instanciar elementos
        listaIngredientes = new JLabel("Lista de Ingredientes ");
        agregarIngredientes = new JButton("Agregar");
        String[] ingr = {"Carne", "Papas", "Tomate"};
        ingredientes = new JComboBox(ingr);
        ingredientes.setSelectedIndex(0);
        contenedoringredientes = new JScrollPane(listaIngredientes);
        
        //agregando
        getContentPane().add(filtroIngredientes, BorderLayout.WEST);
        filtroIngredientes.setLayout(new GridLayout(2,1));
        JPanel buscadorPanel = new JPanel();
        buscadorPanel.add(ingredientes);
        buscadorPanel.add(agregarIngredientes);
        filtroIngredientes.add(buscadorPanel);
        filtroIngredientes.add(contenedoringredientes);
        
    }
    
    private void contruirRecetas(){
        //instanciar el panel
        recetas = new JPanel();
        
        //instanciar los elementos
        textoRecetas = new JLabel("Nombre Receta");
        fotoreceta = new JLabel("Foto Receta");
        ingredientesreceta = new JLabel("Lista Ingredientes");
        preparacionreceta = new JLabel("Pasos para preparar");
        
        //agregar
        getContentPane().add(recetas, BorderLayout.CENTER);
        JPanel contenedorRecetas = new JPanel();
        contenedorRecetas.setLayout(new GridLayout(2,2,20,180));
        textoRecetas.setHorizontalAlignment(JLabel.RIGHT);
        contenedorRecetas.add(textoRecetas);
        contenedorRecetas.add(fotoreceta);
        contenedorRecetas.add(ingredientesreceta);
        contenedorRecetas.add(preparacionreceta);
        recetas.add(contenedorRecetas);
    }
    
    private void construirResultadoBusqueda(){
        //agregar el panel
        resultado = new JPanel();
        
        Provider p = new Provider("http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3");
        resultadojson = p.getJSONQuery();
        posiblesRecetasList = new JList(p.defaultRecetas());
        scrollRecetas = new JScrollPane(posiblesRecetasList);
        
        //agregando el resultado
        JPanel contenedorResultado = new JPanel();
        contenedorResultado.setLayout(new FlowLayout());
        contenedorResultado.add(resultado);
        resultado.add(scrollRecetas);
        getContentPane().add(contenedorResultado, BorderLayout.EAST);
    }
    private void construirEventos(){
        
        ActionListener al1;
       al1 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Categoria categoria = new Categoria("Snack");
               resultadojson = categoria.getJSONResult();
               posiblesRecetasList.setListData(categoria.getRecipiesByCategory());
           }
       };
        
        ActionListener al2;
       al2 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Categoria categoria = new Categoria("Salad");
               resultadojson = categoria.getJSONResult();
               posiblesRecetasList.setListData(categoria.getRecipiesByCategory());
           }
       };
        
         ActionListener al3;
       al3 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Categoria categoria = new Categoria("Dish");
               resultadojson = categoria.getJSONResult();
               posiblesRecetasList.setListData(categoria.getRecipiesByCategory());
           }
       };
         
          ActionListener al4;
       al4 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Categoria categoria = new Categoria("Drinks");
               resultadojson = categoria.getJSONResult();
               posiblesRecetasList.setListData(categoria.getRecipiesByCategory());
           }
       };
          
           ActionListener al5;
       al5 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               Categoria categoria = new Categoria("Dessert");
               resultadojson = categoria.getJSONResult();
               posiblesRecetasList.setListData(categoria.getRecipiesByCategory());
           }
       };
           
       ListSelectionListener al6;
       al6 = new ListSelectionListener(){
           @Override
           public void valueChanged(ListSelectionEvent e) {
               String nombreReceta = posiblesRecetasList.getSelectedValue().toString();
               Recipe receta = new Recipe(nombreReceta);
               String titulo_Aux = receta.getParameter(resultadojson, "title");
               String Ingredientes = receta.getParameter(resultadojson, "ingredients");
               String link = receta.getParameter(resultadojson, "href");
               String foto_Aux = receta.getParameter(resultadojson, "thumbnail");
               
               textoRecetas.setText(titulo_Aux);
               fotoreceta.setText("");
               fotoreceta.setIcon(receta.parseImage(foto_Aux));
               ingredientesreceta.setText(Ingredientes);
               preparacionreceta.setText(link);
           }
       };
       
     

           
        picoteo.addActionListener(al1);
        ensalada.addActionListener(al2);
        platoprincipal.addActionListener(al3);
        tragos.addActionListener(al4);
        postres.addActionListener(al5);
        posiblesRecetasList.addListSelectionListener(al6);
        
    
    }
    public static void main(String[] args) {
        JFrame v = new BonApetit();
        v.setVisible(true);
     
    }
    
}
