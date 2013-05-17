
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Registro de Catálogo</title>
    </head>

    <body>

        <logic:present name="catalogoForm" property="mensaje">
            <br><div class ="status">
                <bean:write name="catalogoForm" property="mensaje"/>
            </div>
        </logic:present> 
        <logic:present name="catalogoForm" property="mensajeError">
            <br><div class ="error">
                <bean:write name="catalogoForm" property="mensajeError"/>
            </div>
        </logic:present><br>

        <h1 class="title" id="page-title">Registro de Catálogo</h1>
        <font size=2>Los campos con el asterisco  <span style="color:red">*</span> 
            son obligatorios.</font><br><br>
        
        <html:form method="POST" action ="/RegistrarCatalogo?method=save">
            Marque la casilla para definir como un catálogo de usuarios
                <html:checkbox name="catalogoForm" property="participantes"/>
    <br><br>Nombre<span style="color:red">*</span>
    <html:text name="catalogoForm" property="nombre"/>
    &nbsp;&nbsp;&nbsp;&nbsp;
    Número de campos<span style="color:red">*</span>
<html:text name="catalogoForm" property="nroCampos" size="1" maxlength="2"/>
<br><br>
<div align="center"><html:submit>Siguiente</html:submit></div>
</html:form>
</body>
</html>
