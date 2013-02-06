<%-- 
    Document   : gestionCatalogo
    Created on : 02/02/2013, 11:15:51 PM
    Author     : SisCon
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <body>
        <h1 class="title" id="page-title">Gestion de Catálogos</h1>
        <logic:present name="catalogoForm" property="mensaje">
            <bean:write name="catalogoForm" property="mensaje" /><br/>
        </logic:present>
        <html:link action="/RegistrarCatalogo?method=page"> 
            Registrar catálogo
        </html:link><br/>  

        <h1 class="title" id="page-title">Catálogos registrados en el sistema</h1>
        <logic:notPresent name="catalogos">
            No hay catalogos que mostrar
        </logic:notPresent>
        <logic:present name="catalogos">
            <logic:empty name="catalogos">
                No hay catalogos que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="catalogos" id="cat">
                    <tr>
                    <bean:write name="cat" property="nombre"></bean:write>
                </tr>
            </logic:iterate>
        </table>
    </logic:present>
</body>
</html>