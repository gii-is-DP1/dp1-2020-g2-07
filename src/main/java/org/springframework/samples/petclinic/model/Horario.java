package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "horario")
public class Horario extends BaseEntity{
	
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;
	
	@Column(name="hora_ini")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaIni;
	
	@Column(name="hora_fin")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaFin;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="sala_id")
	private Sala sala;


	public LocalTime getHoraIni() {
		return horaIni;
	}


	public void setHoraIni(LocalTime horaIni) {
		this.horaIni = horaIni;
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


	public LocalDate getFecha() {
		return fecha;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	

}