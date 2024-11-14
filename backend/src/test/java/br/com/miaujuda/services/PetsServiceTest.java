package br.com.miaujuda.services;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.model.User;
import br.com.miaujuda.repository.PetRepository;
import br.com.miaujuda.serices.PetsServices;
import br.com.miaujuda.serices.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PetsServiceTest {

    @InjectMocks
    private PetsServices petsService;

    @Mock
    private PetRepository petsRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePet() throws Exception {
        // Setup
        PetsDTO petsDTO = new PetsDTO();
        petsDTO.setTxPet("Buuddy");
        petsDTO.setEndereco("12345 Street");
        petsDTO.setTxStatus("ATIVO");
        petsDTO.setTxSx("Male");
        petsDTO.setTxObs("Test");
        petsDTO.setUserId(1L);

        User user = new User();
        user.setId(1L);

        Pets pet = new Pets();
        pet.setPet("Buddy");
        pet.setName("Mel");
        pet.setUser(user);

        when(userService.findById(1L)).thenReturn(user);
        when(petsRepository.save(any(Pets.class))).thenReturn(pet);

 
        PetsDTO createdPet = petsService.create(petsDTO);

        assertNotNull(createdPet);
        assertEquals("Buddy", createdPet.getTxPet());


        verify(userService).findById(1L);
        verify(petsRepository).save(any(Pets.class));
    }
}
