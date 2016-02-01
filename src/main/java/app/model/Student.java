/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "student")
@DiscriminatorValue("2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s ORDER BY s.userName"),
    @NamedQuery(name = "Student.findByUserId", query = "SELECT s FROM Student s WHERE s.userId = :userId")})
public class Student extends User {

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @JoinColumn(name = "spec_id", referencedColumnName = "spec_id")
    @ManyToOne(optional = false)
    private Specialization specId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<Project> projectList;

    public Student() {
        setRoleId(Role.getStudent());
    }

    public Specialization getSpecId() {
        return specId;
    }

    public void setSpecId(Specialization specId) {
        this.specId = specId;
    }

    @XmlTransient
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
