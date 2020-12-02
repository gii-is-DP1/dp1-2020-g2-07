package org.springframework.samples.petclinic.model;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "bonos")
public class Bono extends  BaseEntity {
	
	 @JoinTable(
		        name = "rel_bonos_salas",
		        joinColumns = @JoinColumn(name="FK_Bono"),
		        inverseJoinColumns = @JoinColumn(name="FK_Sala")
		    )
	
	@ManyToMany
    private Set<Sala> salas;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "precio")
	private Integer precio;
	 
	 @Column(name = "duracion")
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate duracion;
	 
	 @Column(name = "descripcion")
	 private String descripcion;

	 @Column(name = "usado")
	 private Boolean usado;


	 public Boolean getUsado() {
		return this.usado;
	}
		
	public void setUsado(Boolean usado) {
		this.usado=usado;
	}
	 
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo() {
	    this.codigo= getAlphaNumericString(8);
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