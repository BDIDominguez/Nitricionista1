/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import datas.DataDieta;
import datas.DataPaciente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;
import entidades.EntidadDieta;
import entidades.EntidadPaciente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Matias
 */
public class ControladorDieta implements ActionListener, KeyListener{
    
    private Connection con;
    private final VistaDieta vista;
    private final DataDieta data;
    private final VistaPantallaPrincipal menu;
    private int idDieta;
    
    public ControladorDieta(VistaPantallaPrincipal menu, VistaDieta vista, DataDieta data) {
        this.menu = menu;
        this.vista = vista;
        this.data = data;
        //Botones
        vista.btBuscar.addActionListener(this);
        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);
        vista.cboxListaDietas.addActionListener(this);
        //Check
        vista.cbEstado.addActionListener(this);
        
        
    }
    
    public void iniciar() {
        menu.dpFondo.add(vista);
        vista.setVisible(true);
        menu.dpFondo.moveToFront(vista);
        vista.requestFocus();
        vista.txDNI.setText("0");
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(false);
       
    }

    @Override
    public void actionPerformed(ActionEvent d) {
        if (d.getSource() == vista.btNuevo) {
            vista.txDNI.setText("-1");
            vista.txNombreP.setText("");
            vista.txNombreD.setText("");
            vista.txPesoIni.setText("");
            vista.txPesoFin.setText("");
            vista.cbEstado.setSelected(true);
            vista.btNuevo.setEnabled(false);
            vista.btGuardar.setEnabled(true);
            vista.txDNI.requestFocus();
        }
        if (d.getSource() == vista.btBuscar) {
            if(vista.txDNI.getText().equals("") || vista.txDNI.getText().equals("0")){
                JOptionPane.showMessageDialog(null, "El Dni no puede estar en blanco");
            }else{
                int b = Integer.parseInt(vista.txDNI.getText());
                EntidadPaciente pac = new EntidadPaciente();
                DataPaciente a = new DataPaciente();
                try {
                    pac = a.pacienteDNI(b);
                    if(pac.getDni() == b){
                        vista.txNombreP.setText(pac.getNombre());
                        //llamar el metodo para rellenar el combo de dietas
                    }else{
                        JOptionPane.showMessageDialog(vista, "No se encontrado el DNI");
                        vista.txDNI.requestFocus();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error de Consultar Paciente");
                }
                 
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.txDNI) {
            char caracter = e.getKeyChar();
            if (caracter < '0' || caracter > '9') {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
