
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
        <title>SiraDEx | Registro de Catálogo</title>
    </head>

    <body>
        <logic:notEqual name="permiso" value="wm">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notEqual>
        <logic:equal name="permiso" value="wm">
            <h1 class="title">Registro de Catálogo</h1>
            <logic:present name="catalogoForm" property="mensaje"><br>
                <div class ="error"><bean:write name="catalogoForm" property="mensaje"/></div>
                <br>
            </logic:present> 
            <br>
            <font size=2>Todos los campos son obligatorios.</font><br>

            <html:form method="POST" action ="/RegistrarCatalogo?method=save">
                <table>
                    <tbody>
                        <tr>
                        <td width="18%"><b>Catálogo de Usuarios</b></td>
                        <td>
                            <html:checkbox name="catalogoForm" property="participantes" value="off"
                                           onclick="if (this.checked) { this.value = 'on'
                                           document.getElementById('aviso').innerHTML='<b>Esta opción agrega por defecto un campo para el usb-id y otro para el nombre del usuario.<b>'
                                           } else {document.getElementById('aviso').innerHTML='', this.value = 'off'}"/>
                            <html:hidden name="catalogoForm" property="participantes" value="false"/>
                        <span id="aviso"></span>
                        </td>
                        </tr>
                        <tr>
                        <td><b>Nombre del cátalogo</b></td>
                        <td><html:text name="catalogoForm" property="nombre" maxlength="140"/></td>
                        </tr>
                    </tbody>
                </table>
                <logic:notEmpty name="catalogoForm" property="campos">
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
                                <html:text name="campos" property="nombre" indexed="true"/>
                                </td>

                                <td>
                                    <html:select name="campos" property="tipo" indexed="true"
                                                 styleClass="selector">
                                        <html:option value="texto">texto</html:option>
                                        <html:option value="numero">numero</html:option>
                                        <html:option value="fecha">fecha</html:option>
                                    </html:select>
                                </td>
                                <td td align="center">
                                    <logic:notEqual name="campos" property="tipo" value="usbid">
                                        <logic:notEqual name="campos" property="tipo" value="usuario">
                                            <html:checkbox name="campos" property="eliminado" indexed="true"
                                                           onclick="if (this.checked) {
                                                           anterior = document.getElementById('submit').value;
                                                           document.getElementById('submit').value='Eliminar Campos'
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
                        </logic:notEmpty>
                    </tbody>
                </table>                    
                <span style="color:#0ADF12;font-weight:bold;font-size:1.2em">Más campos</span>
                <html:text name="catalogoForm" styleId="mas"
                           property="nroCampos" value="0" size="1" maxlength="1"
                           onkeyup="if(this.value > 0 
                           && document.getElementById('submit').value!='Eliminar Campos') {
                           document.getElementById('submit').value='Agregar Campos'
                           } else if (this.value <= 0 
                           && document.getElementById('submit').value!='Eliminar Campos'){
                           document.getElementById('submit').value='Registrar'
                           }"/>
 
                <div align="center">
                    <html:submit value="Registrar" styleId="submit"/>
                </div>

            </html:form>
        </logic:equal>
    </body>
</html>
