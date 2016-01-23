<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="app.model.User"%>
<%@page import="core.LoginManager"%>
<center>
    <%
        String welcomeMessage = "";
        User u = LoginManager.getCurrentUser();
        if (u != null) {
            welcomeMessage = "Hey there, " + u.getUserName();
        } else {
            welcomeMessage = "Hello stranger.";
        }
    %>

    <h2>I am the header. <%= welcomeMessage%></h2>
    
    <% if (u != null) { %>
    <a href="<s:url namespace="/user" action="logout" />">Logout</a>
    <% } else { %>
    <a href="<s:url namespace="/user" action="login" />">Login</a>
    <% }%>

    <%-- Show alerts (fill properties "alertMsg" and optional "alertType"), if exists --%>
    <s:if test="%{alertMsg != null}" >
        <div class="alert alert-<s:property value="alertType" default="info" />">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <s:property escapeHtml="false" value="alertMsg" />
        </div>
    </s:if>
</center>
<br/><br/>


