<%-- 
    Document   : coleccionTiposActividad
    Created on : 31/10/2012, 11:15:51 PM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <body>
        <h1 class="title" id="page-title">Tipos de Actividades registradas en el sistema</h1>
        <logic:notPresent name="tipos">
            No hay tipos de actividad que mostrar
        </logic:notPresent>
        <logic:present name="tipos">
            <logic:empty name="tipos">
                No hay tipos de actividad que mostrar
            </logic:empty>
            <table>
            <logic:iterate name="tipos" id="ta">
                <tr>
                    <td><b>Nombre</b></td>
                    <td><bean:write name="ta" property="nombreTipo"></bean:write></td>
                </tr>
                <tr>
                <td><b>Descripción</b></td>
                <td><bean:write name="ta" property="descripcion"></bean:write></td>
            </tr>
        </logic:iterate>
    </table>
</logic:present>
</body>
</html>