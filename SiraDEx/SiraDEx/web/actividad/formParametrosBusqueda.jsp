<%-- 
    Document   : formParametrosBusqueda
    Created on : 20-may-2013, 19:06:32
    Author     : SisCon
--%>

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
<div id="sidebar-first" class="sidebar grid-5 pull-13" style="overflow-y: auto;max-height: 420px;">
    <div class="region region-sidebar-first">
        <div id="block-system-main-menu" class="block block-system block-menu first">
            <div class="inner">
                <div class="content">
                    <script type="text/javascript" src="../Scripts/jquery-1.8.3.js"></script>
                    <script type="text/javascript" src="../Scripts/jquery-ui-1.9.2.custom.js"></script>

                    <script type="text/javascript">
                        $(function() {
                            $(".fecha_input input").datepicker();
                            $(".fecha_click click").datepicker();
                        });
                    </script>


                    <% String permiso = (String) request.getSession().getAttribute("permiso");
                        String accion;
                        if (permiso == null) {
                            accion = "/BusquedaPublica?method=search";
                        } else {
                            accion = "/BusquedaAvanzada?method=search";
                        }
                    %>
                    <html:form action="<%=accion%>">
                        <div align="right"><html:submit>Buscar</html:submit></div>
                            <br>
                            <b>Mostrar</b> <html:select name="busquedaActividadForm" property="mostrarPorPagina" 
                                     styleClass="selector" value="${busquedaActividadForm.mostrarPorPagina}">
                            <html:option value="10">10</html:option>
                            <html:option value="25">25</html:option>
                            <html:option value="50">50</html:option>
                            <html:option value="100">100</html:option>
                        </html:select>
                        actividades por página
                        <br><br>

                        <b>Tipo de Actividad:</b><br> 
                        <html:select name="busquedaActividadForm" property="nombreTipo">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="tiposdeactividad" label="nombreTipo" value="nombreTipo"/>
                        </html:select><br><br> 

                        <b>Participante:</b><br>
                        <html:select name="busquedaActividadForm" property="participante">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="participantes" label="contenido" value="mensaje"/>
                        </html:select><br><br>

                        <logic:present name="permiso">
                            <b>Producto Tipo:</b><br> 
                            <html:radio property="tipoPR" value="">Cualquiera</html:radio>
                            <html:radio property="tipoPR" value="P"
                                        title="Evaluables por pares académicos">
                                P
                            </html:radio>
                            <html:radio property="tipoPR" value="R"
                                        title="No evaluables por pares académicos">
                                R
                            </html:radio>
                            <br><br>   
                        </logic:present>

                        <b>Dependencia a la que pertenece:</b><br> 
                        <html:select name="busquedaActividadForm" property="validador">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
                        </html:select><br><br>

                        <b>Programa:</b><br> 
                        <html:select name="busquedaActividadForm" property="programa">
                            <html:option value="">-- Cualquiera --</html:option>
                            <html:optionsCollection name="programas" label="contenido" value="contenido"/>
                        </html:select><br><br>

                        <b>Que contenga:</b><br>
                        <html:text name="busquedaActividadForm" size="27" property="palabras"
                                   title="Para buscar una secuencia de palabras encierrela entre comillas. La búsqueda encontrará toda actividad que contenga dentro de su nombre, campos, valores o descripción alguna de las palabras o secuencias ingresadas y que cumpla con el resto de los parámetros de búsqueda dados."/>
                        <br><br>

                        <b>Período:</b><br>

                        <span class="fecha_input" style="margin-left:20px;" >
                            Desde
                            <html:text name="busquedaActividadForm" property="fechaInic" size="8"
                                       readonly="true" ondblclick="this.value = ''"
                                       title="Haga doble click para borrar la fecha"/>
                        </span>
                        <span class="fecha_click">
                            <html:hidden name="busquedaActividadForm" property="fechaInic"/>
                        </span><br>
                        <span class="fecha_input" style="margin-left:20px;" >
                            Hasta&nbsp;
                            <html:text name="busquedaActividadForm" property="fechaFin" size="8"
                                       readonly="true" ondblclick="this.value = ''"
                                       title="Haga doble click para borrar la fecha"/>
                        </span>
                        <span class="fecha_click">
                            <html:hidden name="busquedaActividadForm" property="fechaFin"/>
                        </span>
                        <br><br>
                        <div align="right"><html:submit>Buscar</html:submit></div>
                    </html:form>

                </div>
            </div>
        </div>
    </div>
</div>
