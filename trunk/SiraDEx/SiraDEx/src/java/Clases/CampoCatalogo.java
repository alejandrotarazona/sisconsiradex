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
public class CampoCatalogo {
    
    private int     idCampo;
    private String  nombre;
    private String  tipo;
    
    private static String[] TABLAS = {
        "CATALOGO",//0
        "CAMPO_CATALOGO", //1
    };
    
    private static String[] ATRIBUTOS ={
        "id_campo", //0
        "id_cat", //1
        "nombre_campo", //2
        "tipo_campo" //3
    };

    public CampoCatalogo() {
    }

    public CampoCatalogo(int idCampo, String nombre, String tipo, boolean obligatorio) {
        this.idCampo = idCampo;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean agregarCampo(int idCatalogo){
        boolean resp=true;
        Entity eCampoCatalogo = new Entity(1,10);
        String[] columnas = {
            ATRIBUTOS[0],
            ATRIBUTOS[2],
            ATRIBUTOS[3]
        };
        Integer idCat = new Integer(idCatalogo);
        Object[] valores = {
            idCat,
            nombre,
            tipo
        };
        resp &= eCampoCatalogo.insertar2(columnas, valores);
        
        return resp;
    }
    
}
