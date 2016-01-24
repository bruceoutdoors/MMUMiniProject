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
            <h1><s:property value="project.projectTitle" /></h1>
            <ul>
                <li>Id: <s:property value="project.projectId" /></li>
                <li>Grade: <s:property value="project.projectGrade" /></li>
                <li>Start date: <s:date name="project.startDate" format="dd-MM-yyyy hh:mma" /></li>
                <li>Due date: <s:date name="project.dueDate"  nice="true"/></li>
                <li>Submission Date: <s:date name="project.subDate" format="dd-MM-yyyy hh:mma" /></li>
                <li>Lecturer: <s:property value="project.lecturerId.userName" /></li>
                    <s:if test="%{project.projectFile != null}">
                    <li>Project PDF File: <a href="<s:property value="project.projectFile"  />" target="_blank" 
                                             title="NOTE: If you running this locally, instead of clicking this link directly you need to copy the link address and paste it in another tab. This is because most modern browsers prevent users from accessing files locally for security reasons.">
                            Download Project File
                        </a></li>
                    </s:if>
                    <s:else>
                    <li>No Project PDF File had been uploaded</li>
                </s:else>
                <li>Description:  
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <s:property  escapeHtml="false" value="project.projectDescription" />
                        </div>
                    </div>

                </li>
            </ul>
            <button onclick="window.history.back();">Go Back</button>
            <h3><a href="${pageContext.request.contextPath}/project/<s:property value="id" />/edit">Edit</a><i class="fa fa-pencil-square-o"></i></h3>
            <form action="${pageContext.request.contextPath}/project/<s:property value="id" />">
                <input name="_method" type="hidden" value="delete" />
                <button  onclick="return confirm('Are you sure you want to delete?')" 
                         formmethod="post" type="submit" class="btn btn-danger">Delete</button>
            </form>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
