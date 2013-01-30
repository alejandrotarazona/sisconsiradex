/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
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
public class Catalogo extends ActionForm {
    private int idCatalogo;
    private String nombre;
    private int nroCampos;
    private ArrayList<Campo> campos;
    private static final String[] ATRIBUTOS = {
        "id_cat",
        "nombre",
        "nro_campos"
    };

    private static final String[] tiposCampos = {
        "texto",   //STRING
        "numero",  //INT
        "fecha",   //DATE

    };
    
    /*private ArrayList<Elementos>    elementosCatalogo;
    
    private class Elementos{
        ArrayList<String> valores;

        public Elementos() {
        }

        public Elementos(ArrayList<String> valores) {
            this.valores = valores;
        }

        public ArrayList<String> getValores() {
            return valores;
        }

        public void setValores(ArrayList<String> valores) {
            this.valores = valores;
        }
    }*/
    
    public static String[] getTiposCampos() {
        return tiposCampos;
    }
    
    public Catalogo() {
    }

    public Catalogo(String nombre, int nroCampos, int idCatalogo) {
        this.nombre = nombre;
        this.nroCampos = nroCampos;
        this.idCatalogo = idCatalogo;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCampos() {
        return nroCampos;
    }

    public void setNroCampos(int nroCampos) {
        this.nroCampos = nroCampos;
    }
    
    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "nombre=" + nombre + ", nroCampos=" + nroCampos + ", idCatalogo=" + idCatalogo + '}';
    }
    
    /*Lo siguiente lo saqué de TipoActividad.java falta adaptarlo a Catalogo,
     * y agregarle a Entity las tablas relacionadas con Catalogo.
     
    public void setId() {
        Entity eId = new Entity(0, 1);
        String[] seleccionar = {ATRIBUTOS[0]};
        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3]
        };
        Integer nroDeCampos = new Integer(this.nroCampos);
        Object[] valores = {
            idCat
            nombre,
            nroCampos,
            
        };
        ResultSet rs = eId.proyectar(seleccionar, columnas, valores);
        if(rs != null){
            try {
                if(rs.next()){
                        this.id = rs.getInt(ATRIBUTOS[0]);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }*/
    
    
}
