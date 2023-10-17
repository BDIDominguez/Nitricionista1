package controladores;

import datas.DataComida;
import entidades.EntidadComida;
import vistas.VistaComida;
import vistas.VistaPantallaPrincipal;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.net.URL;
import javax.swing.ButtonGroup;


/**
 *
 * @author louis
 */
public class ControladorComida implements ActionListener {

    private VistaPantallaPrincipal menu;
    public DataComida data;
    private VistaComida vista;
    private Image comidaimg;
    private ArrayList<EntidadComida> comidas = new ArrayList<>();
    private DefaultTableModel modeloTabla; // Esta es una variable miembro de la clase
    private ButtonGroup radioGroup;

    public ControladorComida(VistaPantallaPrincipal menu, DataComida data, VistaComida vista) {
        this.menu = menu;
        this.data = data;
        this.vista = vista;
         this.modeloTabla = new DefaultTableModel();
        //this.modeloTabla = (DefaultTableModel) vista.tbComidas.getModel();
        //obteniendo el modelo de datos de la tabla en vista.tbComidas.getModel() y asigno a la variable miembro this.modeloTabla. 
        //El modelo de datos se utiliza para manipular y mostrar los datos en la tabla. Una vez que se ha realizado esta asignación,
        //se utiliza this.modeloTabla para agregar, eliminar o actualizar filas de la tabla.
        this.radioGroup = new ButtonGroup();

            // Configura todos los campos de búsqueda y habilita inicialmente
//    vista.txNombre.setEditable(true);
//    vista.txIdComida.setEditable(true);
//    vista.txKcal.setEditable(true);
//    vista.txReceta.setEditable(true);
    
        vista.btBuscar.addActionListener(this);
        vista.btLimpiar.addActionListener(this);
        vista.btAgregar.addActionListener(this);
        vista.jbModificar.addActionListener(this);
        vista.jbSalir.addActionListener(this);
        vista.btHabilitarLista.addActionListener(this);
        vista.btDeshabilitarLista.addActionListener(this);
        vista.txIdComida.addActionListener(this);
        vista.txPeso.addActionListener(this);
        vista.txKcal.addActionListener(this);
        vista.txNombre.addActionListener(this);
        vista.txReceta.addActionListener(this);
        vista.rbDeshabilitada.addActionListener(this);
        vista.rbHabilitada.addActionListener(this);
        vista.tbComidas.setModel(modeloTabla);
        vista.tbComidas.getModel().addTableModelListener(new MiTableModelListener());
        
        UIManager.put("OptionPane.messageFont", UIManager.getFont("Label.font").deriveFont(20.0f));

        ImageIcon imageIcon = new ImageIcon("/nutricionista/imagenes/food2.jpg");
        comidaimg = imageIcon.getImage();
    }
    
    public ControladorComida() {
        ControladorComida controlador = new ControladorComida();
        controlador.mostrarMensajePersonalizado();
    }

    public void inicia() {
        ModeloTablaComida();//configura y muestra una tabla en la vista de datos relacionados con las comidas
        menu.dpFondo.add(vista); // Agrega la vista actual (vista) al contenedor jLabel1 de la clase menu
        vista.setVisible(true); //hace visible se mostrará en la pantalla.
        menu.dpFondo.moveToFront(vista);// Coloca la vista actual en la parte delantera del contenedor jLabel1 u otro componentes
        vista.requestFocus(); //le da el foco al formulario la vista estará lista para recibir eventos de entrada
        cargarFondo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         // Deshabilitar todos los campos al inicio
//         vista.txNombre.setEditable(false);
//         vista.txIdComida.setEditable(false);
//         vista.txKcal.setEditable(false);
//         vista.txReceta.setEditable(false);
         
       if (e.getSource() == vista.btBuscar) {
//        if (alMenosUnCampoTieneTexto()) {
//            vista.txNombre.setEditable(false);
//            vista.txIdComida.setEditable(false);
//            vista.txKcal.setEditable(false);
//            vista.txReceta.setEditable(false);
//        }
        buscarComidas();
    } else if (e.getSource() == vista.btLimpiar) {
        limpiarOpciones();
    } else if (e.getSource() == vista.btAgregar) {
            agregarComidas();
//        } else if (e.getSource() == vista.rbDeshabilitada) {
//            obtenerComidasxEstado();
//        } else if (e.getSource() == vista.rbHabilitada) {
//            obtenerComidasxEstado();
         } else if (e.getSource() == vista.btDeshabilitarLista) {
           deshabilitarComidaSeleccionada();
        } else if (e.getSource() == vista.btHabilitarLista) {
            habilitarComidaSeleccionada();
//       } else if (e.getSource() == vista.jbModificar) {}
//            modificarComidas();
} else if (e.getSource() == vista.jbSalir) {
        vista.dispose();
//    } else if (e.getSource() == vista.txNombre) {
//        vista.txNombre.setEditable(true);
//    } else if (e.getSource() == vista.txIdComida) {
//        vista.txIdComida.setEditable(true);
//    } else if (e.getSource() == vista.txKcal) {
//        vista.txKcal.setEditable(true);
//    } else if (e.getSource() == vista.txReceta) {
//        vista.txReceta.setEditable(true);
//    }
   }
}

    private void agregarComidas() {
    String nombre = vista.txNombre.getText();
    String receta = vista.txReceta.getText();
    int calorias = Integer.parseInt(vista.txKcal.getText());
    double peso = Double.parseDouble(vista.txPeso.getText());
    boolean estado = true; // Asumiendo que la comida es inicialmente habilitada
    // Crear una instancia de EntidadComida con los datos ingresados
    EntidadComida nuevaComida = new EntidadComida(nombre, receta, calorias, estado, peso);
    // Llamar al método de DataComida para agregar la comida
    EntidadComida comidaAgregada = data.agregarComidas(nuevaComida);
    // Verificar si la comida se agregó correctamente
    if (comidaAgregada != null) {
        // Actualizar la tabla mostrando la nueva comida
        DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
        Object[] fila = {
            comidaAgregada.getIdComida(),
            comidaAgregada.getNombreComida(),
            comidaAgregada.getReceta(),
            comidaAgregada.getCalorias(),
            comidaAgregada.getPeso()
        };
        modelo.addRow(fila);
        // Limpiar los campos de entrada
        vista.txNombre.setText("");
        vista.txReceta.setText("");
        vista.txKcal.setText("");
        vista.txPeso.setText("");
    } else {
        JOptionPane.showMessageDialog(null, "Error al agregar la comida");
    }
}
    
    public void deshabilitarComidaSeleccionada() {
    int filaSeleccionada = vista.tbComidas.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una comida en la tabla para deshabilitarla.");
        return;
    }
    // Obtener el idcomida en la fila seleccionada (columna 0)
    int idComida = (int) vista.tbComidas.getValueAt(filaSeleccionada, 0);
    // Llama al método de DataComida para deshabilitar la comida
    data.deshabilitarComida(idComida);
    // Actualiza la tabla para reflejar el cambio de estado
    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
    modelo.setValueAt("Deshabilitado", filaSeleccionada, 0);
}
    
      public void habilitarComidaSeleccionada() {
    int filaSeleccionada = vista.tbComidas.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una comida en la tabla para habilitarla");
        return;
    }
    // Obtener el idcomida en la fila seleccionada (columna 0)
    int idComida = (int) vista.tbComidas.getValueAt(filaSeleccionada, 0);
    // Llama al método de DataComida para habilitar la comida
    data.habilitarComida(idComida);
    // Actualiza la tabla para reflejar el cambio de estado
    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
    modelo.setValueAt("Habilitada", filaSeleccionada, 0);
}
      public class MiTableModelListener implements TableModelListener {
       @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    if (columna != 0) { // Asegurarte de no modificar la columna 0 (idComida)
                        DefaultTableModel model = (DefaultTableModel) vista.tbComidas.getModel();
                        int idComida = (int) model.getValueAt(fila, 0);
                        String nombre = (String) model.getValueAt(fila, 1);
                        String receta = (String) model.getValueAt(fila, 2);
                        int calorias = (int) model.getValueAt(fila, 3);
                        double peso = (double) model.getValueAt(fila, 4);
                        
                        // Crea un objeto EntidadComida con los datos modificados
                        EntidadComida comida = new EntidadComida(idComida, nombre, receta, calorias, true, peso);
                        
                        // Llama al método de DataComida para actualizar la comida
                        data.modificarComidas(comida);
                    }
                }
            }
      }
    
    public class MultilineCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Verificar si el valor es demasiado largo y mostrar un tooltip
            if (c instanceof JComponent) {
                JComponent jc = (JComponent) c;
                String text = value.toString();
                if (text.length() > 20) {
                    jc.setToolTipText(text);
                } else {
                    jc.setToolTipText(null);
                }
            }
            return c;
        }
    }
    
public void buscarComidas() {
    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de mostrar nuevos resultados

    List<EntidadComida> resultado = new ArrayList<>();
      // Primero, habilita todos los campos
    vista.txNombre.setEditable(true);
    vista.txIdComida.setEditable(true);
    vista.txKcal.setEditable(true);
    vista.txReceta.setEditable(true);
     vista.txPeso.setEditable(true);

    if (vista.rbHabilitada.isSelected()) {
        resultado.addAll(data.obtenerComidasxEstado(true));
    } else if (vista.rbDeshabilitada.isSelected()) {
        resultado.addAll(data.obtenerComidasxEstado(false));
} else if (!vista.txNombre.getText().isEmpty()) {
    String nombre = vista.txNombre.getText();
    if (!nombre.matches("^[a-zA-Z\\s]+$")) {
        JOptionPane.showMessageDialog(null, "El nombre de comida solo puede contener texto");
    } else {
        resultado.addAll(data.obtenerComidasxNombre(nombre));
        vista.txIdComida.setEditable(false);
        vista.txKcal.setEditable(false);
        vista.txReceta.setEditable(false);
        vista.txPeso.setEditable(false);
}
    } else if (!vista.txIdComida.getText().isEmpty()) {
        String input = vista.txIdComida.getText().trim();
        try {
            int idComida = Integer.parseInt(input);
            if (idComida >= 1 && idComida <= 11) {
            resultado.addAll(data.obtenerComidasxidComida(idComida));
            vista.txNombre.setEditable(false);
            vista.txKcal.setEditable(false);
            vista.txReceta.setEditable(false);
             vista.txPeso.setEditable(false);
       } else {
            JOptionPane.showMessageDialog(null, "El ID de comida debe estar en un rango del 1 al 11");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "No debe ingresar texto, solo se aceptan números del 1 al 11 como ID de comida");
    }
  } else if (!vista.txKcal.getText().isEmpty()) {
    try {
        int calorias = Integer.parseInt(vista.txKcal.getText());
        if (calorias <= 0) {
            JOptionPane.showMessageDialog(null, "Las calorías deben ser un número entero mayor que cero.");
        } else {
            resultado.addAll(data.obtenerComidasxCalorias(calorias));
            vista.txNombre.setEditable(false);
            vista.txIdComida.setEditable(false);
            vista.txReceta.setEditable(false);
            vista.txPeso.setEditable(false);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un número entero para las calorías");
    }
    } else if (!vista.txPeso.getText().isEmpty()) {
        try {
            double peso = Double.parseDouble(vista.txPeso.getText());
            resultado.addAll(data.obtenerComidasxpeso(peso));
            vista.txNombre.setEditable(false);
            vista.txIdComida.setEditable(false);
            vista.txReceta.setEditable(false);
            vista.txKcal.setEditable(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número en gramos para el peso");
            return;
        }
    } else if (!vista.txReceta.getText().isEmpty()) {
        String receta = vista.txReceta.getText();
          if (!receta.matches("^[a-zA-Z\\s]+$")) {
        JOptionPane.showMessageDialog(null, "El nombre de la receta solo puede contener texto");
    } else {
        resultado.addAll(data.obtenerComidasxReceta(receta));
         vista.txNombre.setEditable(false);
         vista.txIdComida.setEditable(false);
         vista.txKcal.setEditable(false);
          vista.txPeso.setEditable(false);
    }
}
    mostrarResultadoBusqueda(resultado); 
}

    private void mostrarResultadoBusqueda(List<EntidadComida> resultado) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de mostrar nuevos resultados

        for (EntidadComida comida : resultado) {
            Object[] fila ={
                comida.getIdComida(),
                comida.getNombreComida(),
                comida.getReceta(), 
                comida.getCalorias(),
                comida.getPeso()
            };
             modeloTabla.addRow(fila);
        }
    }
    
    public void limpiarOpciones() {
    vista.txNombre.setText("");
    vista.txIdComida.setText("");
    vista.txKcal.setText("");
    vista.txReceta.setText("");  
     vista.txPeso.setText("");
   // Reiniciar la selección de los botones de radio
    vista.rbHabilitada.setSelected(false);
    vista.rbDeshabilitada.setSelected(false);
    vista.txNombre.setEditable(true);
    vista.txIdComida.setEditable(true);
    vista.txKcal.setEditable(true);
    vista.txReceta.setEditable(true);
    vista.txPeso.setEditable(true);
    // Limpiar la tabla
    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
    modelo.setRowCount(0);
    }
     
    public void ModeloTablaComida() {
         modeloTabla = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // La columna cero (índice 0) no es editable
            return column != 0;
        }
    };
        modeloTabla.addColumn("idComida");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Receta");
        modeloTabla.addColumn("Kcal");
        modeloTabla.addColumn("Peso");
        vista.tbComidas.setModel(modeloTabla); //Establecer el modelo de tabla creado en modeloTabla
        vista.tbComidas.getColumnModel().getColumn(0).setPreferredWidth(120);
        vista.tbComidas.getColumnModel().getColumn(1).setPreferredWidth(490);
        vista.tbComidas.getColumnModel().getColumn(2).setCellRenderer(new MultilineCellRenderer());
        vista.tbComidas.getColumnModel().getColumn(2).setPreferredWidth(500); 
        vista.tbComidas.getColumnModel().getColumn(3).setPreferredWidth(70); 
        vista.tbComidas.getColumnModel().getColumn(4).setPreferredWidth(90);
         TableColumnModel columnModel = vista.tbComidas.getColumnModel();
         columnModel.getColumn(0).setResizable(false);
    columnModel.getColumn(1).setResizable(false);
    columnModel.getColumn(2).setResizable(false);
    columnModel.getColumn(3).setResizable(false);
    columnModel.getColumn(4).setResizable(false);
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
    
//    private boolean alMenosUnCampoTieneTexto() {
//    return !vista.txNombre.getText().isEmpty() || !vista.txIdComida.getText().isEmpty() ||
//           !vista.txKcal.getText().isEmpty() || !vista.txReceta.getText().isEmpty();
//}
}