/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Comment;
import app.model.Lecturer;
import app.model.Project;
import app.model.Specialization;
import app.model.Student;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
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

    public String alertMsg;
    public String alertType; // success, info, warning, danger
    public int id;

    public final String DATE_FORMAT = "dd-MM-yyyy hh:mma";

    public String index() {
       
        return "index";
    }

    public String show() {
        
        return "show";
    }

    public String edit() {
       
        return "edit";
    }

    public String editNew() {
     
        return "new";
    }

    public String create() throws ParseException, Exception {
        
        return index();
    }

    public String update() throws Exception {
        

        return show();
    }

    public String destroy() {

        return "home";
    }

}
