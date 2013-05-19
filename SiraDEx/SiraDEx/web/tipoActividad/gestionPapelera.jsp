
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
        
        <br><logic:present name="tipoActividadForm" property="mensaje">
            <b><div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div></b>
                </logic:present> 
            <br><logic:present name="tipoActividadForm" property="mensajeError">
            <b><div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div></b>
            </logic:present>
            
            <h1>Tipos de Actividades eliminadas del sistema</h1>
        <logic:notPresent name="tipos">
            No hay tipos de actividad que mostrar
        </logic:notPresent>
        <logic:present name="tipos">
           
            <table>
                <logic:iterate name="tipos" id="ta">
                    <tr>
                    <td>
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
        </table>
    </logic:present>
            
            
    </body>
</html>
