
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
            <font size=2>Para ingresar al SiraDEx utilizando su USBID, por favor
                <html:link action="/Login?method=page">haga click aquí</html:link></font>   
            </logic:notPresent> 
            <logic:present name="user">
            <br><br>
            <font size=2>Para ingresar al SiraDEx con otro USBID, debe cerrar sesión
            </logic:present></font>

    </body>
</html>