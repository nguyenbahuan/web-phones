package com.example.springmvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
//	public Optional<Categories> findById(Long id);
}
