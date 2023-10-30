package com.banco.test.personasms.persistence.mappers;

import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import com.banco.test.personasms.persistence.entity.Client;
import com.banco.test.personasms.dominio.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {

    @Named("genderEnumToString")
    static String genderEnumToString(GenderEnum gender) {
        return gender.name();
    }

    @Named("stringToGenderEnum")
    static GenderEnum stringToGenderEnum(String genero) {
        return GenderEnum.valueOf(genero);
    }

    @Mappings({
            @Mapping(source = "clientId", target = "id"),
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "gender", target = "genero", qualifiedByName = "stringToGenderEnum"),
            @Mapping(source = "age", target = "edad"),
            @Mapping(source = "identification", target = "identificacion"),
            @Mapping(source = "address", target = "direccion"),
            @Mapping(source = "phone", target = "telefono"),
            @Mapping(target = "password", ignore = true),
            @Mapping(source = "state", target = "estado")
    })
    ClientDTO toClientDTO(Client client);
    List<ClientDTO> toClients(List<Client> clients);

    @Mappings({
            @Mapping(source = "id", target = "clientId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "genero", target = "gender", qualifiedByName = "genderEnumToString"),
            @Mapping(source = "edad", target = "age"),
            @Mapping(source = "identificacion", target = "identification"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "telefono", target = "phone"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "estado", target = "state")
    })
    Client toClient(ClientDTO clientDTO);

}
