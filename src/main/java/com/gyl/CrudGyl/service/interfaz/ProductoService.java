package com.gyl.CrudGyl.service.interfaz;

import com.gyl.CrudGyl.dto.producto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.producto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {

    ProductoResponseDto crear(ProductoRequestDto producto);

    List<ProductoResponseDto> listar();

    ProductoResponseDto buscarPorId(Long id);

    ProductoResponseDto actualizar(Long id, ProductoRequestDto dto);

    void eliminar(Long id);

    List<ProductoResponseDto> buscarNombre(String nombre);
}
