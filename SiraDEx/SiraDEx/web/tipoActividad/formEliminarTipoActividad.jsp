<%-- 
    Document   : formEliminarTipoActividad
    Created on : 13/11/2012, 08:59:49 PM
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
        <title>SiraDEx | Eliminar Tipo de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Eliminar Tipo de Actividad</h1>

    <html:form method="POST" action="/EliminarTipoActividad?method=delete">
        Nombre del Tipo de Actividad
        <html:text name="tipoActividadForm" property="nombreTipo"></html:text><br/>
        <html:submit>Eliminar</html:submit>
    </html:form>
    </body>
</html>
