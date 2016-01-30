<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="app.model.User"%>
<%@page import="core.LoginManager"%>
<center>
    <img src="banner.png" alt="page banner" style="width:50%;height:50%"></img>
    <%
        String welcomeMessage = "";
        User u = LoginManager.getCurrentUser();
        if (u != null) {
            welcomeMessage = "Hey there, " + u.getUserName();
        } else {
            welcomeMessage = "Hello stranger.";
        }
    %>
    
    
    <h5><%= welcomeMessage%></h5>
    
    <% if (u != null) { %>
    <a  onclick="return confirm('Are you sure you want to log out?')" href="${pageContext.request.contextPath}/user/logout">Logout</a>
    <% } else { %>
    <a href="${pageContext.request.contextPath}/user/login" class="btn btn-primary"><span class="fa fa-sign-in"></span> Login</a>
    <% }%>
    <a href="${pageContext.request.contextPath}/project" class="btn btn-success"><span class="fa fa-database"></span> Projects</a>

    <%-- Show alerts (fill properties "alertMsg" and optional "alertType"), if exists --%>
    <s:if test="%{alertMsg != null}" >
        <div class="alert alert-<s:property value="alertType" default="info" />">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <s:property escapeHtml="false" value="alertMsg" />
        </div>
    </s:if>
</center>
<br/><br/>


