/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author monta
 */
public class frmproveedore extends javax.swing.JPanel {

    /**
     * Creates new form frmproveedore
     */
    public frmproveedore() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbMaterias = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        txtIdentificacion = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtFormadepago = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnBusca = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnactualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        btnIngresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbforma = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMaterias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tbMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Direccion", "Identificacion Fiscal", "FormaDePago"
            }
        ));
        tbMaterias.setGridColor(new java.awt.Color(255, 255, 255));
        tbMaterias.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tbMaterias.setRowHeight(25);
        tbMaterias.setSelectionBackground(new java.awt.Color(0, 112, 224));
        jScrollPane1.setViewportView(tbMaterias);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, 230));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Datos Guardados");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 150, 30));

        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(204, 204, 204));
        txtNombre.setText("Ingrese Nombre del Proveedor");
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreMousePressed(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 280, 40));

        txtDireccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(204, 204, 204));
        txtDireccion.setText("Ingrese Direccion");
        txtDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtDireccionMousePressed(evt);
            }
        });
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 280, 40));

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-broom-with-a-lot-of-dust-40.png"))); // NOI18N
        btnLimpiar.setBorder(null);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 530, 40, 40));

        txtIdentificacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdentificacion.setForeground(new java.awt.Color(204, 204, 204));
        txtIdentificacion.setText("Ingrese Identificacion Fiscal");
        txtIdentificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIdentificacionMousePressed(evt);
            }
        });
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });
        add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 220, 40));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-eliminar-40.png"))); // NOI18N
        btnEliminar.setBorder(null);
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 30, 40));
        add(txtFormadepago, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 130, 30));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-editar-40.png"))); // NOI18N
        btnUpdate.setBorder(null);
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, -1, -1));

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_search_30px.png"))); // NOI18N
        add(btnBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 50, 30));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscar.setText("Buscar Proveedores");
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtBuscarMousePressed(evt);
            }
        });
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 270, 40));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Direccion:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 259, 77, 23));

        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-actualizar.gif"))); // NOI18N
        btnactualizar.setBorder(null);
        add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 530, 40, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Forma de pago Preferida:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, 30));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Identificacion Fiscal:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 10, 500));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1180, 24));

        btnIngresar.setBackground(new java.awt.Color(0, 112, 224));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-guardar-94.png"))); // NOI18N
        btnIngresar.setBorder(null);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 75, 159));
        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-proveedor-40.png"))); // NOI18N
        jLabel4.setText("CREAR PROVEEDORES");
        jLabel4.setToolTipText("");
        jLabel4.setOpaque(true);
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 110));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 70, 30));

        cmbforma.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        cmbforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbformaActionPerformed(evt);
            }
        });
        add(cmbforma, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 130, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
       
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void cmbformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbformaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbformaActionPerformed

    private void txtBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMousePressed
         if(this.txtBuscar.getText().equals("Buscar Proveedores")){
            this.txtBuscar.setText("");
            this.txtBuscar.setForeground(Color.black);
        }
        if(String.valueOf(this.txtNombre.getText()).isEmpty()){
            this.txtNombre.setText("Ingrese Nombre del Proveedor");
            this.txtNombre.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtDireccion.getText()).isEmpty()){
            this.txtDireccion.setText("Ingrese Direccion");
            this.txtDireccion.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtIdentificacion.getText()).isEmpty()){
            this.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
            this.txtIdentificacion.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtBuscarMousePressed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMousePressed
         if(this.txtNombre.getText().equals("Ingrese Nombre del Proveedor")){
            this.txtNombre.setText("");
            this.txtNombre.setForeground(Color.black);
        }

        if(String.valueOf(this.txtBuscar.getText()).isEmpty()){
            this.txtBuscar.setText("Buscar Proveedores");
            this.txtBuscar.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtDireccion.getText()).isEmpty()){
            this.txtDireccion.setText("Ingrese Direccion");
            this.txtDireccion.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtIdentificacion.getText()).isEmpty()){
            this.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
            this.txtIdentificacion.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtNombreMousePressed

    private void txtDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDireccionMousePressed
       if(this.txtDireccion.getText().equals("Ingrese Direccion")){
            this.txtDireccion.setText("");
            this.txtDireccion.setForeground(Color.black);
        }

        if(String.valueOf(this.txtBuscar.getText()).isEmpty()){
            this.txtBuscar.setText("Buscar Proveedores");
            this.txtBuscar.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtNombre.getText()).isEmpty()){
            this.txtNombre.setText("Ingrese Nombre del Proveedor");
            this.txtNombre.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtIdentificacion.getText()).isEmpty()){
            this.txtIdentificacion.setText("Ingrese Identificacion Fiscal");
            this.txtIdentificacion.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtDireccionMousePressed

    private void txtIdentificacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdentificacionMousePressed
      if(this.txtIdentificacion.getText().equals("Ingrese Identificacion Fiscal")){
            this.txtIdentificacion.setText("");
            this.txtIdentificacion.setForeground(Color.black);
        }

        if(String.valueOf(this.txtBuscar.getText()).isEmpty()){
            this.txtBuscar.setText("Buscar Proveedores");
            this.txtBuscar.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtNombre.getText()).isEmpty()){
            this.txtNombre.setText("Ingrese Nombre del Proveedor");
            this.txtNombre.setForeground(new Color(204,204,204));
        }
        if(String.valueOf(this.txtDireccion.getText()).isEmpty()){
            this.txtDireccion.setText("Ingrese Direccion");
            this.txtDireccion.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtIdentificacionMousePressed

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9')   evt.consume();
    }//GEN-LAST:event_txtIdentificacionKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBusca;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnIngresar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnUpdate;
    public javax.swing.JButton btnactualizar;
    public javax.swing.JComboBox<String> cmbforma;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTable tbMaterias;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtFormadepago;
    public javax.swing.JTextField txtID;
    public javax.swing.JTextField txtIdentificacion;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
