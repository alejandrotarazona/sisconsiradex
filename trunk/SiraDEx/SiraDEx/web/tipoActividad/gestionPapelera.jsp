
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
            $(document).ready(function() {

                $('#datatab').dataTable({
                    "aoColumns": [
                        /* Nombre */ null,
                        /* Consultar */ {"bSortable": false},
                        /* Restaurar */ {"bSortable": false},
                        /* Eliminar */ {"bSortable": false}
                    ]});
            });

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Papelera de Tipos de Actividad</title>
    </head>
    <body>
        <logic:notPresent name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notPresent>
        <logic:present name="permiso">
            <h1 class="title">Papelera de Reciclaje de Tipos de Actividad</h1>

            <logic:present name="mensajeRest"><br>
                <logic:notMatch name="mensajeRest" value="Error:">
                    <div class ="status"><bean:write name="mensajeRest"/></div>
                </logic:notMatch>
                <logic:match name="mensajeRest" value="Error:">
                    <div class ="error"><bean:write name="mensajeRest"/></div>
                </logic:match>
                <br>
            </logic:present>

            <logic:notPresent name="tipos"><br><br>
                <div align="center">No hay Tipo de Actividad que mostrar</div>
            </logic:notPresent>
            <logic:present name="tipos">  
                <h1>Tipos de Actividad eliminadas del sistema</h1>
                <table class="display" id="datatab">
                    <thead>
                        <tr>
                        <th>Nombre</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate name="tipos" id="ta">
                            <tr>
                            <td>
                                <bean:write name="ta" property="nombreTipo"/>
                            </td>
                            <td align="center">
                                <html:form method="POST" action="/ConsultarTipoActividad">
                                    <html:hidden name="ta" property="id" />  
                                    <html:submit styleId="botonExaminar"
                                                 value=" "
                                                 title="Consultar"/>
                                </html:form>
                            </td>
                            <td align="center">
                                <html:form method="POST" action="/RestaurarTipoActividad">
                                    <html:hidden name="ta" property="id" />
                                    <html:submit styleId="botonRestaurar"
                                                 value=" "
                                                 title="Restaurar"
                                                 onclick="return confirm('¿Está seguro que desea restaurar el Tipo de Actividad?')" />
                                </html:form>
                            </td>
                            <td align="center">
                                <logic:equal name="ta" property="actividades" value="0">
                                    <html:form method="POST" action="/EliminarDefinitivoTipoActividad">
                                        <html:hidden name="ta" property="id" />
                                        <html:submit styleId="botonEliminar"
                                                     value=" "
                                                     title="Eliminar"
                                                     onclick="return confirm('¿Está seguro que desea eliminar definitivamente el Tipo de Actividad?')" />
                                    </html:form>
                                </logic:equal>
                                <logic:greaterThan name="ta" property="actividades" value="0">
                                    <html:form method="POST" action="/EliminarDefinitivoTipoActividad">
                                        <html:hidden name="ta" property="id" />
                                        <html:submit styleId="botonEliminar"
                                                     value=" "
                                                     title="Eliminar"
                                                     onclick="return alert('Al eliminar el Tipo de Actividad también serán eliminadas todas las (${ta.actividades}) Actividades de dicho Tipo que están registradas en el sistema.'), 
                                                     confirm('¿Está seguro que desea eliminar  definitivamente el Tipo de Actividad y las Actividades de dicho Tipo?')"/>
                                    </html:form>
                                </logic:greaterThan>
                            </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            </logic:present>
        </logic:present>
    </body>
</html>
