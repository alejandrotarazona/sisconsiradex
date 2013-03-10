<%-- 
    Document   : formPerfilUsuario
    Created on : Mar 10, 2013, 12:23:43 AM
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
        <title>SiraDEx | Perfil del Usuario</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Perfil del Usuario</h1>
        <logic:present name="usuarioForm" property="mensaje">
            <bean:write name="usuarioForm" property="mensaje" /><br/>
        </logic:present>

    </body>
</html>