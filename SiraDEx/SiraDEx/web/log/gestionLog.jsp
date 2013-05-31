<%-- 
    Document   : gestionarLog
    Created on : May 30, 2013, 11:27:56 PM
    Author     : SisCon
--%>
<%@page import="Clases.Actividad"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        
                <script type="text/javascript" src="../Scripts/jquery.min.js"></script>
        <script type="text/javascript" language="javascript" src="../Scripts/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../Scripts/ColReorderWithResize.js"></script>
        <style type="text/css" title="currentStyle">
            @import "../Stylesheets/demo_table_jui.css";
        </style>
        <script>
            $(document).ready(function(){

                $('#datatab').dataTable({
                    "aoColumns": [       
                        /* Actividad */ null,
                        /* Creación */ null,
                        /* Modificación */ 
                        { "bSortable": false },
                       
                    ]});
            });
        </script>

        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Log del sistema </title>
    </head>
    <body>
        
        <h1 class="title">Consulta del Log de actividades del sistema</h1>
        <br>
        
    <logic:present name="acts">
        
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatab">
                <thead>
                    <tr>
                    <th>Actividad</th>
                    <th>Creación</th>
                    <th>Modificación</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <logic:iterate name="acts" id="act">
                        <tr><td>
                            <bean:write name="act" property="nombreTipoActividad"/>
                            <% Actividad a = (Actividad) pageContext.findAttribute("act");
                                out.print(". Participantes: "+a.participantesToString());%>
                            <% out.print(" Campos: "+a.camposValoresToString());%>
                        </td>
                        <td>
                            <bean:write name="act" property="creador"></bean:write>, 
                            <bean:write name="act" property="fechaCreacion"></bean:write>
                        </td>
                        <td>
                            <logic:present  name="act" property="modificador">
                                <bean:write name="act" property="modificador"></bean:write>, 
                                <bean:write name="act" property="fechaModif"></bean:write>
                            </logic:present>

                        </td></tr>
                </logic:iterate>   
                </tbody> 
            </table>
        </logic:present>
      
    </body>
</html>
