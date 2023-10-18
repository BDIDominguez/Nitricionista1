package controladores;

/**
 * * @author DIEGO G.
 */
import datas.DataDieta_Comida;
import datas.DataComida;
import datas.DataDieta;
import datas.DataPaciente;

import entidades.EntidadDieta_Comida;
import entidades.EntidadPaciente;
import entidades.EntidadComida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import vistas.VistaDieta_Comida;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;
//public class ControladorDieta_Comida {

public class ControladorDieta_Comida implements ActionListener, FocusListener, ListSelectionListener {

    private VistaPantallaPrincipal menu;
    private final VistaDieta_Comida vista;
    private final DataDieta_Comida dataDietaComida;
    private DefaultTableModel tablaModelo;
    private int paciente = 0;
    private int dietaSeleccionada = -1;
    private boolean edicionActiva = false;
    //    private DataPaciente  = data;
    MyModelo modelo = new MyModelo();
    private List<EntidadPaciente> pacientes = new ArrayList<>();

    public ControladorDieta_Comida(VistaPantallaPrincipal menu, VistaDieta_Comida vista, DataDieta_Comida dataDietaComida) {
        this.vista = vista;
        this.menu = menu;
        this.dataDietaComida = dataDietaComida;
        //        this.tablaModelo = (DefaultTableModel) VistaDieta_Comida.getJTComidas().getModel();
        //        vista.CBPaciente.setModel(pacientesModel);
        //        vista.CBDietas1.setModel(dietasModel);

        //Escucha de botones 
        vista.BtNuevaDieta.addActionListener(this);
        vista.BtEliminar.addActionListener(this);
        vista.BtAgregarComida.addActionListener(this);
        vista.BtEditar.addActionListener(this);
        vista.BtGuardar.addActionListener(this);
        vista.BtSalir.addActionListener(this);
        //Combos
        vista.CBPaciente.addActionListener(this);
        vista.CBDietas1.addActionListener(this);
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
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        llenarComboBPaciente();
        llenarComboBDietas();
        llenarComboComidasActivas();
    }

    private void modelarTabla() {
        modelo.addColumn("Comida");
        modelo.addColumn("Porción");
        modelo.addColumn("Horarios");
        vista.JTComidas.setModel(modelo);
        vista.JTComidas.getColumnModel().getColumn(0).setPreferredWidth(50);
        vista.JTComidas.getColumnModel().getColumn(1).setPreferredWidth(30);
        vista.JTComidas.getColumnModel().getColumn(2).setPreferredWidth(20);
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
            System.out.println("valor pac" + paciente);
            comidas = dataDietaComida.obtenerDietasComidaPorDieta(paciente);
            System.out.println("cuantas comidas hay" + comidas.size());
            modelo.setRowCount(0);

            for (EntidadDieta_Comida comida : comidas) {
                modelo.addRow(new Object[]{comida.getIdComida(), comida.getPorcion(), comida.getHorario()});
            }
            vista.JTComidas.setModel(modelo);
            pacientes.clear();
            pacientes.addAll(pacientes);

        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaDieta_Comida.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al llenar JTComidas al tratar de obtener una lista de comidas \n" + ex.getMessage());
        }
    }

    private void llenarComboBPaciente() {
        List<EntidadPaciente> pac = new ArrayList<>();
        try {
            DataPaciente e = new DataPaciente();
            pac = e.listarPacientes();
            vista.CBPaciente.removeAllItems();
            System.out.println("valor de pac" + pac.size());
            for (EntidadPaciente paciente : pac) {
                //                if (paciente.isEstado()) {
                String cadena = paciente.getNombre() + "-" + paciente.getDni() + "-" + paciente.getIdpaciente();
                vista.CBPaciente.addItem(cadena);
            }

            AutoCompleteDecorator.decorate(vista.CBPaciente);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de pacientes para llenar Combo pacientes \n" + ex.getMessage());
        }
    }

    private void llenarComboBDietas() {
        List<entidades.EntidadDieta> diet = new ArrayList<>();

        try {
            DataDieta e = new DataDieta();
            diet = e.listarDietas();
            vista.CBDietas1.removeAllItems();

            System.out.println("valor de pac" + diet.size());
            for (entidades.EntidadDieta dieta : diet) {
                if (dieta.getPaciente() == paciente) {
                    String cadena = dieta.getNombre();
                    vista.CBDietas1.addItem(cadena);
                    System.out.println("cadena de dieta funciona");
                }
            }
            AutoCompleteDecorator.decorate(vista.CBDietas1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de dietas para llenar el combo Dietas\n" + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.CBPaciente) { //muestra para seleccionar un paciente activo

            paciente = extraerIdPaciente();
            llenarJTComidas();
            llenarComboBDietas();

        }

        if (e.getSource() == vista.CBDietas1) {// muestra las dietas disponibles activas para elegir
            //        rellenarTabla(); // rellena la tabla con los datos del paciente
            llenarJTComidas();
        }

        if (e.getSource() == vista.BtNuevaDieta) { //llama a la vista Dieta para cargar nueva dieta y actualiza el JTable JTComidas
            VistaDieta vista = new VistaDieta();
            DataDieta data = new DataDieta();
            ControladorDieta ctrl = new ControladorDieta(menu, vista, data);
            ctrl.iniciar();
            JOptionPane.showMessageDialog(vista, "Por favor cargue aquí su nueva dieta, luego cierre la ventana Dietas y continúe usando el plan Nutricional");
        }

        if (e.getSource() == vista.BtEliminar) {// elimina la dieta seleccionada del CBComidasActivas del paciente actual
            DataDieta dataDieta = new DataDieta();
            if (JOptionPane.showConfirmDialog(vista, "Seguro de eliminar la dieta " + vista.CBDietas1.getSelectedItem(), "Confirme", JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    String dietaSeleccionada = (String) vista.CBDietas1.getSelectedItem();
                    String[] partes = dietaSeleccionada.split("-");
                    int idDieta = Integer.parseInt(partes[0].trim());
                    boolean eliminacionOK = dataDietaComida.eliminarDietaComida(idDieta);

                    //  boolean vResp = dataDietaComida.eliminarDietaComida(dietaSeleccionada);
                    //  dataDieta.eliminarDieta(dietaSeleccionada);
                    if (eliminacionOK) {
                        JOptionPane.showMessageDialog(vista, "Dieta eliminada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta.");
                    }

                    llenarComboBDietas();
                    modelo.setRowCount(0);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta \n" + ex.getMessage());
                }
            }
        }
        if (e.getSource() == vista.CBComidasActivas) { // combobox muestra las comidas activas
            String comidaSelect = (String) vista.CBComidasActivas.getSelectedItem();
        }

        if (e.getSource() == vista.BtAgregarComida) { //agrega la comida seleccionada del combo box CBComidasActivas a la dieta del paciente
            String comida = vista.CBComidasActivas.getSelectedItem().toString(); 	// Obtiene comida seleccionada del Combo
            String porcion = vista.TxPorcion.getText(); 					// Obtiene texto del JTextField porción
            String horario = vista.CbHorario.getSelectedItem().toString();		// Obtiene horario seleccionado del Combo

            DefaultTableModel modelaT = (DefaultTableModel) vista.JTComidas.getModel(); // modelo de la tabla

            modelaT.addRow(new Object[]{comida, porcion, horario}); // Agregar valores a una nueva fila

            // Limpia los componentes después de agregar la fila
            vista.CBComidasActivas.setSelectedIndex(0); 	//  reiniciar el ComboBox con 1er elemento selecc
            vista.TxPorcion.setText(""); 			// vacia contenido del JTextField
            vista.CbHorario.setSelectedIndex(0); 		// reiniciar el ComboBox con primer element selecc
        }

        if (e.getSource() == vista.BtGuardar) {

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
            String cadena = comida1.getNombreComida() + "-" + comida1.getIdComida();
            vista.CBComidasActivas.addItem(cadena);
        }
        AutoCompleteDecorator.decorate(vista.CBComidasActivas);
    }

    private int extraerIdPaciente() {
        int id = -1;
        try {
            String combobox = vista.CBPaciente.getSelectedItem().toString();
            System.out.println("linea 263" + combobox);
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[2].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idPaciente");
        }
        return id;
    }
}
