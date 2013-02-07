
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Usuarios</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Usuarios</h1>
        <logic:present name="usuarioForm" property="mensaje">
            <bean:write name="usuarioForm" property="mensaje" /><br/>
        </logic:present>

        <html:link action="/RegistrarUsuario?method=page"> 
            Registrar usuario
        </html:link><br/> 

        <html:link action="/EliminarUsuario?method=page"> 
            Eliminar usuario
        </html:link> 

        <h1 class="title" id="page-title">Usuarios registrados en el sistema</h1>
        <logic:notPresent name="usuarios">
            No hay usuarios que mostrar
        </logic:notPresent>
        <logic:present name="usuarios">
            <logic:empty name="usuarios">
                No hay usuarios que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="usuarios" id="usr">
                    <tr>
                    <b>USB-ID</b> <bean:write name="usr" property="username"></bean:write> 
                    <b>Password</b> <bean:write name="usr" property="password"></bean:write><br>
                </tr>
        </logic:iterate>
    </table>
</logic:present>
</body>
</html>
