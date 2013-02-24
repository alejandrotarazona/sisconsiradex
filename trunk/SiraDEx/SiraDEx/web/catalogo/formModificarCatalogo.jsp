<%-- 
    Document   : formModificarCatalogo
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
        <title>SiraDEx | Edición del Catálogo  <bean:write name="catalogoForm"
                    property="nombre"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición del Catálogo <bean:write 
                name="catalogoForm" property="nombre"/> </h1>

        <logic:present name="catalogoForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="catalogoForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>
                <html:form method="POST" action ="/ModificarCatalogo?method=add">

        <tr>
        <td>Agregar nuevos campos <html:text name="catalogoForm" 
                   property="nroCampos" size="1" maxlength="2"/>
            <html:submit styleId="botonAgregar"
                         value=" "
                         title="Agregar"/> </td></tr>

</html:form>
<html:form method="POST" action ="/ModificarCatalogo?method=update">
    <table>
        <tr>
        <td><b>Nombre del cátalogo</b></td>

        <td><html:text name="catalogoForm" property="nombre">
                <bean:write name="catalogoForm" property="nombre"/>
            </html:text> 

            </tr>
        <tr><td><b>Campos</b></td></tr>
    <tr><td></td><td>Nombre</td><td>Tipo</td></tr>
    <logic:iterate name="catalogoForm" property="campos" id="campos" 
                   indexId="index">

    <tr><td></td>
    <td><html:text name="campos" property="nombre" indexed="true">
            <bean:write name="campos" property="nombre"/>
        </html:text> </td>
    <td><html:select name="campos" property="tipo" indexed="true">
            <html:option value ="${campos.tipo}" >
                <bean:write name="campos" property="tipo"/></html:option>
            <logic:equal name="campos" property="tipo" value="texto">
                <html:option value="numero">numero</html:option>
                <html:option value="fecha">fecha</html:option>
            </logic:equal>
            <logic:equal name="campos" property="tipo" value="numero">
                <html:option value="texto">texto</html:option>
                <html:option value="fecha">fecha</html:option>
            </logic:equal>
            <logic:equal name="campos" property="tipo" value="fecha">
                <html:option value="texto">texto</html:option>
                <html:option value="numero">numero</html:option>
            </logic:equal>

        </html:select>
    </td>
</tr>
</logic:iterate>

<logic:present  name="catalogoForm" property="camposAux">
    <logic:greaterThan name="catalogoForm" property="nroCampos" value="0">

        <tr>
        <tr><td><b>Nuevos campos</b></td></tr>
    </tr>            
    <logic:iterate name="catalogoForm" property="camposAux" id="camposAux" 
                   indexId="index">
        <tr><td></td>
        <td><html:text name="camposAux" property="nombre" indexed="true"/></td>

        <td><html:select name="camposAux" property="tipo" indexed="true">
                <html:option value="texto">texto</html:option>
                <html:option value="numero">numero</html:option>
                <html:option value="fecha">fecha</html:option>
            </html:select>
        </td>
    </tr>
</logic:iterate>

</logic:greaterThan>
</logic:present> 
</table>
<br>

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el catálogo?')"/></div>

</html:form>
</body>
</html>
