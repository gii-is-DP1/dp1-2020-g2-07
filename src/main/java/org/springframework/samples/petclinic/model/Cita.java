package org.springframework.samples.petclinic.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citas")
public class Cita extends BaseEntity{

	@ManyToOne(optional = false)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="sesion_id")
	private Sesion sesion;

	public Cita(Cliente cliente, Sesion sesion) {
		super();
		this.cliente = cliente;
		this.sesion = sesion;
	}

	public Cita() {
		super();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
}
