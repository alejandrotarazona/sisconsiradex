
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiraDEx | Gestion de Elementos del catálogo</title>
    </head>
    <body>
        <h1 class="title" id="page-title">Gestion de elementos del catálogo</h1>
        <logic:present name="elementoCatalogoForm" property="mensaje">
            <bean:write name="elementoCatalogoForm" property="mensaje" /><br/>
        </logic:present>

        <logic:equal name="user" property="rol" value="WM">

            <html:link action="/RegistrarElemento?method=page"> 
                Agregar elemento
            </html:link><br/>  

        </logic:equal>


        <h1 class="title" id="page-title">Elementos del Catálogo</h1>
        <logic:notPresent name="elemtos">
            No hay actividad que mostrar
        </logic:notPresent>
        <logic:present name="elemtos">
            <logic:empty name="elemtos">
                No hay actividad que mostrar
            </logic:empty>
            <table>
                <logic:iterate name="elemtos" id="elem">

                    <logic:iterate name="eleme" property="camposValores" id="campoValor" indexId="index">
                        <tr>
                            <bean:write name="campoValor" property="campo.nombre"></bean:write>: 
                            <bean:write name="campoValor" property="valor"></bean:write><br>
                        </tr>
                    </logic:iterate>

                </logic:iterate>

            </logic:present>

        </table>

    </body>
</html>
