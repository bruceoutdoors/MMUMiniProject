/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.user;

import com.opensymphony.xwork2.ActionSupport;
import core.LoginManager;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author bruceoutdoors
 */
@Results({
    @Result(name = "success", location = "/", type = "redirect")
})
public class LogoutAction extends ActionSupport  {

    public String index() {
        LoginManager.logout();
        return SUCCESS;
    }
}
