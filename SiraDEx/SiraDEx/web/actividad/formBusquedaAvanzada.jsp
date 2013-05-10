<%-- 
    Document   : formBusquedaAvanzada
    Created on : May 2, 2013, 1:27:11 PM
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
        <script type="text/javascript">
            $(function() {
                $(".fecha_input input").datepicker();
                $(".fecha_click click").datepicker();
            })	
        </script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery-ui-1.9.2.custom.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Búsquedas</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Indique los parámetros de su búsqueda</h1>
        <logic:present name="tipoActividadForm" property="mensaje"><br>
            <b><div class ="status"><bean:write name="tipoActividadForm" 
                        property="mensaje" /></div></b>
                </logic:present> 
                <logic:present name="tipoActividadForm" property="mensajeError"><br>
            <b><div class ="error"><bean:write name="tipoActividadForm" 
                        property="mensajeError" /></div></b>
                </logic:present>

        <html:form action="/BusquedaAvanzada.do?method=search">
            <table>
                <tr>
                    <td>
                        Tipo de Actividad: 
                    </td>
                    <td>
                        <html:select name="busquedaAvanzadaForm" property="nombreTipo">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name="tiposdeactividad" label="nombreTipo" value="nombreTipo"/>
                        </html:select>
                    </td> 
                </tr>
                <tr>
                    <td>
                        Creador:
                    </td> 
                    <td>
                        <html:text name="busquedaAvanzadaForm" property="creador"/>
                    </td> 
                </tr>
                <tr>
                    <td>
                        Participante(s):
                    </td> 
                    <td>
                        <html:text name="busquedaAvanzadaForm" property="participantes" styleId="participantes1" value=""/>
                    </td>
                </tr>

    <%--       <tr><td>Fecha de Creación:</td>
<td><span class="fecha_input">
    <html:text name="busquedaAvanzadaForm" property="fechaCreacion" indexed="true" 
               readonly="true" />
</span>
<span class="fecha_click">
    <html:hidden name="busquedaAvanzadaForm" property="fechaCreacion" indexed="true"/>
</span></td></tr>
<br>
<tr><td>Fecha de la última modificación:</td>
<td><span class="fecha_input">
    <html:text name="busquedaAvanzadaForm" property="fechaModif" indexed="true" 
               readonly="true" />
</span>
<span class="fecha_click">
    <html:hidden name="busquedaAvanzadaForm" property="fechaModif" indexed="true"/>
</span></td></tr>
<br> --%>
                <tr>
                    <td>
                        Tipo: 
                    </td>
                    <td>
                        <html:radio property="tipoPR" value="P" bundle="P" >P</html:radio>
                        <html:radio property="tipoPR" value="R" bundle="R">R</html:radio>
                    </td>
                </tr>

                <tr>
                    <td>
                        Validador: 
                    </td>
                    <td>
                        <html:select name="busquedaAvanzadaForm" property="validador">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>
                        Programa: 
                    </td>
                    <td><html:select name="busquedaAvanzadaForm" property="programa">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name="programas" label="contenido" value="contenido"/>
                        </html:select>
                    </td>
                </tr>
        </table>
                        Mostrar <html:text name="busquedaAvanzadaForm" property="mostrarPorPagina" size="1" maxlength="3" value="10"/> actividades por pagina.
                <div align="center"><html:submit>Buscar</html:submit></div>
        </html:form>
            <logic:present name="actividades">
                <logic:empty name="actividades">
                    No hay actividades que mostrar
                </logic:empty>
                <logic:notEmpty name="actividades">
                    <logic:iterate name="actividades" id="act">
                        <bean:write name="act" property="nombreTipo"/>, <br>
                        <%-- <logic:iterate name="act" property="camposValores" id="campoV" indexId="index">
                            <logic:notEqual name="campoV" property="campo.tipo" value="archivo">
                                <bean:write name="campoV" property="valor"/>
                            </logic:notEqual>
                            <logic:equal name="campoV" property="campo.tipo" value="archivo">
                                Archivo
                            </logic:equal>
                        </logic:iterate> --%>
                        <br>
                    </logic:iterate>
                </logic:notEmpty>
            </logic:present>
    </body>
    
</html>
