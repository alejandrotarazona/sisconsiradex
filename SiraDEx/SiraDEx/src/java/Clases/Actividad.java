/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private ArrayList<String> participantes;
    private ArrayList<CampoValor> camposValores;
    private static String[] ATRIBUTOS = {
        "id_actividad", //0
        "id_tipo_actividad", //1
        "validacion", //2
        "creador", //3
        "fecha_creacion",//4
        "modificador",//5
        "fecha_modif"//6
    };
    private static String[] TABLAS = {
        "ACTIVIDAD", //0
        "PARTICIPA", //1
        "USUARIO" //2
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

    public void setNombreTipoActividad() {
        try {
            Entity eTipoAct = new Entity(0, 1);
            String[] ta = {"nombre_tipo_actividad"};
            String[] idTipoAct = {"id_tipo_actividad"};
            Integer[] id = {idTipoActividad};
            ResultSet r = eTipoAct.proyectar(ta, idTipoAct, id);
            r.next();
            nombreTipoActividad = r.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public String toString() {
        return "Actividad\n\t{" + "idTipoActividad=" + idTipoActividad + "\n\t idActividad=" + idActividad + "\n\t usbid=" + creador + '}';
    }

    public boolean esActividadUsuario() {
        try {
            Entity e = new Entity(0, 2);

            String[] col = {ATRIBUTOS[0], ATRIBUTOS[3]};
            Object[] condicion = {idActividad, creador};

            ResultSet rs = e.seleccionar(col, condicion);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getInt(ATRIBUTOS[0]) == idActividad
                            && rs.getString(ATRIBUTOS[3]).equals(creador)) {
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean checkValorCampo(Campo campo, String valor) {
        boolean resp = true;

        String tipo_campo = campo.getTipo();
        String[] posibles_tipos = Campo.getTIPOS();

        if (tipo_campo.equals(posibles_tipos[0])) {
            Pattern limpiar = Pattern.compile(".*");
            Matcher buscar = limpiar.matcher(valor);
            resp &= buscar.matches();
            resp &= valor.length() <= campo.getLongitud();

        } else if (tipo_campo.equals(posibles_tipos[1])) {
            Pattern limpiar = Pattern.compile("[0-9]*");
            Matcher buscar = limpiar.matcher(valor);
            resp &= buscar.matches();

        } else if (tipo_campo.equals(posibles_tipos[2])) {
            Pattern limpiar =
                    Pattern.compile("([0-3][0-9])/([0-1][0-9])/([0-9][0-9][0-9][0-9])");
            Matcher buscar = limpiar.matcher(valor);
            resp &= buscar.matches();
            Calendar corroboracion = Calendar.getInstance();
            int dia = Integer.parseInt(buscar.group(1));
            int mes = Integer.parseInt(buscar.group(2));
            int ano = Integer.parseInt(buscar.group(3));
            corroboracion.set(ano, mes, dia);
            resp &= (corroboracion != null);

        } else if (tipo_campo.equals(posibles_tipos[3])) {
            Pattern limpiar =
                    Pattern.compile("([0-2][0-9]:[0-5][0-9]:[0-5][0-9])|([0-1][0-9]:[0-5][0-9]:[0-5][0-9](am|pm))");
            Matcher buscar = limpiar.matcher(valor);
            resp &= buscar.matches();

        } else if (tipo_campo.equals(posibles_tipos[4])) {
            Pattern limpiar = Pattern.compile("true|false");
            Matcher buscar = limpiar.matcher(valor);
            resp &= buscar.matches();
        }

        if (campo.isObligatorio()) {
            resp &= valor.length() > 0;
        }
        resp &= valor.length() < 140;

        return resp;
    }

    public boolean agregarActividad() {
        Entity e = new Entity(1, 2);
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


        Entity eValores = new Entity(1, 6);
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
            resp &= true; //checkValorCampo(campoVerif, valorVerif);
        }

        if (resp = e.insertar2(columnas, actividad)) {

            setIdActividad(e.seleccionarMaxId(ATRIBUTOS[0]));

            itValores = this.camposValores.iterator();

            while ((itValores.hasNext())) {
                CampoValor v = (CampoValor) itValores.next();
                Campo campoActual = (Campo) v.getCampo();
                String valorActual = (String) v.getValor();

                Object[] tupla = {campoActual.getIdCampo(),
                    idActividad,
                    valorActual};
                resp = eValores.insertar(tupla);
            }

            return resp;

        }
        Entity ePariticipa = new Entity(1, 7);
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
        Entity e = new Entity(5, 2);
        return e.borrar(ATRIBUTOS[0], idActividad);

    }

    public static ArrayList<Actividad> listarActividades() {
        ArrayList<Actividad> listaActividad = new ArrayList<>(0);
        Entity eActividad = new Entity(0, 2);

        ResultSet rs = eActividad.listar();

        Entity eTipoAct = new Entity(0, 1);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[2]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

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
        }

        return listaActividad;
    }

    public ArrayList<Actividad> listarActividadesDeTipo() {
        ArrayList<Actividad> listaActividad = new ArrayList<>(0);
        Entity eActividad = new Entity(0, 2);
        String[] columna = {Actividad.ATRIBUTOS[1]};
        Integer[] condicion = {idTipoActividad};

        ResultSet rs = eActividad.seleccionar(columna, condicion);

        if (rs != null) {
            Entity eTipoAct = new Entity(0, 1);
            try {
                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[2]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

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
        }

        return listaActividad;
    }

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

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[2]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[6]));

                    a.setCamposValores(CampoValor.listarCamposValores(a.idActividad));

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

        eActividad = new Entity(0, 2);
        String[] tabABuscar = {
            TABLAS[0],
            TABLAS[1]
        };
        String[] colCondicion = {TABLAS[1] + ".usbid"};
        String[] colValor = {this.creador};

        rs = eActividad.naturalJoins(ATRIBUTOS, tabABuscar, colCondicion, colValor);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Actividad a = new Actividad();

                    a.setIdActividad(rs.getInt(Actividad.ATRIBUTOS[0]));

                    int id = rs.getInt(Actividad.ATRIBUTOS[1]);
                    a.setIdTipoActividad(id);

                    a.setValidacion(rs.getString(Actividad.ATRIBUTOS[2]));

                    a.setCreador(rs.getString(Actividad.ATRIBUTOS[3]));

                    a.setFechaCreacion(rs.getString(Actividad.ATRIBUTOS[4]));

                    a.setModificador(rs.getString(Actividad.ATRIBUTOS[5]));

                    a.setFechaModif(rs.getString(Actividad.ATRIBUTOS[6]));

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

    public static void main(String args[]) {
        Campo c = new Campo("Blah", "Entero", true);
        String prueba = "1989";
        String prueba2 = "Adios1987425";

        boolean ejec = checkValorCampo(c, prueba);
        System.out.println("El resultado de la primera prueba es: " + ejec);

        ejec = checkValorCampo(c, prueba2);
        System.out.println("El resultado de la segunda prueba es: " + ejec);

        Actividad a = new Actividad();
        a.setCreador("alejandro");
        a.setIdTipoActividad(66);
        ArrayList<Actividad> lista = listarActividades();
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
