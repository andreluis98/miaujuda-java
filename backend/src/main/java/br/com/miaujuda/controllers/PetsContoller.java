package br.com.miaujuda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miaujuda.dtos.LoginDTO;
import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.serices.PetsServices;

@RestController
@RequestMapping("/pets")
public class PetsContoller {

    @Autowired
    private PetsServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetsDTO> findByAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{pet}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetsDTO> findByPet(@PathVariable(value = "pet") String pet) {
        return service.findByPet(pet);
    }
    
    @GetMapping(value = "/mat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PetsDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody PetsDTO petsDTO) {
        try {
            PetsDTO createdPet = service.create(petsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
        } catch (Exception e) {
          
            System.err.println("Erro ao criar o pet: " + e.getMessage());
            e.printStackTrace();

            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar o pet: " + e.getMessage());
        }
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PetsDTO update(@RequestBody PetsDTO petsDTO) throws Exception {
        return service.update(petsDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet excluído com sucesso.");
        return ResponseEntity.ok(response);
    }

}
