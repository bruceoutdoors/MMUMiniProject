<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="app.model.User"%>
<%@page import="core.LoginManager"%>
<%
    String welcomeMessage = "";
    User u = LoginManager.getCurrentUser();
    if (u != null) {
        welcomeMessage = "Hey there, " + u.getUserName();
    } else {
        welcomeMessage = "Hello stranger.";
    }
%>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">MMUMPS</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp">Home</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Manage <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="manageacc.jsp">User Accounts</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                    <li><a href="profile.jsp">Profile</a></li>
                    <li>Profile</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><% if (u != null) { %>
                        <a  onclick="return confirm('Are you sure you want to log out?')" href="${pageContext.request.contextPath}/user/logout">Logout</a>
                        <% } else { %>
                        <a href="${pageContext.request.contextPath}/user/login">Login</a>
                        <% }%></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</header>
<center>

    <%-- Show alerts (fill properties "alertMsg" and optional "alertType"), if exists --%>
    <s:if test="%{alertMsg != null}" >
        <div class="alert alert-<s:property value="alertType" default="info" />">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <s:property escapeHtml="false" value="alertMsg" />
        </div>
    </s:if>
</center>
<br/><br/>


