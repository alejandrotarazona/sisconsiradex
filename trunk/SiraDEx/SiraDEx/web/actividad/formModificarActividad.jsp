<%-- 
    Document   : formModificarActividad
    Created on : Mar 1, 2013, 1:56:33 AM
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
        <title>SiraDEx | Edición de <bean:write name="actividadForm"
                    property="nombreTipoActividad"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición de <bean:write 
                name="actividadForm" property="nombreTipoActividad"/> </h1>

        <logic:present name="actividadForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="actividadForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>

        <html:form method="POST" action ="/ModificarActividad?method=update">
            <table>

                <tr>
                <td><b>Nombre del campo</b></td>
                <td><b>Valor</b></td>
                <td><b>Tipo</b></td>
            </tr>

            <logic:iterate name="actividadForm" property="camposValores" 
                           id="camposValores" indexId="index">
                <tr>
                <td>
                    <bean:write name="camposValores" property="campo.nombre"/>
                </td>    

                <td><html:text name="camposValores" property="valor" indexed="true">
                        <bean:write name="camposValores" property="valor"/>
                    </html:text> 
                </td>
                <td>
                    <bean:write name="camposValores" property="campo.tipo"/>
                </td>
            </tr>
        </logic:iterate>

    </table>
    <br>

    <div align="center"><html:submit value="Modificar"
                 onclick="return confirm('¿Está seguro que desea modificar la actividad?')"/></div>

</html:form>
</body>
</html>
