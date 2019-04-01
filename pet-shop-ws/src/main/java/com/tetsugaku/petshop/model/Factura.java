package com.tetsugaku.petshop.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "facturas")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Modelo para gestión de facturación de la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Factura extends AuditModel {
	
	private static final long serialVersionUID = 308748260963258068L;

	@ApiModelProperty(notes="Identificador único de la factura", position=0)    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ApiModelProperty(notes="Cliente de la factura", position=1)
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JsonIgnore
	private Cliente cliente;
	
	@ApiModelProperty(notes="Fecha y hora de factura", position=2)
	@NotNull    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)    
	private Date fecha;
	
	@ApiModelProperty(notes="Valor total de la factura", position=4)
    @NotNull
    @Column(nullable=false)
    private Double total;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true) 
	private Collection<Detalle> detalles;
	
	public void addDetalle(Detalle detalle) {
        detalles.add(detalle);
        detalle.setFactura(this);
    }
	
	public void removeDetalle(Detalle detalle) {
        detalles.remove(detalle);
        detalle.setFactura(null);
    }
	
}




