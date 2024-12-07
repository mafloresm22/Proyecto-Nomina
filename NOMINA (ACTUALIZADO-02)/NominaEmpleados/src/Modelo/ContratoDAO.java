package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Controlador.Conexion;
import Modelo.TrabajadorContrato;
import java.sql.PreparedStatement;
import java.util.Date;

public class ContratoDAO {
    private Conexion conexion;

    public ContratoDAO() {
        conexion = new Conexion();
    }

    // Nuevo método para listar todos los trabajadores con sus contratos
public List<TrabajadorContrato> listarTrabajadoresContratos(String busqueda, String tipoFiltro) {
    List<TrabajadorContrato> lista = new ArrayList<>();
    Connection cx = null;
    try {
        cx = conexion.conectar();
        CallableStatement cs;

        if (tipoFiltro.equals("CON_CONTRATO")) {
            cs = cx.prepareCall("{CALL sp_listarTrabajadoresConContrato(?)}");
        } else {
            cs = cx.prepareCall("{CALL sp_listarTrabajadoresSinContrato(?)}");
        }

        cs.setString(1, busqueda); // Utiliza el parámetro de búsqueda

        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            TrabajadorContrato tc = new TrabajadorContrato();
            tc.setIdTrabajador(rs.getInt("idTrabajador"));
            tc.setNombreCompleto(rs.getString("nombreCompleto"));
            tc.setIdContrato(rs.getInt("idContrato"));
            tc.setDuracionContrato(rs.getString("duracionContrato"));
            tc.setRemuContrato(rs.getInt("remuContrato"));
            tc.setExpContrato(rs.getString("expContrato"));
            tc.setEstado(rs.getString("estadoContrato")); // Asegúrate de que el estado esté bien cargado
            lista.add(tc);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar trabajadores y contratos: " + e.getMessage());
    } finally {
        if (cx != null) {
            conexion.desconectar();
        }
    }
    return lista;
    
}

public boolean finalizarContrato(int id) {
    String sql = "UPDATE contrato SET estadoContrato = 'Deshabilitado' WHERE idTrabajadorContrato = ? AND estadoContrato = 'Habilitado'";
    try (Connection conn = conexion.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id);
        int filasAfectadas = pstmt.executeUpdate();
        
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.out.println("Error al finalizar contrato: " + e.getMessage());
        return false;
    }
}

public TrabajadorContrato obtenerDatosTrabajadorContrato(int idTrabajador) {
    TrabajadorContrato tc = null;
    Connection cx = null;
    try {
        cx = conexion.conectar();
        CallableStatement cs = cx.prepareCall("{CALL sp_obtenerDatosTrabajadorContrato(?)}");
        cs.setInt(1, idTrabajador);  // Se pasa el ID del trabajador como parámetro

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            tc = new TrabajadorContrato();
            tc.setIdTrabajador(rs.getInt("idTrabajador"));
            tc.setNombreCompleto(rs.getString("nombresTrabajador"));
            tc.setIdContrato(rs.getInt("idContrato"));
            tc.setDuracionContrato(rs.getString("duracionContrato"));
            tc.setRemuContrato(rs.getInt("remuContrato"));
            tc.setExpContrato(rs.getString("expContrato"));
            tc.setEstado(rs.getString("estadoContrato"));
            tc.setIdpuesto(rs.getInt("idPuestoContrato"));
            tc.setJubilacion(rs.getString("jubiContrato"));
            tc.setHijos(rs.getInt("numHijosContrato"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener datos del trabajador y contrato: " + e.getMessage());
    } finally {
        if (cx != null) {
            conexion.desconectar();
        }
    }
    return tc;
}

 public boolean editarContratoPorTrabajador(int idTrabajador, int idPuesto, String duracionContrato, 
            Date expContrato, int remuContrato, String jubiContrato, int numHijosContrato) {
        
        Connection conn = null;
        CallableStatement stmt = null;
        PreparedStatement psVerificar = null;
        ResultSet rs = null;
        boolean contratoEditado = false;
        
        try {
            conn = conexion.conectar();
            
            // Verificar si existe un contrato activo para este trabajador
            String verificarContrato = "SELECT COUNT(*) FROM Contrato WHERE idTrabajadorContrato = ? AND estadoContrato = 'Habilitado'";
            psVerificar = conn.prepareStatement(verificarContrato);
            psVerificar.setInt(1, idTrabajador);
            rs = psVerificar.executeQuery();
            
            String procedimiento;
            if (rs.next() && rs.getInt(1) > 0) {
                // Si existe un contrato activo, usamos el procedimiento de actualización
                procedimiento = "{CALL sp_editarContratoPorTrabajador(?, ?, ?, ?, ?, ?, ?)}";
                System.out.println("Actualizando contrato existente para trabajador ID: " + idTrabajador);
            } else {
                // Si no existe un contrato activo, usamos el procedimiento de creación
                procedimiento = "{CALL sp_crearNuevoContrato(?, ?, ?, ?, ?, ?, ?)}";
                System.out.println("Creando nuevo contrato para trabajador ID: " + idTrabajador);
            }
            
            // Preparar y ejecutar el procedimiento almacenado correspondiente
            stmt = conn.prepareCall(procedimiento);
            stmt.setInt(1, idTrabajador);
            stmt.setInt(2, idPuesto);
            stmt.setString(3, duracionContrato);
            stmt.setDate(4, new java.sql.Date(expContrato.getTime()));
            stmt.setInt(5, remuContrato);
            stmt.setString(6, jubiContrato);
            stmt.setInt(7, numHijosContrato);
            
            contratoEditado = stmt.executeUpdate() > 0;
            
            if (contratoEditado) {
                System.out.println("Operación exitosa para el trabajador ID: " + idTrabajador);
            } else {
                System.out.println("No se pudo realizar la operación para el trabajador ID: " + idTrabajador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error en la operación de contrato: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (psVerificar != null) psVerificar.close();
                if (stmt != null) stmt.close();
                if (conn != null) conexion.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return contratoEditado;
    }


public int obtenerRemuneracionMinima(int idPuesto) throws SQLException {
    int remuneracionMinima = 0;
    String query = "SELECT remuPuesto FROM puesto WHERE idPuesto = ?";
    
    try (PreparedStatement stmt = conexion.conectar().prepareStatement(query)) {
        stmt.setInt(1, idPuesto);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                remuneracionMinima = rs.getInt("remuPuesto");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al obtener la remuneración mínima para el puesto: " + e.getMessage());
    }
    
    return remuneracionMinima;
}

}
