package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "revenue")
public class EmployeeRevenue extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date_start")
    @Setter private LocalDateTime dateStart;

    @Column(name = "date_end")
    @Setter private LocalDateTime dateEnd;

    @Column(name = "hours_worked")
    @Setter private Integer hoursWorked;

    @Column(name = "cuantity")
    @Setter private Integer cuantity;

}
