/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;

/**
 *
 * @author alejandro
 */
public class CampoCatalogoValor {

    private int campo;
    private int elemento;
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

    public CampoCatalogoValor() {
    }

    public int getCampo() {
        return campo;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public boolean agregar(){
        boolean resp = true;
        
        Entity eValor = new Entity(1,9);
        Object[] valores = {
          campo,
          elemento,
          valor
        };
        
        resp = eValor.insertar2(ATRIBUTOS, valores);
        
        return resp;
    }
    
}
