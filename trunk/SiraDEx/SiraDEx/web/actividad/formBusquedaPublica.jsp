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
        <link rel="stylesheet" type="text/css" 
              href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>

        <script type="text/javascript">
            $(function() {
                $(".fecha_input input").datepicker();
                $(".fecha_click click").datepicker();
            })	
        </script>
        <script type="text/javascript">
            $(document).ready(function(){

                $(".textolargo").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.textolargo').toggle();
                    var $this = $(this);
                    $this.text($this.text() == "Menos detalles" ? "Más detalles" : "Menos detalles");
                });
            });
    
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Búsquedas Públicas</title>
    </head>
    <body>
        <h1 class="title">Búsquedas Públicas</h1> 
        <h1>Indique los parámetros de su búsqueda</h1>
        <logic:present name="busquedaActividadForm" property="mensaje"><br>
            <div class ="status"><bean:write name="tipoActividadForm" 
                        property="mensaje" /></div>
            </logic:present> 
            <logic:present name="busquedaActividadForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="tipoActividadForm" 
                        property="mensajeError" /></div>
            </logic:present>

        <html:form action="/BusquedaPublica?method=search">
            <table>
                <tr>
                <td>
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

                    Período:<br>
                <span class="fecha_input">
                    Desde <html:text name="busquedaActividadForm" property="fechaInic" />
                </span>
                <span class="fecha_click">
                    <html:hidden name="busquedaActividadForm" property="fechaInic"/>
                </span><br>
                <span class="fecha_input">
                    Hasta&nbsp;&nbsp;<html:text name="busquedaActividadForm" property="fechaFin"/>
                </span>
                <span class="fecha_click">
                    <html:hidden name="busquedaActividadForm" property="fechaFin"/>
                </span>
            </td>

            <td>

                Validador:<br> 
                <html:select name="busquedaActividadForm" property="validador">
                    <html:option value="">-- Cualquiera --</html:option>
                    <html:optionsCollection name="validadores" label="contenido" value="contenido"/>
                </html:select><br><br>

                Programa:<br> 
                <html:select name="busquedaActividadForm" property="programa">
                    <html:option value="">-- Cualquiera --</html:option>
                    <html:optionsCollection name="programas" label="contenido" value="contenido"/>
                </html:select>
            </td>
        </tr>
    </table>
    <br><br>

    Mostrar <html:text name="busquedaActividadForm" property="mostrarPorPagina" 
               size="1" maxlength="3" value="10"/> actividades por pagina.
    <div align="center"><html:submit>Buscar</html:submit></div>

</html:form><br>


<logic:present name="actividades">
    <logic:empty name="actividades">
        <br>
        <div align="center">No hay actividad que mostrar que coincida con los
            parámetros de búsqueda</div>
        </logic:empty>        <logic:iterate name="actividades" id="act">
        <br><br>
        <b>
            <% Actividad a = (Actividad) pageContext.findAttribute("act");
                out.print(a.participantesToString());%>
        </b>
        "<bean:write name="act" property="nombreTipoActividad"/>",


        <% out.print(a.camposValoresToString());%>

        <br>
        <div>
            <span class="textolargo">

                <b>Descripción:</b> 
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
