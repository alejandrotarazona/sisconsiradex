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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        ElementoCatalogo elemCat = (ElementoCatalogo) form;


        ArrayList campos = Clases.CampoCatalogoValor.listarCamposValores(elemCat.getIdElemento());
        elemCat.setCamposValores(campos);
        int idCat = elemCat.getIdCatalogo();
        elemCat.setIdCatalogo(idCat);
        elemCat.setNombreCatalogo(Clases.Catalogo.getNombre(idCat));

        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.CampoCatalogoValor.listarCamposValores(elemCat.getIdElemento());
        request.getSession().setAttribute("camposNM", camposNM);

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        ElementoCatalogo elemCat = (ElementoCatalogo) form;

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        ArrayList campos = (ArrayList) request.getSession().getAttribute("camposNM");

        if (elemCat.modificar(campos, ip, usuario)) {
            request.getSession().setAttribute("mensajeElem", elemCat.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        return mapping.findForward(FAILURE);
    }
}
