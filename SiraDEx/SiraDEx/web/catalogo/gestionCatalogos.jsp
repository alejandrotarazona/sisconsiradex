
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
                        /* Consultar */ { "bSortable": false },
                        /* Eliminar */ { "bSortable": false }
                    ]});
            });

        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Catálogos</title>
    </head>
    <body>
        <h1 class="title">Gestión de Catálogos</h1>

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

        <html:link action="/RegistrarCatalogo?method=page"> 
            Agregar Catálogo
        </html:link>
        <br>

        <logic:notPresent name="catalogos">
        <span align="center">No hay catálogos que mostrar</span>
    </logic:notPresent>

    <logic:present name="catalogos">
        <h1>Catálogos registrados en el sistema</h1>

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
                <logic:iterate name="catalogos" id="cat">
                    <tr>
                    <td>
                        <logic:notEqual name="cat" property="nombre" value="Dependencias">
                            <logic:notEqual name="cat" property="nombre" value="Programas">
                                <bean:write name="cat" property="nombre" />
                            </logic:notEqual>
                        </logic:notEqual>
                        <logic:equal name="cat" property="nombre" value="Dependencias">
                        <span title="No puede ser eliminado ni cambiado de nombre">
                            <span style="color:red">*</span><bean:write name="cat" property="nombre"/> 
                        </span>
                    </logic:equal>
                    <logic:equal name="cat" property="nombre" value="Programas">
                        <span title="No puede ser eliminado ni cambiado de nombre">
                            <span style="color:red">*</span><bean:write name="cat" property="nombre"/> 
                        </span>
                    </logic:equal>
                    </td>
                    <td align="center">
                        <html:form method="POST" action="/ModificarCatalogo?method=page">
                            <html:hidden name="cat" property="idCatalogo" />
                            <html:submit styleId="botonModificar"
                                         value=" "
                                         title="Modificar"/>
                        </html:form>
                    </td>
                    <td align="center">
                        <html:form method="POST" action="/GestionElementos">
                            <html:hidden name="cat" property="idCatalogo"/>
                            <html:submit styleId="botonExaminar"
                                         value=" "
                                         title="Consultar"/>
                        </html:form>
                    </td>
                    <td align="center">

                        <logic:notEqual name="cat" property="nombre" value="Dependencias">
                            <logic:notEqual name="cat" property="nombre" value="Programas">

                                <html:form method="POST" action="/EliminarCatalogo">
                                    <html:hidden name="cat" property="idCatalogo" />
                                    <html:hidden name="cat" property="nombre" />
                                    <html:submit styleId="botonEliminar"
                                                 value=" "
                                                 title="Eliminar"
                                                 onclick="return confirm('¿Está seguro que desea eliminar catálogo?')"/>
                                </html:form>
                            </logic:notEqual>
                        </logic:notEqual>
                    </td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
    </logic:present>
</body>
</html>
