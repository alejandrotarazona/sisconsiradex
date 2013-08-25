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
        <logic:present name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:present>
        <logic:notPresent name="permiso">
            <h1 class="title">Registro de Actividad</h1>

            <logic:present name="tipos">
                <font size=2>Seleccione el Tipo de Actividad que desea agregar.</font>
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
        <logic:notPresent name="tipos">
            <br><br>
            <div align="center" style="font-size:1.2em">
                Por ahora no hay ningún Tipo de Actividad para los usuarios de tipo ${user.rol}
            </div>
        </logic:notPresent>
    </logic:notPresent>
</body>
</html>
