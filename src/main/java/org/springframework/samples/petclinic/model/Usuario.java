package org.springframework.samples.petclinic.model;


import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@MappedSuperclass
public class Usuario extends BaseEntity {

    @NotEmpty
    @Column(name = "nick")
    @NotEmpty
    private String nick;

    @NotEmpty
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;

    @NotEmpty
    @Column(name = "apellidos")
    @NotEmpty
    private String apellidos;

    @NotEmpty
    @Column(name = "direccion")
    @NotEmpty
    private String direccion;

    @Column(name = "categoria")
    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    @NotEmpty
    @Column(name = "IBAN")
    private String IBAN;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}

