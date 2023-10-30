package com.banco.test.personasms.web.controller;

import com.banco.test.personasms.dominio.dto.ClientDTO;
import com.banco.test.personasms.dominio.service.ClientsService;
import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;

@SpringBootTest
public class ClientsControllerTest {

    @MockBean
    private ClientsService clientsService;

    private MockMvc mockMvc;

    @Test
    public void testCreateClient() throws Exception {
        // Configuración del controlador y validador
        ClientsController clientsController = new ClientsController(clientsService);
        mockMvc = MockMvcBuilders.standaloneSetup(clientsController)
                .setValidator(new LocalValidatorFactoryBean())
                .build();

        Mockito.when(clientsService.createClient( Mockito.any(ClientDTO.class) )).thenReturn( getMockClient() );

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson( getMockClient() )))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(clientsService, Mockito.times(1)).createClient(Mockito.any(ClientDTO.class));
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    private ClientDTO getMockClient(){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNombre("Pepe Perez");
        clientDTO.setGenero(GenderEnum.M);
        clientDTO.setEdad(30);
        clientDTO.setIdentificacion("12345");
        clientDTO.setDireccion("123 Main St");
        clientDTO.setTelefono("555-1234");
        clientDTO.setPassword("password");
        clientDTO.setEstado(true);
        return clientDTO;
    }
}
