/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.ResultPath;
/**
 *
 * @author bruceoutdoors
 */
@ResultPath("/WEB-INF/content/error")
public class ErrorAction extends ActionSupport {
    public String id;
    
    public String index() {
        return "404";
    }
    
    public String show() {
        return id;
    }
}
