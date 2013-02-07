
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registrar Elemento</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registrar Elemento</h1>
        <logic:present name="elementoCatalogoForm" property="mensaje">
            <bean:write name="elementoCatalogoForm" property="mensaje" />
        </logic:present>
        <html:form method="POST" action ="/RegistrarElemento?method=save">

        <tr>
        <td><strong>Catalogo:</strong></td>
        <td><label>
            <html:select property="idCatalogo">
                <html:option value="">(Seleccione un catalogo)</html:option>
                <html:optionsCollection name="catalogos"
                                        label="nombre" value="idCatalogo"/>
            </html:select>
        </label></td>
</tr>

<html:submit>Siguiente</html:submit>
</html:form>
</body>
</html>
