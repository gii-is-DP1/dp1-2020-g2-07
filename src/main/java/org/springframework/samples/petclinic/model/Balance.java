package org.springframework.samples.petclinic.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "balance")
public class Balance extends BaseEntity{
	@Column(name = "mes")
    @Setter private String mes;
	
	@Column(name = "anyo")
    @Setter private String anyo;
	
	@Column(name = "bonos")
    @Setter private Integer bonos;
	
	@Column(name = "sueldos")
    @Setter private Integer sueldos;
	
	@Column(name = "mante")
    @Setter private Integer mante;
	
}