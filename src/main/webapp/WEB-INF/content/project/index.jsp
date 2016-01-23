<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Projects</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h1>Projects List</h1><h3><a href="${pageContext.request.contextPath}/project/new">New</a></h3>
            <ol>
                <s:iterator value="projectList">
                    <li>
                        <ul>
                            <li>Id: <s:property value="top.projectId" /></li>
                            <li><a href="${pageContext.request.contextPath}/project/<s:property value="top.projectId" />">Title: <s:property value="top.projectTitle" /></a></li>
                            <li>Grade: <s:property value="top.projectGrade" /></li>
                            <li>Start date: <s:date name="top.startDate" format="dd-MM-yyyy hh:mma" /></li>
                            <li>Due date: <s:date name="top.dueDate"  nice="true"/></li>
                            <li>Submission Date: <s:date name="top.subDate" format="dd-MM-yyyy hh:mma" /></li>
                            <li>Lecturer: <s:property value="top.lecturerId.userName" /></li>
                        </ul>
                    </li>
                </s:iterator>
            </ol>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
