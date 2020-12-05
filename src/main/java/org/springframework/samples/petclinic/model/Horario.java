package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Table(name = "horario")
public class Horario extends BaseEntity{
	
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;
	
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "horario")
//	private Sesion sesion;


	public LocalDate getFecha() {
		return fecha;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public void setEmployee_id(Employee employee) {
		this.employee = employee;
	}



	
	
	

}
