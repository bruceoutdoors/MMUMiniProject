/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjectId", query = "SELECT p FROM Project p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "Project.findByProjectTitle", query = "SELECT p FROM Project p WHERE p.projectTitle = :projectTitle"),
    @NamedQuery(name = "Project.findByProjectGrade", query = "SELECT p FROM Project p WHERE p.projectGrade = :projectGrade"),
    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Project.findByDueDate", query = "SELECT p FROM Project p WHERE p.dueDate = :dueDate"),
    @NamedQuery(name = "Project.findBySubDate", query = "SELECT p FROM Project p WHERE p.subDate = :subDate"),
    @NamedQuery(name = "Project.findByProjectDescription", query = "SELECT p FROM Project p WHERE p.projectDescription = :projectDescription"),
    @NamedQuery(name = "Project.findByEvaComment", query = "SELECT p FROM Project p WHERE p.evaComment = :evaComment")})
public class Project implements Serializable {

    @Column(name = "project_active")
    private Boolean projectActive = true;

    @Size(max = 300)
    @Column(name = "project_file")
    private String projectFile;

    @JoinColumn(name = "spec_id", referencedColumnName = "spec_id")
    @ManyToOne(optional = false)
    private Specialization specId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<Comment> commentList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id", unique = true)
    private Integer projectId;
    @Size(max = 300)
    @Column(name = "project_title")
    private String projectTitle;
    @Size(max = 2)
    @Column(name = "project_grade")
    private String projectGrade;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate = new Date();
    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name = "sub_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subDate;
    @Column(name = "project_description")
    private String projectDescription;
    @Column(name = "eva_comment")
    private String evaComment;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Lecturer lecturerId;
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @OneToOne(optional = true)
    private Student studentId;

    public static enum statusEnum {
        INACTIVE,
        UNASSIGNED,
        ASSIGNED,
        SUBMITTED,
        EVALUATED,
        OVERDUED
    }

    public statusEnum getStatus() {
        if (projectActive == false) {
            return statusEnum.INACTIVE;
        }

        if (studentId == null) {
            return statusEnum.UNASSIGNED;
        }

        if (subDate != null) {
            if (projectGrade != null) {
                return statusEnum.EVALUATED;
            }

            return statusEnum.SUBMITTED;
        }

        if (dueDate.before(new Date())) {
            return statusEnum.OVERDUED;
        }

        return statusEnum.ASSIGNED;
    }

    public String getShortDescription() {
        String shortDesc = projectDescription.replaceAll("\\<.*?>", "");
        if (projectDescription.length() > 400) {
            return shortDesc.substring(0, 400) + "...";
        } else {
            return shortDesc;
        }
    }

    public Boolean isComplete() {
        return subDate != null;
    }

    public Project() {
    }

    public Project(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(String projectGrade) {
        this.projectGrade = projectGrade;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getEvaComment() {
        return evaComment;
    }

    public void setEvaComment(String evaComment) {
        this.evaComment = evaComment;
    }

    public Lecturer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Lecturer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;

        if (studentId != null) {
            if (studentId.getProjectList() == null) return;
            studentId.getProjectList().add(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Project[ projectId=" + projectId + " ]";
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Specialization getSpecId() {
        return specId;
    }

    public void setSpecId(Specialization specId) {
        this.specId = specId;
    }

    public String getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(String projectFile) {
        this.projectFile = projectFile;
    }

    public Boolean getProjectActive() {
        return projectActive;
    }

    public void setProjectActive(Boolean projectActive) {
        this.projectActive = projectActive;
    }

}
