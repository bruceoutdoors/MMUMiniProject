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
        <title>New Account</title>
        <jsp:include page="/WEB-INF/layouts/head.jsp" />
        <style>
            #fixedbutton1 { position: fixed; bottom: 20px; right: 95px; 
            }

            #fixedbutton2 { position: fixed; bottom: 20px; right: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/layouts/header.jsp" />
            <h1><small>New Account</small></h1>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/user" role="form"> 
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Id:</label>
                    <div class="col-sm-10">
                        <input  name="user.userId" type="tel" class="form-control" id="number" placeholder="Enter Id">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Username:</label>
                    <div class="col-sm-10">
                        <input  name="user.userName" type="tel" class="form-control" id="number" placeholder="Enter Username">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="newpass">Password:</label>
                    <div class="col-sm-10">
                        <input name="user.userPassword" type="password" class="form-control" id="newpass" placeholder="Enter New Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="conpass">Confirm Password:</label>
                    <div class="col-sm-10">
                        <input name="user.confirmPassword" type="password" class="form-control" id="conpass" placeholder="Confirm New Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">E-mail:</label>
                    <div class="col-sm-10">
                        <input name="user.userEmail" type="email" class="form-control" id="email" placeholder="Enter E-mail">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Contact Number:</label>
                    <div class="col-sm-10">
                        <input  name="user.userTel" type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Role:</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="user.roleId">
                            <s:iterator value="roles">
                                <option value="<s:property value="top.roleId" />"
                                        <s:if test="%{user.roleId.roleId == top.roleId}">
                                            selected 
                                        </s:if> ><s:property value="top.roleName" /></option>
                            </s:iterator>
                        </select>
                    </div>
                </div>
                <br>

                <!-- Account status only for Admin -->
                <h3><small>Account Status</small></h3>
                <div class="radio">
                    <label><input type="radio" name="status" value="on" checked>Active</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="status" value="off">Inactive</label>
                </div>
                <br>
                <input name="_method" type="hidden" value="put" />
                <button id="fixedbutton1" type="reset" class="btn btn-info">Clear</button>
                <button id="fixedbutton2" formmethod="POST" type="submit" class="btn btn-primary">Submit</button>
            </form>
            <h3><a href="${pageContext.request.contextPath}/user">Go Back</a></h3>
            <br>


            <jsp:include page="/WEB-INF/layouts/footer.jsp" />
        </div> 
    </body>
</html>
