package com.tetsugaku.petshop.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "facturas_detalles")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Modelo para gestión de detalle de facturación de la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Detalle extends AuditModel {

	private static final long serialVersionUID = -3244255943529546834L;
    
	@ApiModelProperty(notes="Identificador único del detalle en la factura", position=0)  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ApiModelProperty(notes="Producto de venta en el detalle", example="", position=1)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable=false)
    private Product producto;
	
	@ApiModelProperty(notes="Cantidad de items para el detalle", example="", position=1)
    @NotNull
    private Long cantidad;
	
	@ApiModelProperty(notes="Valor unitario del producto en el detalle", example="", position=2)
    @NotNull
    private Long valor;
	
	@ApiModelProperty(notes="Valor del impuesto aplicado en porcentaje", example="19.0", position=3)
    private Double impuesto;
	
	@ApiModelProperty(notes="Valor total del item en la factura", example="", position=4)
    private Double total;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id")
	private Factura factura;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detalle )) return false;
        return id != null && id.equals(((Detalle) o).id);
    }
	
}
