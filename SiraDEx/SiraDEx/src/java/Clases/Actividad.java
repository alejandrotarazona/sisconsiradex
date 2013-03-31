/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static String[] TABLAS = {
        "ACTIVIDAD", //0
        "PARTICIPA", //1
        "USUARIO" //3
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

    @Override
    public String toString() {
        return "Actividad\n\t{" + "idTipoActividad=" + idTipoActividad 
                + "\n\t idActividad=" + idActividad + "\n\t usbid=" + creador + "}";
    }

    public void setParticipantes(int idAct) {

        String participante = getApellidoNombreCreador();
        System.out.println("apellido y nombre del creador: " + participante);
        participantes.add(participante);
        if (participantes.size() > 1) {
            Entity eBuscar = new Entity(0, 7); //SELECT PARTICIPA
            String[] atrib = {
                ATRIBUTOS[0]
            };

            Object[] valor = {
                idAct
            };
            String[] tabABuscar = {
                "ACTIVIDAD",
                "PARTICIPA",
                "USUARIO"
            };

            ResultSet rs = eBuscar.naturalJoin(ATRIBUTOS, tabABuscar, atrib, valor);
            if (rs != null) {
                try {
                    while (rs.next()) {
                        participante = rs.getString("apellidos") + ", " + rs.getString("nombres");
                        participantes.add(participante);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public String participantesToString() {
        String p = "";
        Iterator it = participantes.iterator();
        while (it.hasNext()) {
            p += (String) it.next() + ", ";
        }
        p = p.substring(0, p.length() - 2) + ".";
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
                case "fecha":
                case "checkbox":
                    if (!valor.equals("false")) {
                        s += cv.getCampo().getNombre() + ", ";
                    }
                    break;
                default:
                    s += valor + ", ";
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
                resp = resp && cv.agregar(this.idActividad);
            }

            return resp;

        }

        Entity ePariticipa = new Entity(1, 7);//INSERT PARTICIPA
        String[] columnas2 = {
            "id_act",
            "usuario"
        };
        if (resp) {
            Iterator itParticipantes = this.participantes.iterator();
            while (resp &= itParticipantes.hasNext()) {
                String participante = (String) itParticipantes.next();
                Object[] tuplaInsertar = {idActividad, participante};

                resp &= ePariticipa.insertar2(columnas2, tuplaInsertar);
            }
        }

        return resp;
    }

    public boolean eliminarActividad() {
        Entity e = new Entity(5, 2);//DELETE ACTIVIDAD
        return e.borrar(ATRIBUTOS[0], idActividad);

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
        String val = "";
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

    //modificado por Jorge el cinco de marzo
    public static ArrayList<Actividad> listarActividades() {
        try {
            ArrayList<Actividad> listaActividad = new ArrayList<>(0);
            Entity eActividad = new Entity(0, 2);//SELECT ACTIVIDAD

            String[] tabABuscar = {
                "ACTIVIDAD",
                "TIPO_ACTIVIDAD"
            };
            try (ResultSet rs = eActividad.naturalJoin(ATRIBUTOS, tabABuscar)) {
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

                        listaActividad.add(a);
                    }
                }
                rs.close();
            }

            return listaActividad;
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //hecho por Alejandro falta revisarlo
    public ArrayList<Actividad> listarActividadesDeTipo() {
        try {
            ArrayList<Actividad> listaActividad = new ArrayList<>(0);
            Entity eActividad = new Entity(0, 2);
            String[] columna = {Actividad.ATRIBUTOS[1]};
            Integer[] condicion = {idTipoActividad};

            ResultSet rs = eActividad.seleccionar(columna, condicion);

            if (rs != null) {
                Entity eTipoAct = new Entity(0, 1);

                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[7]));

                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

                    a.setParticipantes(id);

                    String[] ta = {"nombre_tipo_actividad"};
                    String[] idTipoAct = {"id_tipo_actividad"};
                    Integer[] idAct = {id};
                    try (ResultSet r = eTipoAct.proyectar(ta, idTipoAct, idAct)) {
                        r.next();
                        a.setNombreTipoActividad(r.getString(1));

                        listaActividad.add(a);
                    }
                }

            }
            rs.close();

            return listaActividad;
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Lista las actividades que tiene el usuario.
     *
     * @return Lista con las actividades realizadas por un usuario.
     */
    public ArrayList<Actividad> listarActividadesDeUsuario() {
        ArrayList<Actividad> listaActividad = new ArrayList<>(0);
        Entity eActividad = new Entity(0, 2);
        String[] columna = {Actividad.ATRIBUTOS[4]};
        String[] condicion = {this.creador};

        ResultSet rs = eActividad.seleccionar(columna, condicion);

        Entity eTipoAct = new Entity(0, 1);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[7]));

                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

                    a.setParticipantes(id);

                    String[] ta = {"nombre_tipo_actividad"};
                    String[] idTipoAct = {"id_tipo_actividad"};
                    Integer[] idAct = {id};
                    ResultSet r = eTipoAct.proyectar(ta, idTipoAct, idAct);
                    r.next();
                    a.setNombreTipoActividad(r.getString(1));

                    listaActividad.add(a);
                    r.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("RS NULO");
        }

        eActividad = new Entity(0, 2);
        String[] tabABuscar = {
            TABLAS[0],
            TABLAS[1]
        };
        String[] colCondicion = {TABLAS[1] + ".usbid"};
        String[] colValor = {this.creador};

        rs = eActividad.naturalJoin(ATRIBUTOS, tabABuscar, colCondicion, colValor);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[7]));

                    String[] ta = {"nombre_tipo_actividad"};
                    String[] idTipoAct = {"id_tipo_actividad"};
                    Integer[] idAct = {id};
                    ResultSet r = eTipoAct.proyectar(ta, idTipoAct, idAct);
                    r.next();
                    a.setNombreTipoActividad(r.getString(1));

                    listaActividad.add(a);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {
            System.out.println("RS NULO");
        }


        return listaActividad;
    }

    /**
     * Lista las actividades según su validador.
     *
     * @param validador Nombre de la dependencia que desea listarCampos las
     * actividades a por validar.
     * @return Lista con las actividades realacionadas con el validador,
     */
    public static ArrayList<Actividad> listarActividadesDeValidador(String validador) {
        ArrayList<Actividad> resp = new ArrayList<>(0);
        ArrayList<Actividad> aux0 = listarActividades();
        Iterator it = aux0.iterator();
        Actividad a;
        while (it.hasNext()) {
            a = (Actividad) it.next();
            System.out.println("El validador de la actividad es: " + a.getValidador());
            if (a.getValidador().equalsIgnoreCase(validador)
                    && a.getValidacion().equalsIgnoreCase("En espera")) {
                resp.add(a);
            }
        }

        return resp;
    }

    public boolean modificar(ArrayList camposNM) {
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
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
            return false;
        }
        return resp;
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
