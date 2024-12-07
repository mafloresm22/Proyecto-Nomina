package Modelo;

import Modelo.TrabajadorContrato;
import Modelo.Pago;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class HU03 extends javax.swing.JFrame {

    DefaultTableModel modelo;
    GestionPagos gestionPagos = new GestionPagos();

    public HU03() {
        initComponents();
        ListarTrabajadoresConContrato();
        ListarPeriodos();
        initTableSelectionListener();
    }

    void ListarTrabajadoresConContrato() {
        modelo = (DefaultTableModel) tblListarTrabajadorContrato.getModel();
        modelo.setRowCount(0);
        List<TrabajadorContrato> lista = gestionPagos.listarTrabajadoresConContrato();
        for (TrabajadorContrato t : lista) {
            if ("Habilitado".equals(t.getEstado())) {
                modelo.addRow(new Object[]{t.getIdContrato(), t.getNombreCompleto(), t.getPuesto(), t.getPeriodo(), t.getEstado(), t.getEstadoPago()});
            }
        }
    }

    void ListarTrabajadoresConContratoPago() {
        modelo = (DefaultTableModel) tblListarTrabajadorContrato.getModel();
        modelo.setRowCount(0);
        String periodo = cbPeriodo.getSelectedItem().toString();
        List<TrabajadorContrato> lista = gestionPagos.listarTrabajadoresConContratoPeriodo(periodo);
        for (TrabajadorContrato t : lista) {
            if ("Habilitado".equals(t.getEstado())) {
                modelo.addRow(new Object[]{t.getIdContrato(), t.getNombreCompleto(), t.getPuesto(), t.getPeriodo(), t.getEstado(), t.getEstadoPago()});
            }
        }
    }

    void BuscarTrabajadoresConContrato() {
        modelo = (DefaultTableModel) tblListarTrabajadorContrato.getModel();
        modelo.setRowCount(0);
        String busqueda = txtBuscarApellidooNombre.getText().trim();
        List<TrabajadorContrato> lista = gestionPagos.buscarTrabajadoresConContrato(busqueda);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún trabajador con ese apellido o nombre!.");
        } else {
            for (TrabajadorContrato t : lista) {
                if ("Habilitado".equals(t.getEstado())) {
                    modelo.addRow(new Object[]{t.getIdContrato(), t.getNombreCompleto(), t.getPuesto(), t.getPeriodo(), t.getEstado(), t.getEstadoPago()});
                }
            }
        }
    }

    void ListarPeriodos() {
        List<String> periodos = gestionPagos.listarPeriodos();
        for (String periodo : periodos) {
            cbPeriodo.addItem(periodo);
        }
    }

    private void initTableSelectionListener() {
        tblListarTrabajadorContrato.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblListarTrabajadorContrato.getSelectedRow() != -1) {
                    int selectedRow = tblListarTrabajadorContrato.getSelectedRow();
                    int idContrato = (int) modelo.getValueAt(selectedRow, 0);
                    String periodo = (String) modelo.getValueAt(selectedRow, 3);
                    Pago pago = gestionPagos.obtenerDatosPago(idContrato, periodo);
                    if (pago != null) {
                        txtAreaDetallePago.setText("****************** BOLETA DE PAGO ******************\n"
                                + "Periodo de Pago: " + pago.getPeriodoPago() + "\n"
                                + "Fecha de Pago: " + pago.getFechaPago() + "\n"
                                + "***************************************************\n"
                                + "DETALLES DEL TRABAJADOR\n"
                                + "Horas Trabajadas: " + pago.getHorasTrabajadas() + " hrs\n"
                                + "***************************************************\n"
                                + "INGRESOS\n"
                                + "Pago Mensual: S/ " + pago.getPagoMensual() + "\n"
                                + "Pago Horas Extra: S/ " + pago.getPagoHorasExtra() + "\n"
                                + "Bonificación por Hijos: S/ " + pago.getBonificacionHijos() + "\n"
                                + "Total Bonificación: S/ " + pago.getTotalBonificacion() + "\n"
                                + "***************************************************\n"
                                + "DESCUENTOS\n"
                                + "Descuento Salud: S/ " + pago.getDescuentoSalud() + "\n"
                                + "Descuento Pensión: S/ " + pago.getDescuentoPension() + "\n"
                                + "Total Descuento: S/ " + pago.getTotalDescuento() + "\n"
                                + "***************************************************\n"
                                + "NETO A PAGAR: S/ " + pago.getPagoTotal() + "\n"
                                + "***************************************************\n"
                                + "Estado de Pago: " + pago.getEstadoPago() + "\n"
                                + "***************************************************");
                    } else {
                        txtAreaDetallePago.setText("No hay detalles de pago disponibles para este contrato.");
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarApellidooNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListarTrabajadorContrato = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaDetallePago = new javax.swing.JTextArea();
        btnProcesarPago = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        cbPeriodo = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Gestión pagos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Buscar trabajador");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtBuscarApellidooNombre.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        getContentPane().add(txtBuscarApellidooNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 270, 30));

        btnBuscar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 100, 30));

        tblListarTrabajadorContrato.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tblListarTrabajadorContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombres y Apellidos", "Puesto", "Periodo"
            }
        ));
        jScrollPane1.setViewportView(tblListarTrabajadorContrato);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 610, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Detalles de Pago");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        txtAreaDetallePago.setColumns(20);
        txtAreaDetallePago.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAreaDetallePago.setRows(5);
        jScrollPane2.setViewportView(txtAreaDetallePago);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 610, 300));

        btnProcesarPago.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnProcesarPago.setText("Procesar Pago");
        btnProcesarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarPagoActionPerformed(evt);
            }
        });
        getContentPane().add(btnProcesarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, 170, 30));

        btnVolver.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 110, 30));

        cbPeriodo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<<Seleccionar Periodo>>" }));
        cbPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPeriodoActionPerformed(evt);
            }
        });
        getContentPane().add(cbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 220, 30));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 700, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarTrabajadoresConContrato();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnProcesarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarPagoActionPerformed
        String periodoSeleccionado = (String) cbPeriodo.getSelectedItem();
        if (periodoSeleccionado == null || periodoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un periodo.");
            return;
        }

        if (!"202411".equals(periodoSeleccionado)) {
            JOptionPane.showMessageDialog(null, "Porfavor! Seleccione un periodo de pago actual");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date fechaPeriodo = sdf.parse(periodoSeleccionado);
            Date fechaActual = new Date();
            if (fechaPeriodo.after(fechaActual)) {
                JOptionPane.showMessageDialog(null, "No puede seleccionar un periodo de pago posterior a la fecha actual.");
                return;
            }
            Date fechaLimite = new Date(fechaActual.getTime() - (365L * 24 * 60 * 60 * 1000));
            if (fechaPeriodo.before(fechaLimite)) {
                JOptionPane.showMessageDialog(null, "No se puede procesar el pago en un periodo anterior al último periodo pagado.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el Seleccionar pago ");
            return;
        }

        List<TrabajadorContrato> lista = gestionPagos.listarTrabajadoresConContrato();
        lista.removeIf(trabajador -> !"Habilitado".equals(trabajador.getEstado()));
        int trabajadoresPagados = 0;
        boolean todosPagados = true;

        for (TrabajadorContrato trabajador : lista) {
            int idContrato = trabajador.getIdContrato();
            String periodo = trabajador.getPeriodo();
            Pago pago = gestionPagos.obtenerDatosPago(idContrato, periodo);
            if (pago != null && periodoSeleccionado.equals(pago.getPeriodoPago()) && "Pagado".equals(pago.getEstadoPago())) {
                continue;
            }
            todosPagados = false;
            if (pago != null && !"Pagado".equals(pago.getEstadoPago())) {
                TrabajadorContrato datosTrabajador = gestionPagos.obtenerDatosTrabajadorPorContratoProcesoPago(idContrato);
                if (datosTrabajador != null) {
                    double remuneracionBasica = datosTrabajador.getRemuContrato();
                    String tipoJubilacion = datosTrabajador.getJubilacion();
                    int numeroDeHijos = datosTrabajador.getHijos();
                    int horasTrabajadas = pago.getHorasTrabajadas();
                    Pagos pagos = new Pagos();
                    double pagoMensual = pagos.calcularPagoMensual(horasTrabajadas, remuneracionBasica);
                    double pagoHorasExtra = pagos.calcularPagoHorasExtra(horasTrabajadas, remuneracionBasica);
                    double bonificacionPorHijos = pagos.calcularBonificacionPorHijos(numeroDeHijos);
                    double descuentoSeguroSalud = pagos.calcularDescuentoSeguroSalud(horasTrabajadas, remuneracionBasica);
                    double descuentoPension = pagos.calcularDescuentoPension(tipoJubilacion, horasTrabajadas, remuneracionBasica);
                    double sueldoNeto = pagos.calcularSueldoNeto(tipoJubilacion, horasTrabajadas, remuneracionBasica, numeroDeHijos);
                    double totalBonificacion = bonificacionPorHijos;
                    double totalDescuento = descuentoSeguroSalud + descuentoPension;
                    double pagoTotal = sueldoNeto;
                    gestionPagos.actualizarEstadoPago(idContrato, periodo, pagoMensual, pagoHorasExtra, bonificacionPorHijos, totalBonificacion, descuentoSeguroSalud, descuentoPension, totalDescuento, pagoTotal);
                    trabajadoresPagados++;
                }
            }
        }

        if (todosPagados) {
            JOptionPane.showMessageDialog(null, "Todos los trabajadores ya han sido pagados para el periodo " + periodoSeleccionado);
            txtAreaDetallePago.setText("Pago realizado a los trabajadores del periodo " + periodoSeleccionado + ".\nCantidad de trabajadores pagados: 0");
        } else if (trabajadoresPagados == 0) {
            JOptionPane.showMessageDialog(null, "Todos los trabajadores ya han sido pagados para el periodo " + periodoSeleccionado);
            txtAreaDetallePago.setText("Pago realizado a los trabajadores del periodo " + periodoSeleccionado + ".\nCantidad de trabajadores pagados: 0");
        } else {
            JOptionPane.showMessageDialog(null, "Pagos procesados exitosamente.");
            txtAreaDetallePago.setText("Pago realizado a los trabajadores del periodo " + periodoSeleccionado + ".\nCantidad de trabajadores pagados: " + trabajadoresPagados);
        }


    }//GEN-LAST:event_btnProcesarPagoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cbPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeriodoActionPerformed
        // TODO add your handling code here:
        if (cbPeriodo.getSelectedIndex() == 0) {
            ListarTrabajadoresConContrato();
        } else {
            ListarTrabajadoresConContratoPago();
        }
    }//GEN-LAST:event_cbPeriodoActionPerformed

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
            java.util.logging.Logger.getLogger(HU03.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HU03.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HU03.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HU03.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HU03().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnProcesarPago;
    private javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> cbPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblListarTrabajadorContrato;
    public static javax.swing.JTextArea txtAreaDetallePago;
    private javax.swing.JTextField txtBuscarApellidooNombre;
    // End of variables declaration//GEN-END:variables
}
