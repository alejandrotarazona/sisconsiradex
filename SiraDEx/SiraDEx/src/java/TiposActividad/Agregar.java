/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposActividad;

import Clases.Campo;
import Clases.Elemento;
import Clases.TipoActividad;
import Clases.Verificaciones;
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
    private static final String SUCCESSFULL = "successfull";
    private static final String FAILURE = "failure";
    private static final String FAILURE2 = "failureCampos";
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
        TipoActividad ta = (TipoActividad) form;
        ta.deleteSessions(request);
        ArrayList<Elemento> programas;
        programas = Clases.Elemento.listarElementos("Programas", 1);
        request.getSession().setAttribute("programas", programas);
        ArrayList<Elemento> coordinaciones;
        coordinaciones = Clases.Elemento.listarElementos("Coordinaciones", 1);
        request.getSession().setAttribute("coordinaciones", coordinaciones);
        
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad ta = (TipoActividad) form;
        ta.setMensajeError(null);
        ArrayList catalogos = Clases.Catalogo.listar();
        request.getSession().setAttribute("catalogos", catalogos);
        
                
        if (ta.esTipoActividad()) {
            ta.setMensajeError("Error: Ya existe un Tipo de Actividad con el Nombre "
                    + "de la Actividad '" + ta.getNombreTipo() + "'. Por favor "
                    + "intente con otro nombre.");
            return mapping.findForward(FAILURE);
        }

        if (!Verificaciones.verifCF(ta)) {
            return mapping.findForward(FAILURE);
        }

        ta.setCampos();
        //hay que guardar permisos porque se pierde luego por el metodo reset()
        request.getSession().setAttribute("permisos", ta.getPermisos());
        
        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad ta = (TipoActividad) form;
        ta.setMensajeError(null);
        ta.setPermisos((String[])request.getSession().getAttribute("permisos"));
        if (ta.agregarTipoActividad()) {
            
            ArrayList tipos = Clases.TipoActividad.listar();
            request.setAttribute("tipos", tipos);
            return mapping.findForward(SUCCESSFULL);
        }
        
        return mapping.findForward(FAILURE2);
    }
}
