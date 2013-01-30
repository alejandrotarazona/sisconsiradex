
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<header>
    <div id="block-system-main-menu" class="block block-system block-menu first">
        <div class="inner">
            <div class="content">
                <ul class="menu">
                    <table bgcolor= "#E0F2F7">
                        <tr>
                        <td><logic:present name="user" >
                                Usuario <bean:write name="usuarioForm" property="username" />
                                <html:link action="/Logout">
                                    (Cerrar Sesión)
                                </html:link> |
                                <a href="bienvenida.jsp">Inicio</a> |
                            </logic:present>
                            <a href="construccion.html">Contactenos</a> |
                            <a href="construccion.html">Realizar busquedas públicas</a> |
                            <logic:present name="user" >
                                <logic:equal name="user" property="tipo" value="DEx">
                                    <a href="Interfaz/Plantillas/construccion.html">Realizar consultas avanzadas</a> |
                                </logic:equal>
                            </logic:present>
                            <a href="construccion.html">Ayuda</a>
                        </td>
                        </tr>
                    </table>
                </ul>
            </div>
        </div>
    </div>

</header>