<%-- 
    Document   : formModificarCatalogo
    Created on : 12/02/2013, 05:09:07 PM
    Author     : SisCon
--%>

<%@page import="Clases.Catalogo"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <style>
            .selector {width: 70px;}
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Catálogo  <bean:write name="catalogoForm"
                    property="nombre"/></title>
    </head>
    <body>
        <h1 class='title' id='page-title'>Edición del Catálogo <bean:write 
                name="catalogoForm" property="nombre"/> </h1>
        <font size=2>Los campos con el asterisco  <span style="color:red">*</span> 
            son obligatorios.</font><br><br>

        <logic:present name="catalogoForm" property="mensaje"><br>
            <div class ="status"><bean:write name="catalogoForm" property="mensaje"/></div>
        </logic:present> 
        <logic:present name="catalogoForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="catalogoForm" property="mensajeError"/></div>
        </logic:present>


        <html:form method="POST" action ="/ModificarCatalogo?method=update">
            <table>
                <tbody>
                    <tr>
                    <td width="20%">Catálogo de Usuarios</td>

                    <td colspan="3">
                        <logic:equal name="catalogoForm" property="participantes" value="false">
                            <html:checkbox name="catalogoForm" property="participantes" value="off"
                                           onclick="if (this.checked) { 
                                           this.value = 'on'
                                           document.getElementById('aviso').innerHTML='<b>Esta opción agrega por defecto un campo para el usb-id del usuario.<b>'
                                           } else {
                                           document.getElementById('aviso').innerHTML='', 
                                           this.value = 'off'
                                           }"/>
                            <html:hidden name="catalogoForm" property="participantes" value="false"/>
                        <span id="aviso"></span>
                    </logic:equal>

                    <logic:equal name="catalogoForm" property="participantes" value="true">
                        <html:checkbox name="catalogoForm" property="participantes"/>
                        <html:hidden name="catalogoForm" property="participantes" value="false"/>
                    </logic:equal>

                    </td>
                    </tr>

                    <tr>
                    <td>Nombre del cátalogo<span style="color:red">*</span></td>

                    <td>
                        <% Catalogo c = (Catalogo) pageContext.findAttribute("catalogoForm");
                            String nombreCat = (String) c.getNombre();
                            boolean b = false;
                            if (nombreCat.equals("Dependencias")
                                    || nombreCat.equals("Programas")) {
                                b = true;
                            }%>
                        <html:text name="catalogoForm" property="nombre" disabled='<%=b%>'>
                            <bean:write name="catalogoForm" property="nombre"/>
                        </html:text>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Campos</b></td>
                    <td></td>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    <td width="25%"><b>Nombre</b></td>
                    <td width="15%"><b>Tipo</b></td>
                    <td>Eliminar</td>
                    </tr>
                    <logic:iterate name="catalogoForm" property="campos" id="campos" 
                                   indexId="index">

                        <tr><td></td>
                        <td>
                            <logic:notEqual name="campos" property="tipo" value="usbid">
                                <html:text name="campos" property="nombre" indexed="true">
                                    <bean:write name="campos" property="nombre"/>
                                </html:text> 
                            </logic:notEqual>
                            <logic:equal name="campos" property="tipo" value="usbid">
                                <html:text name="campos" property="nombre" indexed="true" 
                                           disabled="true">
                                    <bean:write name="campos" property="nombre"/> 
                                </html:text>                  
                            </logic:equal>

                        </td>
                        <td>
                            <logic:notEqual name="campos" property="tipo" value="usbid">
                                <html:select name="campos" property="tipo" styleClass="selector" 
                                             indexed="true">
                                    <html:option value="texto">texto</html:option>
                                    <html:option value="numero">numero</html:option>
                                    <html:option value="fecha">fecha</html:option>
                                </html:select>
                            </logic:notEqual>
                            <logic:equal name="campos" property="tipo" value="usbid">
                                <html:select name="campos" property="tipo" indexed="true" 
                                             styleClass="selector" disabled="true">
                                    <html:option value="texto">texto</html:option>
                                </html:select>
                            </logic:equal>
                        </td>
                        <td>
                            <logic:notEqual name="campos" property="tipo" value="usbid">
                                <html:checkbox name="campos" property="eliminado" indexed="true"/>
                                <html:hidden name="campos" property="eliminado" value="false" 
                                             indexed="true"/>
                            </logic:notEqual>
                        </td>
                        </tr>
                    </logic:iterate>

                    <logic:present  name="catalogoForm" property="camposAux">
                        <logic:greaterThan name="catalogoForm" property="nroCampos" value="0">

                            <tr>
                            <td><b>Nuevos campos</b></td>
                            </tr>

                            <logic:iterate name="catalogoForm" property="camposAux" id="camposAux" 
                                           indexId="index">
                                <tr><td></td>
                                <td>
                                    <html:text name="camposAux" property="nombre" indexed="true"/>
                                </td>

                                <td>
                                    <html:select name="camposAux" property="tipo" styleClass="selector" 
                                                 indexed="true">
                                        <html:option value="texto">texto</html:option>
                                        <html:option value="numero">numero</html:option>
                                        <html:option value="fecha">fecha</html:option>
                                    </html:select>
                                </td>
                                <td>
                                    <html:checkbox name="camposAux" property="eliminado" indexed="true"/>
                                    <html:hidden name="campos" property="eliminado" value="false" 
                                                 indexed="true"/>
                                </td>
                                </tr>
                            </logic:iterate>

                        </logic:greaterThan>
                    </logic:present> 
                </tbody>
            </table>
            <br>

            Nuevos campos <html:text name="catalogoForm" 
                       property="nroCampos" value="0" size="1" maxlength="2"
                       onkeyup="if(this.value > 0) {document.catalogoForm.submitButton.value='Agregar'
                       } else {document.catalogoForm.submitButton.value='Modificar'}"/>
            <br>
            <div align="center">
                <input type="submit" name="submitButton" value="Modificar"
                       onclick="if (this.value=='Modificar') 
                           return confirm('¿Está seguro que desea modificar el catálogo?')">
            </div>

        </html:form>
    </body>
</html>
