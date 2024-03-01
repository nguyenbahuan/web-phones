package com.example.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springmvc.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{
	
	@Query("SELECT p FROM Products p LEFT JOIN Categories c On p.id = c.id WHERE c.name = :nameCategory")
	List<Products> findAllByCategory(@Param("nameCategory") String nameCategory);

}
