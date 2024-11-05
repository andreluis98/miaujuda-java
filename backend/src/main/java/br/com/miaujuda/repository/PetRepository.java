package br.com.miaujuda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miaujuda.model.Pets;

public interface PetRepository extends JpaRepository<Pets, Long>  {
	Optional<Pets> findByPet(String pet);
	Optional<Pets> findById(Long id);
//	Pets findByUsernameAndPassword(String username, String password); 
	Pets save(Optional<Pets> entity);
	void delete(Pets entity);
}