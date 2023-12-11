package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
public class Comment {

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

    @Column(name = "message", nullable = false)
    private String message;

    @OneToMany(
        mappedBy = "comment",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Reaction> reactions;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOfCreation")
    private Date dateOfCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateAt")
    private Date updatedAt;

    @OneToMany(
        mappedBy = "comment",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Reply> replies;

    public Comment () {

    }

    public Comment(User user, Post post, String message, Set<Reaction> reactions, Set<Reply> replies) {
        this.user = user;
        this.post = post;
        this.message = message;
        this.reactions = reactions;
        this.dateOfCreation = new Date();
        this.updatedAt = new Date();
        this.replies = replies;
    }

    public Comment(User user, Post post, String message, Set<Reaction> reactions, Date dateOfCreation, Date updatedAt, Set<Reply> replies) {
        this.user = user;
        this.post = post;
        this.message = message;
        this.reactions = reactions;
        this.dateOfCreation = dateOfCreation;
        this.updatedAt = updatedAt;
        this.replies = replies;
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

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
