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
    @Result(name = "home", location = "/project", type = "redirect")
})
@ResultPath("/WEB-INF/content/project")
public class ProjectAction extends ActionSupport {

    public String alertMsg;
    public String alertType; // success, info, warning, danger
    public List<Project> projectList;
    public List<Lecturer> lecturers;
    public List<Specialization> specs;
    public List<Comment> comments;
    public Project project;
    public int id;
    public Date today = new Date();

    // file upload
    private File file;
    private String contentType;
    private String filename;

    public final String DATE_FORMAT = "dd-MM-yyyy hh:mma";

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
        
        String lecturer = request.getParameter("lecturer");
        if (lecturer != null && !lecturer.isEmpty()) {
            query.append(" AND p.lecturerId = ").append(lecturer);
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
                query.append(" AND p.commentCollection.size > 0");
            } else if (cmnts.equals("no")) {
                query.append(" AND p.commentCollection.size = 0");
            }
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

    public String edit() {
        try {
            project = getProject();
        } catch (NoResultException ex) {
            alertMsg = "Cannot find project with id: " + id;
            alertType = "warning";
            return index();
        }

        return "edit";
    }

    public String editNew() {
        lecturers = DB.getInstance().createNamedQuery("Lecturer.findAll").getResultList();
        specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();
        return "new";
    }

    public String create() throws ParseException, Exception {
        Project p = new Project();
        HttpServletRequest request = ServletActionContext.getRequest();
        p.setDueDate(new SimpleDateFormat(DATE_FORMAT)
                .parse(request.getParameter("project.dueDate")));
        p.setProjectTitle(request.getParameter("project.projectTitle"));
        p.setProjectDescription(request.getParameter("project.projectDescription"));
        p.setSpecId((Specialization) DB.getInstance()
                .createNamedQuery("Specialization.findBySpecId")
                .setParameter("specId", Integer.parseInt(request.getParameter("project.spec")))
                .getSingleResult());
        p.setLecturerId((Lecturer) DB.getInstance()
                .createNamedQuery("Lecturer.findByUserId")
                .setParameter("userId", Integer.parseInt(request.getParameter("project.lecturer")))
                .getSingleResult());

        // file upload
        if (file != null) {
            String uploadDir = new File(getServletContext()
                    .getRealPath("/"))
                    .getParentFile()
                    .getParent() + File.separator + "uploads" + File.separator;
            File destFile = new File(uploadDir, filename);
            FileUtils.copyFile(file, destFile);
            p.setProjectFile(uploadDir + filename);
        }
        
        DB.getInstance().persist(p);

        alertMsg = "Created project: " + p.getProjectTitle();
        alertType = "success";
        return index();
    }

    public String update() throws Exception {
        try {
            DB.getInstance().execTransaction(new DB.Transaction() {
                @Override
                public void execute() throws Exception {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    Project p = getProject();
                    p.setProjectTitle(request.getParameter("project.projectTitle"));
                    p.setDueDate(new SimpleDateFormat(DATE_FORMAT)
                            .parse(request.getParameter("project.dueDate")));
                    p.setProjectDescription(request.getParameter("project.projectDescription"));
                }
            });
        } catch (NoResultException ex) {
            alertMsg = "Cannot find project with id: " + id;
            alertType = "warning";
            return index();
        }

        return show();
    }

    public String destroy() {

        String title;
        try {
            project = getProject();
            title = project.getProjectTitle();
            DB.getInstance().remove(project);
        } catch (Exception ex) {
            alertMsg = "Unable to delete project with id: " + id;
            alertType = "danger";
            return index();
        }

        // the success message doesn't actually show up.
        // I either have the URL stay the or redirect to home,
        // and though it's more intuitive to have it redirect home
        // I find it kinda sad that I can't have both ):
        alertMsg = "Successfully deleted \"" + title + "\"!";
        alertType = "success";
        return "home";
    }

    private Project getProject() throws NoResultException {
        return (Project) DB.getInstance()
                .createNamedQuery("Project.findByProjectId")
                .setParameter("projectId", id)
                .getSingleResult();
    }

    // File upload functionality:
    public void setUpload(File file) {
        this.file = file;
    }

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUploadFileName(String filename) {
        this.filename = filename;
    }
}
