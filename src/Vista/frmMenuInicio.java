/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author monta
 */
public class frmMenuInicio extends javax.swing.JFrame {

    /**
     * Creates new form frmMenuInicio
     */
    public frmMenuInicio() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
    }
     
     private void execute() {
        ImageIcon iconManteni = new ImageIcon(getClass().getResource("/imagenes/icons8_home_24px_1.png"));
        ImageIcon iconClient = new ImageIcon(getClass().getResource("/imagenes/icons8_client_management_24px.png"));
        ImageIcon iconGastos = new ImageIcon(getClass().getResource("/imagenes/icons8_money_pound_26px.png"));
        ImageIcon iconInventario = new ImageIcon(getClass().getResource("/imagenes/icons8_inventory_flow_30px.png"));
        ImageIcon iconProvee = new ImageIcon(getClass().getResource("/imagenes/icons8_supplier_24px.png"));
        ImageIcon iconCuenBan = new ImageIcon(getClass().getResource("/imagenes/icons8_bank_24px.png"));
        ImageIcon iconAdmin = new ImageIcon(getClass().getResource("/imagenes/icons8_microsoft_admin_24px.png"));
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/imagenes/icons8_user_menu_female_24px.png"));
        ImageIcon iconReport = new ImageIcon(getClass().getResource("/imagenes/icons8_report_file_32px.png"));
        ImageIcon iconCuenCobra = new ImageIcon(getClass().getResource("/imagenes/icons8_billing_24px.png"));
        ImageIcon iconCuenPaga = new ImageIcon(getClass().getResource("/imagenes/icons8_pay_30px.png"));
        ImageIcon iconFactura = new ImageIcon(getClass().getResource("/imagenes/icons8_bill_30px.png"));
        ImageIcon iconSalir = new ImageIcon(getClass().getResource("/imagenes/icons8_exit_30px.png"));
        //  create submenu mantenimiento
        MenuItem subMclient = new MenuItem(new Color(0,109,230),iconClient, "Cliente" , new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ae) {
                panelBody.add(new frmCliente());
                panelBody.repaint();
                panelBody.revalidate();
                
              
               
            }
        });
        MenuItem subMGastos = new MenuItem(new Color(0,109,230),iconGastos, "Gastos", new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ae) {
                panelBody.add(new frmGastos());
                panelBody.repaint();
                panelBody.revalidate();
                
              
               
            }
        });
        MenuItem subMinventa = new MenuItem(new Color(0,109,230),iconInventario, "Inventario", null);
        MenuItem subMprove = new MenuItem(new Color(0,109,230),iconProvee, "Proveedores", null);
        MenuItem subMcuentaban = new MenuItem(new Color(0,109,230),iconCuenBan, "Cuentas Bancarias", null);
        //  create submenu Administracion

         MenuItem subAusuario = new MenuItem(new Color(0,109,230),iconUser, "Usuarios", null);
        

        //  create submenu Reportes 
        MenuItem subRcuentascobrar = new MenuItem(new Color(0,109,230),iconCuenCobra, "Cuentas por Cobrar", null);
        MenuItem subRcuentaspagar = new MenuItem(new Color(0,109,230),iconCuenPaga, "Cuentas por Pagar", null);
        MenuItem subRfactura = new MenuItem(new Color(0,109,230),iconFactura, "Factura", null);
       
        // Create Menus
        MenuItem MenuManteni = new MenuItem(new Color(0,75,159),iconManteni, "Mantenimiento", null, subMclient, 
                subMGastos, subMinventa, subMprove,subMcuentaban);
        MenuItem MenuAdmin = new MenuItem(new Color(0,75,159),iconAdmin, "Administración", null, subAusuario);
        MenuItem MenuReport = new MenuItem(new Color(0,75,159),iconReport, "Reportes", null, subRcuentascobrar, 
                subRcuentaspagar, subRfactura);
        MenuItem Salir = new MenuItem(new Color(0,75,159),iconSalir, "Salir", new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent  ae) {
                 setBackground(new Color(0,109,230));
                JOptionPane.showMessageDialog(rootPane, "Gracias por Usar el Sistema", "Informacion", HEIGHT); 
                System.exit(0);
               
               
            }
        });
        addMenu(MenuManteni, MenuAdmin, MenuReport, Salir);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(0, 75, 159));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Sistema Contable");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_account_24px.png"))); // NOI18N

        jSeparator16.setPreferredSize(new java.awt.Dimension(50, 5));

        jLabel29.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("User");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(0, 75, 159));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
