package com.exalt.petclinic.dto;

import java.util.List;

public class PageDto<T> {
	private List<T> content;
	private long totalElements;
	private int numberOfElements;



	public PageDto(List<T> content, long totalElements, int numberOfElements) {
		super();
		this.content = content;
		this.totalElements = totalElements;
		this.numberOfElements = numberOfElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

}
