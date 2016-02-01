/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.action.*;
import app.model.Comment;
import app.model.Lecturer;
import app.model.Project;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
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
    public User user = LoginManager.getCurrentUser();
    public List<Student> students;
    public int id;
    public Project project;
    public String url = "/account/login";
    public String alertMsg;
    public String alertType;
    public Date lastSignIn;
    public List<Lecturer> lecturers;
    public List<Specialization> specs;

    public String index() {
        StringBuilder query = new StringBuilder();

        if (user == null) {
            return "redirect";
        }

        students = DB.getInstance().createNamedQuery("Student.findAll").getResultList();
        lecturers = DB.getInstance().createNamedQuery("Lecturer.findAll").getResultList();
        specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();
        lastSignIn = LoginManager.getLastSignIn();
        HttpServletRequest request = ServletActionContext.getRequest();
        query.append("SELECT p FROM Project p WHERE p.projectActive = true");

        if (user.isStudent()) {
            query.append(" AND p.specId = ").append(user.getStudent().getSpecId().getSpecId());
        }

        String title = request.getParameter("title");
        if (title != null && !title.isEmpty()) {
            query.append(" AND p.projectTitle LIKE '%").append(title).append("%'");
        }

        String spec = request.getParameter("spec");
        if (spec != null && !spec.isEmpty()) {
            query.append(" AND p.specId = ").append(spec);
        }

        String lecturer = request.getParameter("lecturer");
        if (lecturer != null && !lecturer.isEmpty()) {
            query.append(" AND p.lecturerId = ").append(lecturer);
        }
        
        String student = request.getParameter("student");
        if (student != null && !student.isEmpty()) {
            query.append(" AND p.studentId = ").append(student);
        }

        String active = request.getParameter("active");
        if (active != null && !active.isEmpty()) {
            if (active.equals("yes")) {
                query.append(" AND p.projectActive = true");
            } else if (active.equals("no")) {
                query.append(" AND p.projectActive = false");
            }
        }

        String cmnts = request.getParameter("cmnts");
        if (cmnts != null && !cmnts.isEmpty()) {
            if (cmnts.equals("yes")) {
                query.append(" AND p.commentList.size > 0");
            } else if (cmnts.equals("no")) {
                query.append(" AND p.commentList.size = 0");
            }
        }

        String assigned = request.getParameter("assigned");
        if (assigned != null && !assigned.isEmpty()) {
            if (assigned.equals("yes")) {
                query.append(" AND p.studentId IS NOT NULL");
            } else if (assigned.equals("no")) {
                query.append(" AND p.studentId IS NULL");
            }
        }

        String completed = request.getParameter("completed");
        if (completed != null && !completed.isEmpty()) {
            if (completed.equals("yes")) {
                query.append(" AND p.subDate IS NOT NULL");
            } else if (completed.equals("no")) {
                query.append(" AND p.subDate IS NULL");
            }
        }

        query.append(" ORDER BY p.startDate DESC");

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
