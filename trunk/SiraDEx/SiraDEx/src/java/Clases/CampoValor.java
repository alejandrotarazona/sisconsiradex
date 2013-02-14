/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
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
public class CampoValor implements Serializable {
    
    private Campo campo;
    private String valor;
    private static String[] TABLAS = {
        "VALOR",
        "CAMPO",
        "ACTIVIDAD"
    };

    public CampoValor() {
    }

    public CampoValor(Campo campo) {
        this.campo = campo;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
    public static ArrayList<CampoValor> listar(int idTipoActividad) {
        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(0, 3);
        String[] columnas = {"id_tipo_actividad"};
        Integer[] condiciones = {idTipoActividad};

        ResultSet rs = eCampo.seleccionar(columnas, condiciones);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Campo c = new Campo();
                    c.setIdCampo(rs.getInt("id_campo"));
                    c.setIdTipoActividad(rs.getInt("id_tipo_actividad"));
                    c.setNombre(rs.getString("nombre_campo"));
                    c.setTipo(rs.getString("tipo_campo"));
                    c.setLongitud(rs.getInt("longitud"));
                    c.setObligatorio(rs.getBoolean("obligatorio"));
                    CampoValor cv = new CampoValor(c);
                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaValor;
    }
    
    //Crea una lista de CampoCatalogoValor, donde los valores dependen del 
    //elemento cuyo id es pasado por parametro, los campos y valores son seteados
    public static ArrayList<CampoValor> listarCamposValores(int idActividad) {
        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(0, 2);
        String[] ATRIBUTOS = {
        "id_campo",
        "id_tipo_actividad",
         "nombre_campo", 
        "tipo_campo",
        "longitud",
        "obligatorio",
        "valor", 
       
        };
        String[] tabABuscar = {
            TABLAS[0],
            TABLAS[1],
            TABLAS[2]
        };
        String[] colCondicion = {"id_actividad"};
        Object[] colValor = {idActividad};

        ResultSet rs = eCampo.naturalJoins(ATRIBUTOS, tabABuscar, colCondicion, colValor);

        if (rs != null) {
            try {
                while (rs.next()) {
                    CampoValor cv = new CampoValor();
                    cv.setValor(rs.getString(ATRIBUTOS[6]));
                    Campo c = new Campo();
                    c.setNombre(rs.getString(ATRIBUTOS[2]));
                    cv.setCampo(c);

                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaValor;
    }
}
