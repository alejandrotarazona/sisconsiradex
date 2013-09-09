/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author SisCon
 */
public class BusquedaActividad extends Root {

    private String nombreTipo;
    private String tipoPR;
    private String programa;
    private String validador;
    private String participante; //usbid
    private String fechaInic;
    private String fechaFin;
    private String palabras = "";
    private int mostrarPorPagina = 5;
    private int totalPaginas;
    private ArrayList<ArrayList<Actividad>> libro;
    private int pagina;
    private String[] botonesPaginas;
    private String[] grafica;
    private ArrayList<Par> datosGrafica;
    private int totalActividades;

    public static class Par implements Serializable {

        private String nombre;
        private String cantidad;

        public Par(String nombre, String cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCantidad() {
            return cantidad;
        }
    }

    private void setDatosGrafica() {
        String[] auxNombre = grafica[0].split("\\|");
        String[] auxCant = grafica[1].split(",");
        ArrayList<Par> aux = new ArrayList<>(0);

        for (int i = 0; i < auxNombre.length; i++) {
            Par parNuevo = new Par(auxNombre[i], auxCant[i]);
            aux.add(parNuevo);
        }

        datosGrafica = aux;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getTipoPR() {
        return tipoPR;
    }

    public void setTipoPR(String tipoPR) {
        this.tipoPR = tipoPR;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFechaInic() {
        return fechaInic;
    }

    public void setFechaInic(String fechaInic) {
        this.fechaInic = fechaInic;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getMostrarPorPagina() {
        return mostrarPorPagina;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    public void setMostrarPorPagina(int mostrarPorPagina) {
        this.mostrarPorPagina = mostrarPorPagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public ArrayList<ArrayList<Actividad>> getLibro() {
        return libro;
    }

    public void setLibro(ArrayList<ArrayList<Actividad>> libro) {
        this.libro = libro;
    }

    public int getPagina() {
        return pagina;
    }

    public ArrayList<Actividad> obtenerPagina() {
        return libro.get(pagina - 1);
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String[] getBotonesPaginas() {
        return botonesPaginas;
    }

    public void setBotonesPaginas(String[] botonesPaginas) {
        this.botonesPaginas = botonesPaginas;
    }

    public String[] getGrafica() {
        return grafica;
    }

    public void setGrafica(String[] grafica) {
        this.grafica = grafica;
    }

    public ArrayList<Par> getDatosGrafica() {
        return datosGrafica;
    }

    public void setDatosGrafica(ArrayList<Par> datosGrafica) {
        this.datosGrafica = datosGrafica;
    }

    public int getTotalActividades() {
        return totalActividades;
    }

    public void setTotalActividades(int totalActividades) {
        this.totalActividades = totalActividades;
    }

    private boolean actividadesOtrosParametros(boolean validada,
            ArrayList<Actividad> cjtoAux) {

        Entity eBuscar = new Entity(21);//TIPO_ACT__ACT
        ArrayList<String> auxColumnas = new ArrayList<>(0);
        ArrayList<Object> auxCondiciones = new ArrayList<>(0);

        if (validada) {
            auxColumnas.add("validacion");
            auxCondiciones.add("Validada");
        }

        if (this.nombreTipo != null && !this.nombreTipo.equals("")) {
            auxColumnas.add("nombre_tipo_actividad");
            auxCondiciones.add(nombreTipo);
        }
        if (this.tipoPR != null && !this.tipoPR.equals("")) {
            auxColumnas.add("tipo_p_r");
            auxCondiciones.add(tipoPR);
        }
        if (this.programa != null && !this.programa.equals("")) {
            auxColumnas.add("programa");
            auxCondiciones.add(programa);
        }
        if (this.validador != null && !this.validador.equals("")) {
            auxColumnas.add("validador");
            auxCondiciones.add(validador);
        }

        int tam = auxColumnas.size();
        String[] columnas = new String[tam];
        for (int i = 0; i < tam; i++) {
            columnas[i] = auxColumnas.get(i);
        }
        tam = auxCondiciones.size();
        Object[] condiciones = new Object[tam];

        for (int i = 0; i < tam; i++) {
            condiciones[i] = auxCondiciones.get(i);
        }

        ResultSet rs;
        if (columnas.length > 0) {
            rs = eBuscar.seleccionar(columnas, condiciones);
            cjtoAux.addAll(Actividad.listar(rs));
            return true;
        }

        return false;

    }

    private boolean actividadesRangoFecha(ArrayList<Actividad> listaRango) {
        boolean hayRango = false;
        String[] columna = {
            "tipo_campo"
        };
        Object[] condicion = {
            "fecha"
        };

        Entity eRango = new Entity(24);//ACT_COMPLETA
        ResultSet rsRango = eRango.seleccionar(columna, condicion);
        if (this.fechaInic != null && !this.fechaInic.equals("")) {
            hayRango = true;
            if (this.fechaFin != null && !this.fechaFin.equals("")) {
                try {
                    while (rsRango.next()) {
                        String fecha = rsRango.getString("valor");
                        try {
                            if (antesDe(fechaInic, fecha) && despuesDe(fechaFin, fecha)) {
                                int id = rsRango.getInt("id_actividad");
                                Actividad act = new Actividad();
                                act.setIdActividad(id);
                                act.setActividad();
                                act.setParticipantes(id);
                                listaRango.add(act);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    while (rsRango.next()) {
                        String fecha = rsRango.getString("valor");
                        try {
                            if (antesDe(fechaInic, fecha)) {
                                int id = rsRango.getInt("id_actividad");
                                Actividad act = new Actividad();
                                act.setIdActividad(id);
                                act.setActividad();
                                act.setParticipantes(id);
                                listaRango.add(act);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (this.fechaFin != null && !this.fechaFin.equals("")) {
            hayRango = true;
            try {
                while (rsRango.next()) {
                    String fecha = rsRango.getString("valor");
                    try {
                        if (despuesDe(fechaFin, fecha)) {
                            int id = rsRango.getInt("id_actividad");
                            Actividad act = new Actividad();
                            act.setIdActividad(id);
                            act.setActividad();
                            act.setParticipantes(id);
                            listaRango.add(act);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return hayRango;
    }

    private boolean actividadesParticipantes(ArrayList<Actividad> listaParticipantes) {
        boolean hayParticipantes = false;
        if (this.participante != null && !this.participante.equals("")) {
            hayParticipantes = true;
            listaParticipantes.addAll(Actividad.listarActividadesDeUsuario(participante));
        }
        return hayParticipantes;
    }

    private boolean actividadesContienenPalabras(ArrayList<Actividad> listaContienen) {
        boolean hayPalabras = false;

        Entity eBuscar = new Entity(24);//ACT_COMPLETA
        ResultSet rs = eBuscar.listar();

        if (!palabras.isEmpty()) {
            hayPalabras = true;
            try {
                boolean next = rs.next();
                while (next) {
                    String valor = rs.getString("valor");
                    String tipo = rs.getString("nombre_tipo_actividad");
                    String campo = rs.getString("nombre_campo");
                    String desc = rs.getString("descripcion");
                    if (contienePalabras(desc) || contienePalabras(valor) ||
                            contienePalabras(campo) || contienePalabras(tipo)) {
                        Actividad act = new Actividad();
                        int id = rs.getInt("id_actividad");
                        act.setIdActividad(id);
                        act.setActividad();
                        act.setParticipantes(id);
                        listaContienen.add(act);
                        next = rs.next();
                        while (id == rs.getInt("id_actividad")) {
                            next = rs.next();
                        }
                    } else {
                        next = rs.next();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return hayPalabras;
    }

    /**
     * Metodo para buscar por cada uno de los criterios dados.
     *
     * @return Las páginas con las listas de Actividades por página segun la
     * busqueda establecida.
     */
    public void buscar(boolean validada) {
        libro = new ArrayList<>(0);
        totalPaginas = 0;

        boolean hayColumnas, hayParticipantes, hayRango, hayPalabras;

        ArrayList<Actividad> cjtoAux = new ArrayList<>(0);
        hayColumnas = actividadesOtrosParametros(validada, cjtoAux);

        ArrayList<Actividad> listaRango = new ArrayList<>(0);
        hayRango = actividadesRangoFecha(listaRango);

        ArrayList<Actividad> listaParticipantes = new ArrayList<>(0);
        hayParticipantes = actividadesParticipantes(listaParticipantes);

        ArrayList<Actividad> listaContienen = new ArrayList<>(0);
        hayPalabras = actividadesContienenPalabras(listaContienen);

        ArrayList<ArrayList<Actividad>> listas = new ArrayList<>(0);

        //Revisa las distintas formas en que pueden quedar las
        //listas para poder discernir cual(es) lista(s) es(son) vacia(s).
        if (hayParticipantes) {
            listas.add(listaParticipantes);
        }
        if (hayColumnas) {
            listas.add(cjtoAux);
        }
        if (hayRango) {
            listas.add(listaRango);
        }
        if (hayPalabras) {
            listas.add(listaContienen);
        }

        if (listas.isEmpty()) {
            if (!hayParticipantes && !hayColumnas && !hayRango && !hayPalabras) {
                Entity eBuscar = new Entity(21);//TIPO_ACT__ACT
                ResultSet rs = eBuscar.listar();
                cjtoAux = Actividad.listar(rs);
                grafica = valoresGrafica(cjtoAux);
                libro = paginar(cjtoAux, mostrarPorPagina);
            } else {
                libro = new ArrayList<>(0);
                String[] g = {"",""};
                grafica = g;
            }
        } else {
            ArrayList<Actividad> listaInterceptada = intersectar(listas);
            libro = paginar(listaInterceptada, mostrarPorPagina);
            grafica = valoresGrafica(listaInterceptada);
            System.out.println(listaInterceptada);
        }
        setDatosGrafica();
        totalPaginas = libro.size();
        pagina = 1;
        if (totalPaginas > 0) {
            totalActividades = (totalPaginas - 1) * mostrarPorPagina
                    + libro.get(totalPaginas - 1).size();
        } else {
            totalActividades = 0;
        }
    }

    /**
     *
     * @param listaActividades tiene las actividades que aparecieron en la
     * busqueda
     * @return un arreglo de String con dos posiciones, en una tiene los nombres
     * de las actividades de la busqueda y en la otra tiene las cantidades
     * encontradas de cada actividad
     */
    public static String[] valoresGrafica(ArrayList<Actividad> listaActividades) {

        ArrayList<String> aux = new ArrayList<>(0);
        ArrayList<String> aux2 = new ArrayList<>(0);
        int i, j;
        for (i = 0; i <= listaActividades.size() - 1; i++) {
            aux.add("'" + listaActividades.get(i).getIdTipoActividad() + "'");
            aux2.add(listaActividades.get(i).getNombreTipoActividad());
        }
        int contador;
        String[] grafica = new String[2];
        String nombres = "";
        String cantidad = "";

        for (i = 0; i <= aux.size() - 1; i++) {
            contador = 1;
            for (j = i + 1; j <= aux.size() - 1; j++) {
                if (aux.get(j).equals(aux.get(i))) {
                    aux.remove(j);
                    aux2.remove(j);
                    contador++;
                    j--;
                }
            }
            cantidad += contador + ",";
        }

        for (i = 0; i <= aux2.size() - 1; i++) {
            nombres += aux2.get(i) + "|";
        }

        if (nombres.length() != 0 && cantidad.length() != 0) {
            nombres = nombres.substring(0, nombres.length() - 1);
            cantidad = cantidad.substring(0, cantidad.length() - 1);
        }
        System.out.println(cantidad);
        System.out.println(nombres);

        grafica[0] = nombres;
        grafica[1] = cantidad;
        return grafica;

    }

    /**
     * Método para cargar los botones de un rango de hasta 5 páginas para la
     * navegabilidad
     */
    public void setBotonesPaginas() {
        String pags = "";

        int i, ultima;
        if (pagina - 2 < 1) {
            i = 1;
        } else {
            i = pagina - 2;
        }
        if (pagina + 2 > totalPaginas) {
            ultima = totalPaginas;
        } else {
            ultima = pagina + 2;
        }
        for (; i <= ultima; i++) {
            pags += i + ",";
        }
        botonesPaginas = pags.split(",");
    }

    /**
     * Busca una página específica de la busqueda.
     *
     * @param pagina La página que se desea revisar.
     * @return Lista de Actividades ubicadas en la Pagina solicitada.
     */
    public ArrayList<Actividad> buscarPagina(BusquedaActividad busqueda,
            int pagina) {
        setBotonesPaginas();
        if (busqueda.getLibro().size() > 0) {
            return busqueda.getLibro().get(pagina);
        }
        return new ArrayList<>(0);
    }

    /**
     * Divide en paginas las actividades conseguidas en la busqueda.
     *
     * @param listaActividades Todas las actividades que se consiguieron en la
     * busqueda.
     * @param cantidadPorPagina El numero de actividades que se pueden mostrar
     * por pagina.
     * @return Una lista que, en cada posicion, contiene una lista de
     * actividades. Representa las paginas que se han de mostrar.
     */
    public ArrayList<ArrayList<Actividad>> paginar(ArrayList<Actividad> listaActividades,
            int cantidadPorPagina) {
        ArrayList<ArrayList<Actividad>> resp = new ArrayList<>(0);
        Iterator it = listaActividades.iterator();

        while (it.hasNext()) {
            ArrayList<Actividad> aux = new ArrayList<>(0);
            for (int i = 0; i < cantidadPorPagina && it.hasNext(); i++) {
                aux.add((Actividad) it.next());
            }
            resp.add(aux);

        }

        return resp;
    }

    /**
     * Tomando un arreglo de listas de actividad intersecta las mismas
     *
     * @param listas Las listas que debo intersectar
     * @return La interseccion de las listas.
     */
    private static ArrayList<Actividad> intersectar(ArrayList<ArrayList<Actividad>> listas) {
        ArrayList<Actividad> interseccion = new ArrayList<>(0);
        ArrayList<Actividad> unaLista = listas.get(0);
        System.out.println("#################################################################################################\n"
                + "###################################################################################################");
        System.out.println("El tamaño de la lista de listas es: " + listas.size());
        for (Actividad unaActividad : unaLista) {
            boolean agregar = true;
            Iterator itAux = listas.iterator();
            itAux.next();                                           //Salto la primera lista del iterator, ya fue tomada anteriormente
            while (itAux.hasNext() && agregar) {
                ArrayList<Actividad> aComparar = (ArrayList<Actividad>) itAux.next();   //Tomo a partir de la segunda lista
                if (aComparar.isEmpty()) {
                    return new ArrayList<>(0);
                }
                agregar &= unaActividad.contenidoEn(aComparar);                //Reviso existencia
                System.out.println(unaActividad.toString() + "\t" + agregar);
            }
            if (agregar) {
                System.out.println("Agregando una actividad!!!\n\t" + unaActividad.toString());
                interseccion.add(unaActividad);                 //Agrego
            }
        }
        System.out.println("#################################################################################################\n"
                + "###################################################################################################");
        return interseccion;
    }

    /**
     * Funcion que parsea y compara dos fechas.
     *
     * @param fechaIni
     * @param fechaFin
     * @return true en caso de que las fechas esten en orden.
     * @throws ParseException
     */
    private static boolean antesDe(String fechaIni, String fechaFin) throws ParseException {

        Calendar cIni = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cIni.setTime(sdf.parse(fechaIni));
        cFin.setTime(sdf.parse(fechaFin));

        return cIni.before(cFin) || cIni.equals(cFin);

    }

    /**
     * Funcion que parsea y compara dos fechas.
     *
     * @param fechaIni
     * @param fechaFin
     * @return true en caso de que las fechas esten en el orden deseado.
     * @throws ParseException
     */
    private static boolean despuesDe(String fechaIni, String fechaFin) throws ParseException {

        Calendar cIni = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cIni.setTime(sdf.parse(fechaIni));
        cFin.setTime(sdf.parse(fechaFin));

        return cIni.after(cFin) || cIni.equals(cFin);

    }

    private boolean contienePalabras(String valor) {

        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(palabras);
        while (regexMatcher.find()) {
            String palabra = regexMatcher.group().toLowerCase();
            valor = valor.toLowerCase();
            if (valor.contains((palabra.replace("\"", "")))) {
                return true;
            }
        }
        return false;

    }

    public static void main(String args[]) {

        String palabras = "\"hola; nada, \" que \"viste .\"";
        ArrayList<String> matchList = new ArrayList<>(0);
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(palabras);
        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group().replace("\"", ""));
        }
        String valor = "yo denada que ver";
        if (valor.contains("Nada")){
            System.out.println("ssssssssssssssssssssiiiiiiiiiiiiiii");
        }
        for (String s : matchList) {
            System.out.println("++++++++++++++++++++++++++++++++" + s);
        }
    }
}
