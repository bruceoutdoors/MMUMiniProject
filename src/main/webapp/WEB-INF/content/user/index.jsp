<%-- 
    Document   : edit
    Created on : Jan 21, 2016, 11:04:53 PM
    Author     : Redzrex
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Users</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/layouts/header.jsp" />
            <h1><small>All Users | <a href="${pageContext.request.contextPath}/user/new">Create New User</a></small></h1>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Active</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="users">
                        <tr>
                            <td><b><a href="${pageContext.request.contextPath}/user/<s:property value="top.userId" />">
                                        <s:property value="top.userId" />
                                    </a></b></td>
                            <td><s:property value="top.userName" /></td>
                            <td><s:property value="top.userEmail" /></td>
                            <td><s:property value="top.roleId.roleName" /></td>
                            <td><s:property value="top.userActive" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table> 


            <jsp:include page="/WEB-INF/layouts/footer.jsp" />
        </div> 
    </body>
</html>
