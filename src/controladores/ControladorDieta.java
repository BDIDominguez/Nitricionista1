/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import datas.DataDieta;
import datas.DataPaciente;
import datas.DataComida;
import datas.DataDieta_Comida;
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
import entidades.EntidadComida;
import entidades.EntidadDieta_Comida;
import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
    private int idPaciente = -1;

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
        vista.txDNI.setText("");
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(false);
        llenarComboDieta();

    }

    @Override
    public void actionPerformed(ActionEvent d) {
        if (d.getSource() == vista.btNuevo) {
            //vista.txDNI.setText("-1");
            //vista.txNombreP.setText("");
            vista.txNombreD.setText("");
            vista.txPesoIni.setText("");
            vista.txPesoFin.setText("");
            vista.dcFechInicio.setDate(null);
            vista.dcFechFinal.setDate(null);
            vista.cbEstado.setSelected(true);
            vista.btNuevo.setEnabled(false);
            vista.btGuardar.setEnabled(true);
            vista.txNombreD.requestFocus();
            idDieta = -1;
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
                        idPaciente = pac.getIdpaciente();
                        llenarComboDieta();
                        vista.btEliminar.setEnabled(true);
                        vista.btGuardar.setEnabled(true);

                        //llamar el metodo para rellenar el combo de dietas
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se encontrado el DNI");
                        idPaciente = -1;
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
            vista.cboxListaDietas.setEnabled(true);

            if (idDieta == 0 && vista.txNombreD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de texto no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (idPaciente > 0) {
                // Crear o modificar una dieta
                EntidadDieta di = new EntidadDieta();
                di.setNombre(vista.txNombreD.getText());
                di.setPesoInicial(Double.parseDouble(vista.txPesoIni.getText()));
                di.setPesoFinal(Double.parseDouble(vista.txPesoFin.getText()));

                // Configurar fechas
                java.util.Date fechaInicio = vista.dcFechInicio.getDate();
                Instant instantInicio = fechaInicio.toInstant();
                LocalDate fechaInicial = instantInicio.atZone(ZoneId.systemDefault()).toLocalDate();
                di.setFechaInicial(fechaInicial);

                java.util.Date fechaFinal = vista.dcFechFinal.getDate();
                Instant instantFinal = fechaFinal.toInstant();
                LocalDate fechaFin = instantFinal.atZone(ZoneId.systemDefault()).toLocalDate();
                di.setFechaFinal(fechaFin);

                // Configurar el estado de la dieta (activo o inactivo)
                di.setEstado(vista.cbEstado.isSelected());

                DataDieta diet = new DataDieta();
                try {
                    if (idDieta < 0) {
                        // Guardar una nueva dieta
                        di.setPaciente(idPaciente);
                        EntidadDieta dietaGuardada = diet.crearDieta(di);
                        JOptionPane.showMessageDialog(null, "Dieta guardada exitosamente con ID: " + dietaGuardada.getIdDieta());
                        data.definirDietaUnica(idPaciente, dietaGuardada.getIdDieta());
                    } else {
                        // Modificar una dieta existente
                        di.setIdDieta(idDieta);
                        boolean dietaModificada = diet.modificarDieta(di);
                        if (dietaModificada) {
                            JOptionPane.showMessageDialog(null, "Dieta modificada exitosamente.");
                            if (vista.cbEstado.isSelected()) {
                                data.definirDietaUnica(idPaciente, idDieta);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al modificar la dieta en la base de datos.");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar o modificar la dieta en la base de datos: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "El ID del paciente no es válido.");
            }
            llenarComboDieta();

        }
        if (d.getSource() == vista.btSalir) {
            vista.dispose();
        }
        if (d.getSource() == vista.btEliminar) {
            String nombreDieta = vista.txNombreD.getText();

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
                vista.cbEstado.setBackground(Color.BLUE);
            } else {
                vista.cbEstado.setText("DEBAJA");
                vista.cbEstado.setEnabled(true);
                vista.cbEstado.setBackground(Color.RED);
            }
        }
        if (d.getSource() == vista.cboxListaDietas) {
            if (vista.cboxListaDietas.getItemCount() > 0) { //Si tiene elementos >0
                String selectedItem = (String) vista.cboxListaDietas.getSelectedItem();

                String[] partes = selectedItem.split("-");
                idDieta = Integer.parseInt(partes[0].trim());

                if (partes.length > 0) {
                    try {
                        int id = Integer.parseInt(partes[0].trim());
                        EntidadDieta dt = new EntidadDieta();
                        dt = data.obtenerDietaPorId(id);
                        vista.txNombreD.setText(dt.getNombre());
                        vista.dcFechInicio.setDate(Date.valueOf(dt.getFechaInicial()));
                        vista.dcFechFinal.setDate(Date.valueOf(dt.getFechaFinal()));
                        vista.txPesoIni.setText(dt.getPesoInicial() + "");
                        vista.txPesoFin.setText(dt.getPesoFinal() + "");
                        vista.cbEstado.setSelected(dt.isEstado());
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorDieta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

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

    private void llenarComboDieta() {
        List<EntidadDieta> dt = new ArrayList<>();
        try {
            dt = data.listarDietas();
            vista.cboxListaDietas.removeAllItems();
            for (EntidadDieta enti : dt) {
                if (enti.getPaciente() == idPaciente) {
                    String cadena = enti.getIdDieta() + "-" + enti.getNombre();
                    vista.cboxListaDietas.addItem(cadena);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al tratar de obtener una lista de dietas \n" + ex.getMessage());
        }
    }

    private void mostrarDieta(int id) {
        try {
            EntidadDieta dt = new EntidadDieta();
            dt = data.obtenerDietaPorId(id);
            vista.txDNI.setText(dt.getIdDieta() + "");
            vista.txNombreP.setText(dt.getPaciente() + "");
            vista.txNombreD.setText(dt.getNombre() + "");
            vista.dcFechInicio.setDateFormatString(dt.getFechaInicial() + "");
            vista.dcFechFinal.setDateFormatString(dt.getFechaFinal() + "");
            vista.txPesoIni.setText(dt.getPesoInicial() + "");
            vista.txPesoFin.setText(dt.getPesoFinal() + "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error no se puede consultar el ID " + id + " \n" + ex.getMessage());
        }
    }
}
