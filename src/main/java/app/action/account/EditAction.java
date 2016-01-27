/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.account;

import app.model.Lecturer;
import app.model.Project;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.text.SimpleDateFormat;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author bruceoutdoors
 */
public class EditAction extends ActionSupport {

    public User user;
    public String alertMsg;
    public String alertType; // success, info, warning, danger

    public String index() {
        user = LoginManager.getCurrentUser();

        return "edit";
    }

    public String create() throws Exception {
        user = LoginManager.getCurrentUser();
        try {
            DB.getInstance().execTransaction(new DB.Transaction() {
                @Override
                public void execute() throws Exception {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    user.setUserEmail(request.getParameter("user.userEmail"));
                    user.setUserTel(request.getParameter("user.userTel"));
                }
            });
        } catch (Exception ex) {
            alertMsg = "Cannot update becuase " + ex.getMessage();
            alertType = "warning";
            return "edit";
        }
        
        alertMsg = "Successfully update account credentials!";
        alertType = "success";
        return "edit";
    }

}
