package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.detalleVenta.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entity.DetalleVenta;

public class DetalleVentaMapper {

    private DetalleVentaMapper() {

    }

    public static DetalleVentaResponseDto toResponseDto(DetalleVenta detalleVenta) {
        return new DetalleVentaResponseDto(
                detalleVenta.getCantidad(),
                detalleVenta.getPrecioUnitario(),
                detalleVenta.getSubtotal(),
                detalleVenta.getProducto().getNombre()
        );
    }
}
