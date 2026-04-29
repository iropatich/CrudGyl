package com.gyl.CrudGyl.service.interfaz;

import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoService {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long id);

    List<TipoProductoResponseDto> buscarNombre(String nombre);

    TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto);

    void eliminar(Long id);
}
