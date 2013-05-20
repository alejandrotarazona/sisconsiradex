/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static String verifVacio(String nombreCampo, String valorCampo) {

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
    public static String verifPatron(String nombreCampo, String valorCampo,
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
    public static String verifLV(String nombreCampo, String valorCampo, int longitud,
            boolean obliga) {

        if (obliga) {
            String respVerif = verifVacio(nombreCampo, valorCampo);
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
    public static boolean verifCF(TipoActividad ta) {


        String respVerif = verifLV("'Nombre del Tipo de Actividad'", ta.getNombreTipo(),
                140, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verifLV("'Descripción'", ta.getDescripcion(), 200, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }


        respVerif = verifVacio("'Tipo'", ta.getTipoPR());
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verifVacio("'Programa'", ta.getPrograma());
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        respVerif = verifVacio("'Dependencia a validar'", ta.getValidador());
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
        respVerif = verifPatron("'Número de productos'", nro, patronNum,
                "debe contener sólo números.");
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        if (nro.equals("0")) {
            ta.setMensajeError("Error: El campo 'Número de productos' debe contener al "
                    + "menos 1 como valor.");
            return false;
        }

        respVerif = verifLV("'Número de productos'", nro, 1, true);
        if (respVerif != null) {
            ta.setMensajeError(respVerif);
            return false;
        }

        nro = String.valueOf(ta.getNroCampos());
        respVerif = verifPatron("'Número de campos'", nro, patronNum,
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
        respVerif = verifLV("'Número de campos'", nro, 2, true);
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
    public static boolean verifCV(TipoActividad ta) {

        Iterator it = ta.getCampos().iterator();
        for (int i = 1; it.hasNext(); i++) {
            Campo campo = (Campo) it.next();
            String tipo = campo.getTipo();
            String nombre = campo.getNombre();
            String nroCampo = "número " + i;


            /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
            String respVerif = verifLV(nroCampo, nombre, 100, true);
            if (respVerif != null) {
                ta.setMensajeError(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (numérica, a lo sumo 3 caracteres,
             * mayor que 0 que es lo mismo que no vacía)*/
            if (tipo.equals("texto") || tipo.equals("textol")
                    || tipo.equals("numero")) {

                String patronNum = "^[ ]*[0-9]+[ ]*$";
                String longitud = String.valueOf(campo.getLongitud());
                respVerif = verifPatron(nroCampo, longitud, patronNum,
                        "debe contener sólo números.");
                if (respVerif != null) {
                    ta.setMensajeError(respVerif);
                    return false;
                }

                respVerif = verifLV(nroCampo, longitud, 3, true);
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
    public static boolean verif(Actividad act) {

        Iterator it = act.getCamposValores().iterator();

        while (it.hasNext()) {

            CampoValor cv = (CampoValor) it.next();
            String valor = cv.getValor();
            String tipo = cv.getCampo().getTipo();
            String nombre = "'" + cv.getCampo().getNombre() + "'";
            int longitud = cv.getCampo().getLongitud();
            boolean obligatorio = cv.getCampo().isObligatorio();
            String respVerif;

            /*verifica si el campo es obligatorio que no sea vacío*/
            respVerif = verifVacio(nombre, valor);
            if (obligatorio && respVerif != null) {
                act.setMensajeError(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (solo aplica para campos tipo
             * texto, texto largo y numero)*/
            if ((tipo.equals("texto") || tipo.equals("textol")
                    || tipo.equals("numero")) && valor.length() > longitud) {
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
    public static boolean verifCF(Catalogo c) {

        /*verifica si el nombre del catalogo es válido*/
        String respVerif = verifLV("'Nombre'", c.getNombre(),
                140, true);
        if (respVerif != null) {
            c.setMensajeError(respVerif);
            return false;
        }

        String nro = String.valueOf(c.getNroCampos());
        respVerif = verifPatron("'Número de campos'", nro, "^[ ]*[0-9]+[ ]*$",
                "debe contener sólo números.");
        if (respVerif != null) {
            c.setMensajeError(respVerif);
            return false;
        }

        respVerif = verifLV("'Número de campos'", nro, 1, true);
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
    public static boolean verifCV(Catalogo c) {

        Iterator it = c.getCampos().iterator();
        for (int i = 0; i < c.getCampos().size() && it.hasNext(); i++) {
            CampoCatalogo campo = (CampoCatalogo) it.next();
            String nombre = campo.getNombre();
            String nroCampo = "número " + i;

            /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
            String respVerif = verifLV(nroCampo, nombre, 100, true);
            if (respVerif != null) {
                c.setMensajeError(respVerif);
                return false;
            }
        }
        if (c.getCamposAux() != null) {
            Iterator iter = c.getCamposAux().iterator();
            for (int i = 1; iter.hasNext(); i++) {
                CampoCatalogo campo = (CampoCatalogo) iter.next();
                String nombre = campo.getNombre();
                String nroCampo = "adicional número " + i;

                /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
                String respVerif = verifLV(nroCampo, nombre, 100, true);
                if (respVerif != null) {
                    c.setMensajeError(respVerif);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *
     * @param ec
     * @return
     */
    public static boolean verif(ElementoCatalogo ec) {


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
            ec.setMensajeError("Error: Debe haber al menos un campo lleno para "
                    + "agregar el elemento");
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
