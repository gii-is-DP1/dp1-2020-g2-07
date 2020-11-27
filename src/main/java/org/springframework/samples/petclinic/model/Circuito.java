package org.springframework.samples.petclinic.model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

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
	private List<Sala> salas;
	
//	@Column(name="aforo")
//	@NotNull
	private Integer aforo;
	

	@Size(min = 10, max = 1024)
	@Column(name = "descripcion", length=1024)

	private String descripcion;
		
	
//	protected List<Sala> getSalasInternal() {
//		if (this.salas == null) {
//			this.salas = new ArrayList<Sala>();
//		}
//		return this.salas;
//	}
//	protected void setSalasInternal(List<Sala> salas) {
//		this.salas = salas;
//	}
	
//	public List<Sala> getSalas() {
//		List<Sala> salas = new ArrayList<>(getSalasInternal());
//		PropertyComparator.sort(salas, new MutableSortDefinition("name", true, true));
//		return Collections.unmodifiableList(salas);
//	}

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
