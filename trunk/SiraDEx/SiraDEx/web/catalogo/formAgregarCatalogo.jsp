
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registro de Catálogo</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registro de Catálogo</h1>
        <p>Los campos con el asterisco  <span style="color:red">*</span> 
    son obligatorios.</p>
    <logic:present name="catalogoForm" property="mensaje">
    <br/><bean:write name="catalogoForm" property="mensaje" /><br/>
</logic:present>
<html:form method="POST" action ="/RegistrarCatalogo?method=save">
    <table>
        <tr>
        <td>Nombre<span style="color:red">*</span> </td>
    <td><html:text name="catalogoForm" property="nombre"/></td>
</tr>
<tr>
<td>Número de campos<span style="color:red">*</span> </td>
<td><html:text name="catalogoForm" property="nroCampos"/></td>
</tr>
</table>
<br>
<div align="center"><html:submit>Siguiente</html:submit></div>
</html:form>
</body>
</html>
