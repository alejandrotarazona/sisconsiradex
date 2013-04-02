/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

import Clases.Catalogo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author alejandro
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
        Catalogo cat = (Catalogo) form;

        if (cat.eliminar(cat.getIdCatalogo())) {
            cat.setMensaje("El catálogo ha sido eliminado");
            ArrayList cats = Clases.Catalogo.listar();
            request.setAttribute("catalogos", cats);
            cat.setMensajeError(null);
            return mapping.findForward(SUCCESS);
        } else {
            cat.setMensajeError("Error: El catálogo no pudo ser eliminado");
            ArrayList cats = Clases.Catalogo.listar();
            request.setAttribute("catalogos", cats);
            return mapping.findForward(FAILURE);
        }
    }
}
