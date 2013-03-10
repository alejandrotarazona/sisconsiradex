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
 *
 * @author SisCon
 */
public class Verificaciones {

    /**
     * Verifica que el valor de un campo sea vacío esto incluye también que sea
     * puros espacios blancos.
     *
     * @param nombreCampo Cadena con el nombre del campo a verificar.
     * @param cadena Cadena con el valor del campo.
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
     * Verifica que el valor de un campo cumpla con la longitud dada, el patrón
     * dado y si puede ser vacío o no.
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
    public static String verifLPV(String nombreCampo, String cadena, int longitud,
            Pattern patron, String desc, boolean obliga) {

        if (obliga) {
            String respVerif = verifVacio(nombreCampo, cadena);
            if (respVerif != null) {
                return respVerif;
            }
        }

        if (cadena.length() > longitud) {
            return "Error: El campo " + nombreCampo + " puede contener "
                    + "a lo sumo " + longitud + " carateres.";
        }

        Matcher match = patron.matcher(cadena);
        if (!match.lookingAt()) {
            return "Error: El campo " + nombreCampo + " " + desc;
        }

        return null;
    }

    /**
     * Verifica que todos los campos fijos de un Tipo de Actividad tengan un
     * valor válido.
     *
     * @param ta Tipo de Actividad a verificar.
     * @return true si los valores son validos, de lo contrario retorna false.
     */
    public static boolean verifCF(TipoActividad ta) {

        Pattern alfanumerico = Pattern.compile("^[a-zA-Z áéíóúAÉÍÓÚÑñ0-9]+$");
        String respVerif = verifLPV("'Nombre de la Actividad'", ta.getNombreTipo(),
                140, alfanumerico, "debe contener sólo carateres alfanuméricos", true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verifLPV("'Descripción'", ta.getDescripcion(), 200,
                alfanumerico, "debe contener sólo carateres alfanuméricos", true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }


        respVerif = verifVacio("'Tipo'", ta.getTipoPR());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verifVacio("'Programa'", ta.getPrograma());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verifVacio("'Coordinación a validar'", ta.getValidador());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        String[] permisos = ta.getPermisos();
        if (permisos == null) {
            ta.setMensaje("Error: El campo 'Realizado por' es obligatorio.");
            return false;
        }

        respVerif = verifLPV("'Producto'", ta.getProducto(), 50, alfanumerico,
                "debe contener sólo carateres alfanuméricos.", true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        Pattern numerico = Pattern.compile("^[ ]*[0-9]+[ ]*$");
        String nro = String.valueOf(ta.getNroCampos());
        if (nro.equals("0")) {
            ta.setMensaje("Error: El campo 'Número de Campos' debe contener al "
                    + "menos 1 como valor.");
            return false;
        }
        respVerif = verifLPV("'Número de Campos'", nro, 2, numerico,
                "debe contener sólo números.", true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        return true;

    }

    /**
     * Verifica que todos los campos del Tipo de Actividad pasado por parámetro
     * tengan un nombre válido, una longitud válida y de haber un campo de tipo
     * catálogo que se haya seleccionado algún catálogo para dicho campo.
     *
     * @param ta Tipo de Actividad a verificar.
     * @return true si los nombres y valores son validos, de lo contrario
     * retorna false.
     */
    public static boolean verifCV(TipoActividad ta) {

        Iterator it = ta.getCampos().iterator();
        for (int i = 1; it.hasNext(); i++) {
            Campo campo = (Campo) it.next();
            String tipo = campo.getTipo();
            String nombre = campo.getNombre();
            String nroCampo = "número "+String.valueOf(i);
            
            /*verifica que el nombre sea válido (alfanúmerico, no vacío, a lo 
             * sumo 100 caracteres)*/ 
            Pattern alfanumerico = Pattern.compile("^[a-zA-Z áéíóúAÉÍÓÚÑñ0-9]+$");
            String respVerif = verifLPV(nroCampo, nombre, 100, alfanumerico, 
                    "debe contener sólo carateres alfanuméricos.", true);
            if (respVerif != null) {
                ta.setMensaje(respVerif);
                return false;
            }
            
            /*verifica que la longitud sea válida (númerica, no vacía, a lo 
             * sumo 3 caracteres, mayor que 0)*/
            if (tipo.equals("texto") || tipo.equals("textol")
                    || tipo.equals("numero")) {

                Pattern numerico = Pattern.compile("^[ ]*[0-9]+[ ]*$");
                String nroLong = String.valueOf(campo.getLongitud());
                respVerif = verifLPV(nroCampo, nroLong, 3, numerico,
                        "debe contener sólo números", true);
                if (respVerif != null) {
                    ta.setMensaje(respVerif);
                    return false;
                }

                if (nroLong.equals("0")) {
                    ta.setMensaje("Error: El campo número " + i + " debe contener "
                            + "al menos 1 como valor para su Longitud.");
                    return false;
                }
            }
            
            /*verifica que si el tipo es catálogo el valor de catalogo no sea vacío*/
            if (tipo.equals("catalogo") && campo.getCatalogo().equals("")) {
                ta.setMensaje("Error: Debe seleccionar un catálogo para el "
                        + "campo número " + i+".");
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica que todos los campos de la Actividad pasada por parámetro tengan
     * un valor válido.
     *
     * @param act Actividad a verificar
     * @return true si los valores son validos, de lo contrario retorna false.
     
    public static boolean verif(Actividad act) {
        
        Iterator it = act.getCamposValores().iterator();
        Pattern patron;
        while (it.hasNext()) {

            CampoValor campoV = (CampoValor) it.next();
            String tipo = campoV.getCampo().getTipo();
            String nombre = campoV.getCampo().getNombre();
            if (tipo.equals("texto") || tipo.equals("textol")) {
                patron = Pattern.compile("^[a-zA-Z áéíóúAÉÍÓÚÑñ0-9]+$");
            }
            if (tipo.equals("numero")) {
                patron = Pattern.compile("^[ ]*[0-9]+[ ]*$");
            }
            if (tipo.equals("catalogo") && campo.getCatalogo().equals("")) {
                ta.setMensaje("");
            }

                    ///FALTA TERMINAR
        }


        return true;
    }*/

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
        //problema: admite que sea puros espacios en blanco, se usa otro patron en verifCF para resolverlo 

        //Pattern patron = Pattern.compile("[0-9]+"); //numerico de Alejandro
        //problema: admite que sea cualquier cosa siempre y cuando empiece con un numero

        Pattern patron = Pattern.compile("^[ ]*[0-9]+[ ]*$"); //numerico de Jorge
        //no tiene problemas, admite espacios-numeros-espacios, numeros-espacios y espacios-numeros.

        //Pattern patron = Pattern.compile("^[ ]*$");// vacío o espacios

        String prueba1 = "ACVBó 8mñ";
        String prueba2 = "A%$<5>';&";
        int i = -1;

        String prueba3 = String.valueOf(i);
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
            System.out.println("prueba3 cumple con el patrón");
        }

    }
}
