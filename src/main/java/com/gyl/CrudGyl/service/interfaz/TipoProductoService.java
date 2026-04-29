package com.gyl.CrudGyl.service.interfaz;

import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    List<TipoProductoResponseDto> buscarNombre(String nombre);

    TipoProductoResponseDto buscarPorId(Long id);

    TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto);

    void eliminar(Long id);
}
