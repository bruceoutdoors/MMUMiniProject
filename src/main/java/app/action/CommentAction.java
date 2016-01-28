/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Comment;
import app.model.Project;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import core.LoginManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
/**
 *
 * @author bruceoutdoors
 */
@Result(name = "redirect", location = "${url}", type = "redirect")
public class CommentAction extends ActionSupport {
    public String id;
    public String url;
    public User user = LoginManager.getCurrentUser();
    
    public String create() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        
        String projectId = request.getParameter("project.projectId");
        
        Comment c = new Comment();
        c.setCommentDescription(request.getParameter("comment.commentDescription"));
        c.setProjectId((Project) DB.getInstance()
                .createNamedQuery("Project.findByProjectId")
                .setParameter("projectId", Integer.parseInt(projectId))
                .getSingleResult());
        c.setUserId(user);
        
        DB.getInstance().persist(c);
        
        url = "/viewboard/" + projectId;
        
        return "redirect";
    }
}
