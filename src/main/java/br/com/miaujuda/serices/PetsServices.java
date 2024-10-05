package br.com.miaujuda.serices;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.exceptions.ResourceNotFoundException;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.repository.PetRepository;

@Service
public class PetsServices {

	private Logger logger = Logger.getLogger(PetsServices.class.getName());
	
	@Autowired
	PetRepository repository;

	// List all Pets
    public List<PetsDTO> findAll() {
        logger.info("Finding All Pets");
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // List Pets by Id
    public Optional<Pets> findById(Long pets) {
        logger.info("Finding Pets by Id");
        return repository.findById(pets);
    }
    
    // List by name of Pets
    public List<PetsDTO> findByPet(String pet) {
        logger.info("Finding clients by pets");
        String normalizedInput = pet.replaceAll("\\s+", "").toLowerCase();
        List<Pets> pets = repository.findAll();
        List<PetsDTO> filteredPets = pets.stream()
        		 .filter(petss -> petss.getPet().replaceAll("\\s+", "").toLowerCase().contains(normalizedInput))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (filteredPets.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this Razao Social!");
        }
        
        return filteredPets;
    }
    
    // Create Pet
    public PetsDTO create(Pets pets) throws Exception {
        logger.info("Creating one Pet");
        Pets savedPets = repository.save(pets);
        return convertToDTO(savedPets);
    }
    
    // Edit Pet
    public PetsDTO update(Pets pets) {
        logger.info("Updating one Pets");
        var entity = repository.findById(pets.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

            entity.setName(pets.getName());
            entity.setGender(pets.getGender());
            entity.setAddress(pets.getAddress());
            entity.setObservation(pets.getObservation());
            entity.setPet(pets.getPet());
            entity.setStatus(pets.getStatus());
            entity.setUsername(pets.getUsername()); 

            Pets updatedPets = repository.save(entity);
            return convertToDTO(updatedPets);
    }

	
    // Delete Clients
    public void delete(Long id) {
        logger.info("Deleting one Pets");
        var entity = repository.findById(id)
        		 .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));;
        		 repository.delete(entity); 
    }

	//Login
    public Pets login(String username, String password) {
    	return repository.findByUsernameAndPassword(username, password);
    }
    
    // Convert model to DTO
    private PetsDTO convertToDTO(Pets pets) {
        PetsDTO dto = new PetsDTO();
        dto.setId(pets.getId());
        dto.setName(pets.getName());
        dto.setGender(pets.getGender());
        dto.setStatus(pets.getStatus());
        dto.setAddress(pets.getAddress());
        dto.setObservation(pets.getObservation());
        dto.setPet(pets.getPet());
        dto.setUsername(pets.getUsername());
        return dto;
    }

}
