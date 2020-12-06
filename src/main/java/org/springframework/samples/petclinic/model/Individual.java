package org.springframework.samples.petclinic.model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@MappedSuperclass
public class Individual extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty
    private String last_name;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private Categoria category;

    @Column(name = "IBAN")
    private String IBAN;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String direccion) {
        this.address = direccion;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria categoria) {
        this.category = categoria;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

