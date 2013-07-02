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

<div id="sidebar-first" class="sidebar grid-3 pull-15" style="overflow-y: auto;max-height: 420px;">
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

                                <li><html:link action="/GestionActividades?method=listDex">
                                        Validaciones
                                    </html:link></li>

                                <li><html:link action="/GestionTiposActividad?method=listActive">
                                        Tipos de Actividad
                                    </html:link></li>

                            </logic:equal>


                            <logic:equal name="user" property="rol" value="WM">
                                <li><h1 class="title">Gestionar:</h1></li>
                                <li><html:link action="/GestionTiposActividad?method=listActive"> 
                                        Tipos de Actividad 
                                    </html:link></li>

                                <li><html:link action="/GestionActividades?method=listAll"> 
                                        Actividades
                                    </html:link></li>

                                <li><html:link action="/GestionActividades?method=listDex">
                                        Validaciones
                                    </html:link></li>

                                <li><html:link action="/GestionCatalogos"> 
                                        Catálogos 
                                    </html:link></li>

                                <li><html:link action="/GestionUsuarios"> 
                                        Usuarios
                                    </html:link></li>

                                <li><html:link action="/GestionBackups?method=page"> 
                                        Backups 
                                    </html:link></li>
                                <br>

                                <li><h1 class="title">
                                    <html:link action="/ConsultarLog">
                                        Consultar Log
                                    </html:link></h1></li>

                                <li><h1 class="title">
                                    <html:link action="/Entrar?method=page"> 
                                        Entrar como otro Tipo de Usuario 
                                    </html:link>
                                </h1></li>

                            </logic:equal> 


                            <logic:equal name="user" property="rol" value="profesor">
                                <%--opciones para el menu de un empleado--%>
                            </logic:equal> 

                            <logic:equal name="user" property="rol" value="obrero">
                                <%--opciones para el menu de un empleado--%>
                            </logic:equal>

                            <logic:equal name="user" property="rol" value="estudiante">
                                <%--opciones para el menu de un empleado--%>
                            </logic:equal>

                            <logic:equal name="user" property="rol" value="empleado">
                                <%--opciones para el menu de un empleado--%>
                            </logic:equal>

                        </logic:present>
                    </ul>    
                </div>
            </div>
        </div>
    </div>
</div>