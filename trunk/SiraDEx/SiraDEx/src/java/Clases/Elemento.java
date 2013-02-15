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
//Clase para ser usada como estructura para el pasaje de elementos de un catálogo
//al momento de hacer una lista desplegable de optionsCollection
public class Elemento {

    private String contenido;

    public Elemento(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public static ArrayList<Elemento> listarCamposValor(String nombre) {
        try {
            Entity eCat = new Entity(0, 8);


            String[] cat = {"nombre"};
            String[] idCatalogo = {"id_cat"};
            String[] nombreCat = {nombre};
            ResultSet r = eCat.proyectar(idCatalogo, cat, nombreCat);
            r.next();
            int idCat = r.getInt(1);
            ArrayList<ElementoCatalogo> programas = Clases.ElementoCatalogo.listarElementosId(idCat);
            Iterator it = programas.iterator();
            ArrayList<Elemento> contenidos = new ArrayList<>(0);
            int j = 0;
            while (it.hasNext()) {
                ElementoCatalogo ec = (ElementoCatalogo) it.next();
                ArrayList<CampoCatalogoValor> prog = ec.getCamposValores();
                int i = 0;
                String valor = "";
                for (i = 0; i < prog.size(); i++) {
                    valor += prog.get(i).getValor() + ", ";
                }
                Elemento e = new Elemento(valor);
                valor = valor.substring(0, valor.length() - 2);
                e.setContenido(valor);
                contenidos.add(e);
                j++;
            }
            return contenidos;
        } catch (SQLException ex) {
            Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<Elemento> programas = listarCamposValor("Coordinaciones");
        Iterator it = programas.iterator();

        while (it.hasNext()) {
            Elemento e = (Elemento) it.next();
            System.out.print(e.getContenido() + " ");
            System.out.println();
        }
    }
}
