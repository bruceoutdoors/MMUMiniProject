/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.account;

import app.model.Project;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author bruceoutdoors
 */
public class ProjectAction extends ActionSupport {

    public Project project;
    public List<Project> pastProjects;
    public User user = LoginManager.getCurrentUser();

    public String index() {
        if (user.isStudent()) {
            List<Project> studentProjects = DB.getInstance()
                    .createQuery("SELECT p FROM Project p WHERE"
                            + " p.projectActive = true"
                            + " AND p.subDate IS NULL"
                            + " AND p.studentId = " + user.getUserId())
                    .getResultList();
            if (studentProjects.size() >= 1) {
                project = studentProjects.get(0);
            }

            pastProjects = DB.getInstance()
                    .createQuery("SELECT p FROM Project p WHERE"
                            + " p.subDate IS NOT NULL"
                            + " AND p.studentId = " + user.getUserId()
                            + " ORDER BY p.subDate DESC")
                    .getResultList();
        }

        return SUCCESS;
    }

}
