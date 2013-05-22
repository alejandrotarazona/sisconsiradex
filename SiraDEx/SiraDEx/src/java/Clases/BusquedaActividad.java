/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author SisCon
 */
public class BusquedaActividad extends Root {

    private String nombreTipo;
    private String tipoPR;
    private String programa;
    private String validador;
    private ArrayList<String> participantes = new ArrayList<>(0);
    private String creador; //usbid
    private String fechaInic;
    private String fechaFin;
    private int mostrarPorPagina = 10;
    private int totalPaginas;
    private ArrayList<ArrayList<Actividad>> libro;
    private int pagina;

    public BusquedaActividad() {
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

    public ArrayList<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes.add(participantes);
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
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

    public ArrayList<Actividad> getPagina(int i) {
        return libro.get(i - 1);
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
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

        boolean hayColumnas = false, hayParticipantes = false, hayRango = false;

        Entity eBuscar = new Entity(0, 21);
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
        if (this.creador != null && !this.creador.equals("")) {
            auxColumnas.add("creador");
            auxCondiciones.add(creador);
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
            hayColumnas = true;
        } else {
            rs = eBuscar.listar();
        }

        ResultSet rsRango;
        Entity eRango = new Entity(0, 21);
        if (this.fechaInic != null && !this.fechaInic.equals("")) {
            if (this.fechaFin != null && !this.fechaFin.equals("")) {
                Object[] condRango = {
                    fechaInic,
                    fechaFin
                };
                rsRango = eRango.buscarRango("fecha_creacion", condRango);
                hayRango = true;
            } else {
                rsRango = eRango.buscarMayorQue("fecha_creacion", fechaInic);
                hayRango = true;
            }
        } else if (this.fechaFin != null && !this.fechaFin.equals("")) {
            rsRango = eRango.buscarMenorQue("fecha_creacion", fechaFin);
            hayRango = true;
        } else {
            rsRango = eBuscar.listar();
        }

        ArrayList<Actividad> cjtoAux = Actividad.listar(rs);     //Resultado de la busqueda cochina y gigante//
        ArrayList<Actividad> listaRango = Actividad.listar(rsRango); //Resultado de la busqueda de rango//

        ArrayList<Actividad> listaParticipantes = new ArrayList<>(0);

        if (this.participantes != null && !this.participantes.isEmpty()) {
            Iterator itPart = participantes.iterator();
            hayParticipantes = true;
            while (itPart.hasNext()) {
                String estePart = (String) itPart.next();
                if (estePart != null && !estePart.equalsIgnoreCase("")) {
                    Actividad aux = new Actividad();
                    aux.setCreador(creador);
                    listaParticipantes.addAll(aux.listarActividadesDeUsuario());    //Resultado de la busqueda de participantes//
                }
            }
        }

        ArrayList<ArrayList<Actividad>> listas = new ArrayList<>(0);

        //De aqui pa'lante, el fume fue tan grande que ni yo mismo lo entiendo. Alejandro
        //Lo que trato de hacer es revisar las distintas formas en que pueden quedar las
        //listas para poder discernir cual(es) lista(s) es(son) vacia(s).
        if (listaParticipantes != null && !listaParticipantes.isEmpty() && hayParticipantes) {
            listas.add(listaParticipantes);
        }
        if (cjtoAux != null && !cjtoAux.isEmpty() && hayColumnas) {
            listas.add(cjtoAux);
        }
        if (listaRango != null && !listaRango.isEmpty() && hayRango) {
            listas.add(listaRango);
        }

        ArrayList<Actividad> listaInterceptada = new ArrayList<>(0);
        if (listas.isEmpty()) {
            if (hayParticipantes || hayColumnas || hayRango) {
              libro = paginar(listaInterceptada, mostrarPorPagina);
            } else {
              libro = paginar(cjtoAux, mostrarPorPagina);  
            }
        } else {
            listaInterceptada = intersectar(listas);
            libro = paginar(listaInterceptada, mostrarPorPagina);
        }
        totalPaginas = libro.size();

    }

    /**
     * Busca una página específica de la busqueda.
     *
     * @param pagina La página que se desea revisar.
     * @return Lista de Actividades ubicadas en la Pagina solicitada.
     */
    public static ArrayList<Actividad> buscarPagina(BusquedaActividad busqueda,
            int pagina) {
        //ArrayList<Actividad> resp = new ArrayList<>(0);
        if (busqueda.getLibro().size() > 0) {
            return busqueda.getLibro().get(pagina);
        }
        return new ArrayList<>(0);
    }

    /**
     * Vacía la información de todas las actividades en una lista en limpio
     *
     * @return Lista con todas las actividades generadas por la busqueda.
     */
    private ArrayList<Actividad> coleccion() {
        Iterator it = libro.iterator();
        ArrayList<Actividad> resp = new ArrayList<>(0);
        while (it.hasNext()) {
            ArrayList<Actividad> aux0 = (ArrayList<Actividad>) it.next();
            resp.addAll(aux0);
        }

        return resp;
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
     * Procedimineto para reconfigurar la paginacion de la busqueda.
     *
     * @param cantidadPorPagina Cantidad de actividades a mostrar por cada
     * pagina
     */
    public void repaginar(int cantidadPorPagina) {
        ArrayList<Actividad> compilacion = this.coleccion();
        libro = new ArrayList<>(0);
        Iterator it = compilacion.iterator();

        while (it.hasNext()) {
            ArrayList<Actividad> unaPagina = new ArrayList<>(0);
            for (int i = 0; i < cantidadPorPagina && it.hasNext(); i++) {
                unaPagina.add((Actividad) it.next());
            }
            libro.add(unaPagina);
        }
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
        Iterator it = unaLista.iterator();
        while (it.hasNext()) {
            Actividad unaActividad = (Actividad) it.next();
            boolean agregar = true;
            Iterator itAux = listas.iterator();
            while (itAux.hasNext() && agregar) {
                ArrayList<Actividad> aComparar = (ArrayList<Actividad>) itAux.next();
                agregar &= aComparar.contains(unaActividad);
            }
            if (agregar) {
                interseccion.add(unaActividad);
            }
        }
        return interseccion;
    }
}
