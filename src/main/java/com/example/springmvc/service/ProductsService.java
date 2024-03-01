package com.example.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springmvc.controller.api.admin.product.FormProduct;
import com.example.springmvc.dto.ProductsDTO;
import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.model.Categories;
import com.example.springmvc.model.Products;
import com.example.springmvc.repository.CategoriesRepository;
import com.example.springmvc.repository.ProductsRepository;

@Service
public class ProductsService {
	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ProductsDTO findById(Long id) {

		return modelMapper.map(productsRepository.findById(id).get(), ProductsDTO.class);
	}
	
	public ReqRes delete(Long id) {
		ReqRes reqRes = new ReqRes();
		Optional<Products> optional = productsRepository.findById(id);
		if (optional.isPresent()) {
			productsRepository.delete(optional.get());
			reqRes.setMessage("delete succses");
			reqRes.setStatusCode(200);
			return reqRes;
		}
		reqRes.setMessage("product not found");
		reqRes.setStatusCode(200);
		return reqRes;
	}

	public List<ProductsDTO> getAllProduct() {
		List<ProductsDTO> dtos = new ArrayList<>();
		List<Products> list = productsRepository.findAll();
		list.forEach(p -> {
			ProductsDTO dto = new ProductsDTO();
			dto = modelMapper.map(p, ProductsDTO.class);
			dtos.add(dto);

		});
		return dtos;
	}
	public List<ProductsDTO> getAllProductByCategory(String nameCategory) {
		List<ProductsDTO> dtos = new ArrayList<>();
		List<Products> list = productsRepository.findAllByCategory(nameCategory);
		list.forEach(p -> {
			ProductsDTO dto = new ProductsDTO();
			dto = modelMapper.map(p, ProductsDTO.class);
			dtos.add(dto);

		});
		return dtos;
	}

//	public List<Phones> findAllPhone() {
//		return phonesRepository.findAllPhone();
//	}

	public ReqRes createPhones(FormProduct productReq, BindingResult bindingResult) {
		ReqRes reqRes = new ReqRes();
		try {
			if (bindingResult.hasErrors()) {
				reqRes.setStatusCode(200);
				reqRes.setError(bindingResult.getFieldError().getDefaultMessage());
				return reqRes;
			}
			Optional<Categories> optional = categoriesRepository.findById(productReq.getCategory_id());
			if (optional.isPresent()) {
				Products product = new Products();
				product.setName(productReq.getName());
				product.setPrice(productReq.getPrice());
				product.setCategories(optional.get());
				productsRepository.save(product);
				reqRes.setStatusCode(200);
				reqRes.setMessage("add phone succses");

			} else {
				reqRes.setStatusCode(200);
				reqRes.setMessage("required id category");
			}

		} catch (Exception e) {
			// TODO: handle exception
			reqRes.setStatusCode(500);
			reqRes.setError("Error: " + e.getMessage());
		}

		return reqRes;
	}

	public ReqRes update(FormProduct productReq, BindingResult bindingResult) {
		ReqRes reqRes = new ReqRes();
		try {
			if (bindingResult.hasErrors()) {
				reqRes.setStatusCode(200);
				reqRes.setError(bindingResult.getFieldError().getDefaultMessage());
				return reqRes;
			}
			Optional<Categories> optional = categoriesRepository.findById(productReq.getCategory_id());
			Optional<Products> opProduct = productsRepository.findById(productReq.getId());
			if (opProduct.isPresent()) {
				Products products = opProduct.get();
				products.setCategories(optional.get());
				products.setName(productReq.getName());
				products.setPrice(productReq.getPrice());
				productsRepository.save(products);
				reqRes.setStatusCode(200);
				reqRes.setMessage("add phone succses");

			} else {
				reqRes.setStatusCode(200);
				reqRes.setMessage("required id category");
			}

		} catch (Exception e) {
			// TODO: handle exception
			reqRes.setStatusCode(500);
			reqRes.setError("Error: " + e.getMessage() + productReq.getName());
		}

		return reqRes;
	}

}
