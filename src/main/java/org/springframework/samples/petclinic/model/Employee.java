package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee extends Usuario{

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    @Setter private Profession profession;

    @OneToMany
    private List<Pago> pagos;

    public void addPago(Pago p){
        pagos.add(p);
    }
}
