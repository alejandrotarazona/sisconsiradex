<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registrar Campos del Catalogo</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registrar Campos del Catalogo</h1>
        <logic:present name="catalogoForm" property="mensaje">
            <bean:write name="catalogoForm" property="mensaje" /><br/>
        </logic:present>
        <table>
            <tr>
            <td><b>Nombre</b></td>
            <td><b>Tipo</b></td>
        </tr>            

        <html:form action="/RegistrarCatalogo?method=save2">
            <logic:iterate name="catalogoForm" property="campos" id="campos" indexId="index">
                <tr>
                <td><html:text name="campos" property="nombre" indexed="true"/></td>

                <td><html:select name="campos" property="tipo" indexed="true">
                        <html:option value="texto">texto</html:option>
                        <html:option value="numero">numero</html:option>
                        <html:option value="fecha">fecha</html:option>
                    </html:select>
                </td>
            </tr>
        </logic:iterate>
    </table>
    <br>
    <html:submit>Registrar</html:submit>

</html:form>
</body>
</html>
