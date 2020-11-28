package org.springframework.samples.petclinic.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "balances")
public class Balance extends BaseEntity{
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

	@Column(name = "mante")
    private Integer mante;

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

	public Integer getMante() {
		return mante;
	}

	public void setMante(Integer mante) {
		this.mante = mante;
	}
	
	public Integer getSubs() {
		return subs;
	}

	public void setSubs(Integer subs) {
		this.subs = subs;
	}
	
	
	
}