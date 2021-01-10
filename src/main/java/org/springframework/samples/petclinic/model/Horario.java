package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "horario")
public class Horario extends BaseEntity{
	
	@NotNull(message = "Date can't be null")
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	@OneToMany(mappedBy = "horario",cascade=CascadeType.ALL)
	private List<Sesion> sesiones;
	
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

    public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	public void addSesion(Sesion s){
        this.sesiones.add(s);
    }
	
}