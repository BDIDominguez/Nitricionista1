package controladores;

/**
 * * @author DIEGO G.
 */
import datas.DataDieta_Comida;
import datas.DataComida;
import datas.DataDieta;
import datas.DataPaciente;

import entidades.EntidadDieta_Comida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import vistas.VistaDieta_Comida;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;
//public class ControladorDieta_Comida {

public class ControladorDieta_Comida implements ActionListener, FocusListener, ListSelectionListener {

    private VistaPantallaPrincipal menu;
    private final VistaDieta_Comida vista;
    private final DataDieta_Comida dataDietaComida;
    private DefaultTableModel tablaModelo;
    private int pacienteSeleccionado = -1;
    private int dietaSeleccionada = -1;
    private boolean edicionActiva = false;

    public ControladorDieta_Comida(VistaPantallaPrincipal menu, VistaDieta_Comida vista, DataDieta_Comida data) {
        this.vista = vista;
        this.menu = menu;
        this.dataDietaComida = data;
//        this.tablaModelo = (DefaultTableModel) VistaDieta_Comida.getJTComidas().getModel();

//        vista.CBPaciente.setModel(pacientesModel);
//        vista.CBDietas1.setModel(dietasModel);

        vista.CBPaciente.addActionListener(this);
        vista.CBDietas1.addActionListener(this);
        vista.BtEliminar.addActionListener(this);
        vista.BtNuevaDieta.addActionListener(this);
        vista.BtEditar.addActionListener(this);
        vista.jBGuardar.addActionListener(this);
        vista.BtAgregarComida.addActionListener(this);
        vista.BtSalir.addActionListener(this);
        vista.JTComidas.getSelectionModel().addListSelectionListener(this);
    }

    public void iniciar() {
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                
        if  (e.getSource()== vista.CBPaciente) { //muestra para seleccionar un paciente activo
            
        }
        
        if (e.getSource() == vista.CBDietas1){// muestra las dietas disponibles activas para elegir
        }
        
        if (e.getSource() == vista.BtNuevaDieta) { //llama a la vista Dieta para cargar nueva dieta y actualiza el JTable JTComidas

        }

        if (e.getSource() == vista.BtEliminar) {// elimina la dieta seleccionada del CBComidasActivas del paciente actual

        }
        
        if (e.getSource()== vista.CBComidasActivas ){ // combobox muestra las comidas activas
        
        }
        
        if (e.getSource()== vista.BtAgregarComida){ //agrega la comida seleccionada del combo box CBComidasActivas a la dieta del paciente
        
        }
        
        
        
        
        
        
        if (e.getSource() == vista.BtSalir) {
            vista.dispose();
        }
    }
    
    

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
