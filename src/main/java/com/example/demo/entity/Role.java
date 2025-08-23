package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", length = 64)
    private long roleId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "permit_listing",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @JsonIgnore
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

    public Role() {
        initializeNecessaryFields();
    }

    public Role(long roleId, String name, String description) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        initializeNecessaryFields();
    }

    public void initializeNecessaryFields() {
        this.permissions = new HashSet<>();
        this.users = new HashSet<>();
    }

    public long getId() {
        return roleId;
    }

    public void setId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
