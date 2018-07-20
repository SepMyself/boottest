package com.example.boottest.dao.entity;

//import sun.util.resources.Bundles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
//@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> userList;

    public Role(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }
}
