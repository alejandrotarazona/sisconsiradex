<%-- 
    Document   : formAgregarCampos
    Created on : 02/11/2012, 05:14:54 PM
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
        <title>SiraDEx | Registrar Campos del Tipo de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registro de Campos de <bean:write 
                name="tipoActividadForm" property="nombreTipo"/> </h1>
        <logic:present name="tipoActividadForm" property="mensaje">
            <br/><div align="center"><b>
                    <bean:write name="tipoActividadForm" property="mensaje" />
                </b></div><br/>
                </logic:present>
        <table>
            <tr>
            <td><b>Nombre</b></td>
            <td><b>Tipo</b></td>
            <td><b>Longitud</b></td>
            <td><b>Obligatoriedad</b></td>
            <td><b>Catálogo</b></td>
        </tr>            

        <html:form action="/RegistrarTipoActividad?method=save2">
            <logic:iterate name="tipoActividadForm" property="campos" id="campo" indexId="index">
                <tr>
                <td><html:text name="campo" property="nombre" indexed="true"/></td>

                <td><html:select name="campo" property="tipo" indexed="true">
                        <html:option value="texto">texto</html:option>
                        <html:option value="textol">texto largo</html:option>
                        <html:option value="numero">numero</html:option>
                        <html:option value="fecha">fecha</html:option>
                        <html:option value="catalogo">catálogo</html:option>
                        <html:option value="archivo">archivo</html:option>
                        <html:option value="checkbox">checkbox</html:option>
                    </html:select></td>

                <td><html:text name="campo" property="longitud" indexed="true"/></td>

                <td><html:checkbox name="campo" property="obligatorio" indexed="true" value="true"/></td>

                <td><html:select name="campo" property="tipo" indexed="true"><%--por ahora property=tipo--%>
                        <html:option value="">NO APLICA</html:option>
                        <html:optionsCollection name="catalogos" label="nombre" value="nombre"/>
                    </html:select></td>

            </tr>
        </logic:iterate>
    </table>
    <br>
    <div align="center"><html:submit>Registrar</html:submit></div>

</html:form>
</body>
</html>
