<%-- 
    Document   : leftMenu
    Created on : 14-nov-2012, 15:05:46
    Author     : SisCon
--%>
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
                            <logic:equal name="user" property="rol" value="DEx">

                                <li><html:link action="/AGestionTiposActividad"> 
                                        Gestionar tipos de actividad 
                                    </html:link></li>

                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Mostrar productividad individual</a></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Mostrar productividad general</a></li>
                                <li><html:link action="/AGestionUsuarios"> 
                                        Gestionar usuarios
                                    </html:link></li><br/>  
                                </logic:equal>

                            <logic:equal name="user" property="rol" value="Prof">
                                <li><html:link action="/AGestionActividad?method=listUser"> 
                                        Gestionar mis actividades
                                    </html:link></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Mostrar productividad individual</a></li>
                            </logic:equal>  

                            <logic:equal name="user" property="rol" value="WM">
                                
                                <li><html:link action="/AGestionTiposActividad"> 
                                        Gestionar tipos de actividad 
                                    </html:link></li>
                                
                                 <li><html:link action="/AGestionActividad?method=listAll"> 
                                        Gestionar actividades
                                    </html:link></li>

                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Consultar log</a></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Gestionar backup</a></li>
                                <li><a href="http://localhost:8080/SiraDEx/construccion.html">Gestionar roles</a></li>
                            </logic:equal> 

                            <logic:equal name="user" property="rol" value="CU">
                                <li><html:link action="/AGestionActividad?method=listUser"> 
                                        Gestionar mis actividades
                                    </html:link></li>
                                </logic:equal>
                            </logic:present>
                    </ul>    
                </div>
            </div>
        </div>
    </div>
</div>