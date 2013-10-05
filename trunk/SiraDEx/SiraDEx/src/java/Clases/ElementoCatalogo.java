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

    public boolean usuarioExistente(String usbid) {

        Entity e = new Entity(17);//ELEMENTOS

        String[] atrib = {"id_catalogo", "tipo", "valor"};
        Object[] valor = {idCatalogo, "usbid", usbid};
        ResultSet rs = e.seleccionar(atrib, valor);
        try {
            if (rs != null) {
                if (rs.next()) {
                    rs.close();
                    return true;
                }
                rs.close();
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getCampoUsuario() {
        for (CampoCatalogoValor ccv : camposValores) {
            if (ccv.getCampo().getTipo().equals("usbid")) {
                return ccv.getValor();
            }
        }
        return null;
    }

    public boolean agregar(String ip, String user) {

        if (!Verificaciones.verificar(this)) {
            return false;
        }

        String usbid = getCampoUsuario();

        if (usbid != null && usuarioExistente(usbid)) {
            mensaje = "Error: Ya existe un elemento con el USB-ID '" + usbid + "' en el Catálogo.";
            return false;
        }

        Entity eElemento = new Entity(8);//ELEMENTO_CATALOGO
        boolean resp;
        String[] columnas = {"id_catalogo"};
        Object[] valores = {idCatalogo};
        if (resp = eElemento.insertar2(columnas, valores)) {

            mensaje = "El elemento ha sido registrado con éxito";

            idElemento = eElemento.seleccionarMaxId("id_elemento");

            Iterator itValores = camposValores.iterator();

            while (itValores.hasNext() && resp) {
                CampoCatalogoValor ccv = (CampoCatalogoValor) itValores.next();
                ccv.agregar(idElemento, ip, user);
            }

            if (!resp) {
                mensaje = "Error: El elemento no pudo ser registrado.";
                if (!eliminar(ip, user)) {
                    mensaje = " Error: El elemento 'no pudo ser resgistrado"
                            + " satisfactoriamente, en caso  de que aparezca,"
                            + " por favor, elimínelo.";
                }
            }
        } else {
            mensaje = "Error: El elemento no pudo ser registrado.";
        }
        return resp;
    }
    

    public boolean eliminar(String ip, String user) {
        Entity e = new Entity(8);//ELEMENTO_CATALOGO

        if (e.borrar("id_elemento", idElemento)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El elemento ha sido eliminado con éxito";
            return true;
        } else {
            mensaje = "Error: El elemento no pudo ser eliminado.";
            return false;
        }
    }

    public boolean modificar(String usbidNM, String ip, String user) {
        if (!Verificaciones.verificar(this)) {
            return false;
        }

        String usbid = getCampoUsuario();

        if (usbid != null && !usbidNM.equals(usbid) && usuarioExistente(usbid)) {
            mensaje = "Error: Ya existe un elemento con el USB-ID '" + usbid + "' en el Catálogo.";
            return false;
        }

        for (int i = 0; i < camposValores.size(); i++) {

            if (!camposValores.get(i).modificar(idElemento, ip, user)) {
                mensaje = "Error: El elemento no pudo ser modificado satisfactoriamente";
                return false;
            }
        }

        mensaje = "El elemento ha sido modificado con éxito.";

        return true;
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
            Logger.getLogger(ElementoCatalogo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //retorna una lista donde cada elemento tiene en el atributo contenido la cantidad dada
    //de valores concatenados de cada elemento del catalogo dado, concatena al principio de la cadena
    //el valor del campo tipo usbid de ser un catalogo de usuarios.
    public static ArrayList<ElementoCatalogo> listarElementos(String catalogo,
            int cantidad) {

        Entity eCat = new Entity(6);//CATALOGO


        String[] cat = {"nombre"};
        String[] idCatalogo = {"id_cat"};
        String[] nombreCat = {catalogo};
        int idCat;
        ResultSet rs = eCat.proyectar(idCatalogo, cat, nombreCat);
        try {
            rs.next();
            idCat = rs.getInt(1);
            rs.close();

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
                if (cantidad == 0) {
                    //si cantidad es 0 se cargan todos los cantidad para cada elemento
                    cantidad = elem.size();
                }
                String valusbid = "";
                boolean usbid = false;
                for (i = 0; i < cantidad; i++) {
                    String val = elem.get(i).getValor();
                    if (Verificaciones.esVacio(val)) {
                        val = "(vacío)";
                    }
                    if (!usbid
                            && elem.get(i).getCampo().getTipo().equals("usbid")) {
                        valusbid = val;
                        usbid = true;
                        continue;
                    }
                    valor += val + ", ";
                }
                if (usbid) {
                    valor = valusbid + ", " + valor;
                }

                ElementoCatalogo e = new ElementoCatalogo(valor);
                valor = valor.substring(0, valor.length() - 2);
                e.setContenido(valor);
                e.setMensaje(valusbid);
                contenidos.add(e);
                j++;
            }
            Collections.sort(contenidos);
            return contenidos;


        } catch (SQLException ex) {
            Logger.getLogger(ElementoCatalogo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
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
                Logger.getLogger(ElementoCatalogo.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static ArrayList<ElementoCatalogo> listarUsuarios() {

        ArrayList<ElementoCatalogo> listaElementoCatalogo = new ArrayList<>(0);

        Entity e = new Entity(0);//USUARIO

        ResultSet rs = e.listar();
        if (rs != null) {
            try {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();

                    String contenido;
                    String usbid = rs.getString("usbid");

                    contenido = usbid + ", " + rs.getString("nombres")
                            + " " + rs.getString("apellidos");

                    ec.setMensaje(usbid);
                    ec.setContenido(contenido);

                    listaElementoCatalogo.add(ec);
                }

                rs.close();

                return listaElementoCatalogo;


            } catch (SQLException ex) {
                Logger.getLogger(ElementoCatalogo.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
