/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Clases.Usuario;
import java.util.ArrayList;
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
public class Agregar extends DispatchAction {

    /*
     * forward name="success" path=""
     */
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
        Usuario u = new Usuario();
        u.setMensaje(null);
        u.setMensajeError(null);

        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) form;
        u.setMensaje(null);
        u.setMensajeError(null);
        
        if (u.agregarUsuario()) {
            u.setMensaje(null);
            u.setMensaje("El usuario ha sido registrado con éxito.");
            ArrayList<Usuario> usuarios;
            usuarios = Clases.Usuario.listarUsuario();
            request.setAttribute("usuarios", usuarios);
            return mapping.findForward(SUCCESS);
        } else {
            u.setMensajeError(null);
            u.setMensajeError("Error: Falló el registro. El usuario ya existe en el sistema.");
            return mapping.findForward(FAILURE);
        }
    }
}
