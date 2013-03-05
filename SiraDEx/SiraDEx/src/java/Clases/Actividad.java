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
    private String creador;
    private String fechaCreacion;
    private String modificador;
    private String fechaModif;
    private String producto;
    private ArrayList<String> participantes;
    private ArrayList<CampoValor> camposValores;
    private String descripcion;
    private static String[] ATRIBUTOS = {
        "id_actividad", //0
        "id_tipo_actividad", //1
        "nombre_tipo_actividad", //2
        "validacion", //3
        "creador", //4
        "fecha_creacion",//5
        "modificador",//6
        "fecha_modif",//7
        "producto",//8
        "descripcion"//9
    };
    private static String[] TABLAS = {
        "ACTIVIDAD", //0
        "PARTICIPA", //1
        "USUARIO" //3
    };

    public Actividad(int idActividad, String validacion,
            String evaluacion) {
        this.idActividad = idActividad;
        this.validacion = validacion;
    }

    public Actividad() {
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

    public CampoValor getCampoValor(int i) {
        return camposValores.get(i);
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

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Actividad\n\t{" + "idTipoActividad=" + idTipoActividad + "\n\t idActividad=" + idActividad + "\n\t usbid=" + creador + '}';
    }

    public void setActividad() {
        try {
            Entity eActividad = new Entity(0, 2);//SELECT ACTIVIDAD

            String[] tabABuscar = {
                "ACTIVIDAD",
                "TIPO_ACTIVIDAD"
            };
            try (ResultSet rs = eActividad.naturalJoin(ATRIBUTOS, tabABuscar)) {
                if (rs != null) {
                      rs.next();
                      idActividad = rs.getInt(ATRIBUTOS[0]);
                      idTipoActividad = rs.getInt(ATRIBUTOS[1]);
                      nombreTipoActividad = rs.getString(ATRIBUTOS[2]);
                      validacion = rs.getString(ATRIBUTOS[3]);
                      creador = rs.getString(ATRIBUTOS[4]);
                      fechaCreacion = rs.getString(ATRIBUTOS[5]);
                      modificador = rs.getString(ATRIBUTOS[6]);
                      fechaModif = rs.getString(ATRIBUTOS[7]);
                      producto = rs.getString(ATRIBUTOS[8]);
                      descripcion = rs.getString(ATRIBUTOS[9]);
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
        Entity e = new Entity(1, 2);//SELECT ACTIVIDAD
        boolean resp;

        String[] columnas = {
            "id_tipo_actividad",
            "validacion",
            "creador",
            "fecha_creacion",
            "modificador",
            "fecha_modif"
        };

        Object[] actividad = {
            idTipoActividad,
            validacion,
            creador,
            fechaCreacion,
            modificador,
            fechaModif
        };


        Iterator itValores = this.camposValores.iterator();
        int i = 0;
        while (resp = (itValores.hasNext())) {

            System.out.println("El campo " + camposValores.get(i).getCampo().getNombre());
            System.out.println("El valor " + camposValores.get(i).getValor());
            i++;
            CampoValor v = (CampoValor) itValores.next();
            Campo campoVerif = (Campo) v.getCampo();
            String valorVerif = (String) v.getValor();

            if (campoVerif.isObligatorio() && valorVerif.equals("")) {
                mensaje = "Todo campo obligatorio debe ser llenado";
                return false;
            }
            resp &= Verificaciones.verif(campoVerif, valorVerif);
        }

        if (resp = e.insertar2(columnas, actividad)) {

            setIdActividad(e.seleccionarMaxId(ATRIBUTOS[0]));

            itValores = this.camposValores.iterator();

            while ((itValores.hasNext())) {
                CampoValor v = (CampoValor) itValores.next();
                resp = resp && v.agregar(this.idActividad);
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
                String participa = (String) itParticipantes.next();
                String id = String.valueOf(this.idActividad);
                String[] tuplaInsertar = {id, participa};

                resp &= ePariticipa.insertar2(columnas2, tuplaInsertar);
            }
        }

        return resp;
    }

    public boolean eliminarActividad() {
        Entity e = new Entity(5, 2);//DELETE ACTIVIDAD
        return e.borrar(ATRIBUTOS[0], idActividad);

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

                        a.setProducto(rs.getString(ATRIBUTOS[8]));

                        a.setDescripcion(rs.getString(ATRIBUTOS[9]));

                        a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

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

    //hecho por Alejandro falta revisarlo
    public ArrayList<Actividad> listarActividadesDeUsuario() {
        ArrayList<Actividad> listaActividad = new ArrayList<>(0);
        Entity eActividad = new Entity(0, 2);
        String[] columna = {Actividad.ATRIBUTOS[3]};
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
            try {
                rs.close();
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

    public static void imprimirLista(ArrayList<Actividad> lista) {
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            Actividad a = (Actividad) it.next();
            System.out.println(a.getIdActividad());

        }
    }

    public boolean modificar(ArrayList camposNM) {
        boolean resp = true;

        Iterator it = camposNM.iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoValor campoNM = (CampoValor) it.next();
            resp &= camposValores.get(i).modificar(campoNM, idActividad);
        }

        if (!resp) {
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
        }
        return resp;
    }

    public static void main(String args[]) {
        Campo c = new Campo("Blah", "Entero", true);
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
        imprimirLista(lista);
    }
}
