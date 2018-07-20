//package com.example.boottest.dao.entity;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//
//@Entity
//@Table(name = "user_role")
////@IdClass(UserRoleEmbeddable.class)
//public class UserRole implements Serializable {
//    private static final long serialVersionUID = 3L;
//
//    @EmbeddedId
//    private UserRoleEmbeddable key;
//
//    //@Id
//    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.MERGE)
//    //@JoinColumn(name = "user_id")
//    private User user;
//    //private Integer userId;
//
//    //@Id
//    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = CascadeType.MERGE)
//    //@JoinColumn(name = "role_id")
//    private Role role;
//    //private Integer roleId;
//
//    public UserRole(){}
//
//    public UserRole(User user, Role role){
//        this.user = user;
//        this.role = role;
//    }
//
//    @Transient
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @Transient
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public UserRoleEmbeddable getKey() {
//        return key;
//    }
//
//    public void setKey(UserRoleEmbeddable key) {
//        this.key = key;
//    }
//}
