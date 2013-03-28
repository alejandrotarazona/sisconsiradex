<%-- 
    Document   : formModificarPerfilUsuario
    Created on : Mar 11, 2013, 4:39:30 PM
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
        <title>SiraDEx | Edición del perfil de <bean:write name="user" property="nombres"/>
        <bean:write name="user" property="apellidos"/></title>
    </head>
    <body>
        
        <h1 class='title' id='page-title'>Edición del perfil de 
            <bean:write name="user" property="nombres"/>
        <bean:write name="user" property="apellidos"/> </h1>
        
        <br><logic:present name="usuarioForm" property="mensaje">
            <b><div class ="status"><bean:write name="usuarioForm" property="mensaje" /></div></b>
                </logic:present> 
            <br><logic:present name="usuarioForm" property="mensajeError">
            <b><div class ="error"><bean:write name="usuarioForm" property="mensajeError" /></div></b>
            </logic:present>
                        
                        <html:form method="POST" 
                   action ="/ModificarPerfilUsuario?method=update">
                        
            <table>

                <tr>
                <td>Nombres</td>
                    <td><html:text name="user" property="nombres">
                        <bean:write name="user" property="nombres"/>
                    </html:text></td>
                </tr>
                
                <tr>
                <td>Apellidos</td>
                    <td><html:text name="user" property="apellidos">
                        <bean:write name="user" property="apellidos"/>
                    </html:text></td>
                </tr>
                
                <tr>
                <td>Teléfono</td>
                    <td><html:text name="user" property="telefono">
                        <bean:write name="user" property="telefono"/>
                    </html:text></td>
                </tr>
                <tr>
                <td>Correo Electrónico</td>
                    <td><html:text name="user" property="email">
                        <bean:write name="user" property="email"/>
                    </html:text></td>
                </tr>
                
            </table>  
                    

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el perfil?')"/></div>

    </html:form>     
    </body>
</html>
