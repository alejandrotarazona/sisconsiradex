/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

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
        u.setRol("PO");
        u.setTipo(3);
        u.setUsername("00-00000");
        u.setPassword("123456");
        u.setNombres("Pablo");
        u.setApellidos("Perez");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward inEA(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setRol("EA");
        u.setTipo(3);
        u.setUsername("05-38978");
        u.setPassword("alejandro");
        u.setNombres("Alejandro");
        u.setApellidos("Tarazona");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward inES(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setRol("ES");
        u.setTipo(3);
        u.setUsername("07-41618");
        u.setPassword("diana");
        u.setNombres("Diana");
        u.setApellidos("Vainberg");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
    
    
    
    public ActionForward inDEx(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setRol("DEx");
        u.setTipo(2);
        u.setUsername("07-10000");
        u.setPassword("pedro");
        u.setNombres("Pedro");
        u.setApellidos("Perez");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }


    public ActionForward inProf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setRol("Prof");
        u.setTipo(4);
        u.setUsername("kdoming");
        u.setPassword("kdoming");
        u.setNombres("Kenyer");
        u.setApellidos("Dominguez");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward inWM(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setRol("WM");
        u.setTipo(3);
        u.setUsername("05-38199");
        u.setPassword("123456");
        u.setNombres("Jorge");
        u.setApellidos("García");
        request.getSession().setAttribute("user", u);
        return mapping.findForward(SUCCESS);
    }
}
