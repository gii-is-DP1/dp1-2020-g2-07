package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Usuario{

    @Column(name = "suscripcion")
    @Enumerated(EnumType.STRING)
    @Setter private SubType suscripcion;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pago> pagos;
}
