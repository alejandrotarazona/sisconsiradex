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
            return "Error: El campo " + nombreCampo + " es obligatorio.";
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
    public static String verificarLongitudVacio(String nombreCampo, String valorCampo,
            int longitud, boolean obliga) {

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
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Descripción'", ta.getDescripcion(), 200, true);
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarVacio("'Tipo'", ta.getTipoPR());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarVacio("'Programa'", ta.getPrograma());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarVacio("'Dependencia a validar'", ta.getValidador());
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        String[] permisos = ta.getPermisos();
        if (permisos == null) {
            ta.setMensaje("Error: El campo 'Realizado por' es obligatorio.");
            return false;
        }

        String patronNum = "^[ ]*[0-9]*[ ]*$";

        String nro = String.valueOf(ta.getNroCampos());
        respVerif = verificarPatron("'Número de campos'", nro, patronNum,
                "debe contener sólo números.");
        if (respVerif != null) {
            ta.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Número de campos'", nro, 2, true);
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
    public static boolean verificarCamposVariables(TipoActividad ta) {

        Iterator it = ta.getCampos().iterator();
        boolean hayParticipante = false;
        for (int i = 1; it.hasNext(); i++) {
            Campo campo = (Campo) it.next();
            String tipo = campo.getTipo();
            String nombre = campo.getNombre();
            String nroCampo = "número " + i;

            /*verifica si hay al menos un campo tipo participante*/
            if (tipo.equals("participante")) {
                hayParticipante = true;
            }

            /*verifica que el nombre sea válido (no vacío, a lo sumo 100 caracteres)*/
            String respVerif = verificarLongitudVacio("'Nombre' de la fila " + nroCampo,
                    nombre, 100, true);
            if (respVerif != null) {
                ta.setMensaje(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (numérica, a lo sumo 3 caracteres,
             * mayor que 0 que es lo mismo que no vacía)*/
            if (tipo.equals("texto") || tipo.equals("textol")
                    || tipo.equals("numero") || tipo.equals("participante")) {

                String patronNum = "^[ ]*[0-9]*[ ]*$";
                String longitud = String.valueOf(campo.getLongitud());
                respVerif = verificarPatron(nroCampo, longitud, patronNum,
                        "debe contener sólo números.");
                if (respVerif != null) {
                    ta.setMensaje(respVerif);
                    return false;
                }

                respVerif = verificarLongitudVacio(nroCampo, longitud, 3, true);
                if (respVerif != null) {
                    ta.setMensaje(respVerif);
                    return false;
                }

                if (longitud.equals("0")) {
                    ta.setMensaje("Error: El campo Longitud/Límite de la fila "
                            + i + " debe contener al menos 1 como valor.");
                    return false;
                }
            }

            /*verifica que si el tipo es catálogo, el valor de catalogo no sea vacío*/
            if (tipo.equals("catalogo") && campo.getCatalogo().equals("")) {
                ta.setMensaje("Error: Debe seleccionar un catálogo para el "
                        + "campo número " + i + ".");
                return false;
            }

            /*verifica que si el tipo es participante, el valor de catalogo no sea vacío*/
            if (tipo.equals("participante") && campo.getCatalogoPart().equals("")) {
                ta.setMensaje("Error: Debe seleccionar un catálogo para el "
                        + "campo número " + i + ".");
                return false;
            }
        }
        if (!hayParticipante) {
            ta.setMensaje("Error: Debe haber al menos un campo de tipo 'catálogo de usuarios'.");
            return false;
        }
        return true;
    }

    /**
     * Verifica que todos los campos de la Actividad pasada por parámetro tengan
     * un valor válido.
     *
     * @param act Actividad a verificar
     * @param accion false si es agregar, true si es modificar
     * @return true si los valores son validos, de lo contrario retorna false.
     */
    public static boolean verificar(Actividad act, boolean accion, String rol) {

        boolean creador = false;
        ArrayList<CampoValor> campos = act.getCamposValores();
        ArrayList<String> participantes = new ArrayList<>(0);
        String nombreP = "";

        for (int i = 0; i < campos.size(); i++) {
            CampoValor cv = (CampoValor) campos.get(i);
            String valor = cv.getValor();
            String valorAux = cv.getValorAux();
            String tipo = cv.getCampo().getTipo();
            String nombre = "'" + cv.getCampo().getNombre() + "'";
            int longitud = cv.getCampo().getLongitud();
            boolean obligatorio = cv.getCampo().isObligatorio();
            String respVerif;

            if (valorAux.equals("Apellido(s), Nombre(s)") || esVacio(valorAux)) {
                valorAux = "";
            }

            /*verifica si el campo es tipo participante y es adicional tenga datos en alguno de sus campos*/
            if (tipo.equals("participante") && valorAux.isEmpty() && valor.isEmpty()
                    && longitud == -1) {
                act.setMensaje("Error: No deben haber campos adicionales de participantes"
                        + " sin ningún valor. Por favor, ingrese un valor o elimínelo.");
                return false;
            }

            /*verifica si el campo es tipo participante, no adicional y vacío, no tenga campos adicionales*/
            if (i + 1 < campos.size() && tipo.equals("participante") && valorAux.isEmpty() 
                    && valor.isEmpty() && longitud != -1 
                    && campos.get(i + 1).getCampo().getLongitud() == -1) {
                act.setMensaje("Error: El campo " + nombre + " no puede ser vacío y "
                        + "si ha requerido de campos adicionales para este.");
                return false;
            }

            if (tipo.equals("participante") && longitud >= 0) {
                nombreP = nombre;
            }

            /*verifica si el campo es tipo participante tenga datos en un solo campo*/
            if (tipo.equals("participante") && !valorAux.isEmpty() && !valor.isEmpty()) {
                System.out.println("v " + valor + " vAux " + valorAux + " xxxxxxxxxxxxxxxxxxx");
                act.setMensaje("Error: Se ingresó un participante de " + nombreP
                        + " por el campo de texto y por selección de la lista a la vez."
                        + " Debe ser ingresado por solo una de estas opciones.");
                return false;
            }

            /*verifica si el creador de la actividad esta presente*/
            String usbid = valor.split(",")[0];
            if (rol.equals("WM") || 
                    (tipo.equals("participante") && act.getCreador().equals(usbid))) {
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
                        act.setMensaje("Error: El valor '" + val + "' se repite para "
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
                act.setMensaje(respVerif);
                return false;
            }

            /*verifica que la longitud sea válida (solo aplica para campos tipo
             * texto, texto largo y numero)*/
            if ((tipo.equals("texto")
                    || tipo.equals("textol")
                    || tipo.equals("numero"))
                    && valor.length() > longitud) {
                act.setMensaje("Error: El campo " + nombre + " tiene "
                        + valor.length() + " caracteres y solo puede contener "
                        + "a lo sumo " + longitud + " carateres.");
                return false;
            }

            /*verifica si el campo es tipo numero que su valor sea numérico*/
            if (tipo.equals("numero") && !valor.matches("^[ ]*[0-9]*[ ]*$")) {
                act.setMensaje("Error: El campo " + nombre + " debe contener "
                        + "solo números.");
                return false;
            }


            /*verifica que el archivo sea un PDF y que su tamaño sea menor de 2MB*/
            if (tipo.equals("archivo")) {
                if (cv.getFile().getFileSize() > 2097152) {
                    act.setMensaje("Error: El tamaño del archivo del campo "
                            + nombre + " debe ser menor de 2 MB.");
                    return false;
                }

                if (!esPDF(cv)) {
                    act.setMensaje("Error: El archivo subido al campo " + nombre
                            + " debe ser de tipo PDF");
                    return false;
                }
            }

        }
        String acc = "registra";
        if (accion) {
            acc = "registró";
        }
        if (!creador) {
            act.setMensaje("Error: El usuario que " + acc + " la Actividad debe "
                    + "estar presente en alguno de los campos desplegables de participante.");
            return false;
        }

        return true;
    }

    private static boolean esPDF(CampoValor cv) {
        try {
            byte pdf[] = {0x25, 0x50, 0x44, 0x46};
            byte data[] = cv.getFile().getFileData();
            if (data.length > 0) {
                for (int i = 0; i < pdf.length; i++) {
                    if (pdf[i] != data[i]) {
                        return false;
                    }
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
            c.setMensaje(respVerif);
            return false;
        }

        String nro = String.valueOf(c.getNroCampos());
        respVerif = verificarPatron("'Número de campos'", nro, "^[ ]*[0-9]*[ ]*$",
                "debe contener sólo números.");
        if (respVerif != null) {
            c.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Número de campos'", nro, 1, true);
        if (respVerif != null) {
            c.setMensaje(respVerif);
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
                c.setMensaje(respVerif);
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
            String nombre = "'" + ccv.getCampo().getNombre() + "'";

            /*verifica si el campo es tipo numero que su valor sea numérico*/
            if (ccv.getCampo().getTipo().equals("numero") && !valor.matches("^[ ]*[0-9]*[ ]*$")) {
                ec.setMensaje("Error: El campo " + nombre + " debe contener "
                        + "solo números.");
                return false;
            }
            
            /*verifica si el campo es tipo usbid que no sea vacío*/
            if (ccv.getCampo().getTipo().equals("usbid") && esVacio(valor)) {
                ec.setMensaje("Error: El campo " + nombre + " es obligatorio.");
                return false;
            }
            
            todosVacios &= Verificaciones.esVacio(valor);
        }
        if (todosVacios) {
            ec.setMensaje("Error: Debe llenar al menos un campo");
            return false;
        }
        return true;
    }

    /**
     *
     * @param u
     * @return
     */
    public static boolean verificar(Usuario u) {


        String respVerif = verificarLongitudVacio("'USB-ID'", u.getUsername(),
                20, true);
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Nombres'", u.getNombres(),
                50, true);
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Apellidos'", u.getApellidos(),
                50, true);
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Teléfono'", u.getTelefono(),
                15, false);
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarPatron("'Teléfono'", u.getTelefono(), "^[ ]*[0-9]*[ ]*$",
                "debe contener sólo números.");
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        respVerif = verificarLongitudVacio("'Correo'", u.getEmail(),
                50, false);
        if (respVerif != null) {
            u.setMensaje(respVerif);
            return false;
        }

        if (u.getRol().equals("")) {
            u.setMensaje("Error: Debe elegir un rol para el usuario.");
            return false;
        }

        if (u.getRol().equals("dex")) {
            u.setMensaje("Error: Debe elegir una Dependencia o Unidad.");
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
    }
}
