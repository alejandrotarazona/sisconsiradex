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

    <script type="text/javascript">
        $(function() {
            $(".fecha_input input").datepicker();
            $(".fecha_click click").datepicker();
        })		
    </script>
    <style>
        .cebra tr:nth-of-type(odd) {background-color:#E2E4FF;}
    </style>

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

    <logic:present name="busquedaActividadForm" property="mensaje"><br>
        <div class ="status"><bean:write name="tipoActividadForm" 
                    property="mensaje" /></div>
        </logic:present> 
        <logic:present name="busquedaActividadForm" property="mensajeError"><br>
        <div class ="error"><bean:write name="tipoActividadForm" 
                    property="mensajeError" /></div>
        </logic:present>

    <logic:present name="actividades"> 
        <logic:notEmpty name="actividades">
            <% String chd = (String) request.getSession().getAttribute("graficaCantidad");
                String chdl = (String) request.getSession().getAttribute("graficaNombres");
                String s = "http://chart.apis.google.com/chart?&cht=p3&chs=800x200&chd=t:";
                String chtt = "Actividades%20de%20extensión&";
                String chco = "chco=3399CC,00CC00,00FF00,FF00FF,FF0066,FFCC00&";
                s += chd + chtt + chco + "chdl=" + chdl;
            %>
            <a class="ver" style=" cursor: pointer;">
                Ocultar Gráfica</a>
        <span class="grafica">
            <center>
                <html:img src="<%=s%>"/>
            </center>
        </span>
        <% Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            String accion;
            if (usuario == null) {
                accion = "/BusquedaPublica?method=aPagina";
            } else {
                accion = "/BusquedaAvanzada?method=aPagina";
            }
        %>
        <br><br>    
        <html:form action="<%=accion%>">
            Página ${busquedaActividadForm.pagina} de ${busquedaActividadForm.totalPaginas}
            &nbsp;&nbsp;&nbsp; 
            <html:hidden name="busquedaActividadForm" property="pagina" styleId="actual"/>

            <html:submit value="Anterior" title="Ir a la página anterior"
                         styleId="anterior"
                         onclick="if (${busquedaActividadForm.pagina} > 1){
                         document.getElementById('actual').value=${busquedaActividadForm.pagina}-1;
                         } else {
                         this.disabled=true;
                         this.style.background='grey';
                         } document.getElementById('siguiente').disabled=false;"/>
            <html:submit value="Siguiente" title="Ir a la página siguiente"
                         styleId="siguiente"
                         onclick="if (${busquedaActividadForm.pagina} < ${busquedaActividadForm.totalPaginas}){
                         document.getElementById('actual').value=${busquedaActividadForm.pagina}+1;
                         } else {
                         this.disabled=true; 
                         this.style.background='grey';
                         } document.getElementById('anterior').disabled=false;
                         "/>

            <logic:empty name="actividades">
                <br><br>
                <div align="center">No hay actividad que mostrar que coincida con los
                    parámetros de la búsqueda</div>
                </logic:empty>
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

                                <a class="mostrar" style=" cursor: pointer; text-decoration:underline">
                                    Más detalles</a>
                            </div>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>


            Página ${busquedaActividadForm.pagina} de ${busquedaActividadForm.totalPaginas}
            &nbsp;&nbsp;&nbsp;  
            <html:submit value="Anterior" title="Ir a la página anterior"
                         styleId="anterior"
                         onclick="if (${busquedaActividadForm.pagina} > 1){
                         document.getElementById('actual').value=${busquedaActividadForm.pagina}-1;
                         } else {
                         this.disabled=true;
                         this.style.background='grey';
                         } document.getElementById('siguiente').disabled=false;"/>
            <html:submit value="Siguiente" title="Ir a la página siguiente"
                         styleId="siguiente"
                         onclick="if (${busquedaActividadForm.pagina} < ${busquedaActividadForm.totalPaginas}){
                         document.getElementById('actual').value=${busquedaActividadForm.pagina}+1;
                         } else {
                         this.disabled=true; 
                         this.style.background='grey';
                         } document.getElementById('anterior').disabled=false;
                         "/>
        </html:form>
    </logic:notEmpty>
</logic:present>

<logic:notPresent name="actividades">
    <div align="center"><br>
        Para realizar una consulta, seleccione o rellene los campos mostrados a 
        la izquierda de la página. Luego, presione el botón <b>Buscar</b>.
    </div>
</logic:notPresent>

</body>





