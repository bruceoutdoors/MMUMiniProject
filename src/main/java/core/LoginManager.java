/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import app.model.User;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author bruceoutdoors
 */
public class LoginManager {
    public static User login(String username, String password) throws Exception {
        final User u;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            u = (User) DB.getInstance()
                    .createNamedQuery("User.findByUserName")
                    .setParameter("userName", username)
                    .getSingleResult();
            session.setAttribute("LAST_SIGN_IN", u.getUserlastSignIn());
        } catch (NoResultException ex) {
            return null;
        }

        if (!u.getUserPassword().equals(password)) {
            return null;
        }

        // set user sign in date and time
        DB.getInstance().execTransaction(new DB.Transaction() {
            @Override
            public void execute() throws Exception {
                u.setUserlastSignIn(new Date());
            }
        });

        session.setAttribute("CURRENT_USER", u);

        return u;
    }

    public static void logout() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("CURRENT_USER");
    }
    
    public static Date getLastSignIn() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return (Date) session.getAttribute("LAST_SIGN_IN");
    }

    public static User getCurrentUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        User u = (User) session.getAttribute("CURRENT_USER");

        return u;
    }
}
