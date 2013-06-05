/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

import Clases.CampoCatalogo;
import Clases.Catalogo;
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
        Catalogo cat = (Catalogo) form;
        cat.setMensaje(null);

        int idCat = cat.getIdCatalogo();
        cat.setCatalogo();
        ArrayList campos = Clases.CampoCatalogo.listar(idCat);
        cat.setCampos(campos);

        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.CampoCatalogo.listar(idCat);
        Catalogo catNM = new Catalogo();
        catNM.setIdCatalogo(idCat);
        catNM.setCatalogo();
        catNM.setCampos(camposNM);
        request.getSession().setAttribute("catNM", catNM);
        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;
        
         Catalogo catNM = (Catalogo) request.getSession().getAttribute("catNM");

        int elimino = cat.eliminarCamposMarcados(catNM);
        if (elimino > 0) {
            cat.setMensajeError("");
            return mapping.findForward(PAGE);
        } else if (elimino < 0) {
            return mapping.findForward(FAILURE);
        }

        int numeroCampos = cat.getNroCampos();
        if (numeroCampos > 0) {
            cat.agregarCamposNuevos();
            cat.setMensajeError("");
            return mapping.findForward(PAGE);
        }

        if (cat.modificar(catNM)) {

            ArrayList cats = Clases.Catalogo.listarCatalogos();
            request.setAttribute("catalogos", cats);
            Clases.Root.deleteSessions(request, "");
            request.setAttribute("mensaje", "El Catálogo '" + cat.getNombre()
                    + "' ha sido modificado con éxito.");
            return mapping.findForward(SUCCESS);
        }

        cat.setNombre(catNM.getNombre());

        return mapping.findForward(FAILURE);


    }
}
