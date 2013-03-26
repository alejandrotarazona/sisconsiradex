
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<header>
    <div id="block-system-main-menu" class="block block-system block-menu first">          
                <ul class="menu">
                    <table bgcolor= "#E0F2F7">
                        <tr>
                <td><logic:present name="user">
                        Usuario <html:link action="/VerPerfilUsuario" title="Ver perfil">
                            <bean:write name="user" property="nombres" /> <bean:write name="user" property="apellidos" />
                        </html:link>
                        <html:link action="/Logout">
                            (Cerrar Sesión)
                        </html:link> |
                        <html:link page="/Login.do?method=page">Inicio</html:link> |
                    </logic:present>

                    <html:link href="http://localhost:8080/SiraDEx/construccion.html">Contactenos</html:link> |
                    <html:link href="http://localhost:8080/SiraDEx/construccion.html">Realizar busquedas públicas</html:link> |
                    <logic:present name="user" >
                        <logic:equal name="user" property="rol" value="DEx">
                            <html:link href="http://localhost:8080/SiraDEx/construccion.html">Realizar consultas avanzadas</html:link> |
                        </logic:equal>
                    </logic:present>
                    <html:link href="http://localhost:8080/SiraDEx/construccion.html">Ayuda</html:link>
                </td>
                </tr>
            </table>
        </ul>
    </div>
</header>