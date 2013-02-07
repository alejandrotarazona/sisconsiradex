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
        ArrayList ca = Clases.Catalogo.listar();
        request.setAttribute("catalogos", ca);//verificar si la lista es vacia
        ElementoCatalogo ec = (ElementoCatalogo) form;
        ec.setMensaje("");
        return mapping.findForward(PAGE);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ElementoCatalogo ec = (ElementoCatalogo) form;
        
        ArrayList<CampoCatalogoValor> valores = Clases.CampoCatalogoValor.listar(ec.getIdCatalogo());
        ec.setCamposValores(valores);
        return mapping.findForward(SUCCESS);

    }
    
    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ElementoCatalogo ec = (ElementoCatalogo) form;


        if (ec.agregar()) {

            ec.setMensaje("Su elemento ha sido registrado con éxito.");
            return mapping.findForward(SUCCESSFULL);
        }

        ec.setMensaje("No se pudo registrar el elemento. Por favor revise que "
                + "los campos se han llenado correctamente.");
        return mapping.findForward(FAILURE);


    }
}
