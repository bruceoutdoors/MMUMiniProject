/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.account;

import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.LoginManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author bruceoutdoors
 */
public class ProjectAction extends ActionSupport  {

    public String incorrectCredentials = "hidden";
    HttpServletRequest request;

    public String index() {
        return "project";
    }

   
}
