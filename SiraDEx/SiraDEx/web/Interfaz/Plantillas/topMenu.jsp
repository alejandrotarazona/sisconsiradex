
<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<header>
    <div id="block-system-main-menu" class="block block-system block-menu first">          
        <ul class="menu">
            <table bgcolor= "#E0F2F7">
                <tr>
                <td>

                    <logic:present name="user">

                        <bean:write name="user" property="nombres" /> <bean:write name="user" property="apellidos" />
                        
                        <html:link action="/Logout">
                            (Cerrar Sesión)
                        </html:link> |
                        <html:link action="/VerPerfilUsuario">Ver Perfil</html:link> |
                        <html:link page="/Login.do?method=save">Inicio</html:link> |
                    </logic:present>

                    <html:link action="/BusquedaPublica?method=page">Realizar busquedas públicas</html:link> |


                    <logic:present name="user" >

                        <% Usuario u = (Usuario) request.getSession().getAttribute("user");
                            String r = u.getRol();
                            if (r.equals("profesor") || r.equals("obrero")
                                    || r.equals("estudiante") || r.equals("empleado")) {
                                r = "";
                            }
                        %>
                        <logic:equal name="user" property="rol" value="<%= r%>">
                            <html:link action="/BusquedaAvanzada?method=page">Realizar consultas avanzadas</html:link> |
                        </logic:equal>

                    </logic:present>
                            
                    <a title="No está disponible" style="cursor: pointer; text-decoration:underline">Contactenos</a> |

                    <a title="No está disponible" style="cursor: pointer; text-decoration:underline">Ayuda</a>
                </td>
                </tr>
            </table>
        </ul>
    </div>
</header>
