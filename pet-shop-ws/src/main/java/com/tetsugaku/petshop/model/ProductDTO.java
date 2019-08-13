package com.tetsugaku.petshop.model;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private Long price;
	
	private String image;
	
	private Long quantity;
	
	private Object data;

	public Double total() {		
		if(price!= null && quantity != null) {
			return (double) (price*quantity); 
		}else {
			return 0.0;
		} 
	}
	
}
