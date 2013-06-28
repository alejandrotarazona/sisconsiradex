/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.ElementoCatalogo;
import Clases.TipoActividad;
import Clases.Usuario;
import Clases.Verificaciones;
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
    private static final String PAGE = "page";
    private static final String SUCCESS = "success";

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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }

        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        request.getSession().setAttribute("programas", programas);

        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        request.getSession().setAttribute("dependencias", dependencias);

        ArrayList catalogos = Clases.Catalogo.listarCondicion("participa", false);
        request.getSession().setAttribute("catalogos", catalogos);

        ArrayList catalogosPart = Clases.Catalogo.listarCondicion("participa", true);
        request.getSession().setAttribute("catalogosPart", catalogosPart);

        TipoActividad ta = (TipoActividad) form;

        ta.setCamposObligatorios();

        return mapping.findForward(PAGE);

    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        TipoActividad ta = (TipoActividad) form;

        ta.setMensaje(null);

        int elimino = ta.eliminarCamposMarcados();
        if (elimino > 0 || elimino
                < 0) {
            return mapping.findForward(PAGE);
        }
        int numeroCampos = ta.getNroCampos();
        if (numeroCampos
                > 0) {
            ta.agregarCamposNuevos();
            return mapping.findForward(PAGE);
        }

        if (!Verificaciones.verificarCamposFijos(ta)) {
            return mapping.findForward(PAGE);
        }

        if (ta.esTipoActividad()) {
            ta.setMensaje("Error: Ya existe un Tipo de Actividad con el Nombre "
                    + "de la Actividad '" + ta.getNombreTipo() + "'. Por favor "
                    + "intente con otro nombre.");
            return mapping.findForward(PAGE);
        }

        if (ta.agregar(ip, usuario)) {
            request.getSession().setAttribute("mensajeTipo", ta.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
