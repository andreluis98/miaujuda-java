package br.com.miaujuda.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.miaujuda.dtos.PetsDTO;
import br.com.miaujuda.model.Pets;
import br.com.miaujuda.repository.PetRepository;
import br.com.miaujuda.serices.PetsServices;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PetsServiceTest {

    @InjectMocks
    private PetsServices petsService;

    @Mock
    private PetRepository petsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Pets pet1 = new Pets();
        pet1.setName("Buddy"); // Definindo o nome
        Pets pet2 = new Pets();
        pet2.setName("Max"); // Definindo o nome
        when(petsRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        // Act
        List<PetsDTO> pets = petsService.findAll();

        // Assert
        assertEquals(2, pets.size());
    }

    @Test
    public void testFindById() {
        // Arrange
        Pets pet = new Pets();
        pet.setId(1L); // Definindo o ID
        pet.setName("Buddy"); // Definindo o nome
        when(petsRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Act
        Optional<Pets> foundPet = petsService.findById(1L);

        // Assert
        assertTrue(foundPet.isPresent());
        assertEquals("Buddy", foundPet.get().getName());
    }

    @Test
    public void testSave() {
        // Arrange
        Pets pet = new Pets();
        pet.setId(1L); // Definindo o ID
        pet.setName("Buddy"); // Definindo o nome
        when(petsRepository.save(any(Pets.class))).thenReturn(pet); // Retornando o mesmo objeto

        // Act
        Pets savedPet = petsService.save(pet);

        // Assert
        assertEquals("Buddy", savedPet.getName());
        assertNotNull(savedPet.getId());
    }
}
