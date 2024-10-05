package br.com.miaujuda.controllers;

import br.com.miaujuda.dtos.LoginDTO;
import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.serices.PetsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PetsControllerTest {

    @InjectMocks
    private PetsContoller petsController;

    @Mock
    private PetsServices petsServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByAll() {
        PetsDTO petsDTO = new PetsDTO();
        petsDTO.setId(1L);
        petsDTO.setName("Mel");
        when(petsServices.findAll()).thenReturn(Collections.singletonList(petsDTO));

        List<PetsDTO> result = petsController.findByAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mel", result.get(0).getName());
        verify(petsServices, times(1)).findAll();
    }

    @Test
    public void testFindByPet() {
        PetsDTO petsDTO = new PetsDTO();
        petsDTO.setId(1L);
        petsDTO.setName("Mel");
        when(petsServices.findByPet("Mel")).thenReturn(Collections.singletonList(petsDTO));

        List<PetsDTO> result = petsController.findByPet("Mel");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mel", result.get(0).getName());
        verify(petsServices, times(1)).findByPet("Mel");
    }

    @Test
    public void testFindById() {
        Pets pets = new Pets();
        pets.setId(1L);
        when(petsServices.findById(anyLong())).thenReturn(Optional.of(pets));

        Optional<Pets> result = petsController.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(petsServices, times(1)).findById(1L);
    }

    @Test
    public void testCreate() throws Exception {
        Pets pet = new Pets();
        pet.setId(1L);
        pet.setName("Mel");
        when(petsServices.create(any())).thenReturn(new PetsDTO());

        PetsDTO result = petsController.create(pet);

        assertNotNull(result);
        verify(petsServices, times(1)).create(pet);
    }

    @Test
    public void testUpdate() throws Exception {
        Pets pet = new Pets();
        pet.setId(1L);
        when(petsServices.update(any())).thenReturn(new PetsDTO());

        PetsDTO result = petsController.update(pet);

        assertNotNull(result);
        verify(petsServices, times(1)).update(pet);
    }

    @Test
    public void testDelete() throws Exception {
        ResponseEntity<Map<String, String>> response = petsController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pet excluído com sucesso.", response.getBody().get("message"));
        verify(petsServices, times(1)).delete(1L);
    }

    @Test
    public void testLogin_Success() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("password");
        Pets authenticatedUser = new Pets();
        authenticatedUser.setStatus("ATIVO");
        when(petsServices.login("user", "password")).thenReturn(authenticatedUser);

        ResponseEntity<Map<String, String>> result = petsController.login(loginDTO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Login bem-sucedido.", result.getBody().get("message"));
        verify(petsServices, times(1)).login("user", "password");
    }

    @Test
    public void testLogin_UserInactive() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("password");
        Pets authenticatedUser = new Pets();
        authenticatedUser.setStatus("INATIVO");
        when(petsServices.login("user", "password")).thenReturn(authenticatedUser);

        ResponseEntity<Map<String, String>> result = petsController.login(loginDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("Usuário inativo.", result.getBody().get("message"));
        verify(petsServices, times(1)).login("user", "password");
    }

    @Test
    public void testLogin_InvalidCredentials() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("wrongpassword");
        when(petsServices.login("user", "wrongpassword")).thenReturn(null);

        ResponseEntity<Map<String, String>> result = petsController.login(loginDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("Usuário ou senha incorretos.", result.getBody().get("message"));
        verify(petsServices, times(1)).login("user", "wrongpassword");
    }
}
