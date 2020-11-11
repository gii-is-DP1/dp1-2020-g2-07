package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "salas")
public class Sala extends NamedEntity{
	
	@Column(name = "horario")
	@NotEmpty
	//private Horario horario
	private String horario;
	
	@Column(name = "empleado")
	@NotEmpty
	//private Empleado empleado
	private String empleado;
	
	@Column(name = "aforo")
	@NotEmpty
	private Integer aforo;
	
	@Column(name = "descripcion")
	private String descripcion;

	

}
