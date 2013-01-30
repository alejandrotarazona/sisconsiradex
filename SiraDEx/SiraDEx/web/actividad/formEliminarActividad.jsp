<%-- 
    Document   : formEliminarActividad
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Eliminar Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Eliminar Actividad</h1>
        <logic:present name="actividadForm" property="mensaje">
            <bean:write name="actividadForm" property="mensaje" />
        </logic:present>

        <html:form method="POST" action="/EliminarActividad?method=delete">
            <table>
                <tr>
                <td>ID de la Actividad:</td> 
                <td><html:text name="actividadForm" property="idActividad"></html:text>
                    </td>
                </tr>
            </table>        
        <html:submit>Eliminar</html:submit>
    </html:form>
</body>
</html>
