<%-- 
    Document   : formAgregarActividad2
    Created on : 02/11/2012, 05:14:54 PM
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

        <script type="text/javascript">
            $(function() {
                $(".fecha_input input").datepicker();
                $(".fecha_click click").datepicker();
            })	
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registrar Actividad</title>
    </head>
    <body>
        <table>
            <tr>
            <td align="left">
                <html:link title="Elegir otro Tipo de Actividad" action="/RegistrarActividad?method=page"> 
                    <html:img src="../Stylesheets/iconos/regresar.png"/>
                </html:link>
            </td>
            <td align="right">
                <html:img src="../Stylesheets/iconos/ayuda.png" 
                            title="Los campos con el nombre en negrita sirven para ingresar a los participantes de la Actividad. El usuario que está registrando la Actividad debe ser ingresado en al menos una de las listas desplegables de dichos campos."/>   
            </td>
        </tr>
    </table>
    <h1 class='title'>Registro de Actividad de <bean:write 
            name="actividadForm" property="nombreTipoActividad"/> </h1>

    <logic:present name="actividadForm" property="mensaje"><br>
        <div class ="error"><bean:write name="actividadForm" property="mensaje"/></div>
        <br>
    </logic:present>

    <font size=2>
        Los campos con el asterisco <span style="color:red">*</span> 
        son obligatorios.
    </font><br><br>

    <html:form method="POST"
               enctype="multipart/form-data"
               action="/RegistrarActividad?method=save2">
        <table class="cebra">  
            <tbody>
                <logic:iterate name="actividadForm" property="camposValores" id="camposValores" 
                               indexId="index">
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
                    <td><logic:equal name="camposValores" property="campo.tipo" value="texto">
                            <html:text name="camposValores" property="valor" indexed="true"
                                       maxlength="${camposValores.campo.longitud}"/>  
                        </logic:equal>

                        <logic:equal name="camposValores" property="campo.tipo" value="numero">
                            <html:text name="camposValores" property="valor" indexed="true"
                                       maxlength="${camposValores.campo.longitud}"/> 
                        </logic:equal>

                        <logic:equal name="camposValores" property="campo.tipo" value="fecha">    
                        <span class="fecha_input">
                            <html:text name="camposValores" property="valor" indexed="true" 
                                       readonly="true" ondblclick="this.value = ''"
                                       title="Haga doble click para borrar la fecha."/>
                        </span>
                        <span class="fecha_click">
                            <html:hidden name="camposValores" property="valor" indexed="true"/>
                        </span>
                    </logic:equal>

                    <logic:equal name="camposValores" property="campo.tipo" value="checkbox">
                        <html:checkbox name="camposValores" property="valor" indexed="true"/>
                        <html:hidden name="camposValores" property="valor" value="false" 
                                     indexed="true"/>
                    </logic:equal>

                    <logic:equal name="camposValores" property="campo.tipo" value="textol">
                        <html:textarea name="camposValores" property="valor" indexed="true"/> 
                    </logic:equal>

                    <logic:equal name="camposValores" property="campo.tipo" value="archivo">
                        <html:file name="camposValores" property="file" accept="application/pdf"
                                   indexed="true" />
                    </logic:equal>

                    <%   int i = (Integer) pageContext.findAttribute("index");
                            String catalogoi = ("cat" + i);%>    

                    <logic:equal name="camposValores" property="campo.tipo" value="catalogo">
                        <html:select name="camposValores" property="valor" indexed="true">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name='<%=catalogoi%>' label="contenido" 
                                                    value="contenido"/>
                        </html:select>
                    </logic:equal>
                    <logic:equal name="camposValores" property="campo.tipo" value="participante">
                        <logic:equal name="camposValores" property="valorAux" 
                                     value="Apellido(s), Nombre(s)">
                            <html:text  name="camposValores" property="valorAux" indexed="true"
                                        maxlength="80" style="color:gray;" 
                                        onfocus="if (this.value=='Apellido(s), Nombre(s)'){
                                        this.value = ''}
                                        if (this.style.color='gray'){
                                        this.style.color='black'}" 
                                        onblur="if (this.value=='') 
                                        this.value = 'Apellido(s), Nombre(s)', this.style.color='gray'" 
                                        /> 
                        </logic:equal>
                        <logic:notEqual name="camposValores" property="valorAux" 
                                        value="Apellido(s), Nombre(s)">
                            <html:text  name="camposValores" property="valorAux" indexed="true"
                                        maxlength="80" 
                                        onfocus="if (this.value=='Apellido(s), Nombre(s)'){
                                        this.value = ''}
                                        if (this.style.color='gray'){
                                        this.style.color='black'}" 
                                        onblur="if (this.value=='') 
                                        this.value = 'Apellido(s), Nombre(s)', this.style.color='gray'" 
                                        />
                        </logic:notEqual>
                        <html:select name="camposValores" property="valor" indexed="true">
                            <html:option value="">-- Seleccione --</html:option>
                            <html:optionsCollection name='<%=catalogoi%>' label="contenido" 
                                                    value="contenido"/>
                        </html:select>
                        <logic:greaterThan name="camposValores" property="campo.longitud" value="1">
                            <html:hidden name="camposValores" styleId="${index}" 
                                         property="campo.longitud" indexed="true"/>
                            <html:submit styleId="botonSumar" value=" " title="Agregar"
                                         onclick="document.getElementById('${index}').value=document.getElementById('${index}').value-1"/>
                            <span style="color: gray;font-weight:bold;font-size:14px;cursor: pointer" 
                                  title="Número de campos disponibles para este tipo de participante">
                                ${camposValores.campo.longitud-1}
                            </span>
                        </logic:greaterThan>
                        <logic:equal name="camposValores" property="campo.longitud" value="-1">
                            <html:hidden name="camposValores" styleId="${index}" 
                                         property="campo.longitud" indexed="true"/>
                            <html:submit styleId="botonRestar" value=" " title="Eliminar"
                                         onclick="document.getElementById('${index}').value=0"/>
                        </logic:equal>

                    </logic:equal>
                    </td>
                    </tr>
                </logic:iterate>
            </tbody>
        </table>
        <br>
        <div align="center"><html:submit>Registrar</html:submit></div>

    </html:form>
</body>
</html>