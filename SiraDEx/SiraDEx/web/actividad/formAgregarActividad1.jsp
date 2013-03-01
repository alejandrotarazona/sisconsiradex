<%-- 
    Document   : formAgregarActividad1
    Created on : Oct 26, 2012, 8:37:29 AM
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
        <title>SiraDEx | Agregar Actividad</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registro de Actividad</h1>
        <logic:present name="actividadForm" property="mensaje">
            <br/><b><div align="center">
                    <bean:write name="actividadForm" property="mensaje" />
                </div></b><br/>
            </logic:present>
        Seleccione la Actividad que desea agregar.
        <logic:present name="tipos">
            <table>
                <logic:iterate name="tipos" id="ta">
                    <tr>
                    <td><html:link action="/RegistrarActividad?method=save" 
                               paramName="ta" paramProperty="idTipoActividad" 
                               paramId="idTipoActividad">
                            <ul><bean:write name="ta" property="nombreTipo"/></ul>
                        </html:link> 
                        </td>
                    </tr>
            </logic:iterate>
        </table>

    </logic:present>       
</body>
</html>
