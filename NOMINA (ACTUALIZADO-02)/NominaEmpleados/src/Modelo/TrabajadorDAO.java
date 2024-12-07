package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Controlador.Conexion;
import java.sql.PreparedStatement;

public class TrabajadorDAO {

    Conexion conexion = new Conexion();
    Connection cx;

    public boolean trabajadorDuplicado(Trabajador trabajador) {
        boolean duplicado = false;
        try {
            cx = conexion.conectar();
            String sql = "SELECT COUNT(*) FROM Trabajador WHERE nombresTrabajador = ? AND apePaternoTrabajador = ? AND apeMaternoTrabajador = ? AND direccTrabajador = ?";
            PreparedStatement ps = cx.prepareStatement(sql);
            ps.setString(1, trabajador.getNombresTrabajador());
            ps.setString(2, trabajador.getApePaternoTrabajador());
            ps.setString(3, trabajador.getApeMaternoTrabajador());
            ps.setString(4, trabajador.getDireccTrabajador());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                duplicado = rs.getInt(1) > 0; // Si existe al menos un registro con los mismos datos
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar duplicado: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return duplicado;
    }

    public boolean crearTrabajador(Trabajador trabajador) {
        boolean result = false;
        try {
            if (trabajadorDuplicado(trabajador)) {
                System.out.println("Trabajador ya existe con los mismos datos.");
                return false; // Previene la creación si el trabajador ya existe
            }
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_crearTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, trabajador.getNombresTrabajador());
            cs.setString(2, trabajador.getApePaternoTrabajador());
            cs.setString(3, trabajador.getApeMaternoTrabajador());
            cs.setString(4, trabajador.getDniTrabajador());
            cs.setString(5, trabajador.getSexoTrabajador());
            cs.setString(6, trabajador.getDireccTrabajador());
            cs.setString(7, trabajador.getCellTrabajador());
            cs.setString(8, trabajador.getEmailTrabajador());
            cs.setString(9, trabajador.getNacimientoTrabajador());
            result = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear trabajador: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return result;
    }

    public List<Trabajador> listarTrabajadores() {
        List<Trabajador> lista = new ArrayList<>();
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarTrabajadores()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setNombresTrabajador(rs.getString("nombresTrabajador"));
                String apellidosCompletos = rs.getString("apePaternoTrabajador") + " " + rs.getString("apeMaternoTrabajador");
                trabajador.setApePaternoTrabajador(apellidosCompletos);
                trabajador.setDniTrabajador(rs.getString("dniTrabajador"));
                trabajador.setSexoTrabajador(rs.getString("sexoTrabajador"));
                trabajador.setDireccTrabajador(rs.getString("direccTrabajador"));
                trabajador.setCellTrabajador(rs.getString("cellTrabajador"));
                trabajador.setEmailTrabajador(rs.getString("emailTrabajador"));
                trabajador.setEstadoTrabajador(rs.getString("estadoTrabajador"));
                trabajador.setNacimientoTrabajador(rs.getString("nacimientoTrabajador"));

                lista.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadores: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return lista;
    }

    public boolean actualizarTrabajador(Trabajador trabajador) {
        boolean result = false;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_actualizarTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, trabajador.getIdTrabajador());
            cs.setString(2, trabajador.getNombresTrabajador());
            cs.setString(3, trabajador.getApePaternoTrabajador());
            cs.setString(4, trabajador.getApeMaternoTrabajador());
            cs.setString(5, trabajador.getDniTrabajador());
            cs.setString(6, trabajador.getSexoTrabajador());
            cs.setString(7, trabajador.getDireccTrabajador());
            cs.setString(8, trabajador.getCellTrabajador());
            cs.setString(9, trabajador.getEmailTrabajador());
            cs.setString(10, trabajador.getNacimientoTrabajador());

            result = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar trabajador: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return result;
    }

    public boolean eliminarTrabajador(int idTrabajador) {
        boolean result = false;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_eliminarTrabajador(?)}");
            cs.setInt(1, idTrabajador);
            result = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar trabajador: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return result;
    }

    public Trabajador buscarTrabajadorPorID(int id) {
        Trabajador trabajador = null;
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_buscarTrabajadorPorID(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setNombresTrabajador(rs.getString("nombresTrabajador"));
                trabajador.setApePaternoTrabajador(rs.getString("apePaternoTrabajador"));
                trabajador.setApeMaternoTrabajador(rs.getString("apeMaternoTrabajador"));
                trabajador.setDniTrabajador(rs.getString("dniTrabajador"));
                trabajador.setSexoTrabajador(rs.getString("sexoTrabajador"));
                trabajador.setDireccTrabajador(rs.getString("direccTrabajador"));
                trabajador.setCellTrabajador(rs.getString("cellTrabajador"));
                trabajador.setEmailTrabajador(rs.getString("emailTrabajador"));
                trabajador.setEstadoTrabajador(rs.getString("estadoTrabajador"));
                trabajador.setNacimientoTrabajador(rs.getString("nacimientoTrabajador"));

            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajador: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return trabajador;
    }

    public List<Trabajador> buscarTrabajadoresPorNombreApellido(String search) {
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_buscarTrabajadorPorNombreApellido(?)}");
            cs.setString(1, search);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setNombresTrabajador(rs.getString("nombresTrabajador"));
                trabajador.setApePaternoTrabajador(rs.getString("apePaternoTrabajador"));
                trabajador.setApeMaternoTrabajador(rs.getString("apeMaternoTrabajador"));
                trabajador.setDniTrabajador(rs.getString("dniTrabajador"));
                trabajador.setSexoTrabajador(rs.getString("sexoTrabajador"));
                trabajador.setDireccTrabajador(rs.getString("direccTrabajador"));
                trabajador.setCellTrabajador(rs.getString("cellTrabajador"));
                trabajador.setEmailTrabajador(rs.getString("emailTrabajador"));
                trabajador.setNacimientoTrabajador(rs.getString("nacimientoTrabajador"));
                trabajador.setEstadoTrabajador(rs.getString("estadoTrabajador"));

                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajadores: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return trabajadores;
    }

    public List<Trabajador> buscarTrabajadoresPorNombreApellidoConContrato(String search) {
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarTrabajadoresConContrato(?)}");
            cs.setString(1, search);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setNombresTrabajador(rs.getString("nombreCompleto")); // Aquí usas "nombreCompleto"
                trabajador.setApePaternoTrabajador(rs.getString("apePaternoTrabajador"));
                trabajador.setApeMaternoTrabajador(rs.getString("apeMaternoTrabajador"));
                trabajador.setDniTrabajador(rs.getString("dniTrabajador"));
                trabajador.setSexoTrabajador(rs.getString("sexoTrabajador"));
                trabajador.setDireccTrabajador(rs.getString("direccTrabajador"));
                trabajador.setCellTrabajador(rs.getString("cellTrabajador"));
                trabajador.setEmailTrabajador(rs.getString("emailTrabajador"));
                trabajador.setNacimientoTrabajador(rs.getString("nacimientoTrabajador"));
                trabajador.setEstadoTrabajador(rs.getString("estadoTrabajador"));

                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajadores: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return trabajadores;
    }

    public List<Trabajador> buscarTrabajadoresPorNombreApellidoSinContrato(String search) {
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            cx = conexion.conectar();
            CallableStatement cs = cx.prepareCall("{CALL sp_listarTrabajadoresSinContrato(?)}");
            cs.setString(1, search);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("idTrabajador"));
                trabajador.setNombresTrabajador(rs.getString("nombresTrabajador"));
                trabajador.setApePaternoTrabajador(rs.getString("apePaternoTrabajador"));
                trabajador.setApeMaternoTrabajador(rs.getString("apeMaternoTrabajador"));
                trabajador.setDniTrabajador(rs.getString("dniTrabajador"));
                trabajador.setSexoTrabajador(rs.getString("sexoTrabajador"));
                trabajador.setDireccTrabajador(rs.getString("direccTrabajador"));
                trabajador.setCellTrabajador(rs.getString("cellTrabajador"));
                trabajador.setEmailTrabajador(rs.getString("emailTrabajador"));
                trabajador.setNacimientoTrabajador(rs.getString("nacimientoTrabajador"));
                trabajador.setEstadoTrabajador(rs.getString("estadoTrabajador"));

                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajadores: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return trabajadores;
    }

    public boolean tieneContratoActivo(int idTrabajador) {
        boolean tieneContrato = false;
        try {
            cx = conexion.conectar();
            String sql = "SELECT COUNT(*) FROM Contrato WHERE idTrabajadorContrato = ? AND estadoContrato = 'Habilitado'";
            PreparedStatement ps = cx.prepareStatement(sql);
            ps.setInt(1, idTrabajador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tieneContrato = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar contrato activo: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        return tieneContrato;
    }

}

