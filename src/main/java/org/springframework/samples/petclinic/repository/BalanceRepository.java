package org.springframework.samples.petclinic.repository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;

public interface BalanceRepository  extends Repository<Balance,Integer>,  CrudRepository<Balance,Integer>{
    Collection<Balance> findAll();
    
    List<Balance> findById(int id);
    
    @Query("SELECT pago FROM Pago pago WHERE pago.fEmision >= :init_date and pago.fEmision <= :last_date")
	public Collection<Pago> findSubsByMonth(@Param("init_date") String init_date,@Param("last_date") String last_date);
    
    @Query("SELECT revenue FROM EmployeeRevenue revenue WHERE revenue.dateStart >= :init_date and revenue.dateEnd <= :last_date")
	public Collection<EmployeeRevenue> findSalariesByMonth(@Param("init_date") LocalDate init_date,@Param("last_date") LocalDate last_date);
    
    @Query("SELECT bono FROM Bono bono WHERE bono.date_start >= :init_date and bono.date_end <= :last_date and bono.usado = :used")
   	public Collection<Bono> findUsedTokensByMonth(@Param("init_date") LocalDate init_date,@Param("last_date") LocalDate last_date,@Param("used") Boolean used);
    
    @Query("SELECT COUNT(*) FROM Balance balance WHERE balance.month = :month and balance.year = :year")
	public Integer findBalanceExists(@Param("month") String month,@Param("year") String year);
    
    @Query("SELECT balance FROM Balance balance WHERE balance.id =:id")
	public Balance findBalanceById(@Param("id") int id);
}