package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Horario;

public interface HorarioRepository extends Repository<Horario, Integer>,CrudRepository<Horario,Integer>{


    Collection<Horario> findAll();
    Optional<Horario> findById(int id);


}