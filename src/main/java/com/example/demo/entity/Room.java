package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id", length = 128)
    private long roomId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "peer_user_id", nullable = false)
    private User peerUser;

    public Room() {
        initializeNecessaryFields();
    }

    public Room(long roomId, User user) {
        this.roomId = roomId;
        this.user = user;
        initializeNecessaryFields();
    }

    public void setPeerUser(User peerUser) {
        this.peerUser = peerUser;
    }

    public User getPeerUser() {
        return peerUser;
    }

    public void initializeNecessaryFields() {
        this.messages = new HashSet<>();
    }

    public long getId() {
        return roomId;
    }

    public void setId(long roomId) {
        this.roomId = roomId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}