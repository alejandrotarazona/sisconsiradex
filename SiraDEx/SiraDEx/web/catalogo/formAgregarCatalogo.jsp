
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registro de Catálogo</title>
    </head>

    <body>

        <h1 class="title" id="page-title">Registro de Catálogo</h1>
        <font size=2>Los campos con el asterisco  <span style="color:red">*</span> 
            son obligatorios.</font><br><br>

        <logic:present name="catalogoForm" property="mensaje">
            <br><div class ="status">
                <bean:write name="catalogoForm" property="mensaje"/>
            </div>
        </logic:present> 
        <logic:present name="catalogoForm" property="mensajeError">
            <br><div class ="error">
                <bean:write name="catalogoForm" property="mensajeError"/>
            </div>
        </logic:present><br>


        <html:form method="POST" action ="/RegistrarCatalogo?method=save">
            <table>
                <tbody>
                    <tr>
                    <td width="20%">Catálogo de Usuarios</td>
                    <td>
                        <html:checkbox name="catalogoForm" property="participantes" value="off"
                                       onclick="if (this.value == 'off') { this.value = 'on'
                                       document.getElementById('aviso').innerHTML='<b>Esta opción agrega por defecto un campo para el usb-id del usuario.<b>'
                                       } else {document.getElementById('aviso').innerHTML='', this.value = 'off'}"/>
                        <html:hidden name="catalogoForm" property="participantes" value="false"/>
                    <span id="aviso"></span>
                    </td>
                    </tr>
                    <tr>
                    <td>Nombre del cátalogo<span style="color:red">*</span></td>
                    <td><html:text name="catalogoForm" property="nombre"/></td>
                    </tr>
                    <tr>
                    <td>Número de campos<span style="color:red">*</span></td>
                    <td><html:text name="catalogoForm" property="nroCampos" size="1" maxlength="2"/>
                    </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <html:submit>Siguiente</html:submit>
        </html:form>
    </body>
</html>
