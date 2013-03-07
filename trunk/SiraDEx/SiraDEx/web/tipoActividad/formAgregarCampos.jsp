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
                        $('.tablaHeader').css("visibility", "visible");
                    } else if(valor == "texto"){
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
            )            
            });
            /*var valor = $("verCatalogo").val();
                    $("verCatalogo").hide();
                $("verLista").hide();
                
                $("verCatalogo").show();
                $("verLista").show();*/                
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registrar Campos del Tipo de Actividad</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Registro de Campos de <bean:write 
                name="tipoActividadForm" property="nombreTipo"/> </h1>
            <logic:present name="tipoActividadForm" property="mensaje">
            <br/><div align="center"><b>
                    <bean:write name="tipoActividadForm" property="mensaje" />
                </b></div><br/>
            </logic:present>
        <table>
            <tr>
            <td><b>Nombre</b></td>
            <td><b>Tipo</b></td>
            <td><b>Obligatoriedad</b></td>
            <td><b>Longitud</b></td>
            <td><b><div class="tablaHeader" style="visibility: hidden">Catálogo</div></b></td>
            <td><b><div class="tablaHeader" style="visibility: hidden">Lista</div></b></td>


        </tr>            

        <html:form action="/RegistrarTipoActividad?method=save2">
            <logic:iterate name="tipoActividadForm" property="campos" id="campo"
                           indexId="index">
                <%
                    int i = (Integer) pageContext.getAttribute("index");
                    String s = "selector" + i;
                    String m = "mostrador" + i;
                    String l = "longitud" + i;
                %>
                <tr>
                <td><html:text name="campo" property="nombre" indexed="true"/></td>
                <td><html:select name="campo"  property="tipo" indexed="true" 
                styleId="<%=s%>" styleClass="selector">
                       <html:optionsCollection name="campos" property="tipos" 
                                               label="etiqueta" value="valor"/>
                    </html:select></td>

                <td><html:checkbox name="campo" property="obligatorio" indexed="true" 
                               value="true"/></td>

                <td><div id ="<%=l%>" style="visibility: visible">
                        <html:text name="campo" property="longitud" indexed="true"/>
                    </div>
                </td>

                <td>
                    <div class="<%=m%>" style="visibility: hidden">
                        <html:select name="campo" property="catalogo" indexed="true">
                            <html:option value="">---Seleccionar---</html:option>
                            <html:optionsCollection name="catalogos" label="nombre" value="nombre"/>
                        </html:select>
                    </div>
                </td>
                <td>
                    <div class="<%=m%>" style="visibility: hidden">
                        <html:checkbox name="campo" property="lista" indexed="true" value="true"/>
                    </div>
                </td>
            </tr>
        </logic:iterate>
    </table>
    <br>
    <div align="center"><html:submit>Registrar</html:submit></div>

</html:form>
</body>
</html>
