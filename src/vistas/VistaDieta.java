/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author Matias
 */
public class VistaDieta extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaDieta
     */
    public VistaDieta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlNombre = new javax.swing.JLabel();
        jlDNI = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jrbEstado = new javax.swing.JRadioButton();
        jtfDNI = new javax.swing.JTextField();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        jlPaciente = new javax.swing.JLabel();
        jcbPaciente = new javax.swing.JCheckBox();
        jlDieta = new javax.swing.JLabel();
        jcbDieta = new javax.swing.JCheckBox();

        setClosable(true);

        jlNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlNombre.setText("Nombre:");

        jlDNI.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlDNI.setText("DNI:");

        jlEstado.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlEstado.setText("Estado:");

        jtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNombreActionPerformed(evt);
            }
        });

        jtfDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDNIActionPerformed(evt);
            }
        });

        btNuevo.setText("Nuevo");

        btGuardar.setText("Guardar");

        btEliminar.setText("Eliminar");

        btSalir.setText("Salir");

        jlPaciente.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlPaciente.setText("Paciente");

        jcbPaciente.setText("jCheckBox1");

        jlDieta.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlDieta.setText("Dieta");

        jcbDieta.setText("jCheckBox1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlDNI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfDNI))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlNombre)
                                .addGap(10, 10, 10)
                                .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbPaciente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlDieta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbDieta))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btNuevo)
                            .addComponent(btEliminar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNombre)
                            .addComponent(jlPaciente)
                            .addComponent(jcbPaciente))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDNI)
                            .addComponent(jtfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlDieta)
                            .addComponent(jcbDieta))
                        .addGap(37, 37, 37)
                        .addComponent(jlEstado))
                    .addComponent(jrbEstado))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNombreActionPerformed

    private void jtfDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDNIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btGuardar;
    public javax.swing.JButton btNuevo;
    public javax.swing.JButton btSalir;
    public javax.swing.JCheckBox jcbDieta;
    public javax.swing.JCheckBox jcbPaciente;
    public javax.swing.JLabel jlDNI;
    public javax.swing.JLabel jlDieta;
    public javax.swing.JLabel jlEstado;
    public javax.swing.JLabel jlNombre;
    public javax.swing.JLabel jlPaciente;
    public javax.swing.JRadioButton jrbEstado;
    public javax.swing.JTextField jtNombre;
    public javax.swing.JTextField jtfDNI;
    // End of variables declaration//GEN-END:variables
}
