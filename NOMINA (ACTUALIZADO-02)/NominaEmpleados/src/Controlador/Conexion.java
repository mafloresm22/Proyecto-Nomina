package Controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    Connection cx;
    String BD = "nomina"; 
    String URL = "jdbc:mysql://localhost:3306/"; 
    String USER = "root";
    String PASSWORD = "mathias123";
    String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    // Método para obtener la conexión a la base de datos
    public Conexion(){
       
    }
    
    public Connection conectar(){
        try {
            Class.forName(DRIVER);
            cx = DriverManager.getConnection(URL+BD,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO A LA BASE DE DATOS" + BD);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}

