<%-- 
    Document   : formAgregarUsuario
    Created on : 24/10/2012, 11:38:28 PM
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
            $(document).ready(function() {

                $(".selector").change(function() {
                    var val = $('.selector')[0].value;
                    if (val === "dex") {
                        $('.ocultable').css("visibility", "visible");
                    } else {
                        $('.ocultable').css("visibility", "hidden");
                    }
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registrar Usuario</title>
    </head>
    <body>
        <logic:notEqual name="permiso" value="wm">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notEqual>
        <logic:equal name="permiso" value="wm">
            <h1 class="title">Registro de Usuario</h1>

            <logic:present name="usuarioForm" property="mensaje"><br>
                <div class ="error"><bean:write name="usuarioForm" property="mensaje"/></div>
                <br>
            </logic:present>

            <font size=2>
                Los campos con el asterisco <span style="color:red">*</span> 
                son obligatorios.
            </font><br>
            <html:form action="/RegistrarUsuario?method=save">

                <table>
                    <tbody>
                        <tr>
                        <td width="16%" style="font-weight: bold">USB-ID  <span style="color:red">*</span></td>
                        <td><html:text name="userAux" property="username" maxlength="20"/></td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">Contraseña  <span style="color:red">*</span></td>
                        <td><html:text name="userAux" property="password" maxlength="20"/></td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">Nombres  <span style="color:red">*</span></td>
                        <td><html:text name="userAux" property="nombres" maxlength="50"/></td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">Apellidos  <span style="color:red">*</span></td>
                        <td><html:text name="userAux" property="apellidos" maxlength="50"/></td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">Teléfono</td>
                        <td><html:text name="userAux" property="telefono" maxlength="15"/></td>
                        </tr>
                        <tr>
                        <td><b>Correo</b><br>(distinto al @usb.ve)</td>
                        <td><html:text name="userAux" property="email" 
                                   styleClass="modificar" maxlength="50"
                                   onblur="var x=/^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;
                                   if(this.value != '' && !x.test(this.value)){
                                   document.getElementById('error').innerHTML='Error: El correo debe ser de la forma nombre@dominio.xxx'; 
                                   document.getElementById('registrar').disabled=true;
                                   }
                                   if(this.value != '' && x.test(this.value)){
                                   document.getElementById('error').innerHTML='';
                                   document.getElementById('registrar').disabled=false;
                                   }"/>

                        <span style="color: red" id="error"></span>
                        </td>
                        </tr>
                        <td><b>Rol</b> <span style="color:red">*</span></td>
                        <td>
                            <html:select styleClass="selector"
                                         name="usuarioForm" property="rol" value="">
                                <html:option value="">-- Seleccione --</html:option>
                                <html:option value="empleado">Empleado Administrativo</html:option>
                                <html:option value="estudiante">Estudiante</html:option>
                                <html:option value="obrero">Obrero</html:option>
                                <html:option value="profesor">Profesor</html:option>
                                <html:option value="dex">Personal del DEx</html:option>
                                <html:option value="WM">Webmaster</html:option>
                            </html:select>
                        </td>
                        </tr>
                        <tr>
                        <td>
                        <span align="left" class="ocultable" style="visibility: hidden">
                            <b>Dependencia o Unidad</b> <span style="color:red">*</span>
                        </span></td>
                        <td>
                        <span align="left" class="ocultable" style="visibility: hidden">

                            <html:select name="usuarioForm" property="rolDex">
                                <html:option value="">-- Seleccione --</html:option>
                                <html:optionsCollection name="dependencias" label="contenido" 
                                                        value="contenido"/>

                            </html:select>
                        </span></td>
                        </tr>
                    </tbody> 
                </table>  
                <div align="center">
                    <html:submit styleId="registrar">Registrar</html:submit>
                    </div>
            </html:form>
        </logic:equal>
    </body>
</html>
