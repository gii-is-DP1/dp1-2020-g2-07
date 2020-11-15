package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
@Table(name = "horario")
public class Horario extends BaseEntity{
	
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;
	
	@Column(name = "hora")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm:ss.SSSXXX")
//	para hacer un horario debería ser una lista de horas disponibles en el día
	private List<LocalTime> hora;
	
//	@OneToMany(Optional = false)
//	@JoinColumn(name = "empleado_id")
//	private Empleado empleado;
	
//	@OneToOne(Optional = false)
//	@JoinColumn(name = "cita_id")
//	private Cita cita;
	

}
