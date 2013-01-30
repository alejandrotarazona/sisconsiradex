/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author alejandro
 */
public class campoCatalogo {
    
    private String      nombreCampo;
    private int         idCampo;
    private int         idCatalogo;
    private String      obligatorio;
    private int         longitud;
    private String      tipoCampo;

    public campoCatalogo() {
    }

    public campoCatalogo(String nombreCampo, int idCampo, int idCatalogo, String obligatorio, String tipoCampo) {
        this.nombreCampo = nombreCampo;
        this.idCampo = idCampo;
        this.idCatalogo = idCatalogo;
        this.obligatorio = obligatorio;
        this.tipoCampo = tipoCampo;
    }

    public campoCatalogo(String nombreCampo, int idCampo, int idCatalogo, String obligatorio, int longitud, String tipoCampo) {
        this.nombreCampo = nombreCampo;
        this.idCampo = idCampo;
        this.idCatalogo = idCatalogo;
        this.obligatorio = obligatorio;
        this.longitud = longitud;
        this.tipoCampo = tipoCampo;
    }

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }
    
     
    
}
