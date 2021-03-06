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
            #fixedbutton1 { position: fixed; bottom: 20px; right: 95px; 
            }

            #fixedbutton2 { position: fixed; bottom: 20px; right: 20px;
            }
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
            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/<s:property value="id"/>" role="form"> 
                <div class="form-group">
                    <label class="control-label col-sm-2" for="newpass">Change Password:</label>
                    <div class="col-sm-10">
                        <input name="user.userPassword" type="password" class="form-control" id="newpass" placeholder="Enter New Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="conpass">Confirm Changed Password:</label>
                    <div class="col-sm-10">
                        <input name="user.confirmPassword" type="password" class="form-control" id="conpass" placeholder="Confirm New Password">
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
                        <input  name="user.userTel" value="<s:property value="user.userTel" />" type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
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
                    <label><input type="radio" name="status" value="on"
                                  <s:if test="%{user.userActive == true}">
                                      checked 
                                  </s:if> >Active</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="status" value="off"
                                  <s:if test="%{user.userActive == false}">
                                      checked 
                                  </s:if> >Inactive</label>
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
