package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.service.BonoService;

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

	@Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_start;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_end;
	 
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
	
	/*public void setCodigo(BonoService bs) {
	    this.codigo= getAlphaNumericString(12, true, bs);
	}
	private String getAlphaNumericString(int n, Boolean verifyUnique, BonoService bs) { 
	    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                + "0123456789"
									+ "abcdefghijklmnopqrstuvxyz";
	  
		StringBuilder sb = new StringBuilder(n);
		Boolean isUnique = false;
		while (!isUnique) {
			sb.setLength(n);
	    	for (int i = 0; i < n; i++) { 
	        	int index  = (int)(AlphaNumericString.length() * Math.random()); 
	  
	    		sb.append(AlphaNumericString.charAt(index)); 
			}
			
			isUnique = !bs.findBonos().stream()
				.anyMatch(b -> b.getCodigo() == sb.toString());
			sb.setLength(0);
		}
	  
	    return sb.toString(); 
	}*/
	
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
}