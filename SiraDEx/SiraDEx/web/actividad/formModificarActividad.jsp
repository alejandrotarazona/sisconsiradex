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

<link rel="stylesheet" type="text/css" href="<html:rewrite page="/Interfaz/Stylesheets/jquery-ui-1.9.2.custom.css"/>"/>


<script type="text/javascript">
    $(function() {		
        $("#fecha_input input").datepicker({
            changeMonth: true,
            changeYear: true
        });

        $( "#fecha_input input" ).datepicker(
        "option", "dateFormat", "dd/mm/yy" 
    );
	
        $( "#fecha_input input" ).datepicker({
            dayNamesMin: [ "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab" ] 
        });		

        $( "#fecha_input input" ).datepicker( "option", "yearRange", "1970:2013" );
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
                <td><b>Nombre</b></td>
                <td><b>Valor</b></td>

            </tr>
            <logic:iterate name="actividadForm" property="camposValores" 
                           id="campoValor" indexId="index">
                <tr>
                <td><bean:write name="campoValor" property="campo.nombre"></bean:write>
                    <logic:equal name="campoValor" property="campo.obligatorio" 
                                 value="true">
                    <span style="color:red">*</span>  
                </logic:equal>
            </td>
            <td><logic:equal name="campoValor" property="campo.tipo" value="texto">
                    <html:text name="campoValor" property="valor" indexed="true">
                        <bean:write name="campoValor" property="valor"/>
                    </html:text>
                </logic:equal>

                <logic:equal name="campoValor" property="campo.tipo" value="numero">
                    <html:text name="campoValor" property="valor" indexed="true">
                        <bean:write name="campoValor" property="valor"/>
                    </html:text> 
                </logic:equal>

                <logic:equal name="campoValor" property="campo.tipo" value="fecha">
                <span id="fecha_input"> <html:text name="campoValor" property="valor" 
                           indexed="true" >
                        <bean:write name="campoValor" property="valor"/>
                    </html:text></span>  
                </logic:equal>

            <logic:equal name="campoValor" property="campo.tipo" value="checkbox">
                <html:checkbox name="campoValor" property="valor" indexed="true"/> 
            </logic:equal>

            <logic:equal name="campoValor" property="campo.tipo" value="textol">
                <html:textarea name="campoValor"  cols="campo.longitud" rows="4"
                               property="valor" indexed="true">
                    <bean:write name="campoValor" property="valor"/>
                </html:textarea>
            </logic:equal>
            <logic:equal name="campoValor" property="campo.tipo" value="archivo">
                <html:file name="campoValor" property="file" indexed="true"/>
            </logic:equal>

            <%   int i = (Integer) pageContext.findAttribute("index");
                String catalogoi = ("cat" + i);%>    

            <logic:equal name="campoValor" property="campo.tipo" value="catalogo">
                <html:select name="campoValor" property="valor" indexed="true">
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
