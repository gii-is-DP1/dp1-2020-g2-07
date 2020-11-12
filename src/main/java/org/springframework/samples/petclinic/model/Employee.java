package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee extends Usuario{

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    @Setter private Profession profession;

    @OneToMany
    private List<Pago> pagos;

    public void addPago(Pago p){
        pagos.add(p);
    }
}
