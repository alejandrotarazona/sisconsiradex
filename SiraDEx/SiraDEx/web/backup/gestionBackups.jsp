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
        <h1 class='title' id='page-title'>Gestión de Backups</h1>

        <logic:present name="backupForm" property="mensaje">
            <br><div class ="status"><bean:write name="backupForm" property="mensaje"/></div>
        </logic:present> 
        <logic:present name="backupForm" property="mensajeError">
            <br><div class ="error"><bean:write name="backupForm" property="mensajeError"/></div>
        </logic:present>

        <h1>Crear Backup</h1>

        <html:form method="POST" action="/GestionBackups?method=make" >
            Directorio donde se guardará el Backup
            <html:text  property="dir" size="78">
                <bean:write  name="backupForm" property="dir"/>
            </html:text>
            <br>
                <html:submit>
                    Crear
                </html:submit>
         
        </html:form>

        <br><h1>Restaurar sistema a partir de un Backup</h1>

        <html:form method="POST" enctype="multipart/form-data" 
                   action="/GestionBackups?method=restore">

            Elija el Backup
            <html:file property="backup" accept="application/backup"/>
            <br>
                <html:submit onclick="return confirm('¿Está seguro que desea restaurar el sistema?')" >
                    Restaurar
                </html:submit>
         
        </html:form>

        <br><h1>Configurar la creación de Backups automáticos</h1>

        <html:form method="POST" action="/GestionBackups?method=set" >        
            Frecuencia&nbsp;
            <html:radio property="frecuencia" value="1">diaria</html:radio>
            <html:radio property="frecuencia" value="7">semanal</html:radio>
            <html:radio property="frecuencia" value="30">mensual</html:radio>
            <br>
                <html:submit onclick="return confirm('¿Está seguro que desea cambiar la frecuencia?')" >
                    Cambiar
                </html:submit>
     
        </html:form>

    </body>
</html>
