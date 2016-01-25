<%-- 
    Document   : grade
    Created on : Jan 25, 2016, 1:46:53 PM
    Author     : Redzrex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Projects</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <br>
        <div class="container">
        <center>    
            <h2>Projects Grade</h2>
        <table class="table table-hover table-responsive" style="width:80%">
            <thead>
            <tr>
                    <th><h4><center>Project</center></h4></th>
                    <th><h4><center>Grade</center></h4></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="projectList">
                <td>
                    <p> Project Title </p>
                </td>
                <td>
                <center>
                    <p>Project Grade </p>
                </center>
                </td>
            </s:iterator>
            </tbody>
        </table>
        </center>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
