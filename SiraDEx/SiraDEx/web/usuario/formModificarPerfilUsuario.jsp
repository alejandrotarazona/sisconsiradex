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

<link rel="stylesheet" type="text/css" href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>

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
        
        <logic:present name="usuarioForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="usuarioForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>
                        
                        
                        <html:form method="POST" enctype="multipart/form-data" 
                   action ="/ModificarPerfil?method=update"/>
            <table>

                <tr>
                <td><bean:write name="user" property="nombres"/></td>
                    <td><html:text name="nombres" property="valor" indexed="true">
                        <bean:write name="nombres" property="valor"/>
                    </html:text></td>
                </tr>
                
                <tr>
                <td><bean:write name="user" property="apellidos"/></td>
                    <td><html:text name="apellidos" property="valor" indexed="true">
                        <bean:write name="apellidos" property="valor"/>
                    </html:text></td>
                </tr>
                
                <tr>
                <td><bean:write name="user" property="telefono"/></td>
                    <td><html:text name="telefono" property="valor" indexed="true">
                        <bean:write name="telefono" property="valor"/>
                    </html:text></td>
                </tr>
                <tr>
                <td><bean:write name="user" property="correo"/></td>
                    <td><html:text name="correo" property="valor" indexed="true">
                        <bean:write name="correo" property="valor"/>
                    </html:text></td>
                </tr>
                
            </table>  
                    

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar el perfil?')"/></div>

                    
    </body>
</html>
