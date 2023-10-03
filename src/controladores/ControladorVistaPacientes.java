package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import datas.DataPaciente;
import entidades.EntidadPaciente;
import vistas.VistaPacientes;
import vistas.VistaPantallaPrincipal;

/**
 * @author Dario
 */
public class ControladorVistaPacientes implements ActionListener,FocusListener {
    private VistaPantallaPrincipal menu;
    private VistaPacientes vista;
    private DataPaciente data;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVistaPacientes(VistaPantallaPrincipal menu, VistaPacientes vista, DataPaciente data) {
        this.menu = menu;
        this.vista = vista;
        this.data = data;
        //Botones
        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);
        //Cuadros de texto
        vista.txDNI.addFocusListener(this);
        
    }
    public void iniciar(){
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(false);
        vista.tbPacientes.setEnabled(false);
        modelarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vista.btNuevo){
           vista.txID.setText("-1");
           vista.txDNI.setText("");
           vista.txNombre.setText("");
           vista.txDomicilio.setText("");
           vista.txTelefono.setText("");
           vista.btNuevo.setEnabled(false);
           vista.btGuardar.setEnabled(true);
           vista.txDNI.requestFocus();
       }
       if (e.getSource() == vista.btGuardar){
           if (vista.txID.getText().equals("-1")){ // Crear nuevo Paciente
               EntidadPaciente p = new EntidadPaciente();
               p.setIdpaciente(Integer.parseInt(vista.txID.getText()));
               p.setDni(Integer.parseInt(vista.txDNI.getText()));
               p.setNombre(vista.txNombre.getText());
               p.setDomicilio(vista.txDomicilio.getText());
               p.setTelefono(vista.txTelefono.getText());
               try {
                   EntidadPaciente ide = data.crearPaciente(p);
                   vista.txID.setText(ide.getIdpaciente() + "");
                   vista.btGuardar.setEnabled(false);
                   vista.btEliminar.setEnabled(false);
                   JOptionPane.showMessageDialog(vista, "Paciente creado con exito");
               } catch (SQLException ex) {
                   Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                   JOptionPane.showMessageDialog(vista, "Error con el SQL " + ex.getMessage());
               }
           }
           
       }
       if (e.getSource() == vista.btSalir){
           vista.dispose();
       }
    }

    @Override
    public void focusGained(FocusEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void modelarTabla(){
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        vista.tbPacientes.setModel(modelo);
        vista.tbPacientes.getColumnModel().getColumn(0).setPreferredWidth(80);
        vista.tbPacientes.getColumnModel().getColumn(1).setPreferredWidth(250);
        vista.tbPacientes.getColumnModel().getColumn(2).setPreferredWidth(500);
        vista.tbPacientes.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    
    private void llenarTabla(){
        
    }
}
