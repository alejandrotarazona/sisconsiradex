<%-- 
    Document   : bienvenida
    Created on : 29/11/2012, 12:29:21 AM
    Author     : SisCon
--%>
<%@page import="Clases.Campo"%>
<%@page import="Clases.CampoValor"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.min.js">
        </script>
        <script>
            $(document).ready(function(){

                $(".detalles").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.detalles').toggle();

                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Inicio</title>
    </head>
    <body>
        <h1>Bienvenido al SiraDEX (Sistema de Registro de Actividades de Extensión)</h1>
        <p>SiraDEX permite a los profesores, estudiantes y demás miembros de la comunidad universitaria agregar, modificar y eliminar sus actividades de extensión.
        </p>
        <br>
        <br>
        <logic:equal name="user" property="rol" value="DEx">

            <b>Actividades por validar</b>
            <br>
            <logic:notPresent name="acts">
                No quedan actividades por validar. NotPresent
            </logic:notPresent>
            <logic:present name="acts">
                <logic:empty name="acts">
                    No quedan actividades por validar. Empty
                </logic:empty>
                <logic:notEmpty name="acts">
                    <table> 

                        <logic:iterate name="acts" id="act">
                            <tr><td>
                                <b><bean:write name="act" property="creador"></bean:write></b>,
                                "<bean:write name="act" property="nombreTipoActividad"/>"

                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">
                                    <%  CampoValor campoVal = (CampoValor) pageContext.findAttribute("campoValor");

                                        String tipo0 = (campoVal.getCampo().getTipo());
                                        if (tipo0.equals("textol") || tipo0.equals("checkbox")) {
                                            Campo c0 = campoVal.getCampo();
                                            c0.setTipo("1");
                                            campoVal.setCampo(c0);

                                        }
                                    %>    
                                    <logic:notEqual name="campoValor" property="campo.tipo" value="1">
                                        , <bean:write name="campoValor" property="valor"/>
                                    </logic:notEqual>

                                </logic:iterate>.<br>

                            <span class="detalles"><b>Descripción: </b><bean:write name="act" 
                                        property="descripcion"/>"
                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">

                                    <logic:equal name="campoValor" property="campo.tipo" value="textol">
                                        <br>
                                        <b><bean:write name="campoValor" property="campo.nombre"/>: </b>
                                        <bean:write name="campoValor" property="valor"/>
                                    </logic:equal>

                                </logic:iterate></span>  


                            <div class="mostrar" style=" cursor: pointer;"><a>Mostrar Detalles</a></div>
                        </td>
                        <td>
                            <html:form method="POST" action="/ValidarActividad">
                                <html:hidden name="act" property="idActividad" />
                                <html:submit styleId="botonValidar"
                                             value=" "
                                             title="Validar"/>
                            </html:form>
                        </td>
                        <td>
                            <html:form method="POST" action="/RechazarActividad">
                                <html:hidden name="act" property="idActividad" />
                                <html:submit styleId="botonRechazar"
                                             value=" "
                                             title="Rechazar"
                                             onclick="return confirm('¿Está seguro que desea rechazar (eliminar) la actividad?')"/>
                            </html:form>
                        </td>
                    </logic:iterate>
                </table>
            </logic:notEmpty>
        </logic:present>

    </logic:equal>
</body>

</html>
