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
        <script>
            $(document).ready(function(){
                function visibilidad(valor, mostrador, longitud){
                    //var valor = $(this).val();
                    if(valor == "catalogo"){
                        $('#'+longitud).css("visibility", "hidden");
                        $('.'+mostrador).css("visibility", "visible");
                    } else if(valor == "texto" || valor=="textol" || valor=="numero"){
                        $('#'+longitud).css("visibility", "visible");
                        $('.'+mostrador).css("visibility", "hidden");
                    } else {
                        $('.'+mostrador).css("visibility", "hidden");
                        $('#'+longitud).css("visibility", "hidden");
                    }
                }
                $(".selector").change(function(evento){
                    var tg = evento.target.id;
                    var mos = "mostrador"+tg.slice("selector".length);
                    var lon = "longitud"+tg.slice("selector".length);
                    var val = $('.selector')[tg.slice("selector".length)].value;
                    visibilidad(val,mos, lon);
                }
            );
                $("#duplicate").dynamicForm("#plus", "#minus",
                {
                    limit:3,
                    createColor: 'yellow',
                    removeColor: 'red'
                }
            );
            });              
        </script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery-dynamic-form"></script>
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
            <table>
                <tr>
                <td><b>Nombre</b></td>
                <td><b>Tipo</b></td>
                <td><b>Obligatorio</b></td>
                <td><b>Longitud</b></td>
                <td><b>Catálogo</b></td>
                <td><b>Lista</b></td>
            </tr>            

            <logic:iterate name="tipoActividadForm" property="campos" id="campo"
                           indexId="index">
                <%
                    int i = (Integer) pageContext.getAttribute("index");
                    String s = "selector" + i;
                    String m = "mostrador" + i;
                    String l = "longitud" + i;
                %>
                <div id="duplicate">
                    <tr>
                    <td>
                        <html:text name="campo" property="nombre" indexed="true" size="30"/>
                    </td>

                    <td>
                        <logic:notEqual name="campo" property="tipo" value="producto">
                            <html:select name="campo"  property="tipo" indexed="true" 
                            styleId="<%=s%>" styleClass="selector">
                                <html:optionsCollection name="campo" property="tipos" 
                                                        label="etiqueta" value="valor"/>
                            </html:select>   
                        </logic:notEqual>

                        <logic:equal name="campo" property="tipo" value="producto">
                            <html:select name="campo"  property="tipo" disabled="true" 
                                         indexed="true" >
                                <html:option value="producto">archivo</html:option>
                            </html:select>
                        </logic:equal>
                    </td>

                    <td>
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

                    <td>
                        <div id ="<%=l%>" style="visibility: visible">
                            <logic:notEqual name="campo" property="tipo" value="producto">
                                <html:text name="campo" property="longitud" indexed="true"
                                           size="3"/>
                            </logic:notEqual>

                        </div>
                    </td>

                    <td>
                        <div class="<%=m%>" style="visibility: hidden">
                            <html:select name="campo" property="catalogo" indexed="true">      
                                <html:option value="">-- Seleccione --</html:option>
                                <html:optionsCollection name="catalogos" label="nombre" 
                                                        value="nombre"/>
                            </html:select>
                        </div>
                    </td>
                    <td>
                        <div class="<%=m%>" style="visibility: hidden">
                            <html:checkbox name="campo" property="lista" indexed="true" 
                                           value="true"/>
                        </div>
                    </td>
                    <td>
                        <span><a id="minus">[-]</a> <a id="plus">[+]</a></span>
                    </td>
                    </tr>
                </div>
            </logic:iterate>
        </table>
        <br>
        <div align="center"><html:submit>Registrar</html:submit></div>

    </html:form>
</body>
</html>
