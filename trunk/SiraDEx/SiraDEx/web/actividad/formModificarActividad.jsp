<%-- 
    Document   : formModificarActividad
    Created on : Mar 1, 2013, 1:56:33 AM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="Interfaz/Scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" src="Interfaz/Scripts/jquery-ui-1.9.2.custom.js"></script>
<link rel="stylesheet" type="text/css" 
      href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>

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
        <title>SiraDEx | Edición de <bean:write name="actividadForm"
                    property="nombreTipoActividad"/></title>

    </head>
    <body>
        <h1 class='title' id='page-title'>Edición de <bean:write 
                name="actividadForm" property="nombreTipoActividad"/> </h1>

        <logic:present name="actividadForm" property="mensaje">
            <br/> <div align="center"><b><bean:write name="actividadForm" 
                        property="mensaje" /></b></div><br/>
                </logic:present>

        <html:form method="POST" enctype="multipart/form-data" 
                   action ="/ModificarActividad?method=update">
            <table>

                <tr>
                <th><b>Nombre</b></th>
                <th><b>Valor</b></th>

            </tr>
            <logic:iterate name="actividadForm" property="camposValores" 
                           id="camposValores" indexId="index">
                <tr>
                <td><bean:write name="camposValores" property="campo.nombre"></bean:write>
                    <logic:equal name="camposValores" property="campo.obligatorio" 
                                 value="true">
                    <span style="color:red">*</span>  
                </logic:equal>
            </td>
            <td><logic:equal name="camposValores" property="campo.tipo" value="texto">
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
                               readonly="true">
                        <bean:write name="camposValores" property="valor"/>
                    </html:text>
                </span>
                <span class="fecha_click">
                    <html:hidden name="camposValores" property="valor" indexed="true" />
                </span>
            </logic:equal>

            <logic:equal name="camposValores" property="campo.tipo" value="checkbox">
                <html:checkbox name="camposValores" property="valor" indexed="true" />
                <html:hidden name="camposValores" property="valor" value="false" 
                             indexed="true"/>
            </logic:equal>

            <logic:equal name="camposValores" property="campo.tipo" value="textol">
                <html:textarea name="camposValores"  cols="campo.longitud" rows="4"
                               property="valor" indexed="true">
                    <bean:write name="camposValores" property="valor"/>
                </html:textarea>
            </logic:equal>

            <logic:equal name="camposValores" property="campo.tipo" value="archivo">
                <html:file name="camposValores" property="file" indexed="true" 
                           maxlength="2024" size="2024"/>
            </logic:equal>

            <logic:equal name="camposValores" property="campo.tipo" value="producto">
                <html:file name="camposValores" property="file" indexed="true" 
                           maxlength="2024" size="2024"/>
            </logic:equal>

            <%   int i = (Integer) pageContext.findAttribute("index");
                String catalogoi = ("cat" + i);%>    

            <logic:equal name="camposValores" property="campo.tipo" value="catalogo">
                <html:select name="camposValores" property="valor" indexed="true">
                    <html:optionsCollection name='<%=catalogoi%>' label="contenido" 
                                            value="contenido"/>
                </html:select>
            </logic:equal>
        </td>  
    </tr>
</logic:iterate>
</table>
<br>

<div align="center"><html:submit value="Modificar"
             onclick="return confirm('¿Está seguro que desea modificar la actividad?')"/></div>

</html:form>
</body>
</html>
