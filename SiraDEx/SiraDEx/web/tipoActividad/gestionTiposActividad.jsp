
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
        <logic:present name="tipoActividadForm" property="mensaje">
            <bean:write name="tipoActividadForm" property="mensaje" /><br/>
        </logic:present> 

        <html:link action="/RegistrarTipoActividad?method=page"> 
            Registrar tipo de actividad
        </html:link><br/>  

        <html:link action="/EliminarTipoActividad?method=page"> 
            Eliminar tipo de actividad
        </html:link> 

        <h1 class="title" id="page-title">Tipos de Actividades registradas en el sistema</h1>
        <logic:notPresent name="tipos">
            No hay tipos de actividad que mostrar
        </logic:notPresent>
        <logic:present name="tipos">
            <logic:empty name="tipos">
                No hay tipos de actividad que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="tipos" id="ta">
                    <tr>
                    <bean:write name="ta" property="nombreTipo"></bean:write><br>
                </tr>
        </logic:iterate>
    </table>
</logic:present>
</body>
</html>
