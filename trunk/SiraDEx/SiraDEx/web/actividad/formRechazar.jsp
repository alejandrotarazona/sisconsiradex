<%-- 
    Document   : formRechazar
    Created on : 29/05/2013, 1:12:21 AM
    Author     : SisCon
--%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <script type="text/javascript">
            function countChars(textbox, counter, max) {
                var count = max - document.getElementById(textbox).value.length;
                if (count < 0) {
                    document.getElementById(counter).innerHTML = "<span style=\"color: red;\">" + count + "</span>";
                }
                else {
                    document.getElementById(counter).innerHTML = count;
                }
            }
        </script>

        <title>SiraDEx | Gestión de Validaciones</title>
    </head>
    <body>
        <logic:notPresent name="permiso">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:notPresent>
        <logic:present name="permiso">
            <h1 class="title">Rechazar Actividad</h1>


            <logic:present name="actividadForm" property="mensaje"><br>
                <div class ="error"><bean:write name="actividadForm" property="mensaje"/></div>
                <br>
            </logic:present>    

            <b>Por favor, escriba el motivo de rechazo.</b> (Se enviará un correo 
            automáticamente para informar al usuario.)<br><br> 
            <html:form method="POST" action="/RechazarActividad?method=reject" >

                <html:textarea name="actividadForm" property="descripcion"  cols="100" 
                               rows="8" styleId="textbox"
 
                               onkeyup="countChars('textbox','char_count',2000);
                               if (this.value.length > 2000 || this.value.length <= 0) { 
                               document.getElementById('rechazar').disabled=true;
                               } else {
                               document.getElementById('rechazar').disabled=false;
                               }"
                               onfocus="countChars('textbox','char_count',2000)"/> 

            <br>Máximo <span id="char_count">2000</span> caracteres.<br><br>

            <div align="center">
                <html:submit value="Rechazar" styleId="rechazar" disabled="true"
                             onclick="return confirm('¿Está seguro que desea rechazar la actividad?')"/>
            </div>
        </html:form>
    </logic:present>
</body>

</html>
