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
                                value="<s:property value="top.specId" />">
                                <s:property value="top.specName" />
                            </option>
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
                                value="<s:property value="top.userId" />">
                                <s:property value="top.userName" />
                            </option>
                        </s:iterator>
                    </select>
                </div>
                <div class="form-group col-sm-12">
                    Active projects:  
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
                    Project Has Comments?  
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
                    Project Assigned to student?  
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
                    <button type="submit" class="btn btn-primary col-sm-3">Submit</button>
                </div>
            </form>

            <div class="container col-sm-12">
                <p>A total of <b><s:property value="projectList.size" /></b> projects found:</p>
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
                                    <h5 class="col-sm-12">Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                                        Duis dapibus interdum felis, at luctus dui venenatis commodo. 
                                        Pellentesque eleifend eleifend interdum. Nullam blandit tempus lacus, 
                                        eu semper nibh ultricies ultricies. Vestibulum elementum, sapien nec 
                                        vestibulum congue, nulla odio tincidunt massa, posuere pretium magna 
                                        lacus non metus. Sed quis arcu sit amet nulla tempus ornare. Fusce 
                                        facilisis est vitae urna lobortis...</h5>
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
