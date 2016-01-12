/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmuminiproject.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 *
 * @author bruceoutdoors
 */
public class HomeAction extends ActionSupport {
    private String para;
    
    @Override
    public String execute() {
        setPara("HELLO MOTHERFUCKERS");
        return SUCCESS;
    }

    /**
     * @return the param
     */
    public String getPara() {
        return para;
    }

    /**
     * @param param the param to set
     */
    public void setPara(String param) {
        this.para = param;
    }
    
}
