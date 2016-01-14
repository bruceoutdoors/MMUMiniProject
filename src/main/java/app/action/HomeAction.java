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
    
    @Override
    public String execute() {
        para = "HELLO MR BLUE";
        return SUCCESS;
    }
}
