/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.action.*;
import app.model.Comment;
import app.model.Project;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.util.List;
import javax.persistence.NoResultException;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 *
 * @author bruceoutdoors
 */
@Result(name = "redirect", location = "${url}", type = "redirect")
@ResultPath("/WEB-INF/content/viewboard")
public class ViewboardAction extends ActionSupport {

    public List<Project> projectList;
    public List<Comment> comments;
    public User user = LoginManager.getCurrentUser();;
    public int id;
    public Project project;
    public String url = "/account/login";
    public String alertMsg;
    public String alertType;

    public String index() {
        StringBuilder query = new StringBuilder();
        
        if (user == null) {
            return "redirect";
        }
 
        query.append("SELECT p FROM Project p WHERE p.projectActive = true");

        if (user.isStudent()) {
            query.append(" AND p.specId = ").append(user.getStudent().getSpecId().getSpecId());
        }

        projectList = DB.getInstance().createQuery(query.toString()).getResultList();

        return "index";
    }

    public String show() {
        try {
            project = getProject();
        } catch (NoResultException ex) {
            alertMsg = "Cannot find project with id: " + id;
            alertType = "warning";
            return index();
        }

        comments = DB.getInstance()
                .createQuery("SELECT c FROM Comment c WHERE c.projectId = " + Integer.toString(id)
                        + " ORDER BY c.dateCreated DESC").getResultList();
        return "show";
    }

    private Project getProject() throws NoResultException {
        return (Project) DB.getInstance()
                .createNamedQuery("Project.findByProjectId")
                .setParameter("projectId", id)
                .getSingleResult();
    }
}
