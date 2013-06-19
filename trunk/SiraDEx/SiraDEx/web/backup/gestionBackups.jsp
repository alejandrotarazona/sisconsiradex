<%-- 
    Document   : gestionBackups
    Created on : 04/05/2013, 08:07:25 PM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Backups /></title>
    </head>
    <body>
        <h1 class='title'>Gestión de Backups</h1>

        <logic:present name="mensajeBackup"><br>
            <logic:notMatch name="mensajeBackup" value="Error:">
                <div class ="status"><bean:write name="mensajeBackup"/></div>
            </logic:notMatch>
            <logic:match name="mensajeBackup" value="Error:">
                <div class ="error"><bean:write name="mensajeBackup"/></div>
            </logic:match>
            <br>
        </logic:present>

        <table>
            <tbody>
                <tr>
                <td colspan="2">
                    <h1 class="title">Crear Backup</h1>
                </td>
                </tr>
                <tr>
                <td width="45%">
                    <html:form method="POST" action="/GestionBackups?method=make" >
                        El Backup se creará en el directorio <b>/home/backups_siradex/</b> del servidor. 
                    </td>
                    <td>
                        <html:submit style="width: 100px;">
                            Crear
                        </html:submit>    
                    </html:form><br><br><br>
                </td>
                </tr> 
                <tr>
                <td colspan="2">
                    <h1 class="title">Restaurar a partir de un Backup</h1>
                </td>
                </tr>
                <tr>
                <td>
                    <html:form method="POST" enctype="multipart/form-data" 
                               action="/GestionBackups?method=restore">

                        <b>Elija el Backup</b>
                        <html:file property="backup" accept="application/backup"/><br>
                        (El archivo debe estar en el directorio <b>/home/backups_siradex/</b> del servidor.) 
                    </td>
                    <td>       
                        <html:submit style="width: 100px;"
                                     onclick="return confirm('¿Está seguro que desea restaurar el sistema?')" >
                            Restaurar
                        </html:submit>
                    </html:form><br><br><br><br>
                </td>
                </tr>  
                <tr>
                <td>  
                    <h1 class="title">Configurar la creación de Backups automáticos</h1>
                </td>  
                </tr>
                <tr>
                <td>
                    <html:form method="POST" action="/GestionBackups?method=set" >  
                        <b>Frecuencia&nbsp;</b>
                        <html:radio property="frecuencia" value="1">diaria</html:radio>
                        <html:radio property="frecuencia" value="7">semanal</html:radio>
                        <html:radio property="frecuencia" value="30">mensual</html:radio>
                    </td>
                    <td>
                        <html:submit style="width: 100px;"
                                     onclick="return confirm('¿Está seguro que desea cambiar la frecuencia?')" >
                            Cambiar
                        </html:submit>
                    </td>
                </html:form>
                </tr>
            </tbody>
        </table>
    </body>
</html>
