/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.Serializable;

/**
 *
 * @author SisCon
 */
public class Campo implements Serializable {
    private int idCampo;
    private int idTipoActividad;
    private String nombre;
    private String tipo;
    private int longitud;
    private boolean obligatorio;
   
    private static final String[] TIPOS = {
        "texto",   //STRING
        "numero",  //INT
        "fecha",   //DATE
        "hora",    //TIME
        "checkbox",//CHECKBOX
    };

     private static final String[] ATRIBUTOS = {
        "id_campo",
        "id_tipo_actividad",
        "nombre_campo",
        "tipo_campo",
        "longitud",
        "obligatorio"
    };

    public Campo(String nombre, String tipo, boolean obligatorio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.obligatorio = obligatorio;
    }

    public Campo(String nombre, String tipo, int longitud, boolean obligatorio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.longitud = longitud;
        this.obligatorio = obligatorio;
    }
    
    public Campo() {
    }

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    
    public boolean isObligatorio() {
        return obligatorio;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static String[] getTIPOS() {
        return TIPOS;
    }
    
    

       @Override
    public String toString() {
        return "Campos{" + "nombre=" + nombre + ", tipo=" + tipo + ", longitud=" + longitud + ", obligatorio=" + obligatorio + '}';
    }

    public boolean agregarCampo(int idTipoActividad) {
        System.out.println("Agrego un Campo");
        Entity e = new Entity(1, 3);
        Integer idTA = new Integer(idTipoActividad);
        Object[] valores = {idTA, nombre, tipo, longitud, obligatorio};
        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5]
        };
        boolean resp = e.insertar2(columnas, valores);
        return resp;
        
    }
    
    public boolean modificarCampo(){
    return true;
    }
}
