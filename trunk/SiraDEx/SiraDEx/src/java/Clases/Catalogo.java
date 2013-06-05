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
    private boolean participantes; //especifica si es un catalogo de participantes
    private static final String[] ATRIBUTOS = {
        "id_cat",
        "nombre",
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

    public boolean isParticipantes() {
        return participantes;
    }

    public void setParticipantes(boolean participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "nombre=" + nombre + ", participantes=" + participantes + '}';
    }

    public boolean esCatalogo() {

        Entity eCatalogo = new Entity(6);//CATALOGO

        String[] atrib = {ATRIBUTOS[1]};
        String[] valor = {nombre};
        ResultSet rs = eCatalogo.seleccionar(atrib, valor);

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

        Entity eCatalogo = new Entity(6);//CATALOGO

        String[] atrib = {ATRIBUTOS[0]};
        Integer[] valor = {idCatalogo};
        ResultSet rs = eCatalogo.seleccionar(atrib, valor);
        if (rs != null) {
            try {
                rs.next();
                idCatalogo = rs.getInt(ATRIBUTOS[0]);
                nombre = rs.getString(ATRIBUTOS[1]);
                participantes = rs.getBoolean(ATRIBUTOS[2]);
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean agregar() {

        if (!Verificaciones.verifCF(this)) {
            return false;
        }

        Entity eCatalogo = new Entity(6);//CATALOGO

        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2]
        };

        Object[] valores = {
            nombre,
            participantes
        };

        boolean resp = eCatalogo.insertar2(columnas, valores);
        resp &= agregarCampos();

        return resp;
    }

    private boolean agregarCampos() {
        boolean resp = true;
        setIdCatalogo();
        Iterator itCampos = campos.iterator();
        while (itCampos.hasNext() && resp) {
            CampoCatalogo campo = (CampoCatalogo) itCampos.next();
            if (!campo.isEliminado()) {
                resp &= campo.agregar(idCatalogo);
            }
        }
        return resp;
    }

    // los agregar al form para ser enviados desde el action a la vista
    public void agregarCamposNuevos() {
        for (int i = 0; i < nroCampos; i++) {
            CampoCatalogo c = new CampoCatalogo();
            campos.add(c);
        }
    }

    // los agrega a la base de datos al modificar el catálogo
    private boolean agregarCamposNuevos(int nroCamposCatNM) {
        boolean resp = true;
        setIdCatalogo();
        Iterator itCampos = campos.iterator();
        for (int i = 0; i < nroCamposCatNM; i++) {
            itCampos.next();
        }

        while (itCampos.hasNext() && resp) {
            CampoCatalogo campo = (CampoCatalogo) itCampos.next();
            if (!campo.isEliminado()) {
                resp &= campo.agregar(idCatalogo);
            }
        }
        return resp;
    }

    public boolean eliminar() {
        Entity eEliminar = new Entity(6);//CATALOGO
        if (eEliminar.borrar(ATRIBUTOS[0], idCatalogo)) {
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

        if (this.esCatalogo() && !nombre.equals(catNM.getNombre())) {
            mensajeError = "Error: Ya existe un Catálogo con el Nombre '"
                    + "" + nombre + "'.Por favor, intente con otro nombre.";
            return false;
        }

        if (!catNM.isParticipantes() && participantes) {//se cambia el catalogo a uno de usuarios
            CampoCatalogo c = new CampoCatalogo();
            c.setNombre("USB-ID");
            c.setTipo("usbid");
            campos.add(c);
        }

        Iterator it;
        if (catNM.isParticipantes() && !participantes) {//se cambia el catalogo de usuarios a uno normal
            it = campos.iterator();
            while (it.hasNext()) {
                CampoCatalogo campo = (CampoCatalogo) it.next();
                if (campo.getTipo().equals("usbid")) {
                    campo.setTipo("texto");
                    break;
                }
            }
        }

        Entity eCatalogo = new Entity(6);//CATALOGO

        String[] condColumnas = {ATRIBUTOS[1], ATRIBUTOS[2]};
        Object[] valores = {catNM.getNombre(), catNM.isParticipantes()};
        String[] colModificar = {ATRIBUTOS[1], ATRIBUTOS[2]};
        Object[] nombreCat = {nombre, participantes};

        boolean resp = eCatalogo.modificar(condColumnas, valores, colModificar, nombreCat);

        it = campos.iterator();
        Iterator itNM = catNM.getCampos().iterator();
        while (itNM.hasNext()) {
            CampoCatalogo campoNM = (CampoCatalogo) itNM.next();
            CampoCatalogo campo = (CampoCatalogo) it.next();
            resp &= campo.modificar(campoNM, idCatalogo);
        }

        resp &= agregarCamposNuevos(catNM.campos.size());

        if (!resp) {
            mensaje = "Error: No se pudo modificar el Catálogo satisfactoriamente.";
        }
        return resp;
    }

    private boolean eliminarTodosLosCampos() {
        boolean resp = true;

        Iterator it = campos.iterator();
        while (it.hasNext()) {
            CampoCatalogo campo = (CampoCatalogo) it.next();
            if (!campo.isEliminado()) {
                resp = false;
            }
        }
        if (resp) {
            mensajeError = "Error: El Catálogo debe conservar al menos un campo";
        }
        return resp;
    }

    //elimina los campos marcados para ser eliminados, retorna 0 si no habían campos que eliminar,
    //de lo contrario retorna 1, y -1 si ocurre un error.
    public int eliminarCamposMarcados(Catalogo catNM) {

        if (eliminarTodosLosCampos()) {
            return -1;
        }

        ArrayList<CampoCatalogo> camposNM = catNM.getCampos();
        boolean bien = true;
        int resp = 0;
        int i = 0;
        for (; i < catNM.campos.size(); i++) {//campos registrados en la base de datos
            if (campos.get(i).isEliminado() && bien) {
                resp = 1;
                bien &= campos.get(i).eliminar();//se elimina de la base de datos
                camposNM.remove(i); //se elimina de campos del atributo catNM
                campos.remove(i);   //se elimina de campos del form
                i--;
            }
        }
        for (; i < campos.size(); i++) {//campos no registrados en la base de datos
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + i);
            if (campos.get(i).isEliminado()) {
                resp = 1;
                campos.remove(i);   //se elimina de campos del form
                i--;
            }
        }
        if (!bien) {
            resp = -1;
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
                    cat.setParticipantes(rs.getBoolean(ATRIBUTOS[2]));

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
