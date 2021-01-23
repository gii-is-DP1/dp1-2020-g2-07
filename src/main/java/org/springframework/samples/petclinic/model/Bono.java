package org.springframework.samples.petclinic.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bonos")
public class Bono extends  BaseEntity {
	
	@Size(max = 12, message="Code must be 12 characters long or less")
	@Column(name = "codigo")
	private String codigo;
	
	@NotNull(message = "Price must be filled")
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_start;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_end;
	 
    @Size(min = 3, max = 100, message="Description must be beetwen 3 and 100 characters")
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "usado")
	private Boolean usado;

	 @OneToOne(mappedBy = "token")
	 private Sesion session;
	 
	public Bono() {
		super();
	}

	public Bono(String codigo, @NotNull(message = "Price must be filled") Integer precio, LocalDate date_start,
			LocalDate date_end,
			@Size(min = 3, max = 100, message = "Description must be beetwen 3 and 100 characters") String descripcion,
			Boolean usado, Sesion session) {
		super();
		this.codigo = codigo;
		this.precio = precio;
		this.date_start = date_start;
		this.date_end = date_end;
		this.descripcion = descripcion;
		this.usado = usado;
		this.session = session;
	}

	public Boolean getUsado() {
		return this.usado;
	}
		
	public void setUsado(Boolean usado) {
		this.usado=usado;
	}
	 
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
	    this.codigo= codigo;
	}

	public void setCodigo() {
	    this.codigo= getAlphaNumericString(12);
	}

	private String getAlphaNumericString(int n) { 
	    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                + "0123456789"
									+ "abcdefghijklmnopqrstuvxyz";
	  
		StringBuilder sb = new StringBuilder(n);

	    for (int i = 0; i < n; i++) { 
	        int index  = (int)(AlphaNumericString.length() * Math.random()); 
	  
	    	sb.append(AlphaNumericString.charAt(index)); 
		}
	  
	    return sb.toString(); 
	}
	
	public Integer getPrecio() {
		return this.precio;		
	}
	
	public void setPrecio(Integer precio) {
		this.precio=precio;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}

	public Sesion getSession() {
		return session;
	}

	public void setSession(Sesion session) {
		this.session = session;
	}

	public LocalDate getDate_start() {
		return date_start;
	}

	public LocalDate getDate_end() {
		return date_end;
	}

	public void setDate_start(LocalDate date_start) {
		this.date_start = date_start;
	}

	public void setDate_end(LocalDate date_end) {
		this.date_end = date_end;
	}

	@Override
	public String toString() {
		return  this.getCodigo();
	}
	
	
	
	
}