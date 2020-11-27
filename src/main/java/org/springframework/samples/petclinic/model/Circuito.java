package org.springframework.samples.petclinic.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name="circuitos")
public class Circuito extends NamedEntity {
	
	 @JoinTable(
		        name = "rel_circuito_salas",
		        joinColumns = @JoinColumn(name="FK_Circuito"),
		        inverseJoinColumns = @JoinColumn(name="FK_Sala")
		    )

	@NotEmpty
	@ManyToMany
//	@Size(min = 2)
	private List<Sala> salas;
	
	private Integer aforo;
	
	@Size(min = 10, max = 1024)
	@Column(name = "descripcion", length=1024)
	private String descripcion;
		

	public Sala getSala(String name) {
		return getSala(name);
	}

	public void addSala(Sala s){
        salas.add(s);
    }
	
	public void addSalas(List<Sala> s){
        salas.addAll(s);
    }
	
		
}
