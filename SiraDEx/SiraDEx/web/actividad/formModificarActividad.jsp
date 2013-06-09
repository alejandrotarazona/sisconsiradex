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

        <logic:present name="actividadForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="actividadForm" property="mensajeError"/></div>
            <br>
        </logic:present>

        <font size=2>
            Los campos con el asterisco  <span style="color:red">*</span> 
            son obligatorios.
        </font><br><br>
        Los campos con el nombre en <b>negrita</b> sirven para ingresar a los participantes de la 
        Actividad, el usuario que está registrando la Actividad debe ser ingresado en al menos una 
        de las listas desplegables de dichos campos.
        <br><br>

        <html:form method="POST" enctype="multipart/form-data" 
                   action ="/ModificarActividad?method=update">
            <table>
                <tbody>
                    <tr>
                    <th><b>Nombre</b></th>
                    <th><b>Valor</b></th>

                    </tr>
                    <logic:iterate name="actividadForm" property="camposValores" 
                                   id="camposValores" indexId="index">
                        <tr>
                        <td>
                            <logic:equal name="camposValores" property="campo.tipo" value="participante">
                                <b><bean:write name="camposValores" property="campo.nombre"/></b>
                            </logic:equal>
                            <logic:notEqual name="camposValores" property="campo.tipo" value="participante">
                                <bean:write name="camposValores" property="campo.nombre"/>
                            </logic:notEqual>
                            <logic:equal name="camposValores" property="campo.obligatorio" value="true">
                            <span style="color:red">*</span>  
                        </logic:equal>
                        </td>
                        <td>
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
                            <html:file name="camposValores" property="file" accept="application/pdf"
                                       indexed="true"/>
                            <b>Archivo previamente cargado:
                                <bean:write name="camposValores" property="valor"/></b> 
                            </logic:equal>

                        <logic:equal name="camposValores" property="campo.tipo" value="producto">
                            <html:file name="camposValores" property="file" accept="application/pdf"
                                       indexed="true"/> 
                            <b>Archivo previamente cargado:
                                <bean:write name="camposValores" property="valor"/></b> 
                            </logic:equal>

                        <%   int i = (Integer) pageContext.findAttribute("index");
                            String catalogoi = ("cat" + i);%>    

                        <logic:equal name="camposValores" property="campo.tipo" value="catalogo">
                            <html:select name="camposValores" property="valor" indexed="true">
                                <html:optionsCollection name='<%=catalogoi%>' label="contenido" 
                                                        value="contenido"/>
                            </html:select>
                        </logic:equal>
                        <logic:equal name="camposValores" property="campo.tipo" value="participante">
                            <html:text name="camposValores" property="valorAux" indexed="true"
                                       maxlength="80" style="color:gray;"
                                       onfocus="if (this.value=='Apellido(s), Nombre(s)'){
                                       this.value = ''}
                                       if (this.style.color='gray'){
                                       this.style.color='black'}" 
                                       onblur="if (this.value=='') 
                                       this.value = 'Apellido(s), Nombre(s)', this.style.color='gray'" >
                                <bean:write name="camposValores" property="valorAux"/>
                            </html:text>
                            <html:select name="camposValores" property="valor" indexed="true">
                                <html:option value="">-- Seleccione --</html:option>
                                <html:optionsCollection name='<%=catalogoi%>' label="contenido" 
                                                        value="contenido"/>
                            </html:select>
                        </logic:equal>
                        <logic:greaterThan name="camposValores" property="campo.longitud" value="1">
                            <html:hidden name="camposValores" styleId="${index}" 
                                         property="campo.longitud" indexed="true"/>
                            <html:submit styleId="botonSumar" value=" " title="Agregar"
                                         onclick="document.getElementById('${index}').value=document.getElementById('${index}').value-1"/>
                        </logic:greaterThan>
                        <logic:equal name="camposValores" property="campo.longitud" value="-1">
                            <html:hidden name="camposValores" styleId="${index}" 
                                         property="campo.longitud" indexed="true"/>
                            <html:submit styleId="botonRestar" value=" " title="Eliminar"
                                         onclick="document.getElementById('${index}').value=0"/>
                        </logic:equal>
                        </td>  
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
            <br>

            <div align="center"><html:submit value="Modificar"
                         onclick="return confirm('¿Está seguro que desea modificar la actividad?')"/></div>

        </html:form>
    </body>
</html>
