/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class CampoCatalogoValor implements Serializable{

    private CampoCatalogo campo;
    private int idCampo;
    private int idElemento;
    private String valor;
    private static String[] TABLAS = {
        "VALOR_CATALOGO", //0
        "CAMPO_CATALOGO"  //1
    };
    private static String[] ATRIBUTOS = {
        "id_campo", //0
        "id_elemento", //1
        "valor" //2
    };

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }
    
    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public CampoCatalogoValor() {
    }

    
    public CampoCatalogoValor(CampoCatalogo campo) {
        this.campo = campo;
    }
    
    public CampoCatalogo getCampo() {
        return campo;
    }

    public void setCampo(CampoCatalogo campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public boolean agregar(int idElemento){
        boolean resp = true;
        Integer elementoId = new Integer(idElemento);
        
        Entity eValor = new Entity(1,11);
        Object[] valores = {
          campo.getIdCampo(),
          elementoId,
          valor
        };
        
        resp &= eValor.insertar2(ATRIBUTOS, valores);
        
        return resp;
    }
    
    public static ArrayList<CampoCatalogoValor> listar(int idCatalogo) {
        ArrayList<CampoCatalogoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(0, 9);
        String[] columnas = {"id_cat"};
        Integer[] condiciones = {idCatalogo};

        ResultSet rs = eCampo.seleccionar(columnas, condiciones);

        if (rs != null) { 
            try {
                while (rs.next()) {
                    CampoCatalogo c = new CampoCatalogo();
                    c.setIdCampo(rs.getInt("id_campo"));
                    c.setIdCatalogo(rs.getInt("id_cat"));
                    c.setNombre(rs.getString("nombre_campo"));
                    c.setTipo(rs.getString("tipo_campo"));
      
                    CampoCatalogoValor cv = new CampoCatalogoValor(c);
                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaValor;
    }
    
    public static ArrayList<CampoCatalogoValor> listarElem(int idElem) {
        ArrayList<CampoCatalogoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(0, 12);
        String[] columnas = {"id_elemento"};
        Integer[] condiciones = {idElem};

        ResultSet rs = eCampo.seleccionar(columnas, condiciones);

        if (rs != null) { 
            try {
                while (rs.next()) {
                    CampoCatalogoValor cv = new CampoCatalogoValor();
                    cv.setIdCampo(rs.getInt(ATRIBUTOS[0]));
                    cv.setIdElemento(rs.getInt(ATRIBUTOS[1]));
                    cv.setValor(rs.getString(ATRIBUTOS[2]));
      
                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaValor;
    }
}
