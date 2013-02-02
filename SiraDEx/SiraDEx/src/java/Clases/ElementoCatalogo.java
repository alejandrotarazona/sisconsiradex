/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author SisCon
 */
public class ElementoCatalogo {
    private int                             idCatalogo;
    private int                             idElemento;
    private ArrayList<CampoCatalogoValor>   valor;


    public ElementoCatalogo() {
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }
    
    public void setIdElemento(){
        Entity eElemento = new Entity(0,10);
        this.idElemento = eElemento.seleccionarMaxId("id_elemento");
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
        
        Iterator itValores = this.valor.iterator();
        
        while(itValores.hasNext() && resp){
            CampoCatalogoValor ccv = (CampoCatalogoValor) itValores.next();
            resp &= ccv.agregar();                
        }
        
        return resp;        
    }
    
    public boolean eliminar() {
        Entity e = new Entity(5, 10);
        return e.borrar("idElemento", idElemento);
    }
}
