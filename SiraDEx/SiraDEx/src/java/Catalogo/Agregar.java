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
        Catalogo cat = (Catalogo) form;
        cat.setMensajeError(null);
        cat.setMensaje(null);
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;

        if (cat.getNombre().contains(";") || cat.getNombre().contains("<")
                || cat.getNombre().contains(">") || cat.getNombre().contains("'")
                || cat.getNombre().contains("&") || cat.getNombre().contains("$")) {
            cat.setMensajeError("Error: El nombre tiene un caracter invalido, por favor "
                    + "intente de nuevo.");
            return mapping.findForward(FAILURE);

        }
        int numeroCampos = cat.getNroCampos();
        ArrayList<CampoCatalogo> campos = new ArrayList<>();

        for (int i = 0; i < numeroCampos; i++) {
            CampoCatalogo c = new CampoCatalogo();
            campos.add(c);
        }

        cat.setCampos(campos);

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;
        ArrayList<CampoCatalogo> c = cat.getCampos();
        String nombreCat = cat.getNombre();
        
        for (int i = 1; i <= cat.getNroCampos(); i++) {

                CampoCatalogo aux = c.get(i-1);
                if (aux.isNombreInvalido()) {
                    cat.setMensajeError("El campo número " + i
                            + " contiene un nombre inválido");
                    return mapping.findForward(FAILURE2);
                }
        }
        
        if (cat.agregar()) {
            
            
            ArrayList cats = Clases.Catalogo.listar();
            request.setAttribute("catalogos", cats);
            
            Clases.Root.deleteSessions(request,"catalogoForm");
            cat.setMensaje("El catálogo '" + nombreCat + "' ha sido "
                    + "registrado con éxito.");
            return mapping.findForward(SUCCESSFULL);
        }
        cat.setMensajeError("Error: El catálogo '" + nombreCat + "' no ha sido"
                + " registrado.");
        ArrayList cats = Clases.Catalogo.listar();
        request.setAttribute("catalogos", cats);
        return mapping.findForward(FAILURE);


    }
}
