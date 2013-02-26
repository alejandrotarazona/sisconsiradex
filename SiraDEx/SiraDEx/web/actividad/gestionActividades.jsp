<%-- 
    Document   : gestionActividad
    Created on : 31/10/2012, 08:41:09 AM
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
        <title>SiraDEx | Gestion de Actividades</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Actividades</h1>
        <logic:notPresent name="TipoAct">
            <html:link action="/RegistrarActividad?method=page"> 
                Agregar Actividad
            </html:link><br/>
        </logic:notPresent>
        <logic:present name="TipoAct">
            <html:form action ="/RegistrarActividad?method=save">
                <html:hidden name="TipoAct" property="idTipoActividad" />
                <html:submit>Agregar actividad</html:submit>
            </html:form>
        </logic:present>

        <h1>Actividades registradas en el sistema</h1>
        <logic:present name="actividadForm" property="mensaje">
            <bean:write name="actividadForm" property="mensaje" /><br/>
        </logic:present>
        <logic:notPresent name="acts">
            <div align="center">No hay actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="acts">
            <logic:empty name="acts">
                No hay actividad que mostrar
            </logic:empty>
            <table> 

                <logic:iterate name="acts" id="act">
                    <tr><td>
                        <b><bean:write name="user" property="apellidos"></bean:write></b>, 
                        <b><bean:write name="user" property="nombres"></bean:write></b>,
                        "<bean:write name="act" property="nombreTipoActividad"/>"

                        <logic:iterate name="act" property="camposValores" 
                                       id="campoValor" indexId="index">

                            , <bean:write name="campoValor" property="valor"/> 

                        </logic:iterate>
                    </td> 
                    <td><html:form method="POST" action="/EliminarActividad">
                            <html:hidden name="act" property="idActividad" />
                            <html:submit styleId="botonEliminar"
                                         value=" "
                                         title="Eliminar"
                                         onclick="return confirm('¿Está seguro que desea eliminar la actividad?')" />
                        </html:form></td></tr>
                    </logic:iterate>         
        </table>
    </logic:present>

</body>
</html>
