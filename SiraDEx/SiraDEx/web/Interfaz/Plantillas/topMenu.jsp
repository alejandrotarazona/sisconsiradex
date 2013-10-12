
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

                        <bean:write name="user" property="nombres" /> <bean:write name="user" property="apellidos" />

                        <html:link action="/Logout">
                            (Cerrar Sesi�n)
                        </html:link> |
                        <html:link action="/VerPerfilUsuario?method=prePage">Ver Perfil</html:link> |


                        <logic:equal name="permiso" value="dex">
                            <html:link action="/GestionActividades?method=preListDex">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="profesor">
                            <html:link action="/GestionActividades?method=preListUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="estudiante">
                            <html:link action="/GestionActividades?method=preListUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="obrero">
                            <html:link action="/GestionActividades?method=preListUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="empleado">
                            <html:link action="/GestionActividades?method=preListUser">Inicio</html:link> |
                        </logic:equal>

                        <logic:equal name="user" property="rol" value="WM">
                            <html:link action="/GestionActividades?method=preListAll">Inicio</html:link> |
                        </logic:equal>

                        <logic:present name="permiso">
                            <html:link action="/BusquedaAvanzada?method=page" title="B�squedas avanzadas">
                                B�squedas
                            </html:link>
                        </logic:present>
                        <logic:equal name="user" property="rol" value="estudiante">
                            <html:link action="/BusquedaPublica?method=page" title="B�squedas p�blicas">
                                B�squedas
                            </html:link>
                        </logic:equal>
                        <logic:equal name="user" property="rol" value="obrero">
                            <html:link action="/BusquedaPublica?method=page" title="B�squedas p�blicas">
                                B�squedas
                            </html:link>
                        </logic:equal>
                        <logic:equal name="user" property="rol" value="profesor">
                            <html:link action="/BusquedaPublica?method=page" title="B�squedas p�blicas">
                                B�squedas
                            </html:link>
                        </logic:equal>
                        <logic:equal name="user" property="rol" value="empleado">
                            <html:link action="/BusquedaPublica?method=page" title="B�squedas p�blicas">
                                B�squedas
                            </html:link>
                        </logic:equal>
                    </logic:present>

                    <logic:notPresent name="user">
                        <html:link action="/Welcome">Inicio</html:link> |

                        <html:link action="/BusquedaPublica?method=page" title="B�squedas p�blicas">
                            B�squedas
                        </html:link> 
                    </logic:notPresent>
                    <!-- |
                                        <a title="No est� disponible" style="cursor: pointer; text-decoration:underline">Cont�ctenos</a> |
                    
                                        <a title="No est� disponible" style="cursor: pointer; text-decoration:underline">Ayuda</a>
                    -->    
                </td>
                </tr>
            </table>
        </ul>
    </div>
</header>
