package org.springframework.samples.petclinic.model;


import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "bonos")
public class Bono extends  BaseEntity {
	@Column(name = "codigo")
	 @NotEmpty
	 @Setter private String codigo;
	
	@Column(name = "precio")
	 @NotNull
	 @Setter private Integer precio;
	 
	 
	 @Column(name = "duracion")
	 @NotEmpty
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate duracion;
	 
	 @Column(name = "descripcion")
	 @NotEmpty
	 @Setter private String descripcion;


	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo=codigo;
	}
	
	public Integer getPrecio() {
		return this.precio;		
	}
	
	public void setPrecio(Integer precio) {
		this.precio=precio;
	}
	
	public LocalDate getDuracion() {
		return this.duracion;
	}
	
	public void setDuracion(LocalDate duracion) {
		this.duracion=duracion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
}