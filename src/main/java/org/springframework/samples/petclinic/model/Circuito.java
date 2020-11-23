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
	
	@Column(name="aforo")
	@NotNull
	private Integer aforo;
	
	@Size(min = 10, max = 1024)
	@Column(name = "descripcion", length=1024)
	private String descripcion;
//	
//	private Integer aforoCircuito(List<Sala> salas) {
//		Integer min = salas.get(0).getAforo();
//		for(int i=1; i< salas.size();i++) {
//			Integer aforo = salas.get(i).getAforo();
//			if(aforo<min) {
//				min = aforo;
//			}
//		}
//		return min;
//	}
	
	
	protected List<Sala> getSalasInternal() {
		if (this.salas == null) {
			this.salas = new ArrayList<Sala>();
		}
		return this.salas;
	}
//	protected void setSalassInternal(List<Sala> salas) {
//		this.salas = salas;
//	}
//	
	public List<Sala> getSalas() {
		List<Sala> salas = new ArrayList<>(getSalasInternal());
		PropertyComparator.sort(salas, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(salas);
	}
//	
	public Sala getSala(String name) {
		return getSala(name);
	}
//
	public void addSala(Sala s){
        salas.add(s);
    }
	
	public void addSalas(List<Sala> s){
        salas.addAll(s);
    }
	
//	public boolean removeSala(Sala sala) {
//		return getSalasInternal().remove(sala);
//	}
//	
//	public Sala getSalawithIdDifferent(String name,Integer id) {
//		name = name.toLowerCase();
//		for (Sala sala : getSalasInternal()) {
//			String compName = sala.getName();
//			compName = compName.toLowerCase();
//			if (compName.equals(name) && sala.getId()!=id) {
//				return sala;
//			}
//		}
//		return null;
//	}
}
