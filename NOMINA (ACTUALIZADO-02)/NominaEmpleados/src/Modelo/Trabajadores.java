package Modelo;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Trabajadores extends javax.swing.JFrame {

    DefaultTableModel modelo;
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public Trabajadores() {
        initComponents();
        listarTrabajadores();

        tb_lista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                tb_listaValueChanged(evt);
            }
        });
    }

    void listarTrabajadores() {
        modelo = (DefaultTableModel) tb_lista.getModel();
        modelo.setRowCount(0);
        List<Trabajador> lista = trabajadorDAO.listarTrabajadores();
        for (Trabajador t : lista) {
            if ("Habilitado".equals(t.getEstadoTrabajador())) {
                String apellidos = (t.getApePaternoTrabajador() != null ? t.getApePaternoTrabajador() : "")
                        + " "
                        + (t.getApeMaternoTrabajador() != null ? t.getApeMaternoTrabajador() : "");
                modelo.addRow(new Object[]{
                    t.getIdTrabajador(),
                    t.getNombresTrabajador(),
                    apellidos,
                    t.getDniTrabajador(),
                    t.getSexoTrabajador(),
                    t.getDireccTrabajador(),
                    t.getCellTrabajador(),
                    t.getEmailTrabajador(),
                    //t.getEstadoTrabajador(),
                    t.getNacimientoTrabajador()
                });
            }
        }
    }

      private void tb_listaValueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int selectedRow = tb_lista.getSelectedRow();
            if (selectedRow != -1) {
                txtNombre.setText(modelo.getValueAt(selectedRow, 1).toString());
                String[] apellidos = modelo.getValueAt(selectedRow, 2).toString().split(" ");
                txtApellidoPat.setText(apellidos[0]);
                txtApellidoMat.setText(apellidos.length > 1 ? apellidos[1] : "");
                txtDNI.setText(modelo.getValueAt(selectedRow, 3).toString());
                cbSexo.setSelectedItem(modelo.getValueAt(selectedRow, 4).toString());
                txtDireccion.setText(modelo.getValueAt(selectedRow, 5).toString());
                txtCelular.setText(modelo.getValueAt(selectedRow, 6).toString());
                txtEmail.setText(modelo.getValueAt(selectedRow, 7).toString());
                
                txtNacimiento.setText(modelo.getValueAt(selectedRow, 8).toString());
            }
        }
    }
  void limpiarCampos() {
        txtNombre.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtDNI.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtNacimiento.setText("");
        cbSexo.setSelectedIndex(0);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtApellidoMat = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtBuscarApellidooNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_lista = new javax.swing.JTable();
        txtDNI = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        cbSexo = new javax.swing.JComboBox<>();
        txtApellidoPat = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btn_Limpiar = new javax.swing.JButton();
        btn_Regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtApellidoMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 130, -1));
        jPanel1.add(txtNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 150, -1));

        txtBuscarApellidooNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarApellidooNombreActionPerformed(evt);
            }
        });
        txtBuscarApellidooNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarApellidooNombreKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarApellidooNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 200, -1));

        tb_lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombres", "Apellidos", "DNI", "Sexo", "Direccion", "Celular", "Email", "Fecha de Nacimiento"
            }
        ));
        jScrollPane1.setViewportView(tb_lista);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 750, 260));

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 170, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 170, -1));
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 170, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, -1, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, -1, -1));

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));
        jPanel1.add(cbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, -1, -1));
        jPanel1.add(txtApellidoPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 150, -1));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 150, -1));

        jLabel1.setText("Buscar Trabajador:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 150, -1));

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, -1, -1));

        btn_Regresar.setText("Regresar");
        btn_Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, -1, -1));

        jLabel2.setText("NOMBRES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel3.setText("APELLIDO PATERNO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jLabel4.setText("APELLIDO MATERNO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jLabel5.setText("CELULAR");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel6.setText("DNI");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel7.setText("Fecha de nacimiento");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));

        jLabel8.setText("DIRECCIÓN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabel9.setText("SEXO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        jLabel10.setText("EMAIL");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
           
  if (!validarCampos()) {
            return;
        }

        // Crear objeto Trabajador y asignar valores
        Trabajador trabajador = new Trabajador();
        trabajador.setNombresTrabajador(txtNombre.getText());
        trabajador.setApePaternoTrabajador(txtApellidoPat.getText());
        trabajador.setApeMaternoTrabajador(txtApellidoMat.getText());
        trabajador.setDniTrabajador(txtDNI.getText());
        trabajador.setSexoTrabajador(cbSexo.getSelectedItem().toString());
        trabajador.setDireccTrabajador(txtDireccion.getText());
        trabajador.setCellTrabajador(txtCelular.getText());
        trabajador.setEmailTrabajador(txtEmail.getText());
        trabajador.setNacimientoTrabajador(txtNacimiento.getText());
        trabajador.setEstadoTrabajador("habilitado");

        // Guardar el trabajador en la base de datos
         if (trabajadorDAO.trabajadorDuplicado(trabajador)) {
            JOptionPane.showMessageDialog(null, "Trabajador ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (trabajadorDAO.crearTrabajador(trabajador)) {
                JOptionPane.showMessageDialog(null, "Trabajador creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear trabajador", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Actualizar la lista de trabajadores y limpiar los campos
        listarTrabajadores();
        limpiarCampos();
    }//GEN-LAST:event_btnRegistrarActionPerformed
   private boolean validarCampos() {
        // Verificar campo de Sexo primero para darle prioridad
        if (cbSexo.getSelectedItem() == null || cbSexo.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(this, "Sexo no seleccionado", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false; // Detener la validación aquí
        }
        // Verificar que todos los campos no estén vacíos
        if (txtNombre.getText().trim().isEmpty() ||
            txtApellidoPat.getText().trim().isEmpty() ||
            txtApellidoMat.getText().trim().isEmpty() ||
            txtDNI.getText().trim().isEmpty() ||
            cbSexo.getSelectedItem() == null ||
            txtDireccion.getText().trim().isEmpty() ||
            txtCelular.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty() ||
            txtNacimiento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formato de correo electrónico
        if (!Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "El correo electrónico no tiene un formato válido.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formato de fecha (dd/MM/yyyy)
        if (!validarFecha(txtNacimiento.getText())) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento debe estar en formato dd/MM/yyyy.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar que el DNI tenga 8 caracteres (asumiendo que el DNI tiene 8 dígitos)
        if (txtDNI.getText().length() != 8) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 8 dígitos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validar que el número de celular tenga un formato válido (por ejemplo, 9 dígitos)
        if (!txtCelular.getText().matches("\\d{9}")) {
            JOptionPane.showMessageDialog(this, "El número de celular debe tener 9 dígitos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean validarFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
         // Verificación de campos antes de actualizar
        if (!validarCampos()) {
            return;
        }

        int selectedRow = tb_lista.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un trabajador para actualizar.");
            return;
        }

        int id = Integer.parseInt(modelo.getValueAt(selectedRow, 0).toString());
        Trabajador trabajador = trabajadorDAO.buscarTrabajadorPorID(id);

        if (trabajador != null) {
            String nombres = txtNombre.getText();
            String apellidoPat = txtApellidoPat.getText();
            String apellidoMat = txtApellidoMat.getText();
            String dni = txtDNI.getText();
            String sexo = cbSexo.getSelectedItem().toString();
            String direccion = txtDireccion.getText();
            String celular = txtCelular.getText();
            String email = txtEmail.getText();
            String nacimiento = txtNacimiento.getText();

            trabajador.setNombresTrabajador(nombres);
            trabajador.setApePaternoTrabajador(apellidoPat);
            trabajador.setApeMaternoTrabajador(apellidoMat);
            trabajador.setDniTrabajador(dni);
            trabajador.setSexoTrabajador(sexo);
            trabajador.setDireccTrabajador(direccion);
            trabajador.setCellTrabajador(celular);
            trabajador.setEmailTrabajador(email);
            trabajador.setNacimientoTrabajador(nacimiento);

            trabajadorDAO.actualizarTrabajador(trabajador);
            listarTrabajadores();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Trabajador actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Trabajador no encontrado.");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       int selectedRow = tb_lista.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un trabajador para eliminar.");
            return;
        }

        int id = Integer.parseInt(modelo.getValueAt(selectedRow, 0).toString());
        Trabajador trabajador = trabajadorDAO.buscarTrabajadorPorID(id);

        if (trabajador != null) {
            // Verificamos si tiene contrato activo
            if (trabajadorDAO.tieneContratoActivo(id)) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar el trabajador porque tiene un contrato activo.", "Error de eliminación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Si no tiene contrato activo, procedemos con la eliminación
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este trabajador?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (trabajadorDAO.eliminarTrabajador(trabajador.getIdTrabajador())) {
                    listarTrabajadores();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Trabajador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el trabajador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Trabajador no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarApellidooNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarApellidooNombreActionPerformed
        // TODO add your handling code here:
        String search = txtBuscarApellidooNombre.getText();
        List<Trabajador> trabajadores = trabajadorDAO.buscarTrabajadoresPorNombreApellido(search);
        modelo.setRowCount(0);
        for (Trabajador t : trabajadores) {
            String apellidos = (t.getApePaternoTrabajador() != null ? t.getApePaternoTrabajador() : "")
                    + " "
                    + (t.getApeMaternoTrabajador() != null ? t.getApeMaternoTrabajador() : "");
            modelo.addRow(new Object[]{
                t.getIdTrabajador(),
                t.getNombresTrabajador(),
                apellidos,
                t.getDniTrabajador(),
                t.getSexoTrabajador(),
                t.getDireccTrabajador(),
                t.getCellTrabajador(),
                t.getEmailTrabajador(),
                t.getNacimientoTrabajador(),
                t.getEstadoTrabajador()
            });
        }
    }//GEN-LAST:event_txtBuscarApellidooNombreActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed

    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtBuscarApellidooNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarApellidooNombreKeyReleased

    }//GEN-LAST:event_txtBuscarApellidooNombreKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
         limpiarCampos();
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
    
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
      limpiarCampos();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_RegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Trabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trabajadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trabajadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Regresar;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable tb_lista;
    private javax.swing.JTextField txtApellidoMat;
    private javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtBuscarApellidooNombre;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
