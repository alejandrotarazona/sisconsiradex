/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.Campo;
import Clases.CampoValor;
import Clases.Elemento;
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
 * @author SisCon
 */
public class Agregar extends DispatchAction {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String SUCCESSFULL = "successfull";
    private static final String FAILURE = "failure";
    private static final String PAGE = "page";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList ta = Clases.TipoActividad.listarTiposActividad();
        int tam = ta.size();
        if (tam != 0) {
            request.setAttribute("tipos", ta);
        } else {
            request.setAttribute("tipos", null);
        }
        Actividad a = (Actividad) form;
        a.setMensaje(null);
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;
        a.setNombreTipoActividad();
        int id = a.getIdTipoActividad();
        ArrayList<CampoValor> valores = Clases.CampoValor.listar(id);
        CampoValor producto = new CampoValor();
        
        
        Campo c = new Campo();
        c.setObligatorio(true);
        c.setTipo("archivo");
        TipoActividad ta = Clases.TipoActividad.getTipoActividad(id);
        c.setNombre(ta.getProducto());
        
        producto.setCampo(c);
        valores.add(producto);
                
        a.setCamposValores(valores);

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String username = u.getUsername();
        a.setCreador(username);
        
        
        
        ArrayList<Elemento> catalogo;
        String nombreCat = a.getCampoValor(0).getCampo().getCatalogo();
        catalogo = Clases.Elemento.listarElementos(nombreCat, 5);
        request.getSession().setAttribute("cat", catalogo);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;

        if (a.agregarActividad()) {

            a.setMensaje("Su actividad ha sido registrado con éxito.");

            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> act;

            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);

            return mapping.findForward(SUCCESSFULL);
        }

        a.setMensaje("No se pudo registrar la actividad. Por favor revise que "
                + "los campos se han llenado correctamente.");
        return mapping.findForward(FAILURE);


    }
    public ActionForward saveTipo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;

        if (a.agregarActividad()) {

            a.setMensaje("Su actividad ha sido registrado con éxito.");

            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> act;

            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);

            return mapping.findForward(SUCCESSFULL);
        }

        a.setMensaje("No se pudo registrar la actividad. Por favor revise que "
                + "los campos se han llenado correctamente.");
        return mapping.findForward(FAILURE);


    }
}