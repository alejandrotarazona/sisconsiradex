<%-- 
    Document   : templateAdvancedFind
    Created on : 20/05/2013, 09:23:25 PM
    Author     : SisCon
--%>


<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html version="HTML+RDFa 1.0" lang="es" dir="ltr" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:content="http://purl.org/rss/1.0/modules/content/"
      xmlns:dc="http://purl.org/dc/terms/"
      xmlns:foaf="http://xmlns.com/foaf/0.1/"
      xmlns:og="http://ogp.me/ns#"
      xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
      xmlns:sioc="http://rdfs.org/sioc/ns#"
      xmlns:sioct="http://rdfs.org/sioc/types#"
      xmlns:skos="http://www.w3.org/2004/02/skos/core#"
      xmlns:xsd="http://www.w3.org/2001/XMLSchema#">

    <tiles:insert attribute="head"/>
    <body class="html front not-logged-in two-sidebars page-node page-node- page-node-1 node-type-page" >
        <div id="container" class="container-16">
                <tiles:insert attribute="header"/>
                <tiles:insert attribute="topMenu"/>
                <div id="main" class="section section-main clearfix" role="main">
                    <div id="content" class="column grid-11 push-5">
                        <div class="inner">
                            <div id="main-content" class="clearfix">
                                <div class="region region-content">
                                    <div class="content clearfix">
                                        <div class="field field-name-body field-type-text-with-summary field-label-hidden">
                                            <div class="field-items">
                                                <div class="field-item even" property="content:encoded">
                                                    <logic:present name="user">
                                                        <tiles:insert attribute="body" />
                                                    </logic:present>
                                                    <logic:notPresent name="user">
                                                        <br></br>
                                                        <span class ="warning">Su sesión ha expirado, para regresar a la página principal , por favor
                                                            <html:link action="/Welcome">haga click aquí</html:link>
                                                        </span>
                                                    </logic:notPresent>
                                                    <br>
                                                    </br>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <tiles:insert attribute="leftMenu" />
                </div>
                <tiles:insert attribute="footer" />
            </div>
    </body>
</html>