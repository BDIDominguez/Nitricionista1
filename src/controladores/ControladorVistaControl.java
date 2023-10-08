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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class ControladorVistaControl implements ActionListener, FocusListener, ListSelectionListener {

    private VistaControl vista;
    private VistaPantallaPrincipal menu;
    private DataControl cData;
    private DataPaciente pData;
    MyModelo modelo = new MyModelo();
    private List<EntidadPaciente> pacientes = new ArrayList<>();
    private int idcontrol = -1;
    

    public ControladorVistaControl(VistaControl vista, VistaPantallaPrincipal menu, DataControl cData, DataPaciente pData) {
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

        //Agregando la Tabla
        vista.tbControl.getSelectionModel().addListSelectionListener(this);
        
    }

    public void iniciar() {
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(false);
        llenarPacientes();
        llenarCombo();
        modelarTabla();
        llenarTabla(extraerIdPaciente());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btSalir) {
            vista.dispose();
        }
        if (e.getSource() == vista.btNuevo) {
            idcontrol = -1;
            vista.dcFecha.setDate(new Date());
            vista.txPeso.setText("0.00");
            vista.txAltura.setText("0.00");
            vista.txCintura.setText("0.00");
            vista.txIMC.setText("0.00");
            vista.txGasto.setText("0.00");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, 7);
            vista.dcCita.setDate(cal.getTime());
            vista.btEliminar.setEnabled(false);
            vista.btGuardar.setEnabled(true);
        }
        if (e.getSource() == vista.btGuardar) {
            EntidadControl co = new EntidadControl();
            co.setIdControl(extraerIdPaciente());
            co.setFecha(vista.dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            co.setPeso(Double.parseDouble(vista.txPeso.getText()));
            co.setAltura(Double.parseDouble(vista.txAltura.getText()));
            co.setCintura(Double.parseDouble(vista.txCintura.getText()));
            co.setGasenergetico(Double.parseDouble(vista.txGasto.getText()));
            co.setIMC(Double.parseDouble(vista.txIMC.getText()));
            co.setProximacita(vista.dcCita.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            co.setEstado(true);
            co.setObs(vista.txObs.getText());
            if (co.getIdControl() == -1) { // Crear uno nuevo
                try {
                    co = cData.crearControl(co);
                    if (co.getIdControl() != -1) {
                        JOptionPane.showMessageDialog(vista, "Se Cargo el control con exito!");
                        idcontrol = co.getIdControl();
                        llenarTabla(extraerIdPaciente());
                        vista.btGuardar.setEnabled(false);
                        vista.btEliminar.setEnabled(true);
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorControl.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al intentar guardar el control \n" + ex.getMessage());
                }
            } else { // Guardar Modificaciones
                boolean vRespuesta = false;
                try {
                    vRespuesta = cData.modificarControl(co);
                    if (vRespuesta){
                        JOptionPane.showMessageDialog(vista, "Cambios guardados con exito" );
                        vista.btGuardar.setEnabled(false);
                        vista.btEliminar.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(vista, "No se pudo guardar los cambios!!" );
                        vista.btGuardar.setEnabled(true);
                        vista.btEliminar.setEnabled(false);
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorControl.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al intentar modificar el control \n" + ex.getMessage());
                }
            }
        }
        if (e.getSource() == vista.cbPacientes){
            llenarTabla(extraerIdPaciente());
        }
        
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()){
            int fila = vista.tbControl.getSelectedRow();
            if (fila != -1){
                this.idcontrol = (int) vista.tbControl.getValueAt(fila, 0);
                mostrarControl();
            }
        }

    }

    private class MyModelo extends DefaultTableModel {

        //para evitar las edicion de los campos de la tabla pero que se puedan seleccionar
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void modelarTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Peso");
        modelo.addColumn("Cintura");
        modelo.addColumn("Cita");
        modelo.addColumn("Observaciones");
        vista.tbControl.setModel(modelo);
        vista.tbControl.getColumnModel().getColumn(0).setPreferredWidth(15);
        vista.tbControl.getColumnModel().getColumn(1).setPreferredWidth(40);
        vista.tbControl.getColumnModel().getColumn(2).setPreferredWidth(30);
        vista.tbControl.getColumnModel().getColumn(3).setPreferredWidth(30);
        vista.tbControl.getColumnModel().getColumn(4).setPreferredWidth(40);
        vista.tbControl.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private void llenarTabla(int idPaciente) {
        List<EntidadControl> pa = new ArrayList<>();
        try {
            pa = cData.listarControles();
            modelo.setRowCount(0);
            for (EntidadControl enti : pa) {
                if (enti.getIdPaciente() == idPaciente) {
                    modelo.addRow(new Object[]{enti.getIdControl(), enti.getFecha(), enti.getPeso(), enti.getCintura(), enti.getProximacita(), enti.getObs()});
                }
            }
            vista.tbControl.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de Controles \n" + ex.getMessage());
        }
    }

    private void llenarPacientes() {
        try {
            pacientes = pData.listarPacientes();
            for (int i = pacientes.size() - 1; i >= 0; i--) { // Se eliminaran a los pacientes que no esten activos
                if (!pacientes.get(i).isEstado()) {
                    pacientes.remove(i);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de Pacientes \n" + ex.getMessage());
        }
    }

    private void llenarCombo() {
        vista.cbPacientes.removeAllItems();
        for (EntidadPaciente paciente : pacientes) {
            String cadena = paciente.getIdpaciente() + " - " + paciente.getDni() + " - " + paciente.getNombre();
            vista.cbPacientes.addItem(cadena);
        }
    }
    private int extraerIdPaciente(){
        int id = -1;
        try{
            String combobox = vista.cbPacientes.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[0].trim()); 
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    
    private void mostrarControl(){
        try {
            EntidadControl co = new EntidadControl();
            co = cData.ControlxID(idcontrol);
            //vista.dcFecha.setDate(Date.from(LocalDate.atZone(ZoneId.systemDefault()).toInstant()));
            //java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "no se puede consultar datos del Control " + idcontrol + "\n" + ex.getMessage());
        }
        
    }

} //Fin Clase