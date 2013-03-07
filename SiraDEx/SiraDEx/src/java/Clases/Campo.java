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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class Campo implements Serializable {

    private int idCampo;
    private int idTipoActividad;
    private String nombre;
    private String tipo;
    private int longitud;
    private boolean obligatorio;
    private String catalogo = "";
    private boolean lista;
    private final Par[] tipos = {
            new Par("texto", "texto"),
            new Par("catálogo", "catalogo"),
            new Par("fecha", "fecha"),
            new Par("número", "numero"),
            new Par("texto largo", "textol"),
            new Par("archivo", "archivo"),
            new Par("checkbox", "checkbox")
        }; 
        
    public static class Par implements Serializable {

        private String etiqueta;
        private String valor;

        public Par(String etiqueta, String valor) {
            this.etiqueta = etiqueta;
            this.valor = valor;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public String getValor() {
            return valor;
        }
    }
    
    private static final String[] TIPOS = {
        "texto",
        "número",
        "fecha",
        "archivo",
        "checkbox",
        "texto largo",
        "catálogo"
    };
    private static final String[] ATRIBUTOS = {
        "id_campo",
        "id_tipo_actividad",
        "nombre_campo",
        "tipo_campo",
        "longitud",
        "obligatorio",
        "catalogo"
    };

    public Campo(String nombre, String tipo, boolean obligatorio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.obligatorio = obligatorio;
    }

    public Campo(String nombre, String tipo, int longitud, boolean obligatorio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.longitud = longitud;
        this.obligatorio = obligatorio;
    }

    public Campo() {
    }

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public static String[] getTIPOS() {
        return TIPOS;
    }

    public boolean isNombreInvalido() {
        boolean resp = nombre.equals("");
        return resp;
    }

    public boolean isLista() {
        return lista;
    }

    public void setLista(boolean lista) {
        this.lista = lista;
    }

    public Par[] getTipos() {
        return tipos;
    }
     
    @Override
    public String toString() {
        return "Campos{" + "nombre=" + nombre + ", tipo=" + tipo + ", longitud=" + longitud + ", obligatorio=" + obligatorio + '}';
    }

    public boolean agregarCampo(int idTipoActividad) {
        System.out.println("Agrego un Campo");
        Entity e = new Entity(1, 3);
        Integer idTA = new Integer(idTipoActividad);
        Object[] valores = {
            idTA,
            nombre,
            tipo,
            longitud,
            obligatorio,
            catalogo
        };
        String[] columnas = {
            ATRIBUTOS[1],
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6]
        };
        boolean resp = e.insertar2(columnas, valores);
        return resp;

    }

    public static ArrayList<Campo> listar(int idTA) {

        ArrayList<Campo> resp = new ArrayList<>(0);
        Entity eListar = new Entity(0, 3);//SELECT CAMPO

        String[] columnas = {
            ATRIBUTOS[1]
        };
        Object[] valores = {
            idTA
        };

        ResultSet rs = eListar.seleccionar(columnas, valores);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Campo c = new Campo();
                    c.setIdCampo(rs.getInt(ATRIBUTOS[0]));
                    c.setIdTipoActividad(rs.getInt(ATRIBUTOS[1]));
                    c.setNombre(rs.getString(ATRIBUTOS[2]));
                    c.setTipo(rs.getString(ATRIBUTOS[3]));
                    c.setLongitud(rs.getInt(ATRIBUTOS[4]));
                    c.setObligatorio(rs.getBoolean(ATRIBUTOS[5]));
                    c.setCatalogo(rs.getString(ATRIBUTOS[6]));
                    resp.add(c);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TipoActividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Campo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

    //en el parámetro campo recibe un campo no modificado del tipo de actividad
    public boolean modificar(Campo campo, int idTA) {
        Entity e = new Entity(2, 3);

        String[] condColumnas = ATRIBUTOS;
        Object[] valores = {
            idCampo,
            idTA,
            campo.getNombre(),
            campo.getTipo(),
            campo.getLongitud(),
            campo.isObligatorio(),
            campo.getCatalogo()
        };
        String[] colModificar = {
            ATRIBUTOS[2],
            ATRIBUTOS[3],
            ATRIBUTOS[4],
            ATRIBUTOS[5],
            ATRIBUTOS[6]
        };

        Object[] modificaciones = {
            nombre,
            tipo,
            longitud,
            obligatorio,
            catalogo
        };

        return e.modificar(condColumnas, valores, colModificar, modificaciones);
    }
}
