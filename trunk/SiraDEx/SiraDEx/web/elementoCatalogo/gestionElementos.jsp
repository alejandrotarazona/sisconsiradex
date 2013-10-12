
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
                            aoColumnDefs: [
                                {bSortable: true, aTargets: ['_all']},
                                {bSortable: false, aTargets: [-1]},
                                {bSortable: false, aTargets: [-2]}
                            ]});
                    });

                </script>
            </logic:equal>
            <logic:equal name="permiso" value="dex">
                <script>
                    $(document).ready(function() {

                        $('#datatab').dataTable({
                            aoColumnDefs: [
                                {bSortable: true, aTargets: ['_all']}
                            ]});
                    });

                </script>
            </logic:equal>
        </logic:present>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>SiraDEx | Consulta del Catálogo</title>

    </head>
    <body>
        <logic:notPresent name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notPresent>
        <logic:present name="permiso">

            <h1 class='title'>Consulta del Catálogo 
                <bean:write name="elementoCatalogoForm" property="nombreCatalogo"/>
            </h1>

            <logic:present name="mensajeElem">
                <br>
                <logic:notMatch name="mensajeElem" value="Error:">
                    <div class ="status"><bean:write name="mensajeElem"/></div>
                </logic:notMatch>
                <logic:match name="mensajeElem" value="Error:">
                    <div class ="error"><bean:write name="mensajeElem"/></div>
                </logic:match>
                <br>
            </logic:present>

            <logic:equal name="permiso" value="wm">
                <html:link action="/RegistrarElemento?method=page"> 
                    <html:img src="../Stylesheets/iconos/Add_26x26.png"/>  
                    <b>Agregar un elemento al Catálogo</b>
                </html:link><br>
            </logic:equal>

            <logic:notPresent name="elementos"><br><br>
                <div align="center">No hay elementos que mostrar</div>
            </logic:notPresent>
            <logic:present name="elementos">
                <h1>Elementos del Catálogo</h1>
                <table class="display" id="datatab">
                    <thead>

                        <logic:iterate name="campos" id="campo">
                            <th>
                                <bean:write name="campo" property="campo.nombre"/>
                            </th>    
                        </logic:iterate>
                        <logic:equal name="permiso" value="wm">
                            <th></th>
                            <th></th>
                            </logic:equal>
                    </thead>
                    <tbody>
                        <logic:iterate name="elementos" id="elem">

                            <tr>
                                <logic:iterate name="elem" property="camposValores" 
                                               id="campoValor" indexId="index">
                                <td>
                                    <bean:write name="campoValor" property="valor"/>
                                </td>
                            </logic:iterate>
                            <logic:equal name="permiso" value="wm">
                                <td align="center" width="10%">
                                    <html:form method="POST" action="/ModificarElementoCatalogo?method=page">
                                        <html:hidden name="elem" property="idElemento" />
                                        <html:submit styleId="botonModificar"
                                                     value=" "
                                                     title="Modificar"/>
                                    </html:form>
                                </td>    
                                <td align="center" width="10%">
                                    <html:form method="POST" action="/EliminarElemento">
                                        <html:hidden name="elem" property="idElemento" />
                                        <html:submit styleId="botonEliminar"
                                                     value=" "
                                                     title="Eliminar"
                                                     onclick="return confirm('¿Está seguro que desea eliminar el elemento?')" />
                                    </html:form>
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
