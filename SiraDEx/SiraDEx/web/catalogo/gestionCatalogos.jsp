
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
        <logic:present name="permiso">
            <logic:equal name="permiso" value="wm">
                <script>
                    $(document).ready(function() {

                        $('#datatab').dataTable({
                            "aoColumns": [
                                /* Nombre */ null,
                                /* Consultar */ {"bSortable": false},
                                /* Modificar */ {"bSortable": false},
                                /* Eliminar */ {"bSortable": false}
                            ]});
                    });

                </script>
            </logic:equal>
            <logic:equal name="permiso" value="dex">
                <script>
                    $(document).ready(function() {

                        $('#datatab').dataTable({
                            "aoColumns": [
                                /* Nombre */ null,
                                /* Consultar */ {"bSortable": false}
                            ]});
                    });

                </script>
            </logic:equal>
        </logic:present>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <logic:present name="permiso">
            <logic:equal name="permiso" value="wm">
                <title>SiraDEx | Gestión de Catálogos</title>
            </logic:equal>
            <logic:equal name="permiso" value="dex">
                <title>SiraDEx | Consulta de Catálogos</title>
            </logic:equal>
        </logic:present>
        <logic:notPresent name="permiso">
            <title>SiraDEx | Gestión de Catálogos</title>
        </logic:notPresent>
    </head>
    <body>
        <logic:notPresent name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notPresent>
        <logic:present name="permiso">
            <logic:equal name="permiso" value="wm">
                <h1 class="title">Gestión de Catálogos</h1>
            </logic:equal>
            <logic:equal name="permiso" value="dex">
                <h1 class="title">Consulta de Catálogos</h1>
            </logic:equal>
            <logic:present name="mensajeCat">
                <br>
                <logic:notMatch name="mensajeCat" value="Error:">
                    <div class ="status"><bean:write name="mensajeCat"/></div>
                </logic:notMatch>
                <logic:match name="mensajeCat" value="Error:">
                    <div class ="error"><bean:write name="mensajeCat"/></div>
                </logic:match>
                <br>
            </logic:present>
            <logic:equal name="permiso" value="wm">
                <html:link action="/RegistrarCatalogo?method=page"> 
                    <html:img src="../Stylesheets/iconos/Add_26x26.png"/>  
                    <b>Agregar Catálogo</b><br>    
                </html:link>
            </logic:equal>

            <logic:notPresent name="catalogos"><br><br>
            <span align="center">No hay catálogos que mostrar</span>
        </logic:notPresent>

        <logic:present name="catalogos">
            <h1>Catálogos registrados en el sistema</h1>

            <table class="display" id="datatab">
                <thead>
                    <tr>
                    <th>Nombre</th>
                    <th width="10%"></th>
                        <logic:equal name="permiso" value="wm">
                        <th width="10%"></th>
                        <th width="10%"></th>
                        </logic:equal>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="catalogos" id="cat">
                        <tr>
                        <td>
                            <logic:greaterThan name="cat" property="idCatalogo" value="6">     
                                <bean:write name="cat" property="nombre" />
                            </logic:greaterThan>

                            <logic:lessEqual name="cat" property="idCatalogo" value="6">
                            <span title="Catálogo obligatorio para el sistema">
                                <span style="color:red">*</span> <bean:write name="cat" property="nombre"/> 
                            </span>
                        </logic:lessEqual>

                        </td>

                        <td align="center">
                            <html:form method="POST" action="/GestionElementos">
                                <html:hidden name="cat" property="idCatalogo"/>
                                <html:submit styleId="botonExaminar"
                                             value=" "
                                             title="Consultar"/>
                            </html:form>
                        </td>
                        <logic:equal name="permiso" value="wm">
                            <td align="center">
                                <html:form method="POST" action="/ModificarCatalogo?method=page">
                                    <html:hidden name="cat" property="idCatalogo" />
                                    <html:submit styleId="botonModificar"
                                                 value=" "
                                                 title="Modificar"/>
                                </html:form>
                            </td>
                            <td align="center">

                                <logic:greaterThan name="cat" property="idCatalogo" value="6"> 

                                    <html:form method="POST" action="/EliminarCatalogo">
                                        <html:hidden name="cat" property="idCatalogo" />
                                        <html:hidden name="cat" property="nombre" />
                                        <html:submit styleId="botonEliminar"
                                                     value=" "
                                                     title="Eliminar"
                                                     onclick="return confirm('¿Está seguro que desea eliminar catálogo?')"/>
                                    </html:form>

                                </logic:greaterThan>
                            </td>
                        </logic:equal>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>

    </logic:present>
</body>
</html>
