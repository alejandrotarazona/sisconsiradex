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
        ta.setMensaje(null);
        ArrayList catalogos = Clases.Catalogo.listar();
        request.getSession().setAttribute("catalogos", catalogos);

        if (!Verificaciones.verif(ta)) {
            return mapping.findForward(FAILURE);
        }
        
        int numeroCampos = ta.getNroCampos();
        ArrayList<Campo> campos = new ArrayList<>();

        for (int i = 0; i < numeroCampos; i++) {
            Campo c = new Campo();
            c.setIdTipoActividad(ta.getId());
            campos.add(c);
        }

        ta.setCampos(campos);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TipoActividad ta = (TipoActividad) form;
        ta.setMensaje(null);
        ArrayList<Campo> c = ta.getCampos();

        for (int i = 1; i <= ta.getNroCampos(); i++) {
            try {
                Campo aux = c.get(i - 1);
                if (aux.isNombreInvalido()) {
                    ta.setMensaje("El campo número " + i
                            + " contiene un nombre inválido");
                    return mapping.findForward(FAILURE2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ta.setMensaje("El campo número " + i
                        + " contiene un nombre inválido");
                return mapping.findForward(FAILURE2);
            }
        }

        if (Verificaciones.verif(ta.getCampos())) {

            if (ta.agregarTipoActividad()) {

                ta.setMensaje("El tipo de actividad '" + ta.getNombreTipo() + "' ha sido "
                        + "registrado con éxito.");
                ArrayList tipos = Clases.TipoActividad.listar();
                request.setAttribute("tipos", tipos);
                return mapping.findForward(SUCCESSFULL);
            }
            return mapping.findForward(FAILURE);
        } else {
            ta.setMensaje("Los campos no pueden ser llenados sólo con espacios\n"
                    + "Si elige un campo texto, debe seleccionar una longitud mayor que cero (0)\n"
                    + "Si elige un campo tipo catálogo, debe seleccionar un catálogo de la lista");
            return mapping.findForward(FAILURE2);
        }


    }
}
