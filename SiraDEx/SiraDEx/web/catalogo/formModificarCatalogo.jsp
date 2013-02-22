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
                <html:form method="POST" action ="/ModificarCatalogo?method=update">
            <table>
                <tr>
                <td>Nombre del cátalogo</td>

                <td><html:text name="catalogoForm" property="nombre">
                        <bean:write name="catalogoForm" property="nombre"/>
                    </html:text> 

                    </tr>
                <tr><td>Campos:</td></tr>
                <tr><td>Nombre</td><td>Tipo</td></tr>
            <logic:iterate name="catalogoForm" property="campos" id="campos" 
                           indexId="index">

                <tr>
                <td><html:text name="campos" property="nombre" indexed="true">
                        <bean:write name="campos" property="nombre"/>
                    </html:text> </td>
                <td><html:select name="campos" property="tipo" indexed="true">
                        <html:option value="tipo"> <bean:write name="campos" property="tipo"/></html:option>
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

    </table>
    <br>
    <div align="center"><html:submit value="Modificar"
                 onclick="return confirm('¿Está seguro que desea modificar el catálogo?')"/></div>

</html:form>
</body>
</html>
