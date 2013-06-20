
<%@page import="Clases.Usuario"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>

        <script type="text/javascript" src="../Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="../Scripts/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../Scripts/ColReorderWithResize.js"></script>
        <style type="text/css" title="currentStyle">
            @import "../Stylesheets/demo_table_jui.css";
        </style>
        <script>
            $(document).ready(function(){

                $('#datatab').dataTable({
                    "aoColumns": [       
                        /* USB-ID */ null,
                        /* Nombre(s) */ null,
                        /* Apellidos */ null,
                        /* Teléfono */ null,
                        /* Correo */ null,
                        /* Rol */ null,
                        /* Acciones */
                        { "bSortable": false },
                        { "bSortable": false }
                       
                    ]});
            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Usuarios</title>
    </head>
    <body>
        <h1 class="title">Gestión de Usuarios</h1>

        <logic:present name="mensajeUsuario"><br>
            <logic:notMatch name="mensajeUsuario" value="Error:">
                <div class ="status"><bean:write name="mensajeUsuario"/></div>
            </logic:notMatch>
            <logic:match name="mensajeUsuario" value="Error:">
                <div class ="error"><bean:write name="mensajeUsuario"/></div>
            </logic:match>
            <br>
        </logic:present>

        <html:link action="/RegistrarUsuario?method=page"> 
            Registrar Usuario
        </html:link><br/> 

        <logic:notPresent name="usuarios">
            No hay usuarios que mostrar
        </logic:notPresent>
        <logic:present name="usuarios">

            <h1>Usuarios registrados en el sistema</h1>

            <table class="display" id="datatab">
                <thead>
                    <tr>
                    <th>USBID</th>
                    <th>Nombre(s)</th>
                    <th>Apellidos</th>
                    <th>Teléfono</th>
                    <th>Correo electrónico</th>
                    <th>Rol</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="usuarios" id="usr">
                        <tr>
                        <td> <bean:write name="usr" property="username"/></td>
                        <td> <bean:write name="usr" property="nombres"/></td>
                        <td> <bean:write name="usr" property="apellidos"/></td>
                        <td> <bean:write name="usr" property="telefono"/></td>
                        <td> <bean:write name="usr" property="email"/></td>
                        <td> <bean:write name="usr" property="rol"/></td>

                        <td>
                            <html:form method="POST" action="/ModificarUsuario?method=page">
                                <html:hidden name="usr" property="username" />
                                <html:submit styleId="botonModificar"
                                             value=" "
                                             title="Modificar"/>
                            </html:form>
                        </td>
                        <td>
                            <html:form method="POST" action="/EliminarUsuario">
                                <html:hidden name="usr" property="username" />
                                <html:submit styleId="botonEliminar"
                                             value=" "
                                             title="Eliminar"
                                             onclick="return confirm('¿Está seguro que desea eliminar el usuario?')" />
                            </html:form>
                        </td>
                        </tr>
                    </logic:iterate>   
                </tbody> 
            </table>

        </logic:present>
    </body>
</html>
