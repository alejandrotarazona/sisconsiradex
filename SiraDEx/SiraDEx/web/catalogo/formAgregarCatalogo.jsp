
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Agregar Catálogo</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registro de Catálogos</h1>
        <p>Los campos con el asterisco  <span style="color:red">*</span> son obligatorios.</p>
        <logic:present name="catalogoForm" property="mensaje">
            <br/><bean:write name="catalogoForm" property="mensaje" /><br/>
        </logic:present>
        <html:form method="POST" action ="/RegistrarCatalogo?method=save">
            <table>
                <tr>
                    <td>Nombre<span style="color:red">*</span> </td>
                    <td><html:text name="catalogoForm" property="nombre"></html:text></td>
                </tr>

                <tr><td>Numero de campos<span style="color:red">*</span> </td>
                    <td><html:text name="catalogoForm" property="nroCampos"></html:text></td>
                </tr>
                <tr><td><html:submit> Siguiente </html:submit></td></tr>
            </table>
        </html:form>
    </body>
</html>
