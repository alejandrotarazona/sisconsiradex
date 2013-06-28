/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

import Clases.Catalogo;
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
public class Modificar extends DispatchAction {

    /* forward name="success" path="" */
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
            return mapping.findForward(SUCCESS);
        }
        Clases.Root.deleteSessions(request, "catalogoForm");
        
        Catalogo cat = (Catalogo) form;

        int idCat = cat.getIdCatalogo();
        cat.setCatalogo();
        ArrayList campos = Clases.CampoCatalogo.listar(idCat);
        cat.setCampos(campos);

        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.CampoCatalogo.listar(idCat);
        Catalogo catNM = new Catalogo();
        catNM.setIdCatalogo(idCat);
        catNM.setCatalogo();
        catNM.setCampos(camposNM);
        request.getSession().setAttribute("catNM", catNM);
        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS);
        }
        Catalogo cat = (Catalogo) form;

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        Catalogo catNM = (Catalogo) request.getSession().getAttribute("catNM");
        cat.setMensaje(null);
        int elimino = cat.eliminarCamposMarcados(catNM);
        if (elimino > 0 || elimino < 0) {
            return mapping.findForward(PAGE);
        }

        int numeroCampos = cat.getNroCampos();
        if (numeroCampos > 0) {
            cat.agregarCamposNuevos();
            return mapping.findForward(PAGE);
        }

        if (cat.modificar(catNM, ip, usuario)) {
            request.getSession().setAttribute("mensajeCat", cat.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        cat.setNombre(catNM.getNombre());
        return mapping.findForward(PAGE);
    }
}
