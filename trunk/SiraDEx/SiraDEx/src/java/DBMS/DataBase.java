/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class DataBase {

    private static final String host = "localhost";
    private static final int port = 5432;
    private static final String database = "bdsiradex";
    private static final String username = "siradex";
    private static final String password = "siradex";
    static private Connection conexion;

    protected DataBase() {
    }
    static private DataBase instance = null;

    static public DataBase getInstance() {
        if (null == DataBase.instance) {
            DataBase.instance = new DataBase();
        }

        conexion = conectar();
        return DataBase.instance;
    }

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");


            Connection connection;


            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + host + ":" + port + "/" + database,
                    username, password);

            System.out.println("Conexion realizada.");
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Conexion Fallida!!!.");
            ex.printStackTrace();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ResultSet consult(String sql) {
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;//probando retornar null si no se conecta a la base de datos
        }
    }

    public boolean update(String sql) {
        try {       
            Statement stmt = conexion.createStatement();
            int filas = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (filas > 0) {
                conexion.close();
                return true;
            } else {
                conexion.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
  
    }

    public boolean update(String sql, File archivo) throws FileNotFoundException, SQLException, IOException {
        boolean resp = true;
        try (FileInputStream fis = new FileInputStream(archivo); 
                PreparedStatement ps = conexion.prepareStatement(sql + "?, ?)")) {
            ps.setString(1, archivo.getName());
            ps.setBinaryStream(2, fis, archivo.length());

            System.out.println(ps.toString());
            resp = resp && ps.execute();
        }
        conexion.close();
        return resp;
    }

    public static void main(String[] args) {
        try {
            /*
             * este es un main para pruebas. manejalo para hacer las pruebas que
             * requieras.
             */

            DataBase d = DataBase.getInstance();

            ResultSet rs = d.consult("select * from usuario");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumna = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= numColumna; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}