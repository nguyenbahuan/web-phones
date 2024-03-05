package com.example.springmvc.controller.api.admin.product;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.dto.PhonesDTO;
import com.example.springmvc.dto.ProductsDTO;
import com.example.springmvc.dto.ReqRes;
import com.example.springmvc.model.Products;
import com.example.springmvc.service.CategoriesService;
import com.example.springmvc.service.ProductsService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

	@Autowired
	private ProductsService productsService;

	@GetMapping("get-all")
	public List<ProductsDTO> getPhones() {
		return productsService.getAllProduct();
	}

	@GetMapping()
	public List<ProductsDTO> getProductByCategory(
			@RequestParam(name = "category", required = false) String nameCategory) {
		return productsService.getAllProductByCategory(nameCategory);
	}

	@GetMapping(path = "{productId}")
	public ProductsDTO getPhoneById(@PathVariable(name = "productId") Long id) {
		return productsService.findById(id);
	}

	@PostMapping()
	public ResponseEntity<ReqRes> createProduct(@Valid @RequestBody FormProduct product, BindingResult bindingResult) {
		// TODO: process POST request

		return ResponseEntity.ok(productsService.createPhones(product, bindingResult));
	}

	@PutMapping(path = "update/{productId}")
	public ResponseEntity<ReqRes> updateProduct(@PathVariable(name = "productId") Long id,
			@Valid @RequestBody FormProduct product, BindingResult bindingResult) {
		// TODO: process POST request
		product.setId(id);
		return ResponseEntity.ok(productsService.update(product, bindingResult));
	}

	@DeleteMapping(path = "delete/{productId}")
	public ResponseEntity<ReqRes> deleteProduct(@PathVariable(name = "productId") Long id) {
		// TODO: process POST request

		return ResponseEntity.ok(productsService.delete(id));
	}

}
