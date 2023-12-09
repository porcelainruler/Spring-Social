package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "likeCount")
    private long likeCount;

    @Column(name = "laughCount")
    private long laughCount;

    @Column(name = "heartCount")
    private long heartCount;

    @Column(name = "angryCount")
    private long angryCount;

    @Column(name = "supportCount")
    private long supportCount;

    public Rating () {

    }

    public Rating(long likeCount, long laughCount, long heartCount, long angryCount, long supportCount) {
        this.likeCount = likeCount;
        this.laughCount = laughCount;
        this.heartCount = heartCount;
        this.angryCount = angryCount;
        this.supportCount = supportCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getLaughCount() {
        return laughCount;
    }

    public void setLaughCount(long laughCount) {
        this.laughCount = laughCount;
    }

    public long getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(long heartCount) {
        this.heartCount = heartCount;
    }

    public long getAngryCount() {
        return angryCount;
    }

    public void setAngryCount(long angryCount) {
        this.angryCount = angryCount;
    }

    public long getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(long supportCount) {
        this.supportCount = supportCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id && likeCount == rating.likeCount && laughCount == rating.laughCount && heartCount == rating.heartCount && angryCount == rating.angryCount && supportCount == rating.supportCount;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", likeCount=" + likeCount +
                ", laughCount=" + laughCount +
                ", heartCount=" + heartCount +
                ", angryCount=" + angryCount +
                ", supportCount=" + supportCount +
                '}';
    }
}
