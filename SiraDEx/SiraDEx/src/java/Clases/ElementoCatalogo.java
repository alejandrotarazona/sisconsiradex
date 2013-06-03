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
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class ElementoCatalogo extends Root implements Serializable, Comparable<ElementoCatalogo> {

    private int idElemento;
    private int idCatalogo;
    private String nombreCatalogo;
    private ArrayList<CampoCatalogoValor> camposValores;
    private String contenido;
    private static String[] ATRIBUTOS = {
        "id_elemento", //0
        "id_catalogo" //1
    };

    public ElementoCatalogo() {
    }

    public ElementoCatalogo(String contenido) {
        this.contenido = contenido;
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento() {
        Entity eElemento = new Entity(8);//ELEMENTO_CATALOGO
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

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean agregar() {

        if (!Verificaciones.verif(this)) {
            return false;
        }

        Entity eElemento = new Entity(8);//ELEMENTO_CATALOGO
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
            ccv.agregar(idElemento);
        }

        if (!resp) {
            mensajeError = "Error: El elemento no pudo ser registrado.";
        }

        return resp;
    }

    public boolean eliminar() {
        Entity e = new Entity(8);//ELEMENTO_CATALOGO
        return e.borrar("id_elemento", idElemento);
    }

    public static ArrayList<ElementoCatalogo> listarElementosId(int idCat) {

        ArrayList<ElementoCatalogo> resp = new ArrayList<>(0);
        Entity eBuscar = new Entity(8);//ELEMENTO_CATALOGO
        String[] columnas = {
            ATRIBUTOS[1]
        };
        Object[] valores = {
            idCat
        };

        ResultSet rs = eBuscar.seleccionar(columnas, valores);
        try {
            if (rs != null) {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();
                    ec.idElemento = rs.getInt(ATRIBUTOS[0]);
                    ec.camposValores = CampoCatalogoValor.listarCamposValores(ec.idElemento);
                    resp.add(ec);
                }
                rs.close();
            }
            return resp;
        } catch (SQLException ex) {
            Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean modificar(ArrayList camposNM) {
        if (!Verificaciones.verif(this)) {
            return false;
        }

        Iterator it = camposNM.iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoCatalogoValor campoNM = (CampoCatalogoValor) it.next();
            camposValores.get(i).modificar(campoNM, idElemento);
        }

        return true;
    }

    //retorna una lista con los valores de los elementos del catalogo dado
    public static ArrayList<ElementoCatalogo> listarElementos(String catalogo,
            int valores) {

        Entity eCat = new Entity(6);//CATALOGO


        String[] cat = {"nombre"};
        String[] idCatalogo = {"id_cat"};
        String[] nombreCat = {catalogo};
        int idCat;
        ResultSet rs = eCat.proyectar(idCatalogo, cat, nombreCat);
        try {
            rs.next();
            idCat = rs.getInt(1);
            if (rs != null) {
                rs.close();
            }

            ArrayList<ElementoCatalogo> elementos;
            elementos = Clases.ElementoCatalogo.listarElementosId(idCat);
            Iterator it = elementos.iterator();
            ArrayList<ElementoCatalogo> contenidos = new ArrayList<>(0);
            int j = 0;
            while (it.hasNext()) {
                ElementoCatalogo ec = (ElementoCatalogo) it.next();
                ArrayList<CampoCatalogoValor> elem = ec.getCamposValores();
                int i;
                String valor = "";
                if (valores == 0) {
                    //si valores es 0 se cargan todos los valores para cada elemento
                    valores = elem.size();
                }
                for (i = 0; i < valores; i++) {
                    valor += elem.get(i).getValor() + ", ";
                }
                ElementoCatalogo e = new ElementoCatalogo(valor);
                valor = valor.substring(0, valor.length() - 2);
                e.setContenido(valor);
                contenidos.add(e);
                j++;
            }
            Collections.sort(contenidos);
            return contenidos;
        } catch (SQLException ex) {
            Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int compareTo(ElementoCatalogo e) {

        return contenido.compareTo(e.getContenido());
    }

    public static ArrayList<ElementoCatalogo> listarParticipantes() {

        ArrayList<ElementoCatalogo> listaElementoCatalogo = new ArrayList<>(0);

        Entity eBuscar = new Entity(5);//PARTICIPA
        String[] tablas = {
            "ACTIVIDAD",
            "USUARIO"
        };
        String cols = "p.usbid, nombres, apellidos";
        String[] joins = {"p.id_act=a.id_actividad", "p.usbid=u.usbid"};
        ResultSet rs = eBuscar.seleccionarSinRepeticion(tablas, cols, "LEFT OUTER JOIN", joins, "");
        if (rs != null) {
            try {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();

                    String contenido;
                    String usbid = rs.getString("usbid");

                    if (rs.getString("nombres") != null) {//el participante es usuario
                        contenido = usbid + ", " + rs.getString("nombres")
                                + " " + rs.getString("apellidos");
                    } else {//el participante no es usuario
                        contenido = usbid.substring(1) + " (No es usuario)";
                    }

                    ec.setMensaje(usbid);
                    ec.setContenido(contenido);

                    listaElementoCatalogo.add(ec);
                }

                rs.close();

                return listaElementoCatalogo;
            } catch (SQLException ex) {
                Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
