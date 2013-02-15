/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogo;

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
        cat.setCampos(Clases.CampoCatalogo.listar(idCat));
        String nombreCat = Clases.Catalogo.getNombre(idCat);
        cat.setNombre(nombreCat);
        
        int nroCampos = cat.getCampos().size();
        String[] nombres = new String[nroCampos+1];
        nombres[0] = nombreCat;
            
        for (int i = 1; i < nombres.length; i++) {
            nombres[i] = cat.getCampos().get(i-1).getNombre();
        }
        
        request.getSession().setAttribute("nombres", nombres);
        return mapping.findForward(PAGE);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;
        
        String[] nombres = (String[])request.getSession().getAttribute("nombres");
                
        if (cat.modificar(nombres)) {

            cat.setMensaje("El catálogo ha sido modificado con éxito.");
            ArrayList cats = Clases.Catalogo.listar();
            request.setAttribute("catalogos", cats);
            return mapping.findForward(SUCCESS);
        }
        ArrayList cats = Clases.Catalogo.listar();
        request.setAttribute("catalogos", cats);
        return mapping.findForward(FAILURE);


    }
}
