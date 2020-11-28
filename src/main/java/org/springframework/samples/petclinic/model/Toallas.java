package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "toallas")
public class Toallas extends BaseEntity{
    @Column(name = "cantidad")
    @Setter private Integer cantidad;
}
