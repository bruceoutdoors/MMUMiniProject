<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <h1>Hello Worsssld!</h1>
            <p>Para suppose to be: <s:property value="para" /></p>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
