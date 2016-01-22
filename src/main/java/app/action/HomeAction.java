/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 *
 * @author bruceoutdoors
 */
public class HomeAction extends ActionSupport {
    public String para;
    
    public String index() {
        para = (System.getenv("OPENSHIFT_APP_NAME") == null ? "Localhost" : "Openshift");
        return SUCCESS;
    }
}
