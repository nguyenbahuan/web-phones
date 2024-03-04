package com.example.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
