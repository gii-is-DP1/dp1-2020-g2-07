package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pagos")
public class Pago extends BaseEntity{
    @Column(name = "f_emision")
    @Setter private String fEmision;

    @Column(name = "cantidad")
    @Setter private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Setter private Cliente cliente;

    public String getfEmision(){ return this.fEmision; }
    public Integer getCantidad(){ return this.cantidad; }
    public Cliente getCliente(){ return this.cliente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return Objects.equals(fEmision, pago.fEmision) &&
            Objects.equals(cantidad, pago.cantidad) &&
            Objects.equals(cliente, pago.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fEmision, cantidad, cliente);
    }

    @Override
    public String toString() {
        return "Pago{" +
            "fEmision='" + fEmision + '\'' +
            ", cantidad=" + cantidad +
            ", cliente=" + cliente +
            '}';
    }
}
