/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import Clases.Backup;
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
public class Gestionar extends DispatchAction {
  
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
        Clases.Root.deleteSessions(request, "backupForm");
        Backup b = (Backup) form;
        b.inicializarFrecuencia();
        b.setMensajeError(null);
        b.setMensaje(null);
               
        return mapping.findForward(PAGE);
    }

    public ActionForward make(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Backup b = (Backup) form;
        b.setMensajeError(null);
        b.setMensaje(null);
   
        if (b.hacerBackup()) {
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(FAILURE);

    }

    public ActionForward restore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Backup b = (Backup) form;
        b.setMensajeError(null);
        b.setMensaje(null);
   
        if (b.restaurarDesdeBackup()) {
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(FAILURE);
    }
    public ActionForward set(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Backup b = (Backup) form;
        b.setMensajeError(null);
        b.setMensaje(null);
   
        if (b.cambiarFrecuencia()) {
            return mapping.findForward(SUCCESS);
        }
        
        return mapping.findForward(FAILURE);
    }
}
