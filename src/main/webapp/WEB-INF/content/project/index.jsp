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
            <form role="form">
                <div class="form-group col-sm-6">
                    <label class="control-label">Search by Project Title</label>
                    <div>
                        <input id="search-title" name="title" class="form-control" type="text" value="<s:property value="#parameters.title" />">
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <label for="sel1">Search by Specialization:</label>
                    <select class="form-control" name="spec">
                        <option value="" selected> -- none selected -- </option>
                        <s:iterator value="specs">
                            <option 
                                <s:if test="%{#parameters.spec[0] == top.specId}">
                                    selected 
                                </s:if>
                                value="<s:property value="top.specId" />"><s:property value="top.specName" /></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="form-group col-sm-12">
                    <label for="sel1">Search by Lecturer:</label>
                    <select class="form-control" name="lecturer">
                        <option value="" selected> -- none selected -- </option>
                        <s:iterator value="lecturers">
                            <option 
                                <s:if test="%{#parameters.lecturer[0] == top.userId}">
                                    selected 
                                </s:if>
                                value="<s:property value="top.userId" />"><s:property value="top.userName" /></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="form-group col-sm-12">
                    <button type="submit" class="btn btn-primary col-sm-3">Submit</button>
                </div>
            </form>

            <div class="form-group col-sm-12">
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
                                <li>Comment count: <s:property value="top.commentCollection.size" /></li>
                                <li>Status: <s:property value="top.projectStatus" /></li>
                                <li>Specialization: <s:property value="top.specId.specName" /></li>
                                <li>Lecturer: <s:property value="top.lecturerId.userName" /></li>
                            </ul>
                        </li>
                    </s:iterator>
                </ol>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
