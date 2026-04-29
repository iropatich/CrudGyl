package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.tipoProducto.TipoProductoResponseDto;
import com.gyl.CrudGyl.entity.TipoProducto;

public class TipoProductoMapper {
    private TipoProductoMapper() {

    }

    public static TipoProducto toEntity(TipoProductoRequestDto dto) {
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setNombre(dto.Nombre());
        tipoProducto.setDescripcion(dto.descripcion());

        return tipoProducto;
    }

    public static TipoProductoResponseDto toResponseDto(TipoProducto tipoProducto) {
        return new TipoProductoResponseDto(
                tipoProducto.getId(),
                tipoProducto.getNombre(),
                tipoProducto.getDescripcion()
        );
    }

    public static void updateEntity(TipoProducto tipoProducto, TipoProductoRequestDto dto) {
        tipoProducto.setNombre(dto.Nombre());
        tipoProducto.setDescripcion(dto.descripcion());
    }
}
