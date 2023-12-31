package controladores;

/**
 * * @author DIEGO G
 */
// Importación de paquetes y clases necesarios.
import datas.DataDieta_Comida;
import datas.DataComida;
import datas.DataDieta;
import datas.DataPaciente;
// Importación de clases y paquetes necesarios para el controlador.
import entidades.EntidadDieta_Comida;
import entidades.EntidadPaciente;
import entidades.EntidadComida;
import entidades.EntidadDieta_Comida.HorarioComida;
// Importación de clases para la gestión de eventos de GUI.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Importación de las clases de la interfaz gráfica.
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;
import vistas.VistaDieta_Comida;
// Importación de la clase SQLException para manejo de excepciones SQL.
import java.sql.SQLException;
// Importación de clases para estructuras de datos.
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// Importación de clases para la creación de interfaces gráficas y manejo de tablas.
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ControladorDieta_Comida implements ActionListener, FocusListener, ListSelectionListener {
// Declaración de variables miembro para el controlador.

    private final VistaPantallaPrincipal menu;
    private final VistaDieta_Comida vista;
    private final DataDieta_Comida dataDietaComida;
    private int paciente = 0;
    private int dietaSeleccionada = -1;
    MyModelo modelo = new MyModelo();
    private List<EntidadPaciente> pacientes = new ArrayList<>();
    private Set<String> comidasAgregadas = new HashSet<>();
    // Constructor del controlador.

    public ControladorDieta_Comida(VistaPantallaPrincipal menu, VistaDieta_Comida vista, DataDieta_Comida dataDietaComida) {
        // Inicialización del controlador con los parámetros de la vista y los datos.
        this.vista = vista;
        this.menu = menu;
        this.dataDietaComida = dataDietaComida;
        //Escucha de botones 
        vista.BtNuevaDieta.addActionListener(this);
        vista.BtEliminarDieta.addActionListener(this);
        vista.BtAgregarComida.addActionListener(this);
        vista.BtGuardar.addActionListener(this);
        vista.BtSalir.addActionListener(this);
        vista.BtQuitarComida.addActionListener(this);
        vista.BtCancel.addActionListener(this);
        //Combo. Asignación de controladores de eventos a los combobox de la vista
        vista.CBPaciente.addActionListener(this);
        vista.CBDietasActivas.addActionListener(this);
        vista.CBComidasActivas.addActionListener(this);
        vista.CbHorario.addActionListener(this);
        //Cuadro de texto. Asignación de controladores de eventos al cuadro de texto de la vista.
        vista.TxPorcion.addFocusListener(this);
        //Tabla. Asignación de controladores de eventos a la tabla de la vista.
        vista.JTComidas.getSelectionModel().addListSelectionListener(this);
    }

    public void iniciar() { // Método para iniciar la vista y configurar su estado inicial.
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        llenarComboBPaciente();
        llenarComboBDietasActivas();
        llenarComboComidasActivas();
        vista.BtGuardar.setEnabled(false);
        vista.BtCancel.setEnabled(false);
    }

    private void modelarTabla() { // Método para configurar el modelo de la tabla y su apariencia.
        modelo.addColumn("id");
        modelo.addColumn("Comida");
        modelo.addColumn("Porción");
        modelo.addColumn("Horarios");
        vista.JTComidas.setModel(modelo);
        vista.JTComidas.setAutoResizeMode(vista.JTComidas.AUTO_RESIZE_OFF);
        vista.JTComidas.getColumnModel().getColumn(0).setPreferredWidth(20);
        vista.JTComidas.getColumnModel().getColumn(1).setPreferredWidth(375);
        vista.JTComidas.getColumnModel().getColumn(2).setPreferredWidth(50);
        vista.JTComidas.getColumnModel().getColumn(3).setPreferredWidth(80);
    }

    private class MyModelo extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void llenarJTComidas() { // Método para llenar la tabla de comidas de la dieta seleccionada.
        try {
            List<EntidadDieta_Comida> comidas = new ArrayList<>();

            comidas = dataDietaComida.obtenerDietasComidaPorDieta(extraerIdDieta());
            modelo.setRowCount(0);
            comidasAgregadas.clear();
            DataComida a = new DataComida();

            for (EntidadDieta_Comida comida : comidas) {

                comidasAgregadas.add(comida.getIdComida() + "-" + a.obtenerNombrexidComida(comida.getIdComida())); //rellena el set para que no vuelva a agregar una comida repetida
                modelo.addRow(new Object[]{comida.getIdDieta_Comida(), comida.getIdComida() + "-" + a.obtenerNombrexidComida(comida.getIdComida()), comida.getPorcion(), comida.getHorario()});
            }
            vista.JTComidas.setModel(modelo);
            pacientes.clear();
            pacientes.addAll(pacientes);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al llenar JTComidas al tratar de obtener una lista de comidas \n" + ex.getMessage());
        }
        vista.BtGuardar.setEnabled(false);
//        vista.BtAgregarComida.setEnabled(true);
    }

    private void llenarComboBPaciente() { // Método para llenar el combo de pacientes con información desde la base de datos.
        List<EntidadPaciente> pac = new ArrayList<>();
        try {
            DataPaciente e = new DataPaciente();
            pac = e.listarPacientes();
            vista.CBPaciente.removeAllItems();

            for (EntidadPaciente paciente : pac) {
                String cadena = paciente.getIdpaciente() + "-" + paciente.getDni() + "-" + paciente.getNombre();
                vista.CBPaciente.addItem(cadena);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de pacientes para llenar Combo pacientes \n" + ex.getMessage());
        }
    }

    private void llenarComboBDietasActivas() {
        vista.CBDietasActivas.removeAllItems();
        List<entidades.EntidadDieta> diet = new ArrayList<>();
        DataDieta dataDieta = new DataDieta();
        try {

            List<entidades.EntidadDieta> dietas = dataDieta.listarDietas();
            boolean tieneDietasActivas = false;
            for (entidades.EntidadDieta dieta : dietas) {

                if (dieta.isEstado() && dieta.getPaciente() == paciente) { // Verifica si la dieta está activa
                    tieneDietasActivas = true;
                    vista.CBDietasActivas.addItem(dieta.getIdDieta() + "-" + dieta.getNombre());
                }
            }
            if (!tieneDietasActivas) {
                JOptionPane.showMessageDialog(vista, "El paciente no tiene dietas activas. Puede crear una nueva desde el botón 'Nueva Dieta' o seleccionar otro paciente.", "Sin Dietas Activas", JOptionPane.INFORMATION_MESSAGE);
                vista.BtGuardar.setEnabled(false);
                vista.BtNuevaDieta.setEnabled(true);
                vista.BtEliminarDieta.setEnabled(false);
                vista.BtQuitarComida.setEnabled(false);
                vista.BtCancel.setEnabled(false);
                vista.CbHorario.setEnabled(false);
                vista.TxPorcion.setEnabled(false);
                vista.CBComidasActivas.setEnabled(false);
                vista.BtAgregarComida.setEnabled(false);
            } else {
                reset();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de dietas activas: " + ex.getMessage());
        }
        llenarJTComidas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.CBPaciente) { //muestra para seleccionar un paciente activo
            paciente = extraerIdPaciente();
            llenarComboBDietasActivas();
            vista.BtGuardar.setEnabled(false);
        }

        if (e.getSource() == vista.CBDietasActivas) {// muestra las dietas disponibles activas para elegir
            dietaSeleccionada = extraerIdDieta();
            llenarJTComidas();
            vista.BtGuardar.setEnabled(false);
        }

        if (e.getSource() == vista.BtNuevaDieta) { //llama a la vista Dieta para cargar nueva dieta y actualiza el JTable JTComidas
            vista.dispose();
            VistaDieta vista = new VistaDieta();
            DataDieta data = new DataDieta();
            ControladorDieta ctrl = new ControladorDieta(menu, vista, data);
            ctrl.iniciar();
            JOptionPane.showMessageDialog(vista, "Por favor cargue aquí su nueva dieta, luego cierre la ventana Dietas y continúe usando el plan Nutricional");
        }

        if (e.getSource() == vista.BtEliminarDieta) {// elimina la dieta seleccionada del CBComidasActivas del paciente actual
            DataDieta dataDieta = new DataDieta();

            if (JOptionPane.showConfirmDialog(vista, "Seguro de eliminar la dieta " + vista.CBDietasActivas.getSelectedItem(), "Confirme", JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    String dietaSeleccionada = (String) vista.CBDietasActivas.getSelectedItem();
                    String[] partes = dietaSeleccionada.split("-");
                    int idDieta = Integer.parseInt(partes[0].trim());
                    int idDieta2 = extraerIdDieta();

                    boolean eliminacionOK = dataDieta.eliminarDieta(idDieta);
                    boolean vResp = dataDieta.eliminarDieta(this.dietaSeleccionada);
                    dataDieta.eliminarDieta(this.dietaSeleccionada);

                    if (eliminacionOK) {
                        JOptionPane.showMessageDialog(vista, "Dieta eliminada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta.");
                    }
                    llenarComboBDietasActivas();
                    modelo.setRowCount(0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta \n" + ex.getMessage());
                }
            }
        }
        if (e.getSource() == vista.CBComidasActivas) { // combobox muestra las comidas activas
            String comidaSelect = (String) vista.CBComidasActivas.getSelectedItem();
        }

        vista.TxPorcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();  // Consume el evento si no es un dígito
                }
            }
        });

        if (e.getSource() == vista.BtAgregarComida) { //agrega la comida seleccionada del combo box CBComidasActivas a la dieta del paciente
            vista.CBPaciente.setEnabled(false);
            vista.CBDietasActivas.setEnabled(false);
            vista.BtGuardar.setEnabled(false);
            vista.BtNuevaDieta.setEnabled(false);
            vista.BtEliminarDieta.setEnabled(false);
            vista.BtQuitarComida.setEnabled(false);
            vista.BtCancel.setEnabled(true);

            String porcionText = vista.TxPorcion.getText();

            if (vista.TxPorcion.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo porción no puede estar en blanco. Por favor indique una cantidad. (recuerde seleccionar la comida y el horario también antes de agregar)", "Error: ", JOptionPane.ERROR_MESSAGE);
            } else {
                int porcion = Integer.parseInt(porcionText);

                if (porcion <= 0) {
                    JOptionPane.showMessageDialog(null, "El campo porción debe ser un número mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                vista.BtGuardar.setEnabled(true);
                int id = 0;
                String comida = vista.CBComidasActivas.getSelectedItem().toString(); 	// Obtiene comida seleccionada del Combo
                String horario = vista.CbHorario.getSelectedItem().toString();		// Obtiene horario seleccionado del Combo

                if (!comidasAgregadas.contains(comida)) {                               // Si la comida no estaba en la lista...
                    modelo.addRow(new Object[]{id, comida, porcion, horario});          // Agrega la nueva comida a nueva fila
                    comidasAgregadas.add(comida);
                } else {
                    JOptionPane.showMessageDialog(null, "La comida ya se ha sido agregada anteriormente a la lista.", "Advertencia:", JOptionPane.WARNING_MESSAGE);
                }

                vista.TxPorcion.setText(""); 			// vacia contenido del JTextField
                vista.CbHorario.setSelectedIndex(0); 		// reiniciar el ComboBox horario con primer element selecc
            }
        }

        if (e.getSource() == vista.BtGuardar) {

            String dietaSeleccionada = (String) vista.CBDietasActivas.getSelectedItem();
            String[] partes = dietaSeleccionada.split("-");
            int idDieta = Integer.parseInt(partes[0].trim());   //se obtiene la dieta seleccionada en el ComboBox "CBDietasActivas", se divide en partes utilizando el carácter "-" y se extrae el ID de la dieta. Este ID se almacena en la variable idDieta.
            int rowCount = modelo.getRowCount();    //Se obtiene el número de filas en el modelo de la tabla.

            for (int i = 0; i < rowCount; i++) {    //bucle que recorre todas las filas de la tabla.
                String nombreComida = modelo.getValueAt(i, 1).toString();
                String[] partes2 = nombreComida.split("-");
                int idComida = Integer.parseInt(partes2[0].trim());
                int porcion = Integer.parseInt(modelo.getValueAt(i, 2).toString());
                String horario = modelo.getValueAt(i, 3).toString();
                int idcolumn1 = Integer.parseInt(modelo.getValueAt(i, 0).toString());
//Dentro del bucle, se obtienen los valores de cada fila de la tabla, incluyendo el nombre de la comida, el ID de la comida, la porción, el horario y un ID de columna.
                vista.BtAgregarComida.setEnabled(true);
//Creo un objeto EntidadDieta_Comida con los datos de la dieta seleccionada, la comida, la porción y el horario.                
                EntidadDieta_Comida dietaComida = new EntidadDieta_Comida(idDieta, idComida, porcion, HorarioComida.valueOf(horario));
//Guardo la entidad dietaComida en la base de datos utilizando el método GuardarDietaComida de dataDietaComida. Se verifica que idcolumn1 sea igual a 0 antes de guardar, indicando que la fila es una nueva entrada.
                try {
                    if (idcolumn1 == 0) {
                        dataDietaComida.GuardarDietaComida(dietaComida);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al guardar los datos en la base de datos:\n" + ex.getMessage());
                }
            }
            llenarJTComidas(); //llamo la función llenarJTComidas para actualizar la tabla con los datos guardados.

            reset();
            vista.CBComidasActivas.setSelectedIndex(0); //selecciono el primer elemento en el ComboBox "CBComidasActivas".
            JOptionPane.showMessageDialog(vista, "Datos guardados con éxito.");
        }

        if (e.getSource() == vista.BtQuitarComida) {

            int selectedRow = vista.JTComidas.getSelectedRow(); //obtengo el índice de la fila seleccionada en la tabla "JTComidas" de la vista. Si no se ha seleccionado ninguna fila, selectedRow será igual a -1.
            if (selectedRow != -1) {    //verifica si se ha seleccionado una fila en la tabla.
                int idDietaComida = (int) vista.JTComidas.getValueAt(selectedRow, 0); //obtiene el valor de la primera columna (columna 0) de la fila seleccionada en la tabla. Este valor se interpreta como un ID de "Dieta Comida".
                try {      //bloque try-catch para manejar excepciones
                    boolean eliminacionOK = dataDietaComida.eliminarDietaDeTabla(idDietaComida);
//intento eliminar una entrada de "Dieta Comida" de la base de datos utilizando el método eliminarDietaDeTabla de la instancia dataDietaComida. 
                    if (eliminacionOK) {
                        JOptionPane.showMessageDialog(vista, "Comida eliminada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Comida eliminada de la dieta.");
                        int fila = vista.JTComidas.getSelectedRow(); //obtienen el índice de la fila seleccionada
                        String temp = modelo.getValueAt(fila, 1).toString();   //recupera un valor de la tabla modelo (el valor en la segunda columna con índice 1) esto evita que salga cartel de comida ya ha sido agregada al eliminarla.
                        comidasAgregadas.remove(temp); //elimina un elemento de una colección comidasAgregadas utilizando el valor almacenado en la variable temp.
//Si la eliminación no fue exitosa, se muestra un mensaje de diálogo que indica que la comida se ha eliminado de la dieta. Luego, se obtiene el índice de la fila seleccionada en la tabla y se recupera el valor de la segunda columna (columna 1) de esa fila, que se almacena en la variable temp. Luego, se elimina temp de la colección comidasAgregadas. Esta colección se utiliza para evitar agregar la misma comida varias veces.                    
                    }
                    modelo.removeRow(selectedRow);  // Quitar la fila seleccionada de la tabla

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la comida:\n" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una comida de la tabla antes de quitarla.");
            }
        }

        if (e.getSource() == vista.BtCancel) {
            reset();
            llenarJTComidas();
        }

        if (e.getSource() == vista.BtSalir) {
            vista.dispose();
        }
    }

    private void llenarComboComidasActivas() {
        List<entidades.EntidadComida> comida = new ArrayList<>();
        DataComida e = new DataComida();
        comida = e.obtenerComidasHabilitadas(true);
        vista.CBComidasActivas.removeAllItems();

        for (EntidadComida comida1 : comida) {
            //                if (paciente.isEstado()) {
            String cadena = comida1.getIdComida() + "-" + comida1.getNombreComida();
            vista.CBComidasActivas.addItem(cadena);
        }
    }

    public void reset() {
        vista.CBPaciente.setEnabled(true);
        vista.CBDietasActivas.setEnabled(true);
        vista.CBComidasActivas.setEnabled(true);
        vista.CbHorario.setEnabled(true);
        vista.BtAgregarComida.setEnabled(true);
        vista.BtEliminarDieta.setEnabled(true);
        vista.BtGuardar.setEnabled(false);
        vista.BtNuevaDieta.setEnabled(true);
        vista.BtQuitarComida.setEnabled(true);
        vista.CBComidasActivas.setEnabled(true);
        vista.CBComidasActivas.setEnabled(true);
        vista.TxPorcion.setText("");
        vista.BtCancel.setEnabled(false);
    }

    private void eliminarComidasSeleccionadas() {
        DefaultTableModel tableModel = (DefaultTableModel) vista.JTComidas.getModel();
        int rowCount = tableModel.getRowCount();
        List<Integer> filasAEliminar = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            boolean seleccionado = (boolean) tableModel.getValueAt(i, 0);
            if (seleccionado) {
                filasAEliminar.add(i);
            }
        }
        Collections.reverse(filasAEliminar); // Elimina las filas seleccionadas en orden inverso para evitar problemas de índices

        for (int fila : filasAEliminar) {
            tableModel.removeRow(fila);
        }
    }

    private int extraerIdPaciente() {
        int id = -1;
        try {
            String combobox = vista.CBPaciente.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[0].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idPaciente");
        }
        return id;
    }

    private int extraerIdDieta() {
        int id = -1;
        try {
            String dietaSeleccionada = (String) vista.CBDietasActivas.getSelectedItem();
            if (dietaSeleccionada != null && dietaSeleccionada.contains("-")) {

                String[] partes = dietaSeleccionada.split("-");
                if (partes.length > 0) {
                    id = Integer.parseInt(partes[0].trim());
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idDieta");
        }
        return id;
    }

    @Override
    public void focusGained(FocusEvent e
    ) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e
    ) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e
    ) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
