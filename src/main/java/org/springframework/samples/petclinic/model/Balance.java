package org.springframework.samples.petclinic.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "balances")
public class Balance extends BaseEntity{
	
	 @JoinTable(
		        name = "rel_statement_employee",
		        joinColumns = @JoinColumn(name="FK_Statement"),
		        inverseJoinColumns = @JoinColumn(name="FK_Employee")
		    )
	
	@Column(name = "month")
    private String month;
	
	@Column(name = "year")
    private String year;
	
	@Column(name = "subs")
    private Integer subs;
	
	@Column(name = "bonos")
    private Integer bonos;
	
	@Column(name = "salaries")
    private Integer salaries;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Employee> employee;

	
	public Balance() {
		super();
	}

	public Balance(String month, String year, Integer subs, Integer bonos, Integer salaries, List<Employee> employee) {
		super();
		this.month = month;
		this.year = year;
		this.subs = subs;
		this.bonos = bonos;
		this.salaries = salaries;
		this.employee = employee;
	}



	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getBonos() {
		return bonos;
	}

	public void setBonos(Integer bonos) {
		this.bonos = bonos;
	}

	public Integer getSalaries() {
		return salaries;
	}

	public void setSalaries(Integer salaries) {
		this.salaries = salaries;
	}
	
	public Integer getSubs() {
		return subs;
	}

	public void setSubs(Integer subs) {
		this.subs = subs;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
	
}