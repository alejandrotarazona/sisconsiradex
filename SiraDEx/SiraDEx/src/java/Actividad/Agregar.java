/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import Clases.Actividad;
import Clases.Campo;
import Clases.CampoValor;
import Clases.ElementoCatalogo;
import Clases.TipoActividad;
import Clases.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

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
        Usuario u = (Usuario) request.getSession().getAttribute("user");
        ArrayList<TipoActividad> ta;
        if (u.getRol().equalsIgnoreCase("WM")) {
            ta = Clases.TipoActividad.listar();
        } else if (u.getRol().equalsIgnoreCase("DEx")) {
            ta = Clases.TipoActividad.listarTiposActividad(u.getRol());
        } else {
            ta = Clases.TipoActividad.listarTiposActividad(u);
        }

        int tam = ta.size();
        if (tam != 0) {
            request.setAttribute("tipos", ta);
        } else {
            request.setAttribute("tipos", null);
        }
        
        Actividad a = (Actividad) form;
        a.setMensajeError(null);
        
        return mapping.findForward(PAGE);
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;
        int id = a.getIdTipoActividad();
        TipoActividad ta = new TipoActividad();
        ta.setId(id);
        ta.setTipoActividad();
        a.setNombreTipoActividad(ta.getNombreTipo());
        ArrayList<CampoValor> valores = Clases.CampoValor.listarCampos(id);
        a.setCamposValores(valores);

        Usuario u = (Usuario) request.getSession().getAttribute("user");
        String username = u.getUsername();
        a.setCreador(username);

        for (int i = 0; i < a.getCamposValores().size(); i++) {

            String nombreCat = a.getCamposValores().get(i).getCampo().getCatalogo();

            if (!nombreCat.equals("")) {
                ArrayList<ElementoCatalogo> catalogo = Clases.ElementoCatalogo.listarElementos(nombreCat, 0);
                
                request.getSession().setAttribute("cat" + i, catalogo);
            }
        }

        return mapping.findForward(SUCCESS);

    }

    public ActionForward save2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;
        ArrayList<CampoValor> cv = a.getCamposValores();
        int divisor = 1024 * 1024;

        Iterator it = cv.iterator();
        while (it.hasNext()) {
            CampoValor cv0 = (CampoValor) it.next();
            Campo c = cv0.getCampo();
            if (c.getTipo().equals("archivo") || c.getTipo().equals("producto")) {
                FormFile f0 = cv0.getFile();

                double tamBasico = f0.getFileSize();
                double tamano = (tamBasico / divisor);
                System.out.println("Tamaño "+ f0.getFileName()+" "+ tamano +"MB");
                if (tamano > 2.0) {
                    a.setMensajeError("Error: El tamaño del archivo debe ser menor a 2MB");
                    return mapping.findForward(FAILURE);
                }
                if (!cv0.getValor().endsWith(".pdf")) {
                    a.setMensajeError("Error: El archivo DEBE ser un archivo \".pdf\"");
                    return mapping.findForward(FAILURE);
                }

            }
        }

        if (a.agregarActividad()) {

            a.setMensaje("Su actividad ha sido registrado con éxito.");

            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> act;

            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);


            return mapping.findForward(SUCCESSFULL);
        }

        a.setMensajeError("Error: No se pudo registrar la actividad. Por favor revise que "
                + "los campos se han llenado correctamente.");
        return mapping.findForward(FAILURE);


    }
    
    //Alejandro cuando puedas me explicas para qué hiciste este método
    public ActionForward saveTipo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Actividad a = (Actividad) form;

        if (a.agregarActividad()) {

            a.setMensaje("Su actividad ha sido registrado con éxito.");

            Usuario u = (Usuario) request.getSession().getAttribute("user");
            String rol = u.getRol();
            ArrayList<Actividad> act;

            if (rol.equalsIgnoreCase("WM")) {
                act = Clases.Actividad.listarActividades();
            } else {
                act = a.listarActividadesDeUsuario();
            }
            request.setAttribute("acts", act);
            a.deleteSessions(request);
            return mapping.findForward(SUCCESSFULL);
        }

        a.setMensajeError("Error: No se pudo registrar la actividad. Por favor revise que "
                + "los campos se han llenado correctamente.");
        return mapping.findForward(FAILURE);


    }
}