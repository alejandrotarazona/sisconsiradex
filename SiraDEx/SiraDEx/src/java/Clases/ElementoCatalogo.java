/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class ElementoCatalogo extends Root {

    private int idElemento;
    private int idCatalogo;
    private ArrayList<CampoCatalogoValor> camposValores;
    private static String[] ATRIBUTOS = {
        "id_elemento", //0
        "id_catalogo" //1
    };

    public ElementoCatalogo() {
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento() {
        Entity eElemento = new Entity(0, 10);
        this.idElemento = eElemento.seleccionarMaxId("id_elemento");
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public ArrayList<CampoCatalogoValor> getCamposValores() {
        return camposValores;
    }

    public void setCamposValores(ArrayList<CampoCatalogoValor> camposValores) {
        this.camposValores = camposValores;
    }

    public CampoCatalogoValor getCampoValor(int i) {
        return camposValores.get(i);
    }

    public boolean agregar() {
        Entity eElemento = new Entity(1, 10);
        boolean resp = true;

        String[] columnas = {
            "id_catalogo"
        };
        Integer idCat = new Integer(this.idCatalogo);
        Object[] valores = {
            idCat
        };
        resp &= eElemento.insertar2(columnas, valores);
        if (resp) {
            setIdElemento();
        } else {
            return resp;
        }

        Iterator itValores = this.camposValores.iterator();

        while (itValores.hasNext() && resp) {
            CampoCatalogoValor ccv = (CampoCatalogoValor) itValores.next();
            resp &= ccv.agregar(this.idElemento);
        }

        return resp;
    }

    public boolean eliminar() {
        Entity e = new Entity(5, 10);
        return e.borrar("id_elemento", idElemento);
    }

    public static ArrayList<ElementoCatalogo> listarElementos() {
        ArrayList<ElementoCatalogo> listaElementoCatalogo = new ArrayList<>(0);
        Entity eElementoCatalogo = new Entity(0, 10);

        ResultSet rs = eElementoCatalogo.listar();

        if (rs != null) {
            try {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();

                    ec.setIdElemento(rs.getInt(ElementoCatalogo.ATRIBUTOS[0]));

                    int id = rs.getInt(ElementoCatalogo.ATRIBUTOS[1]);
                    ec.setIdCatalogo(id);

                    listaElementoCatalogo.add(ec);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaElementoCatalogo;
    }

    public static ArrayList<ElementoCatalogo> listarElementosId(int idCat) {
        ArrayList<ElementoCatalogo> resp = new ArrayList<>(0);
        Entity eBuscar = new Entity(0, 10);
        String[] columnas = {
            ATRIBUTOS[1]
        };
        Integer id = new Integer(idCat);
        Object[] valores = {
            id
        };

        ResultSet rs = eBuscar.seleccionar(columnas, valores);
        if (rs != null) {
            try {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();
                    ec.setIdElemento(rs.getInt(ElementoCatalogo.ATRIBUTOS[0]));

                    ec.camposValores = CampoCatalogoValor.listarCamposValores(ec.idElemento);
                    resp.add(ec);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return resp;
    }

    
}
