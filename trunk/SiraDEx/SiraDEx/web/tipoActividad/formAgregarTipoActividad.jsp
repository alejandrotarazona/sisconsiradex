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
        <h1 class="title" id="page-title">Registro de Tipo de Actividad</h1>

        <logic:present name="tipoActividadForm" property="mensaje">
            <br/><div align="center"><b>
                    <bean:write name="tipoActividadForm" property="mensaje" /></b></div><br/>
                </logic:present>

                    <font size=2>Todos los campos son obligatorios.</font><br>
        <html:form method="POST" action ="/RegistrarTipoActividad?method=save">
            <table>
                <tr>
                <td>Nombre de la Actividad</td>
                <td><html:text name="tipoActividadForm" property="nombreTipo"></html:text></td>
            </tr>
            <tr>
            <td>Descripción</td>
            <td><html:textarea name="tipoActividadForm"  cols="80" rows="4"
                           property="descripcion"></html:textarea>
                </td>
            </tr>
            <tr>
            <td>Tipo</td>
            <td><html:select property="tipoPR">
                <html:option value="">-- Seleccione --</html:option>
                <html:option value="P">P</html:option>
                <html:option value="R">R</html:option>
            </html:select>
        </td>
    </tr>
    <tr>
    <td>Programa</td>
    <td>
        <html:select property="programa">
            <html:option value="">-- Seleccione --</html:option>
            <html:optionsCollection name="programas" label="contenido" value="contenido"/>

        </html:select>
    </td>
</tr>
<tr>
<td>Coordinación a validar</td>
<td>       
    <html:select property="validador">
        <html:option value="">-- Seleccione --</html:option>
        <html:optionsCollection name="coordinaciones" label="contenido" value="contenido"/>
    </html:select>
</td>
</tr>
<tr>
<td>Realizado por</td>
<td>
    <html:multibox property="permisos">empleado</html:multibox> Empleados<br>
    <html:multibox property="permisos">estudiante</html:multibox> Estudiantes<br>
    <html:multibox property="permisos">profesor</html:multibox> Profesores<br>
    <html:multibox property="permisos">obrero</html:multibox> Obreros 
    </td>       
</tr>
<tr><td>Número de productos</td>
<td><html:text name="tipoActividadForm" property="nroProductos" size="1" 
               maxlength="1"/></td>
</tr>
<tr><td>Número de campos</td>
<td><html:text name="tipoActividadForm" property="nroCampos" size="1" 
               maxlength="2"/></td>
</tr>
</table>
<br>
<div align="center"><html:submit>Siguiente</html:submit></div>
</html:form>

</body>
</html>
