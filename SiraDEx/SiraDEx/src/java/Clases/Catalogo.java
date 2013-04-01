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
    
    /*atributo auxiliar para agregar nuevos campos en el modificar*/
    private ArrayList<CampoCatalogo> camposAux;
                                                
    private static final String[] ATRIBUTOS = {
        "id_cat",
        "nombre",
        "nro_campos"
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
        try {
            Entity eId = new Entity(0, 8);

                String[] proyectar = {ATRIBUTOS[0]};
                String[] columnas = {
                    "nombre"
                };
                Object[] valores = {
                    this.nombre
                };
                ResultSet rs = eId.proyectar(proyectar, columnas, valores);

                if (rs.next()) {

                        this.idCatalogo = rs.getInt(ATRIBUTOS[0]);
                
                }
                rs.close();
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
        try {
            Entity eCatalogo = new Entity(0, 8);
            String[] nombre = {"nombre"};
            String[] idCat = {"id_cat"};
            Integer[] id = {idCatalogo};
            ResultSet r = eCatalogo.proyectar(nombre, idCat, id);
            r.next();
            String resp = r.getString(1);
            r.close();
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

    @Override
    public String toString() {
        return "Catalogo{" + "nombre=" + nombre + ", nroCampos=" + nroCampos + '}';
    }

    public boolean esCatalogo() {

        try {
            Entity e = new Entity(0, 8);

            String[] atrib = {ATRIBUTOS[1]};
            String[] valor = {nombre};
            ResultSet rs = e.seleccionar(atrib, valor);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[1]).equals(nombre)) {
                        return true;
                    }
                }
            }
            rs.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean agregarCampos(ArrayList campos) {
        boolean resp = true;
        setIdCatalogo();
        Iterator itCampos;
        if (campos != null) {
            itCampos = campos.iterator();
            while (itCampos.hasNext() && resp) {
                CampoCatalogo cC = (CampoCatalogo) itCampos.next();
                resp &= cC.agregarCampo(idCatalogo);
            }
        }
        return resp;
    }

    public boolean agregar() {
        
        if (!Verificaciones.verifCF(this)) {
            return false;
        }
        
        Entity eCatalogo = new Entity(1, 8);//INSERT CATALOGO
        boolean resp;

        String[] columnas = {
            "nombre",
            "nro_campos"
        };
        Integer nCampos = new Integer(this.nroCampos);
        Object[] valores = {
            this.nombre,
            nCampos
        };

        if (this.esCatalogo()) {
            return false;
        } else {
            resp = eCatalogo.insertar2(columnas, valores);
            if (resp) {
                resp = agregarCampos(campos);
            }
        }
        return resp;
    }

    public static ArrayList<Catalogo> listar() {
        try {
            Entity eListar = new Entity(0, 8);//SELECT CATALOGO
            ResultSet rs = eListar.listar();
            ArrayList<Catalogo> tipos = new ArrayList<>(0);

            if (rs != null) {
                    while (rs.next()) {
                        Catalogo t = new Catalogo();
                        t.setIdCatalogo(rs.getInt(ATRIBUTOS[0]));
                        t.setNombre(rs.getString(ATRIBUTOS[1]));
                        t.setNroCampos(rs.getInt(ATRIBUTOS[2]));

                        tipos.add(t);
                    }
            }
            rs.close();
            return tipos;
        } catch (SQLException ex) {
            Logger.getLogger(Catalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean eliminar(int idCat) {
        Entity eEliminar = new Entity(5, 8);
        return eEliminar.borrar(ATRIBUTOS[0], idCat);

    }
    
    //en el parámetro nombreNM recibe el nombre No Modificado del catálogo y en
    //el parámetro camposNM su lista de campos No Modificados
    public boolean modificar(String nombreNM, ArrayList camposNM,
            ArrayList camposNuevos) {
        boolean resp;

        Entity e = new Entity(2, 8);

        String[] condColumnas = {ATRIBUTOS[1]};
        Object[] valores = {nombreNM};
        String[] colModificar = {ATRIBUTOS[1]};
        String[] nombreCat = {nombre};
        if (this.esCatalogo() && !nombre.equals(nombreNM)) {
            mensaje = "Error: Ya existe un catálogo llamado "
                    + "" + nombre + ".\n Intente con otro nombre.";
            return false;
        }
        if (nombreNM.equals("Coordinaciones") && !nombre.equals(nombreNM)) {
            mensaje = "Error: El nombre del catálogo Coordinaciones no puede"
                    + " ser modificado.\n Solo se permite modificar el nombre de"
                    + " sus campos.";
            return false;
        }
        if (nombreNM.equals("Programas") && !nombre.equals(nombreNM)) {
            mensaje = "Error: El nombre del catálogo Programas no puede"
                    + " ser modificado.\n Solo se permite modificar el nombre de"
                    + " sus campos.";
            return false;
        }
        resp = e.modificar(condColumnas, valores, colModificar, nombreCat);

        Iterator it = camposNM.iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoCatalogo campoNM = (CampoCatalogo) it.next();
            resp &= campos.get(i).modificar(campoNM, idCatalogo);
        }
        //En modificar se usa nroCampos para agregar nuevos campos
        if (nroCampos != 0) {
            resp &= agregarCampos(camposAux);
        }
        if (!resp) {
            mensaje = "Error del sistema al intentar actualizar la base de datos.";
        }
        return resp;
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
