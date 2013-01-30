<%-- 
    Document   : formAgregarUsuario
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
        <title>SiraDEx | Registrar Usuario</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registrar Usuario</h1>
        <logic:present name="usuarioForm" property="mensaje">
            <bean:write name="usuarioForm" property="mensaje" /><br/>
        </logic:present>

        <html:form action="/RegistrarUsuario?method=save">

            USBID:<br/>  
            <html:text name="usuarioForm" property="username"></html:text><br/>

            Contraseña:<br/>
            <html:password name="usuarioForm" property="password"></html:password><br/>

            <html:submit>Registrar</html:submit>
        </html:form>
    </body>
</html>
