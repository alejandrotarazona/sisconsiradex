
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Elementos del catálogo</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de elementos del catálogo</h1>
        <logic:present name="elementoCatalogoForm" property="mensaje">
            <bean:write name="elementoCatalogoForm" property="mensaje" /><br/>
        </logic:present>

        <logic:equal name="user" property="rol" value="WM">

            <html:link action="/RegistrarElemento?method=page"> 
                Agregar elemento
            </html:link><br><br>  

        </logic:equal>


        <% out.print("<h1>Elementos del catálogo "+(String) request.getAttribute("nombreCat")+"</h1>");%>
        <logic:notPresent name="elementos">
            No hay elementos que mostrar
        </logic:notPresent>
        <logic:present name="elementos">
            <logic:empty name="elementos">
                No hay elementos que mostrar
            </logic:empty>
            <table>
                <tr>
                    <logic:iterate name="campos" id="campo">
                    <td>
                        <b><bean:write name="campo" property="campo.nombre"/></b>
                    </td>    
                </logic:iterate>  
            </tr>
            <logic:iterate name="elementos" id="elem">

                <tr>
                    <logic:iterate name="elem" property="camposValores" id="campoValor" indexId="index">
                    <td>
                        <bean:write name="campoValor" property="valor"/>
                    </td>
                </logic:iterate>
                <td>
                    <html:form method="POST" action="/EliminarElemento">
                        <html:hidden name="elem" property="idElemento" />
                        <html:submit styleId="botonEliminar"
                                     value=" "
                                     title="Eliminar"
                                     onclick="return confirm('¿Está seguro que desea eliminar el elemento?')" />
                    </html:form>
                </td>
                <br>
                </tr>

            </logic:iterate>

        </logic:present>

    </table>

</body>
</html>
