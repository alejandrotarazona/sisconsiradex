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
        <script type="text/javascript" src="../Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="../Scripts/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../Scripts/ColReorderWithResize.js"></script>
        <style type="text/css" title="currentStyle">
            @import "../Stylesheets/demo_table_jui.css";
        </style>
        <script>
            $(document).ready(function() {

                $('#datatab').dataTable({
                    "aoColumns": [
                        /* Backup */ null,
                        /* Restaurar */{"bSortable": false}
                    ]});
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Backups /></title>
    </head>
    <body>
        <logic:notEqual name="permiso" value="wm">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notEqual>
        <logic:equal name="permiso" value="wm">
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
            <br>
            <table>
                <tr>
                <td>
                    <h1 class="title">Crear Backup</h1>
                </td>
                <td width="10%"></td>
                <td>
                    <h1 class="title">Frecuencia de creación de Backups automáticos</h1>
                </td>
            </tr>
            <tr>
            <td>  
                <html:form method="POST" action="/GestionBackups?method=make" >
                    Si desea crear un respaldo de la BD presione
                    <html:submit style="margin-left:10px;">
                        Crear
                    </html:submit>    
                </html:form>
            </td>
            <td></td>
            <td>
                <html:form method="POST" action="/GestionBackups?method=set" >   

                    <html:radio property="frecuencia" value="1">diaria</html:radio>
                    <html:radio property="frecuencia" value="7">semanal</html:radio>
                    <html:radio property="frecuencia" value="30">mensual</html:radio>

                    <html:submit style="margin-left:40px;"
                                 onclick="return confirm('¿Está seguro que desea cambiar la frecuencia?')" >
                        Cambiar
                    </html:submit>

                </html:form>
            </td>
        </tr>
    </table>
    <br>
    <h1 class="title">Restaurar a partir de un Backup</h1>

    <logic:notPresent name="backups"><br><br>
    <span align="center">No hay backups que mostrar</span>
</logic:notPresent>
<logic:present name="backups">

    <table class="display" id="datatab">
        <thead>
            <tr>
            <th>Backup</th>
            <th></th>
            </tr>
        </thead>
        <tbody>
            <logic:iterate name="backups" id="b">
                <tr>
                <td>${b}</td>

                <td>
                    <html:form method="POST" action="/GestionBackups?method=restore">
                        <html:hidden name="backupForm" property="backup" value="${b}"/>
                        <html:submit styleId="botonRestaurar"
                                     value=" "
                                     title="Restaurar el sistema"
                                     onclick="return confirm('¿Está seguro que desea restaurar la base de datos a partir del archivo ${b}?')" />
                    </html:form>
                </td>
                </tr>
            </logic:iterate>   
        </tbody> 
    </table>
</logic:present>
</logic:equal>
</body>
</html>
