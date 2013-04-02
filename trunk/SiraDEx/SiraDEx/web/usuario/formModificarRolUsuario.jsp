<%-- 
    Document   : formGestionUsuario
    Created on : Mar 9, 2013, 6:22:20 PM
    Author     : SisCon
--%>

<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <% Usuario u = (Usuario) pageContext.getSession().getAttribute("usuarioForm");
            String r = (String) u.getRol();
            String dex = r;
            String modo = "visible";
            if (r.equals("WM") || r.equals("profesor") || r.equals("obrero")
                    || r.equals("estudiante") || r.equals("empleado")) {
                modo = "hidden";
                dex = "";
            }
        %>
        <script>
            $(document).ready(function(){
 
                $(".selector").change(function(){
                    var val = $('.selector')[0].value;
                    if(val=="<%=dex%>"){
                        $('.ocultable').css("visibility", "visible");
                    }else {
                        $('.ocultable').css("visibility", "hidden");
                    }     
                }
            )
                $('.ocultable').change(function(){
                    var val = $('.ocultable')[0].value;
                    $('.selector')[0].value = val;
                })
            
            });              
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Rol de 
            <bean:write name="usuarioForm" property="nombres"/> 
            <bean:write name="usuarioForm" property="apellidos"/>
        </title>
    </head>
    <body>
        <h1 class="title">Edición del Rol de 
            <bean:write name="usuarioForm" property="nombres"/> 
            <bean:write name="usuarioForm" property="apellidos"/>
        </h1>

        <logic:present name="usuarioForm" property="mensajeError"><br>
            <b><div class ="error">
                    <bean:write name="usuarioForm" property="mensajeError" />
                </div></b><br>
            </logic:present>

        <html:form action="/ModificarUsuario?method=modificar">
            <table>
                <tr>
                <td><b>Rol</b></td>
                <td>
                    <html:select styleClass="selector"
                                 name="usuarioForm" property="rol">
                        <html:option value="<%=dex%>">Personal del DEx</html:option>
                        <html:option value="obrero">Obrero</html:option>
                        <html:option value="profesor">Profesor</html:option>
                        <html:option value="empleado">Empleado Administrativo</html:option>
                        <html:option value="estudiante">Estudiante</html:option>
                        <html:option value="WM">Webmaster</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
            <td>
            <span align="left" class="ocultable" style="visibility: <%=modo%>">
                <b>Dependencia o Unidad</b>
            </span></td>
        <td>
        <span align="left" class="ocultable" style="visibility: <%=modo%>">
            <html:select name="usuarioForm" property="rol">
                <html:option value="">-- Seleccione --</html:option>
                <html:optionsCollection name="coord" label="contenido" value="contenido"/>

            </html:select>
        </span></td>
</tr>
</table>
<div align="center"><html:submit>Modificar</html:submit></div>
</html:form>
</body>
</html>
