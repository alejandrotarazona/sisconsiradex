
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrada al SiraDEx</title>
    </head>
    <body>
        <br><br>
        <div style="text-align:center;font-size:1.3em">  
            <span class ="warning">
                Para un buen funcionamiento del sistema se recomienda el uso de navegadores de alta compatibilidad con HTML5, como Chrome o Firefox.
            </span>
            <br><br><br>
            <logic:notPresent name="user">
                Para ingresar al SiraDEx utilizando su USBID, por favor, 
                <html:link action="/Login?method=page">haga click aquí</html:link>
            </logic:notPresent> 
            <logic:present name="user">
                Si desea ingresar al SiraDEx con otro USBID haga click en <b>(Cerrar Sesión)</b>
            </logic:present>
        </div>
    </body>
</html>