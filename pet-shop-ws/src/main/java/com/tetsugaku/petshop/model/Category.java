package com.tetsugaku.petshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "categories")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Representación de las categoría de los productos ofrecidos en la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Category extends AuditModel {
	
	private static final long serialVersionUID = -1525879617173796280L;
	
	@ApiModelProperty(notes="Identificador único de la categoría", position=0)    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ApiModelProperty(notes="Nombre de la categoría", position=1)
    @NotNull
    @Size(max = 100, message="Nombre debe ser de menos de 100 caracteres")
    @Column(unique = true)
	private String name;
	
	@ApiModelProperty(notes="Breve descripción de la categoría", position=2)
    @NotNull
    @Size(max = 250)
    private String description;

    @ApiModelProperty(notes="Url de la imagen para mostrar del producto", position=3, example="./images/category.png, http://images.io/category.png")
    @Size(max = 2000)
    private String urlImage;
    
}
