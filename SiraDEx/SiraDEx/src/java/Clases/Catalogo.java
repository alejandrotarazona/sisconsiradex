/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.IOException;
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
    private ArrayList<CampoCatalogo> campos = new ArrayList<>();
    private boolean participantes; //especifica si es un catalogo de participantes
    private static final String[] ATRIBUTOS = {
        "id_cat",
        "nombre",
        "participa"
    };

    public int getIdCatalogo() {
        return idCatalogo;
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

    public boolean agregar(String ip, String user) {

        if (!participantes && campos.isEmpty()) {
            mensaje = "Error: El Catálogo debe tener al menos un campo.";
            return false;
        }

        if (!Verificaciones.verificarCamposFijos(this)
                || !Verificaciones.verificarCamposVariables(this)) {
            return false;
        }

        if (esCatalogo()) {
            mensaje = "Error: Ya existe un Catálogo con el Nombre '"
                    + nombre + "'. Por favor intente con otro nombre.";
            return false;
        }

        if (isParticipantes()) {
            CampoCatalogo c = new CampoCatalogo();
            c.setNombre("USB-ID");
            c.setTipo("usbid");
            campos.add(c);
            c = new CampoCatalogo();
            c.setNombre("Nombre");
            c.setTipo("usuario");
            campos.add(c);
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

        boolean resp;
        if (resp = eCatalogo.insertar2(columnas, valores)) {
            eCatalogo.setIp(ip);
            eCatalogo.setUser(user);
            eCatalogo.insertarLog();
            mensaje = "El Catálogo '" + nombre + "' ha sido registrado con éxito.";
            idCatalogo = eCatalogo.seleccionarMaxId(ATRIBUTOS[0]);

            Iterator itCampos = campos.iterator();
            while (itCampos.hasNext() && resp) {
                CampoCatalogo campo = (CampoCatalogo) itCampos.next();
                if (!campo.isEliminado()) {
                    resp &= campo.agregar(idCatalogo, ip, user);
                }
            }
            if (!resp) {
                mensaje = "Error: El Catálogo '" + nombre + "'no pudo ser registrado.";
                if (!eliminar(ip, user)) {
                    mensaje = " Error: El Catálogo '" + nombre + "' no pudo ser "
                            + "resgistrado satisfactoriamente, en caso "
                            + "de que aparezca, por favor, elimínelo.";
                }
            }
        } else {
            mensaje = "Error: El Catálogo '" + nombre + "' no pudo ser registrado.";
        }

        return resp;
    }

    // los agrega al form para ser enviados desde el action a la vista
    public void agregarCamposNuevos() {
        for (int i = 0; i < nroCampos; i++) {
            CampoCatalogo c = new CampoCatalogo();
            campos.add(c);
        }
    }

    // los agrega a la base de datos al modificar el catálogo
    private boolean agregarCamposNuevos(int nroCamposCatNM, String ip, String user) {
        boolean resp = true;

        Iterator itCampos = campos.iterator();
        for (int i = 0; i < nroCamposCatNM; i++) {
            itCampos.next();
        }

        while (itCampos.hasNext() && resp) {
            CampoCatalogo campo = (CampoCatalogo) itCampos.next();
            if (!campo.isEliminado()) {
                resp &= campo.agregar(idCatalogo, ip, user);
            }
        }
        return resp;
    }

    public boolean eliminar(String ip, String user) {
        Entity eEliminar = new Entity(6);//CATALOGO
        if (eEliminar.borrar(ATRIBUTOS[0], idCatalogo)) {
            eEliminar.setIp(ip);
            eEliminar.setUser(user);
            eEliminar.insertarLog();
            mensaje = "El Catálogo '" + nombre + "' ha sido eliminado con éxito.";
            return true;
        }

        mensaje = "Error: No se pudo eliminar el Catálogo '" + nombre + "'.";
        return false;

    }

    private boolean verificarEliminacionCampos() {
        for (CampoCatalogo campo : campos) {
            if (!campo.isEliminado()) {
                return true;
            }
        }
        mensaje = "Error: El Catálogo debe tener al menos un campo.";
        return false;
    }

    //elimina los campos marcados para ser eliminados, retorna 0 si no habían campos que eliminar,
    //de lo contrario retorna 1, y 2 si ocurre un error.
    public int eliminarCamposMarcados(Catalogo catNM) {


        if ((catNM == null && !isParticipantes() && campos.size() > 0)
                || (catNM != null && !catNM.isParticipantes()
                && !isParticipantes() && campos.size() > 0)) {
            if (!verificarEliminacionCampos()) {
                return 2;
            }
        }

        int resp = 0;
        int i = 0;
        if (catNM != null && catNM.campos.size() > 0) {
            ArrayList<CampoCatalogo> camposNM = catNM.getCampos();
            String elimBD = "";
            for (; i < catNM.campos.size(); i++) {//campos registrados en la base de datos
                CampoCatalogo cc = campos.get(i);
                if (cc.isEliminado()) {
                    resp = 1;
                    if (!cc.eliminar()) {//se elimina de la base de datos
                        return 2;
                    }
                    camposNM.remove(i); //se elimina de campos del atributo catNM
                    campos.remove(i);   //se elimina de campos del form
                    i--;
                    elimBD += " '" + cc.getNombre() + "',";
                }
            }
            if (!elimBD.isEmpty()) {
                mensaje = "Los campos" + elimBD.substring(0, elimBD.length() - 1)
                        + " han sido eliminados con éxito del Catálogo.";
            }
        }
        for (; i < campos.size(); i++) {//campos no registrados en la base de datos
            if (campos.get(i).isEliminado()) {
                resp = 1;
                campos.remove(i);   //se elimina de campos del form
                i--;
            }
        }

        return resp;
    }

    //en el parámetro nombreNM recibe el nombre No Modificado del catálogo y en
    //el parámetro camposNM su lista de campos No Modificados
    public boolean modificar(Catalogo catNM, String ip, String user) {

        if (!Verificaciones.verificarCamposFijos(this)
                || !Verificaciones.verificarCamposVariables(this)) {
            return false;
        }

        if (this.esCatalogo() && !nombre.equals(catNM.getNombre())) {
            mensaje = "Error: Ya existe un Catálogo llamado '"
                    + "" + nombre + "'. Por favor, intente con otro nombre.";
            return false;
        }

        if (!catNM.isParticipantes() && participantes) {//el catálogo ahora es de usuarios
            CampoCatalogo c = new CampoCatalogo();
            c.setNombre("USB-ID");
            c.setTipo("usbid");
            campos.add(c);
            c = new CampoCatalogo();
            c.setNombre("Nombre");
            c.setTipo("usuario");
            campos.add(c);
        }

        Iterator it;
        if (catNM.isParticipantes() && !participantes) {//el catálogo ahora no es de usuarios
            it = campos.iterator();
            while (it.hasNext()) {
                CampoCatalogo campo = (CampoCatalogo) it.next();
                String tipo = campo.getTipo();
                if (tipo.equals("usbid") || tipo.equals("usuario")) {
                    campo.setTipo("texto");
                }
            }
        }

        Entity eCatalogo = new Entity(6);//CATALOGO

        String[] condColumnas = {ATRIBUTOS[0]};
        Object[] valores = {idCatalogo};
        String[] colModificar = {ATRIBUTOS[1], ATRIBUTOS[2]};
        Object[] nombreCat = {nombre, participantes};

        boolean resp;
        if (resp = eCatalogo.modificar(condColumnas, valores, colModificar, nombreCat)) {
            mensaje = "El Catálogo '" + nombre + "' ha sido modificado con éxito.";

            int tam = catNM.getCampos().size();
            for (int i = 0; i < tam && resp; i++) {
                CampoCatalogo campo = (CampoCatalogo) campos.get(i);
                resp &= campo.modificar(idCatalogo);
            }

            resp &= agregarCamposNuevos(catNM.campos.size(), ip, user);

            if (!resp) {
                mensaje = "Error: El Catálogo no pudo ser modificado satisfactoriamente.";
            }

        } else {
            mensaje = "Error: El Catálogo no pudo ser modificado.";
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
    }
}
