<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Catálogos</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestión de Catálogos</h1>

        <html:link action="/RegistrarCatalogo?method=page"> 
            Agregar Catálogo
        </html:link><br/>

        <logic:present name="catalogoForm" property="mensaje">
            <div align="center"><bean:write name="catalogoForm" 
                        property="mensaje" /></div><br/>
            </logic:present>

        <h1>Catálogos registrados en el sistema</h1>
        <logic:notPresent name="catalogos">
            <p align="center">No hay catálogos que mostrar.</p>
        </logic:notPresent>
        <logic:present name="catalogos">
            <logic:empty name="catalogos">
                <p align="center">No hay catálogos que mostrar.</p>
            </logic:empty>
            <table>
                <logic:iterate name="catalogos" id="cat">
                    <tr>
                    <td>
                        <bean:write name="cat" property="nombre" />
                    </td>
                    <td>
                        <html:form method="POST" action="/ModificarCatalogo?method=page">
                            <html:hidden name="cat" property="idCatalogo" />
                            <html:submit styleId="botonModificar"
                                         value=" "
                                         title="Modificar"/>
                        </html:form>
                    </td>
                    <td>
                        <html:form method="POST" action="/AGestionElementos">
                            <html:hidden name="cat" property="idCatalogo" />
                            <html:submit styleId="botonExaminar"
                                         value=" "
                                         title="Consultar"/>
                        </html:form>
                    </td>
                    <td>
                        <html:form method="POST" action="/EliminarCatalogo">
                            <html:hidden name="cat" property="idCatalogo" />
                            <html:submit styleId="botonEliminar"
                                         value=" "
                                         title="Eliminar"
                                         onclick="return confirm('¿Está seguro que desea eliminar catálogo?')"/>
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </table>
    </logic:present>

</body>
</html>
