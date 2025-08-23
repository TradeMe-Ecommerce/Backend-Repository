package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id", length = 64)
    private long historyId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public History() {
        initializeNecessaryFields();
    }

    public History(long historyId, User user) {
        this.historyId = historyId;
        this.user = user;
        initializeNecessaryFields();
    }

    public void initializeNecessaryFields() {
        this.orders = new HashSet<>();
    }


    public long getId() {
        return historyId;
    }

    public void setId(long historyId) {
        this.historyId = historyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

