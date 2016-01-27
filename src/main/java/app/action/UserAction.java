/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Comment;
import app.model.Lecturer;
import app.model.Project;
import app.model.Role;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author bruceoutdoors
 */
@Results({
    @Result(name = "home", location = "/user", type = "redirect")
})
@ResultPath("/WEB-INF/content/user")
public class UserAction extends ActionSupport {

    public User user;
    public List<User> users;
    public List<Role> roles;
    public String alertMsg;
    public String alertType; // success, info, warning, danger
    public int id;

    public final String DATE_FORMAT = "dd-MM-yyyy hh:mma";

    public String index() {
        users = DB.getInstance().createNamedQuery("User.findAll").getResultList();

        return "index";
    }

    public String show() {

        return edit();
    }

    public String edit() {
        user = getUser();
        roles = DB.getInstance().createNamedQuery("Role.findAll").getResultList();

        return "edit";
    }

    public String editNew() {
        roles = DB.getInstance().createNamedQuery("Role.findAll").getResultList();

        return "new";
    }

    public String create() throws ParseException, Exception {
        User u = new User();

        HttpServletRequest request = ServletActionContext.getRequest();
        
        String password = request.getParameter("user.userPassword");

        if (password != null && !password.isEmpty()) {
            String confirm = request.getParameter("user.confirmPassword");
            if (!password.equals(confirm)) {
                alertMsg = "Passwords do not match!";
                alertType = "warning";
                return editNew();
            }
        }

        try {
            u.setUserId(Integer.parseInt(request.getParameter("user.userId")));
            u.setUserName(request.getParameter("user.userName"));
            u.setUserEmail(request.getParameter("user.userEmail"));
            u.setUserTel(request.getParameter("user.userTel"));
            u.setRoleId((Role) DB.getInstance()
                    .createNamedQuery("Role.findByRoleId")
                    .setParameter("roleId", Integer.parseInt(request.getParameter("user.roleId")))
                    .getSingleResult());
            u.setUserActive(request.getParameter("status").equals("on"));
            u.setUserPassword(password);
            DB.getInstance().persist(u);
        } catch (Exception ex) {
            alertMsg = "Unable to create user " + u.getUserName() + " because " + ex.getMessage();
            alertType = "warning";
            
            return editNew();
        }

        alertMsg = "Created User: " + u.getUserName();
        alertType = "success";
        return index();
    }

    public String update() throws Exception {
        user = getUser();
        HttpServletRequest request = ServletActionContext.getRequest();
        final String password = request.getParameter("user.userPassword");
        Boolean changePassword = false;

        if (password != null && !password.isEmpty()) {
            String confirm = request.getParameter("user.confirmPassword");
            if (password.equals(confirm)) {
                changePassword = true;
            } else {
                alertMsg = "Cannot update because passwords do not match!";
                alertType = "warning";
                return edit();
            }
        }

        try {
            DB.getInstance().execTransaction(new DB.Transaction() {
                @Override
                public void execute() throws Exception {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    user.setUserEmail(request.getParameter("user.userEmail"));
                    user.setUserTel(request.getParameter("user.userTel"));
                    user.setRoleId((Role) DB.getInstance()
                            .createNamedQuery("Role.findByRoleId")
                            .setParameter("roleId", Integer.parseInt(request.getParameter("user.roleId")))
                            .getSingleResult());
                    user.setUserActive(request.getParameter("status").equals("on"));
                }
            });

            if (changePassword) {
                DB.getInstance().execTransaction(new DB.Transaction() {
                    @Override
                    public void execute() throws Exception {
                        user.setUserPassword(password);
                    }
                });
            }
        } catch (Exception ex) {
            alertMsg = "Cannot update becuase " + ex.getMessage();
            alertType = "warning";

            return edit();
        }

        alertMsg = "Successfully update account credentials!";
        alertType = "success";

        return edit();
    }

    public String destroy() {

        return "home";
    }

    private User getUser() throws NoResultException {
        return (User) DB.getInstance()
                .createNamedQuery("User.findByUserId")
                .setParameter("userId", id)
                .getSingleResult();
    }

}
