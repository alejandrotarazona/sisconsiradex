<%-- 
    Document   : formAgregarTipoActividad
    Created on : Oct 26, 2012, 8:37:29 AM
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
        <title>SiraDEx | Agregar Tipo de Actividad</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registro del Tipo de Actividad</h1>
        <p>Los campos con el asterisco  <span style="color:red">*</span> son obligatorios.</p></br>
        <logic:present name="tipoActividadForm" property="mensaje">
            <bean:write name="tipoActividadForm" property="mensaje" /><br/>
        </logic:present>
        <html:form method="POST" action ="/RegistrarTipoActividad?method=save">
            <table><tr>
                <td>Nombre<span style="color:red">*</span> </td>
                <td><html:text name="tipoActividadForm" property="nombreTipo"></html:text></td>
            </tr>
            <tr>
            <td>Descripcion<span style="color:red">*</span> </td>
            <td><html:textarea name="tipoActividadForm"  cols="100" rows="4"
                           property="descripcion"></td></html:textarea>
        </tr>
        <tr><td>Numero de campos<span style="color:red">*</span> </td>
        <td><html:text name="tipoActividadForm" property="nroCampos"></html:text></td>
        </tr>
        <tr><td><html:submit> Siguiente </html:submit></td></tr>
</table>
</html:form>
</body>
</html>
