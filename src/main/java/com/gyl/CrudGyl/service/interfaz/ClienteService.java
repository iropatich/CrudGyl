package com.gyl.CrudGyl.service.interfaz;

import com.gyl.CrudGyl.dto.cliente.ClienteRequestDto;
import com.gyl.CrudGyl.dto.cliente.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long id);

    List<ClienteResponseDto> buscarNombre(String nombre);

    ClienteResponseDto actualizar(Long id, ClienteRequestDto dto);

    void eliminar(Long id);
}
