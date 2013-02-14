/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposActividad;

import Clases.Campo;
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
public class Agregar extends DispatchAction {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String SUCCESSFULL = "successfull";
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
        /*ArrayList programas = Clases.Elemento.listarElementos("Programas");
         request.setAttribute("programas", programas);*/
        TipoActividad t = (TipoActividad) form;
        t.setMensaje("");
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad t = (TipoActividad) form;

        if (t.getNombreTipo().contains(";") || t.getNombreTipo().contains("<")
                || t.getNombreTipo().contains(">") || t.getNombreTipo().contains("'")
                || t.getNombreTipo().contains("&") || t.getNombreTipo().contains("$")) {
            t.setMensaje("El nombre tiene un caracter invalido, por favor "
                    + "intente de nuevo.");
            return mapping.findForward(FAILURE);

        } else if (t.getNroCampos() <= 0) {
            t.setMensaje("El número de campos DEBE SER mayor o igual a 1 campos. "
                    + "Actualmente presenta "
                    + t.getNroCampos() + " campos. Por favor elija otro número.");
            
            return mapping.findForward(FAILURE);
        }
        int numeroCampos = t.getNroCampos();
        ArrayList<Campo> campos = new ArrayList<>();

        for (int i = 0; i < numeroCampos; i++) {
            Campo c = new Campo();
            c.setIdTipoActividad(t.getId());
            campos.add(c);
        }

        t.setCampos(campos);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad t = (TipoActividad) form;

        if (t.agregarTipoActividad()) {

            t.setMensaje("El tipo de actividad '" + t.getNombreTipo() + "' ha sido "
                    + "registrado con éxito.");
            ArrayList ta = Clases.TipoActividad.listarTiposActividad();
            request.setAttribute("tipos", ta);
            return mapping.findForward(SUCCESSFULL);
        }
        return mapping.findForward(FAILURE);


    }
}
