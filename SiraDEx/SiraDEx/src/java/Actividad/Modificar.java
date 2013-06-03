
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.CampoValor;
import Clases.ElementoCatalogo;
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
        act.setActividad();

        /*ArrayList con los valores no modificados*/
        ArrayList camposNM = Clases.CampoValor.listarCamposValores(act.getIdActividad());
        request.getSession().setAttribute("camposNM", camposNM);

        /*Se pasan los catalogos de los campos tipo catalogo al jsp de ser necesario*/
        for (int i = 0; i < act.getCamposValores().size(); i++) {
            String nombreCat = act.getCamposValores().get(i).getCampo().getCatalogo();
            if (!nombreCat.equals("")) {
                ArrayList<ElementoCatalogo> catalogo = Clases.ElementoCatalogo.listarElementos(nombreCat, 0);
                request.getSession().setAttribute("cat" + i, catalogo);
            }
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Actividad act = (Actividad) form;

        ArrayList campos = (ArrayList) request.getSession().getAttribute("camposNM");
        Usuario modificador = (Usuario) request.getSession().getAttribute("user");
        act.setModificador(modificador.getUsername());
        CampoValor.auxModificarArchivo(campos, act.getCamposValores());

        if (act.modificar(campos)) {

            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> acts;
            String[] estadistica = u.cantidadActividadesPorTipo();

            if (rol.equalsIgnoreCase("WM")) {
                acts = Clases.Actividad.listarActividades();
            } else {
                acts = Actividad.listarActividadesDeUsuario(u.getUsername());
            }
            request.setAttribute("acts", acts);
            request.setAttribute("estadisticaNombres", estadistica[0]);
            request.setAttribute("estadisticaCantidad", estadistica[1]);

            String nombre = act.getNombreTipoActividad();
            //act.enviarCorreo(1);
            Clases.Root.deleteSessions(request, "");
            request.setAttribute("mensaje", "La Actividad '" + nombre + "' ha sido modificada con éxito.");
            act.setMensajeError(null);
            
            return mapping.findForward(SUCCESS);

        }

        ArrayList ac = Clases.Actividad.listarActividades();
        request.setAttribute("actividades", ac);

        return mapping.findForward(FAILURE);


    }
}
