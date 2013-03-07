/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de verificaciones. Cada método recibe parametros y regresa true en caso
 * de haber verificado bien. En caso de encontrar algún error en los patrones.
 *
 * @author SisCon
 */
public class Verificaciones {

    /**
     * Verifica la correcta inicializacion de los nombres de campo y los nombres
     * de Tipo de Actividad, Descripción, Nombre del archivo de Producto y
     * Numero de Campos.
     *
     * @param ta Tipo de Actividad a verificar.
     * @return true en caso de estar correctamente inicializados los valores.
     * @return false en caso de no estar correctamente inicializados los
     * valores.
     */
    public static boolean verif(TipoActividad ta) {
        boolean resp = true;
        Pattern limpiar = Pattern.compile("([a-zA-Z]|[0-9])+");

        Matcher buscar = limpiar.matcher(ta.getNombreTipo());
        resp = resp && (buscar.lookingAt());
        System.out.println(ta.getNombreTipo() + " " + resp);
        buscar = limpiar.matcher(ta.getDescripcion());
        resp = resp && (buscar.lookingAt());
        System.out.println(ta.getDescripcion() + " " + resp);
        buscar = limpiar.matcher(ta.getProducto());
        resp = resp && (buscar.lookingAt());
        System.out.println(ta.getProducto() + " " + resp);
        buscar = limpiar.matcher(ta.getValidador());
        resp = resp && (buscar.lookingAt());
        System.out.println(ta.getValidador() + " " + resp);
        limpiar = Pattern.compile("[0-9]+");
        String aux = String.valueOf(ta.getNroCampos());
        buscar = limpiar.matcher(aux);
        resp = resp && (buscar.lookingAt());
        System.out.println(ta.getNroCampos() + " " + resp);

        return resp;

    }
    /**
     * Verifica la inicialización de cada uno de los campos entrantes 
     * basandose en que el Nombre sea correto, no sea 'null' ni conste
     * sólo de espacios, en caso de que sea de tipo texto, verifica que
     * la longitud sea mayor que cero (0) y si es de tipo catalogo, verifica
     * que se haya seleccionado un catálogo válido.
     * @param campos Arreglo de campos que se deben verificar.
     * @return true si están correctamente inicializados los campos.
     */
    public static boolean verif(ArrayList<Campo> campos){
        boolean resp = true;
        Iterator it = campos.iterator();
        Pattern limpiar;
        Matcher buscar;
        while(it.hasNext()){
            try {
                    Campo campo = (Campo)it.next();
                    limpiar = Pattern.compile("[a-zA-Z]+");
                    buscar = limpiar.matcher(campo.getNombre());
                    resp = resp && buscar.lookingAt();
                    if(resp && campo.getTipo().equalsIgnoreCase("texto")){
                        resp = resp && (campo.getLongitud()>0);
                    } else if(resp && campo.getTipo().equalsIgnoreCase("catlogo")){
                        resp = resp && (!campo.getCatalogo().equalsIgnoreCase(""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
        }
        
        return resp;
    }

    /**
     * Funcion que verifica que el valor de una actividad se corresponda con el
     * tipo del campo respectivo.
     *
     * @param campo Campo con el que hacer la correspondencia.
     * @param valor Valor de la actividad.
     * @return true si hay correspondencia
     * @return false si no hay correspondencia.
     */
    public static boolean verif(Campo campo, String valor) {
        boolean resp = true;

        String tipo_campo = campo.getTipo();
        String[] posibles_tipos = Campo.getTIPOS();

        if (tipo_campo.equals(posibles_tipos[0])) {
            Pattern limpiar = Pattern.compile("(([a-zA-Z0-9_-])+s*)*");
            Matcher buscar = limpiar.matcher(valor);
            resp = resp && buscar.lookingAt();
            resp = resp && valor.length() <= campo.getLongitud();

        } else if (tipo_campo.equals(posibles_tipos[1])) {
            Pattern limpiar = Pattern.compile("[0-9]*");
            Matcher buscar = limpiar.matcher(valor);
            resp = resp && buscar.lookingAt();

        } else if (tipo_campo.equals(posibles_tipos[2])) {
            /**
             * Pattern limpiar =
             * Pattern.compile("([0-3][0-9])/([0-1][0-9])/([0-9][0-9][0-9][0-9])");
             * Matcher buscar = limpiar.matcher(valor); resp = resp &&
             * buscar.lookingAt(); Calendar corroboracion =
             * Calendar.getInstance(); int dia =
             * Integer.parseInt(buscar.group(1)); int mes =
             * Integer.parseInt(buscar.group(2)); int ano =
             * Integer.parseInt(buscar.group(3)); corroboracion.set(ano, mes,
             * dia); resp = resp && (corroboracion != null);
             */
            resp = true;
        } else if (tipo_campo.equals(posibles_tipos[3])) {
            Pattern limpiar = Pattern.compile("([a-zA-Z0-9_-])+\\.pdf");
            Matcher buscar = limpiar.matcher(valor);
            resp = true;
        } else if (tipo_campo.equals(posibles_tipos[4])) {
            Pattern limpiar = Pattern.compile("true|false");
            Matcher buscar = limpiar.matcher(valor);
            resp = resp && buscar.lookingAt();
        }

        resp = resp && valor.length() < 1400;

        return resp;
    }
    
    /**
     * Funcion que verifica que el valor de un elemento se corresponda con el
     * tipo del campo del catalogo respectivo.
     *
     * @param campo CampoCatalogo con el que hacer la correspondencia.
     * @param valor Valor del Elemento.
     * @return true si hay correspondencia
     * @return false si no hay correspondencia.
     */
    public static boolean verif(CampoCatalogo campo, String valor) {
        boolean resp = true;

        String tipo_campo = campo.getTipo();
        String[] posibles_tipos = CampoCatalogo.getTIPOS();

        if (tipo_campo.equals(posibles_tipos[0])) {
            Pattern limpiar = Pattern.compile("(([a-zA-Z0-9_-])+s*)*");
            Matcher buscar = limpiar.matcher(valor);
            resp = resp && buscar.lookingAt();

        } else if (tipo_campo.equals(posibles_tipos[1])) {
            Pattern limpiar = Pattern.compile("[0-9]*");
            Matcher buscar = limpiar.matcher(valor);
            resp = resp && buscar.lookingAt();

        } else {
            resp = true;
        } 

        resp = resp && valor.length() < 1400;

        return resp;
    }
}
