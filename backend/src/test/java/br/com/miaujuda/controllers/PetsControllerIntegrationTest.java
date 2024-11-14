package br.com.miaujuda.controllers;

import br.com.miaujuda.MiaujudaApplication;
import br.com.miaujuda.model.User;
import br.com.miaujuda.repository.UserRepository;
import br.com.miaujuda.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MiaujudaApplication.class)
@AutoConfigureMockMvc
class PetsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;


    private User createUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("unique_email" + System.currentTimeMillis() + "@example.com");
        user.setPassword("securePassword");
        user.setUsername("uniqueUsername" + System.currentTimeMillis());
        return userRepository.save(user);
    }

    @Test
    void testCreatePet() throws Exception {
        User user = createUser();

        String json = "{\"txPet\":\"kaltos\", \"txSx\":\"Male\", \"txStatus\":\"ATIVO\", \"endereco\":\"2116 Street\", \"txObs\":\"Friendly\", \"userId\":1}";


        MvcResult result = mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();


        System.out.println("Response: " + result.getResponse().getContentAsString());

 
        assert petRepository.count() > 0;
    }
}
