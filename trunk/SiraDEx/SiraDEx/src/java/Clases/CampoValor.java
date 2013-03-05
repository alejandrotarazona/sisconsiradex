/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.File;
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
    private File file = null;
    private static String[] ATRIBUTOS = {
        "id_campo", //0
        "id_actividad", //1
        "valor" //2
    };
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        this.valor = file.getName();
    }

    public boolean agregar(int idAct) {
        Entity eAgregar = new Entity(1, 6);
        boolean resp = true;

        Integer idCampo = new Integer(campo.getIdCampo());
        Integer idActividad = new Integer(idAct);

        if (file != null) {
            if (file.length() > 2024) {
                return false;
            }
            Object[] aAgregar = {idCampo, idActividad, valor, file};
            resp = resp && eAgregar.insertar(aAgregar);
        } else {
            Object[] aAgregar = {idCampo, idActividad, valor};
            resp = resp && eAgregar.insertar(aAgregar);
        }

        return resp;
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
                    c.setCatalogo(rs.getString("catalogo"));
                    CampoValor cv = new CampoValor(c);
                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoValor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaValor;
    }

    //Crea una lista de CampoCatalogoValor, donde los valores dependen del 
    //elemento cuyo id es pasado por parametro, los campos y valores son seteados
    public static ArrayList<CampoValor> listarCamposValores(int idActividad) {
        try {
            ArrayList<CampoValor> listaValor = new ArrayList<>(0);
            Entity eCampo = new Entity(0, 2);//SELECT CAMPO
            String[] ATRIBUTO = {
                "id_campo",
                "id_tipo_actividad",
                "nombre_campo",
                "tipo_campo",
                "longitud",
                "obligatorio",
                "valor",
                "catalogo"
            };
            String[] tabABuscar = {
                TABLAS[0],
                TABLAS[1],
                TABLAS[2]
            };
            String[] colCondicion = {"id_actividad"};
            Object[] colValor = {idActividad};
            try (ResultSet rs = eCampo.naturalJoin(ATRIBUTO, tabABuscar, colCondicion, colValor)) {
                if (rs != null) {
                    while (rs.next()) {
                        CampoValor cv = new CampoValor();
                        cv.setValor(rs.getString(ATRIBUTO[6]));
                        
                        Campo c = new Campo();
                        c.setIdCampo(rs.getInt(ATRIBUTO[0]));
                        c.setIdTipoActividad(rs.getInt(ATRIBUTO[1]));
                        c.setNombre(rs.getString(ATRIBUTO[2]));
                        c.setTipo(rs.getString(ATRIBUTO[3]));
                        c.setLongitud(rs.getInt(ATRIBUTO[4]));
                        c.setObligatorio(rs.getBoolean(ATRIBUTO[5]));
                        c.setCatalogo(rs.getString(ATRIBUTO[7]));
                        cv.setCampo(c);

                        listaValor.add(cv);
                    }

                }
                rs.close();
            }
 
            return listaValor;
        } catch (SQLException ex) {
            Logger.getLogger(CampoValor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean modificar(CampoValor campo, int idAct) {
        Entity e = new Entity(2, 6);//Update valor

        String[] condColumnas = {ATRIBUTOS[0], ATRIBUTOS[1], ATRIBUTOS[2]}; //id_campo, id_actividad, valor
        String val = campo.getValor();
        Object[] valores = {campo.getCampo().getIdCampo(), idAct, val};
        String[] colModificar = {ATRIBUTOS[2]}; //valor
        String[] valorCampo = {valor};

        return e.modificar(condColumnas, valores, colModificar, valorCampo);
    }
}
