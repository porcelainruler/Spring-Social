package com.shubham.project.spring_network.persistence.model;

import com.shubham.project.spring_network.constant.Rating;
import com.shubham.project.spring_network.constant.ReactionType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
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
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="reply_id", nullable = true, referencedColumnName = "id")
    private Reply reply;

    @Enumerated(EnumType.STRING)
    @Column(name = "reactionType")
    private ReactionType reactionType;


    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    public Reaction () {

    }

    public Reaction(User user, Post post, Rating rating, Comment comment, Reply reply, ReactionType reactionType) {
        this.user = user;
        this.post = post;
        this.rating = rating;
        this.comment = comment;
        this.reply = reply;
        this.reactionType = reactionType;
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}


//@Entity
//public class Reaction {
//
//    private ReactionId id;
//    private Post post;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "rating")
//    private Rating rating;
//
//    public Reaction () {
//
//    }
//
//    public Reaction(Post post, Rating rating) {
//        this.post = post;
//        this.rating = rating;
//    }
//
//    @EmbeddedId
//    public ReactionId getId() {
//        return id;
//    }
//
//    @EmbeddedId
//    public void setId(ReactionId id) {
//        this.id = id;
//    }
//
//    @ManyToOne
//    @JoinColumn(name="postId")
//    @MapsId("postId")
//    public Post getPost() {
//        return post;
//    }
//
//    @ManyToOne
//    @JoinColumn(name="postId")
//    @MapsId("postId")
//    public void setPost(Post post) {
//        this.post = post;
//    }
//
//    public Rating getRating() {
//        return rating;
//    }
//
//    public void setRating(Rating rating) {
//        this.rating = rating;
//    }
//}

//@Embeddable
//class ReactionId implements Serializable {
//    private static final long serialVersionUID = -6437671620548723631L;
//
//    @Column(name = "postId")
//    private long postId;
//    @Column(name = "userId")
//    private long userId;
//
//    public long getPostId () {
//        return postId;
//    }
//
//    public void setPostId (long postId) {
//        this.postId = postId;
//    }
//
//    public long getUserId () {
//        return userId;
//    }
//
//    public void setUserId (long userId) {
//        this.userId = userId;
//    }
//}
