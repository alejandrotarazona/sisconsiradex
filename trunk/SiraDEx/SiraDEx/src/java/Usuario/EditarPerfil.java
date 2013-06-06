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
 * @author Siscon
 */
public class EditarPerfil extends DispatchAction {
  
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
        Clases.Root.deleteSessions(request,"mensaje");
        Usuario u = (Usuario) form;
        u.setMensaje(null);
        u.setMensajeError(null);
        
        Usuario userNM = new Usuario();
        userNM.setUsername(u.getUsername());
        userNM.setUsuario();
        request.getSession().setAttribute("userNM", userNM);
        
        return mapping.findForward(PAGE);
    }
    
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        Usuario u = (Usuario) form;
        
        Usuario userNM = (Usuario) request.getSession().getAttribute("userNM");
            
        if (u.modificar(userNM)) {
            Clases.Root.deleteSessions(request,"");
            request.getSession().setAttribute("mensaje", "El perfil ha sido modificado con éxito");
            return mapping.findForward(SUCCESS);         
        }
        return mapping.findForward(PAGE);

    }
    
}
