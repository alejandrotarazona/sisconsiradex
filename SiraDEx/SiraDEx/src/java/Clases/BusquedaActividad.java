/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author SisCon
 */
public class BusquedaActividad {

    private String nombreTipo;
    private String tipoPR;
    private String programa;
    private String validador;
    private ArrayList<String> participantes = new ArrayList<>(0);
    private String creador; //usbid
    private String fechaCreacion;
    private String fechaModif;
    private int mostrarPorPagina;
    private int totalPaginas;
    private ArrayList<ArrayList<Actividad>> libro;

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

    public void setParticipantes(ArrayList<String> participantes) {
        this.participantes = participantes;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fechaModif) {
        this.fechaModif = fechaModif;
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

    /**
     * Metodo para buscar por cada uno de los criterios dados.
     *
     * @return Las páginas con las listas de Actividades por página segun la
     * busqueda establecida.
     */
    public ArrayList<ArrayList<Actividad>> buscar() {
        libro = new ArrayList<>(0);
        ArrayList<TreeSet<Actividad>> listasDeBusqueda = new ArrayList<>(0);
        totalPaginas = 0;

        if (!this.nombreTipo.equals("") && this.nombreTipo != null) {
            Actividad aux0 = new Actividad();
            aux0.setNombreTipoActividad(nombreTipo);
            TipoActividad auxTA0 = new TipoActividad();
            auxTA0.setNombreTipo(nombreTipo);
            auxTA0.setId();
            aux0.setIdTipoActividad(auxTA0.getId());

            ArrayList<Actividad> estaLista = aux0.listarActividadesDeTipo();
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }
        if (!this.tipoPR.equals("") && this.tipoPR != null) {
            ArrayList<Actividad> estaLista = Actividad.listarActividadesPR(tipoPR);
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }
        if (!this.programa.equals("") && this.programa != null) {
            ArrayList<Actividad> estaLista = Actividad.listarActividadesPrograma(programa);
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }
        if (!this.validador.equals("") && this.validador != null) {
            ArrayList<Actividad> estaLista = Actividad.listarActividadesDeValidador(validador);
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }
        if (!this.creador.equals("") && this.creador != null) {
            ArrayList<Actividad> estaLista = Actividad.listarActividadesDeCreador(creador);
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }
        if (this.participantes != null) {
            Actividad aux = new Actividad();
            aux.setCreador(creador);
            ArrayList<Actividad> estaLista = aux.listarActividadesDeUsuario();
            TreeSet<Actividad> esteConjunto = new TreeSet();
            esteConjunto.addAll(estaLista);
            listasDeBusqueda.add(esteConjunto);
        }

        TreeSet<Actividad> cjtoAux = listasDeBusqueda.get(0);
        Iterator it = listasDeBusqueda.iterator();

        while (it.hasNext()) {                        //Intento de implementacion de la interseccion de conjuntos.
            TreeSet<Actividad> aux0 = (TreeSet<Actividad>) it.next();
            TreeSet<Actividad> aux1 = (TreeSet<Actividad>) cjtoAux.clone();
            Iterator it0 = aux0.iterator();
            while (it0.hasNext()) {
                aux1.remove((Actividad) it0.next());    //Dejo los elementos no comunes.
            }
            Iterator it1 = aux1.iterator();
            while (it1.hasNext()) {
                cjtoAux.remove((Actividad) it1.next()); //Elimino los elementos no comunes del cjto que usaré.
            }
        }

        it = cjtoAux.iterator();
        while (it.hasNext()) {
            ArrayList<Actividad> unaPagina = new ArrayList<>(0);
            for (int i = 0; i < mostrarPorPagina && it.hasNext(); i++) {
                Actividad act = (Actividad) it.next();
                unaPagina.add(act);
            }
            libro.add(unaPagina);
            totalPaginas++;
        }
        return libro;
    }

    /**
     * Busca una página específica de la busqueda.
     *
     * @param pagina La página que se desea revisar.
     * @return Lista de Actividades ubicadas en la Pagina solicitada.
     */
    public static ArrayList<Actividad> buscarPagina(BusquedaActividad busqueda, int pagina) {
        ArrayList<Actividad> resp = busqueda.getLibro().get(pagina);
        return resp;
    }
}
