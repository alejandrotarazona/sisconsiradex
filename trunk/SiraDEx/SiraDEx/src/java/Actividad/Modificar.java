
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
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
 * @author Siscon
 */
public class Modificar extends DispatchAction {
    
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
        Actividad act = (Actividad) form;
        act.setMensaje(null);
        
        ArrayList campos = Clases.CampoValor.listarCamposValores(act.getIdActividad());
        act.setCamposValores(campos);
    
        act.setNombreTipoActividad();
        
                
        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.CampoValor.listarCamposValores(act.getIdActividad());
        request.getSession().setAttribute("camposNM", camposNM);
        return mapping.findForward(PAGE);
    }

   

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad act = (Actividad) form;
        
        ArrayList campos = (ArrayList) request.getSession().getAttribute("camposNM");
        
        if (act.modificar(campos)) {

            act.setMensaje("La actividad ha sido modificada con éxito.");
            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> acts;

            if (rol.equalsIgnoreCase("WM")) {
                acts = Clases.Actividad.listarActividades();
            } else {
                acts = act.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", acts);
            
            return mapping.findForward(SUCCESS);         
      
        }
        
        
        ArrayList ac = Clases.Actividad.listarActividades();
        request.setAttribute("actividades", ac);
        return mapping.findForward(FAILURE);


    }
    
}
