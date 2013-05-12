/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.DataBase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author SisCon
 */
public class Backup extends Root {

    private FormFile backup;
    private String dir = "/home/backups_siradex/";
    private String frecuencia = inicializarFrecuencia(); //1 diario, 7 semanal, 30 mensual

    public Backup() {
    }

    public FormFile getBackup() {
        return backup;
    }

    public void setBackup(FormFile backup) {
        this.backup = backup;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    

    public String inicializarFrecuencia() {

        String[] cmd = {
            "/bin/sh",
            "-c",
            "cat " + dir + "frecuencia.txt | head -1"
        };
        System.out.println(cmd);
        byte b[] = new byte[4];
        
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            p.getInputStream().read(b);
      
            try {
                if (p.waitFor() != 0) {
                    mensajeError = "Error: No se pudo leer el archivo frecuencia.txt";
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensajeError = "Error: No se pudo leer el archivo frecuencia.txt. " + ex;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensajeError = "Error: No se pudo leer el archivo frecuencia.txt. " + ex;
        }
    
        return new String(b).replaceAll("[^\\d]", "");
    }
    
    
    public boolean hacerBackup() {

        String user = "postgres";
        String host = DataBase.getHost();
        String db = DataBase.getDatabase();
        String fecha = Log.getDate();
        fecha = "_" + fecha.replace("/", "-");
        String cmd = "pg_dump -i -h " + host + " -U " + user + " --format=c -f "
                + dir + db + fecha + ".backup " + db;
        System.out.println(cmd);
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            try {
                if (p.waitFor() != 0) {
                    mensajeError = "Error: No se pudo crear el backup.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensajeError = "Error: No se pudo crear el backup. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensajeError = "Error: No se pudo crear el backup. " + ex;
            return false;
        }
        mensaje = "El Backup " + db + fecha + ".backup se ha realizado con éxito.";
        return true;

    }

    public boolean restaurarDesdeBackup() {

        String user = "postgres";
        String host = DataBase.getHost();
        String db = DataBase.getDatabase();
        int port = DataBase.getPort();
        String cmd = "pg_restore -i -h " + host + " -p " + port + " -U " + user
                + " -c -d  " + db + " " + dir + backup.getFileName();
        System.out.println(cmd);
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            try {
                if (p.waitFor() != 0) {
                    mensajeError = "Error: La restauración no se pudo realizar.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensajeError = "Error: La restauración no se pudo realizar. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensajeError = "Error: La restauración no se pudo realizar. " + ex;
            return false;
        }

        mensaje = "La restauración del sistema a partir de "
                + backup.getFileName() + " se ha realizado con éxito";
        return true;
    }

    public boolean cambiarFrecuencia() {

        
        String[] cmd = {
            "/bin/sh",
            "-c",
            "sed -i '1s/.*/"+frecuencia+"/' /home/backups_siradex/frecuencia.txt"
        };
        System.out.println(cmd);
        try {
            Process p;
            p = Runtime.getRuntime().exec(cmd);
            try {
                if (p.waitFor() != 0) {
                    mensajeError = "Error: No se pudo cambiar la frecuencia.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensajeError = "Error: No se pudo cambiar la frecuencia. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensajeError = "Error: No se pudo cambiar la frecuencia. " + ex;
            return false;
        }

        mensaje = "La frecuencia ha sido cambiada.";
        return true;
    }

    public static void main(String[] args) {
        String s = "jnwejn6567njktn3";
        s = s.replaceAll("[^\\d]", "");
        System.out.print(s);
    }
}
