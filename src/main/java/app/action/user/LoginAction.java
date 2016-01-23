/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.user;

import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.LoginManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author bruceoutdoors
 */
@Results({
    @Result(name = "success", location = "/", type = "redirect")
})
public class LoginAction extends ActionSupport implements ServletRequestAware {

    public String incorrectCredentials = "hidden";
    HttpServletRequest request;

    public String index() {
        return INPUT;
    }

    // POST to login
    public String create() throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User u = LoginManager.login(username, password);

        if (u != null) {
            return SUCCESS;
        }

        incorrectCredentials = "";

        return INPUT;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
}
