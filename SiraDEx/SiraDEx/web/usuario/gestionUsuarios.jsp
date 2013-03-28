
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Usuarios</title>
    </head>
    <body>
        <h1 class="title">Gestión de Usuarios</h1>
        
        <br><logic:present name="usuarioForm" property="mensaje">
            <b><div class ="status"><bean:write name="usuarioForm" property="mensaje" /></div></b>
                </logic:present> 
            <br><logic:present name="usuarioForm" property="mensajeError">
            <b><div class ="error"><bean:write name="usuarioForm" property="mensajeError" /></div></b>
            </logic:present>

        <html:link action="/RegistrarUsuario?method=page"> 
            Registrar usuario
        </html:link><br/> 

        <html:link action="/EliminarUsuario?method=page"> 
            Eliminar usuario
        </html:link> 

        <h1>Usuarios registrados en el sistema</h1>
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
                    <h1><b>USB-ID: </b><html:link action="/ModificarUsuario?method=page" paramName="usr" 
                               paramProperty="username" paramId="username">
                            <bean:write name="usr" property="username"/>
                        </html:link></h1>
                </tr>
            </logic:iterate>
        </table>
    </logic:present>
</body>
</html>
