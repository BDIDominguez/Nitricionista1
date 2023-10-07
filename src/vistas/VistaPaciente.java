/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 * @author Dario
 */
public class VistaPaciente extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaPacientes
     */
    public VistaPaciente() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txID = new utilidades.MiCampoTexto(1);
        ;
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txNombre = new utilidades.MiCampoTexto(2);
        jLabel4 = new javax.swing.JLabel();
        txDomicilio = new utilidades.MiCampoTexto(2);
        txTelefono = new utilidades.MiCampoTexto(4);
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        chEstado = new javax.swing.JCheckBox();
        txDNI = new utilidades.MiCampoTextoFormateado(1);
        ;
        lbFondo = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Paciente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, -1, -1));

        txID.setEditable(false);
        txID.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txID.setText("0");
        jPanel1.add(txID, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 13, 154, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DNI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 86, -1, -1));

        txNombre.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel1.add(txNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 82, 316, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Domicilio");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, -1, -1));

        txDomicilio.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel1.add(txDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 117, 316, -1));

        txTelefono.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel1.add(txTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 152, 204, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Telefono");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 156, -1, -1));

        tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbPacientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 0, 655, 463));

        btNuevo.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btNuevo.setText("Nuevo");
        jPanel1.add(btNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        btGuardar.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btGuardar.setText("Guardar");
        jPanel1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        btEliminar.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btEliminar.setText("Eliminar");
        jPanel1.add(btEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        btSalir.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btSalir.setText("Salir");
        jPanel1.add(btSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 330, -1, -1));

        chEstado.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        chEstado.setText("Activo");
        jPanel1.add(chEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 199, 200, -1));

        txDNI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        txDNI.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jPanel1.add(txDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 48, 154, -1));

        lbFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bkg4.jpeg"))); // NOI18N
        jPanel1.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 400, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btGuardar;
    public javax.swing.JButton btNuevo;
    public javax.swing.JButton btSalir;
    public javax.swing.JCheckBox chEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFondo;
    public javax.swing.JTable tbPacientes;
    public javax.swing.JFormattedTextField txDNI;
    public javax.swing.JTextField txDomicilio;
    public javax.swing.JTextField txID;
    public javax.swing.JTextField txNombre;
    public javax.swing.JTextField txTelefono;
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