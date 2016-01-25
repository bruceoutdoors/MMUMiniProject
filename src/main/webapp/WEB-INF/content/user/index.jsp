<%-- 
    Document   : edit
    Created on : Jan 21, 2016, 11:04:53 PM
    Author     : Redzrex
--%>

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
            <h1><small>Edit Account</small></h1>
            <ul class="nav nav-pills">
                <li><a data-toggle="tab" href="#form1">Administrator</a></li>
                <li><a data-toggle="tab" href="#form2">Lecturer</a></li>
                <li><a data-toggle="tab" href="#form3">Student</a></li>
            </ul>

            <div class="tab-content">
                <div id="form1" class="tab-pane fade">
                    <h2><small>Administrator</small></h2>
                    <table class="table table-hover, table-striped">
                        <thead>
                            <tr style="text-align:center">
                                <td>ID</td>
                                <td>Name</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="text-align:center">
                                <td> <p>Admin ID</p></td>
                                <td> <p>Admin Name</p></td>
                            </tr>
                        </tbody>
                    </table>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="oldpass">Old Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="oldpass" placeholder="Enter Old Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="newpass">New Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="newpass" placeholder="Enter New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="conpass">Confirm Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="conpass" placeholder="Confirm New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email">E-mail:</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email" placeholder="Enter E-mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="number">Contact Number:</label>
                            <div class="col-sm-10">
                                <input type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
                            </div>
                        </div>
                        <br>
                        <h3><small>Account Status</small></h3>
                        <div class="radio">
                            <label><input type="radio" name="status">Active</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="status">Inactive</label>
                        </div>
                        <br>
                        <button id="fixedbutton1" type="reset" class="btn btn-info">Clear</button>
                        <button id="fixedbutton2" type="submit" class="btn btn-primary">Submit</button>
                    </form>
                    <br>

                </div>
                <div id="form2" class="tab-pane fade">
                    <h3>Lecturer</h3>
                    <table class="table table-hover, table-striped">
                        <thead>
                            <tr style="text-align:center">
                                <td>ID</td>
                                <td>Name</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="text-align:center">
                                <td> <p>Lecturer ID</p></td>
                                <td> <p>Lecturer Name</p></td>
                            </tr>
                        </tbody>
                    </table>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="oldpass">Old Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="oldpass" placeholder="Enter Old Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="newpass">New Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="newpass" placeholder="Enter New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="conpass">Confirm Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="conpass" placeholder="Confirm New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email">E-mail:</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email" placeholder="Enter E-mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="number">Contact Number:</label>
                            <div class="col-sm-10">
                                <input type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
                            </div>
                        </div>
                        <br>
                        <button id="fixedbutton1" type="reset" class="btn btn-info">Clear</button>
                        <button id="fixedbutton2" type="submit" class="btn btn-primary">Submit</button>
                    </form>

                </div>
                <div id="form3" class="tab-pane fade">
                    <h3>Student</h3>
                    <table class="table table-hover, table-striped">
                        <thead>
                            <tr style="text-align:center">
                                <td>ID</td>
                                <td>Name</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="text-align:center">
                                <td> <p>Student ID</p></td>
                                <td> <p>Student Name</p></td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align:center">Student Specialization</td>
                            </tr>
                        </tbody>
                    </table>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="oldpass">Old Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="oldpass" placeholder="Enter Old Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="newpass">New Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="newpass" placeholder="Enter New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="conpass">Confirm Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="conpass" placeholder="Confirm New Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email">E-mail:</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email" placeholder="Enter E-mail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="number">Contact Number:</label>
                            <div class="col-sm-10">
                                <input type="tel" class="form-control" id="number" placeholder="Enter Contact Number">
                            </div>
                        </div>
                        <br>
                        <button id="fixedbutton1" type="reset" class="btn btn-info">Clear</button>
                        <button id="fixedbutton2" type="submit" class="btn btn-primary">Submit</button>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
