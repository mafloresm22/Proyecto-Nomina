package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Controlador.Conexion;

public class GestionPagos {

    private Conexion conexion;

    public GestionPagos() {
        conexion = new Conexion();
    }

    public List<TrabajadorContrato> listarTrabajadoresConContrato() {
        List<TrabajadorContrato> lista = new ArrayList<>();
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarTrabajadoresConContratoPago}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                TrabajadorContrato trabajadorContrato = new TrabajadorContrato();
                trabajadorContrato.setIdContrato(rs.getInt("idContrato"));
                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
                trabajadorContrato.setPuesto(rs.getString("descripPuesto"));
                trabajadorContrato.setEstado(rs.getString("estadoContrato"));
                trabajadorContrato.setEstadoPago(rs.getString("estadoPagado"));
                trabajadorContrato.setPeriodo(rs.getString("periodoPago"));
                lista.add(trabajadorContrato);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadores con contrato: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return lista;
    }
    
    public List<TrabajadorContrato> listarTrabajadoresConContratoPeriodo(String periodo) {
        List<TrabajadorContrato> lista = new ArrayList<>();
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarTrabajadoresConContratoPagoPeriodo(?)}");
            cs.setString(1, periodo);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                TrabajadorContrato trabajadorContrato = new TrabajadorContrato();
                trabajadorContrato.setIdContrato(rs.getInt("idContrato"));
                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
                trabajadorContrato.setPuesto(rs.getString("descripPuesto"));
                trabajadorContrato.setEstado(rs.getString("estadoContrato"));
                trabajadorContrato.setEstadoPago(rs.getString("estadoPagado"));
                trabajadorContrato.setPeriodo(rs.getString("periodoPago"));
                lista.add(trabajadorContrato);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadores con contrato: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return lista;
    }

    public List<TrabajadorContrato> buscarTrabajadoresConContrato(String busqueda) {
        List<TrabajadorContrato> lista = new ArrayList<>();
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_buscarTrabajadoresConContratoPago(?)}");
            cs.setString(1, busqueda);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                TrabajadorContrato tc = new TrabajadorContrato();
                tc.setIdTrabajador(rs.getInt("idTrabajador"));
                tc.setNombreCompleto(rs.getString("nombreCompleto"));
                tc.setIdContrato(rs.getInt("idContrato"));
                tc.setDuracionContrato(rs.getString("duracionContrato"));
                tc.setRemuContrato(rs.getInt("remuContrato"));
                tc.setExpContrato(rs.getString("expContrato"));
                tc.setEstado(rs.getString("estadoContrato"));
                tc.setPuesto(rs.getString("descripPuesto"));
                tc.setEstadoPago(rs.getString("estadoPagado"));
                tc.setPeriodo(rs.getString("periodoPago"));
                lista.add(tc);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajadores con contrato: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return lista;
    }

    public List<String> listarPeriodos() {
        List<String> periodos = new ArrayList<>();
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarPeriodos}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                periodos.add(rs.getString("idperiodo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar periodos: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return periodos;
    }

    public String verificarEstadoPago(int idContrato) {
        String estadoPago = null;
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_verificarEstadoPago(?)}");
            cs.setInt(1, idContrato);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                estadoPago = rs.getString("estadoPagado");
                System.out.println("Estado del pago encontrado: " + estadoPago);
            } else {
                System.out.println("No se encontró el estado de pago para el contrato: " + idContrato);
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar estado de pago: " + e.getMessage());
        } finally {
            if (cx != null) {
                try {
                    cx.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return estadoPago;
    }

    public TrabajadorContrato obtenerDatosTrabajadorPorContrato(int idContrato) {
        TrabajadorContrato trabajadorContrato = null;
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_obtenerDatosTrabajadorPorContratoPago(?)}");
            cs.setInt(1, idContrato);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                trabajadorContrato = new TrabajadorContrato();
                trabajadorContrato.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajadorContrato.setIdContrato(rs.getInt("idContrato"));
                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del trabajador: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return trabajadorContrato;
    }

//    public TrabajadorContrato obtenerDatosTrabajadorPorContratoProcesarPago(int idContrato) {
//        TrabajadorContrato trabajadorContrato = null;
//        Connection cx = null;
//        try {
//            cx = conexion.conectar();
//            CallableStatement cs = cx.prepareCall("{CALL sp_mostrarDetallePago(?)}");
//            cs.setInt(1, idContrato);
//            ResultSet rs = cs.executeQuery();
//            if (rs.next()) {
//                trabajadorContrato = new TrabajadorContrato();
//                trabajadorContrato.setIdTrabajador(rs.getInt("idTrabajador"));
//                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
//                trabajadorContrato.setRemuContrato(rs.getInt("remuContrato"));
//                trabajadorContrato.setJubilacion(rs.getString("jubiContrato"));
//                trabajadorContrato.setHijos(rs.getInt("numHijosContrato"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al obtener datos del trabajador: " + e.getMessage());
//        } finally {
//            if (cx != null) {
//                try {
//                    cx.close();
//                } catch (SQLException e) {
//                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
//                }
//            }
//        }
//        return trabajadorContrato;
//    }
    public void actualizarEstadoPago(int idContrato, String periodo,double pagoMensual, double pagoHorasExtra, double bonificacionHijos, double totalBonificacion, double descuentoSalud, double descuentoPension, double totalDescuento, double pagoTotal) {
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_procesarPago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, idContrato);
            cs.setString(2,periodo);
            cs.setDouble(3, pagoMensual);
            cs.setDouble(4, pagoHorasExtra);
            cs.setDouble(5, bonificacionHijos);
            cs.setDouble(6, totalBonificacion);
            cs.setDouble(7, descuentoSalud);
            cs.setDouble(8, descuentoPension);
            cs.setDouble(9, totalDescuento);
            cs.setDouble(10, pagoTotal);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de pago: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
    }

    public void registrarPago(int idContrato) {
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_registrarPago(?)}");
            cs.setInt(1, idContrato);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar el pago: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
    }

    private void actualizarEstadoContrato(int idContrato) {
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_actualizarEstadoContrato(?)}");
            cs.setInt(1, idContrato);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del contrato: " + e.getMessage());
        } finally {
            if (cx != null) {
                try {
                    cx.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public TrabajadorContrato obtenerDatosTrabajador(int idContrato) {
        TrabajadorContrato trabajadorContrato = null;
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_obtenerDatosTrabajadorPagos(?)}");
            cs.setInt(1, idContrato);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                trabajadorContrato = new TrabajadorContrato();
                trabajadorContrato.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
                trabajadorContrato.setRemuContrato(rs.getInt("remuContrato"));
                trabajadorContrato.setJubilacion(rs.getString("jubiContrato"));
                trabajadorContrato.setHijos(rs.getInt("numHijosContrato"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del trabajador: " + e.getMessage());
        } finally {
            if (cx != null) {
                try {
                    cx.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return trabajadorContrato;
    }

    public Pago obtenerDatosPago(int idContrato, String periodo) {
        Pago pago = null;
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_mostrarDetallePago(?,?)}");
            cs.setInt(1, idContrato);
            cs.setString(2, periodo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("idPago"));
                pago.setIdPagoContrato(rs.getInt("idPagoContrato"));
                pago.setPeriodoPago(rs.getString("periodoPago"));
                pago.setFechaPago(rs.getDate("fechaPago"));
                pago.setHorasTrabajadas(rs.getInt("horasTrabajadas"));
                pago.setPagoMensual(rs.getDouble("pagoMensual"));
                pago.setPagoHorasExtra(rs.getDouble("pagoHoraExtra"));
                pago.setBonificacionHijos(rs.getDouble("bonificacionHijos"));
                pago.setTotalBonificacion(rs.getDouble("totalBonificacion"));
                pago.setDescuentoSalud(rs.getDouble("descuentoSalud"));
                pago.setDescuentoPension(rs.getDouble("descuentoPension"));
                pago.setTotalDescuento(rs.getDouble("totalDescuento"));
                pago.setPagoTotal(rs.getDouble("pagoTotal"));
                pago.setEstadoPago(rs.getString("estadoPago"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del pago: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return pago;
    }

    public TrabajadorContrato obtenerDatosTrabajadorPorContratoProcesoPago(int idContrato) {
        TrabajadorContrato trabajadorContrato = null;
        Connection cx = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_obtenerDatosTrabajadorPorContratoProcesoPago(?)}");
            cs.setInt(1, idContrato);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                trabajadorContrato = new TrabajadorContrato();
                trabajadorContrato.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajadorContrato.setNombreCompleto(rs.getString("nombreCompleto"));
                trabajadorContrato.setRemuContrato(rs.getInt("remuContrato"));
                trabajadorContrato.setJubilacion(rs.getString("jubiContrato"));
                trabajadorContrato.setHijos(rs.getInt("numHijosContrato"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del trabajador: " + e.getMessage());
        } finally {
            if (cx != null) {
                conexion.desconectar();
            }
        }
        return trabajadorContrato;
    }

}
