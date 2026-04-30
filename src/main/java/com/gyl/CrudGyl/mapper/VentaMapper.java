package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.venta.VentaResponseDto;
import com.gyl.CrudGyl.entity.Venta;

public class VentaMapper {

    private VentaMapper() {

    }

    public static VentaResponseDto toResponseDto(Venta venta) {
        return new VentaResponseDto(
                venta.getId(),
                venta.getFecha(),
                venta.getCliente().getNombre(),
                venta.getTotal(),
                venta.getDetallesVenta()
                        .stream()
                        .map(DetalleVentaMapper::toResponseDto)
                        .toList()
        );
    }
}
