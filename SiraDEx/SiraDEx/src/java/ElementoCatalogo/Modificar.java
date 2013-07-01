/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementoCatalogo;

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
 * @author Siscon
 */
public class Modificar extends DispatchAction {

    /* forward name="success" path="" */
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

        request.getSession().setAttribute("mensajeElem", null);
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        ElementoCatalogo e = (ElementoCatalogo) form;
        e.setMensaje(null);


        ArrayList campos = Clases.CampoCatalogoValor.listarCamposValores(e.getIdElemento());
        e.setCamposValores(campos);
        int idCat = e.getIdCatalogo();
        e.setIdCatalogo(idCat);
        e.setNombreCatalogo(Clases.Catalogo.getNombre(idCat));

        request.getSession().setAttribute("usbidNM", e.getCampoUsuario());

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        ElementoCatalogo e = (ElementoCatalogo) form;

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        String usbidNM = (String) request.getSession().getAttribute("usbidNM");

        if (e.modificar(usbidNM, ip, usuario)) {
            request.getSession().setAttribute("mensajeElem", e.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(PAGE);
    }
}
