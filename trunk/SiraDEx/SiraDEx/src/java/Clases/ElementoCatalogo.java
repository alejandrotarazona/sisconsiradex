/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author alejandro
 */
public class ElementoCatalogo {
    private int                             idCatalogo;
    private String                          nombreCatalogo;
    private ArrayList<CampoCatalogoValor>   valor;


    public ElementoCatalogo() {
    }

    public ElementoCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public ArrayList<CampoCatalogoValor> getValor() {
        return valor;
    }

    public void setValor(ArrayList<CampoCatalogoValor> valor) {
        this.valor = valor;
    }
  
    
}
