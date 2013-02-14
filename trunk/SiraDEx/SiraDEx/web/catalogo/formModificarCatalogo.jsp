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
        <title>SiraDEx | Edición de Catálogo</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Edición de Catálogo</h1>

        <logic:present name="catalogoForm" property="mensaje">
            <br/> <div align="center"><bean:write name="catalogoForm" 
                        property="mensaje" /></div><br/>
            </logic:present>
            <html:form method="POST" action ="/ModificarCatalogo?method=update">
            <table>
                <tr>
                <td>Nombre del cátalogo</td>

                <td><html:text name="catalogoForm" property="nombre" 
                    value='<%=(String) request.getAttribute("nombreCat")%>'/></td>
            </tr>
            <tr><td>Nombre de los campos</td></tr>

        <logic:iterate name="catalogoForm" property="campos" id="campos" 
                       indexId="index">
            <%
                String[] nombres = (String[]) request.getSession().getAttribute("nombres");
                int i = (Integer) pageContext.findAttribute("index");%>

            <tr><td></td>
            <td><html:text name="campos" property="nombre" value='<%=nombres[i + 1]%>' 
                       indexed="true"/></td>
        </tr>
    </logic:iterate>
</table>
<br>
<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el catálogo?')"/></div>

</html:form>
</body>
</html>
