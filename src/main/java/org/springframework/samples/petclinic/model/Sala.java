package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private Integer aforo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy="salas")
	private List<Circuito> circuitos;
	

		

}
