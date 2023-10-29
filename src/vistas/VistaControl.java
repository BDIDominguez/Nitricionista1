/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Image;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Dario
 */
public class VistaControl extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaControl
     */
    public VistaControl() {
        initComponents();
        colocarIconos();
        dcFecha.setDate(new Date());
        dcCita.setDate(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbPacientes = new javax.swing.JComboBox<>();
        btEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txPeso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txAltura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txCintura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txGasto = new javax.swing.JTextField();
        txIMC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dcCita = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txObs = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbControl = new javax.swing.JTable();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setTitle("Controles de pacientes");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 56, -1));

        cbPacientes.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jPanel1.add(cbPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 399, -1));

        btEliminar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btEliminar.setText("Eliminar");
        jPanel1.add(btEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, 156, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Paciente");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        dcFecha.setFocusable(false);
        dcFecha.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        dcFecha.setMinSelectableDate(new Date());
        jPanel1.add(dcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 150, 35));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Peso");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txPeso.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txPeso.setText("0,00");
        jPanel1.add(txPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 80, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kg/m2");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, 30));

        txAltura.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txAltura.setText("0,00");
        jPanel1.add(txAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 103, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cintura");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txCintura.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txCintura.setText("0,00");
        jPanel1.add(txCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 80, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gasto Energetico");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 143, -1));

        txGasto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txGasto.setText("0,00");
        txGasto.setToolTipText("Gasto Energetico de 0 a 100 unidades");
        jPanel1.add(txGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 103, -1));

        txIMC.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txIMC.setText("0,00");
        txIMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIMCActionPerformed(evt);
            }
        });
        jPanel1.add(txIMC, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 103, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Proxi. Cita");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 80, -1));

        dcCita.setFocusable(false);
        dcCita.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        dcCita.setMinSelectableDate(new Date());
        jPanel1.add(dcCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 150, 35));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Observaciones");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 130, -1));

        txObs.setColumns(20);
        txObs.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txObs.setRows(5);
        jScrollPane1.setViewportView(txObs);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 483, 350));

        tbControl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbControl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 0, 493, 650));

        btNuevo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btNuevo.setText("Nuevo");
        jPanel1.add(btNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 156, -1));

        btGuardar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btGuardar.setText("Guardar");
        jPanel1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, 156, -1));

        btSalir.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btSalir.setText("Salir");
        jPanel1.add(btSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 610, 156, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cm");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kgs");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, 30));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mtrs");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Altura");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, 30));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("IMC");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 35, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txIMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIMCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIMCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btGuardar;
    public javax.swing.JButton btNuevo;
    public javax.swing.JButton btSalir;
    public javax.swing.JComboBox<String> cbPacientes;
    public com.toedter.calendar.JDateChooser dcCita;
    public com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tbControl;
    public javax.swing.JTextField txAltura;
    public javax.swing.JTextField txCintura;
    public javax.swing.JTextField txGasto;
    public javax.swing.JTextField txIMC;
    public javax.swing.JTextArea txObs;
    public javax.swing.JTextField txPeso;
    // End of variables declaration//GEN-END:variables
private void colocarIconos(){
        int alto = 30;
        int ancho = 30; 
        
        btNuevo.setIcon(prepararIcono("nuevo.png",alto,ancho));
        btGuardar.setIcon(prepararIcono("salvado.png",alto,ancho));
        btEliminar.setIcon(prepararIcono("inactivo.png",alto,ancho));
        btSalir.setIcon(prepararIcono("salida3.png",alto,ancho));
        ImageIcon icono = new ImageIcon();
        icono = prepararIcono("IconoFormulario.jpg",20,20);
        this.setFrameIcon(icono);
    }
    
    private ImageIcon prepararIcono(String nombre, int alto, int ancho) { // Aplicacion que prepara los Iconos para ser puestos donde se quiera se pasa el nombre y el tamaño que quieres que tenga
        // Obtén la ruta relativa a la ubicación de la clase Controlador eso es la carpeta SRC del proyecto ese seria la carpeta de inicio
        ClassLoader directorio = getClass().getClassLoader();
        URL imagen = directorio.getResource("iconos/" + nombre); // Creamos la ruta al recurso en este caso el icono de lupa
        // Crea un ImageIcon utilizando la URL de la imagen
        ImageIcon icono = new ImageIcon(imagen); // creamos la Imagen Icono para asignarsela al contenerdor
        // Redimensionar el icono pasandolo a imagen con el nuevo tamaño y luego convirtiendolo en icono XD
        Image imagenRedimensionada = icono.getImage().getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenRedimensionada);
        return icono;
    }

}
