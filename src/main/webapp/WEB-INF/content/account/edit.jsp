<%-- 
    Document   : edit
    Created on : Jan 21, 2016, 11:04:53 PM
    Author     : Redzrex
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Account</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <style>
            #fixedbutton1 { margin-right: 20px; }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/layouts/header.jsp" />
            <h1><small>Edit Account</small></h1>

            <table class="table table-hover, table-striped">
                <thead>
                    <tr style="text-align:center">
                        <td>ID</td>
                        <td>Name</td>
                    </tr>
                </thead>
                <tbody>
                    <tr style="text-align:center">
                        <td> <p><s:property value="user.userId" /></p></td>
                        <td> <p><s:property value="user.userName" /></p></td>
                    </tr>
                </tbody>
            </table>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="oldpass">Old Password:</label>
                    <div class="col-sm-10">
                        <input type="password" name="oldpass" class="form-control" id="oldpass" placeholder="Enter Old Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="newpass">New Password:</label>
                    <div class="col-sm-10">
                        <input type="password" name="newpass" class="form-control" id="newpass" placeholder="Enter New Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="conpass">Confirm Password:</label>
                    <div class="col-sm-10">
                        <input type="password" name="conpass" class="form-control" id="conpass" placeholder="Confirm New Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">E-mail:</label>
                    <div class="col-sm-10">
                        <input name="user.userEmail" value="<s:property value="user.userEmail" />" type="email" class="form-control" id="email" placeholder="Enter E-mail">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Contact Number:</label>
                    <div class="col-sm-10">
                        <input name="user.userTel" value="<s:property value="user.userTel" />" type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
                    </div>
                </div>
                <br/>
                <button id="fixedbutton2"  formmethod="post"  type="submit" class="btn btn-primary pull-right">Submit</button>
                <button id="fixedbutton1" type="reset" class="btn btn-info pull-right">Clear</button>
            </form>
                    
            <jsp:include page="/WEB-INF/layouts/footer.jsp" />
        </div> 
    </body>
</html>
