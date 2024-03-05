package com.example.springmvc.controller.api.admin.product;

import java.util.ArrayList;
import java.util.List;

import com.example.springmvc.dto.RangeDTO;
import com.example.springmvc.dto.request.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormSearchProduct extends PageDTO {
	private String name = "";
	private Boolean isActive;
	private List<Long> categoryIds;
	private Integer minPrice;
	private Integer maxPrice;
	private String orderBy;
	private Boolean isBestSeller;
	private Boolean isDiscouting;
	private List<RangeDTO> rangePrices = new ArrayList<RangeDTO>();
	
	
}
