/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Lecturer;
import app.model.Project;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
/**
 *
 * @author bruceoutdoors
 */
public class ReportAction extends ActionSupport {
    public String para;
    public String alertMsg;
    public String alertType;
    public List<Project> projectList;
    public List<Lecturer> lecturers;
    public List<Specialization> specs;
    public User user = LoginManager.getCurrentUser();
    
    public String index() {
        lecturers = DB.getInstance().createNamedQuery("Lecturer.findAll").getResultList();
        specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();
        StringBuilder query = new StringBuilder();

        // 1 = 1 is just to allow me to append AND's. 
        query.append("SELECT p FROM Project p WHERE 1 = 1");

        HttpServletRequest request = ServletActionContext.getRequest();

        String title = request.getParameter("title");
        if (title != null && !title.isEmpty()) {
            query.append(" AND p.projectTitle LIKE '%").append(title).append("%'");
        }

        String spec = request.getParameter("spec");
        if (spec != null && !spec.isEmpty()) {
            query.append(" AND p.specId = ").append(spec);
        }

        if (user.isLecturer()) {
            query.append(" AND p.lecturerId = ").append(user.getUserId());
        } else {
            String lecturer = request.getParameter("lecturer");
            if (lecturer != null && !lecturer.isEmpty()) {
                query.append(" AND p.lecturerId = ").append(lecturer);
            }
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

        projectList = DB.getInstance().createQuery(query.toString()).getResultList();
        return "index";
    }
}
