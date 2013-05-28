/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author SisCon
 */
public class Actividad extends Root {

    private int idActividad;
    private int idTipoActividad;
    private String nombreTipoActividad;
    private String validacion;
    private String creador; //usbid
    private String fechaCreacion;
    private String modificador;
    private String fechaModif;
    private String descripcion;
    private String validador;
    private ArrayList<String> participantes = new ArrayList<>(0);
    private ArrayList<CampoValor> camposValores;
    private ArrayList<Archivo> archivos = new ArrayList<>(0);
    private int idArchivo;
    private static String[] ATRIBUTOS = {
        "id_actividad", //0
        "id_tipo_actividad", //1
        "nombre_tipo_actividad", //2
        "validacion", //3
        "creador", //4
        "fecha_creacion",//5
        "modificador", //6
        "fecha_modif", //7
        "descripcion", //8
        "validador" //9
    };

    public Actividad() {
    }

    public Actividad(int idActividad, String validacion,
            String evaluacion) {
        this.idActividad = idActividad;
        this.validacion = validacion;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombreTipoActividad() {
        return nombreTipoActividad;
    }

    public void setNombreTipoActividad(String nombreTipoActividad) {
        this.nombreTipoActividad = nombreTipoActividad;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public ArrayList<CampoValor> getCamposValores() {
        return camposValores;
    }

    public void setCamposValores(ArrayList<CampoValor> camposValores) {
        this.camposValores = camposValores;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fecha_creacion) {
        this.fechaCreacion = fecha_creacion;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fecha_modif) {
        this.fechaModif = fecha_modif;
    }

    public ArrayList getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList participantes) {
        this.participantes = participantes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(ArrayList<Archivo> archivos) {
        this.archivos = archivos;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public static class Archivo implements Serializable {

        private FormFile file;
        private String nombre;
        private String tipo;

        public Archivo(FormFile file, String nombre, String tipo) {
            this.file = file;
            this.nombre = nombre;
            this.tipo = tipo;
        }

        public FormFile getFile() {
            return file;
        }

        public void setFile(FormFile file) {
            this.file = file;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
    }

    @Override
    public String toString() {
        return "Actividad\n\t{" + "idTipoActividad=" + idTipoActividad
                + "\n\t idActividad=" + idActividad + "\n\t usbid=" + creador + "}";
    }

    public void setParticipantes(int idAct) {


        Entity eBuscar = new Entity(0, 5); //SELECT PARTICIPA
        String[] tablas = {
            "ACTIVIDAD",
            "USUARIO"
        };
        String cols = "p.usbid, nombres, apellidos";
        String[] joins = {"p.id_act=a.id_actividad", "p.usbid=u.usbid"};
        String cond = "id_actividad=" + idAct;
        ResultSet rs = eBuscar.seleccionarSinRepeticion(tablas, cols, "LEFT OUTER JOIN", joins, cond);
        if (rs != null) {
            try {
                while (rs.next()) {
                    String participante;

                    if (rs.getString("nombres") != null) {
                        participante = rs.getString("apellidos") + ", " + rs.getString("nombres");
                    } else {
                        participante = rs.getString("usbid");
                    }
                    participantes.add(participante);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String participantesToString() {
        String p = "";
        Iterator it = participantes.iterator();
        while (it.hasNext()) {
            p += (String) it.next() + "; ";
        }
        p = p.substring(0, p.length()) + ".";
        return p;
    }

    public String camposValoresToString() {
        String s = "";
        Iterator it = camposValores.iterator();
        while (it.hasNext()) {
            CampoValor cv = (CampoValor) it.next();
            String tipo = cv.getCampo().getTipo();
            String valor = cv.getValor();
            if (Clases.Verificaciones.esVacio(valor)) {
                continue;
            }
            switch (tipo) {
                case "textol":
                case "producto":
                    continue;
                case "checkbox":
                    if (!valor.equals("false")) {
                        s += cv.getCampo().getNombre() + ", ";
                    }
                    break;
                default:
                    s += cv.getCampo().getNombre() + ": "
                            + cv.getValor() + ", ";
                    break;
            }
        }
        int tam = s.length();
        if (tam > 1) {
            s = s.substring(0, s.length() - 2) + ".";
        }
        return s;
    }

    public String getApellidoNombreCreador() {

        Entity e = new Entity(0, 0);//SELECT USUARIO

        String[] col = {"usbid"};
        Object[] condicion = {creador};

        ResultSet rs = e.seleccionar(col, condicion);
        if (rs != null) {
            try {
                rs.next();
                String resp = rs.getString("apellidos");
                resp += ", " + rs.getString("nombres");
                rs.close();
                return resp;
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //teniendo el idActividad hace un set del resto de atributos de la Actividad
    public void setActividad() {
        try {
            Entity eActividad = new Entity(0, 2);//SELECT ACTIVIDAD

            String[] tabABuscar = {
                "ACTIVIDAD",
                "TIPO_ACTIVIDAD"
            };
            String[] atrib = {"id_actividad"};
            Integer[] valor = {idActividad};

            try (ResultSet rs = eActividad.naturalJoin(ATRIBUTOS, tabABuscar, atrib, valor)) {
                if (rs != null) {
                    rs.next();
                    idTipoActividad = rs.getInt(ATRIBUTOS[1]);
                    nombreTipoActividad = rs.getString(ATRIBUTOS[2]);
                    validacion = rs.getString(ATRIBUTOS[3]);
                    creador = rs.getString(ATRIBUTOS[4]);
                    fechaCreacion = rs.getString(ATRIBUTOS[5]);
                    modificador = rs.getString(ATRIBUTOS[6]);
                    fechaModif = rs.getString(ATRIBUTOS[7]);
                    descripcion = rs.getString(ATRIBUTOS[8]);
                    camposValores = CampoValor.listarCamposValores(idActividad);

                    Iterator iter = camposValores.iterator();

                    while (iter.hasNext()) {
                        CampoValor cv = (CampoValor) iter.next();
                        String tipoCampo = cv.getCampo().getTipo();
                        String nombre = cv.getValor();
                        if (!nombre.equals("")
                                && ((tipoCampo.equals("archivo")
                                || tipoCampo.equals("producto")))) {
                            Archivo arch = new Archivo(cv.getFile(), nombre,
                                    cv.getCampo().getNombre());
                            archivos.add(arch);
                        }
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean esActividadUsuario() {
        try {
            Entity e = new Entity(0, 2);//SELECT ACTIVIDAD

            String[] col = {ATRIBUTOS[0], ATRIBUTOS[4]};
            Object[] condicion = {idActividad, creador};

            ResultSet rs = e.seleccionar(col, condicion);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getInt(ATRIBUTOS[0]) == idActividad
                            && rs.getString(ATRIBUTOS[4]).equals(creador)) {
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

    public boolean agregarActividad() {

        if (!Verificaciones.verif(this)) {
            return false;
        }

        Entity e = new Entity(1, 2);//INSERT ACTIVIDAD
        boolean resp;

        String[] columnas = {
            "id_tipo_actividad",
            "creador"
        };

        Object[] actividad = {
            idTipoActividad,
            creador
        };

        if (resp = e.insertar2(columnas, actividad)) {

            idActividad = e.seleccionarMaxId(ATRIBUTOS[0]);

            Iterator itValores = this.camposValores.iterator();

            while ((itValores.hasNext())) {
                CampoValor cv = (CampoValor) itValores.next();
                resp &= cv.agregar(this.idActividad);
            }

            if (!resp) {
                mensajeError = "Error: No se pudo registrar la Actividad.";
            }

            return resp;

        }

        return resp;
    }

    public boolean eliminarActividad() {
        Entity e = new Entity(5, 2);//DELETE ACTIVIDAD
        if (e.borrar(ATRIBUTOS[0], idActividad)) {
            mensaje = "La Actividad '" + nombreTipoActividad + "' ha sido eliminada con éxito.";
            return true;
        }
        mensajeError = "La Actividad '" + nombreTipoActividad + "' ha sid.";
        return false;
    }

    public boolean validar(boolean valida) {
        boolean resp = true;
        Entity eValidar = new Entity(2, 2); //UPDATE ACTIVIDAD
        String[] condColumn = {
            ATRIBUTOS[0]
        };
        Object[] condValores = {
            idActividad
        };
        String[] colModificar = {
            ATRIBUTOS[3]
        };
        String val;
        if (valida) {
            val = "Validada";
        } else {
            val = "Rechazada";
        }
        Object[] modificaciones = {
            val
        };

        resp = resp && eValidar.modificar(condColumn, condValores, colModificar, modificaciones);
        return resp;
    }

    /**
     * Funcion que toma los valores de un ResultSet y crea una lista de
     * Actividades a partir de ella.
     *
     * @param rs Resultado de una busqueda en la Base de Datos y que contenga la
     * informacion de una o varias Actividades.
     * @return Lista de las Actividades encontradas por la busqueda.
     * @throws SQLException
     */
    public static ArrayList<Actividad> listar(ResultSet rs) {
        try {
            ArrayList<Actividad> acts = new ArrayList<>(0);
            if (rs != null) {

                while (rs.next()) {
                    Actividad a = new Actividad();
                    a.setIdActividad(rs.getInt(ATRIBUTOS[0]));
                    a.setIdTipoActividad(rs.getInt(ATRIBUTOS[1]));
                    a.setNombreTipoActividad(rs.getString(ATRIBUTOS[2]));
                    a.setValidacion(rs.getString(ATRIBUTOS[3]));
                    a.setCreador(rs.getString(ATRIBUTOS[4]));
                    a.setFechaCreacion(rs.getString(ATRIBUTOS[5]));
                    a.setModificador(rs.getString(ATRIBUTOS[6]));
                    a.setFechaModif(rs.getString(ATRIBUTOS[7]));
                    a.setDescripcion(rs.getString(ATRIBUTOS[8]));
                    a.setValidador(rs.getString(ATRIBUTOS[9]));
                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));
                    a.setParticipantes(a.idActividad);

                    Iterator iter = a.getCamposValores().iterator();
                    ArrayList<Archivo> archs = new ArrayList<>(0);
                    while (iter.hasNext()) {
                        CampoValor cv = (CampoValor) iter.next();
                        String tipoCampo = cv.getCampo().getTipo();
                        String nombre = cv.getValor();
                        if (!nombre.equals("")
                                && ((tipoCampo.equals("archivo")
                                || tipoCampo.equals("producto")))) {
                            Archivo arch = new Archivo(cv.getFile(), nombre,
                                    cv.getCampo().getNombre());
                            archs.add(arch);
                        }
                    }
                    a.setArchivos(archs);

                    acts.add(a);
                }
            } else {
                acts = null;
            }
            return acts;
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Actividad> listarActividades() {

        Entity eActividad = new Entity(0, 21);
        ResultSet rs = eActividad.listar();
        return listar(rs);
    }

    /**
     * Lista todas las actividades de un tipo especificado en el atributo de la
     * actividad que lo llama.
     *
     * @return Lista con todas las actividades del mismo tipo que la actividad.
     */
    public ArrayList<Actividad> listarActividadesDeTipo() {

        Entity eActividad = new Entity(0, 21);
        String[] columna = {ATRIBUTOS[1]};
        Integer[] condicion = {idTipoActividad};

        ResultSet rs = eActividad.seleccionar(columna, condicion);
        return listar(rs);
    }

    /**
     * Lista las actividades que tiene el usuario.
     *
     * @return Lista con las actividades realizadas por un usuario. Tambien en
     * las que participa.
     */
    public static ArrayList<Actividad> listarActividadesDeUsuario(String usbid) {

        Entity eBuscar = new Entity(0, 5); //SELECT PARTICIPA
        String[] tablas = {
            "ACTIVIDAD",
            "TIPO_ACTIVIDAD"
        };
        String cols = "*";
        String[] joins = {"p.id_act=a.id_actividad", "t.id_tipo_actividad=a.id_tipo_actividad"};
        String conds = "p.usbid=" + "'" + usbid + "'";
        ResultSet rs = eBuscar.seleccionarSinRepeticion(tablas, cols, "JOIN", joins, conds);
        return listar(rs);
    }

    /**
     * Lista las actividades según su validador.
     *
     * @param validador Nombre de la dependencia que desea listarCampos las
     * actividades a por validar.
     * @return Lista con las actividades realacionadas con el validador,
     */
    public static ArrayList<Actividad> listarActividadesDeValidador(String validador) {
        Entity eActividad = new Entity(0, 21);
        String[] columna = {ATRIBUTOS[3], ATRIBUTOS[9]}; //validacion, validador
        String[] condicion = {"En espera", validador};

        ResultSet rs = eActividad.seleccionar(columna, condicion);
        return listar(rs);
    }

    /**
     * Lista las actividades según su clasificación para el DEx.
     *
     * @param tipo P o R. En caso de ser distinto arrojará null.
     * @return Lista con las Actividades que sean del tipo indicado.
     */
    public static ArrayList<Actividad> listarActividadesPR(String tipo) {
        Entity eBuscar;
        ResultSet rs;
        switch (tipo) {
            case "P":
            case "p":
                eBuscar = new Entity(0, 19);
                break;
            case "R":
            case "r":
                eBuscar = new Entity(0, 20);
                break;
            default:
                return null;
        }
        rs = eBuscar.listar();

        return listar(rs);
    }

    /**
     * Lista las actividades de un programa dado.
     *
     * @param programa
     * @return Lista con las actividades que pertenezcan al programa dado.
     */
    public static ArrayList<Actividad> listarActividadesPrograma(String programa) {

        Entity eListar = new Entity(0, 21);
        String[] columnas = {
            "programa"
        };
        Object[] condiciones = {
            programa
        };

        ResultSet rs = eListar.seleccionar(columnas, condiciones);

        return listar(rs);
    }

    /**
     * Lista las actividades que ha creado un usuario.
     *
     * @param creador El usuario a buscar.
     * @return Lista con todas las actividades que ha creado el usaurio dado.
     */
    public static ArrayList<Actividad> listarActividadesDeCreador(String creador) {

        Entity eSeleccionar = new Entity(0, 2);
        ResultSet rs;
        String[] columnas = {
            ATRIBUTOS[4]
        };

        Object[] condicion = {
            creador
        };
        rs = eSeleccionar.seleccionar(columnas, condicion);

        return listar(rs);
    }

    public boolean modificar(ArrayList camposNM) {

        if (!Verificaciones.verif(this)) {
            return false;
        }
        boolean resp = true;
        Iterator it = camposNM.iterator();

        for (int i = 0; it.hasNext() && resp; i++) {
            CampoValor campoNM = (CampoValor) it.next();
            System.out.println("antes modif " + campoNM.getCampo().getNombre() + " " + resp);
            resp &= camposValores.get(i).modificar(campoNM, idActividad);
            System.out.println("luego modif " + campoNM.getCampo().getNombre() + " " + resp);
        }

        Entity eActividad = new Entity(2, 2);
        fechaModif = Clases.Log.getFechaHora();
        String[] condColumn = {
            ATRIBUTOS[0]
        };
        Object[] condValores = {
            idActividad
        };
        String[] colModif = {
            ATRIBUTOS[3],
            ATRIBUTOS[6],
            ATRIBUTOS[7]
        };
        Object[] modValor = {
            "En espera",
            modificador,
            fechaModif
        };
        resp &= eActividad.modificar(condColumn, condValores, colModif, modValor);

        if (!resp) {
            mensajeError = "Error: No se pudo modificar la Actividad.";
        }
        return resp;
    }

    public static void imprimirLista(ArrayList<String> lista) {
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    public static void main(String args[]) {
        /*Campo c = new Campo("Blah", "Entero", true);
         String prueba = "1989";
         String prueba2 = "Adios1987425";


         Actividad a = new Actividad();
         a.setCreador("alejandro");
         a.setIdTipoActividad(66);
         ArrayList<Actividad> lista = null;

         lista = listarActividades();


         System.out.println("\n\n\nListando todas las actividades");
         imprimirLista(lista);
         lista = a.listarActividadesDeTipo();



         System.out.println("\n\n\nListando todas las actividades del tipo");
         imprimirLista(lista);
         lista = a.listarActividadesDeUsuario();

         System.out.println("\n\n\nListando todas las actividades del usuario");
         imprimirLista(lista);*/

        Actividad a = new Actividad();
        System.out.println("fecha hora " + Clases.Log.getFechaHora());
    }
}
