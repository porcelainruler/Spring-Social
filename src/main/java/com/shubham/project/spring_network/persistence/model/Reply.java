package com.shubham.project.spring_network.persistence.model;

import com.shubham.project.spring_network.constant.ReactionType;
import com.shubham.project.spring_network.constant.ReplyType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    //    @MapsId("id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = true)
    //    @MapsId("id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="comment_id", nullable = true, referencedColumnName = "id")
    //    @MapsId("id")
    private Comment comment;

    @Column(name = "message", nullable = false)
    private String message;

    @OneToMany(mappedBy = "reply")
    private Set<Reaction> reactions;

    @Enumerated(EnumType.STRING)
    @Column(name = "replyType")
    private ReplyType replyType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOfCreation")
    private Date dateOfCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateAt")
    private Date updatedAt;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_reply_id")
    private Reply parentReply;

    @OneToMany(mappedBy="parentReply")
    private Set<Reply> childReplies = new HashSet<Reply>();

    public Reply () {

    }

    public Reply(User user, Post post, String message, Set<Reaction> reactions, Reply parentReply, Set<Reply> childReplies, ReplyType replyType) {
        this.user = user;
        this.post = post;
        this.message = message;
        this.reactions = reactions;
        this.dateOfCreation = new Date();
        this.updatedAt = new Date();
        this.parentReply = parentReply;
        this.childReplies = childReplies;
        this.replyType = replyType;
    }

    public Reply(User user, Post post, String message, Set<Reaction> reactions, Date dateOfCreation, Date updatedAt, Reply parentReply, Set<Reply> childReplies) {
        this.user = user;
        this.post = post;
        this.message = message;
        this.reactions = reactions;
        this.dateOfCreation = dateOfCreation;
        this.updatedAt = updatedAt;
        this.parentReply = parentReply;
        this.childReplies = childReplies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ReplyType getReplyType() {
        return replyType;
    }

    public void setReplyType(ReplyType replyType) {
        this.replyType = replyType;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Reply getParentReply() {
        return parentReply;
    }

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
    }

    public Set<Reply> getChildReplies() {
        return childReplies;
    }

    public void setChildReplies(Set<Reply> childReplies) {
        this.childReplies = childReplies;
    }
}
