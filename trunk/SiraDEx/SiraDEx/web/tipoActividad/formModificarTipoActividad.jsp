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
            .cebra tr:nth-of-type(odd) {background-color:#E2E4FF;}
            .cebra th {background-image: -webkit-linear-gradient(top, #E2E4FF, #FFF);}
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
                $(".selector").change(function(evento){
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

        <logic:present name="tipoActividadForm" property="mensaje"><br>
            <b><div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div></b>
        </logic:present> 
        <logic:present name="tipoActividadForm" property="mensajeError"><br>
            <b><div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div></b>
        </logic:present><br>

        <font size=2>Todos los campos son obligatorios.</font><br>
        <html:form method="POST" action ="/ModificarTipoActividad?method=update">
            <table>
                <tbody>
                    <tr>
                    <td width="15%"><b>Nombre del Tipo de Actividad</b></td>
                    <td><html:text name="tipoActividadForm" property="nombreTipo" size="78">
                            <bean:write name="tipoActividadForm" property="nombreTipo"/>
                        </html:text> </td>
                    </tr>
                    <tr>
                    <td><b>Descripción</b></td>
                    <td><html:textarea name="tipoActividadForm"  cols="80" rows="2"
                                   property="descripcion">
                            <bean:write name="tipoActividadForm" property="descripcion"/>
                        </html:textarea>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Tipo de Producto</b></td>
                    <td><html:radio property="tipoPR" value="P">P</html:radio>
                        <html:radio property="tipoPR" value="R">R</html:radio>
                        </td>
                        </tr>
                        <tr>
                        <td><b>Programa</b></td>
                        <td>
                        <html:select property="programa">   
                            <html:optionsCollection name="programas" label="contenido" value="contenido"/>
                        </html:select>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Dependencia a validar</b></td>
                    <td> 
                        <logic:equal name="user" property="rol" value="WM">
                            <html:select property="validador">
                                <html:optionsCollection name="dependencias" label="contenido" value="contenido"/>
                            </html:select>
                        </logic:equal>
                        <logic:notEqual name="user" property="rol" value="WM">
                            <html:select property="validador" disabled="true" title="${user.rol}">
                                <html:option value="${user.rol}">${user.rol}</html:option>
                            </html:select>
                        </logic:notEqual>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Realizado por</b></td>
                    <td>
                        <html:multibox property="permisos" bundle="empleado">Empleado</html:multibox> Empleados<br>
                        <html:multibox property="permisos" bundle="estudiante">Estudiante</html:multibox> Estudiantes<br>
                        <html:multibox property="permisos" bundle="profesor">Profesor</html:multibox> Profesores<br>
                        <html:multibox property="permisos" bundle="obrero">Obrero</html:multibox> Obreros 
                        </td>       
                        </tr>
                    </tbody>
                </table>

                <b>Campos</b><br>
                <table class="cebra">
                    <tbody>
                        <tr>
                        <th><b>Nombre</b></th>
                        <th><b>Tipo</b></th>
                        <th><b>Longitud/Límite</b></th>
                        <th><b>Obligatorio</b></th>
                        <th><b>Catálogo</b></th>
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

                        <tr>      
                        <td align="center"><span style="color: gray;font-size:10px">${index+1}</span>
                        <html:text name="campos" property="nombre" indexed="true">
                            <bean:write name="campos" property="nombre"/>
                        </html:text> </td>
                        <td align="center">
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
                        <td align="center">

                            <div id="<%=l%>" style="visibility: visible">
                                <logic:notEqual name="campos" property="tipo" value="producto">
                                    <html:text name="campos" property="longitud" indexed="true" 
                                               title="Si el campo es tipo texto o número indica la cantidad máxima de caracteres o dígitos que podrá almacenar, si es tipo participante indica la cantidad máxima se podrán agregar de estos campos." 
                                               size="2" maxlength="4">
                                        <bean:write name="campos" property="longitud"/>
                                    </html:text>    
                                </logic:notEqual>
                            </div>

                        </td>

                        <td align="center">
                            <logic:notEqual name="campos" property="tipo" value="producto">
                                <html:checkbox name="campos" property="obligatorio" indexed="true" />
                                <html:hidden name="campos" property="obligatorio" value="false" indexed="true"/>        
                            </logic:notEqual>

                            <logic:equal name="campos" property="tipo" value="producto">
                                <html:checkbox name="campos" property="obligatorio" disabled="true" indexed="true"/>
                            </logic:equal>
                        </td>
                        <td align="center">     

                            <logic:equal name="campos" property="tipo" value="catalogo">
                                <div class="<%=m%>" style="visibility: visible">
                                    <html:select name="campos" property="catalogo" indexed="true">                          
                                        <html:option value="">-- Seleccione --</html:option>

                                        <html:optionsCollection name="catalogos" label="nombre" 
                                                                value="nombre"/>
                                    </html:select>
                                </div>
                            </logic:equal>

                            <logic:notEqual name="campos" property="tipo" value="catalogo">
                                <div class="<%=m%>" style="visibility: hidden">
                                    <html:select name="campos" property="catalogo" indexed="true">                          
                                        <html:option value="">-- Seleccione --</html:option>

                                        <html:optionsCollection name="catalogos" label="nombre" 
                                                                value="nombre"/>
                                    </html:select>
                                </div>
                            </logic:notEqual>

                            <logic:equal name="campos" property="tipo" value="participante">
                                <div class="<%=m2%>" style="visibility: visible">
                                    <html:select name="campos" property="catalogoPart" indexed="true">                          
                                        <html:option value="">-- Seleccione --</html:option>

                                        <html:optionsCollection name="catalogosPart" label="nombre" 
                                                                value="nombre"/>
                                    </html:select>
                                </div>
                            </logic:equal>
                            <logic:notEqual name="campos" property="tipo" value="participante">
                                <div class="<%=m2%>" style="visibility: hidden">
                                    <html:select name="campos" property="catalogoPart" indexed="true">                          
                                        <html:option value="">-- Seleccione --</html:option>

                                        <html:optionsCollection name="catalogosPart" label="nombre" 
                                                                value="nombre"/>
                                    </html:select>
                                </div>
                            </logic:notEqual>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
            <br>

            <div align="center"><html:submit value="Modificar"
                         onclick="return confirm('¿Está seguro que desea modificar el Tipo de Actividad?')"/></div>

        </html:form>
    </body>
</html>
