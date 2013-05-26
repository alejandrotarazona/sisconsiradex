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
    private int nroCampos;
    private String descripcion;
    private String[] permisos;
    private String programa;
    private String validador;
    private int nroProductos;
    private ArrayList<Campo> campos;
    private boolean activo;
    private static final String[] ATRIBUTOS = {
        "id_tipo_actividad", //0
        "nombre_tipo_actividad", //1
        "tipo_p_r", //2
        "nro_campos", //3
        "descripcion", //4
        "programa", //5
        "validador", //6
        "nro_productos", //7 
        "activo" //8
    };
    private static final String[] tiposCampos = {
        "texto", //STRING
        "numero", //INT
        "fecha", //DATE
        "checkbox"//CHECKBOX
    };

    public static String[] getTiposCampos() {
        return tiposCampos;
    }

    public TipoActividad() {
    }

    public TipoActividad(String nombreTipo, int nroCampos, String descripcion) {
        this.nombreTipo = nombreTipo;
        this.nroCampos = nroCampos;
        this.descripcion = descripcion;

    }

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

    public Campo getCampo(int indice) {

        return campos.get(indice);
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

    public int getNroProductos() {
        return nroProductos;
    }

    public void setNroProductos(int nroProductos) {
        this.nroProductos = nroProductos;
    }

    @Override
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
        Entity ePermisos = new Entity(0, 12);
        ResultSet rs = ePermisos.listar();
        ArrayList p = new ArrayList<>(0);
        if (rs != null) {
            try {
                while (rs.next()) {
                    String esteTipo = rs.getString("nombre_tipo_actividad");
                    if (nombreTipo.equals(esteTipo)) {
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

    /* metodo para instancias los campos de un tipo de actividad un ves se tiene
     el numero de campos y productos */
    public void setCampos() {
        ArrayList<Campo> cs = new ArrayList<>();
        for (int i = 0; i < nroCampos; i++) {
            Campo c = new Campo();
            c.setIdTipoActividad(id);
            cs.add(c);
        }

        for (int i = 0; i < nroProductos; i++) {
            Campo c = new Campo();
            c.setIdTipoActividad(id);
            c.setObligatorio(true);
            c.setTipo("producto");
            cs.add(c);
        }
        campos = cs;
    }

    @Override
    public String toString() {
        return "TipoActividad{" + "nombreTipo=" + nombreTipo + ", nroCampos=" + nroCampos + ", descripcion=" + descripcion + " }";
    }

    public boolean esTipoActividad() {

        Entity e = new Entity(0, 1);

        String[] atrib = {ATRIBUTOS[1]};
        String[] valor = {nombreTipo};
        ResultSet rs = e.seleccionar(atrib, valor);
        try {
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[1]).equals(nombreTipo)) {
                        rs.close();
                        return true;
                    }
                }
            }
            rs.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //teniendo el id hace un set del resto de atributos del Tipo de Actividad
    public void setTipoActividad() {

        Entity e = new Entity(0, 1);

        String[] atrib = {"id_tipo_actividad"};
        Integer[] valor = {id};
        ResultSet rs = e.seleccionar(atrib, valor);
        if (rs != null) {
            try {
                rs.next();
                nombreTipo = rs.getString("nombre_tipo_actividad");
                tipoPR = rs.getString("tipo_p_r");
                nroCampos = rs.getInt("nro_campos");
                descripcion = rs.getString("descripcion");
                programa = rs.getString("programa");
                validador = rs.getString("validador");
                nroProductos = rs.getInt("nro_productos");
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
        Entity ePermisos = new Entity(1, 10);
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

    public boolean agregarTipoActividad() {

        if (!Verificaciones.verifCV(this)) {
            return false;
        }

        Entity e = new Entity(1, 1);
        boolean resp;

        Object[] valores = {
            nombreTipo,
            tipoPR,
            nroCampos,
            descripcion,
            programa,
            validador,
            nroProductos
        };

        System.out.println("No ha sido insertada previamente");
        String[] aInsertar = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6],
            ATRIBUTOS[7]
        };

        if (resp = e.insertar2(aInsertar, valores)) {
            id = e.seleccionarMaxId(ATRIBUTOS[0]);

            System.out.println("Ya inserte el tipo de Actividad con ID " + id);
            Iterator it = campos.iterator();
            System.out.println("Creo el iterador");
            while (it.hasNext() && resp) {
                System.out.println("Voy iterando");
                Campo cAgregar = (Campo) it.next();
                resp &= cAgregar.agregarCampo(id);
                if (!resp) {
                    System.out.print("No se pudo registrar el campo " + cAgregar.getNombre());
                    this.eliminarTipoActividad();
                }
            }


            resp &= agregarPermisos();

        } else if (!resp) {
            this.eliminarTipoActividad();
            mensajeError = "Error: El Tipo de Actividad no pudo ser registrado.";
            return false;
        }

        return resp;
    }

    public boolean eliminarTipoActividad() {
        Entity eMod = new Entity(2, 1); //UPDATE TIPO_ACTIVIDAD
        String[] condColumnas = {
            ATRIBUTOS[0]
        };
        Object[] valores = {
            this.id
        };
        String[] colModificar = {
            ATRIBUTOS[8]
        };
        Object[] modificaciones = {
            false
        };
        boolean b = eMod.modificar(condColumnas, valores, colModificar, modificaciones);
        if (b) {
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido eliminado";
            return true;
        }

        mensajeError = "Error: No se pudo eliminar el Tipo de Actividad '" + nombreTipo + "'.";
        return false;

    }

    /**
     *
     * @return Lista con todos los Tipos de Actividad que no están activos
     */
    public static ArrayList<TipoActividad> listarDesactivos() {
        Entity eListar = new Entity(0, 1);//SELECT TIPO_ACTIVIDAD
        String[] atrib = {ATRIBUTOS[8]};
        Boolean[] valor = {false};

        ResultSet rs = eListar.seleccionar(atrib, valor);
        ArrayList<TipoActividad> tipos = new ArrayList<>(0);
        if (rs != null) {
            try {
                while (rs.next()) {
                    TipoActividad t = new TipoActividad();
                    t.setId(rs.getInt(ATRIBUTOS[0]));
                    t.setNombreTipo(rs.getString(ATRIBUTOS[1]));
                    tipos.add(t);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tipos;
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
                    t.setNroCampos(rs.getInt(ATRIBUTOS[3]));
                    t.setDescripcion(rs.getString(ATRIBUTOS[4]));
                    t.setPrograma(rs.getString(ATRIBUTOS[5]));
                    t.setValidador(rs.getString(ATRIBUTOS[6]));
                    t.setNroProductos(rs.getInt(ATRIBUTOS[7]));
                    t.setActivo(rs.getBoolean(ATRIBUTOS[8]));
                    t.setPermisos();
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
    public static ArrayList<TipoActividad> listarCondicion(String atributo, Object valor) {
        Entity eListar = new Entity(0, 1);//SELECT TIPO_ACTIVIDAD
        String[] atrib = {atributo};
        Object[] val = {valor};
        ResultSet rs = eListar.seleccionar(atrib, val);
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
        ArrayList<TipoActividad> tiposAux = listarCondicion(ATRIBUTOS[8], true);
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

    /**
     * Modifica los permisos asociados a un tipo de actividad, eliminando
     * primero los permisos previos dados a dicha actividad e insertando luego
     * los nuevos permisos.
     *
     * @return true en caso de lograr la modificación de los permisos.
     */
    public boolean modificarPermisos() {

        Entity e = new Entity(5, 10);//DELETE TIENE_PERMISO
        e.borrar("id_tipo_actividad", id);

        e = new Entity(1, 10); //INSERT TIENE_PERMISO
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
        }
        return resp;
    }

    //Con esto es que puedo definir que cuando el multibox no tenga nada, el arreglo
    //permisos tome el valor de nulo, pero como sobreescribe el reset de Root tuve 
    //que agregar lo que estaba en ese reset de Root tambien
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Root.class.getName()).log(Level.SEVERE, null, ex);
        }
        permisos = null;
    }

    //en el parámetro taNM recibe un TipoActividad No Modificado
    public boolean modificar(TipoActividad taNM) {

        if (!Verificaciones.verifCF(this) || !Verificaciones.verifCV(this)) {
            return false;
        }

        boolean resp = true;

        Entity e = new Entity(2, 1);//UPDATE TIPO_ACTIVIDAD

        String[] condColumnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6],
            ATRIBUTOS[7],
            ATRIBUTOS[8]
        };
        Object[] valores = {
            taNM.getNombreTipo(),
            taNM.getTipoPR(),
            taNM.getDescripcion(),
            taNM.getPrograma(),
            taNM.getValidador(),
            taNM.getNroProductos(),
            taNM.isActivo()
        };
        String[] colModificar = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6],
            ATRIBUTOS[7],
            ATRIBUTOS[8]
        };
        Object[] modificaciones = {
            nombreTipo,
            tipoPR,
            descripcion,
            programa,
            validador,
            nroProductos,
            activo
        };

        if (this.esTipoActividad()
                && !nombreTipo.equals(taNM.getNombreTipo())) {
            mensaje = "Error: Ya existe un Tipo de Actividad llamado '"
                    + nombreTipo + "'. Por favor intente con otro nombre.";
            return false;
        }

        resp &= e.modificar(condColumnas, valores, colModificar, modificaciones);
        System.out.println("modificacion de campos fijos sin permisos " + resp);
        Iterator it = taNM.getCampos().iterator();

        resp &= this.modificarPermisos();
        System.out.println("modificacion de permisos " + resp);

        for (int i = 0; it.hasNext() && resp; i++) {
            Campo campoNM = (Campo) it.next();
            resp &= campos.get(i).modificar(campoNM, id);
            System.out.println("Update " + resp + " " + campoNM.getNombre());
        }

        if (!resp) {
            mensajeError = "Error: El Tipo de Actividad no pudo ser modificado.";
        }

        return resp;
    }

    public boolean restaurarTipoActividad() {

        Entity eMod = new Entity(2, 1);//UPDATE TIPO_ACTIVIDAD

        String[] condColumnas = {
            ATRIBUTOS[0]
        };
        Object[] valores = {
            this.id
        };
        String[] colModificar = {
            ATRIBUTOS[8]
        };
        Object[] modificaciones = {
            true
        };
        boolean b = eMod.modificar(condColumnas, valores, colModificar, modificaciones);
        if (b) {
            mensaje = "El Tipo de Actividad '" + nombreTipo + "' ha sido restaurado";
            return true;
        }

        mensajeError = "Error: No se pudo restaurar el Tipo de Actividad '" + nombreTipo + "'.";
        return false;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //TipoActividad t = new TipoActividad("pasantia", 3, "pasantia");
        //t.agregarTipoActividad();

        TipoActividad t = new TipoActividad("prueba1", 2, "pasantia");
        t.setId(1);

        //t.setTipoPR("p");
        //ArrayList<Campo> cs = new ArrayList<>();

        //Campo c0 = new Campo("Nombre", "texto", 16, true);
        //Campo c1 = new Campo("Apellido", "texto", 16, false);
        //cs.add(c0);
        //cs.add(c1);
        //t.setCampos(cs);

        t.setTipoActividad();

        if (false) {
            System.out.println("\n\n\nExito");
        } else {
            System.out.println("\n\n\nFracaso");
        }

        TipoActividad t0 = (TipoActividad) t.clone();
        System.out.println(t.getNombreTipo());
        System.out.println(t0.getNombreTipo());
//        t.eliminarTipoActividad();


    }
}
