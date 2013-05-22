<%-- 
    Document   : findPublicMenu
    Created on : 20-may-2013, 19:06:32
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<div id="sidebar-first" class="sidebar grid-5 pull-11">
    <div class="region region-sidebar-first">
        <div id="block-system-main-menu" class="block block-system block-menu first">
            <div class="inner">
                <div class="content">
                    <script type="text/javascript" src="Interfaz/Scripts/jquery-1.8.3.js"></script>
                    <script type="text/javascript" src="Interfaz/Scripts/jquery-ui-1.9.2.custom.js"></script>
                    <link rel="stylesheet" type="text/css" 
                          href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>

                    <script type="text/javascript">
                        $(function() {
                            $(".fecha_input input").datepicker();
                            $(".fecha_click click").datepicker();
                        })	
                    </script>

                    <h1>Indique los parámetros de la búsqueda</h1>


                    <logic:present name="busquedaActividadForm" property="mensaje"><br>
                        <div class ="status"><bean:write name="tipoActividadForm" 
                                    property="mensaje" /></div>
                        </logic:present> 
                        <logic:present name="busquedaActividadForm" property="mensajeError"><br>
                        <div class ="error"><bean:write name="tipoActividadForm" 
                                    property="mensajeError" /></div>
                        </logic:present>

                    <html:form action="/BusquedaPublica?method=search">

                        <div align="right"><html:submit>Buscar</html:submit></div>

                        Tipo de Actividad:<br> 
                        <html:select name="busquedaActividadForm" property="nombreTipo">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="tiposdeactividad" label="nombreTipo" value="nombreTipo"/>
                        </html:select><br><br> 

                        Participante:<br>
                        <html:select name="busquedaActividadForm" property="creador">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="usuarios" label="contenido" value="mensaje"/>
                        </html:select><br><br>


                        Validador:<br> 
                        <html:select name="busquedaActividadForm" property="validador">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
                        </html:select><br><br>

                        Programa:<br> 
                        <html:select name="busquedaActividadForm" property="programa">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="programas" label="contenido" value="contenido"/>
                        </html:select><br><br>

                        Período:<br>
                        <span class="fecha_input">
                            Desde
                            <html:text name="busquedaActividadForm" property="fechaInic" size="8"/>
                        </span>
                        <span class="fecha_click">
                            <html:hidden name="busquedaActividadForm" property="fechaInic"/>
                        </span><br>
                        <span class="fecha_input">
                            Hasta&nbsp;
                            <html:text name="busquedaActividadForm" property="fechaFin" size="8"/>
                        </span>
                        <span class="fecha_click">
                            <html:hidden name="busquedaActividadForm" property="fechaFin"/>
                        </span>

                        <br><br>

                        Mostrar <html:text name="busquedaActividadForm" property="mostrarPorPagina" 
                                   size="1" maxlength="3" value="${busquedaActividadForm.mostrarPorPagina}"/>
                        actividades por pagina.
                        <br><br>
                        <div align="right"><html:submit>Buscar</html:submit></div>
                    </html:form>

                </div>
            </div>
        </div>
    </div>
</div>
