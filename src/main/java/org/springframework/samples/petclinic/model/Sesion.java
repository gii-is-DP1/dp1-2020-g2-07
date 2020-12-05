package org.springframework.samples.petclinic.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name = "sesion")
public class Sesion extends BaseEntity{

	@Column(name = "horaInicio")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm:ss.SSSXXX")
	private LocalTime horaInicio;
	
	@Column(name = "horaFin")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm:ss.SSSXXX")
	private LocalTime horaFin;
	
	@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;
	
	@ManyToOne
	@JoinColumn(name = "horario_id")
	private Horario horario;

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
