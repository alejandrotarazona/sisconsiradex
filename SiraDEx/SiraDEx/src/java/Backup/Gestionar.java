/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import Clases.Backup;
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
public class Gestionar extends DispatchAction {

    /* forward name="success" path="" */
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
        Clases.Root.deleteSessions(request, "");
        Backup b = (Backup) form;
        b.inicializarFrecuencia();

        return mapping.findForward(PAGE);
    }

    public ActionForward make(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Backup b = (Backup) form;

        b.hacerBackup();

        return mapping.findForward(PAGE);

    }

    public ActionForward restore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Backup b = (Backup) form;

        b.restaurarDesdeBackup();

        return mapping.findForward(PAGE);
    }

    public ActionForward set(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Backup b = (Backup) form;

        b.cambiarFrecuencia();

        return mapping.findForward(PAGE);
    }
}
