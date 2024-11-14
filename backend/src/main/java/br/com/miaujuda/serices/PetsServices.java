package br.com.miaujuda.serices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.exceptions.ResourceNotFoundException;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.model.User;
import br.com.miaujuda.repository.PetRepository;

@Service
public class PetsServices {

    @Autowired
    PetRepository repository;
    
    @Autowired
    private UserService userService;
    public List<PetsDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PetsDTO findById(Long id) {
        Pets pet = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID!"));
        return convertToDTO(pet);
    }

    public List<PetsDTO> findByPet(String pet) {
        String normalizedInput = pet.replaceAll("\\s+", "").toLowerCase();
        List<Pets> pets = repository.findAll();
        List<PetsDTO> filteredPets = pets.stream()
                .filter(petss -> petss.getPet().replaceAll("\\s+", "").toLowerCase().contains(normalizedInput))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (filteredPets.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this pet!");
        }

        return filteredPets;
    }
    
    public PetsDTO create(PetsDTO petsDTO) throws Exception {
    	System.out.println("PetsDTO" + petsDTO);
        if (petsDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (petsDTO.getTxPet() == null || petsDTO.getTxPet().isEmpty()) {
            throw new IllegalArgumentException("Pet type must not be null or empty");
        }
        if (petsDTO.getEndereco() == null || petsDTO.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("Pet address must not be null or empty");
        }
        if (petsDTO.getTxSx() == null || petsDTO.getTxSx().isEmpty()) {
            throw new IllegalArgumentException("Pet gender must not be null or empty");
        }

        Pets pet = convertToEntity(petsDTO);

        User user = userService.findById(petsDTO.getUserId());
//                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        pet.setUser(user);

        Pets savedPet = repository.save(pet);
        return convertToDTO(savedPet);
    }





    public PetsDTO update(PetsDTO petsDTO) throws Exception {
        
        if (petsDTO.getUserId() == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo.");
        }
        if (petsDTO.getTxPet() == null || petsDTO.getTxPet().isEmpty()) {
            throw new IllegalArgumentException("O tipo do pet não pode ser nulo ou vazio.");
        }
        if (petsDTO.getEndereco() == null || petsDTO.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço do pet não pode ser nulo ou vazio.");
        }
        if (petsDTO.getTxSx() == null || petsDTO.getTxSx().isEmpty()) {
            throw new IllegalArgumentException("O gênero do pet não pode ser nulo ou vazio.");
        }

        try {
            Pets pet = convertToEntity(petsDTO);
            Pets existingPet = repository.findById(pet.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID!"));

            existingPet.setPet(petsDTO.getTxPet());
            existingPet.setGender(petsDTO.getTxSx());
            existingPet.setAddress(petsDTO.getEndereco());
            existingPet.setObservation(petsDTO.getTxObs());
            existingPet.setStatus(petsDTO.getTxStatus());
            existingPet.setPetImage(petsDTO.getPetImage());

            
            Pets updatedPet = repository.save(existingPet);
            
            System.out.println("Pet atualizado com sucesso. ID: " + updatedPet.getId());
            return convertToDTO(updatedPet);

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
            throw e; 

        } catch (Exception e) {
            System.err.println("Erro inesperado ao atualizar o pet: " + e.getMessage());
            throw new Exception("Erro ao atualizar o pet. Tente novamente mais tarde.");
        }
    }



    public void delete(Long id) {
        Pets pet = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(pet);
    }

    private PetsDTO convertToDTO(Pets pet) {
        PetsDTO dto = new PetsDTO();
        dto.setId(pet.getId());
        dto.setTxPet(pet.getPet());
        dto.setTxSx(pet.getGender());
        dto.setEndereco(pet.getAddress());
        dto.setTxObs(pet.getObservation());
        dto.setUserId(pet.getUser().getId());
        dto.setPetImage(pet.getPetImage());
        dto.setTxStatus(pet.getStatus());

        if (pet.getUser() != null) { // Adicione esta verificação
            dto.setUserId(pet.getUser().getId());
        }
        
        return dto;
    }

//    public PetsDTO convertToDTO(Pets pet) {
//        PetsDTO dto = new PetsDTO();
//        dto.setTxPet(pet.getPet());
//        dto.setTxSx(pet.getGender());
//        dto.setTxStatus(pet.getStatus());
//        dto.setEndereco(pet.getAddress());
//        dto.setTxObs(pet.getObservation());
//        
//        if (pet.getUser() != null) { // Adicione esta verificação
//            dto.setUserId(pet.getUser().getId());
//        }
//        return dto;
//    }

    
    

    private Pets convertToEntity(PetsDTO dto) {
        Pets pet = new Pets();
        pet.setId(dto.getId());
        pet.setName(dto.getTxPet());
        pet.setGender(dto.getTxSx());
        pet.setStatus(dto.getTxStatus());
        pet.setAddress(dto.getEndereco());
        pet.setObservation(dto.getTxObs());
        pet.setPet(dto.getTxPet());
        pet.setPetImage(dto.getPetImage());

        return pet;
    }

}
