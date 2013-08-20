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
        <script type="text/javascript" src="../Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="../Scripts/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../Scripts/ColReorderWithResize.js"></script>
        <style type="text/css" title="currentStyle">
            @import "../Stylesheets/demo_table_jui.css";
        </style>
        <script>
            $(document).ready(function(){

                $(".textolargo").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.textolargo').toggle();
                    var $this = $(this);
                    $this.text($this.text() == "Menos detalles" ? "Más detalles" : "Menos detalles");
                });
                
                $('#datatab').dataTable({
                    "aoColumns": [       
                        /* Participantes */ null,
                        /* Actividad */ null,
                        /* Detalles */ { "bSortable": false },
                        /* Creación */ null,
                        /* Modificación */ null,
                        /* Producto y Archivos */ null,
                        /* Acciones */ 
                        { "bSortable": false }
                       
                    ]});
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Validaciones</title>
    </head>
    <body>
        <h1 class="title">Gestión de Validaciones</h1>
        
        
        <logic:present name="mensajeVal"><br>
            <logic:notMatch name="mensajeVal" value="Error:">
                <div class ="status"><bean:write name="mensajeVal"/></div>
            </logic:notMatch>
            <logic:match name="mensajeVal" value="Error:">
                <div class ="error"><bean:write name="mensajeVal"/></div>
            </logic:match>
        </logic:present>

        <h1><bean:write name="user" property="rol"/></h1>


        <logic:notPresent name="acts"><br>
            <div align="center">No hay actividad pendiente por validar</div>
        </logic:notPresent>
        <logic:present name="acts">

            <h1>Actividades por validar</h1> 

            <table class="display" id="datatab">
                <thead>
                    <tr>
                    <th>Participantes</th>
                    <th>Tipo de Actividad</th>
                    <th>Detalles</th>
                    <th>Creación</th>
                    <th title="Modificación">Mod.</th>
                    <th title="Producto y Archivos">PyA</th>
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
                            <% out.print(a.camposValoresToString(a.getIdActividad()));%>

                        <span class="textolargo"><br>
                            <b>Descripción:</b> 
                            <bean:write name="act" property="descripcion"/>

                            <logic:iterate name="act" property="camposValores" 
                                           id="campoValor" indexId="index">
                                <logic:equal name="campoValor" property="campo.tipo" 
                                             value="textol">
                                    <br>
                                    <bean:write name="campoValor" 
                                                property="campo.nombre"/>:
                                    <bean:write name="campoValor" property="valor"/>
                                </logic:equal>

                            </logic:iterate>
                        </span>  


                        <br>
                        <a class="mostrar">
                            Más detalles
                        </a>
                        </td>
                        <td>    
                            <bean:write name="act" property="fechaCreacion"/> 
                            por el usuario <bean:write name="act" property="creador"/> 
                        </td>
                        <td>
                            <logic:present  name="act" property="modificador">
                                <bean:write name="act" property="fechaModif"/>
                                por el usuario <bean:write name="act" property="modificador"/>
                            </logic:present>

                        </td>

                        <td align="center">
                            <logic:iterate name="act" property="archivos" 
                                           id="archivo" indexId="index">

                                <html:form method="POST" action="/MostrarPDF" >
                                    <html:hidden name="act" property="idActividad"/>
                                    <html:hidden name="act" property="idArchivo" value="${index}"/>
                                    <html:submit styleId="botonPDF"
                                                 value=" "
                                                 title="${archivo.tipo}"/>
                                </html:form>

                            </logic:iterate>     
                        </td>
                        <td align="center">
                            <html:form method="POST" action="/ValidarActividad">
                                <html:hidden name="act" property="idActividad" />
                                <html:hidden name="act" property="nombreTipoActividad" />
                                <html:submit styleId="botonValidar"
                                             value=" "
                                             title="Validar"
                                             onclick="return confirm('¿Está seguro que desea validar la actividad?')" />
                            </html:form>

                            <html:form method="POST" action="/RechazarActividad?method=page">
                                <html:hidden name="act" property="idActividad" />
                                <html:hidden name="act" property="nombreTipoActividad" />
                                <html:submit styleId="botonRechazar"
                                             value=" "
                                             title="Rechazar"/>
                            </html:form></td></tr>
                        </logic:iterate>   
                </tbody> 
            </table>
        </logic:present>    
    </body>
</html>
