package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "salas")
public class Sala extends NamedEntity{
	
	@Column(name = "aforo")
	@NotNull
	private Integer aforo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy="salas", cascade = CascadeType.ALL)
	private List<Circuito> circuitos;

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(List<Circuito> circuitos) {
		this.circuitos = circuitos;
	}
	
	
		

}
