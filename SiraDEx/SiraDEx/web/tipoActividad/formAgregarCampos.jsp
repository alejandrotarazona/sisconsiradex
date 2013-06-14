<%-- 
    Document   : formAgregarCampos
    Created on : 02/11/2012, 05:14:54 PM
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
        <style>
            .selector {width: 80px;}
            .cebra tr:nth-of-type(odd) {background-color:#E2E4FF;}
            .cebra th {
                background-image: -webkit-linear-gradient(top, #E2E4FF, #FFF);
            }

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
        <title>SiraDEx | Registrar Campos del Tipo de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registro de Campos de <bean:write 
                name="tipoActividadForm" property="nombreTipo"/> </h1>

        <logic:present name="tipoActividadForm" property="mensaje"><br>
            <b><div class ="status"><bean:write name="tipoActividadForm" property="mensaje" /></div></b>
        </logic:present> 

        <logic:present name="tipoActividadForm" property="mensajeError"><br>
            <b><div class ="error"><bean:write name="tipoActividadForm" property="mensajeError" /></div></b>
        </logic:present>

        <font size=2>Los campos 'Nombre' y 'Longitud' son obligatorios.</font><br>

        <html:form action="/RegistrarTipoActividad?method=save2">
            <table class="cebra">
                <tbody>
                    <tr>
                    <th><b>Nombre</b></th>
                    <th><b>Tipo</b></th>
                    <th><b>Longitud/Límite</b></th>
                    <th><b>Obligatorio</b></th>
                    <th><b>Catálogo</b></th>
                    </tr>      

                    <logic:iterate name="tipoActividadForm" property="campos" id="campo"
                                   indexId="index">
                        <%
                            int i = (Integer) pageContext.getAttribute("index");
                            String s = "selector" + i;
                            String m = "mostrador" + i;
                            String m2 = "mostrador2" + i;
                            String l = "longitud" + i;
                        %>

                        <tr>
                        <td>
                            <html:text name="campo" property="nombre" indexed="true" size="30"/>

                        </td>

                        <td align="center">

                            <logic:notEqual name="campo" property="tipo" value="producto">
                                <html:select name="campo"  property="tipo" indexed="true" 
                                styleId="<%=s%>" styleClass="selector">
                                    <html:optionsCollection name="campo" property="tipos" 
                                                            label="etiqueta" value="valor"/>
                                </html:select>   
                            </logic:notEqual>

                            <logic:equal name="campo" property="tipo" value="producto">
                                <html:select name="campo"  property="tipo" disabled="true" 
                                             indexed="true" styleClass="selector">
                                    <html:option value="producto">archivo</html:option>
                                </html:select>
                            </logic:equal>
                        </td>


                        <td align="center">

                            <div id ="<%=l%>" style="visibility: visible">
                                <logic:notEqual name="campo" property="tipo" value="producto">
                                    <html:text name="campo" property="longitud" indexed="true"
                                               title="Si el campo es tipo texto o número indica la cantidad máxima de caracteres o dígitos que podrá almacenar, si es tipo participante indica la cantidad máxima se podrán agregar de estos campos." 
                                               size="3"/>
                                </logic:notEqual>

                            </div>
                        </td>


                        <td align="center">

                            <logic:notEqual name="campo" property="tipo" value="producto">
                                <html:checkbox name="campo" property="obligatorio" indexed="true"/>
                                <html:hidden name="campo" property="obligatorio" value="false" 
                                             indexed="true"/>
                            </logic:notEqual>

                            <logic:equal name="campo" property="tipo" value="producto">
                                <html:checkbox name="campo" property="obligatorio" disabled="true" 
                                               indexed="true"/>
                            </logic:equal>

                        </td>

                        <td align="center">
                            <div class="<%=m%>" style="visibility: hidden">
                                <html:select name="campo" property="catalogo" indexed="true">                          
                                    <html:option value="">-- Seleccione --</html:option>

                                    <html:optionsCollection name="catalogos" label="nombre" 
                                                            value="nombre"/>
                                </html:select>
                            </div>

                            <div class="<%=m2%>" style="visibility: hidden">
                                <html:select name="campo" property="catalogoPart" indexed="true">                          
                                    <html:option value="">-- Seleccione --</html:option>

                                    <html:optionsCollection name="catalogosPart" label="nombre" 
                                                            value="nombre"/>
                                </html:select>
                            </div>    

                        </td>

                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
            <br>
            <div align="center"><html:submit>Registrar</html:submit></div>

        </html:form>
    </body>
</html>
