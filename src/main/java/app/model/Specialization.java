/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "specialization")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specialization.findAll", query = "SELECT s FROM Specialization s"),
    @NamedQuery(name = "Specialization.findBySpecId", query = "SELECT s FROM Specialization s WHERE s.specId = :specId"),
    @NamedQuery(name = "Specialization.findBySpecName", query = "SELECT s FROM Specialization s WHERE s.specName = :specName")})
public class Specialization implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specId")
    private List<Project> projectList;

    @JoinColumn(name = "fac_id", referencedColumnName = "fac_id")
    @ManyToOne(optional = false)
    private Faculty facId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "spec_id")
    private Integer specId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "spec_name")
    private String specName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specId")
    private List<Student> studentList;

    public Specialization() {
    }

    public Specialization(Integer specId) {
        this.specId = specId;
    }

    public Specialization(Integer specId, String specName) {
        this.specId = specId;
        this.specName = specName;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specId != null ? specId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specialization)) {
            return false;
        }
        Specialization other = (Specialization) object;
        if ((this.specId == null && other.specId != null) || (this.specId != null && !this.specId.equals(other.specId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Specialization[ specId=" + specId + " ]";
    }

    public Faculty getFacId() {
        return facId;
    }

    public void setFacId(Faculty facId) {
        this.facId = facId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
}
