<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Catalogos</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Catalogos</h1>
        <logic:present name="catalogoForm" property="mensaje">
            <bean:write name="catalogoForm" property="mensaje" /><br/>
        </logic:present>

        <html:link action="/RegistrarCatalogo?method=page"> 
            Registrar catalogo
        </html:link><br/>

        <h1 class="title" id="page-title">Catalogos registrados en el sistema</h1>
        <logic:notPresent name="catalogos">
            No hay catalogos de actividad que mostrar
        </logic:notPresent>
        <logic:present name="catalogos">
            <logic:empty name="catalogos">
                No hay catalogos de actividad que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="catalogos" id="cat">
                    <tr>
                    <td>
                        <bean:write name="cat" property="nombre"></bean:write>
                    </td>
                    <td>
                        <html:form method="POST" action="/EliminarCatalogo">
                            <html:hidden name="cat" property="idCatalogo" />
                            <html:submit styleId="botonEliminar"
                                         value=" "
                                         title="Eliminar"
                                         onclick="return confirm('¿Está 
                                         seguro que desea eliminar el Catalogo?')" />
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </table>
    </logic:present>

</body>
</html>
