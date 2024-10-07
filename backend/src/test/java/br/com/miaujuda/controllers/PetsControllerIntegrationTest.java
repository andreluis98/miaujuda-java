package br.com.miaujuda.controllers;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.repository.PetRepository;
import br.com.miaujuda.serices.PetsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetsServices petsServices;

    @BeforeEach
    void setUp() {
        petRepository.deleteAll();
    }

    @Test
    void testCreatePet() throws Exception {
        String json = "{\"name\":\"Buddy\",\"gender\":\"Male\",\"status\":\"ATIVO\",\"address\":\"123 Street\",\"observation\":\"Friendly\",\"pet\":\"Dog\",\"username\":\"user\",\"password\":\"pass\"}";

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testFindByPet() throws Exception {
        Pets pet = new Pets();
        pet.setName("Buddy");
        pet.setGender("Male");
        pet.setStatus("ATIVO");
        pet.setAddress("123 Street");
        pet.setObservation("Friendly");
        pet.setPet("Dog");
        pet.setUsername("user");
        pet.setPassword("pass");
        petRepository.save(pet);

        mockMvc.perform(get("/pets/" + pet.getPet()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"));
    }

    @Test
    void testDeletePet() throws Exception {
        Pets pet = new Pets();
        pet.setName("Buddy");
        pet.setGender("Male");
        pet.setStatus("ATIVO");
        pet.setAddress("123 Street");
        pet.setObservation("Friendly");
        pet.setPet("Dog");
        pet.setUsername("user");
        pet.setPassword("pass");
        pet = petRepository.save(pet);

        mockMvc.perform(delete("/pets/" + pet.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pet excluído com sucesso."));
    }

}
