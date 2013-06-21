<%-- 
    Document   : formBusqueda
    Created on : May 2, 2013, 1:27:11 PM
    Author     : SisCon
--%>

<%@page import="Clases.Usuario"%>
<%@page import="Clases.Actividad"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<head>
    <script type="text/javascript">
        $(document).ready(function(){

            $(".textolargo").hide();
            $(".mostrar").click(function(){
                $(this).siblings('.textolargo').toggle();
                var $this = $(this);
                $this.text($this.text() == "Menos detalles" ? "Más detalles" : "Menos detalles");
            });
            $(".grafica").show();
            $(".ver").click(function(){
                $('.grafica').toggle();
                var $ver1 = $(document.getElementsByClassName('ver')[0]);
                $ver1.text($ver1.text() == "[Mostrar Gráfica]" ? "[Ocultar Gráfica]" : "[Mostrar Gráfica]");
                var $ver2 = $(document.getElementsByClassName('ver')[1]);
                $ver2.text($ver2.text() == "[Mostrar Gráfica]" ? "[Ocultar Gráfica]" : "[Mostrar Gráfica]");
            });
        });
    
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SiraDEx | Búsquedas</title>
</head>

<body>
    <logic:present name="user">
        <h1 class="title">Búsquedas Avanzadas</h1>
    </logic:present>
    <logic:notPresent name="user">
        <h1 class="title">Búsquedas Públicas</h1>
    </logic:notPresent>


    <logic:notPresent name="actividades">
        <div align="center"><br>
            Para realizar una consulta, seleccione o rellene los campos mostrados a 
            la izquierda de la página. Luego, presione el botón <b>Buscar</b>.
        </div>
    </logic:notPresent>

    <logic:present name="actividades">
        <logic:empty name="actividades">
            <br><br>
            <div align="center">
                No hay actividad que mostrar que coincida con los
                parámetros de la búsqueda
            </div>
        </logic:empty>
        <logic:notEmpty name="actividades">
            <% String chd = (String) request.getSession().getAttribute("graficaCantidad");
                String chdl = (String) request.getSession().getAttribute("graficaNombres");
                String s = "http://chart.apis.google.com/chart?&cht=p3&chs=800x200&chd=t:";
                String chtt = "chtt=Actividades%20de%20extensión&";
                String chco = "chco=3399CC,00CC00,00FF00,FF00FF,FF0066,FFCC00&";
                s += chd + "&" + chtt + chco + "chdl=" + chdl;
            %>
            <a class="ver">
                [Ocultar Gráfica]</a>
            <div class="grafica">

                <html:img src="<%=s%>"/>

                <table class="cebra">
                    <tbody>
                        <tr>
                        <th><b>Tipo de Actividad</b></th>
                        <th><b>Actividades</b></th>
                        </tr>

                        <logic:iterate  name="busquedaActividadForm" property="datosGrafica" 
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
                            <b>${(busquedaActividadForm.totalActividades)}</b>
                        </td>
                        </tr>
                    </tbody>
                </table>

                <a class="ver">
                    [Ocultar Gráfica]</a>

            </div>

            <br><br><br>
            <table>
                <tr>
                <td style="font-weight: bold;font-size:1.154em">
                    Actividades de Extensión
                </td>
                <td align="right">
                    Encontradas: <b>${(busquedaActividadForm.totalActividades)} actividades</b>
                </td>
            </tr>
        </table>

        <table class="cebra">
            <tbody>
                <logic:iterate name="actividades" id="act" indexId="index">
                    <tr>
                    <td>
                        ${(busquedaActividadForm.pagina - 1) * busquedaActividadForm.mostrarPorPagina + index + 1}.
                    </td>
                    <td>
                        <b><% Actividad a = (Actividad) pageContext.findAttribute("act");
                                out.print(a.participantesToString());%></b>
                        "<bean:write name="act" property="nombreTipoActividad"/>",


                        <% out.print(a.camposValoresToString());%>

                        <logic:equal name="act" property="validacion" value="En espera">
                            <b>Actividad por validar.</b>
                        </logic:equal>
                        <br>
                        <div>
                            <span class="textolargo">

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
                                <logic:iterate name="act" property="archivos" 
                                               id="archivo" indexId="index">

                                    <html:form method="POST">
                                        <html:hidden name="act" property="idActividad"/>
                                        <html:hidden name="act" property="idArchivo" value="${index}"/>
                                        <html:link action="/MostrarPDF" paramName="act" paramProperty="idActividad" 
                                                   paramId="idActividad" title="Descargar">
                                            ${archivo.tipo}
                                        </html:link> 
                                    </html:form>

                                </logic:iterate>
                            </span> 

                            <a class="mostrar" style="text-decoration:underline">
                                Más detalles</a>
                        </div>
                    </td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
        <% Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            String accion;
            if (usuario == null) {
                accion = "/BusquedaPublica?method=aPagina";
            } else {
                accion = "/BusquedaAvanzada?method=aPagina";
            }
        %>
        <table>
            <tr>
            <td align="left">
                <html:form action="<%=accion%>">
                    <html:hidden name="busquedaActividadForm" property="pagina" styleId="actual"/>
                    Página ${busquedaActividadForm.pagina} de ${busquedaActividadForm.totalPaginas}
                </td>
                <td align="right">
                    <logic:equal name="busquedaActividadForm" property="pagina" value="1">
                        <html:submit value="Anterior" title="Ir a la página anterior"
                                     style="padding: 2px 6px;" disabled="true"/>   
                    </logic:equal>
                    <logic:notEqual name="busquedaActividadForm" property="pagina" value="1">
                        <html:submit value="Anterior" title="Ir a la página anterior"
                                     style="padding: 2px 6px;"
                                     onclick="if (${busquedaActividadForm.pagina} > 1){
                                     document.getElementById('actual').value=${busquedaActividadForm.pagina}-1;
                                     }"/>   
                    </logic:notEqual>
                    <logic:equal name="busquedaActividadForm" property="pagina" 
                                 value="${busquedaActividadForm.totalPaginas}">
                        <html:submit value="Siguiente" title="Ir a la página siguiente"
                                     style="padding: 2px 6px;" disabled="true"/>   
                    </logic:equal>
                    <logic:notEqual name="busquedaActividadForm" property="pagina" 
                                    value="${busquedaActividadForm.totalPaginas}">
                        <html:submit value="Siguiente" title="Ir a la página siguiente"
                                     style="padding: 2px 6px;"
                                     onclick="if (${busquedaActividadForm.pagina} < ${busquedaActividadForm.totalPaginas}){
                                     document.getElementById('actual').value=${busquedaActividadForm.pagina}+1;
                                     }"/>
                    </logic:notEqual>
                </html:form>
            </td>
        </tr>
    </table>


</logic:notEmpty>
</logic:present>

</body>





