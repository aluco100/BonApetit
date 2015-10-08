package bonapetit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
public class BonApetit extends JFrame{
    //paneles
    private JPanel categorias;
    private JLabel textoCategorias;
    private JPanel recetas;
    private JLabel textoRecetas;
    private JPanel filtroIngredientes;
    private JPanel buscadorIngredientes;
    private JPanel resultado;
    private JLabel textoResultado;
    
    //botones categorias
    private JButton ensalada;
    private JButton picoteo;
    private JButton platoprincipal;
    private JButton tragos;
    private JButton postres;
    
    //elementos filtro ingredientes
    private JTextField buscador;
    private JList ingredientes;
    private JScrollPane contenedoringredientes;
    
    
    //constructor
    public BonApetit(){
        //instanciar los paneles
        categorias = new JPanel();
        recetas = new JPanel();
        filtroIngredientes = new JPanel();
        buscadorIngredientes = new JPanel();
        resultado = new JPanel();
        
        //icono categoria
        URL imageurl = getClass().getResource("icon.jpg");
        ImageIcon iconcategoria = new ImageIcon(imageurl);
        Image newicon = iconcategoria.getImage();
        Image resized = newicon.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        iconcategoria = new ImageIcon(resized);
        
        //instanciar los labels
        textoCategorias = new JLabel(iconcategoria);
        textoRecetas = new JLabel();
        textoResultado = new JLabel();
        
        //instanciar botones categorias
        picoteo = new JButton("picoteo");
        ensalada = new JButton("ensaladas");
        platoprincipal = new JButton("dishes");
        tragos = new JButton("tragos");
        postres = new JButton("postres");
        
        //instanciar elementos filtro ingredientes
        buscador = new JTextField("Buscar ingrediente", 20);
        String[] ingr = {"Carne", "Papas", "Tomate"};
        ingredientes = new JList(ingr);
        contenedoringredientes = new JScrollPane(ingredientes);
        
        //centrar los labels
        textoCategorias.setHorizontalAlignment(SwingConstants.LEFT);
        textoRecetas.setHorizontalAlignment(SwingConstants.CENTER);
        buscador.setHorizontalAlignment(SwingConstants.CENTER);
        textoResultado.setHorizontalAlignment(SwingConstants.CENTER);
        
        //titulo de la interfaz
        setTitle("Bon Apetit v1.0");
        //agregando las categorias
        getContentPane().add(categorias, BorderLayout.NORTH);
        categorias.add(textoCategorias);
        categorias.add(picoteo);
        categorias.add(ensalada);
        categorias.add(platoprincipal);
        categorias.add(tragos);
        categorias.add(postres);
        //agregando las recetas
        getContentPane().add(recetas, BorderLayout.EAST);
        textoRecetas.setText("Recetas sin Filtro");
        recetas.add(textoRecetas);
        //agregando el filtro ingredientes
        getContentPane().add(buscadorIngredientes, BorderLayout.WEST);
        getContentPane().add(filtroIngredientes, BorderLayout.WEST);
        buscadorIngredientes.add(buscador);
        filtroIngredientes.add(contenedoringredientes);
        //agregando el resultado
        getContentPane().add(resultado, BorderLayout.CENTER);
        textoResultado.setText("Aqui van las recetas desplegadas");
        resultado.add(textoResultado);
        
        pack();
    }
    public static void main(String[] args) {
        JFrame v = new BonApetit();
        v.setVisible(true);
    }
    
}
