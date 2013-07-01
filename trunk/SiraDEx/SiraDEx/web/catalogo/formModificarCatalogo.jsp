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
        <h1 class='title'>Edición del Catálogo 
            <bean:write name="catalogoForm" property="nombre"/>
        </h1>

        <logic:present name="catalogoForm" property="mensaje"><br>
            <logic:notMatch name="catalogoForm" property="mensaje" value="Error:">
                <div class ="status"><bean:write name="catalogoForm" property="mensaje"/></div>
            </logic:notMatch>
            <logic:match name="catalogoForm" property="mensaje" value="Error:">
                <div class ="error"><bean:write name="catalogoForm" property="mensaje"/></div>
            </logic:match>   
            <br>
        </logic:present> 
        <br>
        <font size=2>Todos los campos son obligatorios.</font><br>

        <html:form method="POST" action ="/ModificarCatalogo?method=update">
            <table>
                <tbody>
                    <tr>
                    <td width="18%"><b>Catálogo de Usuarios</b></td>

                    <td>
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
                        <html:checkbox name="catalogoForm" property="participantes"
                                           onclick="if (!this.checked) { 
                                           this.value = 'off'
                                           document.getElementById('aviso').innerHTML='<b>El campo USB-ID dejará de ser de usuario al modificar el catálogo.<b>'
                                           } else {
                                           document.getElementById('aviso').innerHTML='', 
                                           this.value = 'on'
                                           }"/>
                        <html:hidden name="catalogoForm" property="participantes" value="false"/>
                        <span id="aviso"></span>
                    </logic:equal>

                    </td>
                    </tr>

                    <tr>
                    <td><b>Nombre del cátalogo</b></td>

                    <td>
                        <% Catalogo c = (Catalogo) pageContext.findAttribute("catalogoForm");
                            String nombreCat = (String) c.getNombre();
                            boolean b = false;
                            if (nombreCat.equals("Dependencias")
                                    || nombreCat.equals("Programas")) {
                                b = true;
                            }%>
                        <html:text name="catalogoForm" property="nombre" disabled='<%=b%>'
                                   maxlength="100">
                            <bean:write name="catalogoForm" property="nombre"/>
                        </html:text>
                    </td>
                    </tr>
                </tbody>
            </table>   
            <font size=2>
                Los campos siguientes son variables.
            </font><br>
            <table>
                <tbody>
                    <tr>
                    <td width="25%" align="center"><b>Nombre</b></td>
                    <td width="10%" align="center"><b>Tipo</b></td>
                    <td width="10%" align="center">Eliminar</td>
                    <td></td>
                    </tr>

                    <logic:iterate name="catalogoForm" property="campos" id="campos" 
                                   indexId="index">

                        <tr>
                        <td align="center">
                        <span style="color: gray;font-size:10px">${index+1}</span>
                        <logic:notEqual name="campos" property="tipo" value="usbid">
                            <html:text name="campos" property="nombre" indexed="true" maxlength="100">
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
                        <td align="center">
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
                        <td td align="center">
                            <logic:notEqual name="campos" property="tipo" value="usbid">
                                <html:checkbox name="campos" property="eliminado" indexed="true"
                                               onclick="if (this.checked) {
                                               anterior = document.getElementById('submit').value;
                                               document.getElementById('submit').value='Eliminar'
                                               } else {
                                               document.getElementById('submit').value=anterior
                                               }"/>
                                <html:hidden name="campos" property="eliminado" value="false" 
                                             indexed="true"/>
                            </logic:notEqual>
                        </td>
                        <td></td> 
                        </tr>

                    </logic:iterate>
                </tbody>
            </table>
            Más campos
            <html:text name="catalogoForm" styleId="mas"
                       property="nroCampos" value="0" size="1" maxlength="1"
                       onkeyup="if(this.value > 0 
                       && document.getElementById('submit').value!='Eliminar') {
                       document.getElementById('submit').value='Agregar'
                       } else if (this.value <= 0 
                       && document.getElementById('submit').value!='Eliminar'){
                       document.getElementById('submit').value='Modificar'
                       }"/>
            <html:img src="../Stylesheets/iconos/Add_26x26.png" style="cursor:pointer"
                      onclick="document.getElementById('mas').value= +document.getElementById('mas').value+1;
                      document.getElementById('submit').value='Agregar'"/>
            <div align="center">
                <html:submit value="Modificar" styleId="submit"
                             onclick="if (this.value=='Modificar') 
                             return confirm('¿Está seguro que desea modificar el Catálogo?');
                             if (this.value=='Eliminar') 
                             return alert('Los campos seleccionados no podrán ser recuperados una vez los elimine'), 
                             confirm('¿Está seguro que desea eliminar los campos seleccionados?')"/>
            </div>
        </html:form>
    </body>
</html>
