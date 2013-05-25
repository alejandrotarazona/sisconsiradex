
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SiraDEx | Entradas Directas</title>
    <html:base/>
</head>
<body>
    <h1 class="title">Entrar como otro Tipo de Usuario</h1>
    
    <logic:present name="usuarioForm" property="mensaje"><br>
        <div class ="status"><bean:write name="usuarioForm" property="mensaje" /></div>
    </logic:present> 
    <logic:present name="usuarioForm" property="mensajeError"><br>
        <div class ="error"><bean:write name="usuarioForm" property="mensajeError" /></div>
    </logic:present>

    <h1>Entrar como:</h1>
    <ul>
        <li><html:link action="/Entrar?method=inEA">Empleado Administrativo</html:link></li>
        <li><html:link action="/Entrar?method=inPO">Personal Obrero</html:link></li>
        <li><html:link action="/Entrar?method=inES">Estudiante</html:link></li>
        <li><html:link action="/Entrar?method=inProf">Profesor</html:link></li>
        <li><html:link action="/Entrar?method=inDEx">Jefe de Dependencia del DEx</html:link></li>
    </ul>
</body>
