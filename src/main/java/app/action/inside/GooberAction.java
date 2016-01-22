/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.inside;

import app.model.Admin;
import app.model.Lecturer;
import app.model.Role;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import java.util.Date;
import javax.persistence.EntityManager;

public class GooberAction extends ActionSupport {

    public String talk;

    public String index() throws Exception {

//        EntityManager em = DB.getInstance().createEntityManager();
//        
//        em.getTransaction().begin();
//        Role f = em.find(Role.class, 5);
//        if (f == null) {
//            f = new Role();
//            f.setRoleId(5);
//            f.setRoleName("Goober Lord");
//            em.persist(f);
//        } else {
//            f.setRoleName("Goober Master");
//        }
//
//        em.getTransaction().commit();
//        
//        talk = f.getRoleName();
//        EntityManager em = DB.getInstance().createEntityManager();
//        em.getTransaction().begin();

        Admin a = DB.getInstance().find(Admin.class, 2);

        if (a == null) {
            a = new Admin();
            a.setUserId(2);
            a.setUserEmail("bruceoutdoors@gmail.com");
            a.setUserName("Morpheus");
            a.setUserTel("0192312213");
            a.setUserlastSignIn(new Date());
            DB.getInstance().persist(a);
        }

        talk = a.getUserName();
        
//        em.getTransaction().commit();

        return SUCCESS;
    }
}
