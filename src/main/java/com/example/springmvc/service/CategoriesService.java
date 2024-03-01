package com.example.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springmvc.dto.CategoriesDTO;
import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.model.Categories;
import com.example.springmvc.repository.CategoriesRepository;

@Service
public class CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Optional<Categories> findById(Long id) {
		return categoriesRepository.findById(id);
	}
	
	public List<CategoriesDTO> getAllCategories() {
		List<CategoriesDTO> categoriesDTOs = new ArrayList<>();
		List<Categories> categories = categoriesRepository.findAll();
		categories.forEach(c -> {
			CategoriesDTO categoriesDTO = new CategoriesDTO();
			categoriesDTOs.add(modelMapper.map(c, CategoriesDTO.class));
		});
		return categoriesDTOs;
	}

	public ReqRes createCategories(Categories categories, BindingResult bindingResult) {
		ReqRes reqRes = new ReqRes();
		try {
			if (bindingResult.hasErrors()) {
				reqRes.setMessage(bindingResult.getFieldError().getDefaultMessage());
				reqRes.setStatusCode(200);
			} else {
				categoriesRepository.save(categories);
				reqRes.setMessage("add succses");
				reqRes.setStatusCode(200);
			}
		} catch (Exception e) {
			// TODO: handle exception
			reqRes.setMessage(e.getMessage());
			reqRes.setStatusCode(500);
		}

		return reqRes;
	}
}
