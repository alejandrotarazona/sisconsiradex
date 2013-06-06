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
    private String email = "";
    private String rol;
    private String rolDex;
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

    public String getRolDex() {
        return rolDex;
    }

    public void setRolDex(String rolDex) {
        this.rolDex = rolDex;
    }

    @Override
    public String toString() {
        return "Usuario{" + " username= " + username + ", password= " + password + ", rol= " + rol + " }";
    }

    public Usuario clone() {
        Usuario res = new Usuario();
        res.setUsername(username);
        res.setUsuario();
        return res;
    }

    public void setUsuario() {

        Entity eUsuario = new Entity(0);//USUARIO

        String[] atrib = {"usbid"};
        String[] valor = {username};

        ResultSet rs = eUsuario.seleccionar(atrib, valor);
        try {
            if (rs != null) {
                rs.next();
                nombres = rs.getString(ATRIBUTOS[0]);
                apellidos = rs.getString(ATRIBUTOS[1]);
                password = rs.getString(ATRIBUTOS[3]);
                tipo = rs.getInt(ATRIBUTOS[4]);
                telefono = rs.getString(ATRIBUTOS[5]);
                email = rs.getString(ATRIBUTOS[6]);
                rol = rs.getString(ATRIBUTOS[7]);
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean setUsuario(String rol) {

        Entity eUsuario = new Entity(0);//USUARIO

        String[] atrib = {"rol"};
        String[] valor = {rol};

        try {
            ResultSet rs = eUsuario.seleccionar(atrib, valor);
            if (rs != null) {
                rs.next();
                nombres = rs.getString(ATRIBUTOS[0]);
                apellidos = rs.getString(ATRIBUTOS[1]);
                username = rs.getString(ATRIBUTOS[2]);
                password = rs.getString(ATRIBUTOS[3]);
                tipo = rs.getInt(ATRIBUTOS[4]);
                telefono = rs.getString(ATRIBUTOS[5]);
                email = rs.getString(ATRIBUTOS[6]);
                this.rol = rs.getString(ATRIBUTOS[7]);
                rs.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setUsuarioDEx() {

        Entity eUsuario = new Entity(0);//USUARIO

        String[] valor = {"empleado", "estudiante", "profesor", "obrero", "WM"};

        ResultSet rs = eUsuario.seleccionarDistintos("rol", valor);
        try {
            if (rs != null) {
                rs.next();
                nombres = rs.getString(ATRIBUTOS[0]);
                apellidos = rs.getString(ATRIBUTOS[1]);
                username = rs.getString(ATRIBUTOS[2]);
                password = rs.getString(ATRIBUTOS[3]);
                tipo = rs.getInt(ATRIBUTOS[4]);
                telefono = rs.getString(ATRIBUTOS[5]);
                email = rs.getString(ATRIBUTOS[6]);
                this.rol = rs.getString(ATRIBUTOS[7]);
                rs.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean esUsuario() {

        Entity e = new Entity(0);//USUARIO

        String[] col = {ATRIBUTOS[2]};
        String[] cond = {username};

        ResultSet rs = e.seleccionar(col, cond);
        try {
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
            Entity e = new Entity(0);//USUARIO
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
        Entity eUsuario = new Entity(0);//USUARIO
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

    public boolean agregarUsuario(String ip, String user) {
        Entity e = new Entity(0);//USUARIO
        Object[] usuarios = {username, password, rol, tipo, nombres, apellidos, telefono, email};
        if (esUsuario()) {
            mensajeError = "Ya existe un usuario registrado con el US-BID " + username;
            return false;
        } else {
            if (e.insertar(usuarios)) {
                e.setIp(ip);
                e.setUser(user);
                e.log();
                mensaje = "El usuario " + username + " ha sido registrado con éxito.";
                return true;
            }
            mensajeError = "Error: No se pudo regristrar el usuario.";
            return false;
        }
    }

    public boolean eliminarUsuario(String ip, String user) {
        Entity e = new Entity(0);//USUARIO


        if (e.borrar(ATRIBUTOS[2], this.username)) {
            e.setIp(ip);
            e.setUser(user);
            e.log();
            return true;
        }

        return false;

    }

    //en el parámetro userNM recibe un Usuario no modificado
    public boolean modificar(Usuario userNM, String ip, String user) {


        boolean resp;

        Entity e = new Entity(0);//USUARIO

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

        resp = e.modificar(condColumnas, valores, colModificar, modificaciones);
        e.setIp(ip);
        e.setUser(user);
        e.log();

        if (!resp) {

            mensaje = "Error del sistema al intentar actualizar la base de datos.";
            return false;
        }

        return resp;
    }

    public String[] cantidadActividadesPorTipo() {

        String[] estadistica = new String[2];
        String nombre = "";
        String cantidad = "";
        Entity eSelec = new Entity(22);//ACT_PARTICIPA
        ResultSet rs = eSelec.seleccionarNumActividadesUsuario(username);

        try {
            if (rs != null) {
                while (rs.next()) {
                    nombre += rs.getString("nombre_tipo_actividad") + "|";
                    cantidad += rs.getString("cantidad") + ",";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (nombre.length() != 0 && cantidad.length() != 0) {
            nombre = nombre.substring(0, nombre.length() - 1) + "&";
            cantidad = cantidad.substring(0, cantidad.length() - 1) + "&";
        }

        estadistica[0] = nombre;
        estadistica[1] = cantidad;
        return estadistica;
    }

    public static void main(String[] args) {
        /*
         * probando probando 1 2 3 probando alo alo...
         */
        Usuario u = new Usuario("j", "p");
        if (u.eliminarUsuario("prueba", "de .java")) {
            System.out.println("usuario eliminado");
        } else {
            System.out.println("usuario no eliminado");
        }
    }
}
