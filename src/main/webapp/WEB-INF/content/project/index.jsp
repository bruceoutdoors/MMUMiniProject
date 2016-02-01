<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Projects</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <style type="text/css">
            .noMargin { margin-top: 0px !important; margin-btm: 0px !important; }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h3>
                <s:if test="%{user.isLecturer()}">
                    Mange Projects (<s:property value="user.userName" />)
                </s:if>
                <s:else>
                    [Admin] Manage Projects 
                </s:else>
                | <a href="${pageContext.request.contextPath}/project/new">Create New Project</a></h3>
            <br/>

            <div class="panel panel-default">
                <div class="panel-body">
                    <a data-toggle="collapse" href="#Filters"><h4><b>Filters</b></h4></a>
                    <div id="Filters" class="panel-collapse collapse">
                        <br/>
                        <form role="form">
                            <div class="form-group col-sm-6">
                                <label class="control-label">Search by Project Title</label>
                                <div>
                                    <input id="search-title" name="title" class="form-control" type="text" value="<s:property value="#parameters.title" />">
                                </div>
                            </div>
                            <div class="form-group col-sm-12">
                                <br/>
                                <button type="submit" class="btn btn-primary col-sm-3">Submit</button> &nbsp;
                                <a href="${pageContext.request.contextPath}/project" class="btn btn-warning">Clear Filter</a> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container col-sm-12">
                <p>A total of <b><s:property value="projectList.size" /></b> projects found:</p>
                <br/>
                <table class="table table-striped">
                    <thead>
                        <h5 class="col-sm-2"><b>Title</b></h5>
                        <h5 class="col-sm-2"><b>Specialisation</b></h5>
                        <s:if test="%{user.isAdmin()}">
                        <h5 class="col-sm-1"><b>Lecturer</b></h5>
                        </s:if>
                        <h5 class="col-sm-1"><b>Student</b></h5>
                        <h5 class="col-sm-1"><b>Active?</b></h5>
                        <h5 class="col-sm-1"><b>Comment(s)</b></h5>
                        <div class="col-sm-3">
                            <h5 class="col-sm-6"><b>Start Date</b></h5>
                            <h5 class="col-sm-6"><b>Status</b></h5>
                        </div>
                        <h5 class="col-sm-1"><b>Option</b></h5>
                    </thead>
                    <tbody>
                        <s:iterator value="projectList">
                            <tr>
                                <td>
                                    <div class="row">
                                        <h5 class="col-sm-2"><s:property value="top.projectTitle" /></h5>
                                        <h5 class="col-sm-2"><s:property value="top.specId.specName" /></h5>
                                        <s:if test="%{user.isAdmin()}">
                                        <h5 class="col-sm-1"><s:property value="top.lecturerId.userName" /></h5>
                                        </s:if>
                                        <h5 class="col-sm-1">
                                            <s:if test="%{top.studentId != null}">
                                                <s:property value="top.studentId.userName" />
                                            </s:if> 
                                            <s:else>
                                                None
                                            </s:else>
                                        </h5>
                                        <h5 class="col-sm-1"><s:property value="top.projectActive" /></h5>
                                        <h5 class="col-sm-1"><s:property value="top.commentList.size" /></h5>
                                        <div class="col-sm-3">
                                            <h5 class="col-sm-6"><s:date name="top.startDate" format="dd-MM-yyyy" /></h5>
                                            <h5 class="col-sm-6">[<s:property value="top.status" />]</h5>
                                        </div>
                                            <h5 class="col-sm-1">
                                                <a href="${pageContext.request.contextPath}/project/<s:property value="top.projectId" />">Edit</a> 
                                                <a target="_blank" href="${pageContext.request.contextPath}/viewboard/<s:property value="top.projectId" />">View</a>
                                            </h5>
                                    </div>
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
