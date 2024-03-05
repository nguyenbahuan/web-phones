package com.example.springmvc.dto.request;

import lombok.Getter;

@Getter
public class PageDTO {
	private Integer perPage = 10;
	private Integer page = 1;

	public Integer getOffset() {
		return (page - 1) * perPage;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	
}
