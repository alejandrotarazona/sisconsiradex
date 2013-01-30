<%-- 
    Document   : formAgregarActividad2
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
        <title>SiraDEx | Registrar Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registrar Actividad</h1>
        
        <logic:present name="actividadForm" property="mensaje">
            <bean:write name="actividadForm" property="mensaje" /><br/>
        </logic:present>
        <p>Los campos con el asterisco  <span style="color:red">*</span> son obligatorios.</p></br>
        <table>           
            <html:form action="/RegistrarActividad?method=save2">
                
                <logic:iterate name="actividadForm" property="camposValores" id="campoValor" indexId="index">
                <tr>
                <td><bean:write name="campoValor" property="campo.nombre"></bean:write>
                    <logic:equal name="campoValor" property="campo.obligatorio" value="true">
                    <span style="color:red">*</span>  
                </logic:equal>
            </td>
            <td><logic:equal name="campoValor" property="campo.tipo" value="texto">
                    <html:text name="campoValor" property="valor" indexed="true"/></td>  
                </logic:equal>
                <logic:equal name="campoValor" property="campo.tipo" value="numero">
                    <html:text name="campoValor" property="valor" indexed="true"/></td>  
                </logic:equal>
                <logic:equal name="campoValor" property="campo.tipo" value="fecha">
                    <html:text name="campoValor" property="valor" indexed="true"/></td>  
                </logic:equal>
                <logic:equal name="campoValor" property="campo.tipo" value="hora">
                    <html:text name="campoValor" property="valor" indexed="true"/></td>  
                </logic:equal>
                <logic:equal name="campoValor" property="campo.tipo" value="checkbox">
                    <html:checkbox name="campoValor" property="valor" indexed="true"/></td>  
                </logic:equal>
                
        </tr>
    </logic:iterate>
</table>
<br>
<html:submit>Registrar</html:submit>

</html:form>
</body>
</html>
