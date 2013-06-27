<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <style>
            .selector {width: 70px;}
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registo de Campos de Catálogo</title>
    </head>
    <body>
        <h1 class="title">Registro de Campos del Catálogo 
            <bean:write name="catalogoForm" property="nombre"/> </h1>

        <logic:present name="catalogoForm" property="mensaje"><br>
            <div class ="error"><bean:write name="catalogoForm" property="mensaje"/></div>
            <br>
        </logic:present> 

        <table>
            <tr>
            <td width="25%">
                <b>Nombre</b>
            </td>
            <td>
                <b>Tipo</b>
            </td>
        </tr>            

        <html:form action="/RegistrarCatalogo?method=save2">
            <logic:iterate name="catalogoForm" property="campos" id="campos" 
                           indexId="index">
                <tr>
                <td><span style="color: gray;font-size:10px">${index+1}</span>
                <logic:notEqual name="campos" property="tipo" value="usbid">
                    <html:text name="campos" property="nombre" indexed="true"/>
                </logic:notEqual>
                <logic:equal name="campos" property="tipo" value="usbid">
                    <html:text name="campos" property="nombre" indexed="true" 
                               disabled="true"/> 
                </logic:equal>
            </td>

            <td><logic:notEqual name="campos" property="tipo" value="usbid">
                    <html:select name="campos" property="tipo" indexed="true"
                                 styleClass="selector">
                        <html:option value="texto">texto</html:option>
                        <html:option value="numero">numero</html:option>
                        <html:option value="fecha">fecha</html:option>
                    </html:select>
                </logic:notEqual>
                <logic:equal name="campos" property="tipo" value="usbid">
                    <html:select name="campos" property="tipo" indexed="true" 
                                 disabled="true" styleClass="selector">
                        <html:option value="texto">texto</html:option>
                    </html:select>
                </logic:equal>
            </td>
        </tr>
    </logic:iterate>
</table>                    
<br>
<div align="center"><html:submit>Registrar</html:submit></div>

</html:form>
</body>
</html>
