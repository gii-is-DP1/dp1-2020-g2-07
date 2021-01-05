package org.springframework.samples.petclinic.model;
import java.time.LocalTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name = "sesion")
public class Sesion extends BaseEntity{
	
	@OneToMany(mappedBy="sesion")	
	private Set<Cita> citas;
	
	@NotNull(message = "Start time can't be null")
	@Column(name = "horaInicio")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm")
	private LocalTime horaInicio;

	@NotNull(message = "End time can't be null")
	@Column(name = "horaFin")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm")
	private LocalTime horaFin;

	@NotNull(message = "The room can't be null")
	@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "horario_id")
	private Horario horario;
	
	@OneToOne
	private Bono token;

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Set<Cita> getCitas() {
		return citas;
	}

	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}

	@Override
	public String toString() {
		return horario.getFecha() + ": From " + this.getHoraInicio() + " to " + this.getHoraFin();
	}
	
	public Boolean validate() {
		return horaInicio.isBefore(horaFin);
	}
	
	
}