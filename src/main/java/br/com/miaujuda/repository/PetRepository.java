package br.com.miaujuda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miaujuda.model.Pets;

public interface PetRepository extends JpaRepository<Pets, Long>  {
	Optional<Pets> findByPets(String pets);
	Optional<Pets> findByPet(String usuario); 
	Pets findByUsuarioAndSenha(String usuario, String senha);
}
