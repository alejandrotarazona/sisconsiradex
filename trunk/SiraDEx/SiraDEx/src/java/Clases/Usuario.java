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

/**
 *
 * @author SisCon
 */
public class Usuario extends Root {

    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private int tipo;
    private String telefono;
    private String email;
    private String rol;
    private static final String[] ATRIBUTOS = {
        "nombres", //0
        "apellidos", //1
        "usbid", //2
        "password", //3
        "tipo", //4
        "telefono", //5
        "email", //6
        "rol" //7
    };

    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, String username,
            String password, String mensaje) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.mensaje = mensaje;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + " username= " + username + ", password= " + password + " }";
    }

    public void setUsuario() {
        try {
            Entity eUsuario = new Entity(0, 0);//SELECT USUARIO

            String[] tabABuscar = {
                "USUARIO"
            };
            String[] atrib = {"usbid"};
            String[] valor = {username};

            try (ResultSet rs = eUsuario.naturalJoin(ATRIBUTOS, tabABuscar, atrib, valor)) {
                if (rs != null) {
                    rs.next();
                    nombres = rs.getString(ATRIBUTOS[0]);
                    apellidos = rs.getString(ATRIBUTOS[2]);
                    password = rs.getString(ATRIBUTOS[3]);
                    tipo = rs.getInt(ATRIBUTOS[4]);
                    telefono = rs.getString(ATRIBUTOS[5]);
                    email = rs.getString(ATRIBUTOS[6]);
                    rol = rs.getString(ATRIBUTOS[7]);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean esUsuario() {
        try {
            Entity e = new Entity(0, 0);

            String[] col = {ATRIBUTOS[2]};
            String[] cond = {username};

            ResultSet rs = e.seleccionar(col, cond);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[2]).equals(username)
                            && rs.getString(ATRIBUTOS[3]).equals(password)) {
                        rs.close();
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
                        rs.close();
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

    public static ArrayList<Usuario> listarUsuario() {
        Entity eUsuario = new Entity(0, 0);
        ArrayList<Usuario> listaUsuarios = new ArrayList<>(0);

        ResultSet rs = eUsuario.listar();

        if (rs != null) {
            try {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setNombres(rs.getString(ATRIBUTOS[0]));
                    u.setApellidos(rs.getString(ATRIBUTOS[1]));
                    u.setUsername(rs.getString(ATRIBUTOS[2]));
                    u.setPassword(rs.getString(ATRIBUTOS[3]));
                    u.setTipo(rs.getInt(ATRIBUTOS[4]));
                    u.setTelefono(rs.getString(ATRIBUTOS[5]));
                    u.setEmail(rs.getString(ATRIBUTOS[6]));
                    u.setRol(rs.getString(ATRIBUTOS[7]));
                    listaUsuarios.add(u);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuarios;
    }

    public boolean agregarUsuario() {
        Entity e = new Entity(1, 0);
        String[] usuarios = {username, password, rol};
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
    
    //en el parámetro user recibe un Usuario no modificado
    public boolean modificar(Usuario userNM) {
        
      
        boolean resp;

        Entity e = new Entity(2, 0);//UPDATE USUARIO

        String[] condColumnas = {
            ATRIBUTOS[0],
            ATRIBUTOS[1],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6],
            ATRIBUTOS[7]
        };
        Object[] valores = {
            userNM.getNombres(),
            userNM.getApellidos(),
            userNM.getPassword(),
            userNM.getTipo(),
            userNM.getTelefono(),
            userNM.getEmail(),
            userNM.getRol()
        };
        String[] colModificar = {
            ATRIBUTOS[0],
            ATRIBUTOS[1],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6],
            ATRIBUTOS[7]
        };
        Object[] modificaciones = {
            nombres,
            apellidos,
            password,
            tipo,
            telefono,
            email,
            rol
        };

        if (this.esUsuario() && 
                !modificaciones[0].equals(userNM.getNombres()) &&
                !modificaciones[1].equals(userNM.getApellidos())) {
            mensaje = "Error: Ya existe un Usuario llamado "
                    + "" + modificaciones[0] + "" + modificaciones[1] + ". "
                    + "Por favor intente con otro nombre.";
            return false;
        }

        resp = e.modificar(condColumnas, valores, colModificar, modificaciones);

        if (!resp) {
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
        }
        mensaje = "El perfil ha sido modificado con éxito.";
        
        return resp;
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
