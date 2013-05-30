<%-- 
    Document   : formModificarTipoActividad
    Created on : 12/02/2013, 05:09:07 PM
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
        <script>
            $(document).ready(function(){
                function visibilidad(valor, mostrador, mostrador2, longitud){
                    //var valor = $(this).val();
                    
                    if(valor == "catalogo"){
                        $('#'+longitud).css("visibility", "hidden");
                        $('.'+mostrador).css("visibility", "visible");
                        $('.'+mostrador2).css("visibility", "hidden");
                    } else if(valor == "participante"){
                        $('#'+longitud).css("visibility", "visible");
                        $('.'+mostrador).css("visibility", "hidden");
                        $('.'+mostrador2).css("visibility", "visible");
                    } else if(valor == "texto" || valor=="textol" || valor=="numero"){
                        $('#'+longitud).css("visibility", "visible");
                        $('.'+mostrador).css("visibility", "hidden");
                        $('.'+mostrador2).css("visibility", "hidden");
                    } else {
                        $('#'+longitud).css("visibility", "hidden");
                        $('.'+mostrador).css("visibility", "hidden");
                        $('.'+mostrador2).css("visibility", "hidden");
                    }
                }
                $(".selector").on('focus change',function(evento){
                    var tg = evento.target.id;
                    var mos = "mostrador"+tg.slice("selector".length);
                    var mos2 = "mostrador2"+tg.slice("selector".length);
                    var lon = "longitud"+tg.slice("selector".length);
                    var val = $('.selector')[tg.slice("selector".length)].value;
                    visibilidad(val,mos,mos2,lon);
                });
        
            });        
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Tipo de Actividad  <bean:write name="tipoActividadForm"
                    property="nombreTipo"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición del Tipo de Actividad <bean:write 
                name="tipoActividadForm" property="nombreTipo"/> </h1>

        <br><logic:present name="tipoActividadForm" property="mensaje">
            <b><div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div></b>
        </logic:present> 
        <br><logic:present name="tipoActividadForm" property="mensajeError">
            <b><div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div></b>
        </logic:present><br>

        <font size=2>Todos los campos son obligatorios.</font><br>
        <html:form method="POST" action ="/ModificarTipoActividad?method=update">
            <table>
                <tr>
                <td>Nombre del Tipo de Actividad</td>
                <td><html:text name="tipoActividadForm" property="nombreTipo" size="78">
                        <bean:write name="tipoActividadForm" property="nombreTipo"/>
                    </html:text> </td>
            </tr>
            <tr>
            <td>Descripción</td>
            <td><html:textarea name="tipoActividadForm"  cols="80" rows="2"
                           property="descripcion">
                    <bean:write name="tipoActividadForm" property="descripcion"/>
                </html:textarea>
            </td>
        </tr>
        <tr>
        <td>Tipo</td>
        <td><html:radio property="tipoPR" value="P">P</html:radio>
            <html:radio property="tipoPR" value="R">R</html:radio>
        </td>
    </tr>
    <tr>
    <td>Programa</td>
    <td>
        <html:select property="programa">   
            <html:optionsCollection name="programas" label="contenido" value="contenido"/>
        </html:select>
    </td>
</tr>
<tr>
<td>Dependencia a validar</td>
<td>       
    <html:select property="validador">
        <html:optionsCollection name="dependencias" label="contenido" value="contenido"/>
    </html:select>
</td>
</tr>
<tr>
<td>Realizado por</td>
<td>
    <html:multibox property="permisos" bundle="empleado">Empleado</html:multibox> Empleados<br>
    <html:multibox property="permisos" bundle="estudiante">Estudiante</html:multibox> Estudiantes<br>
    <html:multibox property="permisos" bundle="profesor">Profesor</html:multibox> Profesores<br>
    <html:multibox property="permisos" bundle="obrero">Obrero</html:multibox> Obreros 
</td>       
</tr>
<tr>

</tr>
</table>
<table>
    <tr><td><b>Campos</b></td></tr>
<tr><td></td>
<td><b>Nombre</b></td>
<td><b>Tipo</b></td>
<td><b>Longitud</b></td>
<td><b>Obligatorio</b></td>
<td><b>Catálogo</b></td>        
</tr>
<logic:iterate name="tipoActividadForm" property="campos" id="campos"
               indexId="index">
    <%
        int i = (Integer) pageContext.getAttribute("index");
        String s = "selector" + i;
        String m = "mostrador" + i;
        String m2 = "mostrador2" + i;
        String l = "longitud" + i;
    %>

    <tr><td></td>
    <td>
        <html:text name="campos" property="nombre" indexed="true">
            <bean:write name="campos" property="nombre"/>
        </html:text> </td>
    <td>
        <logic:notEqual name="campos" property="tipo" value="producto">
            <html:select name="campos"  property="tipo" indexed="true" 
            styleId="<%=s%>" styleClass="selector">
                <html:optionsCollection name="campos" property="tipos" label="etiqueta" 
                                        value="valor"/>
            </html:select>   
        </logic:notEqual>

        <logic:equal name="campos" property="tipo" value="producto">
            <html:select name="campos"  property="tipo" disabled="true" 
                         indexed="true" styleClass="selector">
                <html:option value="producto">archivo</html:option>
            </html:select>
        </logic:equal>

    </td>
    <td>

        <div id="<%=l%>" style="visibility: visible">
            <logic:notEqual name="campos" property="tipo" value="producto">
                <html:text name="campos" property="longitud" indexed="true" size="3">
                    <bean:write name="campos" property="longitud"/>
                </html:text>    
            </logic:notEqual>
        </div>

    </td>

    <td>
        <logic:notEqual name="campos" property="tipo" value="producto">
            <html:checkbox name="campos" property="obligatorio" indexed="true" />
            <html:hidden name="campos" property="obligatorio" value="false" indexed="true"/>        
        </logic:notEqual>

        <logic:equal name="campos" property="tipo" value="producto">
            <html:checkbox name="campos" property="obligatorio" disabled="true" indexed="true"/>
        </logic:equal>
    </td>
    <td>     


        <div class="<%=m%>" style="visibility: hidden">
            <html:select name="campos" property="catalogo" indexed="true">                          
                <html:option value="">-- Seleccione --</html:option>

                <html:optionsCollection name="catalogos" label="nombre" 
                                        value="nombre"/>
            </html:select>
        </div>


        <div class="<%=m2%>" style="visibility: hidden">
            <html:select name="campos" property="catalogoPart" indexed="true">                          
                <html:option value="">-- Seleccione --</html:option>

                <html:optionsCollection name="catalogosPart" label="nombre" 
                                        value="nombre"/>
            </html:select>
        </div>
    </td>


</tr>
</logic:iterate>

</table>
<br>

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el Tipo de Actividad?')"/></div>

</html:form>
</body>
</html>
