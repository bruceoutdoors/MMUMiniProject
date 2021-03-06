<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <title>Reports</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h3>Project Report</h3>
            <div class="panel panel-default">
                <div class="panel-body">
                    <a data-toggle="collapse" href="#Filters"><h4><b>View Reports By</b></h4></a>
                    <div id="Filters" class="panel-collapse collapse">
                        <br/>
                        <form role="form">
                            <div class="form-group col-sm-12">
                                <label for="sel1">Search by Specialization:</label>
                                <select class="form-control" name="spec">
                                    <option value="" selected> -- none selected -- </option>
                                    <s:iterator value="specs">
                                        <option 
                                            <s:if test="%{#parameters.spec[0] == top.specId}">
                                                selected 
                                            </s:if>
                                            value="<s:property value="top.specId" />">
                                            <s:property value="top.specName" />
                                        </option>
                                    </s:iterator>
                                </select>
                            </div>
                            <s:if test="%{!user.isLecturer()}">
                                <div class="form-group col-sm-12">
                                    <label for="sel1">Search by Lecturer:</label>
                                    <select class="form-control" name="lecturer">
                                        <option value="" selected> -- none selected -- </option>
                                        <s:iterator value="lecturers">
                                            <option 
                                                <s:if test="%{#parameters.lecturer[0] == top.userId}">
                                                    selected 
                                                </s:if>
                                                value="<s:property value="top.userId" />">
                                                <s:property value="top.userName" />
                                            </option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </s:if>
                            <div class="form-group col-sm-12">
                                <label for="sel1">Active projects:  </label>
                                <select class="form-control" name="active">
                                    <option value="" selected>Both Active and Inactive Projects </option>
                                    <option value="yes"
                                            <s:if test="%{#parameters.active[0] == 'yes'}">
                                                selected 
                                            </s:if> >Active Projects Only</option> 
                                    <option value="no"<s:if test="%{#parameters.active[0] == 'no'}">
                                            selected 
                                        </s:if> >Inactive Projects Only</option>
                                </select>

                            </div>
                            <div class="form-group col-sm-12">
                                <label for="sel1">Project Has Comments?  </label>
                                <select class="form-control" name="cmnts">
                                    <option value="" selected>Not important</option>
                                    <option value="yes"
                                            <s:if test="%{#parameters.cmnts[0] == 'yes'}">
                                                selected 
                                            </s:if> >Project with comments</option> 
                                    <option value="no"<s:if test="%{#parameters.cmnts[0] == 'no'}">
                                            selected 
                                        </s:if> >Project with no comments</option>
                                </select>

                            </div>
                            <div class="form-group col-sm-12">
                                <label for="sel1">Project Assigned to student?  </label>
                                <select class="form-control" name="assigned">
                                    <option value="" selected>Not important</option>
                                    <option value="yes"
                                            <s:if test="%{#parameters.assigned[0] == 'yes'}">
                                                selected 
                                            </s:if> >Assigned</option> 
                                    <option value="no"<s:if test="%{#parameters.assigned[0] == 'no'}">
                                            selected 
                                        </s:if> >Unassigned</option>
                                </select>

                            </div>
                            <div class="form-group col-sm-12">
                                <label for="sel1">Project Completed?  </label>
                                <select class="form-control" name="completed">
                                    <option value="" selected>Not important</option>
                                    <option value="yes"
                                            <s:if test="%{#parameters.completed[0] == 'yes'}">
                                                selected 
                                            </s:if> >Completed</option> 
                                    <option value="no"<s:if test="%{#parameters.completed[0] == 'no'}">
                                            selected 
                                        </s:if> >Not completed</option>
                                </select>
                            </div>
                            <div class="form-group col-sm-12">
                                <br/>
                                <button type="submit" class="btn btn-primary col-sm-3">Submit</button> &nbsp;
                                <a href="${pageContext.request.contextPath}/report" class="btn btn-warning">Clear Filter</a> 
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
                        <h5 class="col-sm-3"><b>Title</b></h5>
                        <h5 class="col-sm-2"><b>Specialisation</b></h5>
                        <h5 class="col-sm-1"><b>Lecturer</b></h5>
                        <h5 class="col-sm-1"><b>Student</b></h5>
                        <h5 class="col-sm-1"><b>Active?</b></h5>
                        <h5 class="col-sm-1"><b>Comment(s)</b></h5>
                        <div class="col-sm-3">
                            <h5 class="col-sm-6"><b>Start Date</b></h5>
                            <h5 class="col-sm-6"><b>Status</b></h5>
                        </div>
                    </thead>
                    <tbody>
                        <s:iterator value="projectList">
                            <tr>
                                <td>
                                    <div class="row">
                                        <h5 class="col-sm-3"><a target="_blank" href="${pageContext.request.contextPath}/viewboard/<s:property value="top.projectId" />"><s:property value="top.projectTitle" /></a></h5>
                                        <h5 class="col-sm-2"><s:property value="top.specId.specName" /></h5>
                                        <h5 class="col-sm-1"><s:property value="top.lecturerId.userName" /></h5>
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
