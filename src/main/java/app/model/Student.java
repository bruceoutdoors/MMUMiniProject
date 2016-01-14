/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByUserId", query = "SELECT s FROM Student s WHERE s.userId = :userId")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @JoinColumn(name = "FACULTY_fac_id", referencedColumnName = "fac_id")
    @ManyToOne(optional = false)
    private Faculty fACULTYfacid;
    @JoinColumn(name = "PROJECT_category_id", referencedColumnName = "project_id")
    @ManyToOne(optional = false)
    private Project pROJECTcategoryid;
    @JoinColumn(name = "SPECIALIZATION_spec_id", referencedColumnName = "spec_id")
    @ManyToOne(optional = false)
    private Specialization sPECIALIZATIONspecid;
    @JoinColumn(name = "USER_user_id1", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User uSERuserid1;

    public Student() {
    }

    public Student(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Faculty getFACULTYfacid() {
        return fACULTYfacid;
    }

    public void setFACULTYfacid(Faculty fACULTYfacid) {
        this.fACULTYfacid = fACULTYfacid;
    }

    public Project getPROJECTcategoryid() {
        return pROJECTcategoryid;
    }

    public void setPROJECTcategoryid(Project pROJECTcategoryid) {
        this.pROJECTcategoryid = pROJECTcategoryid;
    }

    public Specialization getSPECIALIZATIONspecid() {
        return sPECIALIZATIONspecid;
    }

    public void setSPECIALIZATIONspecid(Specialization sPECIALIZATIONspecid) {
        this.sPECIALIZATIONspecid = sPECIALIZATIONspecid;
    }

    public User getUSERuserid1() {
        return uSERuserid1;
    }

    public void setUSERuserid1(User uSERuserid1) {
        this.uSERuserid1 = uSERuserid1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmuminiproject.model.Student[ userId=" + userId + " ]";
    }
    
}
