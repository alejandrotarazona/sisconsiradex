/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Clases.Usuario;
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
        
        Clases.Root.deleteSessions(request,"");
        ArrayList usrs = Clases.Usuario.listarUsuario();
             
        Usuario u = (Usuario) form;
        u.setMensaje(null);
        u.setMensaje(null);
        int tam = usrs.size();
        if (tam != 0) {
            request.setAttribute("usuarios", usrs);
        } else {
            request.setAttribute("usuarios", null);
        }
        return mapping.findForward(SUCCESS);
    }
}
