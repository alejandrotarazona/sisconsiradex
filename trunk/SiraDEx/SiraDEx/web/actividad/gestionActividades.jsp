<%-- 
    Document   : gestionActividad
    Created on : 31/10/2012, 08:41:09 AM
    Author     : SisCon
--%>

<%@page import="Clases.Campo"%>
<%@page import="Clases.CampoValor"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="Interfaz/Scripts/jquery.dataTables.min.js"></script> 
        <style type="text/css" title="currentStyle">
            @import "Interfaz/Stylesheets/demo_page.css";
            @import "Interfaz/Stylesheets/demo_table.css";
        </style>
        <script>
            $(document).ready(function(){

                $(".detalles").hide();
                $(".mostrar").click(function(){
                    $(this).siblings('.detalles').toggle();

                });
                $('#datatab').dataTable();
            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Actividades</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de Actividades</h1>
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

        <h1>Actividades registradas en el sistema</h1>
        <logic:present name="actividadForm" property="mensaje">
            <bean:write name="actividadForm" property="mensaje" /><br/>
        </logic:present>
        <logic:notPresent name="acts">
            <div align="center">No hay actividad que mostrar</div>
        </logic:notPresent>
        <logic:present name="acts">
            <div id="demo">
                <table id="datatab">
                    <thead>
                        <tr>
                        <th>Participantes</th>
                        <th>Actividad</th>
                        <th>Detalles</th>
                        <th>Creación</th>
                        <th>Modificación</th>
                        <th>Validación</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate name="acts" id="act">
                            <%--Hay que cambiar la forma de hacer esto para solucionar
                            el problema de las comas, etre otros, de la forma más fácil
                            y agradable a la vista--%>
                            <tr><td>
                                <%--cambiar el iterate de abajo por un atributo 
                                    con un string que tenga los p participantes
                                    concatenados como p1, p2, ... , pn --%>
                                <logic:iterate name="act" property="participantes" 
                                               id="participante" indexId="index">
                                      
                                    <% String part = (String) pageContext.findAttribute("participante");
                                        out.print(part+"; ");%>

                                </logic:iterate>.
                            </td>
                            <td>
                                <bean:write name="act" property="nombreTipoActividad"/>
                            </td>
                            <td>
                                <%--cambiar el iterate de abajo por un atributo que tenga un 
                                string con todos los v valores de los campos que no sean textol
                                concatenados como v1, v2, ... , vn y los casos de fecha y checkbox
                                concatenar los nombres de los campos también c1:v1,...cn:vn --%>
                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">
                                    <%  CampoValor campoV = (CampoValor) pageContext.findAttribute("campoValor");

                                        String tipo = (campoV.getCampo().getTipo());
                                        if (tipo.equals("textol") || tipo.equals("checkbox")) {
                                            Campo c = campoV.getCampo();
                                            c.setTipo("1");
                                            campoV.setCampo(c);

                                        }
                                    %>    
                                    <logic:notEqual name="campoValor" property="campo.tipo" value="1">
                                        <bean:write name="campoValor" property="valor"/>,
                                    </logic:notEqual>

                                </logic:iterate>.<br>

                            <span class="detalles"><b>Descripción: </b><bean:write name="act" 
                                        property="descripcion"/>
                                <%--cambiar el iterate de abajo por un atributo que tenga un 
                                    string con todos los v valores de los campos que sean textol
                                    concatenados como v1, v2, ... , vn--%>
                                <logic:iterate name="act" property="camposValores" 
                                               id="campoValor" indexId="index">

                                    <logic:equal name="campoValor" property="campo.tipo" value="textol">
                                        <br>
                                        <b><bean:write name="campoValor" property="campo.nombre"/>: </b>
                                        <bean:write name="campoValor" property="valor"/>
                                    </logic:equal>

                                </logic:iterate></span>  


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
                            <td>
                                <bean:write name="act" property="validacion"></bean:write>
                            </td>
                            <td>
                                <html:form method="POST" action="/ModificarActividad?method=page">
                                    <html:hidden name="act" property="idActividad" />
                                    <html:submit styleId="botonModificar"
                                                 value=" "
                                                 title="Modificar"/>
                                </td>
                                <td>
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
            </div>
        </logic:present>

    </body>
</html>
