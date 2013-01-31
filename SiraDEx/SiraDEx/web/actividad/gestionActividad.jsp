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
        <logic:present name="actividadForm" property="mensaje">
            <bean:write name="actividadForm" property="mensaje" /><br/>
        </logic:present>
            
        <logic:equal name="user" property="rol" value="DEx">
            <html:link action="/ColeccionActividad"> 
                Ver actividades
            </html:link><br/>

            <html:link action="/ColeccionMisActividades"> 
                Ver mis actividades
            </html:link><br/>  

            <html:link action="/RegistrarActividad?method=page"> 
                Registrar actividad
            </html:link><br/>  

            <html:link action="/EliminarActividad?method=page"> 
                Eliminar actividad
            </html:link> 
        </logic:equal>
                
        <logic:equal name="user" property="rol" value="CU">
            <html:link action="/ColeccionMisActividades"> 
                Ver mis actividades
            </html:link><br/>  

            <html:link action="/RegistrarActividad?method=page"> 
                Registrar actividad
            </html:link><br/>     
                
            <html:link action="/EliminarMiActividad?method=page"> 
                Eliminar mi actividad  
            </html:link>       
        </logic:equal>
                
        <logic:equal name="user" property="rol" value="Prof">
            <html:link action="/ColeccionMisActividades"> 
                Ver mis actividades
            </html:link><br/>  

            <html:link action="/RegistrarActividad?method=page"> 
                Registrar actividad
            </html:link><br/>     
                
            <html:link action="/EliminarMiActividad?method=page"> 
                Eliminar mi actividad  
            </html:link>
        </logic:equal>

    </body>
</html>
