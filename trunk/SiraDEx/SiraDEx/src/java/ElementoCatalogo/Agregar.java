/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementoCatalogo;

import Clases.CampoCatalogoValor;
import Clases.ElementoCatalogo;
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
        ElementoCatalogo e = (ElementoCatalogo) form;
        e.setMensajeError(null);
        e.setMensaje(null);
        int idCat = e.getIdCatalogo();
        e.setNombreCatalogo(Clases.Catalogo.getNombre(idCat));

        ArrayList<CampoCatalogoValor> valores = Clases.CampoCatalogoValor.listar(idCat);
        e.setCamposValores(valores);

        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ElementoCatalogo e = (ElementoCatalogo) form;
        int idCat = e.getIdCatalogo();
        if (e.agregar()) {
            e.setMensaje("El elemento ha sido registrado con éxito");
            ArrayList<ElementoCatalogo> elemsc = Clases.ElementoCatalogo.listarElementosId(idCat);

            if (elemsc.isEmpty()) {
                elemsc = null;
            }

            request.setAttribute("elementos", elemsc);

            request.setAttribute("campos", elemsc.get(0).getCamposValores());
            
            e.setMensajeError(null);

            return mapping.findForward(SUCCESS);
        }


        ArrayList<ElementoCatalogo> elemsc = Clases.ElementoCatalogo.listarElementosId(idCat);

         if (elemsc.isEmpty()) {
            elemsc = null;
        } else {
            request.setAttribute("campos", elemsc.get(0).getCamposValores());
        }
        
        request.setAttribute("elementos", elemsc);

      
        return mapping.findForward(FAILURE);
    }
}
