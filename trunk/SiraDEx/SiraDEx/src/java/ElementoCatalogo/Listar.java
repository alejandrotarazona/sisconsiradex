/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementoCatalogo;

import Clases.ElementoCatalogo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author SisCon
 */
public class Listar extends org.apache.struts.action.Action {

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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ElementoCatalogo e = (ElementoCatalogo) form;
        e.deleteSessions(request);
        int idCat = e.getIdCatalogo();
        request.setAttribute("nombreCat", Clases.Catalogo.getNombre(idCat));
        ArrayList<ElementoCatalogo> ec = Clases.ElementoCatalogo.listarElementosId(idCat);
        request.setAttribute("elementos", ec);
        int tam = ec.size();
        if (tam > 0) {
            e = ec.get(tam - 1);
            request.setAttribute("campos", e.getCamposValores());
        } else {
            request.setAttribute("elementos", null);
        }
        return mapping.findForward(SUCCESS);
    }
}
