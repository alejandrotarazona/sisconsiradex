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
public class Catalogo {
    private String                  nombre;
    private int                     nroCampos;
    private int                     idCatalogo;
    private ArrayList<Elementos>    elementosCatalogo;
    
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

    @Override
    public String toString() {
        return "Catalogo{" + "nombre=" + nombre + ", nroCampos=" + nroCampos + ", idCatalogo=" + idCatalogo + '}';
    }
    
    
    
}
