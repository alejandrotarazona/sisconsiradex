<%-- 
    Document   : formGestionUsuario
    Created on : Mar 9, 2013, 6:22:20 PM
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
                $(".selector").change(function(){
                    var val = $('.selector')[0].value;
                    if(val=="DEx"){
                        $('.ocultable').css("visibility", "visible");
                    } else {
                        $('.ocultable').css("visibility", "hidden");
                    }
                }
            )});              
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Rol de 
            <bean:write name="usuarioForm" property="nombres"/> 
            <bean:write name="usuarioForm" property="apellidos"/>
        </title>
    </head>
    <body>
        <h1 class="title">Edición del Rol de 
            <bean:write name="usuarioForm" property="nombres"/> 
            <bean:write name="usuarioForm" property="apellidos"/>
        </h1>

       <br><logic:present name="usuarioForm" property="mensaje">
            <b><div class ="status"><bean:write name="usuarioForm" property="mensaje" /></div></b>
                </logic:present> 
            <br><logic:present name="usuarioForm" property="mensajeError">
            <b><div class ="error"><bean:write name="usuarioForm" property="mensajeError" /></div></b>
            </logic:present>

        <html:form action="/ModificarUsuario?method=modificar">
            <b>Rol </b>

            <html:select styleClass="selector"
                         name="usuarioForm" property="rol">
                <html:option value="obrero">Obrero</html:option>
                <html:option value="profesor">Profesor</html:option>
                <html:option value="empleado">Empleado Administrativo</html:option>
                <html:option value="estudiante">Estudiante</html:option>
                <html:option value="DEx">Personal del DEx</html:option>
                <html:option value="WM">Webmaster</html:option>
            </html:select>


        <span align="left" class="ocultable" style="visibility: hidden">
            <b>   Dependencia o Unidad </b>

            <html:select name="usuarioForm" property="username">
                <html:option value="">-- Seleccione --</html:option>
                <html:option value="PSC">PSC</html:option>
                <html:option value="CCTDS">CCTDS</html:option>
                <html:option value="USIDEx">USIDEx</html:option>
                <html:option value="UPAS">UPAS</html:option>
                <html:option value="BPDEx">BPDEx</html:option>
            </html:select>

        </span>
        
        <div align="center"><html:submit>Modificar</html:submit></div>
    </html:form>
</body>
</html>
