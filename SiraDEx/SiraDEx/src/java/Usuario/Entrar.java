/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

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
public class Entrar extends DispatchAction {
    /*
     * forward name="success" path=""
     */
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

    public ActionForward inPO(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("00-00000");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward inEA(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("05-38978");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward inES(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("07-41618");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    
    public ActionForward inDEx(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("99-10000");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }


    public ActionForward inProf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("kdoming");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward inWM(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setUsername("05-38199");
        u.setUsuario();
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
}
