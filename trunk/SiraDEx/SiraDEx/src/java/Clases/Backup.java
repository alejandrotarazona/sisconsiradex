/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DBMS.DataBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SisCon
 */
public class Backup extends Root {

    private String backup;
    private String frecuencia = inicializarFrecuencia(); //1 diario, 7 semanal, 30 mensual

    public Backup() {
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String inicializarFrecuencia() {
        String cmd = "cat /home/backups_siradex/frecuencia.txt | head -1";
        String[] comando = {
            "/bin/sh",
            "-c",
            cmd
        };
        System.out.println(cmd);
        byte b[] = new byte[4];

        try {
            Process p;
            p = Runtime.getRuntime().exec(comando);
            p.getInputStream().read(b);

            try {
                if (p.waitFor() != 0) {
                    mensaje = "Error: No se pudo leer el archivo frecuencia.txt";
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Error: No se pudo leer el archivo frecuencia.txt. " + ex;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: No se pudo leer el archivo frecuencia.txt. " + ex;
        }

        return new String(b).replaceAll("[^\\d]", "");
    }

    public boolean hacerBackup() {

        String user = "siradex";
        String host = DataBase.getHost();
        String db = DataBase.getDatabase();
        String fecha = Log.getDate();
        fecha = "_" + fecha.replace("/", "_");
        String comando = "pg_dump -i -h " + host + " -U " + user + " --format=c -f "
                + "/home/backups_siradex/" + db + fecha + ".backup " + db;
        System.out.println(comando);
        try {
            Process p;
            p = Runtime.getRuntime().exec(comando);
            try {
                if (p.waitFor() != 0) {
                    mensaje = "Error: No se pudo crear el backup.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Error: No se pudo crear el backup. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: No se pudo crear el backup. " + ex;
            return false;
        }
        mensaje = "El Backup " + db + fecha + ".backup se ha realizado con éxito.";
        return true;

    }

    public boolean restaurarDesdeBackup() {

        String user = "siradex";
        String host = DataBase.getHost();
        String db = DataBase.getDatabase();
        int port = DataBase.getPort();
        String comando = "pg_restore -i -h " + host + " -p " + port + " -U " + user
                + " -c -d  " + db + " /home/backups_siradex/" + backup;
        System.out.println(comando);
        try {
            Process p;
            p = Runtime.getRuntime().exec(comando);
            try {
                if (p.waitFor() != 0) {
                    mensaje = "Error: La restauración no se pudo realizar.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Error: La restauración no se pudo realizar. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: La restauración no se pudo realizar. " + ex;
            return false;
        }

        mensaje = "La restauración del sistema a partir de "
                + backup + " se ha realizado con éxito";
        return true;
    }

    public boolean cambiarFrecuencia() {

        String cmd = "sed -i '1s/.*/" + frecuencia + "/' /home/backups_siradex/frecuencia.txt";
        String[] comando = {
            "/bin/sh",
            "-c",
            cmd
        };
        System.out.println(cmd);
        try {
            Process p;
            p = Runtime.getRuntime().exec(comando);
            try {
                if (p.waitFor() != 0) {
                    mensaje = "Error: No se pudo cambiar la frecuencia.";
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Error: No se pudo cambiar la frecuencia. " + ex;
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: No se pudo cambiar la frecuencia. " + ex;
            return false;
        }

        mensaje = "La frecuencia ha sido cambiada con éxito.";
        return true;
    }

    public ArrayList listar() {
        ArrayList backups = new ArrayList<>(0);
        String cmd = "cd /home/backups_siradex && ls *.backup";
        String[] comando = {
            "/bin/sh",
            "-c",
            cmd
        };
        System.out.println(cmd);
        try {
            Process p;
            p = Runtime.getRuntime().exec(comando);

            try {
                if (p.waitFor() != 0) {
                    mensaje = "Error: No se pudo leer los archivos .backups";
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = reader.readLine();
                while (line != null) {
                    backups.add(line);
                    line = reader.readLine();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Error: No se pudo leer los archivos .backups " + ex;
            }

        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: No se pudo leer los archivos .backups " + ex;
        }


        return backups;
    }

    public static void main(String[] args) {
        Backup b = new Backup();
        ArrayList bs = b.listar();
        System.out.println("tamaño " + bs.size());
        for (int i = 0; i < bs.size(); i++) {
            System.out.println(bs.get(i));
        }
    }
}
