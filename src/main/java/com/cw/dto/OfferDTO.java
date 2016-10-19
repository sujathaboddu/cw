package com.cw.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class OfferDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String name;
	private String description;
	private BigDecimal price;
	private String currency;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
		
}
