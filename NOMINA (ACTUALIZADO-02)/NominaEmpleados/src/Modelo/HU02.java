package Modelo;

import Modelo.Trabajador;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modelo.ContratoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
public class HU02 extends javax.swing.JFrame {
 
    DefaultTableModel modelo;
    ContratoDAO contratoDAO = new ContratoDAO();
        
    public HU02() {
        initComponents();
        ListarContratos();
        DeselectionEdit();
        btn_nuevo.setEnabled(false);
        btn_editar.setEnabled(false);
    }
void selectionEdit(){
    btn_nuevo.setEnabled(false);
    btn_editar.setEnabled(true);
}
void DeselectionEdit(){
    btn_nuevo.setEnabled(false);
    btn_editar.setEnabled(false);
    tabla1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
}
    void limpiarCampos() {
     txtBuscarApellidooNombre.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscarApellidooNombre = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        cb_contrato = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        botones1 = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        cb_contrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Con Contrato", "Sin Contrato" }));
        cb_contrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_contratoActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "idTrabajador", "Nombres"
            }
        ));
        tabla1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        tabla1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        botones1.setText("Regresar");
        botones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botones1ActionPerformed(evt);
            }
        });

        btn_nuevo.setText("NUEVO");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_editar.setText("EDITAR");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar trabajador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtBuscarApellidooNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnbuscar)
                        .addGap(18, 18, 18)
                        .addComponent(cb_contrato, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(botones1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11))
                                .addComponent(btn_nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_editar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarApellidooNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbuscar)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(cb_contrato, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(botones1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 840, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_contratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_contratoActionPerformed
if(txtBuscarApellidooNombre.getText().isEmpty()){
    ListarContratos();
    DeselectionEdit();
}
else    {
    BuscarContratos();
}
    }//GEN-LAST:event_cb_contratoActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        DeselectionEdit();
    }//GEN-LAST:event_formMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
     DeselectionEdit();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        
    }//GEN-LAST:event_formMouseEntered

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void tabla1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla1KeyReleased

    }//GEN-LAST:event_tabla1KeyReleased

    private void tabla1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla1KeyPressed

    }//GEN-LAST:event_tabla1KeyPressed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked

    int filaSeleccionada = tabla1.getSelectedRow();
    if (filaSeleccionada != -1) {  // Si hay una fila seleccionada
        if (cb_contrato.getSelectedIndex() == 0) {  // "Con Contrato"
            btn_editar.setEnabled(true);
            btn_nuevo.setEnabled(false);
        } else {  // "Sin Contrato"
            btn_editar.setEnabled(false);
            btn_nuevo.setEnabled(true);
        }
    } else {
        DeselectionEdit();  // Si no hay fila seleccionada, deshabilitar botones
    }
    }//GEN-LAST:event_tabla1MouseClicked

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        
        if(txtBuscarApellidooNombre.getText().isEmpty()){
        ListarContratos();
        }
        else    {
        BuscarContratos();
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtBuscarApellidooNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
              // TODO add your handling code here:
    if(txtBuscarApellidooNombre.getText().isEmpty()){
    ListarContratos();
    }
    else    {
        BuscarContratos();
    }                                            

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void botones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botones1ActionPerformed
    dispose();    
    }//GEN-LAST:event_botones1ActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        // Obtener el trabajador seleccionado en HU02
        modelo.getValueAt(tabla1.getSelectedRow(), tabla1.getSelectedColumn());
        int trabajadorSeleccionado = Integer.parseInt(modelo.getValueAt(tabla1.getSelectedRow(), 0)+"");
        System.err.println(modelo.getValueAt(tabla1.getSelectedRow(), 0));

        CrearContrato crearContrato = null;
        try {
            crearContrato = new CrearContrato(trabajadorSeleccionado);
        } catch (SQLException ex) {
            Logger.getLogger(HU02.class.getName()).log(Level.SEVERE, null, ex);
        }
        crearContrato.setVisible(true);         
        dispose();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        modelo.getValueAt(tabla1.getSelectedRow(), tabla1.getSelectedColumn());
        int trabajadorSeleccionado = Integer.parseInt(modelo.getValueAt(tabla1.getSelectedRow(), 0)+"");
        System.err.println(modelo.getValueAt(tabla1.getSelectedRow(), 0));

        EditarContrato editarContrato = new EditarContrato(trabajadorSeleccionado);
        editarContrato.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_editarActionPerformed

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
            java.util.logging.Logger.getLogger(HU02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HU02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HU02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HU02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HU02().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botones1;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox<String> cb_contrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtBuscarApellidooNombre;
    // End of variables declaration//GEN-END:variables
private void ListarContratos() {
    // Limpiar la tabla
    modelo = (DefaultTableModel) tabla1.getModel();
    modelo.setRowCount(0);

    // Obtener el tipo de filtro seleccionado
    String tipoFiltro = cb_contrato.getSelectedItem().toString().equals("Con Contrato") ? "CON_CONTRATO" : "SIN_CONTRATO";
    
    // Obtener la lista filtrada de trabajadores con/sin contrato
    List<TrabajadorContrato> lista = contratoDAO.listarTrabajadoresContratos("",tipoFiltro);
    
    // Llenar la tabla solo con los trabajadores que coincidan con el estado del filtro
    for (TrabajadorContrato t : lista) {
        // Solo agrega a la tabla los que cumplan con el filtro (estado de contrato)
        if ((tipoFiltro.equals("CON_CONTRATO") && t.getEstado().equals("Habilitado")) ||
            (tipoFiltro.equals("SIN_CONTRATO") && t.getEstado().equals("Sin Contrato"))) {
            
            modelo.addRow(new Object[]{
                t.getIdTrabajador(),
                t.getNombreCompleto(),

            });
        }
    }
}
private void BuscarContratos() {
    String busqueda = txtBuscarApellidooNombre.getText().trim();
    
    // Obtener el tipo de filtro seleccionado en el ComboBox
    String tipoFiltro = cb_contrato.getSelectedItem().toString().equals("Con Contrato") ? "CON_CONTRATO" : "SIN_CONTRATO";
    
    // Limpiar la tabla antes de mostrar los resultados
    modelo = (DefaultTableModel) tabla1.getModel();
    modelo.setRowCount(0);

    // Obtener los resultados según el filtro de contrato
    List<TrabajadorContrato> lista = contratoDAO.listarTrabajadoresContratos(busqueda, tipoFiltro);

    // Llenar la tabla con los resultados filtrados
    for (TrabajadorContrato t : lista) {
        // Mostrar solo los que tienen contrato si se seleccionó "Con Contrato"
       if (tipoFiltro.equals("CON_CONTRATO") && t.getEstado() != null && t.getEstado().equals("Habilitado")) {
    modelo.addRow(new Object[]{
        t.getIdTrabajador(),
        t.getNombreCompleto()
    });
    }
    // Mostrar los que no tienen contrato o tienen contrato deshabilitado
    else if (tipoFiltro.equals("SIN_CONTRATO") && t.getEstado() != null && t.getEstado().equals("Deshabilitado")) {
        modelo.addRow(new Object[]{
            t.getIdTrabajador(),
            t.getNombreCompleto()
        });
    }

}

        }
    }


