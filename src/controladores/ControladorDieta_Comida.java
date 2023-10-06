package controladores;

/*** @author DIEGO G. */

import datas.DataDieta_Comida;
import entidades.EntidadDieta_Comida;
import vistas.VistaDieta_Comida;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ControladorDieta_Comida {

    private final VistaDieta_Comida vista;
    private final DataDieta_Comida dataDietaComida;

//    public ControladorDieta_Comida(VistaDieta_Comida vista) {
//        this.vista = vista;
//        this.dataDietaComida = new DataDieta_Comida();
//        this.vista.getBtEditar().addActionListener(e -> editarDietaComida());
//        this.vista.getBtEliminar().addActionListener(e -> eliminarDietaComida());
//        this.vista.getBtMostrar().addActionListener(e -> mostrarDietaComida());
//        this.vista.getBtListar().addActionListener(e -> listarDietasComida());
//    }
//
//    private void editarDietaComida() {
//        int idDietaComida = Integer.parseInt(vista.getTxtIdDietaComida().getText());
//        int idDieta = Integer.parseInt(vista.getTxtIdDieta().getText());
//        int idComida = Integer.parseInt(vista.getTxtIdComida().getText());
//        double porcion = Double.parseDouble(vista.getTxtPorcion().getText());
//        EntidadDieta_Comida.HorarioComida horario = (EntidadDieta_Comida.HorarioComida) vista.getCbHorario().getSelectedItem();
//
//        EntidadDieta_Comida dietaComida = new EntidadDieta_Comida(idDietaComida, idDieta, idComida, porcion, horario);
//
//        try {
//            dataDietaComida.editarDietaComida(dietaComida);
//            vista.mostrarMensaje("Relación Dieta-Comida editada correctamente.");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vista, "Error al editar la relación Dieta-Comida: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void eliminarDietaComida() {
//        int idDietaComida = Integer.parseInt(vista.getTxtIdDietaComida().getText());
//
//        try {
//            dataDietaComida.eliminarDietaComida(idDietaComida);
//            vista.mostrarMensaje("Relación Dieta-Comida eliminada correctamente.");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vista, "Error al eliminar la relación Dieta-Comida: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void mostrarDietaComida() {
//        int idDietaComida = Integer.parseInt(vista.getTxtIdDietaComida().getText());
//
//        try {
//            EntidadDieta_Comida dietaComida = dataDietaComida.obtenerDietaComidaPorId(idDietaComida);
//
//            if (dietaComida != null) {
//                vista.getTxtIdDieta().setText(String.valueOf(dietaComida.getIdDieta()));
//                vista.getTxtIdComida().setText(String.valueOf(dietaComida.getIdComida()));
//                vista.getTxtPorcion().setText(String.valueOf(dietaComida.getPorcion()));
//                vista.getCbHorario().setSelectedItem(dietaComida.getHorario());
//            } else {
//                vista.mostrarMensaje("La relación Dieta-Comida no se encontró en la base de datos.");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vista, "Error al obtener relación Dieta-Comida: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void listarDietasComida() {
//        int idDieta = Integer.parseInt(vista.getTxtIdDieta().getText());
//
//        try {
//            List<EntidadDieta_Comida> dietasComida = dataDietaComida.obtenerDietasComidaPorDieta(idDieta);
//
//            DefaultTableModel model = (DefaultTableModel) vista.getTbDietasComida().getModel();
//            model.setRowCount(0);
//
//            for (EntidadDieta_Comida dietaComida : dietasComida) {
//                Object[] rowData = {
//                    dietaComida.getIdDietaComida(),
//                    dietaComida.getIdDieta(),
//                    dietaComida.getIdComida(),
//                    dietaComida.getPorcion(),
//                    dietaComida.getHorario()
//                };
//                model.addRow(rowData);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vista, "Error al obtener las relaciones Dieta-Comida: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
