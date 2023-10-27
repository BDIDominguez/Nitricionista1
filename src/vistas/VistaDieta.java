/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dario
 */
public class VistaDieta extends javax.swing.JInternalFrame {

    /**
     * Creates new form miDieta
     */
    public VistaDieta() {
        initComponents();
        colocarIconos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        txDNI = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txNombreP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txNombreD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txPesoIni = new javax.swing.JTextField();
        cbEstado = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        cboxListaDietas = new javax.swing.JComboBox<>();
        btBuscar = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txPesoFin = new javax.swing.JTextField();
        dcFechFinal = new com.toedter.calendar.JDateChooser();
        dcFechInicio = new com.toedter.calendar.JDateChooser();

        setClosable(true);

        jDesktopPane1.setBackground(new java.awt.Color(51, 51, 255));
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txDNI.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txDNI.setText("DNI Paciente");
        jDesktopPane1.add(txDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre del Paciente");
        jDesktopPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 170, -1));

        txNombreP.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txNombreP.setText("Buscar por nombre");
        jDesktopPane1.add(txNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 190, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DNI del Paciente");
        jDesktopPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Listas de Dietas");
        jDesktopPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 130, -1));

        txNombreD.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txNombreD.setText("Nombre Dieta");
        jDesktopPane1.add(txNombreD, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 190, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de Inicio");
        jDesktopPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha de Finalizacion");
        jDesktopPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 170, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Peso Inicial");
        jDesktopPane1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 100, -1));

        txPesoIni.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txPesoIni.setText("Peso Inicial");
        jDesktopPane1.add(txPesoIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 190, -1));

        cbEstado.setBackground(new java.awt.Color(51, 51, 255));
        cbEstado.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cbEstado.setText("ACTIVO");
        jDesktopPane1.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre de la Dieta");
        jDesktopPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 150, -1));

        cboxListaDietas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        cboxListaDietas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dietas del Pacientes Seleccionado" }));
        jDesktopPane1.add(cboxListaDietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 270, -1));

        btBuscar.setText("Buscar DNI");
        jDesktopPane1.add(btBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        btNuevo.setText("Nueva");
        jDesktopPane1.add(btNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        btGuardar.setText("Guardar");
        jDesktopPane1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, -1));

        btEliminar.setText("Eliminar");
        jDesktopPane1.add(btEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, -1));

        btSalir.setText("Salir");
        jDesktopPane1.add(btSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Peso Actual/Final");
        jDesktopPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 140, -1));

        txPesoFin.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txPesoFin.setText("Paso Actual/Final");
        jDesktopPane1.add(txPesoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 190, -1));
        jDesktopPane1.add(dcFechFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 190, -1));
        jDesktopPane1.add(dcFechInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBuscar;
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btGuardar;
    public javax.swing.JButton btNuevo;
    public javax.swing.JButton btSalir;
    public javax.swing.JCheckBox cbEstado;
    public javax.swing.JComboBox<String> cboxListaDietas;
    public com.toedter.calendar.JDateChooser dcFechFinal;
    public com.toedter.calendar.JDateChooser dcFechInicio;
    private javax.swing.JDesktopPane jDesktopPane1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JTextField txDNI;
    public javax.swing.JTextField txNombreD;
    public javax.swing.JTextField txNombreP;
    public javax.swing.JTextField txPesoFin;
    public javax.swing.JTextField txPesoIni;
    // End of variables declaration//GEN-END:variables
private void colocarIconos() {
        int alto = 30;
        int ancho = 30;

        btNuevo.setIcon(prepararIcono("nuevo.png", alto, ancho));
        btGuardar.setIcon(prepararIcono("salvado.png", alto, ancho));
        btEliminar.setIcon(prepararIcono("inactivo.png", alto, ancho));
        btSalir.setIcon(prepararIcono("salida3.png", alto, ancho));
        btBuscar.setIcon(prepararIcono("Lupa-buscar.png",alto, ancho));
        ImageIcon icono = new ImageIcon();
        icono = prepararIcono("IconoFormulario.jpg",20,20);
        this.setFrameIcon(icono);
    }

    private ImageIcon prepararIcono(String nombre, int alto, int ancho) {
        ClassLoader directorio = getClass().getClassLoader();
        URL imagen = directorio.getResource("iconos/" + nombre);
        ImageIcon icono = new ImageIcon(imagen);
        Image imagenRedimensionada = icono.getImage().getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        return icono;
    }
}
