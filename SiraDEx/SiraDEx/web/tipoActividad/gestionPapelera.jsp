
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Papelera de Tipos de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Tipos de Actividad Eliminados</h1>

        <logic:present name="tipoActividadForm" property="mensaje"><br>
            <div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div>
        </logic:present> 
        <logic:present name="tipoActividadForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div>
        </logic:present>

        <h1>Tipos de Actividades eliminadas del sistema</h1>
        <logic:notPresent name="tipos">
            <div align="center">No hay Tipo de Actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="tipos">        
            <table>
                <tbody>
                    <logic:iterate name="tipos" id="ta">
                        <tr>
                        <td width="50%">
                            <bean:write name="ta" property="nombreTipo"/>
                        </td>

                        <td><html:form method="POST" action="/RestaurarTipoActividad">
                                <html:hidden name="ta" property="id" />
                                <html:submit styleId="botonRestaurar"
                                             value=" "
                                             title="Restaurar"
                                             onclick="return confirm('¿Está seguro que desea restaurar el Tipo de Actividad?')" />
                            </html:form>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>


    </body>
</html>
