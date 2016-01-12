/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmuminiproject.model;

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

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByUserTel", query = "SELECT u FROM User u WHERE u.userTel = :userTel"),
    @NamedQuery(name = "User.findByUserlastSignIn", query = "SELECT u FROM User u WHERE u.userlastSignIn = :userlastSignIn"),
    @NamedQuery(name = "User.findByUserStatus", query = "SELECT u FROM User u WHERE u.userStatus = :userStatus")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 45)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 45)
    @Column(name = "user_email")
    private String userEmail;
    @Size(max = 45)
    @Column(name = "user_tel")
    private String userTel;
    @Size(max = 45)
    @Column(name = "user_lastSignIn")
    private String userlastSignIn;
    @Size(max = 45)
    @Column(name = "user_status")
    private String userStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uSERuserid1")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uSERuserid")
    private List<Admin> adminList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uSERuserid")
    private List<Lecturer> lecturerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uSERuserid")
    private List<Comment> commentList;
    @JoinColumn(name = "ROLE_type_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Role rOLEtypeid;

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

    public String getUserlastSignIn() {
        return userlastSignIn;
    }

    public void setUserlastSignIn(String userlastSignIn) {
        this.userlastSignIn = userlastSignIn;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    @XmlTransient
    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Role getROLEtypeid() {
        return rOLEtypeid;
    }

    public void setROLEtypeid(Role rOLEtypeid) {
        this.rOLEtypeid = rOLEtypeid;
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
        return "com.mmuminiproject.model.User[ userId=" + userId + " ]";
    }
    
}
