/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.BusquedaActividad;
import Clases.ElementoCatalogo;
import Clases.Root;
import Clases.TipoActividad;
import Clases.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Siscon
 */
public class Buscar extends DispatchAction {

    /* forward name="success" path="" */
    private final static String PAGE = "page";
    private final static String PAGINA = "pagina";

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1, where "method" is the value
     * specified in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Root.deleteSessions(request, "busquedaActividadForm");
        ArrayList<TipoActividad> ta = Clases.TipoActividad.listarCondicion("activo", true);
        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        ArrayList<ElementoCatalogo> usuarios = Clases.ElementoCatalogo.listarParticipantes();


        System.out.println("Ya en la accion. Nombres preparados para ser pasados\n"
                + "a la pagina para mostrar.");

        request.setAttribute("validadores", dependencias);
        request.setAttribute("programas", programas);
        request.setAttribute("tiposdeactividad", ta);
        request.setAttribute("usuarios", usuarios);//mientras tanto

        return mapping.findForward(PAGE);
    }

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction2, where "method" is the value
     * specified in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BusquedaActividad ba = (BusquedaActividad) form;
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String usuario = user.getUsername(); 
        String ip = request.getHeader("X-Forwarded-For");

        ArrayList<TipoActividad> ta = Clases.TipoActividad.listarCondicion("activo", true);
        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        ArrayList<ElementoCatalogo> usuarios = Clases.ElementoCatalogo.listarParticipantes();
        

        ba.buscar(false, ip, usuario);
        String[] grafica = ba.getGrafica();
        
        ArrayList<String> pags = new ArrayList<>(0);

        for (int i = 1; i <= ba.getTotalPaginas(); i++) {
            pags.add("" + i);
        }

        ArrayList<Actividad> acts = null;

        if (BusquedaActividad.buscarPagina(ba, 0) != null) {
            acts = BusquedaActividad.buscarPagina(ba, 0);
        } else {
            acts = null;
        }

        System.out.println("Actividades para mostrar (nros):");
        for (int i = 0; i < acts.size(); i++) {
            System.out.println("\t" + i + ".- " + acts.get(i).getNombreTipoActividad());
        }

        request.getSession().setAttribute("paginas", pags);
        request.getSession().setAttribute("actividades", acts);
        request.getSession().setAttribute("busqueda", ba);
        request.getSession().setAttribute("validadores", dependencias);
        request.getSession().setAttribute("programas", programas);
        request.getSession().setAttribute("tiposdeactividad", ta);
        request.getSession().setAttribute("usuarios", usuarios);//mientras tanto
        request.getSession().setAttribute("graficaNombres", grafica[0]);
        request.getSession().setAttribute("graficaCantidad", grafica[1]);
        return mapping.findForward(PAGINA);
    }

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction2, where "method" is the value
     * specified in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward publicSearch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BusquedaActividad ba = (BusquedaActividad) form;

        ArrayList<TipoActividad> ta = Clases.TipoActividad.listarCondicion("activo", true);
        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        ArrayList<ElementoCatalogo> usuarios = Clases.ElementoCatalogo.listarParticipantes();        
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        ba.buscar(true, ip, usuario);
        String[] grafica = ba.getGrafica();
        
        ArrayList<String> pags = new ArrayList<>(0);

        for (int i = 1; i <= ba.getTotalPaginas(); i++) {
            pags.add("" + i);
        }

        ArrayList<Actividad> acts = BusquedaActividad.buscarPagina(ba, 0);

        System.out.println("Actividades para mostrar (nros):");
        /*for (int i = 1; i <= acts.size(); i++) {
         System.out.println(i + ".- " + acts.get(i).getNombreTipoActividad());
         }*/

        request.getSession().setAttribute("paginas", pags);
        request.getSession().setAttribute("actividades", acts);
        request.getSession().setAttribute("busqueda", ba);
        request.getSession().setAttribute("validadores", dependencias);
        request.getSession().setAttribute("programas", programas);
        request.getSession().setAttribute("tiposdeactividad", ta);
        request.getSession().setAttribute("usuarios", usuarios);//mientras tanto
        request.getSession().setAttribute("graficaNombres", grafica[0]);
        request.getSession().setAttribute("graficaCantidad", grafica[1]);
        return mapping.findForward(PAGINA);
    }

    public ActionForward aPagina(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BusquedaActividad ba = (BusquedaActividad) form;
        BusquedaActividad busquedaRealizada = (BusquedaActividad) request.getSession().getAttribute("busqueda");
        String[] grafica = ba.getGrafica();


        ArrayList<Actividad> acts = ba.getPagina(ba.getPagina());
        System.out.println("Actividades para mostrar (nros):");
        /*for (int i = 1; i <= acts.size(); i++) {
         System.out.println(i + ".- " + acts.get(i).getNombreTipoActividad());
         }*/

        request.getSession().setAttribute("actividades", acts);
        request.getSession().setAttribute("graficaNombres", grafica[0]);
        request.getSession().setAttribute("graficaCantidad", grafica[1]);
        

        return mapping.findForward(PAGINA);
    }
}
