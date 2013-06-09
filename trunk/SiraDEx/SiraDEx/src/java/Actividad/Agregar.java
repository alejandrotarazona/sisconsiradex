/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.CampoValor;
import Clases.ElementoCatalogo;
import Clases.TipoActividad;
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

        Clases.Root.deleteSessions(request, "");
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        ArrayList<TipoActividad> ta;
        String rol = u.getRol();
        switch (rol) {
            case "WM":
                ta = Clases.TipoActividad.listarCondicion("activo", true);
                break;
            case "empleado":
            case "obrero":
            case "profesor":
            case "estudiante":
                ta = Clases.TipoActividad.listarTiposActividad(u);
                break;
            default:
                ta = Clases.TipoActividad.listarCondicion("validador", rol);
                break;
        }

        int tam = ta.size();
        if (tam != 0) {
            request.setAttribute("tipos", ta);
        } else {
            request.setAttribute("tipos", null);
        }

        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad act = (Actividad) form;
        int id = act.getIdTipoActividad();
        TipoActividad ta = new TipoActividad();
        ta.setId(id);
        ta.setTipoActividad();
        act.setNombreTipoActividad(ta.getNombreTipo());
        ArrayList<CampoValor> campos = Clases.CampoValor.listarCampos(id);
        act.setCamposValores(campos);
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String username = u.getUsername();
        act.setCreador(username);

        for (int i = 0; i < act.getCamposValores().size(); i++) {

            String nombreCat = act.getCamposValores().get(i).getCampo().getCatalogo();

            if (!nombreCat.equals("")) {
                ArrayList<ElementoCatalogo> catalogo;
                catalogo = Clases.ElementoCatalogo.listarElementos(nombreCat, 0);

                request.getSession().setAttribute("cat" + i, catalogo);
            }
        }

        ArrayList<CampoValor> camposActuales = Clases.CampoValor.listarCampos(id);
        request.getSession().setAttribute("camposAntes", camposActuales);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad act = (Actividad) form;

        ArrayList<CampoValor> camposAntes;
        camposAntes = (ArrayList<CampoValor>) request.getSession().getAttribute("camposAntes");

        if (act.modificarCampoParticipante(camposAntes)) {
            ArrayList<CampoValor> camposActuales = act.getCamposValores();
            request.getSession().setAttribute("camposAntes", CampoValor.clonar(camposActuales));
            for (int i = 0; i < camposActuales.size(); i++) {

                String nombreCat = camposActuales.get(i).getCampo().getCatalogo();

                if (!nombreCat.equals("")) {
                    ArrayList<ElementoCatalogo> catalogo;
                    catalogo = Clases.ElementoCatalogo.listarElementos(nombreCat, 0);

                    request.getSession().setAttribute("cat" + i, catalogo);
                }
            }
            return mapping.findForward(SUCCESS);
        }

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String usuario = user.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        if (act.agregar(ip, usuario)) {

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
            Clases.Root.deleteSessions(request, "actividadForm");

            act.setMensaje("La Actividad '" + nombre + "' ha sido registrada con éxito.");
            act.setMensajeError(null);
            //act.enviarCorreo(0);
            return mapping.findForward(SUCCESSFULL);
        }

        return mapping.findForward(FAILURE);


    }
}
