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
        ArrayList campos = Clases.CampoCatalogo.listar(idCat);
        cat.setCampos(campos);
        String nombreCat = Clases.Catalogo.getNombre(idCat);
        cat.setNombre(nombreCat);

        /*es necesario otro ArrayList con los valores no modificados para 
         * guardarlo con setAttribute ya que el anterior se modifica en el form 
         * del jsp debido a que ArrayList es un apuntador*/
        ArrayList camposNM = Clases.CampoCatalogo.listar(idCat);
        request.getSession().setAttribute("camposNM", camposNM);

        request.getSession().setAttribute("nombreNM", nombreCat);
        return mapping.findForward(PAGE);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;

        int numeroCampos = cat.getNroCampos();
        ArrayList<CampoCatalogo> nuevosCampos = new ArrayList<>();

        for (int i = 0; i < numeroCampos; i++) {
            CampoCatalogo c = new CampoCatalogo();
            nuevosCampos.add(c);
        }

        cat.setCamposAux(nuevosCampos);

        return mapping.findForward(PAGE);

    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Catalogo cat = (Catalogo) form;
        
        ArrayList<CampoCatalogo> nuevosCampos = cat.getCamposAux();
        
        
        String nombre = (String) request.getSession().getAttribute("nombreNM");
        ArrayList campos = (ArrayList) request.getSession().getAttribute("camposNM");

        if (cat.modificar(nombre, campos, nuevosCampos)) {
        
            ArrayList cats = Clases.Catalogo.listar();
            request.setAttribute("catalogos", cats);
            Clases.Root.deleteSessions(request,"catalogoForm");
            cat.setMensaje("El Catálogo '"+cat.getNombre()+"' ha sido modificado"
                    + " con éxito.");
            return mapping.findForward(SUCCESS);
        }
        
        cat.setNombre(nombre);
        
        return mapping.findForward(FAILURE);


    }
}
