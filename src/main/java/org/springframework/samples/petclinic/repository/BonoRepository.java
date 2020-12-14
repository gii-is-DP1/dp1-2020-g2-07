  
package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Bono;

public interface BonoRepository extends Repository<Bono, Integer>, CrudRepository<Bono,Integer>{
	Collection<Bono> findAll();

	List<Bono> findById(int id);

	void deleteById(Integer id);
	
	@Query("SELECT COUNT(*) FROM Bono bono WHERE bono.codigo =:code")
	public Integer findTokenExists(@Param("code") String code);
	
	@Query("SELECT bono FROM Bono bono WHERE bono.codigo =:code")
	public Bono findTokenByCode(@Param("code") String code);
}