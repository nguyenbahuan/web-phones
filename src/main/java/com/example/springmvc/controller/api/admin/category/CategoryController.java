package com.example.springmvc.controller.api.admin.category;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.dto.CategoriesDTO;
import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.model.Categories;
import com.example.springmvc.service.CategoriesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping()
	public ResponseEntity<List<CategoriesDTO>> getMethodName() {
		return ResponseEntity.ok(categoriesService.getAllCategories());
	}

	@PostMapping()
	public ResponseEntity<ReqRes> createCategory(@Valid @RequestBody FormCategory category, BindingResult bindingResult) {
		return ResponseEntity.ok(categoriesService.createCategories(mapper.map(category, Categories.class) , bindingResult));
	}
	
}
