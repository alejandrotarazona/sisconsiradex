<%-- 
    Document   : formBusquedaAvanzada
    Created on : May 2, 2013, 1:27:11 PM
    Author     : SisCon
--%>
<%@page import="Clases.Actividad"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <script type="text/javascript" src="Interfaz/Scripts/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery-ui-1.9.2.custom.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){

                $(".textolargo").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.textolargo').toggle();
                    var $this = $(this);
                    $this.text($this.text() == "Menos detalles" ? "Más detalles" : "Menos detalles");
                });
                $(function() {
                    $(".fecha_input input").datepicker();
                    $(".fecha_click click").datepicker();
                })
     
            });
    
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Búsquedas</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Indique los parámetros de su búsqueda</h1>
        <logic:present name="busquedaActividadForm" property="mensaje"><br>
            <b><div class ="status"><bean:write name="tipoActividadForm" 
                        property="mensaje" /></div></b>
                </logic:present> 
                <logic:present name="busquedaActividadForm" property="mensajeError"><br>
            <b><div class ="error"><bean:write name="tipoActividadForm" 
                        property="mensajeError" /></div></b>
                </logic:present>

        <html:form action="/BusquedaPublica?method=publicSearch">
            <table>
                <tr>
                <td>
                    Tipo de Actividad: 
                </td>
                <td>
                    <html:select name="busquedaActividadForm" property="nombreTipo">
                        <html:option value="">-- Seleccione --</html:option>
                        <html:optionsCollection name="tiposdeactividad" label="nombreTipo" value="nombreTipo"/>
                    </html:select>
                </td> 
            </tr>
            <tr>
            <td>
                Usuario que registró la Actividad:
            </td> 
            <td>
                <html:text name="busquedaActividadForm" property="creador"/>
            </td> 
        </tr>
        <tr>
        <td>
            Participante(s):
        </td> 
        <td>
            <html:text name="busquedaActividadForm" property="participantes" styleId="participantes1" value=""/>
        </td>
    </tr>

    <%--       <tr><td>Fecha de Creación:</td>
<td><span class="fecha_input">
    <html:text name="busquedaActividadForm" property="fechaCreacion" indexed="true" 
               readonly="true" />
</span>
<span class="fecha_click">
    <html:hidden name="busquedaActividadForm" property="fechaCreacion" indexed="true"/>
</span></td></tr>
<br>
<tr><td>Fecha de la última modificación:</td>
<td><span class="fecha_input">
    <html:text name="busquedaActividadForm" property="fechaModif" indexed="true" 
               readonly="true" />
</span>
<span class="fecha_click">
    <html:hidden name="busquedaActividadForm" property="fechaModif" indexed="true"/>
</span></td></tr>
<br> --%>
    <tr>
    <td>
        Tipo: 
    </td>
    <td>
        <html:radio property="tipoPR" value="P">P</html:radio>
        <html:radio property="tipoPR" value="R">R</html:radio>
        </td>
    </tr>

    <tr>
    <td>
        Validador: 
    </td>
    <td>
    <html:select name="busquedaActividadForm" property="validador">
        <html:option value="">-- Seleccione --</html:option>
        <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
    </html:select>
</td>
</tr>

<tr>
<td>
    Programa: 
</td>
<td><html:select name="busquedaActividadForm" property="programa">
        <html:option value="">-- Seleccione --</html:option>
        <html:optionsCollection name="programas" label="contenido" value="contenido"/>
    </html:select>
</td>
</tr>
</table>
Mostrar <html:text name="busquedaActividadForm" property="mostrarPorPagina" 
           size="1" maxlength="3" value="10"/> actividades por pagina.
<div align="center"><html:submit>Buscar</html:submit></div>
    <br>
</html:form>
<logic:notPresent name="actividades">
    <br>
    <div align="center">No hay actividad que mostrar</div>
</logic:notPresent>
<logic:present name="actividades">
    <logic:iterate name="actividades" id="act">
        <br><br>
        <b>
            <% Actividad a = (Actividad) pageContext.findAttribute("act");
                out.print(a.participantesToString());%>
        </b>
        <bean:write name="act" property="nombreTipoActividad"/>,


        <% out.print(a.camposValoresToString());%>

        Registrada por el usuario de usb-id 
        <bean:write name="act" property="creador"></bean:write> el 
        <bean:write name="act" property="fechaCreacion"></bean:write>

        <logic:present  name="act" property="modificador">
            <bean:write name="act" property="modificador"></bean:write>, 
            <bean:write name="act" property="fechaModif"></bean:write>
        </logic:present>

        <br>
        <div>
            <span class="textolargo">

                Descripción: 
                <bean:write name="act" property="descripcion"/>

                <logic:iterate name="act" property="camposValores" 
                               id="campoValor" indexId="index">

                    <logic:equal name="campoValor" property="campo.tipo" 
                                 value="textol">
                        <br>
                        <bean:write name="campoValor" property="campo.nombre"/>: 
                        <bean:write name="campoValor" property="valor"/>
                    </logic:equal>

                </logic:iterate>
                <logic:iterate name="act" property="archivos" 
                               id="archivo" indexId="index">

                    <html:form method="POST">
                        <html:hidden name="act" property="idActividad"/>
                        <html:hidden name="act" property="idArchivo" value="${index}"/>
                        <html:link action="/MostrarPDF" paramName="act" paramProperty="idActividad" 
                                   paramId="idActividad" title="Descargar">
                            ${archivo.tipo}
                        </html:link> 
                    </html:form>

                </logic:iterate>
            </span> 

            <a class="mostrar" style=" cursor: pointer; text-decoration:underline">
                Más detalles</a>
        </div>

    </logic:iterate> 
</logic:present>
</body>

</html>
