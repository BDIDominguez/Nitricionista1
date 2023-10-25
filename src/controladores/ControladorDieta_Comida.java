package controladores;

/**
 * * @author DIEGO G
 */
import datas.DataDieta_Comida;
import datas.DataComida;
import datas.DataDieta;
import datas.DataPaciente;

import entidades.EntidadDieta_Comida;
import entidades.EntidadPaciente;
import entidades.EntidadComida;
import entidades.EntidadDieta_Comida.HorarioComida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import vistas.VistaDieta_Comida;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;

public class ControladorDieta_Comida implements ActionListener, FocusListener, ListSelectionListener {

    private VistaPantallaPrincipal menu;
    private final VistaDieta_Comida vista;
    private final DataDieta_Comida dataDietaComida;
    private int paciente = 0;
    private int dietaSeleccionada = -1;
    MyModelo modelo = new MyModelo();
    private List<EntidadPaciente> pacientes = new ArrayList<>();
    private Set<String> comidasAgregadas = new HashSet<>();

    public ControladorDieta_Comida(VistaPantallaPrincipal menu, VistaDieta_Comida vista, DataDieta_Comida dataDietaComida) {
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
        //Combos
        vista.CBPaciente.addActionListener(this);
        vista.CBDietasActivas.addActionListener(this);
        vista.CBComidasActivas.addActionListener(this);
        vista.CbHorario.addActionListener(this);
        //Cuadro de texto
        vista.TxPorcion.addFocusListener(this);
        //Tabla
        vista.JTComidas.getSelectionModel().addListSelectionListener(this);
    }

    public void iniciar() {
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        vista.setLocation(40, 70);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        llenarComboBPaciente();
        llenarComboBDietasActivas();
        llenarComboComidasActivas();
        vista.BtGuardar.setEnabled(false);
    }

    private void modelarTabla() {
        modelo.addColumn("id");
        modelo.addColumn("Comida");
        modelo.addColumn("Porción");
        modelo.addColumn("Horarios");

        vista.JTComidas.setModel(modelo);
        vista.JTComidas.getColumnModel().getColumn(0).setPreferredWidth(10);
        vista.JTComidas.getColumnModel().getColumn(1).setPreferredWidth(50);
        vista.JTComidas.getColumnModel().getColumn(2).setPreferredWidth(30);
        vista.JTComidas.getColumnModel().getColumn(3).setPreferredWidth(20);
    }

    private class MyModelo extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void llenarJTComidas() {
        try {
            List<EntidadDieta_Comida> comidas = new ArrayList<>();

            comidas = dataDietaComida.obtenerDietasComidaPorDieta(extraerIdDieta());
            modelo.setRowCount(0);

            DataComida a = new DataComida();
            for (EntidadDieta_Comida comida : comidas) {
                comidasAgregadas.add(comida.getIdComida() + "-" + a.obtenerNombrexidComida(comida.getIdComida())); //rellena el set para que no vuelva a agregar una comida repetida
                modelo.addRow(new Object[]{comida.getIdDieta_Comida(), comida.getIdComida() + "-" + a.obtenerNombrexidComida(comida.getIdComida()), comida.getPorcion(), comida.getHorario()});
            }
            vista.JTComidas.setModel(modelo);
            pacientes.clear();
            pacientes.addAll(pacientes);

        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaDieta_Comida.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al llenar JTComidas al tratar de obtener una lista de comidas \n" + ex.getMessage());
        }
        vista.BtGuardar.setEnabled(false);
        vista.BtAgregarComida.setEnabled(true);
    }

    private void llenarComboBPaciente() {
        List<EntidadPaciente> pac = new ArrayList<>();
        try {
            DataPaciente e = new DataPaciente();
            pac = e.listarPacientes();
            vista.CBPaciente.removeAllItems();

            for (EntidadPaciente paciente : pac) {
                //                if (paciente.isEstado()) {
                String cadena = paciente.getIdpaciente() + "-" + paciente.getDni() + "-" + paciente.getNombre();
                vista.CBPaciente.addItem(cadena);
            }

            AutoCompleteDecorator.decorate(vista.CBPaciente);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de pacientes para llenar Combo pacientes \n" + ex.getMessage());
        }
//        llenarComboBDietasActivas();
    }

    private void llenarComboBDietasActivas() {
        List<entidades.EntidadDieta> diet = new ArrayList<>();

        try {
            vista.CBDietasActivas.removeAllItems();
            DataDieta dataDieta = new DataDieta();
            List<entidades.EntidadDieta> dietas = dataDieta.listarDietas();

            for (entidades.EntidadDieta dieta : dietas) {
                if (dieta.isEstado() && dieta.getPaciente()==paciente) { // Verifica si la dieta está activa
                    vista.CBDietasActivas.addItem(dieta.getIdDieta() + "-" + dieta.getNombre());
                }
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
            //        rellenarTabla(); // rellena la tabla con los datos del paciente
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
            vista.setLocation(300, 80);
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

            String porcionText = vista.TxPorcion.getText();

            if (vista.TxPorcion.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo porción no puede estar en blanco. Por favor indique una cantidad. (recuerde seleccionar la comida y el horario también antes de agregar)", "Error: ", JOptionPane.ERROR_MESSAGE);

                vista.CBPaciente.setEnabled(true);
                vista.CBDietasActivas.setEnabled(true);
                vista.BtEliminarDieta.setEnabled(true);
                vista.BtNuevaDieta.setEnabled(true);
                vista.BtQuitarComida.setEnabled(true);

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
            int idDieta = Integer.parseInt(partes[0].trim());
            int rowCount = modelo.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                String nombreComida = modelo.getValueAt(i, 1).toString();
                String[] partes2 = nombreComida.split("-");
                int idComida = Integer.parseInt(partes2[0].trim());
                int porcion = Integer.parseInt(modelo.getValueAt(i, 2).toString());
                String horario = modelo.getValueAt(i, 3).toString();
                int idcolumn1 = Integer.parseInt(modelo.getValueAt(i, 0).toString());

                vista.BtAgregarComida.setEnabled(true);
                EntidadDieta_Comida dietaComida = new EntidadDieta_Comida(idDieta, idComida, porcion, HorarioComida.valueOf(horario));

                try {
                    if (idcolumn1 == 0) {
                        dataDietaComida.GuardarDietaComida(dietaComida);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al guardar los datos en la base de datos:\n" + ex.getMessage());
                }
            }
            llenarJTComidas();

            vista.BtNuevaDieta.setEnabled(true);
            vista.BtEliminarDieta.setEnabled(true);
            vista.CBPaciente.setEnabled(true);
            vista.CBDietasActivas.setEnabled(true);
            vista.BtQuitarComida.setEnabled(true);
//            modelaT.setRowCount(0);
//            vista.CBComidasActivas.setSelectedIndex(0);
            vista.TxPorcion.setText("");
            vista.CbHorario.setSelectedIndex(0);
            llenarComboComidasActivas();
            vista.CBComidasActivas.setSelectedIndex(0);
            JOptionPane.showMessageDialog(vista, "Datos guardados con éxito.");
        }

        if (e.getSource() == vista.BtQuitarComida) {

            int selectedRow = vista.JTComidas.getSelectedRow();
            if (selectedRow != -1) {
                int idDietaComida = (int) vista.JTComidas.getValueAt(selectedRow, 0);

                try {
                    boolean eliminacionOK = dataDietaComida.eliminarDietaDeTabla(idDietaComida);

                    if (eliminacionOK) {
                        JOptionPane.showMessageDialog(vista, "Comida eliminada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Comida eliminada de la dieta.");
                    }

                    // Quitar la fila seleccionada de la tabla
                    modelo.removeRow(selectedRow);
                    vista.BtGuardar.setEnabled(true);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la comida:\n" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una comida de la tabla antes de quitarla.");

            }
        }

        if (e.getSource() == vista.BtSalir) {
            vista.dispose();
        }
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
        AutoCompleteDecorator.decorate(vista.CBComidasActivas);
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

        // Elimina las filas seleccionadas en orden inverso para evitar problemas de índices
        Collections.reverse(filasAEliminar);

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
}
