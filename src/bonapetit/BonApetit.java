package bonapetit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
public class BonApetit extends JFrame{
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
        
        pack();
        
        //en caso de cerrarse detener programa
        addWindowListener(
            new WindowAdapter(){
                public void windowClosing (WindowEvent evt){
                    System.exit(0);
                }
            }
        );
    }
    
    void construirCategorias(){
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
        picoteo = new JButton("picoteo");
        ensalada = new JButton("ensaladas");
        platoprincipal = new JButton("dishes");
        tragos = new JButton("tragos");
        postres = new JButton("postres");
        
        //agregar todo al frame
        getContentPane().add(categorias, BorderLayout.NORTH);
        categorias.add(textoCategorias);
        categorias.add(picoteo);
        categorias.add(ensalada);
        categorias.add(platoprincipal);
        categorias.add(tragos);
        categorias.add(postres);
    }
    
    void filtroIngredientes(){
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
    
    void contruirRecetas(){
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
        contenedorRecetas.add(textoRecetas);
        contenedorRecetas.add(fotoreceta);
        contenedorRecetas.add(ingredientesreceta);
        contenedorRecetas.add(preparacionreceta);
        recetas.add(contenedorRecetas);
    }
    
    void construirResultadoBusqueda(){
        //agregar el panel
        resultado = new JPanel();
        
        //instanciar elementos de las posibles recetas
        String[] posiblesRecetas = {"Receta1", "Receta2", "Receta3"};
        posiblesRecetasList = new JList(posiblesRecetas);
        scrollRecetas = new JScrollPane(posiblesRecetasList);
        
        //agregando el resultado
        JPanel contenedorResultado = new JPanel();
        contenedorResultado.setLayout(new FlowLayout());
        contenedorResultado.add(resultado);
        resultado.add(scrollRecetas);
        getContentPane().add(contenedorResultado, BorderLayout.EAST);
    }
    public static void main(String[] args) {
        JFrame v = new BonApetit();
        v.setVisible(true);
    }
    
}
