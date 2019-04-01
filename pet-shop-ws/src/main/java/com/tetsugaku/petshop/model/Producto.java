package com.tetsugaku.petshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "productos")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Representación de productos ofrecidos en la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Producto extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8278370984643981597L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes="Identificador único del producto", position=1)
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnore
    private Categoria categoria;
	
	@ApiModelProperty(notes="Nombre del producto", position=2)
    @NotNull
    @Size(max = 100, message="Nombre debe ser de menos de 100 caracteres")
    @Column(unique = true)
	private String nombre;
	
	@ApiModelProperty(notes="Breve descripción del producto", position=3)
    @NotNull
    @Size(max = 250)
    private String descripcion;
	
    @ApiModelProperty(notes="Ficha con información más detallada del producto", position=4)
    @Lob
    private String ficha;
	
    @ApiModelProperty(notes="Url de la imagen para mostrar del producto", position=5, example="./images/producto.png, http://images.io/producto.png")
    @Size(max = 250)
    private String urlImagen;
	
    @ApiModelProperty(notes="Precio de venta para el producto", position=6)
    @NotNull
    private Double precio;
    
    @ApiModelProperty(notes="Cantidad de inventario del producto", position=7)
    @NotNull
    private Long cantidad;
    
}
