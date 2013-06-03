/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

import Clases.ElementoCatalogo;
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
        ta.setMensajeError(null);
        ta.setMensaje(null);
        
        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        request.getSession().setAttribute("programas", programas);
        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        request.getSession().setAttribute("dependencias", dependencias);
        
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad ta = (TipoActividad) form;
        ta.setMensajeError(null);
        ta.setMensaje(null);
        ArrayList catalogos = Clases.Catalogo.listarCondicion("participa",false);
        request.getSession().setAttribute("catalogos", catalogos);
        
        ArrayList catalogosPart = Clases.Catalogo.listarCondicion("participa",true);
        request.getSession().setAttribute("catalogosPart", catalogosPart);

        if (!Verificaciones.verifCF(ta)){
            return mapping.findForward(FAILURE);
        }
         /*verifica si hay un tipo de actividad con ese nombre*/
        if (ta.esTipoActividad()) {
            ta.setMensajeError("Error: Ya existe un Tipo de Actividad con el Nombre "
                    + "de la Actividad '" + ta.getNombreTipo() + "'. Por favor "
                    + "intente con otro nombre.");
            return mapping.findForward(FAILURE);
        }

        ta.setCampos();//llena el arrayList campos con el numero de campos necesario.
        
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
            
            ArrayList tipos = Clases.TipoActividad.listarCondicion("activo",true);
            request.setAttribute("tipos", tipos);
            String nombre = ta.getNombreTipo();
            Clases.Root.deleteSessions(request,"");
            request.setAttribute("mensaje","El Tipo de Actividad '" + nombre 
                    + "' ha sido registrado con éxito.");
            return mapping.findForward(SUCCESSFULL);
        }
        
        return mapping.findForward(FAILURE2);
    }
}
