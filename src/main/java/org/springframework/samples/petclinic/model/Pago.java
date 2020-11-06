package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pagos")
public class Pago extends BaseEntity{
    @Column(name = "fechEmision")
    @Setter private LocalDateTime fechEmision;

    @Column(name = "precio")
    @Setter private Integer precio;
}
