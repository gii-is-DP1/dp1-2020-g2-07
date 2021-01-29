package org.springframework.samples.petclinic.model;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;


@Entity
@Table(name = "revenue")
public class EmployeeRevenue extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;

    @NotNull
    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    @NotNull
    @Min(0)
    @Max(170)
    @Column(name = "hours_worked")
    private Integer hoursWorked;

    @NotNull
    @Min(8)
    @Column(name = "quantity")
    private Integer quantity;
    
   
    public EmployeeRevenue() {
		super();
	}

	public EmployeeRevenue(Employee employee, LocalDate dateStart, LocalDate dateEnd,
			@Min(1) @Max(170) Integer hoursWorked, @Min(8) Integer quantity) {
		super();
		this.employee = employee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.hoursWorked = hoursWorked;
		this.quantity = quantity;
	}

	public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Integer getQuantity() {
        return getHoursWorked() * getEmployee().getSalary();
    }
}
