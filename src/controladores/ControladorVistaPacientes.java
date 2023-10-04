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
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vistas.VistaPacientes;
import vistas.VistaPantallaPrincipal;

/**
 * @author Dario
 */
public class ControladorVistaPacientes implements ActionListener, FocusListener, ListSelectionListener {

    private VistaPantallaPrincipal menu;
    private VistaPacientes vista;
    private DataPaciente data;
    MyModelo modelo = new MyModelo();

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

        //check
        vista.chEstado.addActionListener(this);

        //Tabla
        vista.tbPacientes.getSelectionModel().addListSelectionListener(this);

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
        if (e.getSource() == vista.btNuevo) {
            vista.txID.setText("-1");
            vista.txDNI.setText("");
            vista.txNombre.setText("");
            vista.txDomicilio.setText("");
            vista.txTelefono.setText("");
            vista.chEstado.setSelected(true);
            vista.btNuevo.setEnabled(false);
            vista.btGuardar.setEnabled(true);
            vista.txDNI.requestFocus();
        }
        if (e.getSource() == vista.btGuardar) {
            EntidadPaciente p = new EntidadPaciente();
            p.setIdpaciente(Integer.parseInt(vista.txID.getText()));
            p.setDni(Integer.parseInt(vista.txDNI.getText()));
            p.setNombre(vista.txNombre.getText());
            p.setDomicilio(vista.txDomicilio.getText());
            p.setTelefono(vista.txTelefono.getText());
            p.setEstado(vista.chEstado.isSelected());
            if (p.getIdpaciente() == -1) { // Crear nuevo Paciente
                try {
                    EntidadPaciente ide = data.crearPaciente(p);
                    vista.txID.setText(ide.getIdpaciente() + "");
                    vista.btGuardar.setEnabled(false);
                    vista.btEliminar.setEnabled(false);
                    JOptionPane.showMessageDialog(vista, "Paciente creado con exito");
                    llenarTabla();
                } catch (SQLException ex) {
                    // Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al tratar de crear el Paciente \n" + ex.getMessage());
                }
            } else {
                try {
                    boolean vRespuesta = data.modificarPaciente(p);
                    if (vRespuesta) {
                        JOptionPane.showMessageDialog(vista, "Se guardaron los cambios Correctamente!");
                        vista.btGuardar.setEnabled(true);
                        vista.btEliminar.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Tenemos algun error, no controlado!!");
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al tratar de modificar el Paciente \n" + ex.getMessage());
                }
            }
            llenarTabla();
        }

        if (e.getSource() == vista.btEliminar) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro de dar de Baja al Paciente " + vista.txNombre.getText(), "Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    boolean vRespuesta = data.eliminarPaciente(Integer.parseInt(vista.txID.getText()));
                    mostrarPaciente(Integer.parseInt(vista.txID.getText()));
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al tratar de dar de baja al Paciente \n" + ex.getMessage());
                }
            }
        }
        if (e.getSource() == vista.chEstado) {
            if (vista.chEstado.isSelected()) {
                vista.chEstado.setText("ACTIVO");
            } else {
                vista.chEstado.setText("DE BAJA");
                vista.btEliminar.setEnabled(false);
            }
        }
        if (e.getSource() == vista.btSalir) {
            vista.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == vista.txDNI) {
            if (vista.txID.getText().equals("-1")) {
                if (vista.txDNI.getText().length() >= 7) {
                    try {
                        EntidadPaciente pa = new EntidadPaciente();
                        pa = data.pacienteDNI(Integer.parseInt(vista.txDNI.getText()));
                        if (pa.getDni() != 0) {
                            JOptionPane.showMessageDialog(vista, "Ese DNI ya existe!");
                            vista.txDNI.requestFocus();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(vista, "Error al tratar buscar al Paciente por DNI \n" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Ese Nro de DNI no es Valido!");
                    vista.txDNI.requestFocus();
                }
            }
        }
    }

    private void modelarTabla() {
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

    private void llenarTabla() {
        List<EntidadPaciente> pa = new ArrayList<>();
        try {
            pa = data.listarPacientes();
            modelo.setRowCount(0);
            for (EntidadPaciente enti : pa) {
                modelo.addRow(new Object[]{enti.getIdpaciente(), enti.getDni(), enti.getNombre(), enti.getTelefono()});
            }
            vista.tbPacientes.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "error al tratar de obtener una lista de pacientes \n" + ex.getMessage());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.tbPacientes.getSelectedRow();
            if (fila != -1) {
                int idPaciente = (int) vista.tbPacientes.getValueAt(fila, 0);  // Obtenemos el ID de la fila seleccionada
                mostrarPaciente(idPaciente);
            }
        }
    }

    private void mostrarPaciente(int id) {
        try {
            EntidadPaciente pa = new EntidadPaciente();
            pa = data.pacienteID(id);
            vista.txID.setText(pa.getIdpaciente() + "");
            vista.txDNI.setText(pa.getDni() + "");
            vista.txNombre.setText(pa.getNombre());
            vista.txDomicilio.setText(pa.getDomicilio());
            vista.txTelefono.setText(pa.getTelefono());
            vista.chEstado.setSelected(pa.isEstado());
            if (pa.isEstado()) {
                vista.chEstado.setText("ACTIVO");
                vista.btEliminar.setEnabled(true);
            } else {
                vista.chEstado.setText("DE BAJA");
                vista.btEliminar.setEnabled(false);
            }
            vista.btGuardar.setEnabled(true);

        } catch (SQLException ex) {
            //Logger.getLogger(ControladorVistaPacientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "Error no se puede consultar el ID " + id + " \n" + ex.getMessage());
        }

    }

    private class MyModelo extends DefaultTableModel {

        //para habilitar la modificacion de la columna 3( indice 2) en jTableCargaNotas
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
