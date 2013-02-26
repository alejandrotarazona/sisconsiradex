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

    //retorna una lista con los valores de los elemenos del catalogo dado
    public static ArrayList<Elemento> listarElementos(String catalogo, int valores) {
        try {
            Entity eCat = new Entity(0, 8);


            String[] cat = {"nombre"};
            String[] idCatalogo = {"id_cat"};
            String[] nombreCat = {catalogo};
            ResultSet r = eCat.proyectar(idCatalogo, cat, nombreCat);
            r.next();
            int idCat = r.getInt(1);
            ArrayList<ElementoCatalogo> elementos;
            elementos = Clases.ElementoCatalogo.listarElementosId(idCat);
            Iterator it = elementos.iterator();
            ArrayList<Elemento> contenidos = new ArrayList<>(0);
            int j = 0;
            while (it.hasNext()) {
                ElementoCatalogo ec = (ElementoCatalogo) it.next();
                ArrayList<CampoCatalogoValor> elem = ec.getCamposValores();
                int i = 0;
                String valor = "";
                if (valores > elem.size()){
                    //si valores es mayor que el numero de campos de un elemento
                    //se le asigna por defecto el numero de campos
                    valores = elem.size(); 
                }
                for (i = 0; i < valores; i++) {
                    valor += elem.get(i).getValor() + ", ";
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

        ArrayList<Elemento> elementos = listarElementos("Coordinaciones",1);
        Iterator it = elementos.iterator();

        while (it.hasNext()) {
            Elemento e = (Elemento) it.next();
            System.out.print(e.getContenido() + " ");
            System.out.println();
        }
    }
}
