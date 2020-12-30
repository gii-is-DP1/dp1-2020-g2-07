package org.springframework.samples.petclinic.model;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.repository.SalaRepository;

@Entity
@Table(name = "salas")
public class Sala extends NamedEntity{
	
	@ManyToMany(mappedBy = "salas",cascade = CascadeType.ALL)
    private Set<Bono> bonos;
		
	@Column(name = "aforo")
	@NotNull(message = "Capacity can't be null")
	@Min(value = 1, message = "The room must have 1 person capacity at least.")
	private Integer aforo;
	
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy="salas", cascade = CascadeType.ALL)
	private List<Circuito> circuitos;
	
	@OneToMany(mappedBy="sala", cascade = CascadeType.ALL)
	private List<Sesion> sesiones;

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
