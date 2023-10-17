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
    private int paciente;
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
        vista.jBGuardar.addActionListener(this);
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
        llenarJTComidas();
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
            List<EntidadDieta_Comida> comidas = dataDietaComida.obtenerDietasComidaPorDieta(paciente);
            modelo.setRowCount(0);

            for (EntidadDieta_Comida comida : comidas) {
                modelo.addRow(new Object[]{comida.getIdComida(), comida.getPorcion(), comida.getHorario()});
            }
            vista.JTComidas.setModel(modelo);
            pacientes.clear();
            pacientes.addAll(pacientes);

        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaDieta_Comida.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de comidas \n" + ex.getMessage());
        }
    }

    private void llenarComboBPaciente() {
        List<EntidadPaciente> pac = new ArrayList<>();
        try {
            pac = DataPaciente.;
            vista.CBPaciente.removeAllItems();
            for (EntidadDieta_Comida enti : pac) {
                if (enti.getPaciente() == idPaciente) {
                    String cadena = paciente.Nombre() + "-" + paciente.getDni();
                    vista.CBPacientes.addItem(cadena);
                }
            }
            AutoCompleteDecorator.decorate(vista.CBPaciente);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de pacientes \n" + ex.getMessage());
        }
    }    
       
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.CBPaciente) { //muestra para seleccionar un paciente activo

        }

        if (e.getSource() == vista.CBDietas1) {// muestra las dietas disponibles activas para elegir
//        rellenarTabla(); // rellena la tabla con los datos del paciente
        }

        if (e.getSource() == vista.BtNuevaDieta) { //llama a la vista Dieta para cargar nueva dieta y actualiza el JTable JTComidas
            VistaDieta vistaDieta = new VistaDieta();
            vistaDieta.setVisible(true);
        }

//        if (e.getSource() == vista.BtEliminar) {// elimina la dieta seleccionada del CBComidasActivas del paciente actual
//            if (JOptionPane.showConfirmDialog(vista, "Seguro de elimiar la dieta " + vista.CBDietas1.getSelectedItem(), "Confirme", JOptionPane.YES_NO_OPTION) == 0) {
//
//            }
//            try {
//                boolean vResp = dataDietaComida.eliminarDietaComida(dietaSeleccionada);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(vista, "Error al eliminar la dieta \n" + Exception.getMessage);
//            }
//        }
        if (e.getSource() == vista.CBComidasActivas) { // combobox muestra las comidas activas

        }

        if (e.getSource() == vista.BtAgregarComida) { //agrega la comida seleccionada del combo box CBComidasActivas a la dieta del paciente

        }

        if (e.getSource() == vista.BtSalir) {
            vista.dispose();
        }

        llenarJTComidas();
    }

    @Override
    public void focusGained(FocusEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
