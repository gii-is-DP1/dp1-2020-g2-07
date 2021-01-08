package org.springframework.samples.petclinic.model;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "clientes")
public class Cliente extends Individual {
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)	
	private Set<Cita> citas;

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

    public Set<Cita> getCitas() {
		return citas;
	}

	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}
	
	public void addApointment(Cita c) {
		this.addApointment(c);
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
    	return this.getFirst_name();
    }
}
