
<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<header>
    <div id="block-system-main-menu" class="block block-system block-menu first">          
        <ul class="menu">
            <table bgcolor= "#E2E4FF">
                <tr>         
                <td>
                    <logic:present name="user">
                        <% Usuario u = (Usuario) request.getSession().getAttribute("user");
                            String r = u.getRol();
                            if (r.equals("WM") || r.equals("profesor") || r.equals("obrero")
                                    || r.equals("estudiante") || r.equals("empleado")) {
                                r = "";
                            }
                        %>

                        <bean:write name="user" property="nombres" /> <bean:write name="user" property="apellidos" />

                        <html:link action="/Logout">
                            (Cerrar Sesión)
                        </html:link> |
                        <html:link action="/VerPerfilUsuario?method=page">Ver Perfil</html:link> |


                        <logic:equal name="user" property="rol" value="<%= r%>"> <%--DEx--%>
                            <html:link action="/GestionActividades?method=listDex">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="profesor">
                            <html:link action="/GestionActividades?method=listUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="estudiante">
                            <html:link action="/GestionActividades?method=listUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="obrero">
                            <html:link action="/GestionActividades?method=listUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="WM">
                            <html:link action="/GestionActividades?method=listAll">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="<%= r%>">
                            <html:link action="/BusquedaAvanzada?method=page" title="Busquedas avanzadas">
                                Busquedas
                            </html:link> |
                        </logic:equal>
                        <logic:equal name="user" property="rol" value="WM">
                            <html:link action="/BusquedaAvanzada?method=page" title="Busquedas avanzadas">
                                Busquedas
                            </html:link> |
                        </logic:equal>

                    </logic:present>

                    <logic:notPresent name="user">
                        <html:link action="/Welcome">Inicio</html:link> |

                        <html:link action="/BusquedaPublica?method=page" title="Busquedas públicas">
                            Busquedas
                        </html:link> |
                    </logic:notPresent>

                    <a title="No está disponible" style="cursor: pointer; text-decoration:underline">Contactenos</a> |

                    <a title="No está disponible" style="cursor: pointer; text-decoration:underline">Ayuda</a>
                </td>
                </tr>
            </table>
        </ul>
    </div>
</header>
