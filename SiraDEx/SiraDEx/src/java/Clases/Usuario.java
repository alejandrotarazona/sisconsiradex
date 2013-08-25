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
    private ArrayList<BusquedaActividad.Par> datosGrafica;
    private int totalActividades;
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

    public ArrayList<BusquedaActividad.Par> getDatosGrafica() {
        return datosGrafica;
    }

    public void setDatosGrafica(ArrayList<BusquedaActividad.Par> datosGrafica) {
        this.datosGrafica = datosGrafica;
    }

    public int getTotalActividades() {
        return totalActividades;
    }

    public void setTotalActividades(int totalActividades) {
        this.totalActividades = totalActividades;
    }

    @Override
    public String toString() {
        return "Usuario{" + " username= " + username + ", password= " + password + ", rol= " + rol + " }";
    }

    public Usuario clone() {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setUsuario();
        return u;
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
        mensaje = "Error: No existe un usuario con el rol de " + rol + ".";
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
        mensaje = "Error: No existe un usuario con el rol de Jefe de Dependencia del DEx.";
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
                    else {
                        if (username.equals("") || password.equals("")) {
                            mensaje = "No puede dejar campos vacíos";
                            return false;
                        }else{
                            mensaje = "La combinación de USBID y Contraseña no es válida. ";
                            mensaje += " Intente de nuevo.";
                            return false;
                        }
                    }
                }
                if (username.equals("") || password.equals("")) {
                    mensaje = "No puede dejar campos vacíos";
                    return false;
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

    public static ArrayList<Usuario> listar() {
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

    public boolean agregar(String ip, String user) {
        if (!Verificaciones.verificar(this)) {
            return false;
        }
        Entity e = new Entity(0);//USUARIO
        Object[] usuarios = {username, password, rol, tipo, nombres, apellidos, telefono, email};
        if (esUsuario()) {
            mensaje = "Error: Ya existe un usuario registrado con el USB-ID " + username;
            return false;
        } else {
            if (e.insertar(usuarios)) {
                e.setIp(ip);
                e.setUser(user);
                e.insertarLog();
                mensaje = "El usuario " + username + " ha sido registrado con éxito.";
                return true;
            }
            mensaje = "Error: No se pudo registrar el usuario.";
            return false;
        }
    }

    public boolean eliminar(String ip, String user) {
        Entity e = new Entity(0);//USUARIO


        if (e.borrar(ATRIBUTOS[2], this.username)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El usuario ha sido eliminado con éxito.";
            return true;
        }
        mensaje = "Error: El usuario no pudo ser eliminado.";
        return false;

    }

    public boolean modificar(String ip, String user) {
        if (!Verificaciones.verificar(this)) {
            return false;
        }
        Entity e = new Entity(0);//USUARIO

        String[] condColumnas = {
            ATRIBUTOS[2]
        };
        Object[] valores = {
            username
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

        if (e.modificar(condColumnas, valores, colModificar, modificaciones)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El perfil ha sido modificado con éxito";
            return true;
        }
        mensaje = "Error: no se pudo modificar el perfil.";
        return false;
    }

    public String[] actividadesPorTipo() {

        String[] grafica = new String[2];
        String nombre = "";
        String cantidad = "";
        Entity eSelec = new Entity(22);//ACT_PARTICIPA
        ResultSet rs = eSelec.seleccionarNumActividades("usbid = '" + username + "'");
        int total = 0;
        try {
            if (rs != null) {
                while (rs.next()) {
                    nombre += rs.getString("nombre_tipo_actividad") + "|";
                    cantidad += rs.getString("cantidad") + ",";
                    total += rs.getInt("cantidad");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (nombre.length() != 0 && cantidad.length() != 0) {
            nombre = nombre.substring(0, nombre.length() - 1);
            cantidad = cantidad.substring(0, cantidad.length() - 1);
        }

        grafica[0] = nombre;
        grafica[1] = cantidad;

        String[] auxNombre = grafica[0].split("\\|");
        String[] auxCant = grafica[1].split(",");

        ArrayList<BusquedaActividad.Par> aux = new ArrayList<>(0);

        for (int i = 0; i < auxNombre.length; i++) {
            BusquedaActividad.Par parNuevo = new BusquedaActividad.Par(auxNombre[i], auxCant[i]);
            aux.add(parNuevo);
        }
        totalActividades = total;
        datosGrafica = aux;

        return grafica;
    }

    public String[] totalActividadesPorTipo() {

        String[] grafica = new String[2];
        String nombre = "";
        String cantidad = "";
        Entity eSelec = new Entity(21);//TIPO_ACT__ACT
        ResultSet rs = eSelec.seleccionarNumActividades();
        int total = 0;
        try {
            if (rs != null) {
                while (rs.next()) {
                    nombre += rs.getString("nombre_tipo_actividad") + "|";
                    cantidad += rs.getString("cantidad") + ",";
                    total += rs.getInt("cantidad");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (nombre.length() != 0 && cantidad.length() != 0) {
            nombre = nombre.substring(0, nombre.length() - 1);
            cantidad = cantidad.substring(0, cantidad.length() - 1);
        }

        grafica[0] = nombre;
        grafica[1] = cantidad;
        String[] auxNombre = grafica[0].split("\\|");
        String[] auxCant = grafica[1].split(",");

        ArrayList<BusquedaActividad.Par> aux = new ArrayList<>(0);

        for (int i = 0; i < auxNombre.length; i++) {
            BusquedaActividad.Par parNuevo = new BusquedaActividad.Par(auxNombre[i], auxCant[i]);
            aux.add(parNuevo);
        }
        totalActividades = total;
        datosGrafica = aux;

        return grafica;
    }

    public static void main(String[] args) {
    }
}
