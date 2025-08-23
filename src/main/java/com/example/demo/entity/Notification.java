package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notifications_id", length = 128)
    private long notificationsId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "description", nullable = false, length = 264)
    private String description;

    public Notification() {
    }

    public Notification(long notificationsId, User user, Date date, String description) {
        this.notificationsId = notificationsId;
        this.user = user;
        this.date = date;
        this.description = description;
    }

    public long getId() {
        return notificationsId;
    }

    public void setId(long notificationsId) {
        this.notificationsId = notificationsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

