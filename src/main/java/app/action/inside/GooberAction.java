/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.inside;

import app.model.Role;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;

public class GooberAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        Role r = new Role();
        r.setRoleId(5);
        r.setRoleName("Goober Daddy");
        DB.getInstance().persist(r);
        return SUCCESS;
    }
}
