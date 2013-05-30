<%-- 
    Document   : gestionActividades
    Created on : 31/10/2012, 08:41:09 AM
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
                        /* Detalles */ null,
                        /* Creación */ null,
                        /* Modificación */ null,
                        /* Validación */ null,
                        /* Producto */ null,
                        /* Acciones */
                        { "bSortable": false }
                       
                    ]});
                
                $(".grafica").hide();
            $(".ver").click(function(){
                $('.grafica').toggle();
                var $this = $(this);
                $this.text($this.text() == "Ocultar Gráfica" ? "Mostrar Gráfica" : "Ocultar Gráfica");
            });
            
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Actividades</title>
    </head>
    <body>
        <h1 class="title">Gestión de Actividades</h1>
        <logic:notPresent name="TipoAct">
            <html:link action="/RegistrarActividad?method=page"> 
                Agregar Actividad
            </html:link><br/>
        </logic:notPresent>
        <logic:present name="TipoAct">
            <html:form action ="/RegistrarActividad?method=save">
                <html:hidden name="TipoAct" property="idTipoActividad" />
                <html:submit>Agregar actividad</html:submit>
            </html:form>
        </logic:present>

         <logic:present name="acts"> 
        <% String chd = (String) request.getAttribute("estadisticaCantidad");
            String chl = (String) request.getAttribute("estadisticaNombres");
            String s = "http://chart.apis.google.com/chart?&cht=p3&chs=800x200&chd=t:";
            String chtt = "Actividades%20de%20extensión&";
            String chco = "chco=3399CC,00CC00,00FF00,FF00FF,FF0066,FFCC00&";
            String chdl = chl;
            s += chd + "chl=" + chl + chtt + chco + "chdl=" + chl;
        %>
        <a class="ver" style=" cursor: pointer">
            Mostrar Gráfica</a>
    <span class="grafica">
        <center>
            <html:img src="<%=s%>"/>
        </center>
    </span>
</logic:present>       
                
        <h1>Actividades registradas en el sistema</h1>

        <logic:present name="actividadForm" property="mensaje"><br>
            <div class ="status"><bean:write name="actividadForm" property="mensaje"/></div><br>
        </logic:present> 
        <logic:present name="actividadForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="actividadForm" property="mensajeError"/></div><br>
        </logic:present>

        <logic:notPresent name="acts">
            <div align="center">No hay Actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="acts">

                <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatab">
                    <thead>
                        <tr>
                        <th>Participantes</th>
                        <th>Actividad</th>
                        <th>Detalles</th>
                        <th>Creación</th>
                        <th>Modificación</th>
                        <th>Validación</th>
                        <th>Producto</th>
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

                            <span class="textolargo"><br>
                                Descripción: 
                                <bean:write name="act" property="descripcion"/>

                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">

                                    <logic:equal name="campoValor" property="campo.tipo" 
                                                 value="textol">
                                        <br>
                                        <bean:write name="campoValor" property="campo.nombre"/>: 
                                        <bean:write name="campoValor" property="valor"/>
                                    </logic:equal>

                                </logic:iterate>
                            </span>  

                            <br>
                            <a class="mostrar" style=" cursor: pointer">
                                Más detalles
                            </a>
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
                            <td>
                                <bean:write name="act" property="validacion"></bean:write>
                                </td>

                                <td align="center">
                                <logic:iterate name="act" property="archivos" 
                                               id="archivo" indexId="index">

                                    <html:form method="POST" action="/MostrarPDF" >
                                        <html:hidden name="act" property="idActividad"/>
                                        <html:hidden name="act" property="idArchivo" value="${index}"/>
                                        <html:submit styleId="botonProducto"
                                                     value=" "
                                                     title="${archivo.tipo}"/>
                                    </html:form>

                                </logic:iterate>   
                            </td>
                            <td align="center">
                                <html:form method="POST" action="/ModificarActividad?method=page">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:submit styleId="botonModificar"
                                                 value=" "
                                                 title="Modificar"/>
                                </html:form>

                                <html:form method="POST" action="/EliminarActividad">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:submit styleId="botonEliminar"
                                                 value=" "
                                                 title="Eliminar"
                                                 onclick="return confirm('¿Está seguro que desea eliminar la actividad?')" />
                                </html:form></td></tr>
                            </logic:iterate>   
                    </tbody> 
                </table>
        </logic:present>
    </body>
</html>
