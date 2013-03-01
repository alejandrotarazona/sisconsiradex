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

/**
 *
 * @author SisCon
 */
public class CampoCatalogoValor implements Serializable {

    private CampoCatalogo campo;
    private String valor;
    private static String[] TABLAS = {
        "VALOR_CATALOGO", //0
        "CAMPO_CATALOGO", //1
        "ELEMENTO_CATALOGO" //2
    };
    
    private static String[] ATRIBUTOS = {
        "id_campo", //0
        "id_elemento", //1
        "valor", //2
    };
    
    public CampoCatalogoValor() {
    }

    public CampoCatalogoValor(CampoCatalogo campo) {
        this.campo = campo;
    }

    public CampoCatalogo getCampo() {
        return campo;
    }

    public void setCampo(CampoCatalogo campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean agregar(int idElemento) {
        boolean resp = true;
        Integer elementoId = new Integer(idElemento);

        Entity eValor = new Entity(1, 11);
        String[] ATRIBUTOS = {
            "id_campo",
            "id_elemento",
            "valor"
        };
        Object[] valores = {
            campo.getIdCampo(),
            elementoId,
            valor
        };

        resp &= eValor.insertar2(ATRIBUTOS, valores);

        return resp;
    }

    //Crea una lista de CampoCatalogoValor para agregar un nuevo elemento al 
    //catalogo cuyo id es pasado por parametro, solo los campos son seteados.
    public static ArrayList<CampoCatalogoValor> listar(int idCatalogo) {
        ArrayList<CampoCatalogoValor> listaValor = new ArrayList<>(0);
        ArrayList campos = Clases.CampoCatalogo.listar(idCatalogo);
        Iterator it = campos.iterator();

        for (int i = 0; it.hasNext(); i++) {
            CampoCatalogo c = (CampoCatalogo) it.next();
            CampoCatalogoValor cv = new CampoCatalogoValor(c);
            listaValor.add(cv);
        }

        return listaValor;
    }

    //Crea una lista de CampoCatalogoValor, donde los valores dependen del 
    //elemento cuyo id es pasado por parametro, los campos y valores son seteados
    public static ArrayList<CampoCatalogoValor> listarCamposValores(int idElem) {
        ArrayList<CampoCatalogoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(0, 10);//SELECT ELEMENTO_CATALOGO
        String[] ATRIBUTOS = {
            "id_campo", //0
            "id_elemento", //1
            "valor", //2
            "nombre_campo", //3
            "tipo_campo" //4
        };
        String[] tabABuscar = {
            TABLAS[0],
            TABLAS[1],
            TABLAS[2]
        };
        String[] colCondicion = {"id_elemento"};
        Object[] colValor = {idElem};

        ResultSet rs = eCampo.naturalJoins(ATRIBUTOS, tabABuscar, colCondicion, colValor);

        if (rs != null) {
            try {
                while (rs.next()) {
                    CampoCatalogoValor cv = new CampoCatalogoValor();
                    cv.setValor(rs.getString(ATRIBUTOS[2]));
                    CampoCatalogo cc = new CampoCatalogo();
                    cc.setNombre(rs.getString(ATRIBUTOS[3]));
                    cc.setIdCampo(rs.getInt(ATRIBUTOS[0]));
                    cc.setTipo(rs.getString(ATRIBUTOS[4]));
                    cv.setCampo(cc);
                    
                    listaValor.add(cv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaValor;
    }
    
    public boolean modificar(CampoCatalogoValor campo) {
        Entity e = new Entity(2, 11);//Update valor_catalogo

        String[] condColumnas = {ATRIBUTOS[0], ATRIBUTOS[2]}; //id_campo, valor
        Object[] valores = {campo.getCampo().getIdCampo(),campo.getValor()};
        String[] colModificar = {ATRIBUTOS[2]}; //valor
        String[] nombreCampo = {valor};

        return e.modificar(condColumnas, valores, colModificar, nombreCampo);
    }
}
