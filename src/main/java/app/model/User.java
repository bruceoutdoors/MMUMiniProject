/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import static com.opensymphony.xwork2.Action.INPUT;
import core.DB;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role_id", discriminatorType = DiscriminatorType.INTEGER)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByUserTel", query = "SELECT u FROM User u WHERE u.userTel = :userTel"),
    @NamedQuery(name = "User.findByUserActive", query = "SELECT u FROM User u WHERE u.userActive = :userActive"),
    @NamedQuery(name = "User.findByUserlastSignIn", query = "SELECT u FROM User u WHERE u.userlastSignIn = :userlastSignIn")})
public class User implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_active")
    private Boolean userActive = true;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 45)
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @Size(max = 45)
    @Column(name = "user_email")
    private String userEmail;
    @Size(max = 45)
    @Column(name = "user_tel")
    private String userTel;
    @Column(name = "user_lastSignIn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userlastSignIn;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Admin admin;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Lecturer lecturer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Comment> commentList;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Role roleId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Date getUserlastSignIn() {
        return userlastSignIn;
    }

    public void setUserlastSignIn(Date userlastSignIn) {
        this.userlastSignIn = userlastSignIn;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @XmlTransient
    public List<Comment> getcommentList() {
        return commentList;
    }

    public void setcommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Role getRoleId() {
        return roleId;
    }

    protected void setRoleId(Role roleId) {
        this.roleId = roleId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.User[ userId=" + userId + " ]";
    }

    public Boolean getUserActive() {
        return userActive;
    }

    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public Boolean isAdmin() {
        return roleId.getRoleId() == 0;
    }
    
    public Boolean isStudent() {
        return roleId.getRoleId() == 2;
    }
    
    public Boolean isLecturer() {
        return roleId.getRoleId() == 1;
    }

}
