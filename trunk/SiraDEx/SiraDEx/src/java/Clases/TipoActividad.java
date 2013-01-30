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
import org.apache.struts.action.ActionForm;

/**
 *
 * @author SisCon
 */
public class TipoActividad extends ActionForm {
    private int id;
    private String nombreTipo;
    private String tipoPR;
    private int nroCampos;
    private String descripcion;
    private ArrayList permiso;
    private String programa;
    private String validador;
    private String producto;
    private boolean activo;
    private ArrayList<Campo> campos; 
    private String mensaje;
    private static final String[] ATRIBUTOS = {
        "id_tipo_actividad", //0
        "nombre_tipo_actividad", //1
        "tipo_p_r", //2
        "nro_campos", //3
        "descripcion", //4
        "programa", //5
        "validador", //6
        "producto", //7
        "activo" //8
    };

    private static final String[] tiposCampos = {
        "texto",   //STRING
        "numero",  //INT
        "fecha",   //DATE
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPR() {
        return tipoPR;
    }

    public void setTipoPR(String tipoPR) {
        this.tipoPR = tipoPR;
    }

    public ArrayList getPermiso() {
        return permiso;
    }

    public void setPermiso(ArrayList permiso) {
        this.permiso = permiso;
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

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public void setId() {
        Entity eId = new Entity(0, 1);
        
        String[] seleccionar = {ATRIBUTOS[0]};
        
        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[8]
        };
        
        Object[] valores = {
            nombreTipo,
            activo
        };
        
        ResultSet rs = eId.proyectar(seleccionar, columnas, valores);
        if(rs != null){
            try {
                if(rs.next()){
                        this.id = rs.getInt(ATRIBUTOS[0]);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }       
        }
    }

    @Override
    public String toString() {
        return "TipoActividad{" + "nombreTipo=" + nombreTipo + ", nroCampos=" + nroCampos + ", descripcion=" + descripcion + " }";
    }

    public boolean esTipoActividad() {
        try {
            Entity e = new Entity(0, 1);

            String[] atrib = {ATRIBUTOS[1]};
            String[] valor = {nombreTipo};
            ResultSet rs = e.seleccionar(atrib, valor);
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(ATRIBUTOS[1]).equals(nombreTipo)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean agregarTipoActividad() {

        Entity e = new Entity(1, 1);
        boolean resp = true;

        Object[] valores = {nombreTipo, nroCampos, descripcion};
        if (resp &= esTipoActividad()) {
            this.mensaje = "No se puede registrar el tipo de actividad."
                    + "Ya existe un tipo de actividad de nombre '"
                    + this.nombreTipo + "'.Por favor elija otro nombre.";
            return !resp;
        } else {
            System.out.println("No ha sido insertada previamente");
            String[] aInsertar = {
                ATRIBUTOS[1],
                ATRIBUTOS[2],
                ATRIBUTOS[3]
            };

            if (resp = e.insertar2(aInsertar, valores)) {
                this.setId();
                System.out.println("Ya inserte el tipo de Actividad");
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
            } else {
                this.eliminarTipoActividad();
            }
        }

        return resp;
    }

    public boolean eliminarTipoActividad() {
        Entity eTipoActividad = new Entity(5, 1);

        if (esTipoActividad()) {

            eTipoActividad.borrar(ATRIBUTOS[1], nombreTipo);

            return true;
        } else {
            
            return false;
        }

    }

    public static ArrayList<TipoActividad> listarTiposActividad() {
        Entity eListar = new Entity(0, 1);
        ResultSet rs = eListar.listar();
        ArrayList<TipoActividad> tipos = new ArrayList<TipoActividad>(0);

        if (rs != null) {
            try {
                while (rs.next()) {
                    TipoActividad t = new TipoActividad();
                    t.setId(rs.getInt(ATRIBUTOS[0]));
                    t.setNombreTipo(rs.getString(ATRIBUTOS[1]));
                    t.setNroCampos(rs.getInt(ATRIBUTOS[2]));
                    t.setDescripcion(rs.getString(ATRIBUTOS[3]));
                    
                    tipos.add(t);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tipos;
    }

    public boolean modificarTipoActividad(String viejoNombre) {
        boolean respuesta = true;
        Entity e = new Entity(2, 1);

        if (respuesta &= this.esTipoActividad()) {

            String[] columnaCondicion = {TipoActividad.ATRIBUTOS[1]};
            String[] condicion = {viejoNombre};
            Integer numeroDeCampos = new Integer(this.nroCampos);
            Object[] modificaciones = {this.nombreTipo, numeroDeCampos, this.descripcion, this.permiso};

            if (respuesta &= e.modificar(columnaCondicion, condicion, TipoActividad.ATRIBUTOS, modificaciones)) {
                this.setMensaje("Cambio realizado con exito");
                return respuesta;
            } else {
                this.setMensaje("El cambio no se pudo realizar");
                return respuesta;
            }

        } else {
            this.setMensaje("No existe ese tipo de Actividad");
            return respuesta;
        }
    }

    public static void main(String[] args) {
        //TipoActividad t = new TipoActividad("pasantia", 3, "pasantia");
        //t.agregarTipoActividad();

        TipoActividad t = new TipoActividad("prueba1", 2, "pasantia");
        ArrayList<Campo> cs = new ArrayList<Campo>();

        Campo c0 = new Campo("Nombre", "texto", 16, true);
        Campo c1 = new Campo("Apellido", "texto", 16, false);
        cs.add(c0);
        cs.add(c1);
        t.setCampos(cs);

        if (t.agregarTipoActividad()) {
            System.out.println("\n\n\nExito");
        } else {
            System.out.println("\n\n\nFracaso");
    }
//        t.eliminarTipoActividad();


}
}
