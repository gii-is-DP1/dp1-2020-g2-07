package org.springframework.samples.petclinic.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends Individual {

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeRevenue> salaries;

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
}
