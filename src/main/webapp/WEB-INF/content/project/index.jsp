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
                    All Projects by <s:property value="user.userName" />
                </s:if>
                <s:else>
                    Projects List 
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
                                <button type="submit" class="btn btn-primary col-sm-3">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container col-sm-12">
                <p>A total of <b><s:property value="projectList.size" /></b> projects found:</p>
                <br/>
                <table class="table table-striped">
                    <tbody>
                        <s:iterator value="projectList">
                            <tr>
                                <td>
                                    <div class="row noMargin">
                                        <h3 class="col-sm-10 noMargin"><a href="${pageContext.request.contextPath}/project/<s:property value="top.projectId" />"><s:property value="top.projectTitle" /></a></h3>
                                        <h5 class="col-sm-2 noMargin text-right"><b>[<s:property value="top.status" />]</b></h5>
                                    </div>
                                    <div class="row noMargin">
                                        <h5 class="col-sm-12 noMargin"><small>Specialization: <s:property value="top.specId.specName" /></small></h6>
                                    </div>
                                    <br/>
                                    <div class="row noMargin">
                                        <h4 class="col-sm-12 noMargin"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   <small>by <s:property value="top.lecturerId.userName" />, </small>
                                            <s:if test="%{top.studentId != null}">
                                                <small>assigned to <s:property value="top.studentId.userName" /></small>
                                            </s:if> 
                                            <s:else>
                                                <small>no student assigned</small>
                                            </s:else>
                                            &nbsp; &nbsp; &nbsp; &nbsp; <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>   <small><s:date name="top.startDate" format="dd-MM-yyyy hh:mma" /></small> 
                                            &nbsp; <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> &nbsp; <small><s:date name="top.dueDate"  format="dd-MM-yyyy hh:mma" /></small></h4>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <h5 class="col-sm-12"><s:property value="top.shortDescription" /></h5>
                                    </div>
                                    <br/>
                                    <div class ="row">
                                        <h4 class="col-sm-12"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>   <small><s:property value="top.commentList.size" /> comment(s)</small></h4>
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
