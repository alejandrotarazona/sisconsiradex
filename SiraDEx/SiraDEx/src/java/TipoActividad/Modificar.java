/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoActividad;

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
public class Modificar extends DispatchAction {

    /* forward name="success" path="" */
    private static final String PAGE = "page";
    private static final String SUCCESS = "success";

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
            return mapping.findForward(SUCCESS);
        }
        Clases.Root.deleteSessions(request, "tipoActividadForm");

        TipoActividad ta = (TipoActividad) form;

        int idTA = ta.getIdTipoActividad();
        ta.setTipoActividad();
        ArrayList campos = Clases.Campo.listar(idTA);
        ta.setCampos(campos);

        ArrayList<ElementoCatalogo> programas;
        programas = Clases.ElementoCatalogo.listarElementos("Programas", 1);
        request.getSession().setAttribute("programas", programas);

        ArrayList<ElementoCatalogo> dependencias;
        dependencias = Clases.ElementoCatalogo.listarElementos("Dependencias", 1);
        request.getSession().setAttribute("dependencias", dependencias);

        ArrayList catalogos = Clases.Catalogo.listarCondicion("participa", false);
        request.getSession().setAttribute("catalogos", catalogos);

        ArrayList catalogosPart = Clases.Catalogo.listarCondicion("participa", true);
        request.getSession().setAttribute("catalogosPart", catalogosPart);

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

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        if (u == null) {
            return mapping.findForward(SUCCESS);
        }

        TipoActividad ta = (TipoActividad) form;

        String usuario = u.getUsername();
        String ip = request.getHeader("X-Forwarded-For");

        TipoActividad taNM = (TipoActividad) request.getSession().getAttribute("taNM");

        ta.setMensaje(null);

        int elimino = ta.eliminarCamposMarcados();
        if (elimino > 0 || elimino < 0) {
            return mapping.findForward(PAGE);
        }

        int numeroCampos = ta.getNroCampos();
        if (numeroCampos > 0) {
            ta.agregarCamposNuevos();
            return mapping.findForward(PAGE);
        }

        if (ta.isModificado()) {
            if (!ta.eliminar(ip, usuario) || !ta.agregar(ip, usuario)) {
                return mapping.findForward(PAGE);
            }
            ta.setMensaje("El Tipo de Actividad '" + ta.getNombreTipo()
                    + "' ha sido modificado con éxito.");
            return mapping.findForward(SUCCESS);
        }

        if (ta.modificar(taNM, ip, usuario)) {

            request.getSession().setAttribute("mensajeTipo", ta.getMensaje());
            return mapping.findForward(SUCCESS);
        }

        ta.setNombreTipo(taNM.getNombreTipo());

        return mapping.findForward(PAGE);
    }
}
