/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void buscar() {
        libro = new ArrayList<>(0);
        totalPaginas = 0;

        Entity eBuscar = new Entity(0, 23);
        ArrayList<String> auxColumnas = new ArrayList<>(0);
        ArrayList<Object> auxCondiciones = new ArrayList<>(0);

        if (!this.nombreTipo.equals("") && this.nombreTipo != null) {
            auxColumnas.add("nombre_tipo_actividad");
            auxCondiciones.add(nombreTipo);
        }
        if (!this.tipoPR.equals("") && this.tipoPR != null) {
            auxColumnas.add("tipo_p_r");
            auxCondiciones.add(tipoPR);
        }
        if (!this.programa.equals("") && this.programa != null) {
            auxColumnas.add("programa");
            auxCondiciones.add(programa);
        }
        if (!this.validador.equals("") && this.validador != null) {
            auxColumnas.add("validador");
            auxCondiciones.add(validador);
        }
        if (!this.creador.equals("") && this.creador != null) {
            auxColumnas.add("creador");
            auxCondiciones.add(creador);
        }

        String[] columnas = (String[]) auxColumnas.toArray();
        Object[] condiciones = auxCondiciones.toArray(columnas);

        ResultSet rs = eBuscar.seleccionar(columnas, condiciones);
        
        ArrayList<Actividad> cjtoAux = new ArrayList<>(0);               //Resultado de la busqueda cochina y gigante//
        try {
            cjtoAux = Actividad.listar(rs);
        } catch (SQLException ex) {
            Logger.getLogger(BusquedaActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Actividad> listaParticipantes = new ArrayList<Actividad>(0);

        if (this.participantes != null) {
            Actividad aux = new Actividad();
            aux.setCreador(creador);
            listaParticipantes.addAll(aux.listarActividadesDeUsuario());    //Resultado de la busqueda de participantes/
        }
        
        Iterator it = listaParticipantes.iterator();
        ArrayList<Actividad> listaInterceptada = new ArrayList<>(0);

        while (it.hasNext() && !cjtoAux.isEmpty()) {                        //Intento de implementacion de la interseccion de conjuntos.
            Actividad auxAct = (Actividad) it.next();
            if(cjtoAux.contains(auxAct)){
                listaInterceptada.add(auxAct);
                cjtoAux.remove(auxAct);
            }
        }
        
        libro = paginar(listaInterceptada, mostrarPorPagina);
        
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
     * @param listaActividades Todas las actividades que se consiguieron en la busqueda.
     * @param cantidadPorPagina El numero de actividades que se pueden mostrar por pagina.
     * @return Una lista que, en cada posicion, contiene una lista de actividades. Representa
     * las paginas que se han de mostrar.
     */
    public static ArrayList<ArrayList<Actividad>> paginar(ArrayList<Actividad> listaActividades,
            int cantidadPorPagina){
        ArrayList<ArrayList<Actividad>> resp = new ArrayList<>(0);
        Iterator it = listaActividades.iterator();
        
        while(it.hasNext()){
            ArrayList<Actividad> aux = new ArrayList<>(0);
            for(int i = 0 ; i < cantidadPorPagina && it.hasNext(); i++){
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
}
