package controladores;

import datas.DataComida;
import entidades.EntidadComida;
import vistas.VistaComida;
import vistas.VistaPantallaPrincipal;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.net.URL;

/**
 * EN CONSTRUCCION
  * @author louis
 */
public class ControladorComida implements ActionListener{//, FocusListener, ListSelectionListener {
    //ActionListener actionPerformed, FocusListener metodos focusGained y focusLost., interfaz ListSelectionListener valueChanged
    public VistaPantallaPrincipal menu;
    public DataComida data;
    public VistaComida vista;
    private Image comidaimg;
    public ArrayList<EntidadComida> comidas = new ArrayList<>();
   MyTableModel modelo = new MyTableModel();

public ControladorComida(VistaPantallaPrincipal menu, DataComida data, VistaComida vista) {
        this.menu = menu;       
        this.data = data;
        this.vista = vista;

        vista.btBuscar.addActionListener(this);
        vista.btAgregar.addActionListener(this);
        vista.btLista.addActionListener(this);
        vista.jbModificar.addActionListener(this);
        vista.btHabilitarLista.addActionListener(this);
        vista.jbDeshabilitarLista.addActionListener(this);
  //      vista.tbComidas.getSelectionModel().addListSelectionListener(this);
  
  //*getSelectionModel(): Esto obtiene el modelo de selección del JTable, que administra
  //la selección de filas y columnas en la tabla.
  //*Utiliza el método addListSelectionListener del modelo de selección para agregar un objeto
 //como oyente de eventos de selección en la tabla. El objeto que se agrega es this, lo que significa
// que la propia instancia de la clase actual (que debe implementar ListSelectionListener) actuará como
//oyente de eventos de selección para la tabla tbPacientes.
//* implementar el método valueChanged de la interfaz ListSelectionListener para manejar los eventos 
//de selección según sea necesario, es decir, para realizar acciones cuando el usuario seleccione 
//o deseleccione filas en la tabla.
  
UIManager.put("OptionPane.messageFont", UIManager.getFont("Label.font").deriveFont(20.0f));

ImageIcon imageIcon = new ImageIcon("/nutricionista/imagenes/food2.jpg");
comidaimg = imageIcon.getImage();   
}

 public ControladorComida() {
        ControladorComida controlador = new ControladorComida();
        controlador.mostrarMensajePersonalizado();
    }

 public void inicia() {
        menu.dpFondo.add(vista); // Agrega la vista actual (vista) al contenedor jLabel1 de la clase menu
        vista.setVisible(true); //hace visible se mostrará en la pantalla.
        menu.dpFondo.moveToFront(vista);// Coloca la vista actual en la parte delantera del contenedor jLabel1 u otro componentes
        vista.requestFocus(); //le da el foco al formulario la vista estará lista para recibir eventos de entrada
         ModeloTablaComida();//configura y muestra una tabla en la vista de datos relacionados con las comidas
        cargarFondo();
    }

 @Override
    public void actionPerformed(ActionEvent e) {
}

public void ModeloTablaComida() {
        modelo.addColumn("idComida");
        modelo.addColumn("Nombre");
        modelo.addColumn("Receta");
        modelo.addColumn("Calorias/gramo");
        modelo.addColumn("Peso");
        vista.tbComidas.setModel(modelo); //Establecer el modelo de tabla creado en modeloTabla
    }

private void cargarFondo() {
        ClassLoader directorio = getClass().getClassLoader();
        URL rutaImagenFondo = directorio.getResource("imagenes/food2.jpg");
       //Se carga una imagen de fondo desde un recurso ubicado en un directorio
        ImageIcon imagenFondoIcon = new ImageIcon(rutaImagenFondo);
       // La ruta de la imagen se utiliza para crear un objeto ImageIcon que representa la imagen de fondo
        Image imagenFondo = imagenFondoIcon.getImage();
       //Se obtiene la imagen desde el objeto ImageIcon
        imagenFondo = imagenFondo.getScaledInstance(vista.jPanel1.getWidth(), vista.jPanel1.getHeight(), Image.SCALE_SMOOTH);
          // Redimensiona la imagen de fondo al tamaño del menu.jFondo JPanel
         ImageIcon imagenFondoRedimensionadaIcon = new ImageIcon(imagenFondo);
         // La imagen redimensionada se utiliza para crear un nuevo objeto ImageIcon
        vista.jLabel1.setIcon(imagenFondoRedimensionadaIcon);
        vista.jLabel1.setBounds(0, 0, vista.jPanel1.getWidth(), vista.jPanel1.getHeight());
        vista.jPanel1.revalidate();
        vista.jPanel1.repaint();
    }

public void mostrarMensajePersonalizado() {
        // Mostrar el mensaje utilizando JOptionPane.showMessageDialog con el tamaño de fuente configurado
        JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
    }

 public class MyTableModel extends DefaultTableModel {
        //para habilitar la modificacion de la columna 3( indice 2) en jTableCargaNotas
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2;
        }
    }
}