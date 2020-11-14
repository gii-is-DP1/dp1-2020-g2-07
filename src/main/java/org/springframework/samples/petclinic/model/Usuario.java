package org.springframework.samples.petclinic.model;


import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@MappedSuperclass
@Data
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

    @Column(name = "direccion")
    @NotEmpty
    private String direccion;

    @Column(name = "categoria")
    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    @Column(name = "IBAN")
    @Setter
    private String IBAN;
}

