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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
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

        //Capturando Text
        vista.txAltura.addFocusListener(this);
        vista.txPeso.addFocusListener(this);
        vista.txCintura.addFocusListener(this);
        vista.txGasto.addFocusListener(this);
        vista.txIMC.addFocusListener(this);
        vista.txPeso.addFocusListener(this);

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
            vista.txPeso.setText("0,00");
            vista.txAltura.setText("0,00");
            vista.txCintura.setText("0,00");
            vista.txIMC.setText("0,00");
            vista.txGasto.setText("0,00");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, 7);
            vista.dcCita.setDate(cal.getTime());
            vista.btEliminar.setEnabled(false);
            vista.btGuardar.setEnabled(true);
        }
        if (e.getSource() == vista.btGuardar) {
            EntidadControl co = new EntidadControl();
            co.setIdControl(idcontrol);
            co.setIdPaciente(extraerIdPaciente());
            co.setFecha(vista.dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            co.setPeso(Double.parseDouble(vista.txPeso.getText().replace(",", ".")));
            co.setAltura(Double.parseDouble(vista.txAltura.getText().replace(",", ".")));
            co.setCintura(Double.parseDouble(vista.txCintura.getText().replace(",", ".")));
            co.setGasenergetico(Double.parseDouble(vista.txGasto.getText().replace(",", ".")));
            co.setIMC(Double.parseDouble(vista.txIMC.getText().replace(",", ".")));
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
                    if (vRespuesta) {
                        JOptionPane.showMessageDialog(vista, "Cambios guardados con exito");
                        vista.btGuardar.setEnabled(false);
                        vista.btEliminar.setEnabled(true);
                        llenarTabla(extraerIdPaciente());
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo guardar los cambios!!");
                        vista.btGuardar.setEnabled(true);
                        vista.btEliminar.setEnabled(false);
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorControl.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al intentar modificar el control \n" + ex.getMessage());
                }
            }
        }

        if (e.getSource() == vista.btEliminar) {
            if (idcontrol > 0) {
                int vRespuesta = JOptionPane.showConfirmDialog(menu, "Seguro de Eliminar este Control?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (vRespuesta == 0) {
                    try {
                        boolean vresp = cData.eliminarControl(idcontrol);
                        if (vresp) {
                            JOptionPane.showMessageDialog(vista, "Se elimino el control correctamente");
                            llenarTabla(extraerIdPaciente());
                            limpiarFomulario();
                            vista.tbControl.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(vista, "No se pudo eliminar, se desconose la causa!");
                        }
                    } catch (SQLException ex) {
                        //Logger.getLogger(ControladorVistaControl.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(vista, "Error al intentar eliminar de la tabla controles \n" + ex.getMessage());
                    }
                }
            }
        }

        if (e.getSource() == vista.cbPacientes) {
            llenarTabla(extraerIdPaciente());
        }

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

        if (e.getSource() == vista.txPeso) {
            double valor = Double.parseDouble(vista.txPeso.getText().replace(",", "."));
            if (valor >= 0 && valor < 595) {
                vista.txPeso.setText(formatoDecimal(Double.parseDouble(vista.txPeso.getText().replace(",", "."))));
            } else {
                JOptionPane.showMessageDialog(vista, "El peso no es uno que se considera Valida!!!");
                vista.txPeso.setText("80,00");
                vista.txPeso.requestFocus();
            }
        }

        if (e.getSource() == vista.txAltura) {
            double valor = Double.parseDouble(vista.txAltura.getText().replace(",", "."));
            if (valor >= 0.50 && valor < 2.8) {
                vista.txAltura.setText(formatoDecimal(Double.parseDouble(vista.txAltura.getText().replace(",", "."))));
            } else {
                JOptionPane.showMessageDialog(vista, "La altura no es una que se considera Valida!!!");
                vista.txAltura.setText("1,60");
                vista.txAltura.requestFocus();
            }
        }
        if (e.getSource() == vista.txCintura) {
            double valor = Double.parseDouble(vista.txCintura.getText().replace(",", "."));
            if (valor >= 0 && valor < 100) {
                vista.txCintura.setText(formatoDecimal(Double.parseDouble(vista.txCintura.getText().replace(",", "."))));
            } else {
                JOptionPane.showMessageDialog(vista, "La cintura no es una que se considera Valida!!!");
                vista.txCintura.setText("35");
                vista.txCintura.requestFocus();
            }
        }
        if (e.getSource() == vista.txIMC) {
            double valor = Double.parseDouble(vista.txIMC.getText().replace(",", "."));
            if (valor >= 0 && valor < 175) {
                vista.txIMC.setText(formatoDecimal(Double.parseDouble(vista.txIMC.getText().replace(",", "."))));
            } else {
                JOptionPane.showMessageDialog(vista, "El IMC no es uno que se considera Valida!!!");
                vista.txIMC.setText("25");
                vista.txIMC.requestFocus();
            }
        }
        if (e.getSource() == vista.txGasto) {
            double valor = Double.parseDouble(vista.txGasto.getText().replace(",", "."));
            if (valor >= 0 && valor < 100) {
                vista.txGasto.setText(formatoDecimal(Double.parseDouble(vista.txGasto.getText().replace(",", "."))));
            } else {
                JOptionPane.showMessageDialog(vista, "El gasto Energetico no es uno que se considera Valido!!!");
                vista.txGasto.setText("35");
                vista.txGasto.requestFocus();
            }
        }

        if (e.getSource() == vista.txAltura || e.getSource() == vista.txPeso) {
            calcularIMC();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.tbControl.getSelectedRow();
            if (fila != -1) {
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
            String cadena = paciente.getNombre() + " - " + paciente.getDni() + " - " + paciente.getIdpaciente();
            vista.cbPacientes.addItem(cadena);
        }
        AutoCompleteDecorator.decorate(vista.cbPacientes); // esta unica linia usa la libreria swingx-all-1.6.4.jar para generar el auto completado del ComboBox
    }

    private int extraerIdPaciente() {
        int id = -1;
        try {
            String combobox = vista.cbPacientes.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[2].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }

    private void mostrarControl() {
        try {
            EntidadControl co = new EntidadControl();
            co = cData.ControlxID(idcontrol);
            idcontrol = co.getIdControl();
            vista.dcFecha.setDate(Date.from(co.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vista.txPeso.setText(formatoDecimal(co.getPeso()));
            vista.txAltura.setText(formatoDecimal(co.getAltura()));
            vista.txCintura.setText(formatoDecimal(co.getCintura()));
            vista.txIMC.setText(formatoDecimal(co.getIMC()));
            vista.txGasto.setText(formatoDecimal(co.getGasenergetico()));
            vista.dcCita.setDate(Date.from(co.getProximacita().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vista.txObs.setText(co.getObs());
            vista.btGuardar.setEnabled(true);
            vista.btEliminar.setEnabled(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "no se puede consultar datos del Control " + idcontrol + "\n" + ex.getMessage());
        }
    }

    private void calcularIMC() {
        double altura = Double.parseDouble(vista.txAltura.getText().replace(",", "."));
        double peso = Double.parseDouble(vista.txPeso.getText().replace(",", "."));
        if (altura > 0 && peso > 0) {
            double imc = peso / (altura * altura);
            vista.txIMC.setText(formatoDecimal(imc));
        }
    }

    private String formatoDecimal(double valor) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        String numeroFormateado = formato.format(valor);
        return numeroFormateado;
    }

    private double convertirTextDouble(String cadena) {
        String valor = cadena.replace(",", ".");
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error al intentar formatear el string a Double!!! \n" + e.getMessage());
            return 0.0;
        }
    }

    private void limpiarFomulario() {
        idcontrol = -1;
        vista.dcFecha.setDate(new Date());
        vista.txPeso.setText("0,00");
        vista.txAltura.setText("0,00");
        vista.txCintura.setText("0,00");
        vista.txIMC.setText("0,00");
        vista.txGasto.setText("0,00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 7);
        vista.dcCita.setDate(cal.getTime());
        vista.btEliminar.setEnabled(false);
        vista.btGuardar.setEnabled(true);
    }

} //Fin Clase
