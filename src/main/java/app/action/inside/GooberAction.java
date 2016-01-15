/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.inside;

import app.model.Role;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class GooberAction extends ActionSupport {

    public String talk;

    @Override
    public String execute() throws Exception {
       

        EntityManager em = DB.getInstance().createEntityManager();
        
        em.getTransaction().begin();
        Role f = em.find(Role.class, 5);
        if (f == null) {
            f = new Role();
            f.setRoleId(5);
            f.setRoleName("Goober Lord");
            em.persist(f);
        } else {
            f.setRoleName("Goober Master");
        }

        em.getTransaction().commit();
        
        talk = f.getRoleName();
        
        return SUCCESS;
    }
}
