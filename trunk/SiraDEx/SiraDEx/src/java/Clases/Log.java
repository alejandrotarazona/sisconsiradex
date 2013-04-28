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
public class Log {
    
    private long idLog;
    private String accion;
    private Calendar fecha;
    private String ip;
    private String usbid;

    public Log() {
    }

    public long getIdLog() {
        return idLog;
    }

    private void setIdLog(BigDecimal idLog) {
        this.idLog = idLog.longValue();
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
    
    public static boolean agregar(String ip, String query, String usbid) {
        boolean resp = true;
        Entity eAgregar = new Entity(1,20);
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
    
    public static ArrayList<Log> listar(){
        Entity eListar = new Entity(0,20);
        ResultSet rs  = eListar.listar();
        ArrayList<Log> resp = new ArrayList<>(0);
        try {
            while(rs.next()){
                Log actual = new Log();
                Calendar ahora = null;
                actual.setAccion(rs.getString("accion"));
                actual.setIdLog(rs.getBigDecimal("id_log"));
                actual.setIp(rs.getString("ip"));
                ahora.setTimeInMillis(rs.getDate("fecha").getTime());
                actual.setFecha(ahora);
                actual.setUsbid(rs.getString("usbid"));
                
                resp.add(actual);
            }
            return resp;
        } catch (SQLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return resp;
        }
    }
    
    public static String getFechaHora() {
        TimeZone tz = TimeZone.getTimeZone("America/Caracas");
        Calendar calendar = new GregorianCalendar(tz);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        String dia = Integer.toString(calendar.get(Calendar.DATE));
        String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);//Enero empieza en 0
        String año = Integer.toString(calendar.get(Calendar.YEAR));
        int amopm = calendar.get(Calendar.AM_PM);
        String am_pm = " p.m.";
        if (amopm == 0) {
            am_pm = " a.m.";
        }
        String hora = Integer.toString(calendar.get(Calendar.HOUR));
        String min = Integer.toString(calendar.get(Calendar.MINUTE));
        String seg = Integer.toString(calendar.get(Calendar.SECOND));
        return dia + "/" + mes + "/" + año + " " + hora + ":" + min + ":" + seg + am_pm;
    }
    
}
