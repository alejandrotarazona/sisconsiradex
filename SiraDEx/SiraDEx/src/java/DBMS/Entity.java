/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Siscon
 */
public class Entity {

    private final String TABLA;
    private boolean ok;
    private String[] respuesta;
    private String sql;
    private final String[] TABLAS = {
        "USUARIO", //0
        "TIPO_ACTIVIDAD", //1
        "ACTIVIDAD", //2
        "CAMPO", //3
        "VALOR", //4
        "PARTICIPA", //5
        "CATALOGO", //6
        "CAMPO_CATALOGO", //7
        "ELEMENTO_CATALOGO", //8
        "VALOR_CATALOGO", //9
        "TIENE_PERMISO", //10   
        "LOG", //11

        "PERMISOS", //12        ---------------Vista-------------------   
        "ESTUDIANTES", //13         OJO!!!
        "PROFESORES", //14          NO se puede hacer insert ni update
        "OBREROS", //15             a traves de estas!!!
        "PROGRAMAS", //16
        "EMPLEADOS", //17
        "DEPENDENCIAS", //18     
        "TIPO_P", //19
        "TIPO_R", //20        
        "TIPO_ACT__ACT", //21
        "ACT_PARTICIPA", //22
        "ELEMENTO_VALOR", //23    
        "ACT_COMPLETA"//24      --------------------------------------
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
    public Entity(int TABLA) {
        this.TABLA = TABLAS[TABLA];
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

    public static String escapeSQL(Object o) {
        return String.valueOf(o).replace("\\", "\\\\").replace("'", "\\'");
    }

    /* PARA USAR CON GOOGLE CHARTS 
     * Obtiene el numero de actividades por tipo de actividad
     */
    public ResultSet seleccionarNumActividades() {
        sql = "SELECT nombre_tipo_actividad, COUNT(id_actividad) AS cantidad "
                + "FROM " + TABLA + " GROUP BY nombre_tipo_actividad";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet seleccionarNumActividadesUsuario(String usbid) {
        sql = "SELECT nombre_tipo_actividad, COUNT(id_actividad) AS cantidad "
                + "FROM " + TABLA + " WHERE usbid = " + "'" + usbid + "'"
                + " GROUP BY nombre_tipo_actividad";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet seleccionar(String[] columnas, Object[] valores) {
        sql = "SELECT * "
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

    //Revisar
    public ResultSet seleccionarEnOrden(String[] columnas, Object[] valores, String valor) {
        sql = "SELECT * "
                + "FROM " + TABLA
                + " WHERE " + columnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        int i;

        for (i = 1; i < columnas.length; i++) {
            sql += " AND " + columnas[i] + " = '" + escapeSQL(valores[i]) + "' ";
        }

        sql += "ORDER BY " + valor;

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    //Selecciona las tuplas donde la columna es distinta a los valores
    public ResultSet seleccionarDistintos(String columna, Object[] valores) {
        sql = "SELECT * "
                + "FROM " + TABLA
                + " WHERE " + columna + " != '" + escapeSQL(valores[0]) + "' ";

        int i;

        for (i = 1; i < valores.length; i++) {
            sql += " AND " + columna + " != '" + escapeSQL(valores[i]) + "' ";
        }

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    /*select * from participa p 
     join usuario u on p.usbid=u.usbid
     join actividad a on p.id_act=a.id_actividad 
     where u.usbid = 'x' and ...*/
    public ResultSet seleccionarSinRepeticion(String[] TABLAS, String cols,
            String JOIN, String[] joins, String conds) {

        sql = "SELECT DISTINCT " + cols
                + " FROM " + TABLA + " " + TABLA.substring(0, 1);
        int tam = TABLAS.length;
        if (tam > 0) {
            for (int i = 0; i < tam; i++) {
                sql += " " + JOIN + " " + TABLAS[i] + " " + TABLAS[i].substring(0, 1)
                        + " ON " + joins[i];
            }
        }

        if (!conds.equals("")) {
            sql += " WHERE " + conds;
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
        sql = "SELECT * FROM " + TABLA;

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet proyectar(String[] seleccionar, String[] columnas,
            Object[] valores) {

        sql = "SELECT " + seleccionar[0];

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

    //Revisar
    public ResultSet proyectarEnOrden(String[] seleccionar, String[] columnas,
            Object[] valores, String valor) {

        sql = "SELECT " + seleccionar[0];

        int i;
        for (i = 1; i < seleccionar.length; i++) {
            sql += " , " + seleccionar[i];
        }

        sql += " FROM " + TABLA
                + " WHERE " + columnas[0] + " = '" + escapeSQL(valores[0]) + "' ";

        for (i = 1; i < columnas.length; i++) {
            sql += " AND " + columnas[i] + " = '" + escapeSQL(valores[i]) + "' ";
        }

        sql += "ORDER BY " + valor;

        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        ResultSet rs = db.consult(sql);

        return rs;
    }

    public ResultSet naturalJoin(String[] seleccionar, String[] tablas,
            String[] columnas, Object[] valores) {

        sql = "SELECT " + seleccionar[0];
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

        sql = "SELECT " + seleccionar[0];
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

        sql = "UPDATE " + TABLA + " SET " + colModificar[0] + " = '"
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
        System.out.println(sql);
        boolean resp = db.update(sql);
        return resp;
    }

    public boolean borrar(String columna, Object valor) {

        sql = "DELETE FROM " + TABLA
                + " WHERE " + columna + " = '" + escapeSQL(valor) + "'";

        System.out.println(sql);
        DataBase db = DataBase.getInstance();

        boolean resp = db.update(sql);
        return resp;
    }

    public boolean borrar(String[] columnas, Object[] valores) {

        sql = "DELETE FROM " + TABLA + " WHERE "
                + columnas[0] + " = '" + escapeSQL(valores[0]) + "'";

        for (int k = 1; k < columnas.length; k++) {
            sql += " AND " + columnas[k] + " = '" + escapeSQL(valores[k]) + "' ";
        }



        System.out.println(sql);
        DataBase db = DataBase.getInstance();
        boolean resp = db.update(sql);
        return resp;
    }

    public boolean insertar(Object[] valores) {
        int k;
        sql = "INSERT INTO " + TABLA
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
        sql = "INSERT INTO " + TABLA + '(';
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
     * Inserta el valor de un campo tipo archivo o producto en la base de datos
     *
     * @param idCampo id del campo
     * @param idActividad id de la actividad a la que pertenece el valor
     * @param valor nombre del archivo
     * @param file archivo a insertar
     * @return
     */
    public boolean insertarArchivo(int idCampo, int idActividad, String valor,
            FormFile archivo) {

        DataBase db = DataBase.getInstance();
        return db.update(idCampo, idActividad, valor, archivo);


    }

    public static void main(String[] args) {
        /*
         * este es un main para pruebas. manejalo para hacer las pruebas que
         * requieras.
         */
        /*
         Entity e = new Entity(0,2);
         ResultSet rs = e.seleccionarNumActividades();
         System.out.println(rs);
         */
    }
}
