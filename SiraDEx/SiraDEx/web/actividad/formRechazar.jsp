<%-- 
    Document   : formRechazar
    Created on : 29/05/2013, 1:12:21 AM
    Author     : SisCon
--%>

<%@page import="Clases.Actividad"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head profile="http://www.w3.org/1999/xhtml/vocab">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="../Imagenes/favicon.ico" type="image/vnd.microsoft.icon" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta content="Decanato de Extensión" about="/node/1" property="dc:title" />
        <link rel="shortlink" href="/node/1" />
        <meta name="Generator" content="Drupal 7 (http://drupal.org)" />
        <link rel="canonical" href="/node/1" />
        <style type="text/css" media="all">
            @import url("Interfaz/Stylesheets/base.css");
        </style>
        <style type="text/css" media="all">
            @import url("Interfaz/Stylesheets/ckeditor.css");
            @import url("Interfaz/Stylesheets/ctools.css");
        </style>
        <style type="text/css" media="all">
            @import url("Interfaz/Stylesheets/layout.css");
            @import "Interfaz/Stylesheets/style.css";
            @import url("Interfaz/Stylesheets/forms.css");
            @import url("Interfaz/Stylesheets/colors.css");
        </style>
        <style type="text/css" media="print">
            @import url("Interfaz/Stylesheets/print.css");
        </style>

        <!--[if lte IE 7]>
        <link type="text/css" rel="stylesheet" href="http://www.dex.usb.ve/themes/sky/css/ie.css?max621" media="all" />
        <![endif]-->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.once.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/drupal.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/es_4XpqCflKazTuXzLphPzjQcs4p_0-3cW6doKJyQdMlBo.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/scripts.js"></script>
        <script type="text/javascript" src="Interfaz/Scripts/jquery.min.js"></script>

        <script type="text/javascript">
            function countChars(textbox, counter, max) {
                var count = max - document.getElementById(textbox).value.length;
                if (count < 0) { document.getElementById(counter).innerHTML = "<span style=\"color: red;\">" + count + "</span>"; }
                else { document.getElementById(counter).innerHTML = count; }
            }
        </script>


        <title>SiraDEx | Gestión de Validaciones</title>
    </head>
    <div id="container" class="container-12">
        <div id="main" class="section section-main clearfix" role="main">
            <div id="content" class="column grid-6 push-1">
                <div class="inner">
                    <div id="main-content" class="clearfix">

                        <body>

                            <h1 class="title">Rechazar Actividad</h1>

                            <logic:present name="actividadForm" property="mensajeError"><br>
                                <div class ="error"><bean:write name="actividadForm" property="mensajeError"/></div>
                            </logic:present>

                            <% Actividad a = (Actividad) pageContext.findAttribute("actividadForm");
                                String s = (String) String.valueOf(a.getIdActividad());
                                out.print(String.valueOf(a.getIdActividad()));%>
                            Por favor, escriba el motivo de rechazo.
                            <html:form method="POST" action="/RechazarActividad?method=reject" >

                                <html:textarea name="actividadForm" property="descripcion"  cols="70" 
                                               rows="4" styleId="textbox"
                                               onkeydown="countChars('textbox','char_count',160)"
                                               onfocus="countChars('textbox','char_count',160)"
                                               onkeyup="countChars('textbox','char_count',160)"
                                               onblur="if (this.value.length > 160) return alert('El motivo debe ser menor de 160 caracteres')"/> 

                            <br>Máximo <span id="char_count">160</span> caracteres.<br><br>

                            <div align="center">
                                <html:submit value="Rechazar"
                                             onclick="return confirm('¿Está seguro que desea rechazar la actividad?')"/>
                            </div>
                        </html:form>

                        </body>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>
