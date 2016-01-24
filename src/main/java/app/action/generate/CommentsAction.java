/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.generate;

import app.model.Comment;
import app.model.Project;
import app.model.User;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import io.codearte.jfairy.Fairy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.joda.time.DateTime;

/**
 *
 * @author bruceoutdoors
 */
public class CommentsAction extends ActionSupport {

    private Fairy fairy;

    public String index() throws Exception {
        fairy = Fairy.create();

        // ** GENERATE COMMENTS ** //
        List<Project> projects = DB.getInstance().createNamedQuery("Project.findAll").getResultList();
        List<User> users = DB.getInstance().createNamedQuery("User.findAll").getResultList();

        for (int i = 0; i < 25; i++) {
            Comment c = new Comment();
            DateTime commentDate = fairy.dateProducer().randomDateBetweenTwoDates(
                    DateTime.now().minusWeeks(1),
                    DateTime.now());
            c.setDateCreated(commentDate.toDate());
            c.setUserId(users.get(ThreadLocalRandom.current().nextInt(0, users.size())));
            c.setProjectId(projects.get(ThreadLocalRandom.current().nextInt(0, projects.size())));
            c.setCommentDescription(fairy.textProducer().latinSentence());
            DB.getInstance().persist(c);
        }

        return SUCCESS;
    }
}
