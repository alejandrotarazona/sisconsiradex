
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

        <logic:notPresent name="user">
            <br><br>
            <div style="margin-left:100px;font-size:1.3em">
                Para ingresar al SiraDEx utilizando su USBID, por favor
                <html:link action="/Login?method=page">haga click aquí</html:link>
            </div>   
            </logic:notPresent> 
            <logic:present name="user">
            <br><br>
            <div style="margin-left:90px;font-size:1.3em">
                Si desea ingresar al SiraDEx con otro USBID haga click en <b>(Cerrar Sesión)</b>
            </div>
            </logic:present>

    </body>
</html>