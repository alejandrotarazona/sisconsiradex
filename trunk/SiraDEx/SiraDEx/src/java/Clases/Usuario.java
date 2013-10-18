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
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author SisCon
 */
public class Usuario extends Root {

    private String nombres;
    private String apellidos;
    private String username;
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
        "telefono", //3
        "email", //4
        "rol" //5
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
        return "Usuario{" + " username= " + username + ", rol= " + rol + " }";
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
                telefono = rs.getString(ATRIBUTOS[3]);
                email = rs.getString(ATRIBUTOS[4]);
                rol = rs.getString(ATRIBUTOS[5]);
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
            if (rs != null && rs.next()) {
              
                nombres = rs.getString(ATRIBUTOS[0]);
                apellidos = rs.getString(ATRIBUTOS[1]);
                username = rs.getString(ATRIBUTOS[2]);
                telefono = rs.getString(ATRIBUTOS[3]);
                email = rs.getString(ATRIBUTOS[4]);
                this.rol = rs.getString(ATRIBUTOS[5]);
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
            if (rs != null && rs.next()) {
               
                nombres = rs.getString(ATRIBUTOS[0]);
                apellidos = rs.getString(ATRIBUTOS[1]);
                username = rs.getString(ATRIBUTOS[2]);
                telefono = rs.getString(ATRIBUTOS[3]);
                email = rs.getString(ATRIBUTOS[4]);
                this.rol = rs.getString(ATRIBUTOS[5]);
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

        if (rs != null) {
            try {

                if (rs.next() && rs.getString(ATRIBUTOS[2]).equals(username)) {
                    rs.close();
                    return true;
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                    u.setTelefono(rs.getString(ATRIBUTOS[3]));
                    u.setEmail(rs.getString(ATRIBUTOS[4]));
                    u.setRol(rs.getString(ATRIBUTOS[5]));
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
        Object[] usuarios = {username, rol, nombres, apellidos, telefono, email};
        if (esUsuario()) {
            mensaje = "Error: Ya existe un usuario registrado con el USB-ID " + username;
            return false;
        } else {
            if (e.insertar(usuarios)) {
                e.setIp(ip);
                e.setUser(user);
                e.insertarLog();
                mensaje = "El usuario " + username + " ha sido registrado con éxito.";
                switch (rol) {
                    case "estudiante":
                        agregarACatalogo(ip, user, 3);
                        break;
                    case "profesor":
                        agregarACatalogo(ip, user, 4);
                        break;
                    case "empleado":
                        agregarACatalogo(ip, user, 5);
                        break;
                    case "obrero":
                        agregarACatalogo(ip, user, 6);
                        break;
                }
                return true;
            }
            mensaje = "Error: No se pudo registrar el usuario.";
            return false;
        }
    }

    public boolean esUsuarioParticipante() {

        Entity e = new Entity(5);//PARTICIPA

        String[] col = {ATRIBUTOS[2]};
        String[] cond = {username};

        ResultSet rs = e.seleccionar(col, cond);

        if (rs != null) {
            try {

                if (rs.next() && rs.getString(ATRIBUTOS[2]).equals(username)) {
                    rs.close();
                    return true;
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private boolean actualizarValor() {
        Entity e = new Entity(5);//PARTICIPA   id_act       INT NOT NULL,
        //usbid        VARCHAR(80) NOT NULL,
        //id_campo     INT NOT NULL,
        boolean resp = true;
        String[] atrib = {ATRIBUTOS[2]};
        Object[] valor = {username};
        ResultSet rs = e.seleccionar(atrib, valor);
        String participantes;
        if (rs != null) {
            try {
                while (rs.next() && resp) {

                    int idCampo = rs.getInt("id_campo");
                    int idActividad = rs.getInt("id_act");
                    e = new Entity(4);//id_actividad INT NOT NULL,
                    //id_actividad INT NOT NULL,

                    String[] condColumnas = {
                        "id_campo",
                        "id_actividad"
                    };
                    Object[] valores = {
                        idCampo,
                        idActividad
                    };

                    ResultSet r = e.seleccionar(condColumnas, valores);

                    if (r != null) {
                        r.next();
                        participantes = r.getString("valor");
                        String[] arregloPart = participantes.split("; ");
                        for (int i = 0; i < arregloPart.length; i++) {
                            if (arregloPart[i].contains(username)) {
                                arregloPart[i] = "$" + apellidos + ", " + nombres;
                                break;
                            }
                        }
                        participantes = arregloPart[0];
                        for (int i = 1; i < arregloPart.length; i++) {
                            participantes += "; " + arregloPart[i];
                        }

                        String[] colModificar = {
                            "valor"
                        };

                        Object[] modificaciones = {
                            participantes
                        };

                        resp &= e.modificar(condColumnas, valores, colModificar, modificaciones);
                        r.close();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return resp;
    }

    private boolean actualizarParticipa() {

        Entity e = new Entity(5);//PARTICIPA

        String[] condColumnas = {
            ATRIBUTOS[2]
        };
        Object[] valores = {
            username
        };

        String[] colModificar = {
            ATRIBUTOS[2]
        };
        Object[] modificaciones = {
            "$" + apellidos + ", " + nombres
        };

        if (!e.modificar(condColumnas, valores, colModificar, modificaciones)) {
            return false;
        }

        return true;
    }

    public boolean eliminar(String ip, String user) {
        Entity e = new Entity(0);//USUARIO
        if (esUsuarioParticipante()) {
            if (!actualizarValor() || !actualizarParticipa()) {
                mensaje = "Error: El usuario no pudo ser eliminado.";
                return false;
            }
        }
        if (e.borrar(ATRIBUTOS[2], this.username)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El usuario ha sido eliminado con éxito.";
            switch (rol) {
                case "estudiante":
                    eliminarDeCatalogo(3);
                    break;
                case "profesor":
                    eliminarDeCatalogo(4);
                    break;
                case "empleado":
                    eliminarDeCatalogo(5);
                    break;
                case "obrero":
                    eliminarDeCatalogo(6);
                    break;
            }

            return true;
        }
        mensaje = "Error: El usuario no pudo ser eliminado.";
        return false;

    }

    public boolean modificar(String ip, String user, String rolNM) {
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
            ATRIBUTOS[5]
        };
        Object[] modificaciones = {
            nombres,
            apellidos,
            telefono,
            email,
            rol
        };

        if (e.modificar(condColumnas, valores, colModificar, modificaciones)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El perfil ha sido modificado con éxito";
            if (rolNM != null && !rolNM.equals(rol)) {
                switch (rolNM) {
                    case "estudiante":
                        eliminarDeCatalogo(3);
                        break;
                    case "profesor":
                        eliminarDeCatalogo(4);
                        break;
                    case "empleado":
                        eliminarDeCatalogo(5);
                        break;
                    case "obrero":
                        eliminarDeCatalogo(6);
                        break;
                }
                switch (rol) {
                    case "estudiante":
                        agregarACatalogo(ip, user, 3);
                        break;
                    case "profesor":
                        agregarACatalogo(ip, user, 4);
                        break;
                    case "empleado":
                        agregarACatalogo(ip, user, 5);
                        break;
                    case "obrero":
                        agregarACatalogo(ip, user, 6);
                        break;
                }
            }
            return true;
        }
        mensaje = "Error: no se pudo modificar el perfil.";
        return false;
    }

    public String[] actividadesPorTipo() {

        String[] grafica = new String[2];
        String nombre = "";
        String cantidad = "";
        Entity eSelec = new Entity(16);//ACT_PARTICIPA
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
            Logger.getLogger(Actividad.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        Entity eSelec = new Entity(15);//TIPO_ACT__ACT
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
            Logger.getLogger(Actividad.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    public boolean obtenerUsuario() {
        String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
        String MY_HOST = "ldap://ldap-master.usb.ve";
        String[] attrIDs = {
            "givenname",
            "uid",
            "sn",
            "telephonenumber",
            "gidnumber"
        };
        // Datos ldap
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);
        env.put(Context.PROVIDER_URL, MY_HOST);

        SearchControls ctls = new SearchControls();
        ctls.setReturningAttributes(attrIDs);
        NamingEnumeration<SearchResult> answer;
        try {
            DirContext ctx = new InitialDirContext(env);

            answer = ctx.search("ou=People,dc=usb,dc=ve", "(&(uid=" + username + "))", ctls);

            while (answer.hasMore()) {
                Attributes attr = answer.next().getAttributes();
                apellidos = attr.get("sn").toString().split(": ")[1].replace("#", "ñ");
                nombres = attr.get("givenname").toString().split(": ")[1].replace("#", "ñ");
                if (nombres == null) {
                    mensaje = "Error: No se pudo establecer la conexión con el LDAP.";
                    return false;
                }
                username = attr.get("uid").toString().split(": ")[1];
                if (attr.get("telephonenumber") != null) {
                    telefono = attr.get("telephonenumber").toString().split(": ")[1];
                }
                String gid = attr.get("gidnumber").toString().split(": ")[1];
                System.out.print("EL GIDNUMBER " + gid);
                switch (gid) {
                    case "1000":
                        rol = "profesor";
                        break;
                    case "1004":
                        rol = "estudiante";
                        break;
                    case "1002":
                        rol = "empleado";
                        break;
                    default:
                        mensaje = "Error: Usted no es un tipo de usuario válido "
                                + "para ser registrado al SiraDEx.";
                        return false;


                }

            }
        } catch (NamingException ex) {
            Logger.getLogger(Usuario.class
                    .getName()).log(Level.SEVERE, null, ex);

            return false;
        }
        return true;

    }

    private void agregarACatalogo(String ip, String user, int idCatalogo) {

        Entity eElemento = new Entity(8);//ELEMENTO_CATALOGO
        boolean resp;
        String[] columnas = {"id_catalogo"};
        Object[] valores = {idCatalogo};
        if (resp = eElemento.insertar2(columnas, valores)) {

            int idElemento = eElemento.seleccionarMaxId("id_elemento");

            ArrayList<CampoCatalogoValor> camposValores = CampoCatalogoValor.listar(idCatalogo);

            Iterator itValores = camposValores.iterator();

            while (itValores.hasNext() && resp) {
                CampoCatalogoValor ccv = (CampoCatalogoValor) itValores.next();
                String tipo = ccv.getCampo().getTipo();
                switch (tipo) {
                    case "usbid":
                        ccv.setValor(username);
                        break;
                    case "usuario":
                        ccv.setValor(nombres + " " + apellidos);
                        break;
                }
                ccv.agregar(idElemento, ip, user);
            }
        }
        if (!resp) {
            mensaje += " Se produjo un error al agregar el nuevo usuario al catálogo "
                    + "correspondiente a su rol, por favor, informe al webmaster"
                    + " sobre este error.";
        }
    }

    private void eliminarDeCatalogo(int idCatalogo) {

        Entity e = new Entity(17);//ELEMENTOS

        String[] atrib = {"id_catalogo", "tipo", "valor"};
        Object[] valor = {idCatalogo, "usbid", username};
        ResultSet rs = e.seleccionar(atrib, valor);

        try {
            if (rs != null) {
                if (rs.next()) {
                    int idElem = rs.getInt("id_elemento");
                    rs.close();
                    e = new Entity(8);//ELEMENTO_CATALOGO
                    if (!e.borrar("id_elemento", idElem)) {
                        mensaje += " Se produjo un error al eliminar el usuario del catálogo "
                                + "correspondiente a su rol, por favor, intente eliminarlo "
                                + "mediante el sistema.";
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
    }
}
