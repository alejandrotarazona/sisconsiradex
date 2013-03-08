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
 * Clase de verificaciones. Cada método recibe parámetros y retorna true en caso
 * de no encontrar algún error, retorna false en caso contratio.
 *
 * @author SisCon
 */
public class Verificaciones {
    
    
    /**
     * Verifica que el valor de un campo sea vacío esto incluye también que sea 
     * puros espacios blancos.
     * @param nombreCampo
     * @param cadena
     * @return Mensaje de error en caso de que la cadena pasada como parámetro
     * sea vacía, null en caso contrario.
     */    
    public static String verifVacio(String nombreCampo, String cadena) {
        
        Matcher match = Pattern.compile("^[ ]*$").matcher(cadena);
            if (match.lookingAt()) {
                return "Error: El campo '" + nombreCampo + "' es obligatorio.";
            }
       return null;
    }

    /**
     * Verifica que el valor de un campo cumpla con la longitud, el patrón dado
     * y si puede ser vacío o no.
     *
     * @param nombreCampo Cadena con el nombre del campo a verificar.
     * @param cadena Cadena con el valor del campo.
     * @param longitud Entero que representa la longitud máxima del valor.
     * @param patron Patrón que debe cumplir el valor.
     * @param desc Cadena que describe el patrón dado.
     * @param obliga Booleano que indica si el campo es obligatorio (no vacío).
     * @return Mensaje de error en caso de que la cadena pasada como parámetro
     * sea más larga al parámetro longitud, no cumpla con el patrón pasado como
     * parámetro o sea vacía, null en caso contrario.
     */
    public static String verifLongPat(String nombreCampo, String cadena, int longitud,
            Pattern patron, String desc, boolean obliga) {
        
        
        if (obliga) {
            return verifVacio(nombreCampo, cadena);
        }

        if (cadena.length() > longitud) {
            return "Error: El campo '" + nombreCampo + "' puede contener "
                    + "a lo sumo " + longitud + " carateres";
        }
        Matcher match = patron.matcher(cadena);

        if (!match.lookingAt()) {
            return "Error: El campo '" + nombreCampo + "' " + desc;
        }

        return null;
    }


    /**
     * Verifica que todos los campos fijos de un Tipo de Actividad tengan un
     * valor válido.
     *
     * @param ta Tipo de Actividad a verificar.
     * @return true en caso de estar correctamente inicializados los valores.
     * @return false en caso de no estar correctamente inicializados los
     * valores.
     */
    public static boolean verif(TipoActividad ta) {

        Pattern alfanumerico = Pattern.compile("^[a-zA-Z áéíóúAÉÍÓÚÑñ0-9]+$");
        String respVerif = verifLongPat("Nombre de la Actividad", ta.getNombreTipo(),
                140,alfanumerico,"debe contener sólo carateres alfanuméricos",true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verifLongPat("Descripción", ta.getDescripcion(), 200, 
                alfanumerico,"debe contener sólo carateres alfanuméricos",true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }


        respVerif = verifVacio("Tipo", ta.getTipoPR());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }
        
        respVerif = verifVacio("Programa", ta.getPrograma());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }
        
        respVerif = verifVacio("Coordinación a validar", ta.getValidador());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }
        
        String[] permisos = ta.getPermisos();
        if (permisos == null){
            ta.setMensaje("Error: El campo 'Realizado por' es obligatorio.");
            return false;
        }
        
        respVerif = verifLongPat("Producto", ta.getProducto(), 50, alfanumerico,
                "debe contener sólo carateres alfanuméricos", true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }
        
        Pattern numerico = Pattern.compile("^[ ]*[0-9]+[ ]*$");
        int nro = ta.getNroCampos();
        if (nro == 0) {
            ta.setMensaje("Error: El campo 'Número de Campos' debe contener al "
                    + "menos 1 como valor");
        }
        respVerif = verifLongPat("Número de Campos", String.valueOf(ta.getNroCampos()),
                2, numerico, "debe contener sólo números",true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        return true;

    }

    /**
     * Verifica la inicialización de cada uno de los campos entrantes basandose
     * en que el Nombre sea correto, no sea 'null' ni conste sólo de espacios,
     * en caso de que sea de tipo texto, verifica que la longitud sea mayor que
     * cero (0) y si es de tipo catalogo, verifica que se haya seleccionado un
     * catálogo válido.
     *
     * @param campos Arreglo de campos que se deben verificar.
     * @return true si están correctamente inicializados los campos.
     */
    public static boolean verif(ArrayList<Campo> campos) {
        boolean resp = true;
        Iterator it = campos.iterator();
        Pattern limpiar;
        Matcher buscar;
        while (it.hasNext()) {
            try {
                Campo campo = (Campo) it.next();
                limpiar = Pattern.compile("[a-zA-Z]+");
                buscar = limpiar.matcher(campo.getNombre());
                resp = resp && buscar.lookingAt();
                if (resp && campo.getTipo().equalsIgnoreCase("texto")) {
                    resp = resp && (campo.getLongitud() > 0);
                } else if (resp && campo.getTipo().equalsIgnoreCase("catalogo")) {
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

    public static void main(String[] args) {

        boolean resp;
        //Pattern patron = Pattern.compile("([a-zA-Z]|[0-9])+"); //alfanumerico de Alejandro
        //problema: admite que sea cualquier cosa siempre y cuando haya un caracter alfanumerico 

        //Pattern patron = Pattern.compile("^[a-zA-Z áéíóúAÉÍÓÚÑñ0-9]+$");//alfanumerico de Jorge
        //problema: admite que sea puros espacios en blanco, se usa otro patron en verif para resolverlo 

        //Pattern patron = Pattern.compile("[0-9]+"); //numerico de Alejandro
        //problema: admite que sea cualquier cosa siempre y cuando empiece con un numero

        //Pattern patron = Pattern.compile("^[ ]*[0-9]+[ ]*$"); //numerico de Jorge
        //no tiene problemas, admite espacios-numeros-espacios, numeros-espacios y espacios-numeros.

        Pattern patron = Pattern.compile("^[ ]*$");// vacío o espacios

        String prueba1 = "ACVBó 8mñ";
        String prueba2 = "A%$<5>';&";
        String prueba3 = "  ";
        Matcher buscar = patron.matcher(prueba1);
        resp = buscar.lookingAt();
        if (!resp) {
            System.out.println("Error: El campo no cumple con el patrón");
        } else {
            System.out.println("prueba1 cumple con el patrón");
        }
        buscar = patron.matcher(prueba2);
        resp = buscar.lookingAt();
        if (!resp) {
            System.out.println("Error: El campo no cumple con el patrón");
        } else {
            System.out.println("prueba2 cumple con el patrón");
        }
        buscar = patron.matcher(prueba3);
        resp = buscar.lookingAt();
        if (!resp) {
            System.out.println("Error: El campo no cumple con el patrón");
        } else {
            System.out.println("Aprueba3 cumple con el patrón");
        }

    }
}
