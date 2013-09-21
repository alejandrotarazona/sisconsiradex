/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author alejandro
 */
public class Listar extends DispatchAction {

    /* forward name="success" path="" */
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

    public ActionForward preList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "");

        return new ActionRedirect("/GestionCatalogos.do?method=list");
    }
    
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Clases.Root.deleteSessions(request, "mensajeCat");
        ArrayList cats = Clases.Catalogo.listarCatalogos();

        if (cats.isEmpty()) {
            cats = null;
        }
        request.setAttribute("catalogos", cats);

        return mapping.findForward(SUCCESS);
    }
}
