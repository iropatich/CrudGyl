package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.cliente.ClienteRequestDto;
import com.gyl.CrudGyl.dto.cliente.ClienteResponseDto;
import com.gyl.CrudGyl.entity.Cliente;

public class ClienteMapper {
    private ClienteMapper() {

    }

    public static Cliente toEntity(ClienteRequestDto dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());

        return cliente;
    }

    public static ClienteResponseDto toResponseDto(Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }

    public static void updateEntity(Cliente cliente, ClienteRequestDto dto) {
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setEmail(dto.email());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
    }
}
