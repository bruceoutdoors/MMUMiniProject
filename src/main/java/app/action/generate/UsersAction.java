/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action.generate;

import app.model.Admin;
import app.model.Lecturer;
import app.model.Specialization;
import app.model.Student;
import app.model.User;
import com.opensymphony.xwork2.ActionSupport;
import core.DB;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UsersAction extends ActionSupport {

    public String talk;
    private Fairy fairy;

    public String index() throws Exception {
        fairy = Fairy.create();

        // ** GENERATE STUDENTS, ADMINS AND LECTURERS ** //
        for (int i = 0; i < 15; i++) {
            Lecturer a = new Lecturer();
            generateUser(a);
            DB.getInstance().persist(a);
        }

        for (int i = 0; i < 5; i++) {
            Admin a = new Admin();
            generateUser(a);
            DB.getInstance().persist(a);
        }

        List<Specialization> specs = DB.getInstance().createNamedQuery("Specialization.findAll").getResultList();
        for (int i = 0; i < 60; i++) {
            Student a = new Student();
            generateUser(a);
            a.setSpecId(specs.get(ThreadLocalRandom.current().nextInt(0, specs.size())));
            DB.getInstance().persist(a);

        }

        return SUCCESS;
    }

    private void generateUser(User u) {
        Person p = fairy.person();
        u.setUserPassword(p.password());
        u.setUserId(ThreadLocalRandom.current().nextInt(1000000, 9999999));
        u.setUserEmail(p.email());
        u.setUserName(p.username());
        u.setUserTel(p.telephoneNumber());
    }
}
