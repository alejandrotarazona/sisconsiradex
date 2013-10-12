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
    private static final String SUCCESSFULL1 = "successfull1";
    private static final String SUCCESSFULL2 = "successfull2";
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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Clases.Root.deleteSessions(request, "");
        ArrayList<TipoActividad> ta;
        String rol = u.getRol();
        switch (rol) {
            case "WM":
                String[] atrib = {"activo"};
                Object[] val = {true};
                ta = Clases.TipoActividad.listarCondicion(atrib, val);
                break;
            case "empleado":
            case "obrero":
            case "profesor":
            case "estudiante":
                ta = Clases.TipoActividad.listarTiposActividad(u);
                break;
            default:
                String[] atributo = {"validador"};
                Object[] valor = {rol};
                ta = Clases.TipoActividad.listarCondicion(atributo, valor);
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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Actividad act = (Actividad) form;
        int id = act.getIdTipoActividad();
        TipoActividad ta = new TipoActividad();
        ta.setId(id);
        ta.setTipoActividad();
        act.setNombreTipoActividad(ta.getNombreTipo());
        ArrayList<CampoValor> campos = Clases.CampoValor.listarCampos(id);
        act.setCamposValores(campos);

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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Actividad act = (Actividad) form;

        ArrayList<CampoValor> camposAntes;
        camposAntes = (ArrayList<CampoValor>) request.getSession().getAttribute("camposAntes");

        if (act.modificarCampoParticipante(camposAntes, null)) {
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
            act.setMensaje(null);
            return mapping.findForward(SUCCESS);
        }

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        if (act.agregar(ip, usuario, u.getRol())) {

            String rol = u.getRol();

            request.getSession().setAttribute("mensajeAct", act.getMensaje());

            act.enviarCorreo(0);
            if (rol.equalsIgnoreCase("WM")) {
                return mapping.findForward(SUCCESSFULL2);
            }
            return mapping.findForward(SUCCESSFULL1);
        }

        return mapping.findForward(SUCCESS);
    }
}
