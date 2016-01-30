<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Assigned Projects</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <s:if test="%{user.isStudent()}">
                <h2>Assigned Project</h2>
                <s:if test="%{project == null}">
                    <p>You currently have no projects assigned to you.</p> 
                </s:if><s:else>
                    <div class="panel panel-default"><div class="panel-body">
                    <div class="row noMargin">
                        <h3 class="col-sm-10 noMargin"><a target="_blank" href="${pageContext.request.contextPath}/viewboard/<s:property value="project.projectId" />"><s:property value="project.projectTitle" /></a></h3>
                        <h5 class="col-sm-2 noMargin text-right"><b>[<s:property value="project.status" />]</b></h5>
                    </div>
                    <div class="row noMargin">
                        <h5 class="col-sm-12 noMargin"><small>Specialization: <s:property value="project.specId.specName" /></small></h6>
                    </div>
                    <br/>
                    <div class="row noMargin">
                        <h4 class="col-sm-12 noMargin"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   <small>by <s:property value="project.lecturerId.userName" />, </small>
                            <s:if test="%{project.studentId != null}">
                                <small>assigned to <s:property value="project.studentId.userName" /></small>
                            </s:if> 
                            <s:else>
                                <small>no student assigned</small>
                            </s:else>
                            &nbsp; &nbsp; &nbsp; &nbsp; <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>   <small><s:date name="project.startDate" format="dd-MM-yyyy hh:mma" /></small> 
                            &nbsp; <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> &nbsp; <small><s:date name="project.dueDate"  format="dd-MM-yyyy hh:mma" /></small></h4>
                    </div>
                    <br/>
                    <div class="row">
                        <h5 class="col-sm-12"><s:property value="project.shortDescription" /></h5>
                    </div>
                    <br/>
                    <div class ="row">
                        <h4 class="col-sm-12"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>   <small><s:property value="project.commentList.size" /> comment(s)</small></h4>
                    </div>
                        </div></div>
                </s:else>
                <br/>
            </s:if>
            <h2>Past Projects</h2>
            <p>You completed <b><s:property value="pastProjects.size" /></b> projects:</p>                                                                                      
            <div class="table-responsive">          
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Project Title</th>
                            <th>Lecturer</th>
                            <th>Submission Date</th>
                            <th>Grade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="pastProjects">
                            <tr>
                                <td><s:property value="top.projectId" /></td>
                                <td><a href="${pageContext.request.contextPath}/viewboard/<s:property value="top.projectId" />"><s:property value="top.projectTitle" /></a></td>
                                <td><s:property value="top.lecturerId.userName" /></td>
                                <td><s:date name="top.subDate"  format="dd-MM-yyyy hh:mma" /></td>
                                <td><s:if test="%{top.projectGrade == null}">
                                        - not graded -
                                    </s:if><s:else>
                                        <s:property value="top.projectGrade" />
                                    </s:else>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
