package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pagos")
public class Pago extends BaseEntity{
    @Column(name = "f_emision")
    @Setter private String fEmision;

    @Column(name = "cantidad")
    @Setter private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Setter private Cliente cliente;
}
