package com.tetsugaku.petshop.model;

import java.util.List;

import lombok.Data;

@Data
public class FacturaDTO {
	
	
	private Double taxRate;
	
	private Double shipping;
	
	private List<ProductDTO> items;
	
	
}
