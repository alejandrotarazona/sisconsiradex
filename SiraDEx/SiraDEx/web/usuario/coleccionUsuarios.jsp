<%-- 
    Document   : coleccion
    Created on : Oct 18, 2012, 8:04:53 AM
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
        <h1 class="title" id="page-title">Usuarios registrados en el sistema</h1>
        <logic:notPresent name="usuarios">
            No hay usuarios que mostrar
        </logic:notPresent>
        <logic:present name="usuarios">
            <logic:empty name="usuarios">
                No hay usuarios que mostrar
            </logic:empty>
            <table>
            <logic:iterate name="usuarios" id="usr">
                <tr>
                    <td><b>Username</b></td>
                    <td><bean:write name="usr" property="username"></bean:write></td>
                </tr>
                <tr>
                <td><b>Password</b></td>
                <td><bean:write name="usr" property="password"></bean:write></td>
            </tr>
        </logic:iterate>
    </table>
</logic:present>
</body>
</html>