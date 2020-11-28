package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario{

    @Column(name = "suscripcion")
    @Enumerated(EnumType.STRING)
    private SubType suscripcion;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Pago> pagos;


    public SubType getSuscripcion() {
        return suscripcion;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setSuscripcion(SubType suscripcion) {
        this.suscripcion = suscripcion;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public void addPay(Pago p){
        this.getPagos().add(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return suscripcion == cliente.suscripcion &&
            Objects.equals(pagos, cliente.pagos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), suscripcion, pagos);
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "suscripcion=" + suscripcion +
            ", pagos=" + pagos +
            '}';
    }
}
