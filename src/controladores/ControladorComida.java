package controladores;

import datas.DataComida;
import entidades.EntidadComida;
import vistas.VistaComida;
import vistas.VistaPantallaPrincipal;
import java.awt.event.ActionListener;
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
 *EN CONSTRUCCION LA VALIDACION
 * @author louis
 */
public class ControladorComida implements ActionListener {

    private VistaPantallaPrincipal menu;
    public DataComida data;
    private VistaComida vista;
    private Image comidaimg;
    private ArrayList<EntidadComida> comidas = new ArrayList<>();
    private DefaultTableModel modeloTabla;
    private ButtonGroup radioGroup;

    public ControladorComida(VistaPantallaPrincipal menu, DataComida data, VistaComida vista) {
        this.menu = menu;
        this.data = data;
        this.vista = vista;
         this.modeloTabla = new DefaultTableModel();
        this.radioGroup = new ButtonGroup();
    
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
       if (e.getSource() == vista.btBuscar) {
        buscarComidas();
         vista.txNombre.setEditable(false);
         vista.txIdComida.setEditable(false);
         vista.txKcal.setEditable(false);
         vista.txPeso.setEditable(false);
         vista.txReceta.setEditable(false);
         vista.btAgregar.setEnabled(false);
         vista.rbHabilitada.setEnabled(false);
         vista.rbDeshabilitada.setEnabled(false);
    } else if (e.getSource() == vista.btLimpiar) {
        limpiarOpciones();
    } else if (e.getSource() == vista.btAgregar) {
          modeloTabla.setRowCount(0);
          agregarComidas();
         } else if (e.getSource() == vista.btDeshabilitarLista) {
           deshabilitarComidaSeleccionada();
        } else if (e.getSource() == vista.btHabilitarLista) {
            habilitarComidaSeleccionada();
       } else if (e.getSource() == vista.jbModificar) {
            modificarComidas();
} else if (e.getSource() == vista.jbSalir) {
        vista.dispose();
   }
}

    private void agregarComidas() {
    try {
    String nombre = vista.txNombre.getText();
    String receta = vista.txReceta.getText();
    int calorias = Integer.parseInt(vista.txKcal.getText());
    double peso = Double.parseDouble(vista.txPeso.getText());
   boolean estado; // Declarar el estado sin inicializarlo
    // Verificar la selección de radio buttons para establecer el estado
    if (vista.rbHabilitada.isSelected()) {
        estado = true; // Habilitada
    } else if (vista.rbDeshabilitada.isSelected()) {
        estado = false; // Deshabilitada
    } else {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un estado (Habilitada o Deshabilitada).");
        return;
    }
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
        JOptionPane.showMessageDialog(null, "Comida agregada exitosamente");
        // Limpiar los campos de entrada
        vista.txNombre.setText("");
        vista.txReceta.setText("");
        vista.txKcal.setText("");
        vista.txPeso.setText("");
         vista.txIdComida.setText("");
  } else {
            JOptionPane.showMessageDialog(null, "Error al agregar la comida");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Debes completar todos los datos del formulario para agregar una comida");
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
     limpiarOpciones();
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
    limpiarOpciones();
}
    
    public void modificarComidas() {
    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
    int filaSeleccionada = vista.tbComidas.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Selecciona una fila en la tabla para modificar.");
        return;
    }
    // Obtener los valores de las celdas de la fila seleccionada
    int idComida = (int) modelo.getValueAt(filaSeleccionada, 0);
    String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
    String receta = (String) modelo.getValueAt(filaSeleccionada, 2);
    int calorias = (int) modelo.getValueAt(filaSeleccionada, 3);
    double peso = (double) modelo.getValueAt(filaSeleccionada, 4);
    boolean estado = true; // Asumiendo que la comida es inicialmente habilitada
    EntidadComida comidaModificada = new EntidadComida(idComida, nombre, receta, calorias, estado, peso);
    data.modificarComidas(comidaModificada);
    //  actualizar la tabla
    modelo.setValueAt(nombre, filaSeleccionada, 1);
    modelo.setValueAt(receta, filaSeleccionada, 2);
    modelo.setValueAt(calorias, filaSeleccionada, 3);
    modelo.setValueAt(peso, filaSeleccionada, 4);
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
    
    if (vista.txNombre.getText().isEmpty() &&
    vista.txIdComida.getText().isEmpty() &&
    vista.txKcal.getText().isEmpty() &&
    vista.txPeso.getText().isEmpty() &&
    vista.txReceta.getText().isEmpty() &&
    (!vista.rbHabilitada.isSelected() && !vista.rbDeshabilitada.isSelected())) {
    JOptionPane.showMessageDialog(null, "Debes ingresar al menos una opción de búsqueda o seleccionar un estado");
    return;
} else if (!vista.txNombre.getText().isEmpty() ||
           !vista.txIdComida.getText().isEmpty() ||
           !vista.txKcal.getText().isEmpty() ||
           !vista.txPeso.getText().isEmpty() ||
           !vista.txReceta.getText().isEmpty() ||
           vista.rbHabilitada.isSelected() ||
           vista.rbDeshabilitada.isSelected()) {
    int opcionesSeleccionadas = 0;
    if (!vista.txNombre.getText().isEmpty()) opcionesSeleccionadas++;
    if (!vista.txIdComida.getText().isEmpty()) opcionesSeleccionadas++;
    if (!vista.txKcal.getText().isEmpty()) opcionesSeleccionadas++;
    if (!vista.txPeso.getText().isEmpty()) opcionesSeleccionadas++;
    if (!vista.txReceta.getText().isEmpty()) opcionesSeleccionadas++;
    if (vista.rbHabilitada.isSelected()) opcionesSeleccionadas++;
    if (vista.rbDeshabilitada.isSelected()) opcionesSeleccionadas++;
    if (opcionesSeleccionadas !=1) {
        JOptionPane.showMessageDialog(null, "Debes ingresar solo 1 opción de búsqueda o seleccionar un estado");
        return;
    } 
}

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
}
    } else if (!vista.txIdComida.getText().isEmpty()) {
        String input = vista.txIdComida.getText().trim();
        try {
            int idComida = Integer.parseInt(input);
            resultado.addAll(data.obtenerComidasxidComida(idComida));
        // Verificar si no se encontraron resultados
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron comidas con el ID de comida especificado");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Se aceptan números únicamente");
    }
  } else if (!vista.txKcal.getText().isEmpty()) {
    try {
        int calorias = Integer.parseInt(vista.txKcal.getText());
        if (calorias <= 0) {
            JOptionPane.showMessageDialog(null, "Las calorías deben ser un número entero mayor que cero.");
        } else {
            resultado.addAll(data.obtenerComidasxCalorias(calorias));
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un número entero para las calorías");
    }
    } else if (!vista.txPeso.getText().isEmpty()) {
        try {
            double peso = Double.parseDouble(vista.txPeso.getText());
            resultado.addAll(data.obtenerComidasxpeso(peso));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número de gramos para el peso");
            return;
        }
    } else if (!vista.txReceta.getText().isEmpty()) {
        String receta = vista.txReceta.getText();
          if (!receta.matches("^[a-zA-Z\\s]+$")) {
        JOptionPane.showMessageDialog(null, "El nombre de la receta solo puede contener texto");
    } else {
        resultado.addAll(data.obtenerComidasxReceta(receta));
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
    vista.btAgregar.setEnabled(true);
    vista.btBuscar.setEnabled(true);
     vista.btHabilitarLista.setEnabled(true);
    vista.btDeshabilitarLista.setEnabled(true);
    vista.jbModificar.setEnabled(true);
     vista.rbHabilitada.setEnabled(true);
     vista.rbDeshabilitada.setEnabled(true);
   // Reiniciar la selección de los botones de radio
   vista.rbHabilitada.setSelected(false);
    vista.rbDeshabilitada.setSelected(false);
      // Editar los textfields
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
            // La columna cero (índice 0) no es editable es el idComida
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
}