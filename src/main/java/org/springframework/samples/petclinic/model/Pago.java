package org.springframework.samples.petclinic.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pagos")
public class Pago extends BaseEntity{
    @Column(name = "f_emision")
    private String fEmision;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public void setfEmision(String fEmision) {
        this.fEmision = fEmision;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

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