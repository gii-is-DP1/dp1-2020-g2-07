package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;

public interface HorarioRepository extends Repository<Horario, Integer>,CrudRepository<Horario,Integer>{


    Collection<Horario> findAll();
    Optional<Horario> findById(int id);
    
//    @Query("SELECT horario FROM Horario horario WHERE horario.employee.id = :employee_id")
//    public Collection<Horario> getHorariosByEmployee(@Param("employee_id") int employee_id);
    
    @Query("SELECT sesion FROM Sesion sesion WHERE sesion.horario.id = :horario_id")
    public Collection<Sesion> getSesionByHorario(@Param("horario_id") int horario_id);

    @Query("SELECT sesion FROM Sesion sesion WHERE sesion.sala.id = :sala_id")
    public Collection<Sesion> getSesionBySala(@Param("sala_id") int sala_id);

}