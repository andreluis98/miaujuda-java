package br.com.miaujuda.controllers;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.serices.PetsServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetsContoller.class)
public class PetsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetsServices petsServices;

    @Test
    public void testCreatePet() throws Exception {
        String json = "{\"txPet\":\"Mel\", \"txSx\":\"Male\", \"txStatus\":\"ATIVO\", \"endereco\":\"Street 219\", \"txObs\":\"Friendly\", \"userId\":1}";


        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        
        verify(petsServices).create(any(PetsDTO.class));
    }
}
