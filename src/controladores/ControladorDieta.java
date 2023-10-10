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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matias
 */
public class ControladorDieta implements ActionListener, KeyListener {

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
            if (vista.txDNI.getText().equals("") || vista.txDNI.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "El Dni no puede estar en blanco");
            } else {
                int b = Integer.parseInt(vista.txDNI.getText());
                EntidadPaciente pac = new EntidadPaciente();
                DataPaciente a = new DataPaciente();
                try {
                    pac = a.pacienteDNI(b);
                    if (pac.getDni() == b) {
                        vista.txNombreP.setText(pac.getNombre());
                        //llamar el metodo para rellenar el combo de dietas
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se encontrado el DNI");
                        vista.txDNI.requestFocus();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error de Consultar Paciente");
                }

            }
        }
        if (d.getSource() == vista.btGuardar) {
            vista.btNuevo.setEnabled(true);
            vista.btEliminar.setEnabled(true);
            vista.txNombreD.setEnabled(true);
            vista.txPesoIni.setEnabled(true);
            vista.txPesoFin.setEnabled(true);

            if (idDieta == 0 && vista.txNombreD.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo de texto no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                EntidadDieta di = new EntidadDieta();
                di.setNombre(vista.txNombreD.getText());
                di.setPesoInicial(Double.parseDouble(vista.txPesoIni.getText()));
                di.setPesoFinal(Double.parseDouble(vista.txPesoFin.getText()));

                java.util.Date fechaInicio = vista.dcFechInicio.getDate();
                Instant instantInicio = fechaInicio.toInstant();
                LocalDate fechaInicial = instantInicio.atZone(ZoneId.systemDefault()).toLocalDate();
                di.setFechaInicial(fechaInicial);

                java.util.Date fechaFinal = vista.dcFechFinal.getDate();
                Instant instantFinal = fechaFinal.toInstant();
                LocalDate fechaFin = instantFinal.atZone(ZoneId.systemDefault()).toLocalDate();
                di.setFechaFinal(fechaFin);

                // Obten el ID del paciente, por ejemplo, mediante una consulta a la base de datos
                int idPaciente = data.pacienteID(); // Implementa esta función para obtener el ID del paciente

                // Asocia el paciente a la dieta
                EntidadPaciente paciente = new EntidadPaciente();
                paciente.setIdpaciente(idPaciente);
                di.setPaciente(paciente);
                
                boolean vRespuesta = data.crearDieta(di);
            }

        }
        if (d.getSource() == vista.btSalir) {
            vista.dispose();
        }
        if (d.getSource() == vista.btEliminar) {
            String nombreDieta = vista.txNombreD.getText();
            int idDieta = Integer.parseInt(vista.txNombreD.getText());

            int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Seguro desea dar de baja la Dieta: " + nombreDieta + "?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    boolean vRespuesta = data.eliminarDieta(idDieta);

                    if (vRespuesta) {
                        JOptionPane.showMessageDialog(vista, "Dieta dada de baja con éxito.");
                        // Realiza cualquier otra acción necesaria después de eliminar
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo dar de baja la dieta.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vista, "Error al dar de baja la dieta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (d.getSource() == vista.cbEstado) {
            if (vista.cbEstado.isSelected()) {
                vista.cbEstado.setText("ACTIVO");
            } else {
                vista.cbEstado.setText("DEBAJA");
                vista.cbEstado.setEnabled(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
        if (e.getSource() == vista.txDNI) {
            char caracter = e.getKeyChar();
            if (caracter < '0' || caracter > '9') {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
