/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import app.model.Admin;
import app.model.Lecturer;
import app.model.Project;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.joda.time.DateTime;

/**
 *
 * @author bruceoutdoors
 */
public class GeneratedataAction extends ActionSupport {

    public String para;

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

    @Override
    public String execute() throws Exception {
        fairy = Fairy.create();

        // ** GENERATE STUDENTS, ADMINS AND LECTURERS ** //
//        EntityManager em = DB.getInstance().createEntityManager();
//        em.getTransaction().begin();
        
        for (int i = 0; i < 20; i++) {
            Lecturer a = new Lecturer();
            generateUser(a);
            DB.getInstance().persist(a);
//            em.persist(a);
        }
        
        for (int i = 0; i < 10; i++) {
            Admin a = new Admin();
            generateUser(a);
            DB.getInstance().persist(a);
//            em.persist(a);
        }
        
        List<Specialization> specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();
        for (int i = 0; i < 100; i++) {
            Student a = new Student();
            generateUser(a);
            a.setSpecId(specs.get(ThreadLocalRandom.current().nextInt(0, specs.size())));
       
                //            em.persist(a);
                DB.getInstance().persist(a);
        
        }
        
//        try {
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
        
        
        // ** GENERATE PROJECTS ** //
//         em = DB.getInstance().createEntityManager();
//        em.getTransaction().begin();

        List<Lecturer> lecturers = DB.getInstance().createNamedQuery("Lecturer.findAll").getResultList();
        List<Student> students = DB.getInstance().createNamedQuery("Student.findAll").getResultList();

        for (int i = 0; i < 250; i++) {
            Project p = new Project();
            p.setLecturerId(lecturers.get(ThreadLocalRandom.current().nextInt(0, lecturers.size())));
            

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
//
//         try {
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }

        // ** GENERATE COMMENTS ** //
        para = "PARA-PARA-PARADISE";

        return SUCCESS;
    }

    private void generateUser(User u) {
        Person p = fairy.person();
        u.setUserId(ThreadLocalRandom.current().nextInt(1000000, 9999999));
        u.setUserEmail(p.email());
        u.setUserName(p.username());
        u.setUserTel(p.telephoneNumber());
    }
}
