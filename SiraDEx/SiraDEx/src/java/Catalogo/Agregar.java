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
 * @author alejandro
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
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo t = (Catalogo) form;

        if (t.getNombre().contains(";") || t.getNombre().contains("<")
                || t.getNombre().contains(">") || t.getNombre().contains("'")
                || t.getNombre().contains("&") || t.getNombre().contains("$")) {
            t.setMensaje("El nombre tiene un caracter invalido, por favor "
                    + "intente de nuevo.");
            return mapping.findForward(FAILURE);

        }
        int numeroCampos = t.getNroCampos();
        ArrayList<CampoCatalogo> campos = new ArrayList<CampoCatalogo>();

        for (int i = 0; i < numeroCampos; i++) {
            CampoCatalogo c = new CampoCatalogo();
            c.setIdCatalogo(t.getIdCatalogo());
            campos.add(c);
        }

        t.setCampos(campos);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward page(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /*ArrayList programas = Clases.Elemento.listarElementos("Programas");
        request.setAttribute("programas", programas);*/
        Catalogo t = (Catalogo) form;
        t.setMensaje("");
        return mapping.findForward(PAGE);
    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo t = (Catalogo) form;

        if (t.agregar()) {

            t.setMensaje("El catalogo '" + t.getNombre() + "' ha sido "
                    + "registrado con éxito.");
            return mapping.findForward(SUCCESSFULL);
        }
        return mapping.findForward(FAILURE);


    }
}
