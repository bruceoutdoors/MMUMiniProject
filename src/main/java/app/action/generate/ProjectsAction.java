/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.generate;

import app.model.Lecturer;
import app.model.Project;
import app.model.Student;
import app.model.Specialization;
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
public class ProjectsAction extends ActionSupport {

    private Fairy fairy;
    
    private final String[] PROJECT_STATUS = {
        "UNASSIGNED", "ASSIGNED",
        "SUBMITTED", "EVALUATED",
        "OVERDUED"
    };

    private final String[] PROJECT_GRADE = {
        "A+", "A", "A-", "B+", "B", "B-",
        "C+", "C", "C-", "D+", "D", "F"
    };

    public String index() throws Exception {
        fairy = Fairy.create();

        // ** GENERATE PROJECTS ** //
        List<Lecturer> lecturers = DB.getInstance().createNamedQuery("Lecturer.findAll").getResultList();
        List<Student> students = DB.getInstance().createNamedQuery("Student.findAll").getResultList();
        List<Specialization> specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();

        for (int i = 0; i < 50; i++) {
            Project p = new Project();
            p.setLecturerId(lecturers.get(ThreadLocalRandom.current().nextInt(0, lecturers.size())));
            p.setSpecId(specs.get(ThreadLocalRandom.current().nextInt(0, specs.size())));
            p.setProjectTitle(fairy.textProducer().sentence());

            StringBuilder content = new StringBuilder();
            int randPara = ThreadLocalRandom.current().nextInt(2, 6);
            for (int j = 0; j < randPara; j++) {
                content.append(fairy.textProducer().paragraph() + "\n\n");
            }

            p.setProjectDescription(content.toString());
            DateTime duedate = fairy.dateProducer().randomDateBetweenTwoDates(
                    DateTime.now().minusWeeks(1),
                    DateTime.now().plusWeeks(5));
            p.setDueDate(duedate.toDate());
            p.setStartDate(fairy.dateProducer().randomDateBetweenTwoDates(
                    DateTime.now().minusWeeks(6),
                    DateTime.now().plusWeeks(3)).toDate());

            if (duedate.isAfterNow()) {
                String status = PROJECT_STATUS[ThreadLocalRandom.current().nextInt(2, 5)];
                p.setProjectStatus(status);

                DateTime subDate = fairy.dateProducer().randomDateBetweenTwoDates(
                        duedate.minusWeeks(1),
                        duedate.toDateTime());

                if (status.equals("EVALUATED")) {
                    p.setStudentId(students.get(ThreadLocalRandom.current().nextInt(0, students.size())));
                    p.setEvaComment(fairy.textProducer().latinSentence());
                    p.setProjectGrade(PROJECT_GRADE[ThreadLocalRandom.current().nextInt(0, PROJECT_GRADE.length)]);
                    p.setSubDate(subDate.toDate());
                } else if (status.equals("SUBMITTED")) {
                    p.setStudentId(students.get(ThreadLocalRandom.current().nextInt(0, students.size())));
                    p.setSubDate(subDate.toDate());
                }
            } else {
                String status = PROJECT_STATUS[ThreadLocalRandom.current().nextInt(0, 2)];
                p.setProjectStatus(status);

                if (status.equals("ASSIGNED")) {
                    p.setStudentId(students.get(ThreadLocalRandom.current().nextInt(0, students.size())));
                }
            }

            DB.getInstance().persist(p);
        }

        return SUCCESS;
    }
}
