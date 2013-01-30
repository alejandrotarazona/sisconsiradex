
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrada al SiraDEx</title>
        <html:base/>
    </head>
    <body style="background-color: white">

        <logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
            <div  style="color: red">
                ERROR:  Application resources not loaded -- check servlet container
                logs for error messages.
            </div>
        </logic:notPresent>


        <p>Para ingresar al SiraDEx utilizando su USBID, por favor
            <a href="http://localhost:8080/SiraDEXS1/construccion.html">haga click aqu&iacute;</a>
        </p><br>

        <html:link action="/Entrar?method=inCU">Entrar como comunidad universitaria</html:link>
        <br>
        <html:link action="/Entrar?method=inProf">Entrar como profesor</html:link>
        <br>
        <html:link action="/Entrar?method=inDEx">Entrar como personal del DEx</html:link>
        <br>
        <html:link action="/Entrar?method=inWM">Entrar como webmaster</html:link>

    </body>
</html:html>
