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
                    $this.text($this.text() === "Menos detalles" ? "Más detalles" : "Menos detalles");
                });
                
                
                $('#datatab').dataTable({
                    "aoColumns": [       
                        /* Participantes */ null,
                        /* Actividad */ null,
                        /* Detalles */ { "bSortable": false },
                        /* Creación */ null,
                        /* Modificación */ null,
                        /* Validación */ null,
                        /* Producto y Archivos */ null,
                        /* Acciones */
                        { "bSortable": false }
                       
                    ]});
                
                $(".grafica").show();
                $(".ver").click(function(){
                    $('.grafica').toggle();
                    var $ver1 = $(document.getElementsByClassName('ver')[0]);
                    $ver1.text($ver1.text() === "[Mostrar Gráfica]" ? "[Ocultar Gráfica]" : "[Mostrar Gráfica]");
                    var $ver2 = $(document.getElementsByClassName('ver')[1]);
                    $ver2.text($ver2.text() === "[Mostrar Gráfica]" ? "[Ocultar Gráfica]" : "[Mostrar Gráfica]");
                });
            
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestión de Actividades</title>
    </head>
    <body>
        <h1 class="title">Gestión de Actividades</h1>

        <logic:present name="mensajeAct"><br>
            <logic:notMatch name="mensajeAct" value="Error:">
                <div class ="status"><bean:write name="mensajeAct"/></div>
            </logic:notMatch>
            <logic:match name="mensajeAct" value="Error:">
                <div class ="error"><bean:write name="mensajeAct"/></div>
            </logic:match>
            <br>
        </logic:present>

        <html:link action="/RegistrarActividad?method=page"> 
            <html:img src="../Stylesheets/iconos/Add_26x26.png"/>  
            <b>Agregar Actividad</b>
        </html:link><br/>

        <logic:present name="acts"> 
            <% String chd = (String) request.getAttribute("graficaCantidad");
                String chdl = (String) request.getAttribute("graficaNombres");
                String s = "http://chart.apis.google.com/chart?&cht=p3&chs=800x200&chd=t:";
                String chtt = "chtt=Actividades%20de%20extensión&";
                String chco = "chco=3399CC,00CC00,00FF00,FF00FF,FF0066,FFCC00&";
                s += chd + "&" + chtt + chco + "chdl=" + chdl;
            %>
            <div align="right">
                <a class="ver">
                    [Ocultar Gráfica]
                </a>
            </div>
            <div class="grafica">

                <html:img src="<%=s%>"/>

                <table class="cebra">
                    <tbody>
                        <tr>
                        <th><b>Tipo de Actividad</b></th>
                        <th><b>Actividades</b></th>
                        </tr>

                        <logic:iterate  name="user" property="datosGrafica" 
                                        id="dato"> 
                            <tr>
                            <td style="padding-left:1em">
                                <bean:write name="dato" property="nombre"/>
                            </td>
                            <td align="center">
                                <bean:write name="dato" property="cantidad"/>
                            </td>
                            </tr>
                        </logic:iterate>
                        <tr>
                        <td style="padding-left:1em;background-color:#D3D6FF">
                            <b>Total</b>
                        </td>
                        <td align="center">
                            <b>${(user.totalActividades)}</b>
                        </td>
                        </tr>
                    </tbody>
                </table>

                <div align="right">
                    <a class="ver">
                        [Ocultar Gráfica]
                    </a>
                </div>
            </div>
        </logic:present>       


        <logic:notPresent name="acts"><br>
            <div align="center">No hay Actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="acts">
            <h1>Actividades registradas en el sistema</h1>
            <table class="display" id="datatab">
                <thead>
                    <tr>
                    <th>Participantes</th>
                    <th>Tipo de Actividad</th>
                    <th>Detalles</th>
                    <th>Creación</th>
                    <th title="Modificación">Mod.</th>
                    <th title="Validación">Val.</th>
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
                                    <bean:write name="campoValor" property="campo.nombre"/>: 
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
                            <logic:equal name="act" property="validacion" value="En espera">
                                <html:img src="../Stylesheets/iconos/espera.png" title="En espera" />
                                <html:hidden name="act" property="validacion" value="e"/>
                            </logic:equal>
                            <logic:equal name="act" property="validacion" value="Validada">
                                <html:img src="../Stylesheets/iconos/check_26x26.png" title="Validada" /> 
                                <html:hidden name="act" property="validacion" value="v"/>
                            </logic:equal>
                            <logic:notEqual name="act" property="validacion" value="En espera">
                                <logic:notEqual name="act" property="validacion" value="Validada">
                                    <html:img src="../Stylesheets/iconos/Delete_26x26.png" title="${act.validacion}"/>
                                    <html:hidden name="act" property="validacion" value="r"/>
                                </logic:notEqual>
                            </logic:notEqual>
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
                            <html:form method="POST" action="/ModificarActividad?method=page">
                                <html:hidden name="act" property="idActividad" />
                                <html:submit styleId="botonModificar"
                                             value=" "
                                             title="Modificar"/>
                            </html:form>

                            <logic:notEqual name="user" property="rol" value="WM">
                                <logic:notEqual name="act" property="validacion" value="Validada">
                                    <html:form method="POST" action="/EliminarActividad">
                                        <html:hidden name="act" property="idActividad" />
                                        <html:hidden name="act" property="nombreTipoActividad" />
                                        <html:submit styleId="botonEliminar"
                                                     value=" "
                                                     title="Eliminar"
                                                     onclick="return confirm('¿Está seguro que desea eliminar la actividad?')" />
                                    </html:form>
                                </logic:notEqual>
                            </logic:notEqual>
                            <logic:equal name="user" property="rol" value="WM">                   
                                <html:form method="POST" action="/EliminarActividad">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:hidden name="act" property="nombreTipoActividad" />
                                    <html:submit styleId="botonEliminar"
                                                 value=" "
                                                 title="Eliminar"
                                                 onclick="return confirm('¿Está seguro que desea eliminar la actividad?')" />
                                </html:form>
                            </logic:equal>


                        </td></tr>
                    </logic:iterate>   
                </tbody> 
            </table>
        </logic:present>
    </body>
</html>
