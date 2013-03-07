/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposActividad;

import Clases.Campo.Par;
import Clases.Elemento;
import Clases.TipoActividad;
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
        ArrayList<Elemento> programas;
        programas = Clases.Elemento.listarElementos("Programas", 1);
        request.getSession().setAttribute("programas", programas);
        ArrayList<Elemento> coordinaciones;
        coordinaciones = Clases.Elemento.listarElementos("Coordinaciones", 1);
        request.getSession().setAttribute("coordinaciones", coordinaciones);
        ArrayList catalogos = Clases.Catalogo.listar();
        request.getSession().setAttribute("catalogos", catalogos);

        TipoActividad ta = (TipoActividad) form;
        ta.setMensaje(null);

        int idTA = ta.getIdTipoActividad();
        ta.setTipoActividad();
        ArrayList campos = Clases.Campo.listar(idTA);
        ta.setCampos(campos);

        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.Campo.listar(idTA);
        TipoActividad taNM = new TipoActividad();
        taNM.setId(idTA);
        taNM.setTipoActividad();
        taNM.setCampos(camposNM);
        request.getSession().setAttribute("taNM", taNM); 

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad ta = (TipoActividad) form;

        TipoActividad taNM = (TipoActividad) request.getSession().getAttribute("taNM");

        if (ta.modificar(taNM)) {

            ta.setMensaje("El tipo de actividad ha sido modificado con éxito.");
            ArrayList<TipoActividad> tas = Clases.TipoActividad.listar();
            request.setAttribute("tipos", tas);

            return mapping.findForward(SUCCESS);
        }

        ta.setNombreTipo(taNM.getNombreTipo());

        return mapping.findForward(FAILURE);


    }
}
