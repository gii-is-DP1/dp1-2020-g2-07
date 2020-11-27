package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "citas")
public class Cita extends BaseEntity{
	
	
	@Column(name = "horario")
	@NotEmpty
	//private Horario horario
	private String horario;
	
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	private Sala sala;
}
