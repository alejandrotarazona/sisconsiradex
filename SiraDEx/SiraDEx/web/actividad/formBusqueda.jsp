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
                var $this = $(this);
                $this.text($this.text() == "Mostrar Gráfica" ? "Ocultar Gráfica" : "Mostrar Gráfica");
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
                Ocultar Gráfica</a>
        <span class="grafica">

            <table>
                <tbody>
                    <tr>
                    <th> Actividades </th>
                    <th> Total </th>
                    </tr>

                    <logic:iterate  name="busquedaActividadForm" property="datosGrafica" 
                                    id="dato"> 
                        <tr>
                        <td><bean:write name="dato" property="nombre"/></td>
                        <td><bean:write name="dato" property="cantidad"/></td>
                        </tr>
                        </logic:iterate>

                </tbody>
            </table>


            <html:img src="<%=s%>"/>

        </span>


        <br><br><br>

        <table class="cebra">
            <tbody>
                <logic:iterate name="actividades" id="act" indexId="index">
                    <tr>
                    <td>
                        <b>${(busquedaActividadForm.pagina - 1) * busquedaActividadForm.mostrarPorPagina + index + 1}.</b>
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





