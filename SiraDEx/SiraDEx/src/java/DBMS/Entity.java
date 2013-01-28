/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siscon
 */
public class Entity {

    private final String ACCION;
    private final String TABLA;
    private boolean ok;
    private String[] respuesta;
    private String sql;
    private final String[] ACCIONES = {
        "SELECT", //0
        "INSERT", //1
        "UPDATE", //2
        "DROP", //3
        "NATURAL JOIN", //4
        "DELETE", //5 
    };
    private final String[] TABLAS = {
        "USUARIO", //0
        "TIPO_ACTIVIDAD", //1
        "ACTIVIDAD", //2
        "CAMPO", //3
        "JEFE_DEPENDENCIA", //4
        "COMUNIDAD_U", //5
        "VALOR" //6
    };

    /*
     * public boolean consultar(Entity e) { if (e instanceof Usuario) { String
     * sqlquery = "SELECT * FROM usuario" + " WHERE usuario ='" + ((Usuario)
     * e).getLogin() + "' " + " AND " + "password ='" + ((Usuario)
     * e).getPassword() + "'";
     *
     * System.out.println(sqlquery); try { ResultSet rs = consult(sqlquery);
     * boolean b = rs.next(); return b; } catch(Exception e){
     * System.out.println(e.getMessage()); return false; } } return false; }
     */
    public Entity(int ACCION, int TABLA) {
        this.ACCION = ACCIONES[ACCION];
        this.TABLA = TABLAS[TABLA];
    }

    public String getACCION() {
        return ACCION;
    }

    public String[] getACCIONES() {
        return ACCIONES;
    }

    public String getTABLA() {
        return TABLA;
    }

    public String[] getTABLAS() {
        return TABLAS;
    }

    public boolean getOk() {
        return ok;
    }

    public String[] getRespuesta() {
        return respuesta;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setRespuesta(String[] respuesta) {
        this.respuesta = respuesta;
    }

    public ResultSet seleccionar(String[] columnas, Object[] valores) {
        if (valores[0] instanceof Integer) {
            sql = ACCION + " * "
                    + "FROM " + TABLA
                    + " WHERE " + columnas[0] + " = " + valores[0];
        } else {

            sql = ACCION + " * "
                    + "FROM " + TABLA
                    + " WHERE " + columnas[0] + " = '" + valores[0] + "' ";
        }
        int i;

        for (i = 1; i < columnas.length; i++) {
            if (valores[i] instanceof Integer) {
                sql += " AND " + columnas[i] + " = " + valores[i];
            } else {
                sql += " AND " + columnas[i] + " = '" + valores[i] + "' ";
            }
        }

        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public int seleccionarMaxId(String id) {
        sql = "SELECT MAX (" + id + ") " + "FROM " + TABLA;

        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);
        int maxId = 0;
        try {
            rs.next();
            maxId = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }

        return maxId;
    }

    public ResultSet listar() {
        sql = ACCION + " * "
                + "FROM " + TABLA;


        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet proyectar(String[] seleccionar, String[] columnas,
            Object[] valores) {

        sql = ACCION + " " + seleccionar[0];
        
        int i;
        for (i = 1; i < seleccionar.length; i++) {
            sql += " , " + seleccionar[i];
        }

        if (valores[0] instanceof Integer) {
            sql += " FROM " + TABLA
                    + " WHERE " + columnas[0] + " = " + valores[0];
        } else {

            sql += " FROM " + TABLA
                    + " WHERE " + columnas[0] + " = '" + valores[0] + "' ";
        }
        
        for (i = 1; i < columnas.length; i++) {
            if (valores[i] instanceof Integer) {
                sql += " AND " + columnas[i] + " = " + valores[i];
            } else {
                sql += " AND " + columnas[i] + " = '" + valores[i] + "' ";
            }
        }

        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet naturalJoins(
            String[] seleccionar,
            String[] tablas,
            String[] columnas,
            String[] valores) {
        sql = ACCION + " " + seleccionar[0];
        int i;

        for (i = 1; i < seleccionar.length; i++) {
            sql += " , " + seleccionar[i];
        }

        sql += " FROM " + tablas[0];

        for (i = 1; i < tablas.length; i++) {
            sql += " NATURAL JOIN " + tablas[i];
        }
        sql += " WHERE " + columnas[0] + " = '" + valores[0] + "' ";


        for (i = 1; i < columnas.length; i++) {
            sql += "AND "
                    + columnas[i] + " = '"
                    + valores[i] + "' ";
        }

        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public boolean modificar(String[] condColumnas, Object[] valores,
            String[] colModificar, Object[] modificaciones) {
        sql = ACCION + TABLA + " SET " + colModificar[0] + " = '"
                + modificaciones[0] + "' ";

        int indice;

        for (indice = 1; indice < colModificar.length; indice++) {
            sql += " , " + colModificar[indice] + " = '" 
                    + modificaciones[indice] + "' ";
        }

        sql += " WHERE " + condColumnas[0] + " = '" + valores[0] + "' ";

        for (indice = 1; indice < condColumnas.length; indice++) {
            sql += " AND " + condColumnas[indice] + " = '" 
                    + valores[indice] + "' ";
        }


        DataBase db = DataBase.getInstance();

        boolean resp = db.update(sql);
        return resp;
    }

    public boolean borrar(String columna, Object valor) {

        if (valor instanceof Integer) {
            sql = ACCION + " FROM " + TABLA
                    + " WHERE " + columna + " = " + valor;
        } else {
            sql = ACCION + " FROM " + TABLA
                    + " WHERE " + columna + " = '" + valor + "'";
        }
        DataBase db = DataBase.getInstance();

        boolean resp = db.update(sql);
        return resp;
    }

    public boolean insertar(Object[] valores) {
        int k;
        sql = ACCION + " INTO " + TABLA
                + " VALUES (";
        for (k = 0; k < (valores.length - 1); k++) {
            if (valores[k] instanceof Integer) {
                sql += " " + valores[k] + " ,";
            } else {
                sql += "'" + valores[k] + "' ,";
            }
        }

        if (valores[valores.length - 1] instanceof Integer) {
            sql += " " + valores[valores.length - 1] + " ";
        } else {
            sql += "'" + valores[valores.length - 1] + "' ";
        }

        sql += ")";
        DataBase db = DataBase.getInstance();
        boolean resp = db.update(sql);
        return resp;
    }

    public boolean insertar2(String[] columnas, Object[] valores){
        int k;
        sql = ACCION + " INTO " + TABLA + '(';
        for (k = 0 ; k < columnas.length - 1; k++){
            sql += columnas[k]+ ',';
        }
        sql += columnas[k] + ")  VALUES (";
        
        for (k = 0; k < (valores.length - 1); k++) {
            if (valores[k] instanceof Integer) {
                sql += " " + valores[k] + " ,";
            } else {
                sql += "'" + valores[k] + "' ,";
            }
        }

        if (valores[valores.length - 1] instanceof Integer) {
            sql += " " + valores[valores.length - 1] + " ";
        } else {
            sql += "'" + valores[valores.length - 1] + "' ";
        }

        sql += ")";
        DataBase db = DataBase.getInstance();
        boolean resp = db.update(sql);
        return resp;
    }
    
    

    public static void main(String[] args) {
        /*
         * este es un main para pruebas. manejalo para hacer las pruebas que
         * requieras.
         */
    }
}
