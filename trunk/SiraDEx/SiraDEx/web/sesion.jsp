<%-- 
    Document   : sesion
    Created on : 29/11/2012, 12:29:21 AM
    Author     : SisCon
--%>

<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<logic:equal name="user" property="rol" value="DEx">
    <jsp:forward page="AGestionActividades.do?method=listDex"/>
</logic:equal>

<logic:equal name="user" property="rol" value="WM">
    <jsp:forward page="AGestionActividades.do?method=listAll"/>
</logic:equal>

<logic:equal name="user" property="rol" value="profesor">
    <jsp:forward page="AGestionActividades.do?method=listUser"/>
</logic:equal> 

<logic:equal name="user" property="rol" value="obrero">
    <jsp:forward page="AGestionActividades.do?method=listUser"/>
</logic:equal>

<logic:equal name="user" property="rol" value="estudiante">
    <jsp:forward page="AGestionActividades.do?method=listUser"/>
</logic:equal>

<logic:equal name="user" property="rol" value="empleado">
    <jsp:forward page="AGestionActividades.do?method=listUser"/>
</logic:equal>
