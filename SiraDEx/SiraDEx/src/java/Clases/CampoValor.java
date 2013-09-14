/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import Json.JSONObject;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author SisCon
 */
public class CampoValor implements Serializable {

    private Campo campo;
    private String valor = "";
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
                //obtiene solo el usbid del elemento
                if (!valor.isEmpty()) {
                    if (valor.startsWith("$")) {
                        usbid = valor.split(";")[0];
                    } else {
                        usbid = valor.split(",")[0];
                    }
                    //agrega un $ al inicio para identificar que no es un usbid 
                } else {
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

        String tipo = campo.getTipo();

        if (campoNM.campo.getLongitud() != -3) { //no es un campo eliminado

            // no es archivo, ni creado dinamicamente
            if (!tipo.equals("archivo") && campo.getLongitud() >= 0) {
                String[] condColumnas = {
                    ATRIBUTOS[0], //id_campo
                    ATRIBUTOS[1] //id_actividad
                };

                String val = valor;

                Object[] valores = {
                    campo.getIdCampo(),
                    idAct
                };
                String[] colModificar = {ATRIBUTOS[2]}; //valor
                String[] modificaciones = {val};

                resp = eValor.modificar(condColumnas, valores, colModificar, modificaciones);
                eValor.insertarLog();
                System.out.println("--------Luego de modificar valor "
                        + campoNM.getValor() + " " + resp);

            } else if (tipo.equals("archivo")) {
                resp &= modificarArchivo(campoNM, idAct, ip, user);
            }
        }
        if (tipo.equals("participante") && !Verificaciones.esVacio(valor)
                && !Verificaciones.esVacio(valorAux)) {
            resp &= modificarParticipante(campoNM, idAct);
            System.out.println("--------Luego de modificar participante "
                    + campoNM.getValor() + " " + resp);
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
        eValor.insertarLog();

        return resp;
    }

    private boolean modificarParticipante(CampoValor campoNM, int idAct) {

        String valorNM = campoNM.valor;
        String valorAuxNM = campoNM.valorAux;
        System.out.println("MODIFICAR PARTICIPANTE VAL " + valor + " VALAUX " + valorAux
                + " VALNM " + valorNM + " VALAUXNM " + valorAuxNM);
        if (valorAux.equals("Apellido(s), Nombre(s)")) {
            valorAux = "";
        }
        if (valorAuxNM.equals("Apellido(s), Nombre(s)")) {
            valorAuxNM = "";
        }

        boolean resp = true;
        Entity eValor = new Entity(5);//PARTICIPA
        String usbid;

        //Se inserta al participante
        System.out.println("0INSERTAR PARTICIPANTE VAL " + valor + " VALAUX " + valorAux
                + " VALNM " + valorNM + " VALAUXNM " + valorAuxNM);
        //obtiene solo el usbid del elemento
        if (!valor.isEmpty()) {
            if (valor.startsWith("$")) {
                usbid = valor.split(";")[0];
            } else {
                usbid = valor.split(",")[0];
            }
            //agrega un $ al inicio para identificar que no es un usbid 
        } else {
            usbid = "$" + valorAux;
        }

        System.out.println("1INSERTAR PARTICIPANTE VAL " + valor + " VALAUX " + valorAux
                + " VALNM " + valorNM + " VALAUXNM " + valorAuxNM);
        Object[] tupla = {idAct, usbid, campo.getIdCampo()};
        resp &= eValor.insertar(tupla);
        System.out.println("---------INSERT PARTICIPA " + usbid + " " + resp);
        return resp;

    }

    public static ArrayList<CampoValor> clonar(ArrayList<CampoValor> campos) {

        ArrayList<CampoValor> clonCampos = new ArrayList<>();
        for (int i = 0; i < campos.size(); i++) {
            CampoValor cv = campos.get(i);
            Campo c = cv.getCampo();
            CampoValor cvClon = new CampoValor();
            cvClon.file = cv.file;
            cvClon.valor = cv.valor;
            cvClon.valorAux = cv.valorAux;
            Campo cClon = new Campo();
            cClon.setIdCampo(c.getIdCampo());
            cClon.setIdTipoActividad(c.getIdTipoActividad());
            cClon.setNombre(c.getNombre());
            cClon.setTipo(c.getTipo());
            cClon.setLongitud(c.getLongitud());
            cClon.setObligatorio(c.isObligatorio());
            cClon.setCatalogo(c.getCatalogo());
            cvClon.campo = cClon;
            clonCampos.add(cvClon);
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

    /* Crea una lista de CampoValor de la actividad cuyo id es pasado por 
     * parametro*/
    public static ArrayList<CampoValor> listarCampoValor(int idActividad) {

        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(2);//ACTIVIDAD
        String[] ATRIBUTO = {
            "nombre_campo",//0
            "tipo_campo",//1
            "valor",//2
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
                    String valor = rs.getString(ATRIBUTO[2]);
                    String tipoCampo = rs.getString(ATRIBUTO[1]);
                    if (tipoCampo.equals("archivo") || tipoCampo.equals("textol")) {
                        continue;
                    }
                    cv.setValor(valor);
                    Campo c = new Campo();
                    c.setNombre(rs.getString(ATRIBUTO[0]));
                    c.setTipo(tipoCampo);
                    cv.setCampo(c);

                    listaValor.add(cv);

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

    /* Crea una lista de CampoValor con los campos y valores de la actividad cuyo
     * id es pasado por parametro y crea nuevos campos para colocar los participantes 
     * del valor de un campo participante en varios campos*/
    public static ArrayList<CampoValor> listarCamposValores(int idActividad) {

        ArrayList<CampoValor> listaValor = new ArrayList<>(0);
        Entity eCampo = new Entity(2);//ACTIVIDAD
        String[] ATRIBUTO = {
            "id_campo",//0
            "id_tipo_actividad",//1
            "nombre_campo",//2
            "tipo_campo",//3
            "longitud",//4
            "obligatorio",//5
            "valor",//6
            "catalogo",//7
            "archivo"//8
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
                            && tipoCampo.equals(ATRIBUTO[8])) {
                        byte[] data = rs.getBytes(ATRIBUTO[8]);
                        if (data != null) {
                            cv.setFile(data);
                        } else {
                            cv.setValor("");
                        }
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
                    boolean sinParticipantes = true;

                    if (tipoCampo.equals("participante") && !valor.isEmpty()) {
                        sinParticipantes = false;
                    }
                    if (sinParticipantes) {
                        listaValor.add(cv);
                    }
                    if (!sinParticipantes && tipoCampo.equals("participante")) {
                        System.out.println("participantes a pasar a campos: " + valor);
                        agregarCamposParticipante(cv, listaValor, catalogo);
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

    private static void agregarCamposParticipante(CampoValor primerCampo,
            ArrayList<CampoValor> listaValor, String catalogo) {
        String concatenacion = primerCampo.valor.replace("; ", ";");
        String[] participantes = concatenacion.split(";");
        int longitud = primerCampo.getCampo().getLongitud() - participantes.length + 1;
        primerCampo.getCampo().setLongitud(longitud);
        if (participantes[0].startsWith("$")) {
            primerCampo.valor = "";
            primerCampo.valorAux = participantes[0].substring(1);
            System.out.println(primerCampo.valorAux + " " + " 1valauxxxxxxxxxxxxx");
        } else {
            primerCampo.valor = participantes[0];
            primerCampo.valorAux = "Apellido(s), Nombre(s)";
            System.out.println(primerCampo.valor + " 1vallllllllllllll");
        }

        listaValor.add(primerCampo);

        int idCampo = primerCampo.getCampo().getIdCampo();
        for (int i = 1; i < participantes.length; i++) {
            CampoValor cv = new CampoValor();
            Campo c = new Campo();
            c.setIdCampo(idCampo);
            c.setTipo("participante");
            c.setLongitud(-1);
            c.setObligatorio(false);
            c.setCatalogo(catalogo);
            cv.setCampo(c);

            String participante = participantes[i];
            if (participante.startsWith("$")) { //participante que no es usuario
                cv.valorAux = participante.substring(1);
                cv.valor = "";
                System.out.println(cv.valorAux + " nvalauxxxxxxxxxxxxx");
            } else {
                cv.valorAux = "Apellido(s), Nombre(s)";
                cv.valor = obtenerValor(participante.split(",")[0], catalogo);
                System.out.println(cv.valor + " nvallllllllllllll");
            }

            listaValor.add(cv);
        }
    }

    public static String obtenerValor(String usbid, String catalogo) {

        ArrayList<ElementoCatalogo> valores = ElementoCatalogo.listarElementos(catalogo, 0);
        for (ElementoCatalogo ec : valores) {
            if (ec.getMensaje().equals(usbid)) {
                return ec.getContenido();
            }
        }
        return usbid + ", NO HA SIDO REGISTRADO AL CATÁLOGO";
    }

    public JSONObject toJSONObject() {
        JSONObject jCampoValor = new JSONObject();

        /*
         *     private Campo campo;
         *     private String valor = ""
         */

        jCampoValor.put("campo", campo.toJSONObject());
        jCampoValor.put("valor", valor);

        return jCampoValor;
    }
}
