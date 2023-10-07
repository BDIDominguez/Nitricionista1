/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import datas.DataControl;
import datas.DataPaciente;
import entidades.EntidadControl;
import entidades.EntidadPaciente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.VistaControl;
import vistas.VistaPantallaPrincipal;

/**
 *
 * @author Dario
 */
public class ControladorControl implements ActionListener, FocusListener, ListSelectionListener {
    private VistaControl vista;
    private VistaPantallaPrincipal menu;
    private DataControl cData;
    private DataPaciente pData;
    MyModelo modelo = new MyModelo();
    private List<EntidadPaciente> pacientes = new ArrayList<>();

    public ControladorControl(VistaControl vista, VistaPantallaPrincipal menu, DataControl cData, DataPaciente pData) {
        this.vista = vista;
        this.menu = menu;
        this.cData = cData;
        this.pData = pData;
        // Capturando Botones
        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);
        
        //Capturando Combo
        vista.cbPacientes.addActionListener(this);
        
        
    
    }
    
    public void iniciar() {
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(false);
        modelarTabla();
        llenarTabla();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

    @Override
    public void focusGained(FocusEvent e) {
       
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
    }
    private class MyModelo extends DefaultTableModel {
        //para evitar las edicion de los campos de la tabla pero que se puedan seleccionar
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
    private void modelarTabla() {
        modelo.addColumn("Fecha");
        modelo.addColumn("Peso");
        modelo.addColumn("Cintura");
        modelo.addColumn("Cita");
        vista.tbControl.setModel(modelo);
        vista.tbControl.getColumnModel().getColumn(0).setPreferredWidth(80);
        vista.tbControl.getColumnModel().getColumn(1).setPreferredWidth(250);
        vista.tbControl.getColumnModel().getColumn(2).setPreferredWidth(500);
        vista.tbControl.getColumnModel().getColumn(3).setPreferredWidth(300);
    }

    private void llenarTabla() {
        List<EntidadControl> pa = new ArrayList<>();
        try {
            pa = cData.listarControles();
            modelo.setRowCount(0);
            for (EntidadControl enti : pa) {
                modelo.addRow(new Object[]{enti.getFecha(), enti.getPeso(), enti.getCintura(), enti.getProximacita()});
            }
            vista.tbControl.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de Controles \n" + ex.getMessage());
        }
    }
    
    private void llenarPacientes(){
        try {
            pacientes = pData.listarPacientes();
            for (int i = pacientes.size() -1; i >= 0;i--){ // Se eliminaran a los pacientes que no esten activos
                if (!pacientes.get(i).isEstado()){
                    pacientes.remove(i);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de Pacientes \n" + ex.getMessage());
        }
    }
    
    
}
