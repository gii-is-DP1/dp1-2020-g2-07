package org.springframework.samples.petclinic.repository;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;

public interface BalanceRepository  extends CrudRepository<Balance,Integer>{
    Collection<Balance> findAll();
    
    @Query("SELECT pago FROM Pago pago WHERE pago.fEmision >= :init_date and pago.fEmision <= :last_date")
	public Collection<Pago> findSubsByMonth(@Param("init_date") String init_date,@Param("last_date") String last_date);
    
    @Query("SELECT revenue FROM EmployeeRevenue revenue WHERE revenue.dateStart >= :init_date and revenue.dateEnd <= :last_date")
	public Collection<EmployeeRevenue> findSalariesByMonth(@Param("init_date") LocalDateTime init_date,@Param("last_date") LocalDateTime last_date);
    
    @Query("SELECT COUNT(*) FROM Balance balance WHERE balance.month = :month and balance.year = :year")
	public Integer findBalanceExists(@Param("month") String month,@Param("year") String year);
}
