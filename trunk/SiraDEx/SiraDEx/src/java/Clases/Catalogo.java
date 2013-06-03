/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Catalogo extends Root {

    private int idCatalogo;
    private String nombre;
    private int nroCampos;
    private ArrayList<CampoCatalogo> campos;
    private ArrayList<CampoCatalogo> camposAux; /*atributo auxiliar para agregar
     nuevos campos en el modificar*/

    private boolean participantes; //especifica si es un catalogo de participantes
    private static final String[] ATRIBUTOS = {
        "id_cat",
        "nombre",
        "nro_campos",
        "participa"
    };
    private static final String[] tiposCampos = {//no lo usamos para nada
        "texto", //STRING
        "numero", //INT
        "fecha", //DATE
    };
    public static String[] TABLAS = {//no lo usamos para nada
        "CATALOGO", //0
        "CAMPO_CATALOGO", //1 
        "ELEMENTO" //3
    };
    /* Alejandro por favor agrega código que usemos y sino lo usamos aún 
     * al menos específica donde crees que lo vamos a usar, porque cada ves 
     * veo que agregas más código inútil. Gracias La Gerencia*/

    public static String[] getTiposCampos() {
        return tiposCampos;
    }

    public Catalogo() {
    }

    public static Catalogo leer() throws IOException {
        Catalogo resp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduzca el nombre del catalogo:");
        String name = br.readLine();
        System.out.println("");
        System.out.println("Introduzca el nro de campos: ");
        int nro = Integer.parseInt(br.readLine());
        System.out.println("");

        resp = new Catalogo(name, nro);
        resp.campos = new ArrayList<>();
        for (int i = 0; i < nro; i++) {
            CampoCatalogo cc = CampoCatalogo.leer();
            resp.campos.add(cc);
        }


        return resp;
    }

    public Catalogo(String nombre, int nroCampos) {
        this.nombre = nombre;
        this.nroCampos = nroCampos;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo() {

        Entity eId = new Entity(6);//CATALOGO

        String[] proyectar = {ATRIBUTOS[0]};
        String[] columnas = {
            "nombre"
        };
        Object[] valores = {
            this.nombre
        };
        ResultSet rs = eId.proyectar(proyectar, columnas, valores);
        try {
            if (rs.next()) {

                this.idCatalogo = rs.getInt(ATRIBUTOS[0]);
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Catalogo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public static String getNombre(int idCatalogo) {

        Entity eCatalogo = new Entity(6);//CATALOGO
        String[] nombre = {ATRIBUTOS[1]};
        String[] idCat = {ATRIBUTOS[0]};
        Integer[] id = {idCatalogo};
        ResultSet rs = eCatalogo.proyectar(nombre, idCat, id);
        try {
            rs.next();
            String resp = rs.getString(1);
            rs.close();
            return resp;
        } catch (SQLException ex) {
            Logger.getLogger(Catalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCampos() {
        return nroCampos;
    }

    public void setNroCampos(int nroCampos) {
        this.nroCampos = nroCampos;
    }

    public ArrayList<CampoCatalogo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<CampoCatalogo> campos) {
        this.campos = campos;
    }

    public ArrayList<CampoCatalogo> getCamposAux() {
        return camposAux;
    }

    public void setCamposAux(ArrayList<CampoCatalogo> camposAux) {
        this.camposAux = camposAux;
    }

    public boolean isParticipantes() {
        return participantes;
    }

    public void setParticipantes(boolean participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "nombre=" + nombre + ", nroCampos=" + nroCampos + '}';
    }

    public boolean esCatalogo() {

        Entity e = new Entity(6);//CATALOGO

        String[] atrib = {ATRIBUTOS[1]};
        String[] valor = {nombre};
        ResultSet rs = e.seleccionar(atrib, valor);

        if (rs != null) {
            try {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[1]).equals(nombre)) {
                        return true;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    //teniendo el id hace un set del resto de atributos del Catálogo
    public void setCatalogo() {

        Entity e = new Entity(6);//CATALOGO

        String[] atrib = {ATRIBUTOS[0]};
        Integer[] valor = {idCatalogo};
        ResultSet rs = e.seleccionar(atrib, valor);
        if (rs != null) {
            try {
                rs.next();
                idCatalogo = rs.getInt(ATRIBUTOS[0]);
                nombre = rs.getString(ATRIBUTOS[1]);
                nroCampos = rs.getInt(ATRIBUTOS[2]);
                participantes = rs.getBoolean(ATRIBUTOS[3]);
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean agregarCampos(ArrayList campos) {
        boolean resp = true;
        setIdCatalogo();
        Iterator itCampos;
        if (campos != null) {
            itCampos = campos.iterator();
            while (itCampos.hasNext() && resp) {
                CampoCatalogo cC = (CampoCatalogo) itCampos.next();
                resp &= cC.agregar(idCatalogo);
            }
        }
        return resp;
    }

    public boolean agregar() {

        if (!Verificaciones.verifCF(this)) {
            return false;
        }

        Entity eCatalogo = new Entity(6);//CATALOGO
        boolean resp;

        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3]
        };

        Object[] valores = {
            nombre,
            nroCampos,
            participantes
        };

        eCatalogo.insertar2(columnas, valores);
        resp = agregarCampos(campos);

        return resp;
    }

    public boolean eliminar() {
        Entity eEliminar = new Entity(6);//CATALOGO
        if ( eEliminar.borrar(ATRIBUTOS[0], idCatalogo)) {
            mensaje = "El Catálogo '" + nombre + "' ha sido eliminado";
            return true;
        }

        mensajeError = "Error: No se pudo eliminar el Catálogo '" + nombre + "'.";
        return false;

    }

    //en el parámetro nombreNM recibe el nombre No Modificado del catálogo y en
    //el parámetro camposNM su lista de campos No Modificados
    public boolean modificar(Catalogo catNM) {

        if (!Verificaciones.verifCF(this) || !Verificaciones.verifCV(this)) {
            return false;
        }

        boolean resp;

        Entity e = new Entity(6);//CATALOGO

        String[] condColumnas = {ATRIBUTOS[1], ATRIBUTOS[3]};
        Object[] valores = {catNM.getNombre(), catNM.isParticipantes()};
        String[] colModificar = {ATRIBUTOS[1], ATRIBUTOS[3]};
        Object[] nombreCat = {nombre, participantes};
        if (this.esCatalogo() && !nombre.equals(catNM.getNombre())) {
            mensajeError = "Error: Ya existe un Catálogo con el Nombre '"
                    + "" + nombre + "'.Por favor intente con otro nombre.";
            return false;
        }

        resp = e.modificar(condColumnas, valores, colModificar, nombreCat);

        Iterator it = catNM.getCampos().iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoCatalogo campoNM = (CampoCatalogo) it.next();
            resp &= campos.get(i).modificar(campoNM, idCatalogo);
        }
        
        if (!camposAux.isEmpty()) {
            resp &= agregarCampos(camposAux);
        }
        if (!resp) {
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
        }
        return resp;
    }

    private static ArrayList<Catalogo> listar(ResultSet rs) {

        ArrayList<Catalogo> cats = new ArrayList<>(0);
        try {
            if (rs != null) {
                while (rs.next()) {
                    Catalogo cat = new Catalogo();
                    cat.setIdCatalogo(rs.getInt(ATRIBUTOS[0]));
                    cat.setNombre(rs.getString(ATRIBUTOS[1]));
                    cat.setNroCampos(rs.getInt(ATRIBUTOS[2]));
                    cat.setParticipantes(rs.getBoolean(ATRIBUTOS[3]));

                    cats.add(cat);
                }
                rs.close();
            }
            return cats;
        } catch (SQLException ex) {
            Logger.getLogger(Catalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return lista con los Catálogos que cumplen la condicion dada
     */
    public static ArrayList<Catalogo> listarCondicion(String atributo, Object valor) {

        Entity eListar = new Entity(6);//CATALOGO
        String[] atrib = {atributo};
        Object[] val = {valor};
        ResultSet rs = eListar.seleccionar(atrib, val);
        return listar(rs);
    }

    /**
     *
     * @return lista con los Catálogos que cumplen la condicion dada
     */
    public static ArrayList<Catalogo> listarCatalogos() {

        Entity eListar = new Entity(6);//CATALOGO
        ResultSet rs = eListar.listar();
        return listar(rs);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduzca la cantidad de catalogos a agregar: ");
        int tam = Integer.parseInt(br.readLine());
        System.out.println("");
        Catalogo[] c = new Catalogo[tam];

        for (int i = 0; i < tam; i++) {
            c[i] = Catalogo.leer();
            System.out.println("agregando: " + c[i].toString());
            c[i].agregar();
        }


    }
}
