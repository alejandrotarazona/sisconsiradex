/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author SisCon
 */
public class ElementoCatalogo extends ActionForm {
    private int                             idElemento;
    private int                             idCatalogo;
    private String                          catalogo;   
    private ArrayList<CampoCatalogoValor>   camposValores;
    private String                          mensaje;

private static String[] ATRIBUTOS = {
        "id_elemento", //1
        "id_catalogo" //0
    };
    public ElementoCatalogo() {
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }
    
    public void setIdElemento(){
        Entity eElemento = new Entity(0,10);
        this.idElemento = eElemento.seleccionarMaxId("id_elemento");
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public ArrayList<CampoCatalogoValor> getCamposValores() {
        return camposValores;
    }

    public void setCamposValores(ArrayList<CampoCatalogoValor> camposValores) {
        this.camposValores = camposValores;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }
    
  
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public boolean agregar(){
        Entity eElemento = new Entity(1,10);
        boolean resp = true;
        
        String[] columnas = {
            "id_catalogo"
        };
        Integer idCat = new Integer(this.idCatalogo);
        Object[] valores = {
            idCat
        };
        resp &= eElemento.insertar2(columnas, valores);
        if(resp) {setIdElemento();}
        else {return resp;}
        
        Iterator itValores = this.camposValores.iterator();
        
        while(itValores.hasNext() && resp){
            CampoCatalogoValor ccv = (CampoCatalogoValor) itValores.next();
            resp &= ccv.agregar(this.idElemento);                
        }
        
        return resp;        
    }
    
    public boolean eliminar() {
        Entity e = new Entity(5, 10);
        return e.borrar("idElemento", idElemento);
    }
    
    public static ArrayList<ElementoCatalogo> listarElementos() {
        ArrayList<ElementoCatalogo> listaElementoCatalogo = new ArrayList<>(0);
        Entity eElementoCatalogo = new Entity(0, 10);

        ResultSet rs = eElementoCatalogo.listar();

        Entity eCatalogo = new Entity(0, 8);

        if (rs != null) {
            try {
                while (rs.next()) {
                    ElementoCatalogo ec = new ElementoCatalogo();

                    ec.setIdElemento(rs.getInt(ElementoCatalogo.ATRIBUTOS[0]));

                    int id = rs.getInt(ElementoCatalogo.ATRIBUTOS[1]);
                    ec.setIdCatalogo(id);

                    String[] nombreCat = {"nombre"};
                    String[] idCat = {"id_cat"};
                    Integer[] idElem = {id};
                    ResultSet r = eCatalogo.proyectar(nombreCat, idCat, idElem);
                    r.next();
                    ec.setCatalogo(r.getString(1));

                    listaElementoCatalogo.add(ec);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElementoCatalogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaElementoCatalogo;
    }

}
