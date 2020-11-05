package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Usuario{
    @Column(name = "IBAN")
    @Setter private String IBAN;

    @Column(name = "subscripcion")
    @Setter private SubType subscripcion;

    @Column(name = "alCorriente")
    @Setter private Boolean alCorriente;

    @OneToMany
    private List<Pago> pagos;
}
