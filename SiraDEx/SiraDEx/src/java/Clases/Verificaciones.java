/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class Verificaciones {

    /**
     * Verifica que si una cadena es vacía esto incluye también que sea de puros
     * espacios blancos.
     *
     * @param cadena Cadena de caracterres.
     * @return true en caso de que la cadena pasada como parámetro sea vacía,
     * false en caso contrario.
     */
    public static boolean esVacio(String cadena) {

        if (cadena.matches("^[ ]*$")) {
            return true;
        }
        return false;
    }

    /**
     * Verifica que el valor de un campo sea vacío esto incluye también que sea
     * puros espacios blancos.
     *
     * @param nombreCampo Cadena con el nombre del campo a verificar.
     * @param valorCampo Cadena con el valor del campo.
     * @return Mensaje de error en caso de que el valor del campo pasado como
     * parámetro sea vacío, null en caso contrario.
     */
    public static String verificarVacio(String nombreCampo, String valorCampo) {

        if (esVacio(valorCampo)) {
            return "Error: El campo '" + nombreCampo + "' es obligatorio.";
        }
        return null;
    }

    /**
     * Verifica que el valor de un campo cumpla con el patrón dado.
     *
     * @param nombreCampo Cadena con el nombre del campo a verificar.
     * @param valorCampo Cadena con el valor del campo.
     * @param patron Expresión regular que debe cumplir el valor.
     * @param desc Cadena que describe el patrón dado.
     * @return Mensaje de error en caso de que el valor del campo pasado como
     * parámetro no cumpla con el patrón dado, null en caso contrario.
     */
    public static String verificarPatron(String nombreCampo, String valorCampo,
            String patron, String desc) {

        if (!valorCampo.matches(patron)) {
            return "Error: El campo " + nombreCampo + " " + desc;
        }
        return null;
    }

    /**
     * Verifica que el valor de un campo cumpla con la longitud dada y si puede
     * ser vacío o no.
     *
     * @param nombreCampo Cadena con el nombre del campo a verificar.
     * @param valorCampo Cadena con el valor del campo.
     * @param longitud Entero que representa la longitud máxima del valor.
     * @param obliga Booleano que indica si el campo es obligatorio (no vacío).
     * @return Mensaje de error en caso de que el valor del campo pasado como
     * parámetro sea más largo al parámetro longitud, no cumpla con el patrón
     * dado o sea vacío, null en caso contrario.
     */
    public static String verificarLongitudVacio(String nombreCampo, String valorCampo, int longitud,
            boolean obliga) {

        if (obliga) {
            String respVerif = verificarVacio(nombreCampo, valorCampo);
            if (respVerif != null) {
                return respVerif;
            }
        }

        if (valorCampo.length() > longitud) {
            return "Error: El campo " + nombreCampo + " tiene "
                    + nombreCampo.length() + " caracteres y solo puede contener "
                    + "a lo sumo " + longitud + " carateres.";
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
    public static boolean verificarCamposFijos(TipoActividad ta) {


        String respVerif = verificarLongitudVacio("'Nombre del Tipo de Actividad'", ta.getNombreTipo(),
                140, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Descripción'", ta.getDescripcion(), 200, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }


        respVerif = verificarVacio("'Tipo'", ta.getTipoPR());
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verificarVacio("'Programa'", ta.getPrograma());
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verificarVacio("'Dependencia a validar'", ta.getValidador());
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        String[] permisos = ta.getPermisos();
        if (permisos == null) {
            ta.setMensajeError("Error: El campo 'Realizado por' es obligatorio.");
            return false;
        }


        String patronNum = "^[ ]*[0-9]+[ ]*$";

        String nro = String.valueOf(ta.getNroProductos());
        respVerif = verificarPatron("'Número de productos'", nro, patronNum,
                "debe contener sólo números.");
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        if (nro.equals("0")) {
            ta.setMensajeError("Error: El campo 'Número de archivos del producto' debe contener al "
                    + "menos 1 como valor.");
            return false;
        }

        respVerif = verificarLongitudVacio("'Número de productos'", nro, 1, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        nro = String.valueOf(ta.getNroCampos());
        respVerif = verificarPatron("'Número de campos'", nro, patronNum,
                "debe contener sólo números.");
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }
        if (nro.equals("0")) {
            ta.setMensajeError("Error: El campo 'Número de Campos' debe contener al "
                    + "menos 1 como valor.");
            return false;
        }
        respVerif = verificarLongitudVacio("'Número de campos'", nro, 2, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
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
    public static boolean verificarCamposVariables(TipoActividad ta) {

        Iterator it = ta.getCampos().iterator();
        for (int i = 1; it.hasNext(); i++) {
            Campo campo = (Campo) it.next();
            String tipo = campo.getTipo();
            String nombre = campo.getNombre();
            String nroCampo = "número " + i;


            /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
            String respVerif = verificarLongitudVacio(nroCampo, nombre, 100, true);
            if (respVerif != null) {
                ta.setMensajeError(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (numérica, a lo sumo 3 caracteres,
             * mayor que 0 que es lo mismo que no vacía)*/
            if (tipo.equals("texto") || tipo.equals("textol")
                    || tipo.equals("numero") || tipo.equals("participante")) {

                String patronNum = "^[ ]*[0-9]+[ ]*$";
                String longitud = String.valueOf(campo.getLongitud());
                respVerif = verificarPatron(nroCampo, longitud, patronNum,
                        "debe contener sólo números.");
                if (respVerif != null) {
                    ta.setMensajeError(respVerif);
                    return false;
                }

                respVerif = verificarLongitudVacio(nroCampo, longitud, 3, true);
                if (respVerif != null) {
                    ta.setMensajeError(respVerif);
                    return false;
                }

                if (longitud.equals("0")) {
                    ta.setMensajeError("Error: El campo número " + i + " debe contener "
                            + "al menos 1 como valor para su Longitud.");
                    return false;
                }
            }

            /*verifica que si el tipo es catálogo, el valor de catalogo no sea vacío*/
            if (tipo.equals("catalogo") && campo.getCatalogo().equals("")) {
                ta.setMensajeError("Error: Debe seleccionar un catálogo para el "
                        + "campo número " + i + ".");
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
     */
    public static boolean verificar(Actividad act) {

        boolean creador = false;
        Iterator it = act.getCamposValores().iterator();
        ArrayList<String> participantes = new ArrayList<>(0);
        while (it.hasNext()) {

            CampoValor cv = (CampoValor) it.next();
            String valor = cv.getValor();
            String valorAux = cv.getValorAux();
            String tipo = cv.getCampo().getTipo();
            String nombre = "'" + cv.getCampo().getNombre() + "'";
            int longitud = cv.getCampo().getLongitud();
            boolean obligatorio = cv.getCampo().isObligatorio();
            String respVerif;

            if (valorAux.equals("Apellido(s), Nombre(s)")) {
                valorAux = "";
            }

            /*verifica si el campo es tipo participante tenga datos en un solo campo*/
            if (tipo.equals("participante") && !valorAux.isEmpty() && !valor.isEmpty()) {
                act.setMensajeError("Error: El campo " + nombre + " debe contener "
                        + "datos en uno de sus dos campos, no puede contener en ambos.");
                return false;
            }

            /*verifica si el creador de la actividad esta presente*/
            String usbid = valor.split(",")[0];
            if (tipo.equals("participante") && act.getCreador().equals(usbid)) {
                creador = true;
            }

            /*asignación para verificación de si es obligatorio el campo tipo participante*/
            String val = valor;
            if (tipo.equals("participante")) {
                if (valor.isEmpty()) {
                    val = valorAux;
                }

                /*verifica si ya se agrega a un mismo participante*/
                Iterator iter = participantes.iterator();
                while (iter.hasNext()) {
                    if (iter.next().equals(val + cv.getCampo().getIdCampo())) {
                        act.setMensajeError("Error: El valor " + val + " se repite para "
                                + "dos campos del mismo tipo de participante. Por favor, "
                                + "cambie uno de los valores.");
                        return false;
                    }
                }
                if (!val.isEmpty()) {
                    participantes.add(val + cv.getCampo().getIdCampo());
                }
            }
            /*verifica si el campo es obligatorio que no sea vacío*/
            respVerif = verificarVacio(nombre, val);
            if (obligatorio && respVerif != null) {
                act.setMensajeError(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (solo aplica para campos tipo
             * texto, texto largo y numero)*/
            if ((tipo.equals("texto")
                    || tipo.equals("textol")
                    || tipo.equals("numero"))
                    && valor.length() > longitud) {
                act.setMensajeError("Error: El campo " + nombre + " tiene "
                        + valor.length() + " caracteres y solo puede contener "
                        + "a lo sumo " + longitud + " carateres.");
                return false;
            }

            /*verifica si el campo es tipo numero que su valor sea numérico*/
            if (tipo.equals("numero") && !valor.matches("^[ ]*[0-9]+[ ]*$")) {
                act.setMensajeError("Error: El campo " + nombre + " debe contener "
                        + "solo números.");
                return false;
            }


            /*verifica que el archivo sea un PDF y que su tamaño sea menor de 2MB*/
            if (tipo.equals("producto") || tipo.equals("archivo")) {
                if (cv.getFile().getFileSize() > 2097152) {
                    act.setMensajeError("Error: El tamaño del archivo del campo "
                            + nombre + " debe ser menor de 2 MB.");
                    return false;
                }

                if (!esPDF(cv)) {
                    act.setMensajeError("Error: El archivo subido al campo " + nombre
                            + " debe ser de tipo PDF");
                    return false;
                }
            }

        }
        String accion = "registró";
        if (act.getCreador() == null) {
            accion = "registra";
        }
        if (!creador) {
            act.setMensajeError("Error: El usuario que " + accion + " la Actividad debe "
                    + "estar presente en alguno de los campos desplegables de participante.");
            return false;
        }

        return true;
    }

    private static boolean esPDF(CampoValor cv) {
        try {
            byte pdf[] = {0x25, 0x50, 0x44, 0x46};
            byte data[] = cv.getFile().getFileData();
            for (int i = 0; i < pdf.length; i++) {

                if (pdf[i] != data[i]) {
                    return false;
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Verificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Verificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param c
     * @return
     */
    public static boolean verificarCamposFijos(Catalogo c) {

        /*verifica si el nombre del catalogo es válido*/
        String respVerif = verificarLongitudVacio("'Nombre'", c.getNombre(),
                140, true);
        if (respVerif != null) {
            c.setMensajeError(respVerif);
            return false;
        }

        String nro = String.valueOf(c.getNroCampos());
        respVerif = verificarPatron("'Número de campos'", nro, "^[ ]*[0-9]+[ ]*$",
                "debe contener sólo números.");
        if (respVerif != null) {
            c.setMensajeError(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Número de campos'", nro, 1, true);
        if (respVerif != null) {
            c.setMensajeError(respVerif);
            return false;
        }

        return true;

    }

    /**
     *
     * @param c
     * @return
     */
    public static boolean verificarCamposVariables(Catalogo c) {

        Iterator it = c.getCampos().iterator();
        for (int i = 1; i <= c.getCampos().size() && it.hasNext(); i++) {
            CampoCatalogo campo = (CampoCatalogo) it.next();
            String nombre = campo.getNombre();
            String nroCampo = "número " + i;

            /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
            String respVerif = verificarLongitudVacio(nroCampo, nombre, 100, true);
            if (respVerif != null) {
                c.setMensajeError(respVerif);
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param ec
     * @return
     */
    public static boolean verificar(ElementoCatalogo ec) {


        Iterator it = ec.getCamposValores().iterator();
        boolean todosVacios = true;
        while (it.hasNext()) {

            CampoCatalogoValor ccv = (CampoCatalogoValor) it.next();
            String valor = ccv.getValor();
            String tipo = ccv.getCampo().getTipo();
            String nombre = "'" + ccv.getCampo().getNombre() + "'";

            /*verifica si el campo es tipo numero que su valor sea numérico*/
            if (tipo.equals("numero") && !valor.matches("^[ ]*[0-9]+[ ]*$")) {
                ec.setMensajeError("Error: El campo " + nombre + " debe contener "
                        + "solo números.");
                return false;
            }
            todosVacios &= Verificaciones.esVacio(valor);
        }
        if (todosVacios) {
            ec.setMensajeError("Error: Debe llenar al menos un campo");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
