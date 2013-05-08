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
        <h1>Indique los parámetros de su búsqueda</h1>
        <br>
        <br>
        <html:form action="/BuscarActividad">
            <p>Tipo de Actividad: </p> 
            <html:select name="busquedaActividadForm" property="nombreTipoActividad">
                <html:option value="">-- Seleccione --</html:option>
                <html:optionsCollection name="tiposdeactividad" label="contenido" value="contenido"/>
            </html:select>
            <br>
            <p>Participante: </p>
            <html:text name="busquedaActividadForm" property="participantes"/>
            <br>
            <p>Creador: </p>
            <html:text name="busquedaActividadForm" property="creador"/>
            <br>
            <%--       <p>Fecha de Creación: </p>
        <span class="fecha_input">
            <html:text name="busquedaActividadForm" property="fechaCreacion" indexed="true" 
                       readonly="true" />
        </span>
        <span class="fecha_click">
            <html:hidden name="busquedaActividadForm" property="fechaCreacion" indexed="true"/>
        </span>
        <br>
        <p>Fecha de la última modificación:</p>
        <span class="fecha_input">
            <html:text name="busquedaActividadForm" property="fechaModif" indexed="true" 
                       readonly="true" />
        </span>
        <span class="fecha_click">
            <html:hidden name="busquedaActividadForm" property="fechaModif" indexed="true"/>
        </span>
<br> --%>
            <p>Tipo: </p>
            <html:radio property="tipoPR" value="P" bundle="P" >P</html:radio>
            <html:radio property="tipoPR" value="R" bundle="R">R</html:radio>

            <p>Validador: </p>
            <html:select name="busquedaActividadForm" property="validador">
                <html:option value="">-- Seleccione --</html:option>
                <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
            </html:select>
            <br>
            <p>Programa: </p>
            <html:select name="busquedaActividadForm" property="programa">
                <html:option value="">-- Seleccione --</html:option>
                <html:optionsCollection name="programas" label="contenido" value="contenido"/>
            </html:select>
            <div align="center"><html:submit>Buscar</html:submit></div>


        </html:form>


    </body>
</html>
