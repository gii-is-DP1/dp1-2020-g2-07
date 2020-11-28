package org.springframework.samples.petclinic.model;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "revenue")
public class EmployeeRevenue extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "hours_worked")
    private Integer hoursWorked;

    @Column(name = "cuantity")
    private Integer cuantity;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setCuantity(Integer cuantity) {
        this.cuantity = cuantity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public Integer getCuantity() {
        return cuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRevenue that = (EmployeeRevenue) o;
        return Objects.equals(employee, that.employee) &&
            Objects.equals(dateStart, that.dateStart) &&
            Objects.equals(dateEnd, that.dateEnd) &&
            Objects.equals(hoursWorked, that.hoursWorked) &&
            Objects.equals(cuantity, that.cuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, dateStart, dateEnd, hoursWorked, cuantity);
    }

    @Override
    public String toString() {
        return "EmployeeRevenue{" +
            "employee=" + employee +
            ", dateStart=" + dateStart +
            ", dateEnd=" + dateEnd +
            ", hoursWorked=" + hoursWorked +
            ", cuantity=" + cuantity +
            '}';
    }
}