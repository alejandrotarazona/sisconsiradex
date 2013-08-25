<%-- 
    Document   : autenticacion
    Created on : 28/11/2012, 10:51:10 PM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <title>Servicio Centralizado de Autenticación (CAS)</title>
    </head>
    <body >
        <table border="0" width="100%">
            <tr>

                <td align="center">
                    <h1>Universidad Sim&oacute;n Bol&iacute;var</h1>
                    <h2>Servicio Centralizado de Autenticación</h2>
                </td>
            </tr>
        </table>

        <logic:present name="usuarioForm" property="mensaje"><br>
                <div style ="font-size: 1.154em;text-align: center;font-weight: bold;background-color: #FFA07A;color: brown;">
                    <bean:write name="usuarioForm" property="mensaje"/>
                </div>
            </logic:present>
            <p style="text-align: center;">Por razones de seguridad, por favor cierre la sesión y su navegador web cuando haya terminado de acceder a los servicios que requieren autenticación.</p>

            <p align="center"><strong>Introduzca su USBID y Contraseña.</strong></p>
            <p>
                <html:form method="POST" action="/Login?method=save">
                    <table align="center">
                        <tr>
                            <td>USBID:</td> 
                            <td><html:text name="usuarioForm" property="username"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>Contraseña:</td>
                                <td><html:password name="usuarioForm" property="password"></html:password>
                                </td>
                            </tr>
                        </table>
                        <p style="text-align: center;">
                        <html:submit>Login</html:submit>
                        </p>
                </html:form>

                </body>
                </html>



