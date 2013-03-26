<%-- 
    Document   : formGestionUsuario
    Created on : Mar 9, 2013, 6:22:20 PM
    Author     : alejandro
--%>
<%@page import="Clases.Usuario"%>
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
        <title>SiraDEx | Gestión de Roles de Usuarios</title>
    </head>
    <body>
        <logic:present name="usuarios" property="mensaje">
            <bean:write name="usuarios" property="mensaje" /><br/>
        </logic:present>
        <%
            Usuario u = (Usuario) pageContext.getAttribute("usuarioM");
            pageContext.setAttribute("viejoUsuario", u);
        %>
        <b>Nombre(s): </b><bean:write name="usuarioM" property="nombres"/><br>
        <b>Apellido(s): </b><bean:write name="usuarioM" property="apellidos"/><br>
        <html:form action="/ModificarUsuario?method=modificar">
            <b>Roles: </b><br>
            <html:select styleClass="selector"
                         name="usuarioM" property="rol">
                <html:option value="">-- Seleccione --</html:option>
                <html:option value="obrero">Obrero</html:option>
                <html:option value="profesor">Profesor</html:option>
                <html:option value="empleado">Empleado Administrativo</html:option>
                <html:option value="estudiante">Estudiante</html:option>
                <html:option value="DEx">Personal del DEx</html:option>
                <html:option value="WM">Webmaster</html:option>
            </html:select>
            <br><br>
            <div align="left" class="ocultable" style="visibility: hidden">
                <b>Dependencia o Unidad </b><br>
                <html:select name="usuarioM" property="username">
                    <html:option value="">-- Seleccione --</html:option>
                    <html:option value="PSC">PSC</html:option>
                    <html:option value="CCTDS">CCTDS</html:option>
                    <html:option value="USIDEx">USIDEx</html:option>
                    <html:option value="UPAS">UPAS</html:option>
                    <html:option value="BPDEx">BPDEx</html:option>
                </html:select><br><br>
            </div>
            <html:submit>Modificar</html:submit>
        </html:form>
    </body>
</html>
