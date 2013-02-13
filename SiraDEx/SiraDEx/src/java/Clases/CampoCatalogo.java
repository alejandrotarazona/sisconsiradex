/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class CampoCatalogo implements Serializable {

    private int idCampo;
    private int idCatalogo;
    private String nombre;
    private String tipo;
    private static String[] TABLAS = {
        "CATALOGO",//0
        "CAMPO_CATALOGO", //1
    };
    private static String[] ATRIBUTOS = {
        "id_campo", //0
        "id_cat", //1
        "nombre_campo", //2
        "tipo_campo" //3
    };

    public CampoCatalogo() {
    }

    public static CampoCatalogo leer() throws IOException {
        CampoCatalogo resp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduzca el nombre del campo:");
        String name = br.readLine();
        System.out.println("");
        System.out.println("Introduzca el tipo del campo: ");
        String type = br.readLine();
        System.out.println("");

        resp = new CampoCatalogo(name, type);

        return resp;
    }

    public CampoCatalogo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getIdCampo() {
        return idCampo;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public static String[] getTABLAS() {
        return TABLAS;
    }

    public static void setTABLAS(String[] TABLAS) {
        CampoCatalogo.TABLAS = TABLAS;
    }

    public static String[] getATRIBUTOS() {
        return ATRIBUTOS;
    }

    public static void setATRIBUTOS(String[] ATRIBUTOS) {
        CampoCatalogo.ATRIBUTOS = ATRIBUTOS;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean agregarCampo(int idCatalogo) {
        boolean resp = true;
        Entity eCampoCatalogo = new Entity(1, 9);
        Integer idCat = new Integer(idCatalogo);
        Object[] valores = {
            idCat,
            nombre,
            tipo
        };
        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3]
        };

        resp &= eCampoCatalogo.insertar2(columnas, valores);

        return resp;
    }

    public static ArrayList<CampoCatalogo> listar(int idCat) {
        ArrayList<CampoCatalogo> resp = new ArrayList<>(0);
        Entity eListar = new Entity(0, 9);

        String[] columnas = {
            ATRIBUTOS[1]
        };
        Integer idBuscar = new Integer(idCat);
        Object[] valores = {
            idBuscar
        };

        ResultSet rs = eListar.seleccionar(columnas, valores);

        if (rs != null) {
            try {
                while (rs.next()) {
                    CampoCatalogo cc = new CampoCatalogo();
                    cc.setIdCampo(rs.getInt(ATRIBUTOS[0]));
                    cc.setIdCatalogo(idCat);
                    cc.setNombre(rs.getString(ATRIBUTOS[2]));
                    System.out.println("NOMBRE CAMPO "+rs.getString(ATRIBUTOS[2]));
                    cc.setTipo(rs.getString(ATRIBUTOS[3]));

                    resp.add(cc);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resp;
    }

    //en el parámetro nombre está el nuevo nombre para el campo del catalogo
    public static boolean modificar(String nombre, int idCat) {
        Entity e = new Entity(2, 9);

        String[] condColumnas = {ATRIBUTOS[1], ATRIBUTOS[0]};
        Object[] valores = {nombre, idCat};
        String[] colModificar = {ATRIBUTOS[1]};
        String[] nombreCampo = {nombre};

        return e.modificar(condColumnas, valores, colModificar, nombreCampo);

    }
}
