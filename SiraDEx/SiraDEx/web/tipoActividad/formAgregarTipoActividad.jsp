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
        <h1 class="title">Registro de Tipo de Actividad</h1>

        <logic:present name="tipoActividadForm" property="mensaje"><br>
            <div class ="error"><bean:write name="tipoActividadForm" property="mensaje"/></div>
            <br>
        </logic:present>
        <br>
        <font size=2>Todos los campos son obligatorios.</font><br>
        <html:form method="POST" action ="/RegistrarTipoActividad?method=save">
            <table>
                <tbody>
                    <tr>
                    <td width="15%"><b>Nombre del Tipo de Actividad</b></td>
                    <td><html:text name="tipoActividadForm" property="nombreTipo" 
                               size="78"/></td>
                    </tr>
                    <tr>
                    <td><b>Descripción</b></td>
                    <td><html:textarea name="tipoActividadForm"  cols="80" rows="2"
                                   property="descripcion"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Tipo de Producto</b></td>
                    <td>
                        <html:radio property="tipoPR" value="P" >P</html:radio>
                        <html:radio property="tipoPR" value="R" >R</html:radio>
                        </td>
                        </tr>
                        <tr>
                        <td><b>Programa</b></td>
                        <td>
                        <html:select property="programa">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name="programas" label="contenido" value="contenido"/>

                        </html:select>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Dependencia a validar</b></td>
                    <td> 
                        <logic:equal name="user" property="rol" value="WM">
                            <html:select property="validador">
                                <html:option value="">-- Seleccione --</html:option>
                                <html:optionsCollection name="dependencias" label="contenido" value="contenido"/>
                            </html:select>
                        </logic:equal>
                        <logic:notEqual name="user" property="rol" value="WM">
                            <html:select property="validador" disabled="true" title="${user.rol}">
                                <html:option value="${user.rol}">${user.rol}</html:option>
                            </html:select>
                        </logic:notEqual>    
                    </td>
                    </tr>
                    <tr>
                    <td><b>Realizado por</b></td>
                    <td>
                        <html:multibox property="permisos">Empleado</html:multibox> Empleados<br>
                        <html:multibox property="permisos">Estudiante</html:multibox> Estudiantes<br>
                        <html:multibox property="permisos">Profesor</html:multibox> Profesores<br>
                        <html:multibox property="permisos">Obrero</html:multibox> Obreros 
                        </td>       
                        </tr>
                        <tr>
                        <td><b>Número de archivos del Producto</b></td>
                        <td><html:text name="tipoActividadForm" property="nroProductos" size="1" maxlength="1"/></td>
                    </tr>
                    <tr>
                    <td><b>Número de campos</b></td>
                    <td><html:text name="tipoActividadForm" property="nroCampos" size="1" maxlength="2"/></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <div align="center"><html:submit>Siguiente</html:submit></div>
        </html:form>

    </body>
</html>
