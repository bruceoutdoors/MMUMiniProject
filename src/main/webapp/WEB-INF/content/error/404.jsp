<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>This page does not exist!</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="container">
            <center>
                <h1>Excuse me good sir, are you lost?</h1>
                <h3>This page that you seek - I'm afraid it doesn't exist... );</h3>
                <h5>But it's ok. Here's a kitty. Everything's going to be alright.</h5>
                <img src="${pageContext.request.contextPath}/images/super-cute-kitty.jpg" class="img-responsive img-rounded" alt="SUPER CUTE KITTEH!!">
            </center>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
