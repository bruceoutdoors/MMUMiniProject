<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Project</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <script src="//cdn.ckeditor.com/4.5.6/standard/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h2>Edit Project</h2>
            <form role="form" action="${pageContext.request.contextPath}/project/<s:property value="id" />">
                <div class="form-group col-sm-12">
                    <label class="control-label">Project Title</label>
                    <div>
                        <input name="project.projectTitle" class="form-control" 
                               type="text" value="<s:property value="project.projectTitle"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="datetimepicker1" class="control-label col-sm-1">Due Date</label>
                    <div class='input-group date col-sm-3' id='datetimepicker1'>
                        <input name="project.dueDate" type='text' class="form-control" value="<s:date name="project.dueDate" format="dd-MM-yyyy hh:mma" />" />
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
                                   rows="10" cols="80" required><s:property value="project.projectDescription"/></textarea>
                    </div>
                </div>
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
