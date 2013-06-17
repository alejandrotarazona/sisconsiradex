<%-- 
    Document   : formParametrosBusqueda
    Created on : 20-may-2013, 19:06:32
    Author     : SisCon
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <style>
        .selector {width: 43px;}
    </style>
</head>
<div id="sidebar-first" class="sidebar grid-5 pull-13">
    <div class="region region-sidebar-first">
        <div id="block-system-main-menu" class="block block-system block-menu first">
            <div class="inner">
                <div class="content">
                    <script type="text/javascript" src="../Scripts/jquery-1.8.3.js"></script>
                    <script type="text/javascript" src="../Scripts/jquery-ui-1.9.2.custom.js"></script>
                    <link rel="stylesheet" type="text/css" 
                          href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>

                    <script type="text/javascript">
                        $(function() {
                            $(".fecha_input input").datepicker();
                            $(".fecha_click click").datepicker();
                        })	
                    </script>


                    <logic:present name="busquedaActividadForm" property="mensaje"><br>
                        <div class ="status"><bean:write name="tipoActividadForm" 
                                    property="mensaje" /></div>
                        </logic:present> 
                        <logic:present name="busquedaActividadForm" property="mensajeError"><br>
                        <div class ="error"><bean:write name="tipoActividadForm" 
                                    property="mensajeError" /></div>
                        </logic:present>
                        <% Usuario usuario = (Usuario) request.getSession().getAttribute("user");
                            String accion;
                            if (usuario == null) {
                                accion = "/BusquedaPublica?method=search";
                            } else {
                                accion = "/BusquedaAvanzada?method=search";
                            }
                        %>
                        <html:form action="<%=accion%>">

                        Mostrar <html:select name="busquedaActividadForm" property="mostrarPorPagina" 
                                     styleClass="selector" value="${busquedaActividadForm.mostrarPorPagina}">
                            <html:option value="10">10</html:option>
                            <html:option value="25">25</html:option>
                            <html:option value="50">50</html:option>
                            <html:option value="100">100</html:option>
                        </html:select>
                        actividades por página.
                        <br><br>

                        Tipo de Actividad:<br> 
                        <html:select name="busquedaActividadForm" property="nombreTipo">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="tiposdeactividad" label="nombreTipo" value="nombreTipo"/>
                        </html:select><br><br> 

                        Participante:<br>
                        <html:select name="busquedaActividadForm" property="participante">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="usuarios" label="contenido" value="mensaje"/>
                        </html:select><br><br>

                        <logic:present name="user">
                            Producto Tipo:<br> 
                            <html:radio property="tipoPR" value="">Cualquiera</html:radio>
                            <html:radio property="tipoPR" value="P">P</html:radio>
                            <html:radio property="tipoPR" value="R">R</html:radio>
                            <br><br>   
                        </logic:present>

                        Dependencia a la que pertenece:<br> 
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
                        <div align="right"><html:submit>Buscar</html:submit></div>
                    </html:form>

                </div>
            </div>
        </div>
    </div>
</div>
