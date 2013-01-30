/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author SisCon
 */
public class Usuario extends ActionForm {

    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String tipo;
    private String telefono;
    private String email;
    private String mensaje;
    private static final String[] ATRIBUTOS = {
        "nombre", //0
        "apellido", //1
        "usbid", //2
        "password", //3
    };
 

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String username,
            String password, String mensaje) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.mensaje = mensaje;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Usuario{" + " username= " + username + ", password= " + password + " }";
    }


    public boolean esUsuario() {
        try {
            Entity e = new Entity(0, 0);

            String[] col = {ATRIBUTOS[2]};
            String[] cond = {username};

            ResultSet rs = e.seleccionar(col, cond);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[2]).equals(username)) {
                        return true;
                    }
                }
            } 
        } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    return false;    
    }
       

    public boolean usuarioLogin() {
        try {
            Entity e = new Entity(0, 0);
            String[] col = {ATRIBUTOS[2], ATRIBUTOS[3]};
            String[] cond = {username, password};
            ResultSet rs = e.seleccionar(col, cond);
            if (rs != null) {
                
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[2]).equals(username)
                            && rs.getString(ATRIBUTOS[3]).equals(password)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public static ArrayList<Usuario> listarUsuario(){
        Entity eUsuario = new Entity(0,0);
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>(0);
        
        ResultSet rs = eUsuario.listar();

        if (rs != null) {
            try {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    //u.setNombre(rs.getString("nombre_campo"));
                    //u.setApellido(rs.getString("apellido"));
                    u.setUsername(rs.getString("usbid"));
                    u.setPassword(rs.getString("password"));
                    listaUsuarios.add(u);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return listaUsuarios;
    }

    public boolean agregarUsuario() {
        Entity e = new Entity(1, 0);
        String[] usuarios = {username, password, tipo};
        if (esUsuario()) {
            return false;
        } else {
            boolean resp = e.insertar(usuarios);
            return resp;
        }
    }

    public boolean eliminarUsuario() {
        Entity e = new Entity(5, 0);

        if (esUsuario()) {
            System.out.println(this.username + "ES USUARIO");
            boolean resp = e.borrar(ATRIBUTOS[2], this.username);
            return resp;
        } else {
            System.out.println(this.username + "NO ES USUARIO");
            return false;
        }

    }

    public static void main(String[] args) {
        /*
         * probando probando 1 2 3 probando alo alo...
         */
        Usuario u = new Usuario("j", "p");
        if (u.eliminarUsuario()) {
            System.out.println("usuario eliminado");
        } else {
            System.out.println("usuario no eliminado");
        }
    }
}
