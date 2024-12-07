package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for managing Puesto operations.
 */
public class PuestoDAO {
    private Conexion conexion;

    public PuestoDAO() {
        conexion = new Conexion();
    }

    // Method to list all Puestos using stored procedure
    public List<Puesto> listarPuestos() {
        List<Puesto> listaPuestos = new ArrayList<>();
        String sql = "{CALL sp_listarPuestos()}";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(rs.getInt("idPuesto"));
                puesto.setDescripPuesto(rs.getString("descripPuesto"));
                puesto.setRemuPuesto(rs.getInt("remuPuesto"));
                puesto.setEstadoPuesto(rs.getString("estadoPuesto"));
                listaPuestos.add(puesto);
            }
        } catch (SQLException e) {
            System.out.println("Error while listing Puestos: " + e.getMessage());
        }
        return listaPuestos;
    }

    // Method to get the remuneration of a specific Puesto by idPuesto
    public int obtenerRemuneracion(int idPuesto) {
        String sql = "{CALL sp_obtenerRemuneracion(?)}"; // Assuming you'll create this stored procedure
        int remuneracion = 0;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPuesto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    remuneracion = rs.getInt("remuPuesto");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while getting remuneration: " + e.getMessage());
        }
        return remuneracion;
    }
    
}
