<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/content/layouts/header.jsp" />
        <h1>Oink oink I'm a little pig</h1>
        <s:include value="/WEB-INF/content/layouts/footer.jsp" />
    </body>
</html>
