<%-- 
    Document   : formModificarElemento
    Created on : 12/02/2013, 05:09:07 PM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script type="text/javascript">
    $(function() {
        $(".fecha_input input").datepicker();
        $(".fecha_click click").datepicker();
    })
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Edición del Catálogo</title>

    </head>
    <body>
        <html:link title="Volver" action="/GestionElementos"> 
            <html:img src="../Stylesheets/iconos/regresar.png"/>
        </html:link><br>
        <h1 class='title'>Edición del Catálogo 
            <bean:write name="elementoCatalogoForm" property="nombreCatalogo"/> 
        </h1>

        <logic:present name="elementoCatalogoForm" property="mensaje"><br>
            <div class ="error"><bean:write name="elementoCatalogoForm" property="mensaje"/></div>
            <br>
        </logic:present>

        <html:form method="POST" action ="/ModificarElementoCatalogo?method=update">
            <table>
                <tbody>
                    <tr>
                    <td width="20%"><b>Nombre del campo</b></td>
                    <td><b>Valor</b></td>
                    </tr>

                    <logic:iterate name="elementoCatalogoForm" property="camposValores" 
                                   id="camposValores" indexId="index">
                        <tr>
                        <td>
                            <bean:write name="camposValores" property="campo.nombre"/>
                        </td>    
                        <td>
                            <logic:equal name="camposValores" property="campo.tipo" value="usbid">
                                <html:text name="camposValores" property="valor" indexed="true">
                                    <bean:write name="camposValores" property="valor"/>
                                </html:text>
                            </logic:equal>

                            <logic:equal name="camposValores" property="campo.tipo" value="texto">
                                <html:text name="camposValores" property="valor" indexed="true">
                                    <bean:write name="camposValores" property="valor"/>
                                </html:text>
                            </logic:equal>

                            <logic:equal name="camposValores" property="campo.tipo" value="numero">
                                <html:text name="camposValores" property="valor" indexed="true">
                                    <bean:write name="camposValores" property="valor"/>
                                </html:text> 
                            </logic:equal>

                            <logic:equal name="camposValores" property="campo.tipo" value="fecha"> 
                            <span class="fecha_input">
                                <html:text name="camposValores" property="valor" indexed="true"
                                           readonly="true" ondblclick="this.value = ''"
                                           title="Haga doble click para borrar la fecha">
                                    <bean:write name="camposValores" property="valor"/>
                                </html:text>
                            </span>
                            <span class="fecha_click">
                                <html:hidden name="camposValores" property="valor" indexed="true" />
                            </span>
                        </logic:equal>
                        </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
            <br>

            <div align="center"><html:submit value="Modificar"
                         onclick="return confirm('¿Está seguro que desea modificar el elemento?')"/></div>

        </html:form>
    </body>
</html>
