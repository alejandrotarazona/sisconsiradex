<%-- 
    Document   : formGestionUsuario
    Created on : Mar 9, 2013, 6:22:20 PM
    Author     : SisCon
--%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>

        <script>
            $(document).ready(function() {

                $(".selector").change(function() {
                    var val = $('.selector')[0].value;
                    if (val === "dex") {
                        $('.ocultable').css("visibility", "visible");
                    } else {
                        $('.ocultable').css("visibility", "hidden");
                    }
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Rol del Usuario</title>
    </head>
    <body>
        <logic:notEqual name="permiso" value="wm">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notEqual>
        <logic:equal name="permiso" value="wm">
            <h1 class="title">Edición del Rol de 
                <bean:write name="usuarioForm" property="nombres"/> 
                <bean:write name="usuarioForm" property="apellidos"/>
            </h1><br>

            <logic:present name="usuarioForm" property="mensaje"><br>
                <div class ="error"><bean:write name="usuarioForm" property="mensaje"/></div>
                <br>
            </logic:present>

            <html:form action="/ModificarUsuario?method=modificar">
                <table>
                    <tbody>
                        <tr>
                        <td width="5%"><b>Rol&nbsp;&nbsp;</b>
                            <html:select styleClass="selector"
                                         name="usuarioForm" property="rol">
                                <html:option value="empleado">Empleado Administrativo</html:option>
                                <html:option value="estudiante">Estudiante</html:option>
                                <html:option value="obrero">Obrero</html:option>
                                <html:option value="profesor">Profesor</html:option>
                                <html:option value="dex">Personal del DEx</html:option>
                                <html:option value="WM">Webmaster</html:option>
                            </html:select>
                        </td>

                        <td width="18%">
                            <logic:equal name="usuarioForm" property="rol" value="dex">
                            <span align="left" class="ocultable" style="visibility: visible">
                                <b>Dependencia/Unidad&nbsp;&nbsp;</b>
                            </span>
                        </logic:equal>
                        <logic:notEqual name="usuarioForm" property="rol" value="dex">
                            <span align="left" class="ocultable" style="visibility: hidden">
                                <b>Dependencia/Unidad&nbsp;&nbsp;</b>
                            </span>
                        </logic:notEqual>

                        <logic:equal name="usuarioForm" property="rol" value="dex">
                            <span align="left" class="ocultable" style="visibility: visible">

                                <html:select name="usuarioForm" property="rolDex">
                                    <html:option value="">-- Seleccione --</html:option>
                                    <html:optionsCollection name="dependencias" label="contenido" 
                                                            value="contenido"/>

                                </html:select>
                            </span>
                        </logic:equal>
                        <logic:notEqual name="usuarioForm" property="rol" value="dex">
                            <span align="left" class="ocultable" style="visibility: hidden">

                                <html:select name="usuarioForm" property="rolDex">
                                    <html:option value="">-- Seleccione --</html:option>
                                    <html:optionsCollection name="dependencias" label="contenido" 
                                                            value="contenido"/>

                                </html:select>
                            </span>
                        </logic:notEqual>
                        </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <div align="center"><html:submit>Modificar</html:submit></div>
            </html:form>
        </logic:equal>
    </body>
</html>
