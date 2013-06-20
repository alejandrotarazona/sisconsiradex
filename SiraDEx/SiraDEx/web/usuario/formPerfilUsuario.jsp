<%-- 
    Document   : formPerfilUsuario
    Created on : Mar 10, 2013, 12:23:43 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Mi Perfil</title>
    </head>
    <body>
        <h1 class="title">Mi Perfil</h1>

        <logic:present name="mensajePerfil"><br>
            <logic:notMatch name="mensajePerfil" value="Error:">
                <div class ="status"><bean:write name="mensajePerfil"/></div>
            </logic:notMatch>
            <logic:match name="mensajePerfil" value="Error:">
                <div class ="error"><bean:write name="mensajePerfil"/></div>
            </logic:match>
            <br>
        </logic:present>

        <a onclick="document.getElementsByClassName('modificar')[0].disabled=false;
            document.getElementsByClassName('modificar')[1].disabled=false;
            document.getElementsByClassName('modificar')[2].disabled=false;
            document.getElementsByClassName('modificar')[3].disabled=false;
            document.getElementById('boton').style.visibility= 'visible';">
            <html:image src="../Stylesheets/iconos/Edit_26x26.png"/>  
            <b>Editar perfil</b>
        </a>   
        <html:form method="POST" 
                   action ="/VerPerfilUsuario?method=update">

            <table>
                <tbody>
                    <tr>
                    <td width="15%" style="font-weight: bold">USB-ID</td>
                    <td>
                        <input type="text" name="email" value="${user.username}" disabled="disabled">
                    </td>
                    </tr>
                    <tr>
                    <td width="15%" style="font-weight: bold">Nombres</td>
                    <td><html:text name="user" property="nombres" disabled="true"
                               styleClass="modificar" maxlength="50">
                            <bean:write name="user" property="nombres"/>
                        </html:text></td>
                    </tr>

                    <tr>
                    <td style="font-weight: bold">Apellidos</td>
                    <td><html:text name="user" property="apellidos" disabled="true"
                               styleClass="modificar" maxlength="50">
                            <bean:write name="user" property="apellidos"/>
                        </html:text></td>
                    </tr>

                    <tr>
                    <td style="font-weight: bold">Teléfono</td>
                    <td><html:text name="user" property="telefono" disabled="true"
                               styleClass="modificar" maxlength="15">
                            <bean:write name="user" property="telefono"/>
                        </html:text></td>
                    </tr>
                    <tr>
                    <td style="font-weight: bold">Correo Institucional</td>
                    <td>
                        <input type="text" value="${user.username}@usb.ve" disabled="disabled">
                    </td>
                    </tr>
                    <tr>
                    <td style="font-weight: bold">Otro Correo</td>
                    <td><html:text name="user" property="email" disabled="true" 
                               styleClass="modificar" maxlength="50"
                               onblur="var x=/^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/
                               if(this.value != '' && !x.test(this.value)){
                               document.getElementById('error').innerHTML='Error: El correo debe ser de la forma nombre@dominio.xxx'; 
                               document.getElementById('modificar').disabled=true;
                               }
                               if(this.value != '' && x.test(this.value)){
                               document.getElementById('error').innerHTML='';
                               document.getElementById('modificar').disabled=false;
                               }">
                            <bean:write name="user" property="email"/>
                        </html:text>
                    <span style="color: red" id="error"></span>
                    </td>
                    </tr>
                </tbody> 
            </table> 
            <div id="boton" style="visibility: hidden" align="center">
                <html:submit value="Modificar" styleId="modificar"
                             onclick="return confirm('¿Está seguro que desea modificar el perfil?')"/>
            </div>
        </html:form>     
    </body>
</html>