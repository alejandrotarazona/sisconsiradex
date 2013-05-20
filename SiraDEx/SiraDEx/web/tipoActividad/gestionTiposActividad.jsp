
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Tipos de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Tipos de Actividad</h1>

        <html:link action="/RegistrarTipoActividad?method=page"> 
            Agregar Tipo de Actividad
        </html:link>
        <br>
        <html:link action="/GestionPapelera"> 
            Papelera
        </html:link>


        <h1>Tipos de Actividades registradas en el sistema</h1>
        <logic:present name="tipoActividadForm" property="mensaje">
            <br><div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div>
        </logic:present> 
        <logic:present name="tipoActividadForm" property="mensajeError">
            <br><div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div>
        </logic:present>
        <logic:notPresent name="tipos">
            No hay Tipo de Actividad que mostrar
        </logic:notPresent>
        <logic:present name="tipos">

            <table>
                <logic:iterate name="tipos" id="ta">
                    <tr>
                    <td>
                        <bean:write name="ta" property="nombreTipo"/>
                    </td>
                    <td>
                        <html:form method="POST" action="/ModificarTipoActividad?method=page">
                            <html:hidden name="ta" property="id" />
                            <html:submit styleId="botonModificar"
                                         value=" "
                                         title="Modificar"/>
                        </html:form>
                    </td>
                    <td><html:form method="POST" action="/EliminarTipoActividad">
                            <html:hidden name="ta" property="id" />
                            <html:submit styleId="botonEliminar"
                                         value=" "
                                         title="Eliminar"
                                         onclick="return confirm('¿Está seguro que desea eliminar el Tipo de Actividad?')" />
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </table>
    </logic:present>
</body>
</html>
