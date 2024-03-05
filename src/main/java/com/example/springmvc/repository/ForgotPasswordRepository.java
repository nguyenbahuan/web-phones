package com.example.springmvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springmvc.model.ForgotPassword;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

	Optional<ForgotPassword> findByToken(String token);
}
