package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.repository.BonoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BonoService {
	@Autowired
	BonoRepository tokenRepo;
	
	public BonoService(BonoRepository tokenRepo) {
		super();
		this.tokenRepo = tokenRepo;
	}

	public Collection<Bono> findAll(){
		return tokenRepo.findAll();
	}
	
	public List<Bono> findByIdLista(int id){
		return tokenRepo.findById(id);
	}
	
	public void delete(Bono bono) {
		tokenRepo.deleteById(bono.getId());
		log.info(String.format("Token with code %s and ID %d has been deleted", bono.getCodigo(), bono.getId()));
	}
	
	public void save(@Valid Bono bono) {
		tokenRepo.save(bono);
		log.info(String.format("Token with code %s and ID %d has been created or updated", bono.getCodigo(), bono.getId()));
	}
	
	public Optional<Bono> findById(Integer id) {
		return tokenRepo.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Bono> findBonos() throws DataAccessException{
		return tokenRepo.findAll();
	}
	
	//---------------------------//
	
	public boolean findTokenNoExist(String code) {
		return tokenRepo.findTokenExists(code) == 0;
	}
	
	public Bono findTokenByCode(String code) {
		return tokenRepo.findTokenByCode(code);
	}
}
