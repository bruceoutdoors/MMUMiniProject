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
            <h2>View Assigned Projects</h2>
            <p>You currently have 99999  projects:</p>                                                                                      
            <div class="table-responsive">          
                <table class="table">
                    <thead>
                        <tr>
                            <th>Project Id</th>
                            <th>Project Title</th>
                            <th>Lecturer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Anna</td>
                            <td>Pitt</td>
                            <td>35</td>
                            <td>New York</td>
                            <td>USA</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
