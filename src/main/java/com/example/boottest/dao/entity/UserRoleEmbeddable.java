package com.example.boottest.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRoleEmbeddable implements Serializable {
    private static final long serialVersionUID = 4L;

    private Integer userId;
    private Integer roleId;

    public UserRoleEmbeddable(){}

    public UserRoleEmbeddable(Integer uid, Integer rid){
        this.userId = uid;
        this.roleId = rid;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof UserRoleEmbeddable){
            UserRoleEmbeddable key = (UserRoleEmbeddable)o;
            if(this.userId.equals(key.getUserId()) && this.roleId.equals(key.getRoleId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (this.getUserId() + "" + this.getRoleId()).hashCode();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

