<%-- 
    Document   : gestionValidaciones
    Created on : 26/03/2013, 3:14:21 AM
    Author     : SisCon
--%>

<%@page import="Clases.Actividad"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="Interfaz/Scripts/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/ColReorderWithResize.js"></script>
        <style type="text/css" title="currentStyle">
            @import "Interfaz/Stylesheets/demo_page.css";
            @import "Interfaz/Stylesheets/demo_table_jui.css";
        </style>
        <script>
            $(document).ready(function(){

                $(".textolargo").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.textolargo').toggle();

                });
                $('#datatab').dataTable({
                    "aoColumns": [       
                        /* Participantes */ null,
                        /* Actividad */ null,
                        /* Detalles */ null,
                        /* Creación */ null,
                        /* Modificación */ null,
                        /* Producto */ null,
                        /* Acciones */ 
                        { "bSortable": false },
                        { "bSortable": false }
                       
                    ]});
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Validaciones</title>
    </head>
    <body>
        <h1 class="title">Gestión de Validaciones</h1>

        <h1>Actividades por validar de la <bean:write name="user" property="rol"/></h1>

        <br><logic:present name="actividadForm" property="mensaje">
            <b><div class ="status"><bean:write name="actividadForm" property="mensaje"/></div></b>
        </logic:present> 
        <br><logic:present name="actividadForm" property="mensajeError">
            <b><div class ="error"><bean:write name="actividadForm" property="mensajeError"/></div></b>
        </logic:present>

        <logic:notPresent name="acts">
            <div align="center">No hay actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="acts">

            <div id="demo">
                <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatab">
                    <thead>
                        <tr>
                        <th>Participantes</th>
                        <th>Actividad</th>
                        <th>Detalles</th>
                        <th>Creación</th>
                        <th>Modificación</th>
                        <th>Producto</th>
                        <th></th>
                        <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate name="acts" id="act">
                            <tr><td>
                                <% Actividad a = (Actividad) pageContext.findAttribute("act");
                                    out.print(a.participantesToString());%>
                            </td>
                            <td>
                                <bean:write name="act" property="nombreTipoActividad"/>
                            </td>
                            <td>
                                <% out.print(a.camposValoresToString());%>

                            <span class="textolargo">
                                <b>Descripción: </b>
                                <bean:write name="act" property="descripcion"/>

                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">
                                    <logic:equal name="campoValor" property="campo.tipo" 
                                                 value="textol">
                                        <br>
                                        <b><bean:write name="campoValor" 
                                                    property="campo.nombre"/>: </b>
                                            <bean:write name="campoValor" property="valor"/>
                                        </logic:equal>

                                </logic:iterate>
                            </span>  


                            <div class="mostrar" style=" cursor: pointer;"><a>Más detalles</a></div>
                            </td>
                            <td>
                                <bean:write name="act" property="creador"></bean:write>, 
                                <bean:write name="act" property="fechaCreacion"></bean:write>
                            </td>
                            <td>
                                <logic:present  name="act" property="modificador">
                                    <bean:write name="act" property="modificador"></bean:write>, 
                                    <bean:write name="act" property="fechaModif"></bean:write>
                                </logic:present>

                            </td>

                            <td align="center">
                                <logic:iterate name="act" property="archivos" 
                                               id="archivo" indexId="index">
                                   
                                        <html:form method="POST" action="/MostrarPDF" >
                                            <html:hidden name="act" property="idActividad"/>
                                            <html:hidden name="act" property="idArchivo" value="${index}"/>
                                            <html:submit styleId="botonProducto"
                                                         value=" "
                                                         title="${archivo.nombre}"/>
                                            <br>
                                            <bean:write name="archivo" property="tipo"/>
                                        </html:form>
                                  
                                </logic:iterate>     
                            </td>
                            <td align="center">
                                <html:form method="POST" action="/ValidarActividad">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:submit styleId="botonValidar"
                                                 value=" "
                                                 title="Validar"
                                                 onclick="return confirm('¿Está seguro que desea validar la actividad?')" />
                                </html:form>
                            </td>
                            <td align="center">
                                <html:form method="POST" action="/RechazarActividad">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:submit styleId="botonRechazar"
                                                 value=" "
                                                 title="Rechazar"
                                                 onclick="return confirm('¿Está seguro que desea rechazar la actividad?')"/>
                                </html:form></td></tr>
                            </logic:iterate>   
                    </tbody> 
                </table>
            </div>
        </logic:present>    
    </body>
</html>
