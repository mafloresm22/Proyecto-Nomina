/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Modelo;

/**
 *
 * @author WINDOWS
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
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

        btnTrabajadores = new javax.swing.JButton();
        btnContratos = new javax.swing.JButton();
        btnProcesarPagos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTrabajadores.setText("GESTIONAR TRABAJADORES");
        btnTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadoresActionPerformed(evt);
            }
        });
        getContentPane().add(btnTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 200, 60));

        btnContratos.setText("GESTIONAR CONTRATOS");
        btnContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContratosActionPerformed(evt);
            }
        });
        getContentPane().add(btnContratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 200, 60));

        btnProcesarPagos.setText("GESTIONAR PAGOS");
        btnProcesarPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarPagosActionPerformed(evt);
            }
        });
        getContentPane().add(btnProcesarPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 200, 60));

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadoresActionPerformed
        // TODO add your handling code here:
        Trabajadores objetoFormulario = new Trabajadores();
        objetoFormulario.setVisible(true);
    }//GEN-LAST:event_btnTrabajadoresActionPerformed

    private void btnContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContratosActionPerformed
        // TODO add your handling code here:
        HU02 objetoFormulario = new HU02();
        objetoFormulario.setVisible(true);
    }//GEN-LAST:event_btnContratosActionPerformed

    private void botones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botones1ActionPerformed
    
    }//GEN-LAST:event_botones1ActionPerformed

    private void btnProcesarPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarPagosActionPerformed
        HU03 objetoFormulario = new HU03();
        objetoFormulario.setVisible(true);
    }//GEN-LAST:event_btnProcesarPagosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContratos;
    private javax.swing.JButton btnProcesarPagos;
    private javax.swing.JButton btnTrabajadores;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
