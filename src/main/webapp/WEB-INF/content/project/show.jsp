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
                <li>Project Active: <s:property value="project.projectActive" /></li>
                    <s:if test="%{project.studentId != null}">
                    <li>Assigned Student: <s:property value="project.studentId.userName" /></li>
                    </s:if> 
                    <s:else>
                    <li>* No student assigned *</li>
                    </s:else>
                <li>Project Specialization: <s:property value="project.specId.specName" /></li>
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
            <h3><a href="${pageContext.request.contextPath}/project">Back</a></h3>

            <h3><a href="${pageContext.request.contextPath}/project/<s:property value="id" />/edit">Edit</a><i class="fa fa-pencil-square-o"></i></h3>
            <form action="${pageContext.request.contextPath}/project/<s:property value="id" />">
                <input name="_method" type="hidden" value="delete" />
                <button  onclick="return confirm('Are you sure you want to delete?')" 
                         formmethod="post" type="submit" class="btn btn-danger">Delete</button>
            </form>

            <h3>Comments (<s:property value="comments.size" />)</h3>
            <ol>
                <s:iterator value="comments">
                    <li>
                        <ul>
                            <li>Date Commented: <s:date name="top.dateCreated" format="dd-MM-yyyy hh:mma" /></li>
                            <li>Name: <s:property value="top.userId.userName" /></li>
                            <li>Content: <s:property value="top.commentDescription" /></li>
                        </ul>
                    </li>
                    <br/>
                </s:iterator>
            </ol>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
