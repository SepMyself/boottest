package com.example.boottest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 3L;

    //@Id
    //private UserRoleEmbeddable key;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public UserRoleEmbeddable getKey() {
//        return key;
//    }
//
//    public void setKey(UserRoleEmbeddable key) {
//        this.key = key;
//    }

}
