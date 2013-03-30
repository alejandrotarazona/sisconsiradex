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
            resp &= Verificaciones.verif(ccv.getCampo(), ccv.getValor());
            if (resp) {
                resp &= ccv.agregar(this.idElemento);
            } else {
                this.eliminar();
                return resp;
            }

        }

        return resp;
    }

    public boolean eliminar() {
        Entity e = new Entity(5, 10);
        return e.borrar("id_elemento", idElemento);
    }

    public static ArrayList<ElementoCatalogo> listarElementos() {
        try {
            ArrayList<ElementoCatalogo> listaElementoCatalogo = new ArrayList<>(0);
            Entity eElementoCatalogo = new Entity(0, 10);

            ResultSet rs = eElementoCatalogo.listar();

            if (rs != null) {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();

                    ec.setIdElemento(rs.getInt(ElementoCatalogo.ATRIBUTOS[0]));

                    int id = rs.getInt(ElementoCatalogo.ATRIBUTOS[1]);
                    ec.setIdCatalogo(id);

                    listaElementoCatalogo.add(ec);
                }

            }
            rs.close();

            return listaElementoCatalogo;
        } catch (SQLException ex) {
            Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<ElementoCatalogo> listarElementosId(int idCat) {
        try {
            ArrayList<ElementoCatalogo> resp = new ArrayList<>(0);
            Entity eBuscar = new Entity(0, 10);
            String[] columnas = {
                ATRIBUTOS[1]
            };
            Integer id = new Integer(idCat);
            Object[] valores = {
                id
            };
            try (ResultSet rs = eBuscar.seleccionar(columnas, valores)) {
                if (rs != null) {
                    while (rs.next()) {
                        ElementoCatalogo ec = new ElementoCatalogo();
                        ec.setIdElemento(rs.getInt(ElementoCatalogo.ATRIBUTOS[0]));

                        ec.camposValores = CampoCatalogoValor.listarCamposValores(ec.idElemento);
                        resp.add(ec);
                    }
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
        boolean resp = true;

        Iterator it = camposNM.iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoCatalogoValor campoNM = (CampoCatalogoValor) it.next();
            resp &= camposValores.get(i).modificar(campoNM, idElemento);
        }

        if (!resp) {
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
        }
        return resp;
    }
    
   
    //retorna una lista con los valores de los elementos del catalogo dado
    public static ArrayList<ElementoCatalogo> listarElementos(String catalogo,
            int valores) {
        try {
            Entity eCat = new Entity(0, 8);


            String[] cat = {"nombre"};
            String[] idCatalogo = {"id_cat"};
            String[] nombreCat = {catalogo};
            int idCat;
            try (ResultSet rs = eCat.proyectar(idCatalogo, cat, nombreCat)) {
                rs.next();
                idCat = rs.getInt(1);
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
    
}
