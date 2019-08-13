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
@Table(name = "clientes")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Representación de los clientes de la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Client extends AuditModel {

	private static final long serialVersionUID = 1107106926236258367L;
	
	@ApiModelProperty(notes="Identificador único del cliente", position=0)    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ApiModelProperty(notes="Cédula del cliente", position=1)
    @NotNull
    @Column(unique=true, nullable=false)
    @Size(max = 20, message="Cédula debe ser de menos de 20 caracteres")
    private String cedula;
	
	@ApiModelProperty(notes="Nombres del cliente", position=2)
    @NotNull
    @Column(nullable=false)
    @Size(max = 200, message="Nombre debe ser de menos de 200 caracteres")
    private String nombres;
	
	@ApiModelProperty(notes="Apellidos del cliente", position=3)
    @NotNull
    @Column(nullable=false)
    @Size(max = 200, message="Apellidos debe ser de menos de 200 caracteres")
    private String apellidos;
	
	@ApiModelProperty(notes="Teléfono de cliente", position=4)
    @NotNull
    @Size(max = 20, message="Teléfono debe ser de menos de 20 caracteres")
    private String telefono;
	
	@ApiModelProperty(notes="Email de cliente", position=5)
    @NotNull
    @Size(max = 500, message="Email debe ser de menos de 500 caracteres")
    private String email;
	
}
