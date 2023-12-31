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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ButtonGroup;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.TransferHandler;

/**
 * @author louisinette zaoral de entesano
 */
public class ControladorComida implements ActionListener {

    private VistaPantallaPrincipal menu;
    public DataComida data;
    private VistaComida vista;
    private ArrayList<EntidadComida> comidas = new ArrayList<>();
    private DefaultTableModel modeloTabla;
    private ButtonGroup radioGroup;
    // estructura para almacenar cambios pendientes de la tabla
    private List<EntidadComida> cambiosPendientes = new ArrayList<>();
    private Font customFont;

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
        vista.btNueva.addActionListener(this);
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
        // Se impide Copiar y pegar en los jTextField
        vista.txNombre.setTransferHandler(null);
        vista.txKcal.setTransferHandler(null);
        vista.txReceta.setTransferHandler(null);
        vista.txPeso.setTransferHandler(null);
        vista.txIdComida.setTransferHandler(null);
        vista.btAgregar.setEnabled(false);
        vista.jbModificar.setEnabled(false);
        vista.btDeshabilitarLista.setEnabled(false);
        vista.btHabilitarLista.setEnabled(false);
        UIManager.put("OptionPane.messageFont", UIManager.getFont("Label.font").deriveFont(20.0f));
        vista.tbComidas.setDefaultEditor(Object.class, new DefaultCellEditor(new miText()));
        customFont = new Font("Arial", Font.PLAIN, 16);
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
        vista.tbComidas.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                if (fila >= 0 && columna >= 0) {
                    DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
                    Object nuevoValor = modelo.getValueAt(fila, columna);
                    if (nuevoValor != null) {
// capturar los cambios y almacenarlos en la estructura de cambios pendientes
                        capturarCambios(fila, columna, nuevoValor);
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btBuscar) {
            buscarComidas();
        } else if (e.getSource() == vista.btLimpiar) {
            limpiarOpciones();
        } else if (e.getSource() == vista.btAgregar) {
            modeloTabla.setRowCount(0);
                vista.txIdComida.setEditable(true);
            agregarComidas();
        } else if (e.getSource() == vista.btDeshabilitarLista) {
            deshabilitarComidaSeleccionada();
        } else if (e.getSource() == vista.btHabilitarLista) {
            habilitarComidaSeleccionada();
        } else if (e.getSource() == vista.btNueva) {
            vista.btBuscar.setEnabled(false);
            vista.btHabilitarLista.setEnabled(false);
            vista.btDeshabilitarLista.setEnabled(false);
            vista.jbModificar.setEnabled(false);
            vista.btAgregar.setEnabled(true);
             vista.txNombre.setEditable(true);
             vista.txKcal.setEditable(true);
             vista.txIdComida.setEditable(false);
             vista.txPeso.setEditable(true);
             vista.txReceta.setEditable(true);
             vista.rbDeshabilitada.setEnabled(true);
             vista.rbHabilitada.setEnabled(true);
             vista.rbHabilitada.setSelected(false);
             vista.rbDeshabilitada.setSelected(false);
            vista.txIdComida.setText("");
            vista.txPeso.setText("");
            vista.txNombre.setText("");
            vista.txReceta.setText("");
            vista.txKcal.setText("");
        } else if (e.getSource() == vista.jbModificar) {
           // Verificar si hay cambios pendientes
    if (cambiosPendientes.isEmpty()) {
        // No hay cambios pendientes, mostrar un JOptionPane
        JOptionPane.showMessageDialog(null, "No se han realizado modificaciones");
    } else {
        // Realizar la lógica de modificación
        modificarComidas2();
    }
        } else if (e.getSource() == vista.jbSalir) {
            vista.dispose();
        }
    }

    static class miText extends JTextField {
        @Override
        public TransferHandler getTransferHandler() {
            return new TransferHandler(null) {
                @Override
                public boolean canImport(TransferSupport support) {
                    return false;
                }
                @Override
                public boolean importData(TransferSupport support) {
                    return false;
                }
            };
        }
    }

    private void agregarComidas() {
        String nombre = vista.txNombre.getText();
        String receta = vista.txReceta.getText();
        double peso = 0.0;
        int calorias = 0;
        boolean estado = false;

        if (nombre.isEmpty() || vista.txKcal.getText().isEmpty() || vista.txPeso.getText().isEmpty() || receta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes completar todos los datos del formulario para agregar una comida");
          
            return;
        }
        if (!vista.rbHabilitada.isSelected() && !vista.rbDeshabilitada.isSelected() || vista.rbHabilitada.isSelected() && vista.rbDeshabilitada.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar 1 solo estado (Habilitada o Deshabilitada) para agregar una comida");
            limpiarOpciones();
            return;
        }
        if (!nombre.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "El nombre de comida solo puede contener texto");
        
            return;
        }
        if (!receta.matches("^[a-zA-Z,\\s]+$")) {
            JOptionPane.showMessageDialog(null, "La receta solo puede contener texto");
     
            return;
        }
        try {
            calorias = Integer.parseInt(vista.txKcal.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Las calorías deben ser un número entero");
          
            return;
        }
       try {
    peso = Double.parseDouble(vista.txPeso.getText());
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, "El peso debe ser un número en gramos");
    return;
}
        if (vista.rbHabilitada.isSelected()) {
            estado = true; // Habilitada
        } else if (vista.rbDeshabilitada.isSelected()) {
            estado = false; // Deshabilitada
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un estado (Habilitada o Deshabilitada");
            return;
        }
        EntidadComida nuevaComida = new EntidadComida(nombre, receta, calorias, estado, peso);
        EntidadComida comidaAgregada = data.agregarComidas(nuevaComida);

        if (comidaAgregada != null) {
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
            vista.txIdComida.setEnabled(true);
            vista.btBuscar.setEnabled(true); // Deshabilitar el botón de búsqueda
            vista.btHabilitarLista.setEnabled(true);
            vista.btDeshabilitarLista.setEnabled(true);
            vista.jbModificar.setEnabled(true);
            vista.btAgregar.setEnabled(false);
            vista.rbHabilitada.setSelected(false);
            vista.rbDeshabilitada.setSelected(false);
            vista.txIdComida.setText("");
            vista.txPeso.setText("");
            vista.txNombre.setText("");
            vista.txReceta.setText("");
            vista.txKcal.setText("");
            
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar la comida");
        }
    }

    public void deshabilitarComidaSeleccionada() {
        int filaSeleccionada = vista.tbComidas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para deshabilitarla");
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
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para habilitarla");
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

// Método para capturar los cambios en la tabla cuando el usuario modifica una celda
    public void capturarCambios(int fila, int columna, Object nuevoValor) {
        Object valorOriginal = null; // Declarar la variable fuera del bloque try-catch
      try {  // Antes de permitir la edición de la celda, guarda el valor original en una variable temporal
      valorOriginal = vista.tbComidas.getValueAt(fila, columna);

        // Obtener el ID de la comida que se está modificando desde la primera columna
Object idComidaObj = vista.tbComidas.getValueAt(fila, 0);
int idComida = 0; // Valor predeterminado en caso de error de conversión

if (idComidaObj != null) {
    if (idComidaObj instanceof Integer) {
        // El valor es un entero, se puede obtenerlo directamente
        idComida = (int) idComidaObj;
    } else if (idComidaObj instanceof String) {
        try {
            // Intentar convertir el valor a entero
            idComida = Integer.parseInt((String) idComidaObj);
        } catch (NumberFormatException ex) {
        }
    } else {
        // Manejo de error si el tipo de valor no es compatible
        System.out.println("Error: Valor de tipo no compatible para el ID de comida");
    }
}
        // Buscar si ya existe un registro de cambios pendientes para esta comida
        EntidadComida comidaActual = null;
        for (EntidadComida comida : cambiosPendientes) {
            if (comida.getIdComida() == idComida) {
                comidaActual = comida;
                break;
            }
        }
        // Si no se encuentra en los cambios pendientes, crear un nuevo objeto EntidadComida
        if (comidaActual == null) {
            comidaActual = new EntidadComida();
            comidaActual.setIdComida(idComida);
            cambiosPendientes.add(comidaActual);
        }
        // Actualizar el objeto EntidadComida con el nuevo valor según la columna modificada
        switch (columna) {
             case 1: // Nombre
    String nombre = (String) nuevoValor;
    if (nombre!= null) {
       if (nombre.matches("^[a-zA-Z\\s]+$")) {
            if (!nombre.equals(comidaActual.getNombreComida())) {
                comidaActual.setNombreComida(nombre);                
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre de la comida solo puede contener texto");
            vista.tbComidas.setValueAt(comidaActual.getNombreComida(), fila, columna);
        }
    }
    break;
           case 2: // Receta
    String receta = (String) nuevoValor;
    if (receta != null) {
       if (receta.matches("^[a-zA-Z,\\s]+$")) {
            if (!receta.equals(comidaActual.getReceta())) {
                comidaActual.setReceta(receta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La receta de la comida solo puede contener texto");
            vista.tbComidas.setValueAt(comidaActual.getReceta(), fila, columna);
        }
    }
    break;

            case 3: // Calorías
                try {
                    int calorias = Integer.parseInt(nuevoValor.toString());
                    if (calorias != comidaActual.getCalorias()) {
                        comidaActual.setCalorias(calorias);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Las calorías deben ser un número entero");
                    vista.tbComidas.setValueAt(comidaActual.getCalorias(), fila, columna);
                }
                break;
            case 4: // Peso
                try {
                    double peso = Double.parseDouble(nuevoValor.toString());
                    if (peso != comidaActual.getPeso()) {
                        comidaActual.setPeso(peso);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El peso debe ser un número");
                    vista.tbComidas.setValueAt(comidaActual.getPeso(), fila, columna);
                }
                break;
            default:
                break;
        }
        //  actualizar la lista de cambios pendientes
        cambiosPendientes.set(cambiosPendientes.indexOf(comidaActual), comidaActual);
         } catch (NumberFormatException ex) {
        // Restaurar el valor original en la tabla
        vista.tbComidas.setValueAt(valorOriginal, fila, columna);
    }
}

    public void modificarComidas2() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();

        for (EntidadComida comidaModificada : cambiosPendientes) {
            int idComida = comidaModificada.getIdComida();
            EntidadComida comidaActual = obtenerComidasxidComida(idComida);

            if (comidaActual != null) {
                // Combinar los valores modificados con los no modificados
                // Aquí se asume que comidaActual representa el estado actual de la comida
                if (comidaModificada.getNombreComida() == null) {
                    comidaModificada.setNombreComida(comidaActual.getNombreComida());
                }
                if (comidaModificada.getReceta() == null) {
                    comidaModificada.setReceta(comidaActual.getReceta());
                }
                if (comidaModificada.getCalorias() == 0) {
                    comidaModificada.setCalorias(comidaActual.getCalorias());
                }
                if (comidaModificada.getPeso() == 0.0) {
                    comidaModificada.setPeso(comidaActual.getPeso());
                }
                // Actualizar la base de datos con los cambios realizados
                data.modificarComidas2(comidaModificada);

                // Actualizar la tabla con los nuevos valores
                for (int fila = 0; fila < modelo.getRowCount(); fila++) {
                    if ((int) modelo.getValueAt(fila, 0) == idComida) {
                        modelo.setValueAt(comidaModificada.getIdComida(), fila, 0);
                        modelo.setValueAt(comidaModificada.getNombreComida(), fila, 1);
                        modelo.setValueAt(comidaModificada.getReceta(), fila, 2);
                        modelo.setValueAt(comidaModificada.getCalorias(), fila, 3);
                        modelo.setValueAt(comidaModificada.getPeso(), fila, 4);
                       JOptionPane.showMessageDialog(null, "Se ha modificado la comida");
                        break;
                    }
                }
            }
        }
        // Limpiar la lista de cambios pendientes después de aplicarlos
        cambiosPendientes.clear();
    }

    private EntidadComida obtenerComidasxidComida(int idComida) {
        List<EntidadComida> comidasxid = data.obtenerComidasxidComida(idComida);
        if (!comidasxid.isEmpty()) {
            // Suponemos que solo debería haber una EntidadComida para el ID único
            return comidasxid.get(0);
        }
        return null;
    }

        public class MultilineCellRenderer extends DefaultTableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Verificar si el valor es demasiado largo y mostrar un tooltip
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    String text = (value != null) ? value.toString() : "";
                    if (text.length() > 20) {
                        jc.setToolTipText(text);
                    } else {
                       jc.setToolTipText(null);
                }
            }
            // Aplicar la fuente personalizada
            c.setFont(customFont);
            return c;
        }
    }

    public void buscarComidas() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de mostrar nuevos resultados

        List<EntidadComida> resultado = new ArrayList<>();

        if (vista.txNombre.getText().isEmpty()
                && vista.txIdComida.getText().isEmpty()
                && vista.txKcal.getText().isEmpty()
                && vista.txPeso.getText().isEmpty()
                && vista.txReceta.getText().isEmpty()
                && (!vista.rbHabilitada.isSelected() && !vista.rbDeshabilitada.isSelected())) {
            JOptionPane.showMessageDialog(null, "Debes ingresar al menos una opción de búsqueda o seleccionar un estado");
            return;
        } else if (!vista.txNombre.getText().isEmpty()
                || !vista.txIdComida.getText().isEmpty()
                || !vista.txKcal.getText().isEmpty()
                || !vista.txPeso.getText().isEmpty()
                || !vista.txReceta.getText().isEmpty()
                || vista.rbHabilitada.isSelected()
                || vista.rbDeshabilitada.isSelected()) {
            int opcionesSeleccionadas = 0;
            if (!vista.txNombre.getText().isEmpty()) {
                opcionesSeleccionadas++;
            }
            if (!vista.txIdComida.getText().isEmpty()) {
                opcionesSeleccionadas++;
            }
            if (!vista.txKcal.getText().isEmpty()) {
                opcionesSeleccionadas++;
            }
            if (!vista.txPeso.getText().isEmpty()) {
                opcionesSeleccionadas++;
            }
            if (!vista.txReceta.getText().isEmpty()) {
                opcionesSeleccionadas++;
            }
            if (vista.rbHabilitada.isSelected()) {
                opcionesSeleccionadas++;
            }
            if (vista.rbDeshabilitada.isSelected()) {
                opcionesSeleccionadas++;
            }
            if (opcionesSeleccionadas != 1) {
                JOptionPane.showMessageDialog(null, "Debes ingresar solo 1 opción de búsqueda o seleccionar un estado");
                return;
            }
        }
          if (vista.rbHabilitada.isSelected() && vista.rbDeshabilitada.isSelected()) {
          JOptionPane.showMessageDialog(null, "Debes ingresar seleccionar solo 1 estado a la vez, Habilitada o Deshabilitada");
        } else if (vista.rbHabilitada.isSelected()) {
            resultado.addAll(data.obtenerComidasxEstado(true));
        } else if (vista.rbDeshabilitada.isSelected()) {
            resultado.addAll(data.obtenerComidasxEstado(false));
        } else if (!vista.txNombre.getText().isEmpty()) {
            String nombre = vista.txNombre.getText();
            if (!nombre.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "El nombre de comida solo puede contener texto");
               vista.txNombre.setText("");
          } else {
        List<EntidadComida> comidasEncontradas = data.obtenerComidasxNombre(nombre);
        if (comidasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron comidas con el nombre especificado, puede seleccionar un estado y presionar buscar para listar las comidas");
       vista.txNombre.setText("");
        } else {
            resultado.addAll(comidasEncontradas);
        }
    }
        } else if (!vista.txIdComida.getText().isEmpty()) {
            String input = vista.txIdComida.getText().trim();
            try {
                int idComida = Integer.parseInt(input);
                resultado.addAll(data.obtenerComidasxidComida(idComida));
                // Verificar si no se encontraron resultados
                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron comidas con el ID de comida especificado, puede seleccionar un estado y presionar buscar para listar las comidas");
                   vista.txIdComida.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Se aceptan números únicamente");
                vista.txIdComida.setText("");
            }
        } else if (!vista.txKcal.getText().isEmpty()) {
            try {
                int calorias = Integer.parseInt(vista.txKcal.getText());
                if (calorias <= 0) {
                    JOptionPane.showMessageDialog(null, "Las calorías deben ser un número entero mayor que cero");
                   vista.txKcal.setText("");
                 } else  {
        List<EntidadComida> comidasEncontradas = data.obtenerComidasxCalorias(calorias);
        if (comidasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron comidas con la cantidad de calorias especificadas, puede seleccionar un estado y presionar buscar para listar las comidas");
      vista.txKcal.setText(""); 
        } else {
            resultado.addAll(comidasEncontradas);
        }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero para las calorías");
                  vista.txKcal.setText(""); 
            }
//           
        } else if (!vista.txPeso.getText().isEmpty()) {
            try {
                double peso = Double.parseDouble(vista.txPeso.getText());
                if (peso <= 0) {
                    JOptionPane.showMessageDialog(null, "El peso debe ser un número mayor que cero");
                   vista.txPeso.setText("");  
                } else {
         List<EntidadComida> comidasEncontradas = data.obtenerComidasxpeso(peso);
        if (comidasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron comidas con el peso indicado, puede seleccionar un estado y presionar buscar para listar las comidas");
      vista.txPeso.setText(""); 
        } else {
            resultado.addAll(comidasEncontradas);
        }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero en gramos para el peso");
                 vista.txPeso.setText("");  
            }
        } else if (!vista.txReceta.getText().isEmpty()) {
    String receta = vista.txReceta.getText();
    if (!receta.matches("^[a-zA-Z,\\s]+$")) {
        JOptionPane.showMessageDialog(null, "El nombre de la receta solo puede contener texto");
        vista.txReceta.setText("");
    } else {
        List<EntidadComida> comidasEncontradas = data.obtenerComidasxReceta(receta);
        if (comidasEncontradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron comidas con el nombre especificado, puede seleccionar un estado y presionar buscar para listar las comidas");
            vista.txReceta.setText("");
        } else {
            resultado.addAll(comidasEncontradas);
        }
    }
}
        mostrarResultadoBusqueda(resultado);
        vista.jbModificar.setEnabled(true);
        vista.btDeshabilitarLista.setEnabled(true);
        vista.btHabilitarLista.setEnabled(true);
    }

    private void mostrarResultadoBusqueda(List<EntidadComida> resultado) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tbComidas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de mostrar nuevos resultados
        for (EntidadComida comida : resultado) {
            Object[] fila = {
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
        vista.btAgregar.setEnabled(false);
        vista.btBuscar.setEnabled(true);
        vista.btNueva.setEnabled(true);
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
        vista.tbComidas.getColumnModel().getColumn(1).setPreferredWidth(520);
        vista.tbComidas.getColumnModel().getColumn(2).setCellRenderer(new MultilineCellRenderer());
        vista.tbComidas.getColumnModel().getColumn(2).setPreferredWidth(500);
        vista.tbComidas.getColumnModel().getColumn(3).setPreferredWidth(95);
        vista.tbComidas.getColumnModel().getColumn(4).setPreferredWidth(120);
        TableColumnModel columnModel = vista.tbComidas.getColumnModel();
        columnModel.getColumn(0).setResizable(false);
        columnModel.getColumn(1).setResizable(false);
        columnModel.getColumn(2).setResizable(false);
        columnModel.getColumn(3).setResizable(false);
        columnModel.getColumn(4).setResizable(false);
    }

    public void mostrarMensajePersonalizado() {
        // Mostrar el mensaje utilizando JOptionPane.showMessageDialog con el tamaño de fuente configurado
        JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
    }
}