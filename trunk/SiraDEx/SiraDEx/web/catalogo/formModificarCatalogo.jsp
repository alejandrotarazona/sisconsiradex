<%-- 
    Document   : formModificarCatalogo
    Created on : 12/02/2013, 05:09:07 PM
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
        <style>
            .selector {width: 70px;}
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Catálogo  <bean:write name="catalogoForm"
                    property="nombre"/></title>
    </head>
    <body>
        <logic:notEqual name="permiso" value="wm">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notEqual>
        <logic:equal name="permiso" value="wm">
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
                            <logic:greaterThan name="catalogoForm" property="idCatalogo" value="6">
                                <logic:equal name="catalogoForm" property="participantes" value="false">
                                    <html:checkbox name="catalogoForm" property="participantes" value="off"
                                                   onclick="if (this.checked) { 
                                                   this.value = 'on'
                                                   document.getElementById('aviso').innerHTML='<b>Esta opción agrega por defecto un campo para el usb-id y otro para el nombre del usuario.<b>'
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
                                               document.getElementById('aviso').innerHTML='<b>El campo USB-ID y Nombre serán editables al presionar Modificar con esta opción desmarcada.<b>'
                                               } else {
                                               document.getElementById('aviso').innerHTML='', 
                                               this.value = 'on'
                                               }"/>
                                <html:hidden name="catalogoForm" property="participantes" value="false"/>
                                <span id="aviso"></span>
                            </logic:equal>
                        </logic:greaterThan>
                        <logic:lessEqual name="catalogoForm" property="idCatalogo" value="6">
                            <html:checkbox name="catalogoForm" property="participantes" disabled="true"/>
                        </logic:lessEqual>
                        </td>
                        </tr>

                        <tr>
                        <td><b>Nombre del cátalogo</b></td>

                        <td>
                            <logic:greaterThan name="catalogoForm" property="idCatalogo" value="6"> 
                                <html:text name="catalogoForm" property="nombre" maxlength="140">
                                    <bean:write name="catalogoForm" property="nombre"/>
                                </html:text>
                            </logic:greaterThan>
                            <logic:lessEqual name="catalogoForm" property="idCatalogo" value="6">
                                <html:text name="catalogoForm" property="nombre" maxlength="140" 
                                           disabled="true">
                                    <bean:write name="catalogoForm" property="nombre"/>
                                </html:text>
                            </logic:lessEqual>
                        </td>
                        </tr>
                    </tbody>
                </table>   
                <font size=2>
                    Los campos siguientes son variables, 
                    <logic:equal name="catalogoForm" property="participantes" value="true">
                        con excepcion del USB-ID y Nombre.
                    </logic:equal>
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
                                <logic:notEqual name="campos" property="tipo" value="usuario">
                                    <html:text name="campos" property="nombre" indexed="true" maxlength="100">
                                        <bean:write name="campos" property="nombre"/>
                                    </html:text> 
                                </logic:notEqual>
                            </logic:notEqual>
                            <logic:equal name="campos" property="tipo" value="usbid">
                                <html:text name="campos" property="nombre" indexed="true" 
                                           disabled="true">
                                    <bean:write name="campos" property="nombre"/> 
                                </html:text>                  
                            </logic:equal>
                            <logic:equal name="campos" property="tipo" value="usuario">
                                <html:text name="campos" property="nombre" indexed="true" 
                                           disabled="true">
                                    <bean:write name="campos" property="nombre"/> 
                                </html:text>                  
                            </logic:equal>
                            </td> 
                            <td align="center">
                                <logic:notEqual name="campos" property="tipo" value="usbid">
                                    <logic:notEqual name="campos" property="tipo" value="usuario">
                                        <html:select name="campos" property="tipo" styleClass="selector" 
                                                     indexed="true">
                                            <html:option value="texto">texto</html:option>
                                            <html:option value="numero">numero</html:option>
                                            <html:option value="fecha">fecha</html:option>
                                        </html:select>
                                    </logic:notEqual>
                                </logic:notEqual>
                                <logic:equal name="campos" property="tipo" value="usbid">
                                    <html:select name="campos" property="tipo" indexed="true" 
                                                 styleClass="selector" disabled="true">
                                        <html:option value="texto">texto</html:option>
                                    </html:select>
                                </logic:equal>
                                <logic:equal name="campos" property="tipo" value="usuario">
                                    <html:select name="campos" property="tipo" indexed="true" 
                                                 styleClass="selector" disabled="true">
                                        <html:option value="texto">texto</html:option>
                                    </html:select>
                                </logic:equal>
                            </td>
                            <td td align="center">
                                <logic:notEqual name="campos" property="tipo" value="usbid">
                                    <logic:notEqual name="campos" property="tipo" value="usuario">
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
                                </logic:notEqual>
                            </td>
                            <td></td> 
                            </tr>

                        </logic:iterate>
                    </tbody>
                </table>
                <span style="color:#0ADF12;font-weight:bold;font-size:1.2em">Más campos</span>
                <html:text name="catalogoForm" styleId="mas"
                           property="nroCampos" value="0" size="1" maxlength="1"
                           onkeyup="if(this.value > 0 
                           && document.getElementById('submit').value!='Eliminar') {
                           document.getElementById('submit').value='Agregar'
                           } else if (this.value <= 0 
                           && document.getElementById('submit').value!='Eliminar'){
                           document.getElementById('submit').value='Modificar'
                           }"/>

                    <html:submit value="Modificar" styleId="submit"
                                 onclick="if (this.value=='Modificar') 
                                 return confirm('¿Está seguro que desea modificar el Catálogo?');
                                 if (this.value=='Eliminar') 
                                 return alert('Los campos seleccionados no podrán ser recuperados  una vez confirme esta acción. Esto afectara a todos los elementos del catálogo.'), 
                                 confirm('¿Está seguro que desea eliminar los campos seleccionados?')"/>
                </div>
            </html:form>
        </logic:equal>
    </body>
</html>
