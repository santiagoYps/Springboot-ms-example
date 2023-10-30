package com.banco.test.cuentasms.persistence.repository;

import com.banco.test.cuentasms.dominio.dto.ClientDTO;
import com.banco.test.cuentasms.dominio.repository.ClientsRepository;
import com.banco.test.cuentasms.web.error.ConsumingServiceError;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class ClientsRepositoryImpl implements ClientsRepository {

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${services.clients.urlBase}")
    private String urlBase;

    @Override
    public ClientDTO getClientByIdentification(String identification) {
        Request request = new Request.Builder()
                .url(this.urlBase+"/"+identification)
                .get()
                .build();
        try ( Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, ClientDTO.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConsumingServiceError("Error en la comunicación con el servicio clients");
        }
    }
}
