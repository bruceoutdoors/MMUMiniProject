/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Admin;
import app.model.Lecturer;
import app.model.Role;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import java.text.ParseException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
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
    public List<Specialization> specs;
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
        specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();

        return "new";
    }

    public String create() throws ParseException, Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        User u = new User();

        int roleId = Integer.parseInt(request.getParameter("user.roleId"));
        switch (roleId) {
            case 0: {
                Admin a = new Admin();
                u = a;
                break;
            }
            case 1: {
                Lecturer l = new Lecturer();
                u = l;
                break;
            }
            case 2: {
                Student s = new Student();
                u = s;
                break;
            }
            default:
                break;
        }

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
//            u.setRoleId((Role) DB.getInstance()
//                    .createNamedQuery("Role.findByRoleId")
//                    .setParameter("roleId", Integer.parseInt(request.getParameter("user.roleId")))
//                    .getSingleResult());
            u.setUserActive(request.getParameter("status").equals("on"));
            u.setUserPassword(password);
            switch (roleId) {
                case 0: {
                    DB.getInstance().persist((Admin) u);
                    break;
                }
                case 1: {
                    DB.getInstance().persist((Lecturer) u);
                    break;
                }
                case 2: {
                    Student s = (Student) u;
                    s.setSpecId((Specialization) DB.getInstance()
                        .createNamedQuery("Specialization.findBySpecId")
                        .setParameter("specId", Integer.parseInt(request.getParameter("spec")))
                        .getSingleResult());
                    DB.getInstance().persist(s);
                    break;
                }
                default:
                    break;
            }

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
