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
                if (count < 0) { document.getElementById(counter).innerHTML = "<span style=\"color: red;\">" + count + "</span>"; }
                else { document.getElementById(counter).innerHTML = count; }
            }
        </script>

        <title>SiraDEx | Gestión de Validaciones</title>
    </head>
    <body>

        <h1 class="title">Rechazar Actividad</h1>

        <logic:present name="actividadForm" property="mensajeError"><br>
            <div class ="error"><bean:write name="actividadForm" property="mensajeError"/></div><br>        </logic:present>

        <b>Por favor, escriba el motivo de rechazo.</b> (Se enviará un correo 
        automáticamente para informar al usuario.) 
        <html:form method="POST" action="/RechazarActividad?method=reject" >

            <html:textarea name="actividadForm" property="descripcion"  cols="150" 
                           rows="8" styleId="textbox"
                           onkeydown="countChars('textbox','char_count',2000)"
                           onfocus="countChars('textbox','char_count',2000)"
                           onkeyup="countChars('textbox','char_count',2000)"
                           onblur="if (this.value.length > 2000) return alert('El texto debe contener a lo sumo 2000 caracteres')"/> 

        <br>Máximo <span id="char_count">2000</span> caracteres.<br><br>

        <div align="center">
            <html:submit value="Rechazar"
                         onclick="return confirm('¿Está seguro que desea rechazar la actividad?')"/>
        </div>
    </html:form>

</body>

</html>
