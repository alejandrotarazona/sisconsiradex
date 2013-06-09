/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author SisCon
 */
public class CampoValor implements Serializable {

    private Campo campo;
    private String valor;
    private String valorAux = ""; /*atributo auxiliar para setearlo al momento llenar
     un campo de texto de tipo participante*/

    private FormFile file = null;
    private static String[] ATRIBUTOS = {
        "id_campo", //0
        "id_actividad", //1
        "valor", //2
        "archivo", //3
    };
    private static String[] TABLAS = {
        "VALOR",
        "CAMPO"
    };

    public CampoValor() {
    }

    public CampoValor(Campo campo) {
        this.campo = campo;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public FormFile getFile() {
        return file;
    }

    public String getValorAux() {
        return valorAux;
    }

    public void setValorAux(String valorAux) {
        this.valorAux = valorAux;
    }

    public void setFile(FormFile file) {
        this.file = file;
        this.valor = file.getFileName();
    }

    public static File bytesToFile(byte[] data, String path) {
        try {
            File archivo = new File(path);
            try (FileOutputStream fileOS = new FileOutputStream(path)) {
                fileOS.write(data);
                if (fileOS != null) {
                    fileOS.close();
                }
            }
            return archivo;
        } catch (IOException ex) {
            Logger.getLogger(CampoValor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setFile(final byte[] data) {

        FormFile ff = new FormFile() {
            @Override
            public String getContentType() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setContentType(String contentType) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getFileSize() {
                return data.length;
            }

            @Override
            public void setFileSize(int fileSize) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getFileName() {
                return valor;
            }

            @Override
            public void setFileName(String fileName) {
                valor = fileName;
            }

            @Override
            public byte[] getFileData() {
                return data;
            }

            @Override
            public InputStream getInputStream() throws FileNotFoundException, IOException {
                File file = bytesToFile(data, valor);
                return new FileInputStream(file);
            }

            @Override
            public void destroy() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        System.out.println("Nombre archivo:" + ff.getFileName());
        System.out.println("Tamaño archivo:" + ff.getFileSize() + "bytes");
        file = ff;

    }

    public boolean agregar(int idAct) {



        Entity eAgregar = new Entity(4);//VALOR
        boolean resp = true;

        Integer idCampo = campo.getIdCampo();


        if (file != null) { // agrega el archivo

            resp &= eAgregar.insertarArchivo(idCampo, idAct, valor, file);
        } // evita los campos adicionales de un tipo de participante
        else if (campo.getLongitud() != -1) {
            Object[] tupla = {idCampo, idAct, valor};
            // inserta el valor, puede se el valor obvio, el nombre de un archivo o 
            //los participantes concatenados de un tipo de p articipante.
            resp &= eAgregar.insertar(tupla);
        }

        if (campo.getTipo().equals("participante")) { //agrega en PARTICIPA
            String valAux = valorAux;
            if (valorAux.equals("Apellido(s), Nombre(s)")) {
                valAux = "";
            }
            if (!valor.isEmpty() || !Verificaciones.esVacio(valAux)) {
                Entity e = new Entity(5);//PARTICIPA
                String usbid;
                if (!valor.isEmpty()) {//obtiene solo el usbid del elemento
                    usbid = valor.split(",")[0];
                } else {//agrega un $ al inicio para identificar que no es un usbid 
                    usbid = "$" + valorAux;
                }
                Object[] tupla = {idAct, usbid, idCampo};
                System.out.println("INSERTARNDO EN PARTICIPA " + idAct + " "
                        + usbid + " " + idCampo + " pppppppppppppppppppppppp");
                resp &= e.insertar(tupla);
            }
        }

        return resp;
    }

    public boolean modificar(CampoValor campoNM, int idAct, String ip, String user) {
        boolean resp = true;
        Entity eValor = new Entity(4);//VALOR
        eValor.setIp(ip);
        eValor.setUser(user);

        String tipo = campoNM.getCampo().getTipo();

        if (!tipo.equals("archivo") && !tipo.equals("producto")
                && campo.getLongitud() != -1) {
            String[] condColumnas = {
                ATRIBUTOS[0], //id_campo
                ATRIBUTOS[1], //id_actividad
                ATRIBUTOS[2] //valor
            };

            String valorNM = campoNM.getValor();
            String val = valor;

            Object[] valores = {
                campo.getIdCampo(),
                idAct,
                valorNM
            };
            String[] colModificar = {ATRIBUTOS[2]}; //valor
            String[] modificaciones = {val};

            resp = eValor.modificar(condColumnas, valores, colModificar, modificaciones);
            eValor.log();
            System.out.println("--------Luego de modificar valor "
                    + campoNM.getValor() + " " + resp);

            if (tipo.equals("participante")) {
                resp &= modificarParticipante(campoNM, idAct);
                System.out.println("--------Luego de modificar participante "
                        + campoNM.getValor() + " " + resp);
            }

        } else {
            resp &= modificarArchivo(campoNM, idAct, ip, user);
        }

        return resp;
    }

    private boolean modificarArchivo(CampoValor campoNM, int idAct, String ip, String user) {
        Entity eValor = new Entity(4);//VALOR
        eValor.setIp(ip);
        eValor.setUser(user);
        String[] campos = {
            ATRIBUTOS[0], //id_campo
            ATRIBUTOS[1] //id_actividad
        };
        Integer[] condicion = {
            campoNM.getCampo().getIdCampo(),
            idAct
        };

        boolean resp = eValor.borrar(campos, condicion);

        resp &= eValor.insertarArchivo(campo.getIdCampo(), idAct, valor, file);
        eValor.log();

        return resp;
    }

    private boolean modificarParticipante(CampoValor campoNM, int idAct) {

        String valorNM = campoNM.getValor();
        String valorAuxNM = campoNM.getValorAux();
        if (valorAuxNM.isEmpty() && valorNM.isEmpty()
                && valorAux.isEmpty() && valor.isEmpty()) { //no se modifica
            return true;
        }
        boolean resp = true;
        String usbid;

        //Se elimina al participante

        if (!valorNM.isEmpty()) {// Decide si es un usuario o no
            usbid = valorNM.split(",")[0];
        } else {
            usbid = "$" + campoNM.getValorAux();
        }

        Entity eValor = new Entity(5);//PARTICIPA
        String[] campos = {
            ATRIBUTOS[0], //id_campo
            "id_act",
            "usbid"
        };
        Object[] condicion = {
            campo.getIdCampo(),
            idAct,
            usbid
        };
        resp &= eValor.borrar(campos, condicion);
        System.out.println("---------DELETE PARTICIPA " + campo.getNombre() + " " + resp);


        //Se inserta al particpante

        if (!valor.isEmpty()) {// Decide si es un usuario o no
            usbid = valor.split(",")[0];
        } else {
            usbid = "$" + valorAux;
        }


        Object[] tupla = {idAct, usbid, campo.getIdCampo()};
        resp &= eValor.insertar(tupla);
        System.out.println("---------INSERT PARTICIPA " + campo.getNombre() + " " + resp);


        return resp;

    }

//funcion auxiliar para solucionar el problema de que el tag html file no reconoce
//si el property file tiene un archivo y esto dificultaba la implementacion 
//de las verificaciones para los campos tipo archivo o producto 
    public static void auxModificarArchivo(ArrayList camposNM, ArrayList campos) {
        Iterator it = campos.iterator();
        for (int i = 0; it.hasNext(); i++) {
            CampoValor c = (CampoValor) it.next();
            String tipo = c.getCampo().getTipo();
            if (tipo.equals("archivo") || tipo.equals("producto")) {
                CampoValor campoNM = (CampoValor) camposNM.get(i);
                if (c.getValor().isEmpty() && !campoNM.getValor().isEmpty()) {
                    c.setCampo(campoNM.getCampo());
                    c.setFile(campoNM.getFile());
                    c.setValor(campoNM.getValor());
                }
            }
        }
    }

    public static ArrayList<CampoValor> clonar(ArrayList<CampoValor> campos) {

        ArrayList<CampoValor> clonCampos = new ArrayList<>();
        for (int i = 0; i < campos.size(); i++) {
            Campo campo = campos.get(i).getCampo();
            CampoValor cv = new CampoValor();
            Campo campoClon = new Campo();
            campoClon.setIdCampo(campo.getIdCampo());
            campoClon.setIdTipoActividad(campo.getIdTipoActividad());
            campoClon.setNombre(campo.getNombre());
            campoClon.setTipo(campo.getTipo());
            campoClon.setLongitud(campo.getLongitud());
            campoClon.setObligatorio(campo.isObligatorio());
            campoClon.setCatalogo(campo.getCatalogo());
            cv.setCampo(campoClon);
            clonCampos.add(cv);
        }
        return clonCampos;
    }


    /*Crea una lista de CampoValor con los campos del tipo de actividad cuyo id 
     es pasado por parametro*/
    public static ArrayList<CampoValor> listarCampos(int idTipoActividad) {
        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(3);//CAMPO
        String[] columnas = {"id_tipo_actividad"};
        Integer[] condiciones = {idTipoActividad};

        ResultSet rs = eCampo.seleccionar(columnas, condiciones);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Campo c = new Campo();
                    c.setIdCampo(rs.getInt("id_campo"));
                    c.setIdTipoActividad(rs.getInt("id_tipo_actividad"));
                    c.setNombre(rs.getString("nombre_campo"));
                    String tipo = rs.getString("tipo_campo");
                    c.setTipo(tipo);
                    c.setLongitud(rs.getInt("longitud"));
                    c.setObligatorio(rs.getBoolean("obligatorio"));
                    c.setCatalogo(rs.getString("catalogo"));
                    CampoValor cv = new CampoValor(c);
                    if (tipo.equals("participante")) {
                        cv.valorAux = "Apellido(s), Nombre(s)";
                    }
                    listaValor.add(cv);

                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaValor;
    }

    /* Crea una lista de CampoValor con los campos y valores de la actividad cuyo
     * id es pasado por parametro*/
    public static ArrayList<CampoValor> listarCamposValores(int idActividad) {

        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(2);//ACTIVIDAD
        String[] ATRIBUTO = {
            "id_campo",
            "id_tipo_actividad",
            "nombre_campo",
            "tipo_campo",
            "longitud",
            "obligatorio",
            "valor",
            "catalogo",
            "archivo"
        };
        String[] tabABuscar = {
            TABLAS[0],
            TABLAS[1]
        };
        String[] colCondicion = {"id_actividad"};
        Object[] colValor = {idActividad};
        ResultSet rs = eCampo.naturalJoin(ATRIBUTO, tabABuscar, colCondicion, colValor);
        if (rs != null) {
            try {
                while (rs.next()) {
                    CampoValor cv = new CampoValor();
                    String valor = rs.getString(ATRIBUTO[6]);
                    String tipoCampo = rs.getString(ATRIBUTO[3]);

                    cv.setValor(valor);
                    if (!cv.getValor().isEmpty()
                            && ((tipoCampo.equals(ATRIBUTO[8])
                            || tipoCampo.equals("producto")))) {
                        byte[] data = rs.getBytes(ATRIBUTO[8]);
                        cv.setFile(data);
                    }
                    Campo c = new Campo();
                    int idCampo = rs.getInt(ATRIBUTO[0]);
                    c.setIdCampo(idCampo);
                    c.setIdTipoActividad(rs.getInt(ATRIBUTO[1]));
                    c.setNombre(rs.getString(ATRIBUTO[2]));
                    c.setTipo(tipoCampo);
                    c.setLongitud(rs.getInt(ATRIBUTO[4]));
                    c.setObligatorio(rs.getBoolean(ATRIBUTO[5]));
                    String catalogo = rs.getString(ATRIBUTO[7]);
                    c.setCatalogo(catalogo);
                    cv.setCampo(c);
                    boolean hayParticipantes = true;
                    if (tipoCampo.equals("participante") && valor.isEmpty()) {
                        cv.valorAux = "Apellido(s), Nombre(s)";
                        hayParticipantes = false;
                    }
                    listaValor.add(cv);

                    if (hayParticipantes && tipoCampo.equals("participante")) {
                        agregarCamposParticipante(idCampo, idActividad, listaValor, catalogo);
                    }
                }

                rs.close();

                return listaValor;

            } catch (SQLException ex) {
                Logger.getLogger(CampoValor.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private static void agregarCamposParticipante(int idCampo, int idActividad,
            ArrayList<CampoValor> listaValor, String catalogo) {

        ArrayList<String> participantes = obtenerParticipantesCampo(idCampo, idActividad);
        Iterator it = participantes.iterator();
        while (it.hasNext()) {
            CampoValor cv = new CampoValor();
            Campo c = new Campo();
            c.setIdCampo(idCampo);
            c.setTipo("participante");
            c.setLongitud(-1);
            c.setObligatorio(false);
            c.setCatalogo(catalogo);
            cv.setCampo(c);

            String participante = (String) it.next();
            if (participante.startsWith("$")) { //participante que no es usuario
                cv.valorAux = participante.substring(1);
                cv.valor = "";
            } else {
                cv.valorAux = "Apellido(s), Nombre(s)";
                cv.valor = obtenerValor(participante, catalogo);
            }
            listaValor.add(cv);
        }
    }

    private static ArrayList<String> obtenerParticipantesCampo(int idCampo, int idActividad) {
        ArrayList<String> participantes = new ArrayList<>(0);
        Entity e = new Entity(5); //PARTICIPA
        String[] seleccion = {
            "usbid"
        };
        String[] tabla = {
            "ACTIVIDAD"
        };
        String[] columnas = {"id_actividad", "id_campo"};
        Integer[] valores = {idActividad, idCampo};

        ResultSet rs = e.naturalJoin(seleccion, tabla, columnas, valores);
        if (rs != null) {
            try {
                while (rs.next()) {
                    participantes.add(rs.getString("usbid"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return participantes;
    }

    public static String obtenerValor(String usbid, String catalogo) {

        ArrayList<ElementoCatalogo> valores = ElementoCatalogo.listarElementos(catalogo, 0);
        Iterator it = valores.iterator();
        while (it.hasNext()) {
            ElementoCatalogo ec = (ElementoCatalogo) it.next();
            if (ec.getMensaje().equals(usbid)) {
                return ec.getContenido();
            }
        }
        return "no se encontró al usuario en el catálogo";
    }
}
