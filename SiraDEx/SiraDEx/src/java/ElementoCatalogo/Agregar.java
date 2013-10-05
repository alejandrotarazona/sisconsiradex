/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementoCatalogo;

import Clases.CampoCatalogoValor;
import Clases.ElementoCatalogo;
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

        Clases.Root.deleteSessions(request, "elementoCatalogoForm");
        ElementoCatalogo e = (ElementoCatalogo) form;
        e.setMensaje(null);
        int idCat = e.getIdCatalogo();
        e.setNombreCatalogo(Clases.Catalogo.getNombre(idCat));

        ArrayList<ElementoCatalogo> usuarios = Clases.ElementoCatalogo.listarUsuarios();
        request.getSession().setAttribute("usuarios", usuarios);

        ArrayList<CampoCatalogoValor> valores = Clases.CampoCatalogoValor.listar(idCat);
        e.setCamposValores(valores);

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

        ElementoCatalogo e = (ElementoCatalogo) form;

        if (e.agregar(ip, usuario)) {
            request.getSession().setAttribute("mensajeElem", e.getMensaje());

            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
