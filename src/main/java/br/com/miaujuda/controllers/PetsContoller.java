package br.com.miaujuda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import br.com.miaujuda.dtos.PetsDTO;
import br.com.system.dto.ClientDTO;
import br.com.system.dto.LoginDTO;
import br.com.system.model.Client;

@RestController
@RequestMapping("/pets")
public class PetsContoller {

    @Autowired
    private PetsService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PetsDTO> findByAll() {
		return service.findAll();
    }
	
	@GetMapping(value = "/pets/{pets}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PetsDTO> findByPets(@PathVariable(value = "pets") String razaoSocial){
		
	}
	
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PetsDTO create(@RequestBody Pets pets) throws Exception {
        return service.create(pets);
    }
	
    
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PetsDTO update(@RequestBody Pets pets) throws Exception {
        return service.update(pets);
    }
	
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet excluído com sucesso.");
        return ResponseEntity.ok(response);
    }
    
    //Login
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        Map<String, String> response = new HashMap<>();

        User authenticatedUser = service.login(loginDTO.getUsuario(), loginDTO.getSenha());

        if (authenticatedUser != null) {
            if ("ativo".toUpperCase().equals(authenticatedUser.getStatus())) {
                response.put("message", "Login bem-sucedido.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Usuário inativo.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            response.put("message", "Usuário ou senha incorretos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

	
}
