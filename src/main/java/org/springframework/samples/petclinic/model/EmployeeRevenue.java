package org.springframework.samples.petclinic.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "revenue")
public class EmployeeRevenue extends BaseEntity{
    @Column(name = "dateStart")
    @Setter private LocalDateTime dateStart;

    @Column(name = "dateEnd")
    @Setter private LocalDateTime dateEnd;

    @Column(name = "hoursWorked")
    @Setter private Integer hoursWorked;

    @Column(name = "cuantity")
    @Setter private Integer cuantity;

}
