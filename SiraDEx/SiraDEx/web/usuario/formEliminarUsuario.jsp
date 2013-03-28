<%-- 
    Document   : formEliminarUsuario
    Created on : 24/10/2012, 11:38:28 PM
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
        <title>SiraDEx | Eliminar Usuario</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Eliminar Usuario</h1>
        
        <br><logic:present name="usuarioForm" property="mensaje">
            <b><div class ="status"><bean:write name="usuarioForm" property="mensaje" /></div></b>
                </logic:present> 
            <br><logic:present name="usuarioForm" property="mensajeError">
            <b><div class ="error"><bean:write name="usuarioForm" property="mensajeError" /></div></b>
            </logic:present>
    

        <html:form action="/EliminarUsuario?method=delete">
            
            Username: 
            <html:text name="usuarioForm" property="username"></html:text><br/>
        <html:submit>Eliminar</html:submit>
    </html:form>
</body>
</html>
