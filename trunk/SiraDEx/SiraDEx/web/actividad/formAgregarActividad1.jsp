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
        <title>SiraDEx | Registrar Actividad</title>
    </head>

    <body>
        <h1 class="title" id="page-title">Registro de Actividad</h1>
        <logic:present name="actividadForm" property="mensaje">
            <br/><b><div align="center">
                    <bean:write name="actividadForm" property="mensaje" />
                </div></b><br/>
            </logic:present>
            <html:form method="POST" action ="/RegistrarActividad?method=save">
            <table>
                <tr>
                <td><b>Tipo de Actividad </b></td>
                <td>
                    <html:select property="idTipoActividad">
                        <html:option value="">(Seleccione un tipo de actividad)</html:option>
                        <html:optionsCollection name="tipos"
                                                label="nombreTipo" value="id"/>
                    </html:select>
                </td>
            </tr>
        </table>
        <br>
        <div align="center"><html:submit>Siguiente</html:submit></div>
    </html:form>
</body>
</html>
