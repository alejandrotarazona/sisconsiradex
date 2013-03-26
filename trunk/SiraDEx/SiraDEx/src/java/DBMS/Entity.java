/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        "VALOR", //6
        "PARTICIPA", //7
        "CATALOGO", //8
        "CAMPO_CATALOGO", //9
        "ELEMENTO_CATALOGO", //10
        "VALOR_CATALOGO", //11

        "ESTUDIANTES", //12             OJO!!!
        "PROFESORES", //13              NO se puede hacer insert ni update
        "OBREROS", //14                a traves de estas!!!
        "PROGRAMAS", //15
        "EMPLEADOS", //16
        "COORDINACIONES", //17    ---------------------------------------

        "TIENE_PERMISO", //18
        "PERMISOS" //19      ---------------Vista-------------------
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
    
    public String escapeSQL(Object o){
        return String.valueOf(o).replace("\\", "\\\\").replace("'", "\\'");
    }

    public ResultSet seleccionar(String[] columnas, Object[] valores) {
        sql = ACCION + " * "
                + "FROM " + TABLA
                + " WHERE " + columnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        int i;

        for (i = 1; i < columnas.length; i++) {
            sql += " AND " + columnas[i] + " = '" + escapeSQL(valores[i]) + "' ";
        }

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public int seleccionarMaxId(String id) {
        sql = "SELECT MAX (" + id + ") " + "FROM " + TABLA;

        System.out.println(sql);
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

        System.out.println(sql);
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

        sql += " FROM " + TABLA
                + " WHERE " + columnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        for (i = 1; i < columnas.length; i++) {
            sql += " AND " + columnas[i] + " = '" + escapeSQL(valores[i]) + "' ";
        }

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet naturalJoin(String[] seleccionar, String[] tablas,
            String[] columnas, Object[] valores) {

        sql = ACCION + " " + seleccionar[0];
        int i;

        for (i = 1; i < seleccionar.length; i++) {
            sql += " , " + seleccionar[i];
        }
        sql += " FROM " + tablas[0];

        for (i = 1; i < tablas.length; i++) {
            sql += " NATURAL JOIN " + tablas[i];
        }
        sql += " WHERE " + columnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        for (i = 1; i < columnas.length; i++) {
            sql += " AND " + columnas[i] + " = '" + escapeSQL(valores[i]) + "' ";
        }

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet naturalJoin(String[] seleccionar, String[] tablas) {

        sql = ACCION + " " + seleccionar[0];
        int i;

        for (i = 1; i < seleccionar.length; i++) {
            sql += " , " + seleccionar[i];
        }
        sql += " FROM " + tablas[0];

        for (i = 1; i < tablas.length; i++) {
            sql += " NATURAL JOIN " + tablas[i];
        }

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public boolean modificar(String[] condColumnas, Object[] valores,
            String[] colModificar, Object[] modificaciones) {

        sql = ACCION + " " + TABLA + " SET " + colModificar[0] + " = '"
                + escapeSQL(modificaciones[0]) + "' ";

        int indice;

        for (indice = 1; indice < colModificar.length; indice++) {
            sql += " , " + colModificar[indice] + " = '"
                    + escapeSQL(modificaciones[indice]) + "' ";
        }

        sql += " WHERE " + condColumnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        for (indice = 1; indice < condColumnas.length; indice++) {
            sql += " AND " + condColumnas[indice] + " = '"
                    + escapeSQL(valores[indice]) + "' ";
        }

        DataBase db = DataBase.getInstance();
        //System.out.println(sql);
        boolean resp = db.update(sql);
        return resp;
    }

    public boolean borrar(String columna, Object valor) {

        sql = ACCION + " FROM " + TABLA
                + " WHERE " + columna + " = '" + escapeSQL(valor) + "'";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();

        boolean resp = db.update(sql);
        return resp;
    }

    public boolean insertar(Object[] valores) {
        int k;
        sql = ACCION + " INTO " + TABLA
                + " VALUES (";
        for (k = 0; k < (valores.length - 1); k++) {
            sql += "'" + escapeSQL(valores[k]) + "' ,";
        }
        sql += "'" + escapeSQL(valores[valores.length - 1]) + "')";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        boolean resp = db.update(sql);
        return resp;
    }

    public boolean insertar2(String[] columnas, Object[] valores) {
        int k;
        sql = ACCION + " INTO " + TABLA + '(';
        for (k = 0; k < columnas.length - 1; k++) {
            sql += columnas[k] + ',';
        }
        sql += columnas[k] + ")  VALUES (";

        for (k = 0; k < (valores.length - 1); k++) {
            sql += "'" + escapeSQL(valores[k]) + "' ,";
        }
        sql += "'" + escapeSQL(valores[valores.length - 1]) + "')";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        boolean resp = db.update(sql);
        return resp;
    }

    /**
     * Insert que se usa cuando se quiere agregar un archivo a una tabla dada.
     *
     * @param tabla Tabla donde se hará el INSERT
     * @param valores Valores que deben de insertarse en la fila de la tabla.
     * @param archivo Archivo que acompaña a los valores y tambien debe
     * insertarse
     * @return true si hace el insert, sino false.
     */
    public boolean insert(String tabla, Object[] valores, File archivo) {
        try {
            int k;
            sql = ACCION + " INTO " + TABLA
                    + " VALUES (";
            for (k = 0; k < (valores.length - 1); k++) {
                sql += "'" + escapeSQL(valores[k]) + "' ,";
            }

            System.out.println(sql);
            DataBase db = DataBase.getInstance();
            boolean resp = db.update(sql, archivo);
            return resp;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        /*
         * este es un main para pruebas. manejalo para hacer las pruebas que
         * requieras.
         */
    }
}
