package com.adotai.pets.core.usecases;

import com.adotai.pets.core.DTOs.request.RegisterPetRequestDTO;
import com.adotai.pets.core.DTOs.response.RegisterPetResponseDTO;
import com.adotai.pets.core.domain.Pet;
import com.adotai.pets.core.gateways.PetGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterPetUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @InjectMocks
    private RegisterPetUseCase registerPetUseCase;

    @Test
    @DisplayName("Should register pet successfully and return response DTO")
    void testShouldRegisterPetSuccessfully() {
        var request = RegisterPetRequestDTO.builder()
                .name("Rex")
                .age(4)
                .type("Dog")
                .observations("Very friendly")
                .build();
        var orgDocument = "123.456.789-00";

        var petSaved = Pet.builder()
                .id("pet-001")
                .name("Rex")
                .age(4)
                .type("Dog")
                .observations("Very friendly")
                .build();

        when(petGateway.savePet(any(Pet.class), eq(orgDocument))).thenReturn(petSaved);

        RegisterPetResponseDTO response = registerPetUseCase.execute(request, orgDocument);

        assertNotNull(response);
        assertEquals("pet-001", response.id());
        assertEquals("Rex", response.name());
        assertEquals(4, response.age());
        assertEquals("Dog", response.type());
        assertEquals("Very friendly", response.observations());

        verify(petGateway, times(1)).savePet(any(Pet.class), eq(orgDocument));
    }
}
