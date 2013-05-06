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
    private int periodo; //1 diario, 2 semanal, 3 mensual
    private String dir = "/home/garcia/NetBeansProjects/SiraDEx/Backups/";

    public FormFile getBackup() {
        return backup;
    }

    public void setBackup(FormFile backup) {
        this.backup = backup;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean hacerBackup() {

        String user = "postgres";
        String host = DataBase.getHost();
        String db = DataBase.getDatabase();
        String fecha_hora = Log.getFechaHora();
        fecha_hora = "_" + fecha_hora.replace("/", "-").replace(" ", "_").replace(".", "");
        String cmd = "pg_dump -i -h " + host + " -U " + user + " --format=c -f "
                + dir + db + fecha_hora + ".backup " + db;
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
        mensaje = "El Backup " + db + fecha_hora + ".backup se ha realizado con éxito.";
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
                mensajeError = "Error: La restauración no se pudo realizar. "+ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensajeError = "Error: La restauración no se pudo realizar. "+ex;
            return false;
        }

        mensaje = "La restauración del sistema a partir de "
                + backup.getFileName() + " se ha realizado con éxito";
        return true;
    }
}
