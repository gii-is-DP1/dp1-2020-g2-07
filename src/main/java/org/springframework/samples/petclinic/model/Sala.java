package org.springframework.samples.petclinic.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "salas")
public class Sala extends NamedEntity{

	
    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType room_type;
  
	@Column(name = "aforo")
	@NotNull(message = "Capacity can't be null")
	@Min(value = 1, message = "The room must have 1 person capacity at least.")
	private Integer aforo;
	
	@Size(min=10, message="The description needs 10 letters")
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy="salas", cascade = CascadeType.ALL)
	private List<Circuito> circuitos;
	
	@OneToMany(mappedBy="sala", cascade = CascadeType.ALL)
	private List<Sesion> sesiones;

	public Sala() {
		super();
	}

	public Sala(RoomType room_type,
			@NotNull(message = "Capacity can't be null") @Min(value = 1, message = "The room must have 1 person capacity at least.") Integer aforo,
			@Size(min = 10, message = "The description needs 10 letters") String descripcion, List<Circuito> circuitos,
			List<Sesion> sesiones) {
		super();
		this.room_type = room_type;
		this.aforo = aforo;
		this.descripcion = descripcion;
		this.circuitos = circuitos;
		this.sesiones = sesiones;
	}

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

	public RoomType getRoom_type() {
		return room_type;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setRoom_type(RoomType room_type) {
		this.room_type = room_type;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}	
}
