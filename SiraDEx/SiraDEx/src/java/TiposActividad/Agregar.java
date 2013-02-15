/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposActividad;

import Clases.Campo;
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
        ArrayList<Elemento> programas;
        programas = Clases.Elemento.listarElementos("Programas",1);
        request.getSession().setAttribute("programas", programas);
        ArrayList<Elemento> coordinaciones;
        coordinaciones = Clases.Elemento.listarElementos("Coordinaciones",1);
        request.getSession().setAttribute("coordinaciones", coordinaciones);
        TipoActividad t = (TipoActividad) form;
        t.setMensaje(null);
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        TipoActividad t = (TipoActividad) form;
        ArrayList catalogos = Clases.Catalogo.listar();
        request.getSession().setAttribute("catalogos", catalogos);
        
        if (t.getNombreTipo().contains(";") || t.getNombreTipo().contains("<")
                || t.getNombreTipo().contains(">") || t.getNombreTipo().contains("'")
                || t.getNombreTipo().contains("&") || t.getNombreTipo().contains("$")) {
            t.setMensaje("Error: El nombre del tipo de actividad contiene uno de los "
                    + "siguientes carácteres inválidos: ;>?'&$ por favor "
                    + "intente de nuevo.");
            return mapping.findForward(FAILURE);

        } else if (t.getNroCampos() <= 0) {
            t.setMensaje("Error: El número de campos de un tipo de actividad "
                    + "debe ser de al menos 1. Ingrese un número válido y "
                    + "presione Siguiente.");
            return mapping.findForward(FAILURE);
        } else if (t.getDescripcion().equals("") || t.getNombreTipo().equals("") ||
                t.getPermiso().equals("") || t.getPrograma().equals("") || t.getTipoPR().equals("")
                || t.getValidador().equals("")){
            t.setMensaje("Error: Los campos con obligatorios deben ser llenados");
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
