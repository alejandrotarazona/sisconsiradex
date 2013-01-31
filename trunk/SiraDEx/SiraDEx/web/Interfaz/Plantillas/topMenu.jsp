
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<header>
    <div id="block-system-main-menu" class="block block-system block-menu first">          
                <ul class="menu">
                    <table bgcolor= "#E0F2F7">
                        <tr>
                        <td><logic:present name="user" >
                                Usuario <bean:write name="usuarioForm" property="username" />
                                <html:link action="/Logout">
                                    (Cerrar Sesión)
                                </html:link> |
                                <html:link page="/Login.do?method=save">Inicio</html:link> |
                            </logic:present>
                            
                            <html:link href="http://localhost:8080/SiraDEXS1/construccion.html">Contactenos</html:link> |
                            <html:link href="http://localhost:8080/SiraDEXS1/construccion.html">Realizar busquedas públicas</html:link> |
                            <logic:present name="user" >
                                <logic:equal name="user" property="rol" value="DEx">
                                    <html:link href="http://localhost:8080/SiraDEXS1/construccion.html">Realizar consultas avanzadas</html:link> |
                                </logic:equal>
                            </logic:present>
                            <html:link href="http://localhost:8080/SiraDEXS1/construccion.html">Ayuda</html:link>
                        </td>
                        </tr>
                    </table>
                </ul>
            </div>
</header>