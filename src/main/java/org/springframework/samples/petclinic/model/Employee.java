package org.springframework.samples.petclinic.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

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

    public List<Horario> getHorarios() {
    	PropertyComparator.sort(horarios, new MutableSortDefinition("fecha", true, true));
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
}

