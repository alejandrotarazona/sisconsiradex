<%-- 
    Document   : coleccionActividad
    Created on : 14/11/2012
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
        <h1 class="title" id="page-title">Actividades registradas en el sistema</h1>
        <logic:notPresent name="acts">
            No hay actividad que mostrar
        </logic:notPresent>
        <logic:present name="acts">
            <logic:empty name="acts">
                No hay actividad que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="acts" id="act">
                    <tr>
                    <td><b>Tipo de Actividad</b></td>
                    <td><bean:write name="act" property="nombreTipoActividad"></bean:write></td>
                </tr>
                <tr>
                <td><b>Id</b></td>
                <td><bean:write name="act" property="idActividad"></bean:write></td>
            </tr>

        </logic:iterate>
    </table>
</logic:present>
</body>
</html>