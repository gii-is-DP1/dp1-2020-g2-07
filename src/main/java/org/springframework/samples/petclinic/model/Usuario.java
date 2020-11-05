package org.springframework.samples.petclinic.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@MappedSuperclass
public class Usuario extends BaseEntity {

    @Column(name = "nick")
    @NotEmpty
    private String nick;

    @Column(name = "nombre")
    @NotEmpty
    private String nombre;

    @Column(name = "apellidos")
    @NotEmpty
    private String apellidos;

    @Column(name = "categoria")
    @NotEmpty
    private Categoria categoria;


}

