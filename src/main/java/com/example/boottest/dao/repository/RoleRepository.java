package com.example.boottest.dao.repository;

import com.example.boottest.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
