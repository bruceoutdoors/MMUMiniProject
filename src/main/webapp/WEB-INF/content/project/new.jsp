<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Project</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <script src="//cdn.ckeditor.com/4.5.6/standard/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h2>Edit Project</h2>
            <form role="form" action="${pageContext.request.contextPath}/project"  enctype="multipart/form-data"/>
            <div class="form-group col-sm-12">
                <label class="control-label">Project Title</label>
                <div>
                    <input name="project.projectTitle" class="form-control" 
                           type="text" required>
                </div>
            </div>
            <div class="form-group col-sm-12">
                <label for="sel1">Select Specialization:</label>
                <select class="form-control" name="project.spec">
                    <s:iterator value="specs">
                        <option value="<s:property value="top.specId" />"><s:property value="top.specName" /></option>
                    </s:iterator>
                </select>
            </div>
            <div class="form-group col-sm-12">
                <label for="sel1">(Admin Only) Select Lecturer:</label>
                <select class="form-control" name="project.lecturer">
                    <s:iterator value="lecturers">
                        <option value="<s:property value="top.userId" />"><s:property value="top.userName" /></option>
                    </s:iterator>
                </select>
            </div>
            <div class="form-group col-sm-12">
                <label>Upload Project PDF File: </label>
                <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                    <div class="form-control" data-trigger="fileinput"><i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
                    <span class="input-group-addon btn btn-default btn-file"><span class="fileinput-new">Select file</span><span class="fileinput-exists">Change</span>
                        <input type="file" name="upload">
                    </span>
                    <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
                </div>
            </div>
            <div class="form-group">
                <label for="datetimepicker1" class="control-label col-sm-1">Due Date</label>
                <div class='input-group date col-sm-3' id='datetimepicker1'>
                    <input name="project.dueDate" type='text' class="form-control" value="<s:date name="today"  format="dd-MM-yyyy hh:mma" />"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

                <script type="text/javascript">
                    $(function () {
                        $('#datetimepicker1').datetimepicker({
                            format: "D-MM-YYYY h:mmA"
                        });
                    });
                </script>
            </div>

            <div class="form-group col-sm-12">
                <label class="control-label">Project Description</label>
                <div>
                    <textarea  id="editor1" name="project.projectDescription" class="form-control" 
                               rows="10" cols="80" required></textarea>
                </div>
            </div>
            <div class="form-group col-sm-12">
                <label for="sel1">Project active:</label>
                <input type="checkbox" name="project.projectActive" checked>
            </div>
            <button onclick="window.history.back();" class="btn btn-info">Go Back</button>
            <input name="_method" type="hidden" value="put" />
            <button formmethod="post" type="submit" class="btn btn-primary">Submit</button>

            <script>
                CKEDITOR.replace('editor1');
            </script>
        </form>
    </div>
    <jsp:include page="/WEB-INF/layouts/footer.jsp" />
</body>
</html>
