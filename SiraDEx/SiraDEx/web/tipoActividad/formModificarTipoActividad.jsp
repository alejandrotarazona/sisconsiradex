<%-- 
    Document   : formModificarTipoActividad
    Created on : 12/02/2013, 05:09:07 PM
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
        <title>SiraDEx | Edición del Tipo de Actividad  <bean:write name="tipoActividadForm"
                    property="nombreTipo"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición del Tipo de Actividad <bean:write 
                name="tipoActividadForm" property="nombreTipo"/> </h1>

        <logic:present name="tipoActividadForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="tipoActividadForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>

        <html:form method="POST" action ="/ModificarTipoActividad?method=update">
            <table>
                <tr>
                <td>Nombre de la Actividad<span style="color:red">*</span> </td>
            <td><html:text name="tipoActividadForm" property="nombreTipo">
                    <bean:write name="tipoActividadForm" property="nombreTipo"/>
                </html:text> </td>
        </tr>
        <tr>
        <td>Descripción<span style="color:red">*</span> </td>
    <td><html:textarea name="tipoActividadForm"  cols="80" rows="4"
                   property="descripcion">
            <bean:write name="tipoActividadForm" property="descripcion"/>
        </html:textarea>
    </td>
</tr>
<tr>
<td>Tipo<span style="color:red">*</span> </td>
<td><html:select property="tipoPR">
        <html:option value ="${tipoPR}" >
            <bean:write name="tipoActividadForm" property="tipoPR"/></html:option>
        <logic:equal name="tipoActividadForm" property="tipoPR" value="P">
            <html:option value="R">R</html:option>
        </logic:equal>
        <logic:equal name="tipoActividadForm" property="tipoPR" value="R">
            <html:option value="P">P</html:option>
        </logic:equal>
    </html:select>
</td>
</tr>
<tr>
<td>Programa del tipo de actividad<span style="color:red">*</span> </td>
<td>
    <html:select property="programa">
        <html:option value ="${programa}" >
            <bean:write name="tipoActividadForm" property="programa"/></html:option>
        <html:optionsCollection name="programas" label="contenido" value="contenido"/>

    </html:select>
</td>
</tr>
<tr>
<td>Coordinación a validar<span style="color:red">*</span> </td>
<td>       
    <html:select property="validador">
        <html:option value ="${validador}" >
            <bean:write name="tipoActividadForm" property="validador"/></html:option>
        <html:optionsCollection name="coordinaciones" label="contenido" value="contenido"/>
    </html:select>
</td>
</tr>
<%--<tr>
<td>Realizado por<span style="color:red">*</span> </td>
<td>
    <html:multibox property="permiso">Empleados</html:multibox> Empleados<br>
    <html:multibox property="permiso">Estudiantes</html:multibox> Estudiantes<br>
    <html:multibox property="permiso">Profesores</html:multibox> Profesores<br>
    <html:multibox property="permiso">Obreros</html:multibox> Obreros 
</td>       
</tr>--%>
<tr>
<td>Producto<span style="color:red">*</span> </td>
<td><html:text name="tipoActividadForm" property="producto">
        <bean:write name="tipoActividadForm" property="producto"/>
    </html:text></td>
</tr>

<tr><td><b>Campos</b></td></tr>
<tr><td></td>
<td><b>Nombre</b></td>
<td><b>Tipo</b></td>
<td><b>Longitud</b></td>
<td><b>Obligatoriedad</b></td></tr>
<logic:iterate name="tipoActividadForm" property="campos" id="campos"
               indexId="index">

<tr><td></td>
<td><html:text name="campos" property="nombre" indexed="true">
        <bean:write name="campos" property="nombre"/>
    </html:text> </td>
<td><html:select name="campos" property="tipo" indexed="true">
        <html:option value ="${campos.tipo}" >
            <bean:write name="campos" property="tipo"/></html:option>
        <logic:equal name="campos" property="tipo" value="texto">
            <html:option value="textol">texto largo</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="fecha">fecha</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="archivo">archivo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="textol">
            <html:option value="texto">texto</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="fecha">fecha</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="archivo">archivo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="numero">
            <html:option value="textol">texto largo</html:option>
            <html:option value="texto">texto</html:option>
            <html:option value="fecha">fecha</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="archivo">archivo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="fecha">
            <html:option value="textol">texto largo</html:option>
            <html:option value="texto">texto</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="archivo">archivo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="catalogo">
            <html:option value="textol">texto largo</html:option>
            <html:option value="texto">texto</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="fehca">fecha</html:option>
            <html:option value="archivo">archivo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="archivo">
            <html:option value="textol">texto largo</html:option>
            <html:option value="texto">texto</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="fehca">fecha</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="checkbox">checkbox</html:option>
        </logic:equal>
        <logic:equal name="campos" property="tipo" value="checkbox">
            <html:option value="textol">texto largo</html:option>
            <html:option value="texto">texto</html:option>
            <html:option value="numero">número</html:option>
            <html:option value="fehca">fecha</html:option>
            <html:option value="catalogo">catálogo</html:option>
            <html:option value="archivo">archivo</html:option>
        </logic:equal>
    </html:select>

    <logic:equal name="campos" property="tipo" value="catalogo">
        <html:select name="campos" property="catalogo" indexed="true">
            <html:option value ="${campos.catalogo}" >
                <bean:write name="campos" property="catalogo"/></html:option>
            <html:optionsCollection name="catalogos" label="nombre" value="nombre"/>
        </html:select>
    </logic:equal>
</td>
<td><html:text name="campos" property="longitud" indexed="true">
    <bean:write name="campos" property="longitud"/>
    </html:text></td>

<td><html:checkbox name="campos" property="obligatorio" indexed="true" value="true"/></td>

</tr>
</logic:iterate>

</table>
<br>

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el tipo de actividad?')"/></div>

</html:form>
</body>
</html>
