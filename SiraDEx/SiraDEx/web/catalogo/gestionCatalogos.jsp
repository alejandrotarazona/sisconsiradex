<%-- 
    Document   : gestionTiposActividad
    Created on : 31/10/2012, 08:41:09 AM
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
        <title>SiraDEx | Gestion de Catalogos</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Catalogos</h1>
        <logic:present name="catalogoForm" property="mensaje">
            <bean:write name="catalogoForm" property="mensaje" /><br/>
        </logic:present>

        <html:link action="/AgregarCatalogo?method=page"> 
            Registrar catalogo
        </html:link><br/>  

    </body>
</html>
