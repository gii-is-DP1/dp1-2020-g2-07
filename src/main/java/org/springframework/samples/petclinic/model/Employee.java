package org.springframework.samples.petclinic.model;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employees")

public class Employee extends Individual {

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeRevenue> salaries;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Horario> horarios;
    
    /*@ManyToMany(mappedBy="incomest", cascade = CascadeType.ALL)
	private List<Balance> incomest;*/

    public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<EmployeeRevenue> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<EmployeeRevenue> salaries) {
        this.salaries = salaries;
    }

    public void addSalary(EmployeeRevenue e){
        this.salaries.add(e);
    }

    public void addHorario(Horario h){
        this.horarios.add(h);
    }

	@Override
	public String toString() {
		return getFirst_name();
	}

    public Integer getSalary() {
        //retorna el salario (euros / hora)
        switch (profession) {
            case ADMIN: return 17;
            case CLEANER: return 10;
            case LIFE_GUARD: return 12;
            case MASSAGIST: return 15;
            default: return 0;
        }
    }

    public Double getHoursWorked(LocalDate start, LocalDate end) {
        return getHorarios().stream()
            .filter(h -> h.getFecha().isBefore(end) && h.getFecha().isAfter(start))
            .map(h -> h.getSesiones()
                .stream()
                .map(s -> (double)Duration.between(s.getHoraInicio(), s.getHoraFin()).toMinutes() / 60)
                .mapToDouble(Double::doubleValue)
                .sum())
            .mapToDouble(Double::doubleValue)
            .sum();
    }
    
	public Boolean validEmployee(String prof, String room_type) {
		Boolean res = true;
		if(!prof.equals(room_type)) {
			res = false;
		}
		return res;
	}
}

