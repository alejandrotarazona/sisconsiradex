
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
                                       document.getElementById('aviso').innerHTML='<b>Esta opción agrega por defecto un campo para el usb-id del usuario.<b>'
                                       } else {document.getElementById('aviso').innerHTML='', this.value = 'off'}"/>
                        <html:hidden name="catalogoForm" property="participantes" value="false"/>
                    <span id="aviso"></span>
                    </td>
                    </tr>
                    <tr>
                    <td><b>Nombre del cátalogo</b></td>
                    <td><html:text name="catalogoForm" property="nombre"/></td>
                    </tr>
                    <tr>
                    <td><b>Número de campos</b></td>
                    <td><html:text name="catalogoForm" property="nroCampos" size="1" maxlength="1"/>
                    </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <div align="center"><html:submit>Siguiente</html:submit></div>
        </html:form>
    </body>
</html>
