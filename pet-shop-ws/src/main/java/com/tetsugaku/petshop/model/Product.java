package com.tetsugaku.petshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "products")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Representación de productos ofrecidos en la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Product extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2797770578972264730L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position=1, notes="Identificador único del producto")
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
	
	@ApiModelProperty(position=2, notes="Nombre del producto")
    @NotNull
    @Size(max = 100, message="Nombre debe ser de menos de 100 caracteres")
    @Column(unique = true)
	private String name;
	
	@ApiModelProperty(position=3, notes="SKU (stock-keeping unit) es un identificador único que ayuda en la gestión de los productos y del stock")
    @NotNull
    @Size(max = 100)
	@Column(unique = true)
	private String sku;
	
	@ApiModelProperty(position=4, notes="Breve descripción del producto")
    @NotNull
    @Size(max = 2000)
	private String description;
	
	@ApiModelProperty(position=5, notes="Precio de venta para el producto")
    @NotNull
	private Long price;
	
	@ApiModelProperty(position=6, notes="Cantidad de inventario del producto")
    @NotNull
	private Long stock;
	
	@ApiModelProperty(position=7, notes="Imagen del producto")
	@Size(max = 2000)
	private String image;

}
