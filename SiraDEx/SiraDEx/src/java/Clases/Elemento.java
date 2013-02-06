/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SisCon
 */
public class Elemento {

    private String id;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private String atributo4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(String atributo1) {
        this.atributo1 = atributo1;
    }

    public String getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(String atributo2) {
        this.atributo2 = atributo2;
    }

    public String getAtributo3() {
        return atributo3;
    }

    public void setAtributo3(String atributo3) {
        this.atributo3 = atributo3;
    }

    public String getAtributo4() {
        return atributo4;
    }

    public void setAtributo4(String atributo4) {
        this.atributo4 = atributo4;
    }
    
    /*public static ArrayList<Elemento> listarElementos(String nombreCatalogo) {
 
        Entity eListar = new Entity(0, 8);
        ResultSet rs = eListar.listar();
        ArrayList<Elemento> lista = new ArrayList<>(0);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Elemento e = new Elemento();
                    e.setId(rs.getInt(ATRIBUTOS[0]));
              
                    lista.add(e);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;*/

}
