<%-- 
    Document   : formRegistroUsuario
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

            function verifCorreo(campo) {
                var x = /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;
                if (campo.value !== '' && !x.test(campo.value)) {
                    document.getElementById('error').innerHTML = 'El correo debe ser de la forma nombre@dominio.xxx';
                    document.getElementById('registrar').disabled = true;
                }
                if ((campo.value !== '' && x.test(campo.value)) || campo.value === '') {
                    document.getElementById('error').innerHTML = '';
                    document.getElementById('registrar').disabled = false;
                }
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registro de Usuario</title>
    </head>
    <body>
        <h1 class="title">Registro de Usuario</h1>
        <logic:present name="fallo"><br><br>            
            <div class ="error"><bean:write name="fallo"/></div><br><br><br>
            <div style="font-size:1.3em">
                Para cerrar su sesión en el Servicio Centralizado de Autenticación, por favor  
                <html:link action="/Logout">
                    haga click aquí
                </html:link>
            </div> 
        </logic:present>
        <logic:notPresent name="fallo">    
            <logic:present name="usuarioForm" property="mensaje"><br>
                <div class ="error"><bean:write name="usuarioForm" property="mensaje"/></div>
                <br>
            </logic:present>

            <font size=2>
                Por favor, complete o edite sus datos, luego presione
                <b>Continuar</b> para finalizar su registro como usuario de SiraDEx.
                <br><br>
                Los campos con el asterisco <span style="color:red">*</span> 
                son obligatorios.
            </font><br>
            <html:form method="POST" action="/Login?method=save">

                <table>
                    <tbody>
                        <tr>
                        <td width="15%" style="font-weight: bold">
                            Nombre(s)  <span style="color:red">*</span>
                        </td>
                        <td>
                            <html:text name="usuarioForm" property="nombres" maxlength="50"/>
                        </td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">
                            Apellido(s)  <span style="color:red">*</span>
                        </td>
                        <td>
                            <html:text name="usuarioForm" property="apellidos" maxlength="50"/>
                        </td>
                        </tr>
                        <tr>
                        <td style="font-weight: bold">Teléfono</td>
                        <td><html:text name="usuarioForm" property="telefono" maxlength="15"/>
                        </td>
                        </tr>
                        <tr>
                        <td>
                            <b>Correo</b><br>(distinto al @usb.ve)
                        </td>
                        <td>
                            <html:text name="usuarioForm" property="email" maxlength="50"
                                       onkeyup="verifCorreo(this)" onchange="verifCorreo(this)"/>

                        <span style="color: red" id="error"></span>
                        </td>
                        </tr>
                        <logic:equal name="usuarioForm" property="rol" value="empleado">
                            <tr>
                            <td>
                                <b>Rol</b> <span style="color:red">*</span>
                            </td>
                            <td>
                                <html:radio property="rol" value="empleado">Empleado Administrativo</html:radio>
                                    <br>
                                <html:radio property="rol" value="obrero">Obrero</html:radio>
                                </td>
                                </tr>
                        </logic:equal>
                    </tbody> 
                </table>  
                <div align="center">
                    <html:submit styleId="registrar">Continuar</html:submit>
                    </div>
            </html:form>
        </logic:notPresent>
    </body>
</html>
