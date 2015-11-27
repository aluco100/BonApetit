package bonapetit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
 
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
    private JTextField ingredientes;
    private IngredientManager IngrMngr = new IngredientManager();
    private JButton agregarIngredientes;
    private JScrollPane contenedoringredientes;
    private JButton limpiar;
    
    //elementos recetas display
    private JLabel textoRecetas, fotoreceta,ingredientesreceta;
    private JButton preparacionreceta;
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
        limpiar = new JButton ();
        try{
        Image img = ImageIO.read(getClass().getResource("clean.png"));
        limpiar.setIcon(new ImageIcon(img));
        } catch (IOException ex){}
        ingredientes = new JTextField("",10);
        contenedoringredientes = new JScrollPane(listaIngredientes);
        
        //agregando
        getContentPane().add(filtroIngredientes, BorderLayout.WEST);
        filtroIngredientes.setLayout(new GridLayout(2,1));
        JPanel buscadorPanel = new JPanel();
        buscadorPanel.add(ingredientes);
        buscadorPanel.add(agregarIngredientes);
        buscadorPanel.add(limpiar);
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
        preparacionreceta = new JButton("Pasos para preparar");
        
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
               try {
                   fotoreceta.setIcon(receta.parseImage(foto_Aux));
               } catch (IOException ex) {
                   Logger.getLogger(BonApetit.class.getName()).log(Level.SEVERE, null, ex);
               }
               ingredientesreceta.setText(Ingredientes);
               preparacionreceta.setText(link);
           }
       };
       
       
       ActionListener al7;
       al7 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               String textofiltro = ingredientes.getText();
               IngrMngr.addIngredient(textofiltro);
               String memory = IngrMngr.getIngredientsMemory();
               listaIngredientes.setText(memory);
               Provider p = new Provider("http://www.recipepuppy.com/api/?i="+memory);
               resultadojson=p.getJSONQuery();
               Parser filtroactual= new Parser(resultadojson);
               String[] posiblesrecetas = filtroactual.getPosiblesRecetas();
               posiblesRecetasList.setListData(posiblesrecetas);
           }
       };
       
       ActionListener al8;
       al8 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
              IngrMngr.clearIngredient();
              String memory = IngrMngr.getIngredientsMemory();
              listaIngredientes.setText(memory);
              ingredientes.setText(memory);
              Provider p = new Provider("http://www.recipepuppy.com/api/?");
               resultadojson=p.getJSONQuery();
               Parser filtroactual= new Parser(resultadojson);
               String[] posiblesrecetas = filtroactual.getPosiblesRecetas();
               posiblesRecetasList.setListData(posiblesrecetas);
           }
       };
       
       ActionListener al9;
       al9 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               String url = preparacionreceta.getText();
               URI uri;
               try {
                   uri = new URI(url);
                   Desktop dt = Desktop.getDesktop();
                   dt.browse(uri);
               } catch (URISyntaxException | IOException ex) {
                   Logger.getLogger(BonApetit.class.getName()).log(Level.SEVERE, null, ex);
               } 
               
           }
       };

           
        picoteo.addActionListener(al1);
        ensalada.addActionListener(al2);
        platoprincipal.addActionListener(al3);
        tragos.addActionListener(al4);
        postres.addActionListener(al5);
        posiblesRecetasList.addListSelectionListener(al6);
        agregarIngredientes.addActionListener(al7);
        limpiar.addActionListener(al8);
        preparacionreceta.addActionListener(al9);
        
    
    }
    public static void main(String[] args) {
        JFrame v = new BonApetit();
        v.setVisible(true);
     
    }
    
}
