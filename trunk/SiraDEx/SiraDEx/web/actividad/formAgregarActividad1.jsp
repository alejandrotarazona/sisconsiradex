<%-- 
    Document   : formAgregarActividad1
    Created on : Oct 26, 2012, 8:37:29 AM
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
            function submitform()
            {
                if (document.actividadForm.onsubmit &&
                        !document.actividadForm.onsubmit())
                {
                    return;
                }
                document.actividadForm.submit();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Agregar Actividad</title>
    </head>

    <body>
        <logic:equal name="permiso" value="dex">
            <div align="center" class ="warning">
                Usted no tiene permiso para acceder a esta página del SiraDEx.
            </div>
        </logic:equal>
        <logic:notEqual name="permiso" value="dex">
            <h1 class="title">Registro de Actividad</h1>

            <logic:present name="tipos">
                <font size=2>Seleccione el Tipo de Actividad que desea agregar.</font><br><br>
                    <html:form method="POST" action="/RegistrarActividad?method=save">
                    <ul>
                        <logic:iterate name="tipos" id="ta">
                            <html:hidden name="actividadForm" 
                                         property="idTipoActividad" value="${ta.id}"/>
                            <li>
                                <a href="javascript: submitform()">
                                    <bean:write name="ta" property="nombreTipo"/>
                                </a>
                            </li>
                        </logic:iterate>
                    </ul>
                </html:form>
            </logic:present> 
            <logic:notPresent name="tipos">
                <br><br>
                <div align="center" style="font-size:1.2em">
                    Por ahora no hay ningún Tipo de Actividad para los usuarios de tipo ${user.rol}
                </div>
            </logic:notPresent>
        </logic:notEqual>
    </body>
</html>
