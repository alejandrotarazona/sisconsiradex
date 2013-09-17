/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author SisCon
 */
public class TipoActividad extends Root {

    private int id;
    private String nombreTipo;
    private String tipoPR;
    private int nroCampos = 1;
    private String descripcion;
    private String[] permisos;
    private String programa;
    private String validador;
    private ArrayList<Campo> campos = new ArrayList<>();
    private boolean activo;
    private boolean modificado = false;
    private int actividades;
    private static final String[] ATRIBUTOS = {
        "id_tipo_actividad", //0
        "nombre_tipo_actividad", //1
        "tipo_p_r", //2
        "descripcion", //3
        "programa", //4
        "validador", //5
        "activo" //6
    };

    public int getId() {
        return id;
    }

    public int getIdTipoActividad() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public int getNroCampos() {
        return nroCampos;
    }

    public void setNroCampos(int nroCampos) {
        this.nroCampos = nroCampos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPR() {
        return tipoPR;
    }

    public void setTipoPR(String tipoPR) {
        this.tipoPR = tipoPR;
    }

    public String[] getPermisos() {
        return permisos;
    }

    public void setPermisos(String[] permisos) {
        this.permisos = permisos;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public int getActividades() {
        return actividades;
    }

    public void setActividades(int actividades) {
        this.actividades = actividades;
    }

    //Este m[etodo permite que cuando el multibox no tenga nada, el arreglo
    //permisos tome el valor de nulo, pero como sobreescribe el reset de Root  
    //tuve que agregar tambien lo que esta en el reset de Root 
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Root.class.getName()).log(Level.SEVERE, null, ex);
        }
        permisos = null;
    }

    public TipoActividad clone() {
        TipoActividad resp = new TipoActividad();
        resp.setId(this.getId());
        resp.setTipoActividad();

        return resp;
    }

    private boolean checkPermiso(String rol) {
        for (int i = 0; i < permisos.length; i++) {
            String estePermiso = permisos[i];

            System.out.println("\tRol:\t" + rol + "\n\t"
                    + "Permiso:\t" + estePermiso);
            if (rol.equalsIgnoreCase(estePermiso)) {
                System.out.println("permiso concedido");
                return true;
            }
        }
        return false;
    }

    private void setPermisos() {
        Entity ePermisos = new Entity(12);//PERMISOS
        ResultSet rs = ePermisos.listar();
        ArrayList p = new ArrayList<>(0);
        if (rs != null) {
            try {
                while (rs.next()) {
                    int esteTipo = rs.getInt("id_tipo_actividad");
                    if (id == esteTipo) {
                        String permiso = rs.getString("nombre");
                        p.add(permiso);
                        System.out.print("Agregado el permiso " + permiso);
                        System.out.println(" de la actividad " + esteTipo);
                    }
                }
                rs.close();
                permisos = new String[p.size()];
                for (int i = 0; i < p.size(); i++) {
                    permisos[i] = (String) p.get(i);
                    System.out.println(permisos[i]);
                }

            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Salió null el ResultSet de setPermisos");
        }
    }

    /* metodo para instanciar los campos obligatorios al agregar un tipo de actividad */
    public void setCamposObligatorios() {

        Campo c1 = new Campo("participante");
        campos.add(c1);
        Campo c2 = new Campo("fecha");
        campos.add(c2);
        Campo c3 = new Campo("archivo");
        campos.add(c3);
    }

    @Override
    public String toString() {
        return "TipoActividad{" + "nombreTipo=" + nombreTipo + ", nroCampos=" + nroCampos + ", descripcion=" + descripcion + " }";
    }

    public boolean esTipoActividad() {

        Entity e = new Entity(1);//TIPO_ACTIVIDAD

        String[] atrib = {ATRIBUTOS[1], ATRIBUTOS[6]};
        Object[] valor = {nombreTipo, true};
        ResultSet rs = e.seleccionar(atrib, valor);
        try {
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[1]).equals(nombreTipo)) {
                        rs.close();
                        return true;
                    }
                }
                rs.close();
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //teniendo el id hace un set del resto de atributos del Tipo de Actividad
    public void setTipoActividad() {

        Entity e = new Entity(1);//TIPO_ACTIVIDAD

        String[] atrib = {"id_tipo_actividad"};
        Integer[] valor = {id};
        ResultSet rs = e.seleccionar(atrib, valor);
        if (rs != null) {
            try {
                rs.next();
                nombreTipo = rs.getString("nombre_tipo_actividad");
                tipoPR = rs.getString("tipo_p_r");
                descripcion = rs.getString("descripcion");
                programa = rs.getString("programa");
                validador = rs.getString("validador");
                activo = rs.getBoolean("activo");
                this.setPermisos();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public boolean agregarPermisos() {
        boolean resp = true;
        Entity ePermisos = new Entity(10);//TIENE_PERMISO
        Object[] valoresPermisos = {id, 0};
        for (int i = 0; i < permisos.length; i++) {
            String estePermiso = permisos[i];
            switch (estePermiso) {
                case "Estudiante":
                    valoresPermisos[1] = 1;
                    break;
                case "Empleado":
                    valoresPermisos[1] = 2;
                    break;
                case "Obrero":
                    valoresPermisos[1] = 3;
                    break;
                case "Profesor":
                    valoresPermisos[1] = 4;
                    break;
            }
            resp &= ePermisos.insertar(valoresPermisos);
        }
        return resp;
    }

    public boolean agregar(String ip, String user) {

        if (!Verificaciones.verificarCamposVariables(this)
                || !Verificaciones.verificarCamposFijos(this)) {
            return false;
        }

        if (esTipoActividad()) {
            mensaje = "Error: Ya existe un Tipo de Actividad con el Nombre "
                    + "de la Actividad '" + nombreTipo + "'. Por favor "
                    + "intente con otro nombre.";
            return false;
        }

        Entity e = new Entity(1);//TIPO_ACTIVIDAD
        boolean resp;

        Object[] valores = {
            nombreTipo,
            tipoPR,
            descripcion,
            programa,
            validador
        };

        System.out.println("No ha sido insertada previamente");
        String[] aInsertar = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5]
        };

        if (resp = e.insertar2(aInsertar, valores)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido registrado con éxito.";
            id = e.seleccionarMaxId(ATRIBUTOS[0]);

            System.out.println("Ya inserte el tipo de Actividad con ID " + id);
            Iterator it = campos.iterator();
            while (it.hasNext() && resp) {
                System.out.println("Voy iterando");
                Campo cAgregar = (Campo) it.next();
                resp &= cAgregar.agregar(id, ip, user);
                if (!resp) {
                    System.out.print("No se pudo registrar el campo " + cAgregar.getNombre());
                }
            }

            resp &= agregarPermisos();

            if (!resp) {
                mensaje = "Error: El Tipo de Actividad '" + nombreTipo
                        + "'no pudo ser registrado.";
                if (!eliminar(ip, user)) {
                    mensaje = " Error: El Tipo de Actividad '" + nombreTipo
                            + "' no pudo ser resgistrado satisfactoriamente, en caso "
                            + "de que aparezca, por favor, elimínelo.";
                }
            }
        } else {
            mensaje = "Error: El Tipo de Actividad '" + nombreTipo
                    + "'no pudo ser registrado.";
        }

        return resp;

    }

    // los agrega al form para ser enviados desde el action a la vista
    public void agregarCamposNuevos() {
        for (int i = 0; i < nroCampos; i++) {
            Campo c = new Campo();
            campos.add(c);
            modificado = true;
        }
    }

    public boolean eliminar(String ip, String user) {
        Entity eMod = new Entity(1);//TIPO_ACTIVIDAD
        String[] condColumnas = {ATRIBUTOS[0]};
        Object[] valores = {id};
        String[] colModificar = {ATRIBUTOS[6]};
        Object[] modificaciones = {false};

        if (eMod.modificar(condColumnas, valores, colModificar, modificaciones)) {
            eMod.setIp(ip);
            eMod.setUser(user);
            eMod.insertarLog();
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido eliminado con éxito.";
            return true;
        }
        mensaje = "Error: No se pudo eliminar el Tipo de Actividad '" + nombreTipo + "'.";
        return false;
    }

    public boolean eliminarDefinitivo(String ip, String user) {
        Entity e = new Entity(1);//ACTIVIDAD
        e.setIp(ip);
        e.setUser(user);
        if (e.borrar(ATRIBUTOS[0], id) && e.insertarLog()) {

            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido eliminado con éxito.";
            return true;
        }
        mensaje = "El tipo de Actividad '" + nombreTipo + "' no pudo ser eliminado.";
        return false;
    }

    private boolean verificarEliminacionCampos() {
        boolean participante = false;
        boolean archivo = false;

        Iterator it = campos.iterator();
        while (it.hasNext()) {
            Campo campo = (Campo) it.next();
            String tipo = campo.getTipo();
            if (!campo.isEliminado()) {
                if (tipo.equals("participante")) {
                    participante = true;
                }
                if (tipo.equals("archivo")) {
                    archivo = true;
                }
            }
        }
        if (!participante || !archivo) {
            mensaje = "Error: El Tipo de Actividad debe conservar al menos un "
                    + "campo tipo participante y un campo tipo archivo";
            return false;
        }
        return true;
    }

    //elimina los campos marcados para ser eliminados, retorna 0 si no habían campos que eliminar,
    //de lo contrario retorna 1, y 2 si ocurre un error.
    public int eliminarCamposMarcados() {

        if (!verificarEliminacionCampos()) {
            return 2;
        }

        int resp = 0;

        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).isEliminado()) {
                resp = 1;
                campos.remove(i);   //se elimina de campos del form
                i--;
                modificado = true;
            }
        }

        return resp;
    }

    /**
     * Modifica los permisos asociados a un tipo de actividad, eliminando
     * primero los permisos previos dados a dicha actividad e insertando luego
     * los nuevos permisos.
     *
     * @return true en caso de lograr la modificación de los permisos.
     */
    public boolean modificarPermisos(String ip, String user) {

        Entity e = new Entity(10);//TIENE_PERMISO
        e.borrar("id_tipo_actividad", id);
        e.setIp(ip);
        e.setUser(user);

        String[] columnas = {"id_tipo_actividad", "id_permiso"};
        Object[] valores = {id, null};
        boolean resp = true;
        for (int i = 0; i < permisos.length && resp; i++) {

            System.out.println("El permiso nro " + i + " es: " + permisos[i]);
            switch (permisos[i]) {
                case "Estudiante":
                    valores[1] = 1;
                    resp &= e.insertar2(columnas, valores);
                    break;
                case "Empleado":
                    valores[1] = 2;
                    resp &= e.insertar2(columnas, valores);
                    break;
                case "Obrero":
                    valores[1] = 3;
                    resp &= e.insertar2(columnas, valores);
                    break;
                case "Profesor":
                    valores[1] = 4;
                    resp &= e.insertar2(columnas, valores);
                    break;
            }
            e.insertarLog();
        }
        return resp;
    }

    //en el parámetro nombreNM recibe el nombre No Modificado
    public boolean modificar(String nombreNM, String ip, String user) {

        if (!Verificaciones.verificarCamposFijos(this)
                || !Verificaciones.verificarCamposVariables(this)) {
            return false;
        }

        if (esTipoActividad() && !nombreTipo.equals(nombreNM)) {
            mensaje = "Error: Ya existe un Tipo de Actividad llamado '"
                    + nombreTipo + "'. Por favor, intente con otro nombre.";
            return false;
        }

        boolean resp;

        Entity e = new Entity(1);//TIPO_ACTIVIDAD

        String[] condColumnas = {
            ATRIBUTOS[0]
        };
        Object[] valores = {
            id
        };
        String[] colModificar = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6]
        };
        Object[] modificaciones = {
            nombreTipo,
            tipoPR,
            descripcion,
            programa,
            validador,
            activo
        };


        if (resp = e.modificar(condColumnas, valores, colModificar, modificaciones)) {
            e.setIp(ip);
            e.setUser(user);
            e.insertarLog();
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido modificado con éxito.";
            System.out.println("modificacion de campos fijos sin permisos " + resp);

            resp &= this.modificarPermisos(ip, user);
            System.out.println("modificacion de permisos " + resp);

            int tam = campos.size();
            for (int i = 0; i < tam && resp; i++) {
                resp &= campos.get(i).modificar(id, ip, user);
            }

            if (!resp) {
                mensaje = "Error: El Tipo de Actividad no pudo ser modificado satisfactoriamente.";
            }
        } else {
            mensaje = "Error: El Tipo de Actividad no pudo ser modificado.";
        }

        return resp;
    }

    public boolean restaurarTipoActividad() {

        Entity eMod = new Entity(1);//TIPO_ACTIVIDAD

        String[] condColumnas = {
            ATRIBUTOS[0]
        };
        Object[] valores = {
            this.id
        };
        String[] colModificar = {
            ATRIBUTOS[6]
        };
        Object[] modificaciones = {
            true
        };
        boolean b = eMod.modificar(condColumnas, valores, colModificar, modificaciones);
        if (b) {
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido restaurado con éxito.";
            return true;
        }

        mensaje = "Error: No se pudo restaurar el Tipo de Actividad '" + nombreTipo + "'.";
        return false;
    }

    private int totalActividades() {

        Entity eSelec = new Entity(21);//TIPO_ACT__ACT
        ResultSet rs = eSelec.seleccionarNumActividades("id_tipo_actividad = '" + id + "'");
        try {
            if (rs != null) {
                if (rs.next()) {
                    return rs.getInt("cantidad");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static ArrayList<TipoActividad> listar(ResultSet rs) {
        ArrayList<TipoActividad> tipos = new ArrayList<>(0);

        if (rs != null) {
            try {
                while (rs.next()) {
                    TipoActividad t = new TipoActividad();
                    t.setId(rs.getInt(ATRIBUTOS[0]));
                    t.setNombreTipo(rs.getString(ATRIBUTOS[1]));
                    t.setTipoPR(rs.getString(ATRIBUTOS[2]));
                    t.setDescripcion(rs.getString(ATRIBUTOS[3]));
                    t.setPrograma(rs.getString(ATRIBUTOS[4]));
                    t.setValidador(rs.getString(ATRIBUTOS[5]));
                    t.setActivo(rs.getBoolean(ATRIBUTOS[6]));
                    t.setPermisos();
                    t.setActividades(t.totalActividades());
                    tipos.add(t);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tipos;
    }

    /**
     *
     * @return lista con los Tipos de Actividad que cumplen la condicion dada
     */
    public static ArrayList<TipoActividad> listarCondicion(String[] atrib, Object[] val) {
        Entity eListar = new Entity(1);//TIPO_ACTIVIDAD
        ResultSet rs = eListar.seleccionarEnOrden(atrib, val, "nombre_tipo_actividad");
        return listar(rs);
    }

    /**
     * Lista los tipos de actividades que puede realizar el usuario.
     *
     * @param usuario El usuario del cual se quieren listarActivos las
     * posibilidades de tipos de actividad.
     * @return Lista de los tipos de actividad que puede realizar el usuario
     * dado.
     */
    public static ArrayList<TipoActividad> listarTiposActividad(Usuario u) {
        String[] atrib = {ATRIBUTOS[6]};
        Object[] val = {true};
        ArrayList<TipoActividad> tiposAux = listarCondicion(atrib, val);
        ArrayList<TipoActividad> tipos = new ArrayList<>(0);
        Iterator it = tiposAux.iterator();

        System.out.println("El rol del usuario es: " + u.getRol());
        while (it.hasNext()) {
            TipoActividad t = (TipoActividad) it.next();
            System.out.println("estoy checkeando los permisos...");
            if (t.checkPermiso(u.getRol())) {
                tipos.add(t);
            }
        }
        return tipos;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
    }
}
