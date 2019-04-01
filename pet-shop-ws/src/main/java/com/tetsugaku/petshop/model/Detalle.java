package com.tetsugaku.petshop.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Producto producto;
	
	@ApiModelProperty(notes="Cantidad de items para el detalle", example="", position=1)
    @NotNull
    private int cantidad;
	
	@ApiModelProperty(notes="Valor unitario del producto en el detalle", example="", position=2)
    @NotNull
    private double valor;
	
	@ApiModelProperty(notes="Valor del impuesto aplicado en porcentaje", example="19.0", position=3)
    @NotNull
    private double impuesto;
	
	@ApiModelProperty(notes="Valor total del item en la factura", example="", position=4)
    private double total;
	
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
