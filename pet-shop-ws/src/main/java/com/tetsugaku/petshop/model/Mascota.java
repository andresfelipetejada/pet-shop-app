package com.tetsugaku.petshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "mascotas")

@Data
@EqualsAndHashCode(callSuper=false)

@ApiModel(description="Representación de las mascotas de la tienda")
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Mascota extends AuditModel {

	private static final long serialVersionUID = -922744416486351556L;

	@ApiModelProperty(notes="Identificador único del cliente", position=0)    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ApiModelProperty(notes="Nombre de la mascota", position=3)
    @NotNull
    @Size(max = 250)
	private String nombre;
	
	@ApiModelProperty(notes="Sexo", example="Macho, Hembre", position=3)
    @NotNull
    @Size(max = 20)
	private String sexo;
	
	@ApiModelProperty(notes="Especie animal", example="Perro, Gato, Ave, ...", position=3)
    @NotNull
    @Size(max = 20)
	private String especie;
	
	@ApiModelProperty(notes="Fecha de nacimiento", example="", position=3)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	
	@ApiModelProperty(notes="Cliente responsable de la mascota", example="", position=4)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;
	
}
