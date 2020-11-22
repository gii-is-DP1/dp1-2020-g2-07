package org.springframework.samples.petclinic.repository;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Pago;

public interface BalanceRepository  extends CrudRepository<Balance,Integer>{
    Collection<Balance> findAll();
    
    @Query("SELECT pago FROM Pago pago WHERE pago.fEmision >= :init_date and pago.fEmision <= :last_date")
	public Collection<Pago> findSubsByMonth(@Param("init_date") String init_date,@Param("last_date") String last_date);
}
