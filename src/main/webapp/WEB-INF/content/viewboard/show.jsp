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
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h4>Project ID: <s:property value="project.projectId" /></h4>
                </div>
                <div class="panel-footer">
                    <h2><s:property value="project.projectTitle" /></h2>
                </div>
            </div>
            <div class="well">
                <p class="well"><strong>Project Active: </strong> <s:property value="project.projectActive" /></p>
                <s:if test="%{project.studentId != null}">
                    <p class="well"><strong>Assigned Student: </strong> <s:property value="project.studentId.userName" /></p>
                </s:if> 
                <s:else>
                    <p class="well"><strong>* No student assigned *</strong> </p>
                </s:else>
                <p class="well"><strong>Project Specialization: </strong> <s:property value="project.specId.specName" /></p>
                <p class="well"><strong>Grade: </strong> <s:property value="project.projectGrade" /></p>
                <p class="well"><strong>Start date: </strong> <s:date name="project.startDate" format="dd-MM-yyyy hh:mma" /></p>
                <p class="well"><strong>Due date: </strong> <s:date name="project.dueDate"  nice="true"/></p>
                <p class="well"><strong>Submission Date: </strong> <s:date name="project.subDate" format="dd-MM-yyyy hh:mma" /></p>
                <p class="well"><strong>Lecturer: </strong> <s:property value="project.lecturerId.userName" /></p>
                <s:if test="%{project.projectFile != null}">
                    <p class="well"><strong>Project PDF File: </strong> <a href="<s:property value="project.projectFile"  />" target="_blank" 
                                                                           title="NOTE: If you running this locally, instead of clicking this link directly you need to copy the link address and paste it in another tab. This is because most modern browsers prevent users from accessing files locally for security reasons.">
                            Download Project File
                        </a></p>
                    </s:if>
                    <s:else>
                    <p  class="well"><strong>No Project PDF File had been uploaded</strong> </p>
                </s:else>
                <br>
                <p><strong>Description:</strong>  
                <div class="panel panel-default">
                    <div class="panel-body">
                        <s:property  escapeHtml="false" value="project.projectDescription" />
                    </div>
                </div>

                </p>
            </div>
            <h3><a href="${pageContext.request.contextPath}/project"><p style="position: fixed; bottom: 20px; right: 20px;"><span class="fa fa-chevron-left"></span> Back</p></a></h3>



            <h3>Comments (<s:property value="comments.size" />)</h3>
            <h4>Add Comment (<s:property value="user.userName" />):</h4>
            <div class="form-group" action="${pageContext.request.contextPath}/comment">
                <textarea name="comment.commentDescription" rows="4" cols="100"></textarea>
                <input name="project.projectId" type="hidden" value="<s:property value="project.projectId" />" />
                <br><button formmethod="post" type="submit" class="btn btn-default" data-toggle="tooltip" title="Click to post comment!">Post comment</button>
            </div>
                <br>
            <ol>
                <s:iterator value="comments">
                    <div class="table-responsive">
                    <table class="table table-hover">
                        <tr>
                            <td rowspan="3" style="width:90px" class="warning"><img src="${pageContext.request.contextPath}/images/user.png" class="img-responsive" style="width:80px;height:80px"></td>
                            <td class="danger"><i class="fa fa-user"></i> - <s:property value="top.userId.userName" /> (<s:property value="top.userId.roleId.roleName" />)</td>
                        </tr>
                        <tr class="success">
                            <td><i class="fa fa-comment-o"></i> - <s:property value="top.commentDescription" /></td>
                        </tr>
                        <tr class="info">
                            <td><i class="fa fa-calendar-times-o"></i> - <s:date name="top.dateCreated" format="dd-MM-yyyy hh:mma" /></td>
                        </tr>
                    </table>
                    </div>
                </s:iterator>
            </ol>
            <br/>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
