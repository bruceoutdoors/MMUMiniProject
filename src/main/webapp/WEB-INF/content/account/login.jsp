<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/layouts/header.jsp" />

        <div class="container">

            <form class="form-signin" method="post">
                <img src="${pageContext.request.contextPath}/images/splash.jpg" class="img-responsive img-rounded" alt="Cinque Terre">
                <h2 class="form-signin-heading">Please sign in</h2>
                <font color="red" <s:property value="incorrectCredentials" /> >Incorrect username or password</font>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input name="username" class="form-control" placeholder="Username" required="" autofocus="">
                <label for="inputPassword" class="sr-only">Password</label>
                <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>

        </div> <!-- /container -->
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
