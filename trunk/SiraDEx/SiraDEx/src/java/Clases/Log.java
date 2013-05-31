/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.Entity;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class Log extends Root{

    private BigDecimal idLog;
    private String accion;
    private Calendar fecha;
    private String ip;
    private String usbid;

    public Log() {
    }

    public BigDecimal getIdLog() {
        return idLog;
    }

    private void setIdLog(BigDecimal idLog) {
        this.idLog = idLog;
    }

    public String getAccion() {
        return accion;
    }

    private void setAccion(String accion) {
        this.accion = accion;
    }

    public Calendar getFecha() {
        return fecha;
    }

    private void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getIp() {
        return ip;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsbid() {
        return usbid;
    }

    private void setUsbid(String usbid) {
        this.usbid = usbid;
    }

    /**
     * Método para agregar a la Base de Datos una instancia de Log de un usuario
     * registrado del sistema
     *
     * @param ip IP desde donde se realiza la acción.
     * @param query La acción que se solicita.
     * @param usbid El usuario que solicita la acción.
     * @return true en caso de agregar efectivamente la accion.
     */
    public static boolean agregar(String ip, String query, String usbid) {
        boolean resp = true;
        Entity eAgregar = new Entity(1, 11);
        String[] columnas = {
            "accion",
            "ip",
            "usbid"
        };

        String[] valores = {
            query,
            ip,
            usbid
        };

        resp &= eAgregar.insertar2(columnas, valores);

        return resp;
    }

    /**
     * Método para agregar una instancia del Log cuando no es una solicitud
     * de un usuario registrado
     * @param ip    IP del sitio desde el cual se hace la solicitud.
     * @param query Solicitud realizada al sistema.
     * @return  true en caso de realizar la incersión de manera  satisfactoria.
     */
    public static boolean agregar(String ip, String query) {
        boolean resp = true;
        Entity eAgregar = new Entity(1, 11);
        String[] columnas = {
            "accion",
            "ip"
        };

        String[] valores = {
            query,
            ip
        };

        resp &= eAgregar.insertar2(columnas, valores);

        return resp;
    }

    public static ArrayList<Log> listar() {
        Entity eListar = new Entity(0, 11);
        ResultSet rs = eListar.listar();
        ArrayList<Log> resp = new ArrayList<>(0);
        try {
            while (rs.next()) {
                Log actual = new Log();
                Calendar ahora = null;
                actual.setAccion(rs.getString("accion"));
                actual.setIdLog(rs.getBigDecimal("id_log"));
                actual.setIp(rs.getString("ip"));
                ahora.setTimeInMillis(rs.getDate("fecha").getTime() + rs.getDate("hora").getTime());
                actual.setFecha(ahora);
                actual.setUsbid(rs.getString("usbid"));

                resp.add(actual);
            }
            return resp;
        } catch (SQLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
     public static String getDate() {
        TimeZone tz = TimeZone.getTimeZone("America/Caracas");
        Calendar calendar = new GregorianCalendar(tz);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        String dia = Integer.toString(calendar.get(Calendar.DATE));
        String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);//Enero empieza en 0
        String año = Integer.toString(calendar.get(Calendar.YEAR));
        return dia + "/" + mes + "/" + año;
    }
    
    public static String getHora() {
        TimeZone tz = TimeZone.getTimeZone("America/Caracas");
        Calendar calendar = new GregorianCalendar(tz);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int amopm = calendar.get(Calendar.AM_PM);
        String am_pm = " p.m.";
        if (amopm == 0) {
            am_pm = " a.m.";
        }
        String hora = Integer.toString(calendar.get(Calendar.HOUR));
        String min = Integer.toString(calendar.get(Calendar.MINUTE));
        String seg = Integer.toString(calendar.get(Calendar.SECOND));
        return hora + ":" + min + ":" + seg + am_pm;
    }
     
    public static String getFechaHora() {
        return getDate() + " " + getHora();
    }
}
