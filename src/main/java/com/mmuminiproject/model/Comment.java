/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmuminiproject.model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruceoutdoors
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentId", query = "SELECT c FROM Comment c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comment.findByCommectDescription", query = "SELECT c FROM Comment c WHERE c.commectDescription = :commectDescription")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "comment_id")
    private Integer commentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "commect_description")
    private String commectDescription;
    @JoinColumn(name = "USER_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User uSERuserid;

    public Comment() {
    }

    public Comment(Integer commentId) {
        this.commentId = commentId;
    }

    public Comment(Integer commentId, String commectDescription) {
        this.commentId = commentId;
        this.commectDescription = commectDescription;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommectDescription() {
        return commectDescription;
    }

    public void setCommectDescription(String commectDescription) {
        this.commectDescription = commectDescription;
    }

    public User getUSERuserid() {
        return uSERuserid;
    }

    public void setUSERuserid(User uSERuserid) {
        this.uSERuserid = uSERuserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmuminiproject.model.Comment[ commentId=" + commentId + " ]";
    }
    
}
