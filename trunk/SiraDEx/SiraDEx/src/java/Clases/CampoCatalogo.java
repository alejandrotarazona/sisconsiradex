/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    public static CampoCatalogo leer() throws IOException{
        CampoCatalogo resp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Introduzca el nombre del campo:");
        String name = br.readLine();
        System.out.println("");
        System.out.println("Introduzca el tipo del campo: ");
        String type = br.readLine();
        System.out.println("");
        
        resp = new CampoCatalogo(name,type);
        
        return resp;
    }

    public CampoCatalogo(String nombre, String tipo) {
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
        Entity eCampoCatalogo = new Entity(1,9);
        String[] columnas = {
            ATRIBUTOS[1],
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
