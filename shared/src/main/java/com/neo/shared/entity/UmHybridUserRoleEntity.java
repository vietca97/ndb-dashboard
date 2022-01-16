package com.neo.shared.entity;

import javax.persistence.*;

@Entity
@Table(name = "UM_HYBRID_USER_ROLE", schema = "shared_db")
public class UmHybridUserRoleEntity {
    @Id
    @Column(name="UM_ID")
    private Long id;

    @Column(name="UM_USER_NAME")
    private String username;

    @Column(name="UM_ROLE_ID")
    private Long role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
