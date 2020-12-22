package org.springframework.samples.petclinic.model;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pagos")
public class Pago extends BaseEntity{
    @Column(name = "f_emision")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fEmision;

    @Column(name = "cantidad")
    @NotNull
    @Min(30)
    @Max(50)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public void setfEmision(LocalDate fEmision) {
        this.fEmision = fEmision;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getfEmision(){ return this.fEmision; }
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
