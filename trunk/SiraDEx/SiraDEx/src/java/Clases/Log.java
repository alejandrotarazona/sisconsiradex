/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author SisCon
 */
public class Log {
    
    
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
