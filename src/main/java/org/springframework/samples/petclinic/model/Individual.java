package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Individual extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty(message = "First name must be filled")
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name must be filled")
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")
    private String last_name;
    
    @Min(value=18, message="Must be equal or greater than 18")
    private Integer age;

    @Column(name = "DOB")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;

    @Column(name = "address")
    @NotEmpty(message = "Address must be filled")
    private String address;

    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private Categoria category;

    @Column(name = "IBAN")
    @NotEmpty(message = "IBAN must be filled")
    @Pattern(regexp = "([a-zA-Z]{2})\\s*\\t*(\\d{2})\\s*\\t*(\\d{4})\\s*\\t*(\\d{4})\\s*\\t*(\\d{2})\\s*\\t*(\\d{10})")
    private String IBAN;

    @Column(name = "email")
    @NotEmpty(message = "Email must be filled")
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
    private  String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Integer getAge() {
		return Period.between(DOB, LocalDate.now()).getYears();
	}
    
    public LocalDate getDOB() {
        return DOB;
    }
    
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Boolean isBirthday() {
        return DOB.getMonthValue() == LocalDate.now().getMonthValue() && DOB.getDayOfMonth() == LocalDate.now().getDayOfMonth();
    }
}

