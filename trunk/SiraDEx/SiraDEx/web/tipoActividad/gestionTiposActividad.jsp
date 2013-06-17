
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
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
                        /* Nombre */ null,
                        /* Modificar */ { "bSortable": false },
                        /* Eliminar */ { "bSortable": false }
                    ]});
            });

        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Tipos de Actividad</title>
    </head>
    <body>
        <h1 class="title">Gestion de Tipos de Actividad</h1>

        <logic:present name="mensajeTipo"><br>
            <logic:notMatch name="mensajeTipo" value="Error:">
                <div class ="status"><bean:write name="mensajeTipo"/></div>
            </logic:notMatch>
            <logic:match name="mensajeTipo" value="Error:">
                <div class ="error"><bean:write name="mensajeTipo"/></div>
            </logic:match>
            <br>
        </logic:present>

        <html:link action="/RegistrarTipoActividad?method=page"> 
            Agregar Tipo de Actividad
        </html:link>
        <br>
        <html:link action="/GestionTiposActividad?method=listDisable"> 
            Papelera
        </html:link><br>

        <logic:notPresent name="tipos">
            <div align="center">No hay Tipo de Actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="tipos">
            <h1>Tipos de Actividades registradas en el sistema</h1>
            <table class="display" id="datatab">
                <thead>
                    <tr>
                    <th>Nombre</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="tipos" id="ta">
                        <tr>
                        <td>
                            <bean:write name="ta" property="nombreTipo"/>
                        </td>
                        <td align="center">
                            <html:form method="POST" action="/ModificarTipoActividad?method=page">
                                <html:hidden name="ta" property="id" />
                                <html:submit styleId="botonModificar"
                                             value=" "
                                             title="Modificar"/>
                            </html:form>
                        </td>
                        <td align="center"><html:form method="POST" action="/EliminarTipoActividad">
                                <html:hidden name="ta" property="id" />
                                <html:submit styleId="botonEliminar"
                                             value=" "
                                             title="Eliminar"
                                             onclick="return confirm('¿Está seguro que desea eliminar el Tipo de Actividad?')" />
                            </html:form>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>
    </body>
</html>
