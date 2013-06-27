
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
    private static final String PAGE = "page";
    private static final String SUCCESS1 = "success1";
    private static final String SUCCESS2 = "success2";

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
        Clases.Root.deleteSessions(request, "actividadForm");
        Actividad act = (Actividad) form;
        act.setActividad();

        /*ArrayList con los valores no modificados*/
        ArrayList<CampoValor> camposNM = CampoValor.listarCamposValores(act.getIdActividad());
        act.auxModificarParticipante(camposNM);
        request.getSession().setAttribute("camposNM", camposNM);

        for (int i = 0; i < camposNM.size(); i++) {
            if (camposNM.get(i).getCampo().getTipo().equals("participante")) {
                System.out.println("CAMPOSNM PARTICIPANTE page()" + camposNM.get(i).getValor() + " ######################");
            }
        }

        /*Se pasan los catalogos de los camposNM tipo catalogo al jsp de ser necesario*/
        for (int i = 0; i < act.getCamposValores().size(); i++) {
            String nombreCat = act.getCamposValores().get(i).getCampo().getCatalogo();
            if (!nombreCat.equals("")) {
                ArrayList<ElementoCatalogo> catalogo = ElementoCatalogo.listarElementos(nombreCat, 0);
                request.getSession().setAttribute("cat" + i, catalogo);
            }
        }

        ArrayList<CampoValor> campos = CampoValor.listarCamposValores(act.getIdActividad());
        request.getSession().setAttribute("campos", campos);

        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(PAGE);
        }
        Actividad act = (Actividad) form;

        ArrayList<CampoValor> campos;
        campos = (ArrayList<CampoValor>) request.getSession().getAttribute("campos");

        ArrayList<CampoValor> camposNM = (ArrayList<CampoValor>) request.getSession().getAttribute("camposNM");
        Usuario modificador = (Usuario) request.getSession().getAttribute("user");
        act.setModificador(modificador.getUsername());
        act.auxModificarArchivo(camposNM);

        for (int i = 0; i < camposNM.size(); i++) {
            if (camposNM.get(i).getCampo().getTipo().equals("participante")) {
                System.out.println("CAMPOSNM PARTICIPANTE EN update()" + camposNM.get(i).getValor() + " ######################");
            }
        }

        if (act.modificarCampoParticipante(campos, camposNM)) {
            ArrayList<CampoValor> camposActuales = act.getCamposValores();
            for (int i = 0; i < camposNM.size(); i++) {
                System.out.println("CAMPOSNM " + camposNM.get(i).getValor() + " auxModificarParticipante  AAAAAAAAAAAAAAAAAAAAA");

            }
            act.auxModificarArchivo(camposNM);
            request.getSession().setAttribute("campos", CampoValor.clonar(camposActuales));
            request.getSession().setAttribute("camposNM", camposNM);
            for (int i = 0; i < camposNM.size(); i++) {
                System.out.println("CAMPOSNM " + camposNM.get(i).getValor() + " auxModificarParticipante DDDDDDDDDDDDDDDDDDDDDDD");

            }

            for (int i = 0; i < camposActuales.size(); i++) {

                String nombreCat = camposActuales.get(i).getCampo().getCatalogo();

                if (!nombreCat.equals("")) {
                    ArrayList<ElementoCatalogo> catalogo;
                    catalogo = ElementoCatalogo.listarElementos(nombreCat, 0);

                    request.getSession().setAttribute("cat" + i, catalogo);
                }
            }
            act.setMensaje(null);
            return mapping.findForward(PAGE);
        }

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        if (act.modificar(camposNM, ip, usuario)) {

            String rol = u.getRol();
            act.enviarCorreo(1);
            request.getSession().setAttribute("mensajeAct", act.getMensaje());

            if (rol.equalsIgnoreCase("WM")) {
                return mapping.findForward(SUCCESS2);
            }
            return mapping.findForward(SUCCESS1);
        }

        return mapping.findForward(PAGE);
    }
}
