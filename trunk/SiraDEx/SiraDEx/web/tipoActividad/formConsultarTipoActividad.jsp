<%-- 
    Document   : formConsultarTipoActividad
    Created on : 16/06/2013, 10:11:11 PM
    Author     : SisCon
--%>

<%@page import="Clases.Campo"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <style>
            .selector {width: 80px;}
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            SiraDEx | Consulta del Tipo de Actividad  <bean:write name="tipoActividadForm"
                        property="nombreTipo"/>
        </title>

    </head>
    <body>
        <logic:notPresent name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notPresent>
        <logic:present name="permiso">
            <html:link title="Ir a la Papelera" action="/GestionTiposActividad?method=listDisable"> 
                <html:img src="../Stylesheets/iconos/regresar.png"/>
            </html:link><br>
            <h1 class='title'>Consulta del Tipo de Actividad <bean:write 
                    name="tipoActividadForm" property="nombreTipo"/> </h1>

            <table>
                <tbody>
                    <tr>
                    <td width="15%"><b>Nombre del Tipo de Actividad</b></td>
                    <td>
                        <bean:write name="tipoActividadForm" property="nombreTipo"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Descripción</b></td>
                    <td>
                        <bean:write name="tipoActividadForm" property="descripcion"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Tipo de Producto</b></td>
                    <td>
                        <bean:write name="tipoActividadForm" property="tipoPR"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Programa</b></td>
                    <td>
                        <bean:write name="tipoActividadForm"  property="programa"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Dependencia a validar</b></td>
                    <td> 
                        <bean:write name="tipoActividadForm"  property="validador"/>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Realizado por</b></td>
                    <td>
                        <logic:iterate name="tipoActividadForm" property="permisos" id="permiso">
                            <bean:write name="permiso"/><br>
                        </logic:iterate> 
                    </td>       
                    </tr>
                </tbody>
            </table>

            <b>Campos</b><br>
            <table class="cebra">
                <tbody>
                    <tr>
                    <th></th>    
                    <th><b>Nombre</b></th>
                    <th><b>Tipo</b></th>
                    <th><b>Longitud/Límite</b></th>
                    <th><b>Obligatorio</b></th>
                    <th><b>Catálogo</b></th>
                    </tr>
                    <logic:iterate name="tipoActividadForm" property="campos" id="campos"
                                   indexId="index">

                        <tr>
                        <td><span style="color: gray;font-size:10px">${index+1}</span></td>
                        <td>
                        <span style="padding-left:6em;">
                            <bean:write name="campos" property="nombre"/>
                        </span>
                        </td>
                        <td align="center">
                            <bean:write name="campos" property="tipo"/>
                        </td>
                        <td align="center">
                            <logic:notEqual name="campos" property="tipo" value="archivo">
                                <logic:notEqual name="campos" property="tipo" value="checkbox">
                                    <bean:write name="campos"  property="longitud"/>
                                </logic:notEqual>
                            </logic:notEqual>  
                        </td>
                        <td align="center">
                            <html:checkbox name="campos" property="obligatorio" 
                                           disabled="true" indexed="true"/>
                        </td>
                        <td align="center">     
                            <bean:write name="campos" property="catalogo"/>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>
    </body>
</html>
