<%-- 
    Document   : leftMenu
    Created on : 14-nov-2012, 15:05:46
    Author     : SisCon
--%>
<%@page import="Clases.Usuario"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sidebar-first" class="sidebar grid-3 pull-10">
    <div class="region region-sidebar-first">
        <div id="block-system-main-menu" class="block block-system block-menu first">
            <div class="inner">
                <div class="content">
                    <ul class="menu">
                        <logic:present name="user">
                            <% Usuario u = (Usuario) request.getSession().getAttribute("user");
                                String r = u.getRol();
                                if (r.equals("WM") || r.equals("profesor") || r.equals("obrero")
                                        || r.equals("estudiante") || r.equals("empleado")) {
                                    r = "";
                                }
                            %>
                            <logic:equal name="user" property="rol" value="<%= r%>"> <%--DEx--%>
                                <li><h1 class="title">Gestionar:</h1></li>
                                <li><html:link action="/AGestionActividades.do?method=listDex">
                                        Validaciones
                                    </html:link></li>
                            <%--    </logic:equal>

                            <logic:equal name="user" property="rol" value="DEx">--%>
                                <li><html:link action="/AGestionTiposActividad">
                                        Tipos de Actividad 
                                    </html:link></li><br>

                                <li><h1 class="title">Mostrar Productividad:</h1></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Individual</a></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">General</a></li>

                            </logic:equal>

                            <logic:equal name="user" property="rol" value="profesor">
                                <li><html:link action="/AGestionActividades?method=listUser"> 
                                        Gestionar Actividades
                                    </html:link></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Mostrar Productividad Individual</a></li>
                            </logic:equal>  

                            <logic:equal name="user" property="rol" value="WM">
                                <li><h1 class="title">Gestionar:</h1></li>
                                <li><html:link action="/AGestionTiposActividad"> 
                                        Tipos de Actividad 
                                    </html:link></li>

                                <li><html:link action="/AGestionActividades?method=listAll"> 
                                        Actividades
                                    </html:link></li>

                                <li><html:link action="/AGestionCatalogos"> 
                                        Catálogos 
                                    </html:link></li>

                                <li><html:link action="/AGestionUsuarios"> 
                                        Usuarios
                                    </html:link></li>

                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Backup</a></li><br>
                                <li><h1 class="title"><a href="http://localhost:8080/SiraDEx/construccion.html">Consultar Log</a></h1></li>

                            </logic:equal> 

                            <logic:equal name="user" property="rol" value="obrero">
                                <li><html:link action="/AGestionActividades?method=listUser"> 
                                        Gestionar Actividades
                                    </html:link></li>
                                </logic:equal>

                            <logic:equal name="user" property="rol" value="estudiante">
                                <li><html:link action="/AGestionActividades?method=listUser"> 
                                        Gestionar Actividades
                                    </html:link></li>
                                </logic:equal>

                            <logic:equal name="user" property="rol" value="empleado">
                                <li><html:link action="/AGestionActividades?method=listUser"> 
                                        Gestionar Actividades
                                    </html:link></li>
                                </logic:equal>
                            </logic:present>
                    </ul>    
                </div>
            </div>
        </div>
    </div>
</div>