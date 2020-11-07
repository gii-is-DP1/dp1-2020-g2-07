package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Usuario{
    @Column(name = "IBAN")
    @Setter private String IBAN;

    @Column(name = "suscripcion")
    @Enumerated(EnumType.ORDINAL)
    @Setter private SubType suscripcion;



    @OneToMany
    private List<Pago> pagos;

    public void addPago(Pago p){
        pagos.add(p);
    }
}
