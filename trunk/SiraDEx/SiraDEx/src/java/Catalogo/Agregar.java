/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

import Clases.Catalogo;
import Clases.Usuario;
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

        Catalogo cat = (Catalogo) form;
        cat.setMensaje(null);
        request.getSession().setAttribute("mensajeCat", null);
  
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }

        Catalogo cat = (Catalogo) form;

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        cat.setMensaje(null);
        int elimino = cat.eliminarCamposMarcados(null);
        if (elimino > 0) {
            return mapping.findForward(PAGE);
        }

        int numeroCampos = cat.getNroCampos();
        if (numeroCampos > 0) {
            cat.agregarCamposNuevos();
            return mapping.findForward(PAGE);
        }

        if (cat.agregar(ip, usuario)) {
            request.getSession().setAttribute("mensajeCat", cat.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
