<%-- 
    Document   : formModificarElemento
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
        <title>SiraDEx | Edición de los elementos del Catálogo  <bean:write name="elementoCatalogoForm"
                    property="nombreCatalogo"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición de los elementos del Catálogo <bean:write 
                name="elementoCatalogoForm" property="nombreCatalogo"/> </h1>

        <logic:present name="elementoCatalogoForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="elementoCatalogoForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>

        <html:form method="POST" action ="/ModificarElementoCatalogo?method=update">
            <table>

                <tr>
                <td><b>Nombre del campo</b></td>
                <td><b>Valor</b></td>
                <td><b>Tipo</b></td>
            </tr>

            <logic:iterate name="elementoCatalogoForm" property="camposValores" 
                           id="camposValores" indexId="index">
                <tr>
                <td>
                    <bean:write name="camposValores" property="campo.nombre"/>
                </td>    

                <td><html:text name="camposValores" property="valor" indexed="true">
                        <bean:write name="camposValores" property="valor"/>
                    </html:text> 
                </td>
                <td>
                    <bean:write name="camposValores" property="campo.tipo"/>
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
