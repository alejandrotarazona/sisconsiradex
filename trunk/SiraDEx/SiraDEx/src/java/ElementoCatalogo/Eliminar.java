/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementoCatalogo;

import Clases.CampoCatalogoValor;
import Clases.ElementoCatalogo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author diana
 */
public class Eliminar extends org.apache.struts.action.Action {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ElementoCatalogo ec = (ElementoCatalogo) form;
        
        if (ec.eliminar()) {
            ec.setMensaje("El elemento ha sido eliminado");
            ArrayList<CampoCatalogoValor> elem = Clases.CampoCatalogoValor.listarElem(ec.getIdCatalogo());
            request.setAttribute("elementos", elem);
            return mapping.findForward(SUCCESS);
        } else {
            ec.setMensaje("El elemento que desea eliminar no existe.");
            ArrayList<CampoCatalogoValor> elem = Clases.CampoCatalogoValor.listarElem(ec.getIdCatalogo());
            request.setAttribute("elementos", elem);
            return mapping.findForward(FAILURE);
        }
    }
}
