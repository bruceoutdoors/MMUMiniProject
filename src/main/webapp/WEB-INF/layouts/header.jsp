<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="app.model.User"%>
<%@page import="core.LoginManager"%>
<center>
    <%
        String welcomeMessage = "";
        User u = LoginManager.getCurrentUser();
        if (u != null) {
            welcomeMessage = "Hey there, " + u.getUserName() + ". You role is " + u.getRoleId().getRoleName();
        } else {
            welcomeMessage = "Hello stranger.";
        }
    %>
    
    
    <h5><%= welcomeMessage%></h5>
    
    <% if (u != null) { %>
    <a href="${pageContext.request.contextPath}/account/edit">Edit Account</a> || 
    <a href="${pageContext.request.contextPath}/user">Edit Users</a> || 
    <a href="${pageContext.request.contextPath}/project" class="btn btn-success"><span class="fa fa-database"></span> Projects</a> || 
    <a onclick="return confirm('Are you sure you want to log out?')" href="${pageContext.request.contextPath}/account/logout">Logout</a>
    <% } else { %>
    <a href="${pageContext.request.contextPath}/account/login" class="btn btn-primary"><span class="fa fa-sign-in"></span> Login</a>
    || <a href="${pageContext.request.contextPath}/user/new">create user</a>
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


